// ----------------------------------------------------------------------------
/**
 * 执行统计分析: 阶梯电量电
 * 
 * 基于spark中的表 "VolumePriceInfo_of_Ladder"
 * 
 * 阶梯电量电价, 表 "VolumePriceInfo_of_Ladder", 变量 tablenameOfVolumePriceInfo_of_Ladder
 * 分时电量电价, 表 "VolumePriceInfo_of_Ts", 变量 tablenameOfVolumePriceInfo_of_Ts
 *
 * 引入依赖
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/transformations-of-volumeprice.scala
 *      val datasetID = "s01" // s98
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/create-rdd-of-volumeprice-2.getmappedData.scala
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/analyzing-ladder-1.computeYearVolumePriceInfo.scala
 * 引入并执行
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/analyzing-ladder-2.statistic.scala
 *
 */

// ****************************************************************************
// 一. 求最小最大 YearVolume, YearSplit, YearMoney => extremeYearVolumePriceInfo
// ****************************************************************************
// ----------------------------------------------------------------------------
// 数据样本
/*
YearVolumePriceInfoItem(0259649786,YearVolume(1976.0,0.0,0.0),YearSplit(201302,null,null),YearMoney(988.0,988.0,0.0,0.0))
 YearVolumePriceInfoItem(0268425993,YearVolume(2591.0,1900.0,239.0),YearSplit(201301,201303,201307),YearMoney(2836.0,1080.0,660.0,1096.0))
 */

// ----------------------------------------------------------------------------
// 最小最大的年电量
val minYearVolumeCols = "min(yearVolume.v1),min(yearVolume.v2),min(yearVolume.v3)"  // 其实不用计算,应该就是0
val maxYearVolumeCols = "max(yearVolume.v1),max(yearVolume.v2),max(yearVolume.v3)"
// 最小最大的 YearSplit
val minYearSplitCols = "min(yearSplit.mL0),min(yearSplit.mL1),min(yearSplit.mL2)" 
val maxYearSplitCols = "max(yearSplit.mL0),max(yearSplit.mL1),max(yearSplit.mL2)" 
// 最小最大的年费用合计
val minYearMoneyCols = "min(yearMoney.money1),min(yearMoney.money2),min(yearMoney.money3)"  // 其实不用计算,应该就是0
val maxYearMoneyCols = "max(yearMoney.money1),max(yearMoney.money2),max(yearMoney.money3)"


val minCols = s"${minYearVolumeCols},${minYearSplitCols},${minYearMoneyCols}"
val maxCols = s"${maxYearVolumeCols},${maxYearSplitCols},${maxYearMoneyCols}"
val extremeYearVolumePriceInfoRDD = sqlContext.sql(s"SELECT ${minCols},${maxCols} FROM ${tablenameOfVolumePriceInfo_of_Ladder}")
val tmpResultCollect = extremeYearVolumePriceInfoRDD.collect

