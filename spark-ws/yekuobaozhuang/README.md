执行步骤
======

运行spark脚本
----------
1. 运行spark-shell
### yarn-client
    //  [集群]   SPARK_EXECUTOR_INSTANCES=7 SPARK_EXECUTOR_MEMORY=1G SPARK_DRIVER_MEMORY=1G spark-shell
    //  [单机]	SPARK_EXECUTOR_INSTANCES=4 SPARK_EXECUTOR_MEMORY=1G SPARK_DRIVER_MEMORY=1G spark-shell
### local[*]
export MASTER=local[*]
#SPARK_EXECUTOR_MEMORY=4G SPARK_DRIVER_MEMORY=4G spark-shell
SPARK_DRIVER_MEMORY=3G spark-shell

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

// 抽取特征
:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/transformations-features2vector.scala

// 1.执行聚类, 尝试求最佳 k
//:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/execute-try-kmeans.scala

// 2.执行聚类(k=50),决策树分析
// =============================
////方式一: 一个数据集
//// step 1
//:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/execute-decisiontree-v1-01-prepare.scala

//// step 2
//// 假定所有特征都是连续的
////:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/execute-decisiontree-v1-01-all-are-continuous.scala
//// 并非所有特征都是连续的,设置分类属性性质
//:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/execute-decisiontree-v1-02-setting-categorical.scala
// =============================
//方式二: 多个数据集,对数据处理封装为函数. 只对"分类属性进行处理"
// 封装函数
:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/execute-decisiontree-v2-functions.scala
// 运行封装函数
:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/yekuobaozhuang/3-analyzing-spark-shell/execute-decisiontree-v2-executor.scala


// 错误信息:
原因在于分类特征为将值与categoricalFeaturesInfo匹配
14/09/22 21:22:48 INFO BlockManagerInfo: Added broadcast_135_piece0 in memory on master-hadoop:53374 (size: 7.4 KB, free: 516.3 MB)
14/09/22 21:22:48 INFO BlockManagerInfo: Added rdd_9_0 in memory on master-hadoop:53374 (size: 574.6 KB, free: 515.7 MB)
14/09/22 21:22:48 INFO BlockManagerInfo: Added broadcast_139_piece1 in memory on master-hadoop:53374 (size: 4.0 MB, free: 511.7 MB)
org.apache.spark.SparkException: Job aborted due to stage failure: Task 2 in stage 110.0 failed 4 times, most recent failure: Lost task 2.3 in stage 110.0 (TID 526, master-hadoop): java.lang.IllegalArgumentException: DecisionTree given invalid data: Feature 1 is categorical with values in {0,...,22, but a data point gives it value -1.417096326E9.
  Bad data point: (12.0,[-4.7784216E8,-1.417096326E9,1.318696835E9,-1.082620502E9,1540.0,4.8611737E7,50.0,1603554.0,51510.0,4.8611737E7,4.8611737E7,1708.0])

categoricalFeaturesInfo_array: Array[(Int, Int)] = Array((0,3), (1,23), (2,2105), (3,1863), (4,4), (5,350), (6,3), (7,313), (8,12), (9,347), (10,338))
categoricalFeaturesInfo: scala.collection.immutable.Map[Int,Int] = Map(0 -> 3, 5 -> 350, 10 -> 338, 1 -> 23, 6 -> 3, 9 -> 347, 2 -> 2105, 7 -> 313, 3 -> 1863, 8 -> 12, 4 -> 4)
impurity: String = gini

*/

// 错误信息: local
增加内存可解决该问题,如下:
export MASTER=local[*]
SPARK_EXECUTOR_MEMORY=2G SPARK_DRIVER_MEMORY=1G spark-shell

原始错误信息:
14/09/22 20:25:19 INFO DAGScheduler: Submitting 5 missing tasks from Stage 110 (MapPartitionsRDD[162] at mapPartitionsWithIndex at RDDFunctions.scala:107)
14/09/22 20:25:19 INFO TaskSchedulerImpl: Adding task set 110.0 with 5 tasks
14/09/22 20:25:19 INFO TaskSetManager: Starting task 0.0 in stage 110.0 (TID 513, localhost, ANY, 8256 bytes)
14/09/22 20:25:19 INFO TaskSetManager: Starting task 1.0 in stage 110.0 (TID 514, localhost, ANY, 7507 bytes)
14/09/22 20:25:19 INFO TaskSetManager: Starting task 2.0 in stage 110.0 (TID 515, localhost, ANY, 8256 bytes)
14/09/22 20:25:19 INFO TaskSetManager: Starting task 3.0 in stage 110.0 (TID 516, localhost, ANY, 8256 bytes)
14/09/22 20:25:19 INFO TaskSetManager: Starting task 4.0 in stage 110.0 (TID 517, localhost, ANY, 8256 bytes)
14/09/22 20:25:19 INFO Executor: Running task 0.0 in stage 110.0 (TID 513)
14/09/22 20:25:19 INFO Executor: Running task 1.0 in stage 110.0 (TID 514)
14/09/22 20:25:19 INFO Executor: Running task 3.0 in stage 110.0 (TID 516)
14/09/22 20:25:19 INFO Executor: Running task 2.0 in stage 110.0 (TID 515)
14/09/22 20:25:19 INFO Executor: Running task 4.0 in stage 110.0 (TID 517)
14/09/22 20:26:22 ERROR Executor: Exception in task 0.0 in stage 110.0 (TID 513)
java.lang.OutOfMemoryError: GC overhead limit exceeded
	at scala.collection.immutable.$colon$colon.readObject(List.scala:371)
	at sun.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1017)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1893)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
