// ----------------------------------------------------------------------------
// 原始文章
Spark LogisticRegression

	http://blog.csdn.net/liangliang8086/article/details/17337279

// ----------------------------------------------------------------------------
// 修改了
//	1. 数据存放在hdfs上面
//		val trainfile = "dm-data/spark-data/a8a";
//		val testfile = "dm-data/spark-data/a8a.t";
//	2. sc使用yarn-client
在spark-shell中交互运行:

:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/demo01-Spark-LogisticRegression/SparserVector.scala
:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/demo01-Spark-LogisticRegression/SparseLR.scala

// 调用
SparseLR.main(Array("随意输入的一个字符串,构建Array[String],模拟命令行"))

// -------------------------
但最后提示下面错误信息:

14/07/12 12:42:58 INFO cluster.YarnClientClusterScheduler: YarnClientClusterScheduler.postStartHook done
14/07/12 12:42:58 INFO storage.MemoryStore: ensureFreeSpace(180826) called with curMem=0, maxMem=296327577
14/07/12 12:42:58 INFO storage.MemoryStore: Block broadcast_0 stored as values in memory (estimated size 176.6 KB, free 282.4 MB)
org.apache.spark.SparkException: Task not serializable
	at org.apache.spark.util.ClosureCleaner$.ensureSerializable(ClosureCleaner.scala:166)
	at org.apache.spark.util.ClosureCleaner$.clean(ClosureCleaner.scala:158)
	at org.apache.spark.SparkContext.clean(SparkContext.scala:1216)
	at org.apache.spark.rdd.RDD.map(RDD.scala:269)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$SparseLR$.main(<console>:207)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:128)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:133)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:135)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:137)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:139)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:141)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:143)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:145)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:147)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:149)
	at $iwC$$iwC$$iwC$$iwC$$iwC.<init>(<console>:151)
	at $iwC$$iwC$$iwC$$iwC.<init>(<console>:153)
	at $iwC$$iwC$$iwC.<init>(<console>:155)
	at $iwC$$iwC.<init>(<console>:157)
	at $iwC.<init>(<console>:159)
	at <init>(<console>:161)
	at .<init>(<console>:165)
	at .<clinit>(<console>)
	at .<init>(<console>:7)
	at .<clinit>(<console>)
	at $print(<console>)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.apache.spark.repl.SparkIMain$ReadEvalPrint.call(SparkIMain.scala:788)
	at org.apache.spark.repl.SparkIMain$Request.loadAndRun(SparkIMain.scala:1056)
	at org.apache.spark.repl.SparkIMain.loadAndRunReq$1(SparkIMain.scala:614)
	at org.apache.spark.repl.SparkIMain.interpret(SparkIMain.scala:645)
	at org.apache.spark.repl.SparkIMain.interpret(SparkIMain.scala:609)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:796)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:841)
	at org.apache.spark.repl.SparkILoop.command(SparkILoop.scala:753)
	at org.apache.spark.repl.SparkILoop.processLine$1(SparkILoop.scala:601)
	at org.apache.spark.repl.SparkILoop.innerLoop$1(SparkILoop.scala:608)
	at org.apache.spark.repl.SparkILoop.loop(SparkILoop.scala:611)
	at org.apache.spark.repl.SparkILoop$$anonfun$process$1.apply$mcZ$sp(SparkILoop.scala:936)
	at org.apache.spark.repl.SparkILoop$$anonfun$process$1.apply(SparkILoop.scala:884)
	at org.apache.spark.repl.SparkILoop$$anonfun$process$1.apply(SparkILoop.scala:884)
	at scala.tools.nsc.util.ScalaClassLoader$.savingContextLoader(ScalaClassLoader.scala:135)
	at org.apache.spark.repl.SparkILoop.process(SparkILoop.scala:884)
	at org.apache.spark.repl.SparkILoop.process(SparkILoop.scala:982)
	at org.apache.spark.repl.Main$.main(Main.scala:31)
	at org.apache.spark.repl.Main.main(Main.scala)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.apache.spark.deploy.SparkSubmit$.launch(SparkSubmit.scala:303)
	at org.apache.spark.deploy.SparkSubmit$.main(SparkSubmit.scala:55)
	at org.apache.spark.deploy.SparkSubmit.main(SparkSubmit.scala)
Caused by: java.io.NotSerializableException: $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$SparseLR$
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1183)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:347)
	at org.apache.spark.serializer.JavaSerializationStream.writeObject(JavaSerializer.scala:42)
	at org.apache.spark.serializer.JavaSerializerInstance.serialize(JavaSerializer.scala:71)
	at org.apache.spark.util.ClosureCleaner$.ensureSerializable(ClosureCleaner.scala:164)
	... 55 more


scala> 

                                 ^

