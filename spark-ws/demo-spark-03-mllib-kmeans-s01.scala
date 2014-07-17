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

// 转换为vector,最后变为matrices
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
val rddFromHive = hiveContext.hql("SELECT  vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312,vpm201401,vpm201402,vpm201403,vpm201404,vpm201405,vpm201406 ,rcved_amt201301,rcved_amt201302,rcved_amt201303,rcved_amt201304,rcved_amt201305,rcved_amt201306,rcved_amt201307,rcved_amt201308,rcved_amt201309,rcved_amt201310,rcved_amt201311,rcved_amt201312,rcved_amt201401,rcved_amt201402,rcved_amt201403,rcved_amt201404,rcved_amt201405,rcved_amt201406  ,rcvbl_amt201301,rcvbl_amt201302,rcvbl_amt201303,rcvbl_amt201304,rcvbl_amt201305,rcvbl_amt201306,rcvbl_amt201307,rcvbl_amt201308,rcvbl_amt201309,rcvbl_amt201310,rcvbl_amt201311,rcvbl_amt201312,rcvbl_amt201401,rcvbl_amt201402,rcvbl_amt201403,rcvbl_amt201404,rcvbl_amt201405,rcvbl_amt201406  ,owning_amt201301,owning_amt201302,owning_amt201303,owning_amt201304,owning_amt201305,owning_amt201306,owning_amt201307,owning_amt201308,owning_amt201309,owning_amt201310,owning_amt201311,owning_amt201312,owning_amt201401,owning_amt201402,owning_amt201403,owning_amt201404,owning_amt201405,owning_amt201406  FROM BIGDATA_USER_INFO_S01_V_FOR_CLUSTERING x")  // 没有 cons_id

// ------------------------ begin: 转换为vector,最后变为matrices
// 定义函数:对一个org.apache.spark.sql.Row中每一个列的处理函数
// 函数原型为: g: (y: Any)Double
def g(y:Any):Double = y match {
	case null => 0		// 将null转换为0
	case i:Int => i.toDouble	// 将Int转换为Double
	case d:Double => d
}

// 定义函数: 对每一个org.apache.spark.sql.Row对象的处理函数
// 原型为: f: (x: org.apache.spark.sql.Row)Seq[Double]
def f(x:org.apache.spark.sql.Row) = {
	x.map(y => g(y))
}

// 转换为vector,最后变为matrices
val parsedData = rddFromHive.map(x => f(x)).	// 去掉null,转换为Double
	map(x => Vectors.dense(x.toArray))	// 转变为Vector, 构建matrices

// 测试 g(y)
// 对一个org.apache.spark.sql.Row中每一个列的处理
rddFromHive.first.map(y => g(y))
/* 
1. rddFromHive.first是:
res19: org.apache.spark.sql.Row = [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null]
2. 转换后的结果是:
res20: Seq[Double] = List(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
*/


// ------------------------ end: 转换为vector,最后变为matrices

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
val maxK = 40
val numIterations = 20
tryKMeans(parsedData,minK,maxK, numIterations)

// 从结果中画图,识别出拐点
// .......
// 27^A20^A1.0115298147379333E11
val perfectK = 27
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
val perfectK = 27
val numIterations = 20
perfectKMeans(parsedData, perfectK, numIterations)

// 将原始RDD写入hdfs
parsedData.saveAsTextFile("/user/spark/model/k-means-2014-07-17-16-16-49.perfectK-27.data")