val tmpFirst = tmpResultCollect(0)
case class ExtremeYearVolumePriceInfo(min:YearVolumePriceInfoItem, max:YearVolumePriceInfoItem)
/*
val extremeYearVolumePriceInfo = ExtremeYearVolumePriceInfo(
        YearVolumePriceInfoItem("minYearVolumePriceInfo",
            YearVolume(tmpFirst(0), tmpFirst(1), tmpFirst(2)),
            YearSplit(tmpFirst(3), tmpFirst(4), tmpFirst(5)),
            buildYearMoney(tmpFirst(6), tmpFirst(7), tmpFirst(8))),
        YearVolumePriceInfoItem("maxYearVolumePriceInfo",
            YearVolume(tmpFirst(9), tmpFirst(10), tmpFirst(11)),
            YearSplit(tmpFirst(12), tmpFirst(13), tmpFirst(14)),
            buildYearMoney(tmpFirst(15), tmpFirst(16), tmpFirst(17)))
    )
val extremeYearVolumePriceInfo = ExtremeYearVolumePriceInfo(
        YearVolumePriceInfoItem("minYearVolumePriceInfo",
            YearVolume(tmpFirst(0).toString.toDouble, tmpFirst(1).toString.toDouble, tmpFirst(2).toString.toDouble),
            YearSplit(tmpFirst(3).toString, tmpFirst(4).toString, tmpFirst(5).toString),
            buildYearMoney(tmpFirst(6).toString.toDouble, tmpFirst(7).toString.toDouble, tmpFirst(8).toString.toDouble)),
        YearVolumePriceInfoItem("maxYearVolumePriceInfo",
            YearVolume(tmpFirst(9).toString.toDouble, tmpFirst(10).toString.toDouble, tmpFirst(11).toString.toDouble),
            YearSplit(tmpFirst(12).toString, tmpFirst(13).toString, tmpFirst(14).toString),
            buildYearMoney(tmpFirst(15).toString.toDouble, tmpFirst(16).toString.toDouble, tmpFirst(17).toString.toDouble))
    )
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
val extremeYearVolumePriceInfo = ExtremeYearVolumePriceInfo(
        YearVolumePriceInfoItem("minYearVolumePriceInfo",
            YearVolume(Any2Double(tmpFirst(0)), Any2Double(tmpFirst(1)), Any2Double(tmpFirst(2))),
            YearSplit(Any2String(tmpFirst(3)), Any2String(tmpFirst(4)), Any2String(tmpFirst(5))),
            buildYearMoney(Any2Double(tmpFirst(6)), Any2Double(tmpFirst(7)), Any2Double(tmpFirst(8)))),
        YearVolumePriceInfoItem("maxYearVolumePriceInfo",
            YearVolume(Any2Double(tmpFirst(9)), Any2Double(tmpFirst(10)), Any2Double(tmpFirst(11))),
            YearSplit(Any2String(tmpFirst(12)), Any2String(tmpFirst(13)), Any2String(tmpFirst(14))),
            buildYearMoney(Any2Double(tmpFirst(15)), Any2Double(tmpFirst(16)), Any2Double(tmpFirst(17))))
    )

// ****************************************************************************
//  二. 基于yearVolumePriceInfoRDD, 构造区间 => rangeYearVolume, rangeYearSplit, rangeYearMoney
//  broadcast variable: 
//          broadcastRef_rangeYearVolume
//          broadcastRef_rangeYearSplit
//          broadcastRef_rangeYearMoney
// ****************************************************************************

import scala.collection.immutable.Range
import scala.collection.immutable.NumericRange._

case class RangeYearVolume(r1:Range, r2:Range, r3:Range)
case class RangeYearSplit(r1:Range, r2:Range, r3:Range)
case class RangeYearMoney(r1:Range, r2:Range, r3:Range)

// 计算step
def computeSuitableStep(minDouble:Double, maxDouble:Double):Int = {
    val maxSplits = 100 // 最大分区数
    val minStep = 100   // 最小步长, 即用电量差异
    
    val min = minDouble
    val max = maxDouble
    
    // 根据最小步长求对应分区数 => 实际的可以达到的最大分区数
    // 区间能被整除
    val remainder = (max - min + 1) % minStep
    
    val splits_minStep = if (remainder == 0) {  //能被整除
            (max - min + 1).toInt / minStep
        } else {    // 不能被整除
            (max - min + 1).toInt / minStep + 1
        }
            
    // 分区数在 splits_minStep 和 maxSplits 取小值
    // 在分区数不超过 maxSplits 时, 步长越小越好, 即不超过maxSplitsd的最大分区数
    val Splits = if(splits_minStep > maxSplits) maxSplits else splits_minStep
    val step = (max - min + 1).toInt / Splits
    
    // 但是,步长也有最小值限制
    val finalStep = if (step < minStep) minStep else step
    
    // 步长不能太琐碎,要是50的倍数
    val base = 50
    val remainderOfBase = finalStep % base
    val goodStep = if (remainderOfBase == 0) {
            finalStep
        } else {
            val quotient =  finalStep / base
            if (remainder >= base/2) (quotient+1)*base else quotient*base
        }
      
    return goodStep
}
// 生成Range
def buildPositiveRangeOfDouble(minDouble:Double, maxDouble:Double, step:Int):Range = {
    val min = if (minDouble % step == 0.0) minDouble.toInt else (minDouble - 1).toInt
    val positiveMin = if (min >= 0) min else 0
    
    val remainder = maxDouble % step
    val max = if (maxDouble % step == 0.0) maxDouble.toInt else (maxDouble + (step - remainder)).toInt
       
    println(s"positiveMin: ${positiveMin}, max: ${max}")
    
    return Range(positiveMin, max+1, step)    
}

def buildRangeYearVolume(minYearVolume:YearVolume, maxYearVolume:YearVolume):RangeYearVolume = {  
    /*
    val minmin = (minYearVolume.v1).min(minYearVolume.v2).min(minYearVolume.v3)
    val maxmax = (maxYearVolume.v1).max(maxYearVolume.v2).max(maxYearVolume.v3)
    val step = computeSuitableStep(minmin, maxmax)
    */
    
    val stepV1 = computeSuitableStep(minYearVolume.v1, maxYearVolume.v1)
    val stepV2 = computeSuitableStep(minYearVolume.v2, maxYearVolume.v2)
    val stepV3 = computeSuitableStep(minYearVolume.v3, maxYearVolume.v3)

    val rangeV1 = buildPositiveRangeOfDouble(minYearVolume.v1, maxYearVolume.v1, stepV1)
    val rangeV2 = buildPositiveRangeOfDouble(minYearVolume.v2, maxYearVolume.v2, stepV2)
    val rangeV3 = buildPositiveRangeOfDouble(minYearVolume.v3, maxYearVolume.v3, stepV3)
    
    return RangeYearVolume(rangeV1, rangeV2, rangeV3)
}
def buildRangeYearSplit(minYearSplit:YearSplit, maxYearSplit:YearSplit):RangeYearSplit = {
    val step = 1
    val atomRang = Range(201301,201312+1, step)
    val result = RangeYearSplit(atomRang, atomRang, atomRang)
    
    return result
}
def buildRangeYearMoney(minYearMoney:YearMoney, maxYearMoney:YearMoney):RangeYearMoney = {
    val stepMoney1 = computeSuitableStep(minYearMoney.money1, maxYearMoney.money1)
    val stepMoney2 = computeSuitableStep(minYearMoney.money2, maxYearMoney.money2)
    val stepMoney3 = computeSuitableStep(minYearMoney.money3, maxYearMoney.money3)

    val rangeMoney1 = buildPositiveRangeOfDouble(minYearMoney.money1, maxYearMoney.money1, stepMoney1)
    val rangeMoney2 = buildPositiveRangeOfDouble(minYearMoney.money2, maxYearMoney.money2, stepMoney2)
    val rangeMoney3 = buildPositiveRangeOfDouble(minYearMoney.money3, maxYearMoney.money3, stepMoney3)
    
    return RangeYearMoney(rangeMoney1, rangeMoney2, rangeMoney3)
}

