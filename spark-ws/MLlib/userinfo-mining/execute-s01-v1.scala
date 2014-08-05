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
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/clusteringUserInfo-v1.scala

// ----------------------------------------------------------------------------
// 任务1： 寻找最佳K

val minK = 2
val maxK = 200
val maxIterations = 20 // 当前没有生效

def taskName_GoodM1(taskNamePre:String) = taskNamePre + "_" + "GoodM1"
def taskName_GoodM2(taskNamePre:String) = taskNamePre + "_" + "GoodM2"
def taskName_BadF3(taskNamePre:String) = taskNamePre + "_" + "BadF3"
def taskName_BadF2ExcludeF3(taskNamePre:String) = taskNamePre + "_" + "BadF2ExcludeF3"

val taskNamePre = "S98"
// --------------------------
// 1. 有效数据
// (1) 单月数据
val taskName_GoodM1 = taskNamePre + "_" + "GoodM1"
val resultAccount_GoodM1 = tryKMeansSmart(parsedData_GoodM1,minK,maxK,maxIterations)
val resultW2HDFS_GoodM1 = writeAccount2HDFS(resultAccount_GoodM1,2,taskName_GoodM1(taskNamePre))
// (2) 双月数据
val taskName_GoodM2 = taskNamePre + "_" + "GoodM2"
val resultAccount_GoodM2 = tryKMeansSmart(parsedData_GoodM2,minK,maxK,maxIterations)
val resultW2HDFS_GoodM2 = writeAccount2HDFS(resultAccount_GoodM2,2,taskName_GoodM2(taskNamePre))	
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL
val taskName_BadF3 = taskNamePre + "_" + "BadF3"
val resultAccount_BadF3 = tryKMeansSmart(parsedData_BadF3,minK,maxK,maxIterations)
val resultW2HDFS_BadF3 = writeAccount2HDFS(resultAccount_BadF3,2,taskName_BadF3(taskNamePre))
// (2) 仅前两个月都是 0或NULL
val taskName_BadF2ExcludeF3 = taskNamePre + "_" + "BadF2ExcludeF3"
val resultAccount_BadF2ExcludeF3 = tryKMeansSmart(parsedData_BadF2ExcludeF3,minK,maxK,maxIterations)
val resultW2HDFS_BadF2ExcludeF3 = writeAccount2HDFS(resultAccount_BadF2ExcludeF3,2,taskName_BadF2ExcludeF3(taskNamePre))

// ----------------------------------------------------------------------------
// 任务2： 最佳K的分群
// --------------------------
// 1. 有效数据
// (1) 单月数据
val resultClusterInfo_GoodM1 = getClusteringUserInfoFromAccount(parsedData_GoodM1, resultAccount_GoodM1) 
val resultWClusterInfo2HDFS_GoodM1 = writeClusterInfo2HDFS(resultClusterInfo_GoodM1, taskName_GoodM1(taskNamePre))  
// (2) 双月数据
val resultClusterInfo_GoodM2 = getClusteringUserInfoFromAccount(parsedData_GoodM2, resultAccount_GoodM2) 
val resultWClusterInfo2HDFS_GoodM2 = writeClusterInfo2HDFS(resultClusterInfo_GoodM2, taskName_GoodM2(taskNamePre))  	
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL
val resultClusterInfo_BadF3 = getClusteringUserInfoFromAccount(parsedData_BadF3, resultAccount_BadF3) 
val resultWClusterInfo2HDFS_BadF3 = writeClusterInfo2HDFS(resultClusterInfo_BadF3, taskName_BadF3(taskNamePre))
// (2) 仅前两个月都是 0或NULL
val resultClusterInfo_BadF2ExcludeF3 = getClusteringUserInfoFromAccount(parsedData_BadF2ExcludeF3, resultAccount_BadF2ExcludeF3)
val resultWClusterInfo2HDFS_BadF2ExcludeF3 = writeClusterInfo2HDFS(resultClusterInfo_BadF2ExcludeF3, taskName_BadF2ExcludeF3(taskNamePre))

