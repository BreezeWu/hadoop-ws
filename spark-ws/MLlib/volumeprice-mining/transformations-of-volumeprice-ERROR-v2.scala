// ----------------------------------------------------------------------------
// 引入依赖
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/hiveselect-of-volumeprice.scala
/**
 * hive 表映射
 * 
 * 阶梯电量电价表, Item_of_ladder
 * 分时电量电价表, Item_of_ts
 *
 * 引入依赖
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/hiveselect-of-volumeprice.scala
 *
 * 引入
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/transformations-of-volumeprice-ERROR-v2.scala
 *
 * 注意:  必须使用 spark-1.0.0-SNAPSHOT-20140718.tar.gz 这个版本, 否则出现下面错误
 *
org.apache.spark.SparkException: Task not serializable
	at org.apache.spark.util.ClosureCleaner$.ensureSerializable(ClosureCleaner.scala:166)
	at org.apache.spark.util.ClosureCleaner$.clean(ClosureCleaner.scala:158)
	at org.apache.spark.SparkContext.clean(SparkContext.scala:1242)
	at org.apache.spark.rdd.RDD.map(RDD.scala:270)
	at $iwC$$iwC$$iwC$$iwC.<init>(<console>:71)
	at $iwC$$iwC$$iwC.<init>(<console>:76)
	at $iwC$$iwC.<init>(<console>:78)
	at $iwC.<init>(<console>:80)
	at <init>(<console>:82)
	at .<init>(<console>:86)
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
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.reallyInterpret$1(SparkILoop.scala:832)
	at org.apache.spark.repl.SparkILoop.interpretStartingWith(SparkILoop.scala:859)
	at org.apache.spark.repl.SparkILoop.command(SparkILoop.scala:771)
	at org.apache.spark.repl.SparkILoop.processLine$1(SparkILoop.scala:616)
	at org.apache.spark.repl.SparkILoop.innerLoop$1(SparkILoop.scala:624)
	at org.apache.spark.repl.SparkILoop.loop(SparkILoop.scala:629)
	at org.apache.spark.repl.SparkILoop$$anonfun$interpretAllFrom$1$$anonfun$apply$mcV$sp$1$$anonfun$apply$mcV$sp$2.apply(SparkILoop.scala:639)
	at org.apache.spark.repl.SparkILoop$$anonfun$interpretAllFrom$1$$anonfun$apply$mcV$sp$1$$anonfun$apply$mcV$sp$2.apply(SparkILoop.scala:636)
	at scala.reflect.io.Streamable$Chars$class.applyReader(Streamable.scala:104)
	at scala.reflect.io.File.applyReader(File.scala:82)
	at org.apache.spark.repl.SparkILoop$$anonfun$interpretAllFrom$1$$anonfun$apply$mcV$sp$1.apply$mcV$sp(SparkILoop.scala:636)
	at org.apache.spark.repl.SparkILoop$$anonfun$interpretAllFrom$1$$anonfun$apply$mcV$sp$1.apply(SparkILoop.scala:636)
	at org.apache.spark.repl.SparkILoop$$anonfun$interpretAllFrom$1$$anonfun$apply$mcV$sp$1.apply(SparkILoop.scala:636)
	at org.apache.spark.repl.SparkILoop.savingReplayStack(SparkILoop.scala:150)
	at org.apache.spark.repl.SparkILoop$$anonfun$interpretAllFrom$1.apply$mcV$sp(SparkILoop.scala:635)
	at org.apache.spark.repl.SparkILoop$$anonfun$interpretAllFrom$1.apply(SparkILoop.scala:635)
	at org.apache.spark.repl.SparkILoop$$anonfun$interpretAllFrom$1.apply(SparkILoop.scala:635)
	at org.apache.spark.repl.SparkILoop.savingReader(SparkILoop.scala:155)
	at org.apache.spark.repl.SparkILoop.interpretAllFrom(SparkILoop.scala:634)
	at org.apache.spark.repl.SparkILoop$$anonfun$loadCommand$1.apply(SparkILoop.scala:699)
	at org.apache.spark.repl.SparkILoop$$anonfun$loadCommand$1.apply(SparkILoop.scala:698)
	at org.apache.spark.repl.SparkILoop.withFile(SparkILoop.scala:692)
	at org.apache.spark.repl.SparkILoop.loadCommand(SparkILoop.scala:698)
	at org.apache.spark.repl.SparkILoop$$anonfun$standardCommands$7.apply(SparkILoop.scala:308)
	at org.apache.spark.repl.SparkILoop$$anonfun$standardCommands$7.apply(SparkILoop.scala:308)
	at scala.tools.nsc.interpreter.LoopCommands$LineCmd.apply(LoopCommands.scala:81)
	at org.apache.spark.repl.SparkILoop.command(SparkILoop.scala:766)
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
	at org.apache.spark.deploy.SparkSubmit$.launch(SparkSubmit.scala:314)
	at org.apache.spark.deploy.SparkSubmit$.main(SparkSubmit.scala:73)
	at org.apache.spark.deploy.SparkSubmit.main(SparkSubmit.scala)
Caused by: java.io.NotSerializableException: org.apache.spark.sql.hive.HiveContext$$anon$3
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1183)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1547)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1508)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1431)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1177)
	at java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:347)
	at org.apache.spark.serializer.JavaSerializationStream.writeObject(JavaSerializer.scala:42)
	at org.apache.spark.serializer.JavaSerializerInstance.serialize(JavaSerializer.scala:73)
	at org.apache.spark.util.ClosureCleaner$.ensureSerializable(ClosureCleaner.scala:164)
	... 101 more

 */
 