val rangeYearVolume = buildRangeYearVolume(extremeYearVolumePriceInfo.min.yearVolume, extremeYearVolumePriceInfo.max.yearVolume)
val rangeYearSplit = buildRangeYearSplit(extremeYearVolumePriceInfo.min.yearSplit, extremeYearVolumePriceInfo.max.yearSplit)
val rangeYearMoney = buildRangeYearMoney(extremeYearVolumePriceInfo.min.yearMoney, extremeYearVolumePriceInfo.max.yearMoney)

// ----------------------------------------------------------------------------
// 将 Range 变为 Broadcast variable
val broadcastRef_rangeYearVolume = sc.broadcast(rangeYearVolume)
val broadcastRef_rangeYearSplit = sc.broadcast(rangeYearSplit)
val broadcastRef_rangeYearMoney = sc.broadcast(rangeYearVolume)
// ****************************************************************************
//  三. 基于 yearVolumePriceInfoRDD , 分别在区间 rangeYearVolume, rangeYearSplit, rangeYearMoney 执行统计
// ****************************************************************************
// ----------------------------------------------------------------------------
// 样本数据
// val first = yearVolumePriceInfoRDD.first
// first: YearVolumePriceInfoItem = YearVolumePriceInfoItem(7130391510,YearVolume(123.0,0.0,0.0),YearSplit(201302,null,null),YearMoney(61.5,61.5,0.0,0.0))

