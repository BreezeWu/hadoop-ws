// --------------------------------------------------------------------------
// 对用户进行分群: 独立模式
//		并不依赖于 execute-tryKMeansSmart-ClusterCount.scala 的执行
//
//	在spark-shell中执行  (注意"1.加载数据"的语句即可)
//      val taskNamePre = "s98" // s01
//		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/execute-ClusteringUserInfo-Standalone.scala

// ****************************************************************************
// 1. 加载数据与函数
// ****************************************************************************
// (1)样本数据
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s01.scala
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedDataIndexed-userinfo-s01.scala
// (2)全量数据
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s98.scala
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedDataIndexed-userinfo-s98.scala

// 2. 加载函数
// 聚类函数
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/tryKMeansSmart.scala
// 根据聚类模型计算ClusterCount --已经在 execute-s98-v1.scala/execute-s01-v1.scala 中加载
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/ComputeClusterCount.scala

// 给索引数据分配clusterID
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/ClusteringUserInfo.scala

// ****************************************************************************
// 执行
// 方式2： ClusteringUserInfo_Standalone 模式
// ****************************************************************************
val perfectK = 20;
val maxIterations = 20 // 当前没有生效

// 1. 有效数据
// (1) 单月数据 GoodM1
val clusteredInfo_Standalone_GoodM1 = ClusteringUserInfo_Standalone(parsedData_GoodM1, parsedDataIndexed_GoodM1, perfectK, maxIterations, sc) 
val clusterSet_Standalone_GoodM1 = ComputeClusterSet(clusteredInfo_Standalone_GoodM1)
// (2) 双月数据 GoodM2
val clusteredInfo_Standalone_GoodM2 = ClusteringUserInfo_Standalone(parsedData_GoodM2, parsedDataIndexed_GoodM2, perfectK, maxIterations, sc) 
val clusterSet_Standalone_GoodM2 = ComputeClusterSet(clusteredInfo_Standalone_GoodM2)
// --------------------------
// 2. 无效数据
// (1) 前三个月都是 0或NULL    BadF3
val clusteredInfo_Standalone_BadF3 = ClusteringUserInfo_Standalone(parsedData_BadF3, parsedDataIndexed_BadF3, perfectK, maxIterations, sc) 
val clusterSet_Standalone_BadF3 = ComputeClusterSet(clusteredInfo_Standalone_BadF3)
// (2) 仅前两个月都是 0或NULL   BadF2ExcludeF3
val clusteredInfo_Standalone_BadF2ExcludeF3 = ClusteringUserInfo_Standalone(parsedData_BadF2ExcludeF3, parsedDataIndexed_BadF2ExcludeF3, perfectK, maxIterations, sc) 
val clusterSet_Standalone_BadF2ExcludeF3 = ComputeClusterSet(clusteredInfo_Standalone_BadF2ExcludeF3)

// ****************************************************************************
// 交互式查询样本
// ****************************************************************************
// ----------------------------------------------------------------------------
// (1) 单月数据 GoodM1
val x = clusterSet_Standalone_GoodM1
x.k
x.clusterCenters
x.clusterArray
getSampleFromClusterSet(x,0,10) // 从簇0中寻找10个样本
getSampleFromClusterSet(x,3,50) // 从簇0中寻找50个样本
getSampleFromClusterSet(x,x.k+1,2) // 从簇0中寻找2个样本  应该报错!

// ----------------------------------------------------------------------------
val head = taskNamePre + "_"

// 将簇样本信息写入文件
val sampleNum = 100
writeClusterSetSample2File(clusterSet_Standalone_GoodM1, sampleNum, head + "clusterSet_Standalone_GoodM1")
writeClusterSetSample2File(clusterSet_Standalone_GoodM2, sampleNum, head + "clusterSet_Standalone_GoodM2")
writeClusterSetSample2File(clusterSet_Standalone_BadF3, sampleNum, head + "clusterSet_Standalone_BadF3")
writeClusterSetSample2File(clusterSet_Standalone_BadF2ExcludeF3, sampleNum, head + "clusterSet_Standalone_BadF2ExcludeF3")

// 单独将簇中心信息写入文件
writeClusterSetCenters2File(clusterSet_Standalone_GoodM1, head + "clusterSet_Standalone_GoodM1")
writeClusterSetCenters2File(clusterSet_Standalone_GoodM2, head + "clusterSet_Standalone_GoodM2")
writeClusterSetCenters2File(clusterSet_Standalone_BadF3, head + "clusterSet_Standalone_BadF3")
writeClusterSetCenters2File(clusterSet_Standalone_BadF2ExcludeF3, head + "clusterSet_Standalone_BadF2ExcludeF3")