// index + data
case class IndexData(index:Array[String], data:Array[Double]) extends serializable

// 阶梯电量电价表: 用户号, 年月, 一阶电量, 一阶电价, 二阶电量, 二阶电价, 三阶电量, 三阶电价,
case class Item_of_ladder(cons_no:String, ym:String, volume1:Int, price1:Double, 
    volume2:Int, price2:Double, volume3:Int, price3:Double)  extends serializable
    
// 分时电量电价表: 用户号, 年月, 尖峰电量, 尖峰电价, 峰电量, 峰电价, 平电量, 平电价, 谷电量, 谷电价, 脊骨电量, 脊骨电价
// 当前数据没有 尖峰和脊骨 信息
case class Item_of_ts(cons_no:String, ym:String, 
    volume_top:Int, price_top:Double, 
    volume_high:Int, price_high:Double, volume_median:Int, price_median:Double, volume_low:Int, price_low:Double, 
    volume_bottom:Int, price_bottom:Double)  extends serializable

// ----------------------------------------------------------------------------
/**
 * 从 hive hiveContext row 转换到 case class
 */
/*
def Any2String(x:Any):String = x match {
    case null => ""			// 将null转换为0
    case s:String => s
    case other => null
}
def Any2Int(x:Any):Int = x match {
    case null => 0			// 将null转换为0
    case i:Int => i
    case other => 0
}
def Any2Double(x:Any):Double = x match {
    case null => 0			// 将null转换为0.0
    case i:Int => i.toDouble
    case d:Double => d
    case other => 0.0
}
*/

val Any2String = (x:Any) => x match {
    case null => ""			// 将null转换为0
    case s:String => s
    case other => null
}
val Any2Int = (x:Any) => x match {
    case null => 0			// 将null转换为0
    case i:Int => i
    case other => 0
}
val Any2Double = (x:Any) => x match {
    case null => 0			// 将null转换为0.0
    case i:Int => i.toDouble
    case d:Double => d
    case other => 0.0
}

val row2Item_of_ladder = (r:org.apache.spark.sql.Row) => {  
    Item_of_ladder(Any2String(r(0)), Any2String(r(1)),
        Any2Int(r(2)), Any2Double(r(3)), Any2Int(r(4)), Any2Double(r(5)), Any2Int(r(6)), Any2Double(r(7))
        )
}
val row2Item_of_ts = (r:org.apache.spark.sql.Row) => {
    Item_of_ts(Any2String(r(0)), Any2String(r(1)),
        Any2Int(r(2)), Any2Double(r(3)),
        Any2Int(r(4)), Any2Double(r(5)), Any2Int(r(6)), Any2Double(r(7)), Any2Int(r(8)), Any2Double(r(9)),
        Any2Int(r(10)), Any2Double(r(11))
        )
}