// ----------------------------------------------------------------------------
// 求一个值在Range中位置 假定为"左闭右开区间"
// r.start r.last r.step r.numRangeElements r.indices
def GetRangeIndex(r:scala.collection.immutable.Range, d:Double):Int = {
    val start = r.start
    val last = r.last
    val step = r.step
    val numRangeElements = r.numRangeElements
    
    if (d < start) return -1
    if (d > last) return numRangeElements
    
    val quotient = ((d - start) / step).toInt
    val remainder = (d - start) % step
    
    // 区间的索引从1开始
    val index = quotient + 1
    //val index = if (remainder == 0) quotient else quotient
    
    return index
}

def GetRangeIndex_YearSplit(r:scala.collection.immutable.Range, ym:String):Int = {
    if (null == ym) return -1
    
    val index = r.indexOf(ym.toInt)
    // 区间的索引从1开始
    val finalIndex = index+1
    
    return finalIndex
}
// ----------------------------------------------------------------------------
// 将 yearVolumePriceInfoRDD 变换为 对应的Range的区间索引 : 数据准备与函数定义

case class RangeIndexItem(i1:Int, i2:Int, i3:Int)
//case class YearVolumePriceInfoItem(id:String, yearVolume:YearVolume, yearSplit:YearSplit, yearMoney:YearMoney)
case class YearVolumePriceInfoItem_RangeIndex(id:String, yearVolume:RangeIndexItem, yearSplit:RangeIndexItem, yearMoney:RangeIndexItem)

def map2RangeIndex(item:YearVolumePriceInfoItem):YearVolumePriceInfoItem_RangeIndex = {
    val id = item.id
    val yearVolume = item.yearVolume
    val yearSplit = item.yearSplit
    val yearMoney = item.yearMoney
    
    val rangeYearVolume = broadcastRef_rangeYearVolume.value
    val rangeYearSplit = broadcastRef_rangeYearSplit.value
    val rangeYearMoney = broadcastRef_rangeYearMoney.value
    
    val rangeIndex_yearVolume = RangeIndexItem(
            GetRangeIndex(rangeYearVolume.r1, yearVolume.v1),
            GetRangeIndex(rangeYearVolume.r2, yearVolume.v2),
            GetRangeIndex(rangeYearVolume.r3, yearVolume.v3)  )
    val rangeIndex_yearSplit = RangeIndexItem(
            GetRangeIndex_YearSplit(rangeYearSplit.r1, yearSplit.mL0),
            GetRangeIndex_YearSplit(rangeYearSplit.r2, yearSplit.mL1),
            GetRangeIndex_YearSplit(rangeYearSplit.r3, yearSplit.mL2)  )
    val rangeIndex_yearMoney = RangeIndexItem(
            GetRangeIndex(rangeYearMoney.r1, yearMoney.money1),
            GetRangeIndex(rangeYearMoney.r2, yearMoney.money2),
            GetRangeIndex(rangeYearMoney.r3, yearMoney.money3)  )
    
    return YearVolumePriceInfoItem_RangeIndex(id, rangeIndex_yearVolume, rangeIndex_yearSplit, rangeIndex_yearMoney)
}

// ----------------------------------------------------------------------------
// 将 yearVolumePriceInfoRDD 变换为 对应的Range的区间索引 : 执行函数
val yearVolumePriceRangeIndexRDD = yearVolumePriceInfoRDD.map(x => map2RangeIndex(x))

// 样本数据测试
val s1 = yearVolumePriceInfoRDD.take(1000)
val s2 = s1.map(x => map2RangeIndex(x))

