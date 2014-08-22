// ----------------------------------------------------------------------------
/**
 * 执行统计分析: 阶梯电量电
 * 
 *      一. 求最小最大 YearVolume, YearSplit, YearMoney => extremeYearVolumePriceInfo
 *      二. 基于yearVolumePriceInfoRDD, 构造区间 => rangeYearVolume, rangeYearSplit, rangeYearMoney
 * 
 * 引入依赖
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/transformations-of-volumeprice.scala
 *      val datasetID = "s01" // s98
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/create-rdd-of-volumeprice-2.getmappedData.scala
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/analyzing-ladder-1.computeYearVolumePriceInfo.scala
 * 引入并执行
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/analyzing-ladder-2.statistic-01.rangeYear-v2.scala
 *
 */
/*
   这个版本执行 analyzing-ladder-2.statistic-02.distribution.scala 没有错误
scala> yearVolumePriceRangeIndexRDD.first
scala> yearVolumePriceRangeIndexRDD.first
14/08/22 08:35:35 INFO spark.SparkContext: Starting job: first at <console>:129
14/08/22 08:35:35 INFO scheduler.DAGScheduler: Got job 5 (first at <console>:129) with 1 output partitions (allowLocal=true)
14/08/22 08:35:35 INFO scheduler.DAGScheduler: Final stage: Stage 8(first at <console>:129)
14/08/22 08:35:35 INFO scheduler.DAGScheduler: Parents of final stage: List(Stage 9)
14/08/22 08:35:35 INFO scheduler.DAGScheduler: Missing parents: List()
14/08/22 08:35:35 INFO scheduler.DAGScheduler: Submitting Stage 8 (MappedRDD[18] at map at <console>:126), which has no missing parents
14/08/22 08:35:35 INFO scheduler.DAGScheduler: Submitting 1 missing tasks from Stage 8 (MappedRDD[18] at map at <console>:126)
14/08/22 08:35:35 INFO cluster.YarnClientClusterScheduler: Adding task set 8.0 with 1 tasks
14/08/22 08:35:35 INFO scheduler.TaskSetManager: Starting task 8.0:0 as TID 1200 on executor 2: master-hadoop (PROCESS_LOCAL)
14/08/22 08:35:35 INFO scheduler.TaskSetManager: Serialized task 8.0:0 as 14432 bytes in 0 ms
14/08/22 08:35:36 INFO scheduler.TaskSetManager: Finished TID 1200 in 731 ms on master-hadoop (progress: 1/1)
14/08/22 08:35:36 INFO scheduler.DAGScheduler: Completed ResultTask(8, 0)
14/08/22 08:35:36 INFO cluster.YarnClientClusterScheduler: Removed TaskSet 8.0, whose tasks have all completed, from pool 
14/08/22 08:35:36 INFO scheduler.DAGScheduler: Stage 8 (first at <console>:129) finished in 0.744 s
14/08/22 08:35:36 INFO spark.SparkContext: Job finished: first at <console>:129, took 0.765056534 s
res60: YearVolumePriceInfoItem_RangeIndex = YearVolumePriceInfoItem_RangeIndex(7130391510,RangeIndexItem(2,1,1),RangeIndexItem(2,-1,-1),RangeIndexItem(1,1,1))
*/
// ----------------------------------------------------------------------------
// 数据样本
/*
YearVolumePriceInfoItem(0259649786,YearVolume(1976.0,0.0,0.0),YearSplit(201302,null,null),YearMoney(988.0,988.0,0.0,0.0))
 YearVolumePriceInfoItem(0268425993,YearVolume(2591.0,1900.0,239.0),YearSplit(201301,201303,201307),YearMoney(2836.0,1080.0,660.0,1096.0))
 */

// ****************************************************************************
// 一. 求最小最大 YearVolume, YearSplit, YearMoney => extremeYearVolumePriceInfo
// ****************************************************************************
// ----------------------------------------------------------------------------
case class ExtremeYearVolumePriceInfo(min:YearVolumePriceInfoItem, max:YearVolumePriceInfoItem)

