// --------------------------------------------------------------------------
// 使用方法
//	在spark-shell中执行  (注意"1.加载数据"的语句 和  taskNamePre 的设置即可)
//		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/execute-tryKMeansSmart-ClusterCount.scala.scala

val datasetID="s01" //s01 s98
// 1. 加载数据 
// (1)样本数据
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s01.scala
// (2)全量数据
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s98.scala

// 百分比方式
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-percent.scala


// 2. 加载函数
// 聚类函数
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/tryKMeansSmart.scala
// 根据聚类模型计算ClusterCount
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/ComputeClusterCount.scala

// 设置变量
val taskNamePre = datasetID
// ----------------------------------------------------------------------------
// 任务1： 寻找最佳K
val minK = 2
val maxK = 1000
val maxIterations = 20 // 当前没有生效

def taskName_GoodM1(taskNamePre:String) = taskNamePre + "_" + "GoodM1"
def taskName_GoodM2(taskNamePre:String) = taskNamePre + "_" + "GoodM2"
def taskName_BadF3(taskNamePre:String) = taskNamePre + "_" + "BadF3"
def taskName_BadF2ExcludeF3(taskNamePre:String) = taskNamePre + "_" + "BadF2ExcludeF3"
// --------------------------
// 1. 有效数据
// (1) 单月数据
val resultAccount_GoodM1 = tryKMeansSmart(parsedData_GoodM1,minK,maxK,maxIterations)
val resultW2HDFS_GoodM1 = writeAccount2HDFS(resultAccount_GoodM1,2,taskName_GoodM1(taskNamePre))
// (2) 双月数据
val resultAccount_GoodM2 = tryKMeansSmart(parsedData_GoodM2,minK,maxK,maxIterations)
val resultW2HDFS_GoodM2 = writeAccount2HDFS(resultAccount_GoodM2,2,taskName_GoodM2(taskNamePre))	
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL
val resultAccount_BadF3 = tryKMeansSmart(parsedData_BadF3,minK,maxK,maxIterations)
val resultW2HDFS_BadF3 = writeAccount2HDFS(resultAccount_BadF3,2,taskName_BadF3(taskNamePre))
// (2) 仅前两个月都是 0或NULL
val resultAccount_BadF2ExcludeF3 = tryKMeansSmart(parsedData_BadF2ExcludeF3,minK,maxK,maxIterations)
val resultW2HDFS_BadF2ExcludeF3 = writeAccount2HDFS(resultAccount_BadF2ExcludeF3,2,taskName_BadF2ExcludeF3(taskNamePre))

// ----------------------------------------------------------------------------
// 任务2： 计算簇数量（方式一，利用任务1的计算结果获得最佳K和最佳模型)
// --------------------------
// 1. 有效数据
// (1) 单月数据
val resultClusterCountInfo_GoodM1 = ComputeClusterCount_FromAccount(parsedData_GoodM1, resultAccount_GoodM1) 
val resultWClusterCountInfo2HDFS_GoodM1 = writeClusterCountInfo2HDFS(resultClusterCountInfo_GoodM1, taskName_GoodM1(taskNamePre))  
// (2) 双月数据
val resultClusterCountInfo_GoodM2 = ComputeClusterCount_FromAccount(parsedData_GoodM2, resultAccount_GoodM2) 
val resultWClusterCountInfo2HDFS_GoodM2 = writeClusterCountInfo2HDFS(resultClusterCountInfo_GoodM2, taskName_GoodM2(taskNamePre))  	
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL
val resultClusterCountInfo_BadF3 = ComputeClusterCount_FromAccount(parsedData_BadF3, resultAccount_BadF3) 
val resultWClusterCountInfo2HDFS_BadF3 = writeClusterCountInfo2HDFS(resultClusterCountInfo_BadF3, taskName_BadF3(taskNamePre))
// (2) 仅前两个月都是 0或NULL
val resultClusterCountInfo_BadF2ExcludeF3 = ComputeClusterCount_FromAccount(parsedData_BadF2ExcludeF3, resultAccount_BadF2ExcludeF3)
val resultWClusterCountInfo2HDFS_BadF2ExcludeF3 = writeClusterCountInfo2HDFS(resultClusterCountInfo_BadF2ExcludeF3, taskName_BadF2ExcludeF3(taskNamePre))

