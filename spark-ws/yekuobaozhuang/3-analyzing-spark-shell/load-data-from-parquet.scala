// ----------------------------------------------------------------------------
/**
 * 从 parquetFile 创建RDD
 *
 */
// ----------------------------------------------------------------------------
import org.apache.spark.sql.SQLContext

val sqlContext = new SQLContext(sc)
val parquet = sqlContext.parquetFile("file:///home/hadoop/dm-data/rddFromHive-parquet/")
val rddFromHive = parquet
// ----------------------------------------------------------------------------
// 变换
// 从 SchemaRDD 变换为 MappedRDD:org.apache.spark.rdd.RDD[MPVolumeItem]
val mappedRddData = rddFromHive.map(r => row2MPVolumeItem(r))
// ----------------------------------------------------------------------------
// 求每年的月用电量平均值, 并将其转换为平均值的百分比
// 注意: 不是 convertMPVolumeItem2Percent() !
val mappedRddData_percent = mappedRddData.map(r => convertMPVolumeItem2AveragePercent(r)).cache()

val first = mappedRddData_percent.first
// ----------------------------------------------------------------------------
// 将 run_date 复原
// 最后使用这个语句完成 : case Array(x, _*) => new java.lang.String(y.asInstanceOf[Array[Byte]], "utf-8")
/*val first2 = mappedRddData.first
val run_date = first2.index.left.run_date
val mp_name = first1(2)
val run_date = first1(6)
testType(mp_name)
testType(run_date)
any2TypeString(mp_name)
any2TypeString(run_date)
val s = new String(any2ArrayByte21(run_date), "utf-8")

val a = Array(50, 48, 49, 48, 45, 48, 50, 45, 50, 50, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48)
val arrayByte:Array[Byte] = a.map(x => x.toByte)
val s = new String(arrayByte, "utf-8")

  // 将 run_date 从 any 变为 Array[Byte]
  def any2ArrayByte21(y:Any) = y match {
    case Array(i0,i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15,i16,i17,i18,i19,i20) => {
      val x = Array(i0,i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15,i16,i17,i18,i19,i20)
      val any2byte = (a:Any) => a match { case b:Byte => b}
      x.map(any2byte)
    }
    //case other => other
  }
  val runDate = new String(any2ArrayByte21(index.left.run_date), "utf-8")
*/


// ----------------------------------------------------------------------------
// 从 objectFile 中读取
/* 从 objectFile 中读取时报错!  spark版本不匹配?
val obj = sc.objectFile("file:///home/hadoop/dm-data/mappedRddData-MPVolumeItem-obj/")

14/09/19 09:27:38 WARN TaskSetManager: Lost task 0.0 in stage 3.0 (TID 3, master-hadoop): java.io.InvalidClassException: $line38.$read$$iwC$$iwC$$iwC$$iwC; local class incompatible: stream classdesc serialVersionUID = 7076212071028325629, local class serialVersionUID = 7745297845718137848
        java.io.ObjectStreamClass.initNonProxy(ObjectStreamClass.java:617)
        java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1622)
        java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1517)
        java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1771)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
        java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:1990)
        java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
        java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1798)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
        java.io.ObjectInputStream.readArray(ObjectInputStream.java:1681)
        java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1344)
        java.io.ObjectInputStream.readObject(ObjectInputStream.java:370)
        org.apache.spark.util.Utils$.deserialize(Utils.scala:83)
        org.apache.spark.SparkContext$$anonfun$objectFile$1.apply(SparkContext.scala:735)
        org.apache.spark.SparkContext$$anonfun$objectFile$1.apply(SparkContext.scala:735)
        scala.collection.Iterator$$anon$13.hasNext(Iterator.scala:371)
        scala.collection.Iterator$$anon$10.hasNext(Iterator.scala:308)
        scala.collection.Iterator$class.foreach(Iterator.scala:727)
        scala.collection.AbstractIterator.foreach(Iterator.scala:1157)
        scala.collection.generic.Growable$class.$plus$plus$eq(Growable.scala:48)
        scala.collection.mutable.ArrayBuffer.$plus$plus$eq(ArrayBuffer.scala:103)
        scala.collection.mutable.ArrayBuffer.$plus$plus$eq(ArrayBuffer.scala:47)
        scala.collection.TraversableOnce$class.to(TraversableOnce.scala:273)
        scala.collection.AbstractIterator.to(Iterator.scala:1157)
        scala.collection.TraversableOnce$class.toBuffer(TraversableOnce.scala:265)
        scala.collection.AbstractIterator.toBuffer(Iterator.scala:1157)
        scala.collection.TraversableOnce$class.toArray(TraversableOnce.scala:252)
        scala.collection.AbstractIterator.toArray(Iterator.scala:1157)
        org.apache.spark.rdd.RDD$$anonfun$28.apply(RDD.scala:1080)
        org.apache.spark.rdd.RDD$$anonfun$28.apply(RDD.scala:1080)
        org.apache.spark.SparkContext$$anonfun$runJob$4.apply(SparkContext.scala:1121)
        org.apache.spark.SparkContext$$anonfun$runJob$4.apply(SparkContext.scala:1121)
        org.apache.spark.scheduler.ResultTask.runTask(ResultTask.scala:62)
        org.apache.spark.scheduler.Task.run(Task.scala:54)
        org.apache.spark.executor.Executor$TaskRunner.run(Executor.scala:177)
        java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
        java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
        java.lang.Thread.run(Thread.java:745)
14/09/19 09:27:38 INFO TaskSetManager: Starting task 0.1 in stage 3.0 (TID 4, master-hadoop, PROCESS_LOCAL, 1223 bytes)
14/09/19 09:27:38 INFO TaskSetManager: Lost task 0.1 in stage 3.0 (TID 4) on executor master-hadoop: java.io.InvalidClassException ($line38.$read$$iwC$$iwC$$iwC$$iwC; local class incompatible: stream classdesc serialVersionUID = 7076212071028325629, local class serialVersionUID = 7745297845718137848) [duplicate 1]
*/
