﻿===================
# 在 spark-shell 中 运行
	// spark-shell的启动
	//        SPARK_EXECUTOR_INSTANCES=12 SPARK_EXECUTOR_MEMORY=2G SPARK_DRIVER_MEMORY=1G spark-shell

    // 1. 加载数据 
    // 最后生成 parsedData:org.apache.spark.rdd.RDD[org.apache.spark.mllib.linalg.Vector]
    //:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/create-KMeansModel-parsedData-s01-all.scala
    :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/create-KMeansModel-parsedData-s01-m1m2.scala
    // 或者
    // :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/create-KMeansModel-parsedData-s98-all.scala
    :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/create-KMeansModel-parsedData-s98-m1m2.scala

    // 2. 加载函数
    :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/tryKMeansSmart.scala

    // 3. 使用函数进行分析
    // (1). 实际数据
    // 数据, 起始k, 最大k, KMeans.train中的maxIterations
    //val resultAccount = tryKMeansSmart(parsedData,1,50,20)	//所有用户数据
    //val resultAccountM1 = tryKMeansSmart(parsedDataM1,1,100,20)
    //val resultAccountM2 = tryKMeansSmart(parsedDataM2,1,100,20)
    // (2). 随机数模拟
    // 若evalWSSSEOfK是随即数模拟
    // val resultAccount = tryKMeansSmart(null,1,50,20)

    // 4. 将结果写入HDFS
    // 参数: sortedType,排序方式	0-默认,即计算k的顺序; 1-按照k从小到大排序; 2-两种排序方式都写入
    //writeMetricList2HDFS(resultAccount,2)
    //writeMetricList2HDFS(resultAccountM1,2)
    //writeMetricList2HDFS(resultAccountM2,2)
    
    // 3-4
	val resultAccountM2 = tryKMeansSmart(parsedDataM2,2,6000,20)
	val rr1 = writeMetricList2HDFS(resultAccountM1,2)
	
	val resultAccountM1 = tryKMeansSmart(parsedDataM1,2,2000,20)
	val rr2 = writeMetricList2HDFS(resultAccountM2,2)

	rr1
	rr2

===================
# -----------------------------------------------------------------------------
# metrics数据
# 默认排序的
hadoop@master-hadoop:~/workspace_github/hadoop-ws/sparkR-ws$ hdfs dfs -cat hdfs://master-hadoop:9000//user/spark/metric/k-means-2014-07-23-21-14-41_c10_try10/*

1, 20, 4.477062216021369E12, 2014-07-23 21:14:41, 2014-07-23 21:15:05, org.apache.spark.mllib.clustering.KMeansModel@14a0cb16
25, 20, 1.2250575933088956E11, 2014-07-23 21:15:14, 2014-07-23 21:15:20, org.apache.spark.mllib.clustering.KMeansModel@1e14775c
37, 20, 5.9178515066331055E10, 2014-07-23 21:15:20, 2014-07-23 21:15:26, org.apache.spark.mllib.clustering.KMeansModel@170a0487
40, 20, 5.967428611173654E10, 2014-07-23 21:15:32, 2014-07-23 21:15:38, org.apache.spark.mllib.clustering.KMeansModel@3f4ee455
41, 20, 5.578072841191458E10, 2014-07-23 21:15:38, 2014-07-23 21:15:44, org.apache.spark.mllib.clustering.KMeansModel@143906e6
42, 20, 5.0378695096408035E10, 2014-07-23 21:15:44, 2014-07-23 21:15:49, org.apache.spark.mllib.clustering.KMeansModel@1b6a7413
43, 20, 4.226707644702229E10, 2014-07-23 21:15:26, 2014-07-23 21:15:32, org.apache.spark.mllib.clustering.KMeansModel@279f741
44, 20, 4.448979207088634E10, 2014-07-23 21:15:56, 2014-07-23 21:16:02, org.apache.spark.mllib.clustering.KMeansModel@13af4141
46, 20, 4.265166731277183E10, 2014-07-23 21:15:49, 2014-07-23 21:15:56, org.apache.spark.mllib.clustering.KMeansModel@29d6045
50, 20, 4.344414598330335E10, 2014-07-23 21:15:05, 2014-07-23 21:15:14, org.apache.spark.mllib.clustering.KMeansModel@58087a79


# 按照k排序的
hadoop@master-hadoop:~/workspace_github/hadoop-ws/sparkR-ws$ hdfs dfs -rm -R hdfs://master-hadoop:9000//user/spark/metric/k-means-2014-07-23-21-14-41_c10_try10/
14/07/23 20:03:22 INFO fs.TrashPolicyDefault: Namenode trash configuration: Deletion interval = 0 minutes, Emptier interval = 0 minutes.
Deleted hdfs://master-hadoop:9000/user/spark/metric/k-means-2014-07-23-21-14-41_c10_try10
hadoop@master-hadoop:~/workspace_github/hadoop-ws/sparkR-ws$ hdfs dfs -cat hdfs://master-hadoop:9000//user/spark/metric/k-means-2014-07-23-21-14-41_c10_try10/*

1, 20, 4.477062216021369E12, 2014-07-23 21:14:41, 2014-07-23 21:15:05, org.apache.spark.mllib.clustering.KMeansModel@14a0cb16
50, 20, 4.344414598330335E10, 2014-07-23 21:15:05, 2014-07-23 21:15:14, org.apache.spark.mllib.clustering.KMeansModel@58087a79
25, 20, 1.2250575933088956E11, 2014-07-23 21:15:14, 2014-07-23 21:15:20, org.apache.spark.mllib.clustering.KMeansModel@1e14775c
37, 20, 5.9178515066331055E10, 2014-07-23 21:15:20, 2014-07-23 21:15:26, org.apache.spark.mllib.clustering.KMeansModel@170a0487
43, 20, 4.226707644702229E10, 2014-07-23 21:15:26, 2014-07-23 21:15:32, org.apache.spark.mllib.clustering.KMeansModel@279f741
40, 20, 5.967428611173654E10, 2014-07-23 21:15:32, 2014-07-23 21:15:38, org.apache.spark.mllib.clustering.KMeansModel@3f4ee455
41, 20, 5.578072841191458E10, 2014-07-23 21:15:38, 2014-07-23 21:15:44, org.apache.spark.mllib.clustering.KMeansModel@143906e6
42, 20, 5.0378695096408035E10, 2014-07-23 21:15:44, 2014-07-23 21:15:49, org.apache.spark.mllib.clustering.KMeansModel@1b6a7413
46, 20, 4.265166731277183E10, 2014-07-23 21:15:49, 2014-07-23 21:15:56, org.apache.spark.mllib.clustering.KMeansModel@29d6045
44, 20, 4.448979207088634E10, 2014-07-23 21:15:56, 2014-07-23 21:16:02, org.apache.spark.mllib.clustering.KMeansModel@13af4141


# -----------------------------------------------------------------------------
# 最佳k的簇中心数据
hadoop@master-hadoop:~/workspace_github/hadoop-ws/sparkR-ws$ hdfs dfs -cat hdfs://master-hadoop:9000/user/spark/clustercenters/k-means-2014-07-23-21-14-41_bestK43_clusterCenters/*

...略...
# -----------------------------------------------------------------------------
# 其他

# 清空hdfs中的数据
# hdfs dfs -rm -R hdfs://master-hadoop:9000/user/spark/metric/k-means-2014-07-23-21-14-41_c10_try10
# hdfs dfs -rm -R hdfs://master-hadoop:9000/user/spark/clustercenters/k-means-2014-07-23-21-14-41_bestK43_clusterCenters
