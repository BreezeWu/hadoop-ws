执行方法
==============
1. 启动hdfs服务:    start-dfs.sh
2. 运行spark: 以WordCount为例
(请参考 "spark-ws/01.demo-of-spark-simpleApp.sh")

以WordCount为例
---------------
# 进入jar包所在目录
cd ~/workspace_github/hadoop-ws/spark-ws/ideaProjects/DataMining-of-StationGrid-2014/out/artifacts/DataMining_of_StationGrid_2014_jar

# 完整命令
spark-submit --master local --class org.wuhz.spark.demo.WordCount --executor-memory 500m DataMining-of-StationGrid-2014.jar hdfs://master-hadoop:9000/user/hadoop/input/words-data

# 执行(参数形式)
export input_data=hdfs://master-hadoop:9000/user/hadoop/input/words-data/*
export task_jar=DataMining-of-StationGrid-2014.jar
#export task_main_class=org.wuhz.spark.sg.volumetrends.Analyzing
export task_main_class=org.wuhz.spark.demo.WordCount

# local模式
spark-submit --master local --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data}

# spark://模式
spark-submit --master spark://master-hadoop:7077 --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data}

# yarn-client模式
spark-submit --master yarn-client --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data}

# yarn-cluster模式
spark-submit --master yarn-cluster --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data}

volumetrends
---------------
# 执行(参数形式)
export input_data=
export task_jar=DataMining-of-StationGrid-2014.jar
export task_main_class=org.wuhz.spark.sg.volumetrends.Executer

# 第一个参数是数据集标志: s01/s98
# 第二个参数是原值还是百分比标志: 1-原值 100/1000/1000: 转换为相对值,值区间是100/1000/10000
spark-submit --master local --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data} s01 10000

volumetrends: 在spark-shell中运行(搞不成啮!!!!)
---------------
# 将源代码链接到某一个路径(执行目录下,如 ~/workspace_datamining/volumetrends-v2-percent)
cd ~/workspace_datamining/volumetrends-v2-percent
ln -s ~/workspace_github/hadoop-ws/spark-ws/ideaProjects/DataMining-of-StationGrid-2014/src/main/scala src-scala

# 启动 spark-shell
export MASTER=local[6]
spark-shell

# 在spark-shell中
// 导入
:load src-scala/org/wuhz/spark/sg/volumetrends/transformations.scala
:load src-scala/org/wuhz/spark/sg/volumetrends/AccountedKMeans.scala
:load src-scala/org/wuhz/spark/sg/volumetrends/Clustering.scala
:load src-scala/org/wuhz/spark/sg/volumetrends/DataSet.scala
:load src-scala/org/wuhz/spark/sg/volumetrends/CreateRDD.scala
:load src-scala/org/wuhz/spark/sg/volumetrends/ComputeVolumeSum.scala
:load src-scala/org/wuhz/spark/sg/volumetrends/Counting.scala
:load src-scala/org/wuhz/spark/sg/volumetrends/Analyzer.scala
:load src-scala/org/wuhz/spark/sg/volumetrends/Executer.scala

// 运行Executer.main函数
val args = Array("s01")
Executer.main(args)
// 或者, 逐行运行Executer.main函数中的代码
val args = Array("s01")  // Array("s98")
.....   // Executer.main函数中的代码

val ParsedRDDMatrix = transform2ParsedRDDMatrix(HiveRDDMatrix)

