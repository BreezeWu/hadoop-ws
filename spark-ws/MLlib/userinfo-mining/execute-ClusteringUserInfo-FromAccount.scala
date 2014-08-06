// --------------------------------------------------------------------------
// 对用户进行分群: 依赖模式
//		依赖于 execute-s98-v1.scala/execute-s01-v1.scala, 在他们成功执行之后运行
//
//	在spark-shell中执行  (注意"1.加载数据"的语句即可)
//		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/execute-ClusteringUserInfo-FromAccount.scala

// ****************************************************************************
// 1. 加载数据与函数
// ****************************************************************************
// (1)样本数据
// 下面一行 --已经在 execute-s98-v1.scala/execute-s01-v1.scala 中加载
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s01.scala
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedDataIndexed-userinfo-s01.scala
// (2)全量数据
// 下面一行 --已经在 execute-s98-v1.scala/execute-s01-v1.scala 中加载
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s98.scala
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedDataIndexed-userinfo-s98.scala

// 2. 加载函数
// 聚类函数 --已经在 execute-s98-v1.scala/execute-s01-v1.scala 中加载
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/tryKMeansSmart.scala
// 根据聚类模型计算ClusterCount --已经在 execute-s98-v1.scala/execute-s01-v1.scala 中加载
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/ComputeClusterCount.scala

// 给索引数据分配clusterID
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/ClusteringUserInfo.scala

// ****************************************************************************
// 执行
// 方式2： ClusteringUserInfo_FromAccount 模式
//      在 execute-s01-v1.scala 或 execute-s98-v1.scala 执行之后执行
// ****************************************************************************
// ----------------------------------------------------------------------------
// 一。 从“查找最佳K”中继承
// 1. 有效数据
// (1) 单月数据 GoodM1
val clusteredInfo_GoodM1 = ClusteringUserInfo_FromAccount(parsedDataIndexed_GoodM1, resultAccount_GoodM1, sc) 
val clusterSet_GoodM1 = ComputeClusterSet(clusteredInfo_GoodM1)
// (2) 双月数据 GoodM2
val clusteredInfo_GoodM2 = ClusteringUserInfo_FromAccount(parsedDataIndexed_GoodM2, resultAccount_GoodM2, sc) 
val clusterSet_GoodM2 = ComputeClusterSet(clusteredInfo_GoodM2)
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL    BadF3
val clusteredInfo_BadF3 = ClusteringUserInfo_FromAccount(parsedDataIndexed_BadF3, resultAccount_BadF3, sc) 
val clusterSet_BadF3 = ComputeClusterSet(clusteredInfo_BadF3)
// (2) 仅前两个月都是 0或NULL   BadF2ExcludeF3
val clusteredInfo_BadF2ExcludeF3 = ClusteringUserInfo_FromAccount(parsedDataIndexed_BadF2ExcludeF3, resultAccount_BadF2ExcludeF3, sc) 
val clusterSet_BadF2ExcludeF3 = ComputeClusterSet(clusteredInfo_BadF2ExcludeF3)

// ----------------------------------------------------------------------------
// 二。 从“手工指定最佳K”中继承
val resultAccount_Special_GoodM1 = resultWClusterInfoSpecial2HDFS_GoodM1._1.account
val resultAccount_Special_GoodM2 = resultWClusterInfoSpecial2HDFS_GoodM2._1.account
val resultAccount_Special_BadF3 = resultWClusterInfoSpecial2HDFS_BadF3._1.account
val resultAccount_Special_BadF2ExcludeF3 = resultWClusterInfoSpecial2HDFS_BadF2ExcludeF3._1.account

// 1. 有效数据
// (1) 单月数据 GoodM1
val clusteredInfo_Special_GoodM1 = ClusteringUserInfo_FromAccount(parsedDataIndexed_GoodM1, resultAccount_Special_GoodM1, sc) 
val clusterSet_Special_GoodM1 = ComputeClusterSet(clusteredInfo_Special_GoodM1)
// (2) 双月数据 GoodM2
val clusteredInfo_Special_GoodM2 = ClusteringUserInfo_FromAccount(parsedDataIndexed_GoodM2, resultAccount_Special_GoodM2, sc) 
val clusterSet_Special_GoodM2 = ComputeClusterSet(clusteredInfo_Special_GoodM2)
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL    BadF3
val clusteredInfo_Special_BadF3 = ClusteringUserInfo_FromAccount(parsedDataIndexed_BadF3, resultAccount_Special_BadF3, sc) 
val clusterSet_Special_BadF3 = ComputeClusterSet(clusteredInfo_Special_BadF3)
// (2) 仅前两个月都是 0或NULL   BadF2ExcludeF3
val clusteredInfo_Special_BadF2ExcludeF3 = ClusteringUserInfo_FromAccount(parsedDataIndexed_BadF2ExcludeF3, resultAccount_Special_BadF2ExcludeF3, sc) 
val clusterSet_Special_BadF2ExcludeF3 = ComputeClusterSet(clusteredInfo_Special_BadF2ExcludeF3)

// ****************************************************************************
// 交互式查询样本
// ****************************************************************************
// ----------------------------------------------------------------------------
// (1) 单月数据 GoodM1
val x = clusterSet_GoodM1
x.k
x.clusterCenters
x.clusterArray
getSampleFromClusterSet(x,0,10) // 从簇0中寻找10个样本
getSampleFromClusterSet(x,3,50) // 从簇0中寻找50个样本
getSampleFromClusterSet(x,x.k+1,2) // 从簇0中寻找2个样本  应该报错!