14/09/22 20:26:22 ERROR Executor: Exception in task 4.0 in stage 110.0 (TID 517)
java.lang.OutOfMemoryError: GC overhead limit exceeded
	at scala.collection.immutable.$colon$colon.readObject(List.scala:371)
	at sun.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1017)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1893)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
14/09/22 20:26:22 ERROR Executor: Exception in task 2.0 in stage 110.0 (TID 515)
java.lang.OutOfMemoryError: GC overhead limit exceeded
	at scala.collection.immutable.$colon$colon.readObject(List.scala:371)
	at sun.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1017)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1893)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
14/09/22 20:26:22 ERROR ExecutorUncaughtExceptionHandler: Uncaught exception in thread Thread[Executor task launch worker-1,5,main]
java.lang.OutOfMemoryError: GC overhead limit exceeded
	at scala.collection.immutable.$colon$colon.readObject(List.scala:371)
	at sun.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1017)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1893)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
14/09/22 20:26:22 ERROR Executor: Exception in task 1.0 in stage 110.0 (TID 514)
java.lang.OutOfMemoryError: GC overhead limit exceeded
	at scala.collection.immutable.$colon$colon.readObject(List.scala:371)
	at sun.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1017)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1893)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
14/09/22 20:26:22 ERROR ExecutorUncaughtExceptionHandler: Uncaught exception in thread Thread[Executor task launch worker-3,5,main]
java.lang.OutOfMemoryError: GC overhead limit exceeded
	at scala.collection.immutable.$colon$colon.readObject(List.scala:371)
	at sun.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1017)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1893)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
14/09/22 20:26:22 ERROR ExecutorUncaughtExceptionHandler: Uncaught exception in thread Thread[Executor task launch worker-0,5,main]
java.lang.OutOfMemoryError: GC overhead limit exceeded
	at scala.collection.immutable.$colon$colon.readObject(List.scala:371)
	at sun.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1017)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1893)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
14/09/22 20:26:22 ERROR ExecutorUncaughtExceptionHandler: Uncaught exception in thread Thread[Executor task launch worker-2,5,main]
java.lang.OutOfMemoryError: GC overhead limit exceeded
	at scala.collection.immutable.$colon$colon.readObject(List.scala:371)
	at sun.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1017)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1893)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
14/09/22 20:26:22 WARN TaskSetManager: Lost task 4.0 in stage 110.0 (TID 517, localhost): java.lang.OutOfMemoryError: GC overhead limit exceeded
        scala.collection.immutable.$colon$colon.readObject(List.scala:371)
        sun.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
        sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        java.lang.reflect.Method.invoke(Method.java:606)
        java.io.ObjectStreamClass.invokeReadObject(ObjectStreamClass.java:1017)
        java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1893)
        java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
        java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
        java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
        java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
        java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
        java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
        java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
        java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
        java.io.ObjectInputStream.readArray(ObjectInputStream.java:1706)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
        java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
        java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
        java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
        java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
        java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
        java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
        java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
        java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
        java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
14/09/22 20:26:22 ERROR TaskSetManager: Task 4 in stage 110.0 failed 1 times; aborting job
14/09/22 20:26:22 INFO TaskSetManager: Lost task 2.0 in stage 110.0 (TID 515) on executor localhost: java.lang.OutOfMemoryError (GC overhead limit exceeded) [duplicate 1]
14/09/22 20:26:22 INFO TaskSetManager: Lost task 1.0 in stage 110.0 (TID 514) on executor localhost: java.lang.OutOfMemoryError (GC overhead limit exceeded) [duplicate 2]
14/09/22 20:26:22 INFO TaskSetManager: Lost task 0.0 in stage 110.0 (TID 513) on executor localhost: java.lang.OutOfMemoryError (GC overhead limit exceeded) [duplicate 3]
14/09/22 20:26:22 INFO TaskSchedulerImpl: Cancelling stage 110
14/09/22 20:26:22 INFO Executor: Executor is trying to kill task 3.0 in stage 110.0 (TID 516)
14/09/22 20:26:22 INFO TaskSchedulerImpl: Stage 110 was cancelled
14/09/22 20:26:22 INFO DAGScheduler: Failed to run reduce at RDDFunctions.scala:111
Exception in thread "delete Spark temp dir /tmp/spark-ae370e93-a86a-4965-a1e0-a80915b4a7f0" java.io.IOException: Failed to delete: /tmp/spark-ae370e93-a86a-4965-a1e0-a80915b4a7f0
	at org.apache.spark.util.Utils$.deleteRecursively(Utils.scala:692)
	at org.apache.spark.util.Utils$$anon$4.run(Utils.scala:281)
hadoop@master-hadoop:~/dm-work$
hadoop@master-hadoop:~/dm-work$


