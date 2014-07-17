// ----------------------------------------------------------------------------
// MLlib - Clustering
//	http://spark.apache.org/docs/latest/mllib-clustering.html

// Load and parse the data
//val data = sc.textFile("data/kmeans_data.txt")
//val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble)))

// begin =========================================== 将用户数据,用电量数据,欠费等信息组合起来

// begin ------------------------------ 从hive取数据 ###随便找的数据###
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
val rddFromHive = hiveContext.hql("SELECT cons_id, ym, volume_per_month FROM bigdata_arc_volume_perm_s01")

// 转换为vector的方法是
val parsedData = rddFromHive.map(x => Array(
		x(0).toString.toDouble,x(1).toString.toDouble,x(2).toString.toDouble)
	).map(x => Vectors.dense(x)).cache()

// end ------------------------------ 从hive取数据 ###随便找的数据###

// 具体脚本请参见 ~/workspace_github/hadoop-ws/hive-ws/ 下的 preprocess-dm2014-userinfoClustering.sql 脚本
// ############################## 不在这里再次描述了!!! ##############################
// 用于聚类的hive数据表是 BIGDATA_USER_INFO_S01_V_FOR_CLUSTERING

// begin ------------------------------ 从hive取数据 ###2万用户的数据###
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
// val rddFromHive = hiveContext.hql("SELECT vpm*,rcved_amt*,rcvbl_amt*,owning_amt*,inspect_count FROM BIGDATA_USER_INFO_S01_V_FOR_CLUSTERING")
val rddFromHive = hiveContext.hql("SELECT vpm*,rcved_amt*,rcvbl_amt*,owning_amt*,inspect_count FROM BIGDATA_USER_INFO_S01_V_FOR_CLUSTERING")

// 转换为vector,最后变为matrices
val parsedData = rddFromHive.map(x => Array(
		x(0).toString.toDouble,x(1).toString.toDouble,x(2).toString.toDouble)
	).map(x => Vectors.dense(x)).cache()

// end ------------------------------ 从hive取数据 ###2万用户的数据###

// end =========================================== 将用户数据,用电量数据,欠费等信息组合起来

// begin =========================================== k-means 及其评估
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors

// Cluster the data into two classes using KMeans
val numClusters = 2
val numIterations = 20
val clusters = KMeans.train(parsedData, numClusters, numIterations)

// Evaluate clustering by computing Within Set Sum of Squared Errors
val WSSSE = clusters.computeCost(parsedData)
println("Within Set Sum of Squared Errors = " + WSSSE)

val sparkRoot = "/user/spark/"
val dmProfiling = sparkRoot + "profiling/"
val kmeansProfiling = dmProfiling + "k-means"

// numClusters,numIterations,
// 分隔符
val delimiter:String = 0x01.toChar.toString

val outputLine = s"$numClusters$delimiter$numIterations$delimiter$WSSSE"
val outputData = List(outputLine)
val distData = sc.parallelize(outputData)
distData.saveAsTextFile(kmeansProfiling)

// hdfs查看结果
// 	hdfs dfs -ls /user/spark/profiling/k-means
// 	hdfs dfs -cat /user/spark/profiling/k-means/
// 清空数据 hdfs dfs -rm -r /user/spark/profiling/k-means/

// end =========================================== k-means 及其评估

// ----------------------------------------------------------------------------
// 将"k-means 及其评估"封装为函数,以便多次运行,以找出最小WSSSE的k值
// 定义函数

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors

import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector

//def train(data: org.apache.spark.rdd.RDD[org.apache.spark.mllib.linalg.Vector], k: Int, maxIterations: Int, runs: Int, initializationMode: String): KMeansModel  
def tryKMeans(data: RDD[Vector], mixK: Int = 2, maxK: Int, maxIterations: Int = 20) = {
	// 基础变量
	var outputDataList = List[String]()
	outputDataList = Nil

	// Cluster the data into two classes using KMeans
	val numClusters = 2
	val numIterations = maxIterations	// 默认值为 20
	
	// ----------------- 执行K-means算法
	// val mixK = 2,maxK = 10
	val range = Range(mixK, maxK)
	for(k <- range) {
		val clusters = KMeans.train(data, k, numIterations)

		// Evaluate clustering by computing Within Set Sum of Squared Errors
		val WSSSE = clusters.computeCost(parsedData)

		// Profiling
		val delimiter:String = 0x01.toChar.toString	// 分隔符 Ctrl+A
		val outputLine = s"$k$delimiter$numIterations$delimiter$WSSSE"
		outputDataList = outputLine.toString :: outputDataList
	}

	// ----------------- 写入文件 文件名后面有日期
	import java.util.Date
	val format = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")	// "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
	val dateString = format.format(new java.util.Date())
	// format.parse("21-03-2011")

	val sparkRoot = "/user/spark/"
	val dmProfiling = sparkRoot + "profiling/"
	val kmeansProfilingPath = dmProfiling + "k-means-" + dateString

	val distData = sc.parallelize(outputDataList)
	distData.saveAsTextFile(kmeansProfilingPath)
	
	// 函数返回值
	Tuple2(kmeansProfilingPath, outputDataList)
}

// 应用自定义函数
val minK = 2
val maxK = 10
val numIterations = 20
tryKMeans(parsedData,minK,maxK, numIterations)

// 从结果中画图,识别出拐点
// .......
val perfectK = 4
// ----------------------------------------------------------------------------
// 找到最佳k之后,再次执行获得其clusters信息 (或者,从spark缓存中获得)
// 定义函数
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors

import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector

def perfectKMeans(data: RDD[Vector], perfectK: Int, maxIterations: Int = 20) = {
	val clusters = KMeans.train(data, perfectK, maxIterations)

	val sparkRoot = "/user/spark/"
	val dmModel = sparkRoot + "model/"

	val format = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")	// "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
	val dateString = format.format(new java.util.Date())

	val kmeansModelPath = dmModel + "k-means-" + dateString + ".perfectK-" + perfectK + ".clusterCenters"

	// 将簇中心转换为RDD,并写入文件
	val distData = sc.parallelize(clusters.clusterCenters)
	distData.saveAsTextFile(kmeansModelPath)

	// 函数返回值
	Tuple2(kmeansModelPath, clusters)
}


// 调用函数
val perfectK = 4
val numIterations = 20
perfectKMeans(parsedData, perfectK, numIterations)








