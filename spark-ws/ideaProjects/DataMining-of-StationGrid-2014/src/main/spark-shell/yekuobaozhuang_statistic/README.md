### 执行方法
--- 启动spark-shell
--- 修改 loadDataFromOneFile.scala 文件中的 datasetId 和对应文件路径
--- 加载执行 loadDataFromOneFile.scala
--- 加载执行 statistic_common.scala
--- 加载执行
    statistic_01_le_runnedMonths.scala
    statistic_02_between_runnedMonths.scala
    statistic_03_be_runnedMonths.scala
--- 加载执行
    transferResult2Stdout.scala //在屏幕上输出
    transferResult2CSV.scala //输出到文件
--- 加载执行
    execute_statistic.scala

cd ~/workspace_github/hadoop-ws/spark-ws/ideaProjects/DataMining-of-StationGrid-2014/src/main/spark-shell/yekuobaozhuang_statistic
export MASTER=local[*]
spark-shell
:load loadDataFromOneFile.scala
:load statistic_common.scala
:load statistic_01_le_runnedMonths.scala
:load statistic_02_between_runnedMonths.scala
:load statistic_03_be_runnedMonths.scala
:load transferResult2Stdout.scala
:load transferResult2CSV.scala
:load execute_statistic.scala

### 其他: 数据探索
:load data-explore.scala
