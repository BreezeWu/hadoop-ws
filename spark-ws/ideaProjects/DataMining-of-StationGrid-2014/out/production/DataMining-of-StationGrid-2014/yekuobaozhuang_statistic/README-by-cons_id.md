以用户(CONS_ID)为单位进行统计分析 (:load方式)
==============================
cd ~/workspace_github/hadoop-ws/spark-ws/ideaProjects/DataMining-of-StationGrid-2014/src/main/spark-shell/yekuobaozhuang_statistic
export MASTER=local
# export MASTER=spark://master-hadoop:7077
spark-shell # SPARK_DRIVER_MEMORY=3G spark-shell
:load loadDataFromOneFile.scala

:load CreateRDD-by-cons_id-common.scala
// 用于统计分析
:load CreateRDD-by-cons_id-01-for-statistic.scala
// 用于导出CSV
//:load CreateRDD-by-cons_id-02-export2CSV.scala

:load statistic_common.scala
:load statistic_01_le_runnedMonths.scala
:load statistic_02_between_runnedMonths.scala
:load statistic_03_be_runnedMonths.scala
:load transferResult2Stdout.scala
:load transferResult2CSV.scala
:load execute_statistic.scala
