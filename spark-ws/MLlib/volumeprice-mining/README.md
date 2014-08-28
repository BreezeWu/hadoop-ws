===
两个步骤:
1. 通过spark创建hive表
2. 利用建立的hive表,进行分析

===
一. 通过spark创建hive表
//引入依赖
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/hiveselect-of-volumeprice.scala
// 选择数据集
val datasetID = "s01"
//引入并执行
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/create-rdd-of-volumeprice-1.createhivetable.scala

// 退出spark-shell,目的是清除所有加载的SQL包~曾经发生过问题, 见"".
:quit
===
二.利用建立的hive表进行分析

//引入依赖
 
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/transformations-of-volumeprice.scala
// 选择数据集
val datasetID = "s01" // s98
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/create-rdd-of-volumeprice-2.getmappedData.scala
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/analyzing-ladder-1.computeYearVolumePriceInfo.scala
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/analyzing-ladder-2.statistic-01.rangeYear-v2.scala

// 引入并执行
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/analyzing-ladder-2.statistic-02.distribution-v2.scala

:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/util.scala
// ----------------------------------------------------------------------------
// 写入文件
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/util.scala
write3RangeCountObj2File(rangeCountAccObj_yearVolume, datasetID + "_yearVolume")
write3RangeCountObj2File(rangeCountAccObj_yearSplit, datasetID + "_yearSplit")
write3RangeCountObj2File(rangeCountAccObj_yearMoney, datasetID + "_yearMoney")

