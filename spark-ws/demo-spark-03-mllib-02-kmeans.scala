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
	).map(x => Vectors.dense(x))

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

// end =========================================== k-means 及其评估
