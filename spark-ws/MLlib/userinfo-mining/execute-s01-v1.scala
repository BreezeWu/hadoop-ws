// --------------------------------------------------------------------------
// 使用方法
//	在spark-shell中执行  (注意"1.加载数据"的语句即可)
//		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/execute-s01-v1.scala

// 1. 加载数据 
// (1)样本数据
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s01-v1.scala
// (2)全量数据
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s98-v1.scala

// 2. 加载函数
// 聚类函数
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/tryKMeansSmart.scala
// 根据聚类模型分配clusterID
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/clusteringUserInfo.scala

// ----------------------------------------------------------------------------
// 任务1： 寻找最佳K

val minK = 2
val maxK = 200
val maxIterations = 20 // 当前没有生效

val dataType = "S98"
// --------------------------
// 1. 有效数据
// (1) 单月数据
val taskName_GoodM1 = dataType + "_" + "GoodM1"
val resultAccount_GoodM1 = tryKMeansSmart(parsedData_GoodM1,minK,maxK,maxIterations)
val resultW2HDFS_GoodM1 = writeAccount2HDFS(resultAccount_GoodM1,2,taskName_GoodM1)
// (2) 双月数据
val taskName_GoodM2 = dataType + "_" + "GoodM2"
val resultAccount_GoodM2 = tryKMeansSmart(parsedData_GoodM2,minK,maxK,maxIterations)
val resultW2HDFS_GoodM2 = writeAccount2HDFS(resultAccount_GoodM2,2,taskName_GoodM2)	
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL
val taskName_BadF3 = dataType + "_" + "BadF3"
val resultAccount_BadF3 = tryKMeansSmart(parsedData_BadF3,minK,maxK,maxIterations)
val resultW2HDFS_BadF3 = writeAccount2HDFS(resultAccount_BadF3,2,taskName_BadF3)
// (2) 仅前两个月都是 0或NULL
val taskName_BadF2ExcludeF3 = dataType + "_" + "BadF2ExcludeF3"
val resultAccount_BadF2ExcludeF3 = tryKMeansSmart(parsedData_BadF2ExcludeF3,minK,maxK,maxIterations)
val resultW2HDFS_BadF2ExcludeF3 = writeAccount2HDFS(resultAccount_BadF2ExcludeF3,2,taskName_BadF2ExcludeF3)

// ----------------------------------------------------------------------------
// 任务2： 最佳K的分群
// --------------------------
// 1. 有效数据
// (1) 单月数据
val resultClusterInfo_GoodM1 = getClusteringUserInfoFromAccount(parsedData_GoodM1, resultW2HDFS_GoodM1) 
val resultWClusterInfo2HDFS_GoodM1 = writeClusterInfo2HDFS(resultClusterInfo_GoodM1, taskName_GoodM1)  
// (2) 双月数据
val resultClusterInfo_GoodM2 = getClusteringUserInfoFromAccount(parsedData_GoodM2, resultW2HDFS_GoodM2) 
val resultWClusterInfo2HDFS_GoodM2 = writeClusterInfo2HDFS(resultClusterInfo_GoodM2, taskName_GoodM2)  	
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL
val resultClusterInfo_BadF3 = getClusteringUserInfoFromAccount(parsedData_BadF3, resultW2HDFS_BadF3) 
val resultWClusterInfo2HDFS_BadF3 = writeClusterInfo2HDFS(resultClusterInfo_BadF3, taskName_BadF3)
// (2) 仅前两个月都是 0或NULL
val resultClusterInfo_BadF2ExcludeF3 = getClusteringUserInfoFromAccount(parsedData_BadF2ExcludeF3, resultW2HDFS_BadF2ExcludeF3)
val resultWClusterInfo2HDFS_BadF2ExcludeF3 = writeClusterInfo2HDFS(resultClusterInfo_BadF2ExcludeF3, taskName_BadF2ExcludeF3)

// 统一显示一下结果变量
resultW2HDFS_GoodM1
resultW2HDFS_GoodM2
resultW2HDFS_BadF3
resultW2HDFS_BadF2ExcludeF3

resultWClusterInfo2HDFS_GoodM1
resultWClusterInfo2HDFS_GoodM2
resultWClusterInfo2HDFS_BadF3
resultWClusterInfo2HDFS_BadF2ExcludeF3

