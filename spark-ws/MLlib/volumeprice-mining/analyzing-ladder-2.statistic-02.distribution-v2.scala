// ----------------------------------------------------------------------------
/**
 * 执行统计分析: 阶梯电量电
 *      基于 yearVolumePriceInfoRDD , 分别在区间 rangeYearVolume, rangeYearSplit, rangeYearMoney 执行统计
 * 
 *      一. 基于 yearVolumePriceInfoRDD , 分别在区间 rangeYearVolume, rangeYearSplit, rangeYearMoney 将数据变换为RangeIndex
 *          =>  yearVolumePriceRangeIndexRDD
 *      
 *
 * 引入依赖
 
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/transformations-of-volumeprice.scala
val datasetID = "s01" // s98
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/create-rdd-of-volumeprice-2.getmappedData.scala
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/analyzing-ladder-1.computeYearVolumePriceInfo.scala
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/analyzing-ladder-2.statistic-01.rangeYear-v2.scala

// 引入并执行
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/analyzing-ladder-2.statistic-02.distribution.scala

:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/util.scala
// ----------------------------------------------------------------------------
// 写入文件
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/util.scala
write3RangeCountObj2File(rangeCountAccObj_yearVolume, datasetID + "_yearVolume")
write3RangeCountObj2File(rangeCountAccObj_yearSplit, datasetID + "_yearSplit")
write3RangeCountObj2File(rangeCountAccObj_yearMoney, datasetID + "_yearMoney")
 *
 */

// ****************************************************************************
//  一. 基于 yearVolumePriceInfoRDD , 分别在区间 rangeYearVolume, rangeYearSplit, rangeYearMoney 将数据变换为RangeIndex
//      =>  yearVolumePriceRangeIndexRDD
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
    //val index = quotient + 1
    // 区间的索引从0开始, 与Range的zipWithIndex保持一致
    val index = quotient
    //val index = if (remainder == 0) quotient else quotient
    
    return index
    /*
    测试代码
    val start = 1
    val end = 3600
    val step = 100
    val last = end - step
    val r = start until last by step
    GetRangeIndex(r, start-1)
    GetRangeIndex(r, start)
    GetRangeIndex(r, start+1)
    GetRangeIndex(r, last-1)
    GetRangeIndex(r, last)
    GetRangeIndex(r, last+1)
    GetRangeIndex(r, end-1)
    GetRangeIndex(r, end)
    GetRangeIndex(r, end+1)
    */
}