// ----------------------------------------------------------------------------
// 任务2： 计算簇数量（方式二，手工指定最佳K，重新计算模型，然后...）
// --------------------------
val perfectK = 20;
val maxIterations = 20 // 当前没有生效

var taskNamePre_perfect = s"${taskNamePre}_perfectK" + perfectK

// 1. 有效数据
// (1) 单月数据
val resultClusterCountInfo_Special_GoodM1 = ComputeClusterCount_Standalone(parsedData_GoodM1, perfectK, maxIterations) 
val resultWClusterCountInfo2HDFS_Special_GoodM1 = writeClusterCountInfo2HDFS(resultClusterCountInfo_Special_GoodM1, taskName_GoodM1(taskNamePre_perfect))  
// (2) 双月数据
val resultClusterCountInfo_Special_GoodM2 = ComputeClusterCount_Standalone(parsedData_GoodM2, perfectK, maxIterations)  
val resultWClusterCountInfo2HDFS_Special_GoodM2 = writeClusterCountInfo2HDFS(resultClusterCountInfo_Special_GoodM2, taskName_GoodM2(taskNamePre_perfect))  	
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL
val resultClusterCountInfo_Special_BadF3 = ComputeClusterCount_Standalone(parsedData_BadF3, perfectK, maxIterations) 
val resultWClusterCountInfo2HDFS_Special_BadF3 = writeClusterCountInfo2HDFS(resultClusterCountInfo_Special_BadF3, taskName_BadF3(taskNamePre_perfect))
// (2) 仅前两个月都是 0或NULL
val resultClusterCountInfo_Special_BadF2ExcludeF3 = ComputeClusterCount_Standalone(parsedData_BadF2ExcludeF3, perfectK, maxIterations) 
val resultWClusterCountInfo2HDFS_Special_BadF2ExcludeF3 = writeClusterCountInfo2HDFS(resultClusterCountInfo_Special_BadF2ExcludeF3, taskName_BadF2ExcludeF3(taskNamePre_perfect))

// ----------------------------------------------------------------------------
//  统一显示一下结果变量
// 任务1
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
// 任务2, 方式一
resultClusterCountInfo_GoodM1.clusterCount
resultClusterCountInfo_GoodM2.clusterCount
resultClusterCountInfo_BadF3.clusterCount
resultClusterCountInfo_BadF2ExcludeF3.clusterCount
/*
resultClusterCountInfo_GoodM1.account
resultClusterCountInfo_GoodM2.account
resultClusterCountInfo_BadF3.account
resultClusterCountInfo_BadF2ExcludeF3.account
*/

// 任务2, 方式二
resultClusterCountInfo_Special_GoodM1.clusterCount
resultClusterCountInfo_Special_GoodM2.clusterCount
resultClusterCountInfo_Special_BadF3.clusterCount
resultClusterCountInfo_Special_BadF2ExcludeF3.clusterCount

resultClusterCountInfo_Special_GoodM1.account
resultClusterCountInfo_Special_GoodM2.account
resultClusterCountInfo_Special_BadF3.account
resultClusterCountInfo_Special_BadF2ExcludeF3.account

// ----------------------------------------------------------------------------
// 统一回显一下HDFS路径
// 任务1
resultW2HDFS_GoodM1
resultW2HDFS_GoodM2
resultW2HDFS_BadF3
resultW2HDFS_BadF2ExcludeF3

// 任务2, 方式一
resultWClusterCountInfo2HDFS_GoodM1._2
resultWClusterCountInfo2HDFS_GoodM2._2
resultWClusterCountInfo2HDFS_BadF3._2
resultWClusterCountInfo2HDFS_BadF2ExcludeF3._2

// 任务2, 方式二
resultWClusterCountInfo2HDFS_Special_GoodM1._2
resultWClusterCountInfo2HDFS_Special_GoodM2._2
resultWClusterCountInfo2HDFS_Special_BadF3._2
resultWClusterCountInfo2HDFS_Special_BadF2ExcludeF3._2