/*
scala> yearVolumePriceRangeIndexRDD.first
14/08/21 16:34:15 INFO spark.SparkContext: Starting job: first at <console>:147
14/08/21 16:34:15 INFO scheduler.DAGScheduler: Got job 24 (first at <console>:147) with 1 output partitions (allowLocal=true)
14/08/21 16:34:15 INFO scheduler.DAGScheduler: Final stage: Stage 47(first at <console>:147)
14/08/21 16:34:15 INFO scheduler.DAGScheduler: Parents of final stage: List(Stage 48)
14/08/21 16:34:15 INFO scheduler.DAGScheduler: Missing parents: List()
14/08/21 16:34:15 INFO scheduler.DAGScheduler: Submitting Stage 47 (MappedRDD[31] at map at <console>:144), which has no missing parents
14/08/21 16:34:15 INFO scheduler.DAGScheduler: Submitting 1 missing tasks from Stage 47 (MappedRDD[31] at map at <console>:144)
14/08/21 16:34:15 INFO cluster.YarnClientClusterScheduler: Adding task set 47.0 with 1 tasks
14/08/21 16:34:15 INFO scheduler.TaskSetManager: Starting task 47.0:0 as TID 2226 on executor 7: master-hadoop (PROCESS_LOCAL)
14/08/21 16:34:15 INFO scheduler.TaskSetManager: Serialized task 47.0:0 as 16667 bytes in 1 ms
14/08/21 16:34:16 WARN scheduler.TaskSetManager: Lost TID 2226 (task 47.0:0)
14/08/21 16:34:16 WARN scheduler.TaskSetManager: Loss was due to java.lang.ExceptionInInitializerError
java.lang.ExceptionInInitializerError
	at $line93.$read$$iwC.<init>(<console>:6)
	at $line93.$read.<init>(<console>:43)
	at $line93.$read$.<init>(<console>:47)
	at $line93.$read$.<clinit>(<console>)
	at $line142.$read$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC.map2RangeIndex(<console>:112)
	at $line143.$read$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$anonfun$1.apply(<console>:144)
	at $line143.$read$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$anonfun$1.apply(<console>:144)
	at scala.collection.Iterator$$anon$11.next(Iterator.scala:328)
	at scala.collection.Iterator$$anon$10.next(Iterator.scala:312)
	at scala.collection.Iterator$class.foreach(Iterator.scala:727)
	at scala.collection.AbstractIterator.foreach(Iterator.scala:1157)
	at scala.collection.generic.Growable$class.$plus$plus$eq(Growable.scala:48)
	at scala.collection.mutable.ArrayBuffer.$plus$plus$eq(ArrayBuffer.scala:103)
	at scala.collection.mutable.ArrayBuffer.$plus$plus$eq(ArrayBuffer.scala:47)
	at scala.collection.TraversableOnce$class.to(TraversableOnce.scala:273)
	at scala.collection.AbstractIterator.to(Iterator.scala:1157)
	at scala.collection.TraversableOnce$class.toBuffer(TraversableOnce.scala:265)
	at scala.collection.AbstractIterator.toBuffer(Iterator.scala:1157)
	at scala.collection.TraversableOnce$class.toArray(TraversableOnce.scala:252)
	at scala.collection.AbstractIterator.toArray(Iterator.scala:1157)
	at org.apache.spark.rdd.RDD$$anonfun$27.apply(RDD.scala:1056)
	at org.apache.spark.rdd.RDD$$anonfun$27.apply(RDD.scala:1056)
	at org.apache.spark.SparkContext$$anonfun$runJob$4.apply(SparkContext.scala:1096)
	at org.apache.spark.SparkContext$$anonfun$runJob$4.apply(SparkContext.scala:1096)
	at org.apache.spark.scheduler.ResultTask.runTask(ResultTask.scala:112)
	at org.apache.spark.scheduler.Task.run(Task.scala:51)
	at org.apache.spark.executor.Executor$TaskRunner.run(Executor.scala:187)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
14/08/21 16:34:16 INFO scheduler.TaskSetManager: Starting task 47.0:0 as TID 2227 on executor 8: master-hadoop (PROCESS_LOCAL)
*/