// 统一显示一下结果变量
resultAccount_GoodM1
resultAccount_GoodM2
resultAccount_BadF3
resultAccount_BadF2ExcludeF3
/*
println(resultAccount_GoodM1.toRecordOfMetricList)
println(resultAccount_GoodM.toRecordOfMetricList)
println(resultAccount_BadF3.toRecordOfMetricList)
println(resultAccount_BadF2ExcludeF3.toRecordOfMetricList)
*/

resultClusterInfo_GoodM1.clusterCount
resultClusterInfo_GoodM2.clusterCount
resultClusterInfo_BadF3.clusterCount
resultClusterInfo_BadF2ExcludeF3.clusterCount
/*
resultClusterInfo_GoodM1.account
resultClusterInfo_GoodM2.account
resultClusterInfo_BadF3.account
resultClusterInfo_BadF2ExcludeF3.account
*/

// 统一回显一下HDFS路径
resultW2HDFS_GoodM1
resultW2HDFS_GoodM2
resultW2HDFS_BadF3
resultW2HDFS_BadF2ExcludeF3

resultWClusterInfo2HDFS_GoodM1._2
resultWClusterInfo2HDFS_GoodM2._2
resultWClusterInfo2HDFS_BadF3._2
resultWClusterInfo2HDFS_BadF2ExcludeF3._2

// ----------------------------------------------------------------------------
// 任务3： 手工指定最佳K
// --------------------------
/*
val perfectK = 20;
val maxIterations = 20 // 当前没有生效

var taskNamePre = "S98_perfectK" + perfectK

// 1. 有效数据
// (1) 单月数据
val resultClusterInfoSpecial_GoodM1 = ClusteringUserInfo(parsedData_GoodM1, perfectK, maxIterations) 
val resultWClusterInfoSpecial2HDFS_GoodM1 = writeClusterInfo2HDFS(resultClusterInfoSpecial_GoodM1, taskName_GoodM1(taskNamePre))  
// (2) 双月数据
val resultClusterInfoSpecial_GoodM2 = ClusteringUserInfo(parsedData_GoodM2, perfectK, maxIterations)  
val resultWClusterInfoSpecial2HDFS_GoodM2 = writeClusterInfo2HDFS(resultClusterInfoSpecial_GoodM2, taskName_GoodM2(taskNamePre))  	
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL
val resultClusterInfoSpecial_BadF3 = ClusteringUserInfo(parsedData_BadF3, perfectK, maxIterations) 
val resultWClusterInfoSpecial2HDFS_BadF3 = writeClusterInfo2HDFS(resultClusterInfoSpecial_BadF3, taskName_BadF3(taskNamePre))
// (2) 仅前两个月都是 0或NULL
val resultClusterInfoSpecial_BadF2ExcludeF3 = ClusteringUserInfo(parsedData_BadF2ExcludeF3, perfectK, maxIterations) 
val resultWClusterInfoSpecial2HDFS_BadF2ExcludeF3 = writeClusterInfo2HDFS(resultClusterInfoSpecial_BadF2ExcludeF3, taskName_BadF2ExcludeF3(taskNamePre))

// 统一显示一下结果变量
resultClusterInfoSpecial_GoodM1.clusterCount
resultClusterInfoSpecial_GoodM2.clusterCount
resultClusterInfoSpecial_BadF3.clusterCount
resultClusterInfoSpecial_BadF2ExcludeF3.clusterCount

resultClusterInfoSpecial_GoodM1.account
resultClusterInfoSpecial_GoodM2.account
resultClusterInfoSpecial_BadF3.account
resultClusterInfoSpecial_BadF2ExcludeF3.account

// 统一回显一下HDFS路径
resultWClusterInfoSpecial2HDFS_GoodM1._2
resultWClusterInfoSpecial2HDFS_GoodM2._2
resultWClusterInfoSpecial2HDFS_BadF3._2
resultWClusterInfoSpecial2HDFS_BadF2ExcludeF3._2
*/