val zerominExtremeYearVolumePriceInfo = 
    YearVolumePriceInfoItem("minYearVolumePriceInfo",
        YearVolume(0.0, 0.0, 0.0),
        YearSplit(null, null, null),
        buildYearMoney(0.0, 0.0, 0.0))
val zeromaxExtremeYearVolumePriceInfo = 
    YearVolumePriceInfoItem("maxYearVolumePriceInfo",
        YearVolume(0.0, 0.0, 0.0),
        YearSplit(null, null, null),
        buildYearMoney(0.0, 0.0, 0.0))

// 最小值
def ComputeMinYearVolumePriceInfo(x:YearVolumePriceInfoItem, y:YearVolumePriceInfoItem):YearVolumePriceInfoItem = {
    def MinYearSplit(a:YearSplit, b:YearSplit):YearSplit = {
        def MinString(s1:String, s2:String):String = {
            val min = if(s1 == null) { s2 } else {
                           if (s2 == null) s1 else {if(s1 < s2) s1 else s2}
                      }
            return min
        }
        
        return YearSplit(MinString(a.mL0, b.mL0), MinString(a.mL1, b.mL1), MinString(a.mL2, b.mL2))
    }
    
    return YearVolumePriceInfoItem(x.id,
                YearVolume((x.yearVolume.v1).min(y.yearVolume.v1),
                            (x.yearVolume.v2).min(y.yearVolume.v2),
                            (x.yearVolume.v3).min(y.yearVolume.v3)),
                MinYearSplit(x.yearSplit,y.yearSplit),                    
                buildYearMoney((x.yearMoney.money1).min(y.yearMoney.money1),
                            (x.yearMoney.money2).min(y.yearMoney.money2),
                            (x.yearMoney.money3).min(y.yearMoney.money3)))
}

// 最大值
def ComputeMaxYearVolumePriceInfo(x:YearVolumePriceInfoItem, y:YearVolumePriceInfoItem):YearVolumePriceInfoItem = {
    def MaxYearSplit(a:YearSplit, b:YearSplit):YearSplit = {
        def MaxString(s1:String, s2:String):String = {
            val max = if(s1 == null) { s2 } else {
                           if (s2 == null) s1 else {if(s1 > s2) s1 else s2}
                      }
            return max
        }
        
        return YearSplit(MaxString(a.mL0, b.mL0), MaxString(a.mL1, b.mL1),MaxString(a.mL2, b.mL2))
    }
    
    return YearVolumePriceInfoItem(x.id,
                YearVolume((x.yearVolume.v1).max(y.yearVolume.v1),
                            (x.yearVolume.v2).max(y.yearVolume.v2),
                            (x.yearVolume.v3).max(y.yearVolume.v3)),
                MaxYearSplit(x.yearSplit,y.yearSplit),                    
                buildYearMoney((x.yearMoney.money1).max(y.yearMoney.money1),
                            (x.yearMoney.money2).max(y.yearMoney.money2),
                            (x.yearMoney.money3).max(y.yearMoney.money3)))
}

// ----------------------------------------------------------------------------
// 执行计算
val minYearVolumePriceInfo = yearVolumePriceInfoRDD.fold(zerominExtremeYearVolumePriceInfo)((x,y) => ComputeMinYearVolumePriceInfo(x,y))
val maxYearVolumePriceInfo = yearVolumePriceInfoRDD.fold(zeromaxExtremeYearVolumePriceInfo)((x,y) => ComputeMaxYearVolumePriceInfo(x,y))
/*
val minYearVolumePriceInfo = yearVolumePriceInfoRDD.foldLeft(zerominExtremeYearVolumePriceInfo)((x,y) => ComputeMinYearVolumePriceInfo(x,y))
val maxYearVolumePriceInfo = yearVolumePriceInfoRDD.foldLeft(zeromaxExtremeYearVolumePriceInfo)((x,y) => ComputeMaxYearVolumePriceInfo(x,y))
*/

val extremeYearVolumePriceInfo = ExtremeYearVolumePriceInfo(
        minYearVolumePriceInfo,
        maxYearVolumePriceInfo)

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