// 到达下面这句时出错
org.apache.spark.SparkException: Task not serializable
	at org.apache.spark.util.ClosureCleaner$.ensureSerializable(ClosureCleaner.scala:166)
	at org.apache.spark.util.ClosureCleaner$.clean(ClosureCleaner.scala:158)
	at org.apache.spark.SparkContext.clean(SparkContext.scala:1242)
	at org.apache.spark.rdd.RDD.map(RDD.scala:270)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$objCreateRDD$.$line37$$read$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$objCreateRDD$$transformHiveRDD$1(<console>:187)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$objCreateRDD$$anonfun$10.apply(<console>:199)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$objCreateRDD$$anonfun$10.apply(<console>:199)
	at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:244)
	at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:244)
	at scala.collection.immutable.List.foreach(List.scala:318)
	at scala.collection.TraversableLike$class.map(TraversableLike.scala:244)
	at scala.collection.AbstractTraversable.map(Traversable.scala:105)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$objCreateRDD$.$line37$$read$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$objCreateRDD$$transformHiveRDDList$1(<console>:199)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$objCreateRDD$$anonfun$11.apply(<console>:203)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$objCreateRDD$$anonfun$11.apply(<console>:203)
	at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:244)
	at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:244)
	at scala.collection.immutable.List.foreach(List.scala:318)
	at scala.collection.TraversableLike$class.map(TraversableLike.scala:244)
	at scala.collection.AbstractTraversable.map(Traversable.scala:105)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$objCreateRDD$.transform2ParsedRDDMatrix(<console>:203)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$Executer$.main(<console>:118)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:90)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:95)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:97)
	at $iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:99)
	at $iwC$$iwC$$iwC$$iwC.<init>(<console>:101)
	at $iwC$$iwC$$iwC.<init>(<console>:103)
	at $iwC$$iwC.<init>(<console>:105)
	at $iwC.<init>(<console>:107)
	at <init>(<console>:109)
	at .<init>(<console>:113)
	at .<clinit>(<console>)
	at .<init>(<console>:7)
	at .<clinit>(<console>)
	at $print(<console>)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.apache.spark.repl.SparkIMain$ReadEvalPrint.call(SparkIMain.scala:789)
	at org.apache.spark.repl.SparkIMain$Request.loadAndRun(SparkIMain.scala:1062)
	at org.apache.spark.repl.SparkIMain.loadAndRunReq$1(SparkIMain.scala:615)
	at org.apache.spark.repl.SparkIMain.interpret(SparkIMain.scala:646)
	at org.apache.spark.repl.SparkIMain.interpret(SparkIMain.scala:610)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:814)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.command(SparkILoop.scala:771)
	at org.apache.spark.repl.SparkILoop.processLine$1(SparkILoop.scala:616)
	at org.apache.spark.repl.SparkILoop.innerLoop$1(SparkILoop.scala:624)
	at org.apache.spark.repl.SparkILoop.loop(SparkILoop.scala:629)
	at org.apache.spark.repl.SparkILoop$$anonfun$process$1.apply$mcZ$sp(SparkILoop.scala:954)
	at org.apache.spark.repl.SparkILoop$$anonfun$process$1.apply(SparkILoop.scala:902)
	at org.apache.spark.repl.SparkILoop$$anonfun$process$1.apply(SparkILoop.scala:902)
	at scala.tools.nsc.util.ScalaClassLoader$.savingContextLoader(ScalaClassLoader.scala:135)
	at org.apache.spark.repl.SparkILoop.process(SparkILoop.scala:902)
	at org.apache.spark.repl.SparkILoop.process(SparkILoop.scala:997)
	at org.apache.spark.repl.Main$.main(Main.scala:31)
	at org.apache.spark.repl.Main.main(Main.scala)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.apache.spark.deploy.SparkSubmit$.launch(SparkSubmit.scala:317)
	at org.apache.spark.deploy.SparkSubmit$.main(SparkSubmit.scala:73)
	at org.apache.spark.deploy.SparkSubmit.main(SparkSubmit.scala)
Caused by: java.io.NotSerializableException: $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$objCreateRDD$
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1183)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:347)
	at org.apache.spark.serializer.JavaSerializationStream.writeObject(JavaSerializer.scala:42)
	at org.apache.spark.serializer.JavaSerializerInstance.serialize(JavaSerializer.scala:73)
	at org.apache.spark.util.ClosureCleaner$.ensureSerializable(ClosureCleaner.scala:164)
	... 65 more