def GetRangeIndex_YearSplit(r:scala.collection.immutable.Range, ym:String):Int = {
    if (null == ym) return -1
    
    val index = r.indexOf(ym.toInt)
    // 区间的索引从1开始
    //val finalIndex = index+1
    // 区间的索引从0开始, 与Range的zipWithIndex保持一致
    val finalIndex = index
    
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

// 样本数据
// yearVolumePriceInfoRDD
//YearVolumePriceInfoItem(7130391510,YearVolume(123.0,0.0,0.0),YearSplit(201302,null,null),YearMoney(61.5,61.5,0.0,0.0))
//YearVolumePriceInfoItem(0268425993,YearVolume(2591.0,1900.0,239.0),YearSplit(201301,201303,201307),YearMoney(2836.0,1080.0,660.0,1096.0))

// yearVolumePriceRangeIndexRDD
//YearVolumePriceInfoItem_RangeIndex(7130391510,RangeIndexItem(2,1,1),RangeIndexItem(2,-1,-1),RangeIndexItem(1,1,1))
//YearVolumePriceInfoItem_RangeIndex = YearVolumePriceInfoItem_RangeIndex(0268425993,RangeIndexItem(26,20,2),RangeIndexItem(1,3,7),RangeIndexItem(11,7,6))

// ****************************************************************************
//  二. 基于 yearVolumePriceRangeIndexRDD , 分别在区间 rangeYearVolume, rangeYearSplit, rangeYearMoney 执行统计
// ****************************************************************************
// ----------------------------------------------------------------------------
// 对象定义: 变换为counter对象
//case class RangeIndexCounter(index:Int, count:Long)
//case class RangeIndexCountItem(c1:RangeIndexCounter, c2:RangeIndexCounter, c3:RangeIndexCounter) // 费内存 ?????
case class RangeIndexCountItem(c1:(Int, Long), c2:(Int, Long), c3:(Int, Long))
case class YearVolumePriceInfoItem_RangeIndexCount(id:String, yearVolume:RangeIndexCountItem, yearSplit:RangeIndexCountItem, yearMoney:RangeIndexCountItem)

//zero 对象
val zeroRangeIndexCountItem = RangeIndexCountItem((-1,0),(-1,0),(-1,0))
val zeroYearVolumePriceInfoItem_RangeIndexCount = YearVolumePriceInfoItem_RangeIndexCount("***zero***", zeroRangeIndexCountItem,zeroRangeIndexCountItem,zeroRangeIndexCountItem)

// ----------------------------------------------------------------------------
// 定义函数: 变换为counter对象
// 将 yearVolumePriceRangeIndexRDD 变换为 yearVolumePriceRangeIndexCounterRDD 
def map2RangeIndexCount(item:YearVolumePriceInfoItem_RangeIndex):YearVolumePriceInfoItem_RangeIndexCount = {
    val id = item.id
    val yearVolume = item.yearVolume
    val yearSplit = item.yearSplit
    val yearMoney = item.yearMoney
    
    val rangeIndexCount_yearVolume = RangeIndexCountItem((yearVolume.i1,1), (yearVolume.i2,1), (yearVolume.i3,1))
    val rangeIndexCount_yearSplit = RangeIndexCountItem((yearSplit.i1,1), (yearSplit.i2,1), (yearSplit.i3,1))
    val rangeIndexCount_yearMoney = RangeIndexCountItem((yearMoney.i1,1), (yearMoney.i2,1), (yearMoney.i3,1))
    
    return YearVolumePriceInfoItem_RangeIndexCount(id, rangeIndexCount_yearVolume, rangeIndexCount_yearSplit, rangeIndexCount_yearMoney)
}

// ----------------------------------------------------------------------------
// 执行函数
// 将 yearVolumePriceRangeIndexRDD 变换为 yearVolumePriceRangeIndexCounterRDD
val yearVolumePriceRangeIndexCounterRDD = yearVolumePriceRangeIndexRDD.map(x => map2RangeIndexCount(x))

// ----------------------------------------------------------------------------
// 对象定义: 基于Counter对象进行统计
case class RangeCountAccObj(counter1:List[((Int, Int), Long)],
    counter2:List[((Int, Int), Long)],
    counter3:List[((Int, Int), Long)])

case class YearVolumePriceInfo_RangeCountAccObj(yearVolume:RangeCountAccObj, yearSplit:RangeCountAccObj, yearMoney:RangeCountAccObj)
// 空 YearVolumePriceInfo_RangeCountAccObj 对象
val nullYearVolumePriceInfo_RangeCountAccObj:YearVolumePriceInfo_RangeCountAccObj = null

// ----------------------------------------------------------------------------
// 定义函数: 基于Counter对象进行统计
// 将Range变换为含索引的区间(左闭右开区间): 本来应该是(rangeIndices,0)
def ConvertRange2ZeroRangeCountItem(r:scala.collection.immutable.Range):List[((Int, Int), Long)] = {
    val rangeCountAccObj = r.zipWithIndex.map(x => (x,0:Long)).toList
    //val rangeCountAccObj = r.indices.map(x => (x,0:Long)).toList
    
    return rangeCountAccObj
    
    // 测试代码
    //val r = 0 until 3600 by 100; ConvertRange2RangeCountAccObj(r)
}
def Convert3Ranges2ZeroRangeCountAccObj(r1:scala.collection.immutable.Range,
                            r2:scala.collection.immutable.Range,
                            r3:scala.collection.immutable.Range):RangeCountAccObj = {
    val counter1 = ConvertRange2ZeroRangeCountItem(r1)
    val counter2 = ConvertRange2ZeroRangeCountItem(r2)
    val counter3 = ConvertRange2ZeroRangeCountItem(r3)
    
    return RangeCountAccObj(counter1, counter2, counter3)
    
    // 测试代码
    // val r1 = 0 until 3600 by 100; val r2 = 1 until 3600 by 77;val r3 = -333 until -1 by 111;
    // val result = Convert3Ranges2RangeCountAccObj(r1,r2,r3)
}

// zero 对象: YearVolumePriceInfo_RangeCountAccObj 的成分
val zeroRangeCountAccObj_yearVolume = Convert3Ranges2ZeroRangeCountAccObj(
    broadcastRef_rangeYearVolume.value.r1, 
    broadcastRef_rangeYearVolume.value.r2, 
    broadcastRef_rangeYearVolume.value.r3)
val zeroRangeCountAccObj_yearSplit = Convert3Ranges2ZeroRangeCountAccObj(
    broadcastRef_rangeYearSplit.value.r1,
    broadcastRef_rangeYearSplit.value.r2,
    broadcastRef_rangeYearSplit.value.r3)
val zeroRangeCountAccObj_yearMoney = Convert3Ranges2ZeroRangeCountAccObj(
    broadcastRef_rangeYearMoney.value.r1,
    broadcastRef_rangeYearMoney.value.r2,
    broadcastRef_rangeYearMoney.value.r3)

// zero 对象: YearVolumePriceInfo_RangeCountAccObj
val zeroYearVolumePriceInfo_RangeCountAccObj = YearVolumePriceInfo_RangeCountAccObj(zeroRangeCountAccObj_yearVolume, zeroRangeCountAccObj_yearSplit, zeroRangeCountAccObj_yearMoney)
// zero 对象: Tuple2(YearVolumePriceInfoItem_RangeIndexCount, YearVolumePriceInfo_RangeCountAccObj)
//val zeroObj_RangeIndexCount_RangeCountAccObj = Tuple2(zeroYearVolumePriceInfoItem_RangeIndexCount, zeroYearVolumePriceInfo_RangeCountAccObj)
val zeroObj_RangeIndexCount_RangeCountAccObj = Tuple2(null:YearVolumePriceInfoItem_RangeIndexCount, zeroYearVolumePriceInfo_RangeCountAccObj)

// ------------------------------------------------------------------------
// 定义函数: 基于Counter对象进行统计: 统计合并函数

// ------------------------------------------------------------------------
// 生成newAcc
// 计数器: ((Int, Int), Long)  当前值: (Int, Long)
def buildNewCounter(x:((Int, Int), Long),y:(Int, Long)) = {
     x match { case((start:Int, y._1), count:Long) => ((start, y._1),count+y._2); case other => other} 
}
/*
def buildNewCounter(x:((Int, Int), Long),y:(Int, Long)):((Int, Int), Long) = {
    if (x._1._2 == y._1) {
        ((x._1._1, x._1._2), x._2 + y._2)
    } else {x}
}
*/
    
// 从左向右执行计算 foldLeft[B](z: B)(op: (A, B) => B): B
// 这个函数供fold调用,但实现有问题: 只考虑了分区内部的计算,未考虑将两个分区的结果进行合并 --> 改为通过aggregate调用
def mergeCombiner_VolumePriceRangeIndexCounterRDD_Accumulator_v1_ERROR(
    x:(YearVolumePriceInfoItem_RangeIndexCount, YearVolumePriceInfo_RangeCountAccObj),
    y:(YearVolumePriceInfoItem_RangeIndexCount, YearVolumePriceInfo_RangeCountAccObj)
    ):(YearVolumePriceInfoItem_RangeIndexCount, YearVolumePriceInfo_RangeCountAccObj) = {
    
    //val item_x = x._1     // 不取左边参数的item
    val acc_x = x._2
    val acc_yearVolume = acc_x.yearVolume
    val acc_yearSplit = acc_x.yearSplit
    val acc_yearMoney = acc_x.yearMoney

    val item_y = y._1
    //val acc_y = y._2      // 不取右边参数的Accumulator
    val item_yearVolume = item_y.yearVolume
    val item_yearSplit = item_y.yearSplit
    val item_yearMoney = item_y.yearMoney
    
    // acc_yearVolume
    val newCounter1_yearVolume = (acc_yearVolume.counter1).map(x => buildNewCounter(x,item_yearVolume.c1))
    val newCounter2_yearVolume = (acc_yearVolume.counter2).map(x => buildNewCounter(x,item_yearVolume.c2))
    val newCounter3_yearVolume = (acc_yearVolume.counter3).map(x => buildNewCounter(x,item_yearVolume.c3))    
    val newAcc_yearVolume = RangeCountAccObj(newCounter1_yearVolume,newCounter2_yearVolume,newCounter3_yearVolume)
    
    // acc_yearSplit
    val newCounter1_yearSplit = (acc_yearSplit.counter1).map(x => buildNewCounter(x,item_yearSplit.c1))
    val newCounter2_yearSplit = (acc_yearSplit.counter2).map(x => buildNewCounter(x,item_yearSplit.c2))
    val newCounter3_yearSplit = (acc_yearSplit.counter3).map(x => buildNewCounter(x,item_yearSplit.c3))
    val newAcc_yearSplit = RangeCountAccObj(newCounter1_yearSplit,newCounter2_yearSplit,newCounter3_yearSplit)
    
    // acc_yearMoney
    val newCounter1_yearMoney = (acc_yearMoney.counter1).map(x => buildNewCounter(x,item_yearMoney.c1))
    val newCounter2_yearMoney = (acc_yearMoney.counter2).map(x => buildNewCounter(x,item_yearMoney.c2))
    val newCounter3_yearMoney = (acc_yearMoney.counter3).map(x => buildNewCounter(x,item_yearMoney.c3))
    val newAcc_yearMoney = RangeCountAccObj(newCounter1_yearMoney,newCounter2_yearMoney,newCounter3_yearMoney)
    
    // 结果对象
    val newAcc = YearVolumePriceInfo_RangeCountAccObj(newAcc_yearVolume,newAcc_yearSplit,newAcc_yearMoney)
    // 因为后面mergeCombiner_*时从来不使用左边参数的 item, 所以每个对象添加一个空item :::: 这是做不到的!!!!
    //val nullItem:YearVolumePriceInfoItem_RangeIndexCount = null
    //val newObj = Tuple2(nullItem, newAcc)
    
    // 使用nullItem会出现错误,所以,结果对象的item使用右边参数的item
    // 这里失败的原因是因为这个函数是给RDD调用的,而RDD的各个分区合并时,就需要使用第一个参数了!
    // 比较:  文件"analyzing-ladder-1.computeYearVolumePriceInfo.scala"中的def mergeCombiner
    val newObj = Tuple2(item_y, newAcc)
    
    return newObj
    
    // 测试代码
    // val x = zeroObj_RangeIndexCount_RangeCountAccObj; val y = Tuple2(yearVolumePriceRangeIndexCounterRDD.first,null:YearVolumePriceInfo_RangeCountAccObj)
    // ......
    // val list = acc_yearVolume.counter1
    // val newAccList =  list.map(x => buildNewAcc(x,(0,1)))
}

// 这个函数供fold调用,但实现有问题: 只考虑了分区内部的计算,未考虑将两个分区的结果进行合并 --> 改为通过aggregate调用
// 这个函数供fold调用,但实现有问题: 只考虑了分区内部的计算,未考虑将两个分区的结果进行合并 --> 改为通过aggregate调用
def mergeCombiner_VolumePriceRangeIndexCounterRDD_seqOp(
    acc:YearVolumePriceInfo_RangeCountAccObj,
    item:YearVolumePriceInfoItem_RangeIndexCount
    ):YearVolumePriceInfo_RangeCountAccObj = {
    
    val acc_yearVolume = acc.yearVolume
    val acc_yearSplit = acc.yearSplit
    val acc_yearMoney = acc.yearMoney

    val itemearVolume = item.yearVolume
    val itemearSplit = item.yearSplit
    val itemearMoney = item.yearMoney
    
    // 左边的参数可以被修改,所以,不再调用 buildNewCounter, 而是直接修改
    
    val newCounter1_yearVolume = 
    
    // acc_yearVolume
    val newCounter1_yearVolume = (acc_yearVolume.counter1).map(x => buildNewCounter(x,itemearVolume.c1))
    val newCounter2_yearVolume = (acc_yearVolume.counter2).map(x => buildNewCounter(x,itemearVolume.c2))
    val newCounter3_yearVolume = (acc_yearVolume.counter3).map(x => buildNewCounter(x,itemearVolume.c3))    
    val newAcc_yearVolume = RangeCountAccObj(newCounter1_yearVolume,newCounter2_yearVolume,newCounter3_yearVolume)
    
    // acc_yearSplit
    val newCounter1_yearSplit = (acc_yearSplit.counter1).map(x => buildNewCounter(x,itemearSplit.c1))
    val newCounter2_yearSplit = (acc_yearSplit.counter2).map(x => buildNewCounter(x,itemearSplit.c2))
    val newCounter3_yearSplit = (acc_yearSplit.counter3).map(x => buildNewCounter(x,itemearSplit.c3))
    val newAcc_yearSplit = RangeCountAccObj(newCounter1_yearSplit,newCounter2_yearSplit,newCounter3_yearSplit)
    
    // acc_yearMoney
    val newCounter1_yearMoney = (acc_yearMoney.counter1).map(x => buildNewCounter(x,itemearMoney.c1))
    val newCounter2_yearMoney = (acc_yearMoney.counter2).map(x => buildNewCounter(x,itemearMoney.c2))
    val newCounter3_yearMoney = (acc_yearMoney.counter3).map(x => buildNewCounter(x,itemearMoney.c3))
    val newAcc_yearMoney = RangeCountAccObj(newCounter1_yearMoney,newCounter2_yearMoney,newCounter3_yearMoney)
    
    // 结果对象
    val newAcc = YearVolumePriceInfo_RangeCountAccObj(newAcc_yearVolume,newAcc_yearSplit,newAcc_yearMoney)
    
    // 这是一个 在每一个 partition 内部调用的函数, 所以可以将item设置为null
    // 因为后面mergeCombiner_*时从来不使用左边参数的 item, 所以每个对象添加一个空item
    val nullItem:YearVolumePriceInfoItem_RangeIndexCount = null
    val newObj = Tuple2(nullItem, newAcc)
    
    return newObj
    
    // 测试代码
    // val x = zeroObj_RangeIndexCount_RangeCountAccObj; val y = Tuple2(yearVolumePriceRangeIndexCounterRDD.first,null:YearVolumePriceInfo_RangeCountAccObj)
    // ......
    // val list = acc_yearVolume.counter1
    // val newAccList =  list.map(x => buildNewAcc(x,(0,1)))
}
// ----------------------------------------------------------------------------
// 执行函数
// (1) 因为后面mergeCombiner_*时从来不使用右边参数的 accumulator, 所以每个对象添加一个空统计对象
val yearVolumePriceRangeIndexCounterRDD_nullAccumulator = yearVolumePriceRangeIndexCounterRDD.map(x => (x, nullYearVolumePriceInfo_RangeCountAccObj))

// (2) fold
val resultObj_RangeIndexCount_RangeCountAccObj = yearVolumePriceRangeIndexCounterRDD_nullAccumulator.fold(zeroObj_RangeIndexCount_RangeCountAccObj)((x,y) => mergeCombiner_VolumePriceRangeIndexCounterRDD_Accumulator(x,y))

// 测试语句
val s1 = yearVolumePriceRangeIndexCounterRDD_nullAccumulator.take(100)
val zero = zeroObj_RangeIndexCount_RangeCountAccObj

val acc0 = zero
val acc1 = mergeCombiner_VolumePriceRangeIndexCounterRDD_Accumulator(acc0,s1(0))
val acc2 = mergeCombiner_VolumePriceRangeIndexCounterRDD_Accumulator(acc1,s1(1))
val acc3 = mergeCombiner_VolumePriceRangeIndexCounterRDD_Accumulator(acc2,s1(2))

s1(0)
s1(1)
s1(2)
printAccumulator(acc0)
printAccumulator(acc1)
printAccumulator(acc2)
printAccumulator(acc3)

val acc1_2 = mergeCombiner_VolumePriceRangeIndexCounterRDD_Accumulator(acc1,s1(0))
val acc1_3 = mergeCombiner_VolumePriceRangeIndexCounterRDD_Accumulator(acc1_2,s1(0))
printAccumulator(acc1_2)
printAccumulator(acc1_3)

val acc1 = acc1_3

// (3) 打印结果 或 写入文件
val result_RangeCountAccObj = resultObj_RangeIndexCount_RangeCountAccObj._2

val rangeCountAccObj_yearVolume = result_RangeCountAccObj.yearVolume
val rangeCountAccObj_yearSplit = result_RangeCountAccObj.yearSplit
val rangeCountAccObj_yearMoney = result_RangeCountAccObj.yearMoney

def print3RangeCountObj(rangeCountAccObj3: RangeCountAccObj, name:String) = {
    val acc1 = rangeCountAccObj3.counter1
    val acc2 = rangeCountAccObj3.counter2
    val acc3 = rangeCountAccObj3.counter3
    
    // 将((Int, Int), Long)) 转换为 (Int, (Int, Int), Long))
    def convert2interval(list:List[((Int, Int), Long)]):List[(Int, (Int, Int), Long)] = {
        val length = list.length
        
        val range = 0 until length by 1
        
        // for comprehension       
        val result = for {
            //i <- range; item <- list        // 这是List中的List
            i <- range              // 这是List
        } yield {
            val item = list(i)      // 没有 item<-list 时需要该行语句
            val begin = item._1._1;
            val index = item._1._2;
            val count = item._2;
            
            val end = if(i==length -1) // 最后一个 
                        Int.MaxValue   // 最大Int
                      else 
                        list(i+1)._1._1;
                        
            val obj = Tuple3(index, Tuple2(begin, end), count)
            obj
        }
        
        return result.toList       // 转换为List
        
        /*
        // 改写的 for
        val result = range.map(i => list.map(item => {
            val begin = item._1._1;
            val index = item._1._2;
            val count = item._2;
            
            val end = if(i==length -1) // 最后一个 
                        9999999   // 随便一个很大的整数
                      else 
                        list(i+1)._1._1;
                        
            val obj = Tuple3(index, Tuple2(begin, end), count)
            obj
        } ))
        */        
    }
    
    ////val map2String = (x:((Int, Int), Long)) => x match { case ((v:Int, index:Int), count:Long) => s"${v},${index},${count}"}
    //val convertAndPrint = (x:((Int, Int), Long)) => x match { case ((v:Int, index:Int), count:Long) => val str = s"${v},${index},${count}"; println(str)}
    
    
    val convertAndPrint = (x:(Int, (Int, Int), Long)) => x match { case ((index:Int, (begin:Int, end:Int), count:Long)) => val str = s"${index},${begin},${end},${count}"; println(str)}
    
    val colsDesc = "索引,起始值(含),终止值(不含),计数"
    println("----------------------------------------------------------------")
    println(s"\n${name}.counter1 \n${colsDesc}")
    convert2interval(acc1).foreach(convertAndPrint)
    println(s"\n${name}.counter2 \n${colsDesc}")
    convert2interval(acc2).foreach(convertAndPrint)
    println(s"\n${name}.counter3 \n${colsDesc}")
    convert2interval(acc3).foreach(convertAndPrint)
}

// 执行函数
print3RangeCountObj(rangeCountAccObj_yearVolume, "yearVolume")
print3RangeCountObj(rangeCountAccObj_yearSplit, "yearSplit")
print3RangeCountObj(rangeCountAccObj_yearMoney, "yearMoney")


// ------------------------------------------------------------------------------
// 曾经的错误日志:
/*

    // 因为后面mergeCombiner_*时从来不使用左边参数的 item, 所以每个对象添加一个空item
    //val nullItem:YearVolumePriceInfoItem_RangeIndexCount = null
    //val newObj = Tuple2(nullItem, newAcc)
    
    会导致出现下面错误!!!  (但通过take(Int.MaxValue)后则没有问题)
    
    // 这里失败的原因是因为这个函数是给RDD调用的,而RDD的各个分区合并时,就需要使用第一个参数了!
    // 比较:  文件"analyzing-ladder-1.computeYearVolumePriceInfo.scala"中的def mergeCombiner
    
scala> val resultObj_RangeIndexCount_RangeCountAccObj = yearVolumePriceRangeIndexCounterRDD_nullAccumulator.fold(zeroObj_RangeIndexCount_RangeCountAccObj)((x,y) => mergeCombiner_VolumePriceRangeIndexCounterRDD_Accumulator(x,y))
14/08/22 15:52:04 INFO spark.SparkContext: Starting job: fold at <console>:162
14/08/22 15:52:04 INFO scheduler.DAGScheduler: Got job 2 (fold at <console>:162) with 200 output partitions (allowLocal=false)
14/08/22 15:52:04 INFO scheduler.DAGScheduler: Final stage: Stage 4(fold at <console>:162)
14/08/22 15:52:04 INFO scheduler.DAGScheduler: Parents of final stage: List(Stage 5)
14/08/22 15:52:04 INFO scheduler.DAGScheduler: Missing parents: List()
14/08/22 15:52:04 INFO scheduler.DAGScheduler: Submitting Stage 4 (MappedRDD[15] at map at <console>:138), which has no missing parents
14/08/22 15:52:04 INFO scheduler.DAGScheduler: Submitting 200 missing tasks from Stage 4 (MappedRDD[15] at map at <console>:138)
14/08/22 15:52:04 INFO cluster.YarnClientClusterScheduler: Adding task set 4.0 with 200 tasks
14/08/22 15:52:04 INFO scheduler.TaskSetManager: Starting task 4.0:0 as TID 600 on executor 1: master-hadoop (PROCESS_LOCAL)
14/08/22 15:52:04 INFO scheduler.TaskSetManager: Serialized task 4.0:0 as 19066 bytes in 0 ms
14/08/22 15:52:04 INFO scheduler.TaskSetManager: Starting task 4.0:1 as TID 601 on executor 2: master-hadoop (PROCESS_LOCAL)
14/08/22 15:52:04 INFO scheduler.TaskSetManager: Serialized task 4.0:1 as 19066 bytes in 0 ms
14/08/22 15:52:05 INFO scheduler.TaskSetManager: Starting task 4.0:2 as TID 602 on executor 2: master-hadoop (PROCESS_LOCAL)
14/08/22 15:52:05 INFO scheduler.TaskSetManager: Serialized task 4.0:2 as 19066 bytes in 0 ms
14/08/22 15:52:05 INFO scheduler.DAGScheduler: Completed ResultTask(4, 1)
14/08/22 15:52:05 INFO scheduler.TaskSetManager: Finished TID 601 in 975 ms on master-hadoop (progress: 1/200)
14/08/22 15:52:05 INFO scheduler.DAGScheduler: Failed to run fold at <console>:162
org.apache.spark.SparkDriverExecutionException: Execution error
	at org.apache.spark.scheduler.DAGScheduler.handleTaskCompletion(DAGScheduler.scala:846)
	at org.apache.spark.scheduler.DAGSchedulerEventProcessActor$$anonfun$receive$2.applyOrElse(DAGScheduler.scala:1228)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:498)
	at akka.actor.ActorCell.invoke(ActorCell.scala:456)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:237)
	at akka.dispatch.Mailbox.run(Mailbox.scala:219)
	at akka.dispatch.ForkJoinExecutorConfigurator$AkkaForkJoinTask.exec(AbstractDispatcher.scala:386)
	at scala.concurrent.forkjoin.ForkJoinTask.doExec(ForkJoinTask.java:260)
	at scala.concurrent.forkjoin.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1339)
	at scala.concurrent.forkjoin.ForkJoinPool.runWorker(ForkJoinPool.java:1979)
	at scala.concurrent.forkjoin.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:107)
Caused by: java.lang.NullPointerException
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC.mergeCombiner_VolumePriceRangeIndexCounterRDD_Accumulator(<console>:39)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$anonfun$1.apply(<console>:162)
	at $iwC$$iwC$$iwC$$iwC$$iwC$$iwC$$anonfun$1.apply(<console>:162)
	at org.apache.spark.rdd.RDD$$anonfun$20.apply(RDD.scala:853)
	at org.apache.spark.rdd.RDD$$anonfun$20.apply(RDD.scala:853)
	at org.apache.spark.scheduler.JobWaiter.taskSucceeded(JobWaiter.scala:56)
	at org.apache.spark.scheduler.DAGScheduler.handleTaskCompletion(DAGScheduler.scala:842)
	... 10 more


scala> 14/08/22 15:52:05 INFO scheduler.TaskSetManager: Starting task 4.0:3 as TID 603 on executor 2: master-hadoop (PROCESS_LOCAL)

*/

