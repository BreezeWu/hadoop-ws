执行步骤
======

运行spark脚本
----------
1. 运行spark-shell
2. 在spark-shell中运行
:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/transformations-01-hive2rowitem.scala
:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/transformations-02-yearvolumes2average.scala

// 这个是从为了备份数据的
//:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/create-rdd-of-hive.scala
// 这个是从备份数据恢复的
:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/load-data-from-parquet.scala

// 聚类函数
:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/tryKMeansSmart-v3.scala
// 根据聚类模型计算ClusterCount
:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/ComputeClusterCount.scala

