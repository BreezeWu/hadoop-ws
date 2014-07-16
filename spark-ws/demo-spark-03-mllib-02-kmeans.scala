// ----------------------------------------------------------------------------
// MLlib - Clustering
//	http://spark.apache.org/docs/latest/mllib-clustering.html

// Load and parse the data
//val data = sc.textFile("data/kmeans_data.txt")
//val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble)))

// begin =========================================== 将用户数据,用电量数据,欠费等信息组合起来
// 目前


// begin ------------------------------ 从hive取数据
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
val rddFromHive = hiveContext.hql("SELECT cons_id, ym, volume_per_month FROM bigdata_arc_volume_perm_s01")

// 转换为vector的方法是
val parsedData = rddFromHive.map(x => Array(
		x(0).toString.toDouble,x(1).toString.toDouble,x(2).toString.toDouble)
	).map(x => Vectors.dense(x)).cache()

// end ------------------------------ 从hive取数据
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
// 	hdfs dfs -cat /user/spark/profiling/k-means/*
// 清空数据 hdfs dfs -rm -r /user/spark/profiling/k-means/

// end =========================================== k-means 及其评估


// ----------------------------------------------------------------------------
// 将"k-means 及其评估"封装为函数,以便多次运行
// 定义函数
//def tryKMeans(k: Int, maxIterations: Int = 20, runs: Int = 1, initializationMode: String)
def tryKMeans(mixK: Int = 2, maxK: Int) = {
	// 基础变量
	var outputDataList = List[String]()
	outputDataList = Nil

	// Cluster the data into two classes using KMeans
	val numClusters = 2
	val numIterations = 20
	
	// ----------------- 执行K-means算法
	// val mixK = 2,maxK = 10
	val range = Range(mixK, maxK)
	for(k <- range) {
		val clusters = KMeans.train(parsedData, k, numIterations)

		// Evaluate clustering by computing Within Set Sum of Squared Errors
		val WSSSE = clusters.computeCost(parsedData)

		// Profiling
		val delimiter:String = 0x01.toChar.toString	// 分隔符 Ctrl+A
		val outputLine = s"$k$delimiter$numIterations$delimiter$WSSSE"
		outputDataList = outputLine.toString :: outputDataList
	}

	// ----------------- 写入文件
	val sparkRoot = "/user/spark/"
	val dmProfiling = sparkRoot + "profiling/"
	val kmeansProfiling = dmProfiling + "k-means"

	val distData = sc.parallelize(outputDataList)
	distData.saveAsTextFile(kmeansProfiling)
	outputDataList
}

tryKMeans(2,10)







