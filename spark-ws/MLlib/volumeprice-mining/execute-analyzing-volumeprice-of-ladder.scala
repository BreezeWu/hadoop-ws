// ----------------------------------------------------------------------------
/**
 * 执行分析: 阶梯电量电
 * 
 * 阶梯电量电价, 变量 mappedData_volumeprice_of_ladder
 * 分时电量电价, 变量 mappedData_volumeprice_of_ts
 *
 * 引入依赖
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/transformations-of-volumeprice.scala
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/create-rdd-of-volumeprice-2.getmappedData.scala
 * 引入并执行
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/execute-analyzing-volumeprice-of-ladder.scala
 *
 */

// ----------------------------------------------------------------------------
// 1. 每个用户的计算结果

// 一年累计 三档+total
case class MonthVolume(v1:Double, v2:Double, v3:Double)
case class YearVolume(v1:Double, v2:Double, v3:Double)
case class YearMoney(total:Double, money1:Double, money2:Double, money3:Double)
// 一年分档发生的月份: mL0是第一个用电量不为0的月份; mL1是达到第一档的月份; mL2是达到第二档的月份
case class YearSplit(mL0:String = null, mL1:String = null, mL2:String = null)
// id即cons_no
case class YearVolumePriceInfoItem(id:String, yearVolume:YearVolume, yearSplit:YearSplit, yearMoney:YearMoney)

// ----------------------------------------------------------------------------
// 2 计算年累计的阶梯电价信息
// 月用电量三档
val monthThresholdL1 = 180
val monthThresholdL2 = 280
// 年用电量三档
val yearThresholdL1 = 2160
val yearThresholdL2 = 3360
// 电价三档
val priceL1 = 0.50
val priceL2 = 0.55
val priceL3 = 0.80

// 计算月费用
def computeMonthMoney(v:Double) = {
    0.0
}
// 计算年费用
def computeYearMoney(v:Double):YearMoney = {
    var moneyL1 = 0.0
    var moneyL2 = 0.0
    var moneyL3 = 0.0

    if(v > yearThresholdL2) {
        moneyL3 = (v - yearThresholdL2) * priceL3   // 第三档
        moneyL2 = (yearThresholdL2 - yearThresholdL1) * priceL2   // 第二档
        moneyL1 = (yearThresholdL1) * priceL1   // 第一档
    } else {
        if(v > yearThresholdL1) {
            moneyL2 = (v - yearThresholdL1) * priceL2   // 第二档
            moneyL1 = (yearThresholdL1) * priceL1   // 第一档
        } else {
            moneyL1 = (v) * priceL1   // 第一档
        }
    }
    
    return YearMoney(moneyL1+moneyL2+moneyL3, moneyL1, moneyL2, moneyL3)
}

