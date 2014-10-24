import org.apache.spark.rdd.RDD

/**
 * Created by HongZe.Wu on 10/21/14.
 * 基于 specialRecordRdd_MAXDL2Percent 这个RDD对象进行统计分析
 */

// ----------------------------------------------------------------------------
// 基于 specialRecordRdd_MAXDL2Percent 这个RDD对象进行统计分析

// 按照运行时长进行分组
// 累积统计
//val runnedMonthsList = List(0,3,6,9,12,15,18,21,24,27,30,33,36,39,42,45,48,51,54,57,60) // 累积划分
// 区间统计: 左开右闭区间,第一个代表所有,所以第一个值要小于0, 这里取-1
val interval = 3  // 三个月一个区间
val runnedMonthsIntervalList = List(-1/*0*/,3,6,9,12,15,18,21,24,27,30,33,36,39,42,45,48,51,54,57,60).
    map(x => (x, x+interval)) .:+ (63, 900) //(63, Int.MaxValue)

// 最大电力达到合同容量的百分比
val percentList = List(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 120, 150, 200, 400, 1000)
// 运行时长+百分比的用户数量百分比: 第一层是运行时长,第二层是百分比
//val userCountList:List[List[Int]]

// 根据 (1)运行时长与(2)最大电力达到的百分比 进行统计
def ComputeUserCountListList(specialRecordRdd:RDD[(String, String, Float, scala.collection.immutable.IndexedSeq[(Int, Double)], String)]): List[List[Long]] = {
  // 累积统计
//  runnedMonthsList.map(x => {
//    // 第一层是运行时长
//    val runnedMonths = x
  // 区间统计
  runnedMonthsIntervalList.map(x => {
    // 第一层是运行时长区间
    val runnedMonthsInterval = x

    val userCountList = percentList.map(y => {
      // 第二层是百分比
      val percentThreshold = y

      // 数据集筛选
      val curRdd = specialRecordRdd.filter(z => {
        // 运行时长检测
        //      val mp_id = z._1
        //      val run_date = z._2
        //      val contract_cap = z._3
        val zip = z._4
        //      val elec_type_code = z._5

        // 累积统计
//        // zip中的最后一个,其"第几月"小于当前runnedMonths吗?
//        val size = zip.size
//        val flag = if (zip(size - 1)._1 <= runnedMonths) true
//        else false

        // 区间统计
        // zip中的最后一个,其"第几月"在当前runnedMonthsInterval区间吗?
        val size = zip.size
        val flag = if (zip(size - 1)._1 > runnedMonthsInterval._1 && zip(size - 1)._1 <= runnedMonthsInterval._2) true
        else false

        // 保留的内容
        flag
      }).filter(z => {
        // 百分比检测
        //      val mp_id = z._1
        //      val run_date = z._2
        //      val contract_cap = z._3
        val zip = z._4
        //      val elec_type_code = z._5

        // 最大电力有达到这个百分比吗?
        val maxdl_list = zip.map(x => x._2)
        val bigThanThisPercentList = maxdl_list.filter(x => x >= percentThreshold)
        val flag = if (bigThanThisPercentList.size > 0) true
        else false

        // 保留的内容
        flag
      })

      // 数据集数量
      val curCount = curRdd.count
      curCount
    })

    // 返回值
    userCountList
  })
}

// ------------------------------------------------------------------------
// 执行统计
/*val x = runnedMonthsList(0)
val y = percentList(0)
val z = specialRecordRdd_MAXDL2Percent.first*/
val userCountListList = ComputeUserCountListList(specialRecordRdd_MAXDL2Percent)

// ------------------------------------------------------------------------
// 数据分组
// ELEC_TYPE_CODE
// 其他分组: 按照用电类别 ELEC_TYPE_CODE
val elec_type_code_list = specialRecordRdd_MAXDL2Percent.map(x => x._5).distinct().collect().sorted.toList
val userCountListList_ELEC_TYPE_CODE = elec_type_code_list.map(x => {
  val this_elec_type_code = x

  val this_Rdd = specialRecordRdd_MAXDL2Percent.filter(z => {
    //      val mp_id = z._1
    //      val run_date = z._2
    //      val contract_cap = z._3
    //      val zip = z._4
    val elec_type_code = z._5

    // 是这个用电类别的吗?
    val flag = if (elec_type_code == this_elec_type_code) true else false

    // 保留的内容
    flag
  })

  // 数据集数量
  val this_userCountListList = ComputeUserCountListList(this_Rdd)
  // 返回值
  this_userCountListList
}).toList

//val longInstance_ELEC_TYPE_CODE_userCountListList = userCountListList_ELEC_TYPE_CODE.map(x=>x.map(y => y.map(z=>z.asInstanceOf[Long])))

// ----------------------------------------------------------------------------
// 运行结果集
userCountListList
userCountListList_ELEC_TYPE_CODE
// ----------------------------------------------------------------------------
// 将上面结果集变换为百分比
def convertCountListList2Percent(countListList:List[List[Long]]):List[List[Any]] = {
  val countListList_percent = countListList.map(countList => {
    val (max, otherList) = countList match {
      case (x::other) => (x, other)
    }

    // 百分比:不保留百分比小数: (x*100/max)
    // 百分比:保留2位百分比小数: (x*100*100/max).toDouble/100
    // 非百分比,两位小数点:  (x*100/max).toFloat/(100)
    // 非百分比,四位小数点:  (x*100*100/max).toFloat/(100*100)
    val otherList2Percent = otherList.map(x => {
      //if (0 != max) (x*100/max)+"%" else 0+"%"
      if (0 != max) (x*100/max).toFloat/(100) else 0F
    })
    max::otherList2Percent
  })

  // 结果
  countListList_percent
}

val userCountListList_percent = convertCountListList2Percent(userCountListList)
val userCountListList_ELEC_TYPE_CODE_percent = userCountListList_ELEC_TYPE_CODE.map(x => convertCountListList2Percent(x))
// ----------------------------------------------------------------------------