// ----------------------------------------------------------------------------
// 3. 从RDD中计算 VolumePriceInfoItem
val originRDD = mappedData_volumeprice_of_ladder
// (1) 将数据变成key-value格式: cons_no是key => org.apache.spark.rdd.RDD[(String, (String, Array[Double]))]
val pairRDD = originRDD.map(x => (x.index.cons_no, Tuple2(x.index.ym, x.data)))
// (2) 将 pairRDD 按照 cons_no 分组  =>  org.apache.spark.rdd.RDD[(String, Iterable[(String, Array[Double])])]
val groupedRDD = pairRDD.groupByKey().cache
// (3) 对分组数据计算出 VolumePriceInfoItem
// 函数: 对每一个用户的数据 (String, Iterable[(String, Array[Double])]), 计算 VolumePriceInfoItem
def computeYearVolumePriceInfo(item:(String, Iterable[(String, Array[Double])])):YearVolumePriceInfoItem = {
    val id = item._1
    val arrayBuf = item._2
    //val array = arrayBuf.toArray
    val list = arrayBuf.toList
    
    // 排序后处理: 日期从小到大, 利用 201301 这样的字符串的String排序 => ((String, MonthVolume), (YearVolume, YearSplit))
    val sortedList = list.sortWith((x,y) => x._1 < y._1)
    // 排序后处理: 日期从小到大, 将 201301 这样的字符串转换为Int后排 => ((Int, MonthVolume), (YearVolume, YearSplit))
    //val sortedList = list.map(x => (x._1.toInt, x._2)).sortWith((x,y) => x._1 < y._1)
    
    // sortedList: List[(String, Array[Double])] = List((201301,Array(0.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201302,Array(11.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201303,Array(0.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201304,Array(44.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201305,Array(26.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201306,Array(35.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201307,Array(0.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201308,Array(0.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201309,Array(7.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201310,Array(0.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201311,Array(0.0, 0.5, 0.0, 0.55, 0.0, 0.8)), (201312,Array(0.0, 0.5, 0.0, 0.55, 0.0, 0.8)))

    // 空 accumulator: totalVolume,YearSplit
    val zeroAcc = Tuple2(YearVolume(0.0, 0.0, 0.0),YearSplit(null:String,null:String,null:String))
    val zeroItem = (null:String, MonthVolume(0.0, 0.0, 0.0))
    val zeroObj = Tuple2(zeroItem,  zeroAcc)
    
    // 为每一个成员添加 zeroAcc, 并且只获取其月电量信息
    def item2Tuple2(x:(String, Array[Double])) = {
        Tuple2(x._1, MonthVolume((x._2)(0), (x._2)(2), (x._2)(4)))
    }
    val zeroAccList = sortedList.map(x => Tuple2(item2Tuple2(x),zeroAcc))
    
    // ------------------------------------------------------------------------
    // 从左向右执行计算 foldLeft[B](z: B)(op: (A, B) => B): B
    def mergeCombiner(x:((String, MonthVolume), (YearVolume, YearSplit)),
                    y:((String, MonthVolume), (YearVolume, YearSplit))) :((String, MonthVolume), (YearVolume, YearSplit)) = {
        // 测试变量
        // val x = ((201305,MonthVolume(26.0,0.0,0.0)),(YearVolume(0.0,0.0,0.0),YearSplit(null,null,null)))
        // val y = ((201306,MonthVolume(35.0,0.0,0.0)),(YearVolume(0.0,0.0,0.0),YearSplit(null,null,null)))
        
        //val item_x = x._1
        val acc_x = x._2        
        //val itemYm_x = item_x._1      // 年月
        //val itemMonthVolume_x = item_x._2   // 该月份中的阶梯电价信息
        val accYearVolume_x = acc_x._1  // 累计用电量
        val accYearSplit_x = acc_x._2   // YearSplit
        
        val item_y = y._1
        //val acc_y = y._2        
        val itemYm_y = item_y._1      // 年月
        val itemMonthVolume_y = item_y._2   // 该月份中的阶梯电价信息
        //val accYearVolume_y = acc_y._1  // 累计用电量
        //val accYearSplit_y = acc_y._2   // YearSplit
        
        // 将 accYearVolume_x 和 itemArray_y 相加 => newAccYearVolume
        // 年用电量累计
        val newAccYearVolume = YearVolume(accYearVolume_x.v1 + itemMonthVolume_y.v1, accYearVolume_x.v2 + itemMonthVolume_y.v2, accYearVolume_x.v3 + itemMonthVolume_y.v3)
        // YearSplit
        val curYearSplit = accYearSplit_x
        var mL0_ = curYearSplit.mL0
        var mL1_ = curYearSplit.mL1
        var mL2_ = curYearSplit.mL2
        if (null == curYearSplit.mL0) {         // 未设置,且发生了用电
            if (0 != itemMonthVolume_y.v1) mL0_ = itemYm_y
        }
        if (null == curYearSplit.mL1) {         // 未设置,且累计用电大于yearThresholdL1
            val totalYearVolume = newAccYearVolume.v1 + newAccYearVolume.v2 + newAccYearVolume.v3
            if (totalYearVolume > yearThresholdL1) mL1_ = itemYm_y
        }
        if (null == curYearSplit.mL2) {         // 未设置,且累计用电大于yearThresholdL1
            val totalYearVolume = newAccYearVolume.v1 + newAccYearVolume.v2 + newAccYearVolume.v3
            if (totalYearVolume > yearThresholdL2) mL2_ = itemYm_y
        }
        // 新 YearSplit
        val newYearSplit = YearSplit(mL0_, mL1_, mL2_)
        
        // 结果对象
        val newAcc = Tuple2(newAccYearVolume,newYearSplit)
        val newObj = Tuple2(item_y, newAcc)
        return newObj
    }
    
    // ------------------------------------------------------------------------
    val result = zeroAccList.foldLeft(zeroObj)(
            (x,y) => mergeCombiner(x, y)
        )
    
    // 结果对象
    val yearVolume = result._2._1
    val yearSplit = result._2._2
    val yearMoney = computeYearMoney(yearVolume.v1 + yearVolume.v2 + yearVolume.v3)
    val yearVolumePriceInfoItem = YearVolumePriceInfoItem(id, yearVolume, yearSplit, yearMoney)
    
    return yearVolumePriceInfoItem
}

// ----------------------------------------------------------------------------
// 3. 从RDD中计算 VolumePriceInfoItem 执行计算
val yearVolumePriceInfoRDD = groupedRDD.map(x => computeYearVolumePriceInfo(x))
//val result = yearVolumePriceInfoRDD.take(40)

