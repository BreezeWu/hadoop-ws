/**
 * Created by Hongze.Wu on 10/24/14.
 * 基于 specialRecordRdd_MAXDL2Percent 这个RDD对象进行统计分析
 * 回答这个问题: 用户的最大电力是否已经达到合同容量的某个百分比
 *
 * 方法三, 分段统计: 找到运行时长大于给定时长的所有用户,但只取其小于给定时长的数据
 */

import org.apache.spark.rdd.RDD
// 按照运行时长进行分组
// 1.混合统计: 运行时长小于等于给定时长的所有用户
// 2.区间统计: 运行时长在等于给定时长区间的所有用户(左开右闭区间,第一个代表所有,所以第一个值要小于0, 这里取-1)
// 3.分段统计: 找到运行时长大于给定时长的所有用户,但只取其小于给定时长的数据
val minRunnedMonthsList = Range(0, 90+1, 3).toList // 按三个月递增

// ----------------------------------------------------------------------------
// 根据 (1)运行时长与(2)最大电力达到的百分比 进行统计
// 方法三: 分段统计
def ComputeUserCountListList_SEGMENT(specialRecordRdd:RDD[(String, String, Float, scala.collection.immutable.IndexedSeq[(Int, Double)], String)],
                                     minRunnedMonthsList:List[Int],
                                     percentList:List[Int]): List[List[Long]] = {
  // ----------------------------------
  // 第一层是运行时长最小值
  minRunnedMonthsList.map(x => {

    val minRunnedMonths = x
    val curRdd_L01 = specialRecordRdd.filter(z => { // 运行时长过滤
      val zip = z._4

      // zip中的最后一个,其"第几月份"信息
      val maxMonthNo = zip(zip.size - 1)._1 // zip中的最后一个元素中的"第几个月份"信息
      val filterFlag = if (maxMonthNo >= minRunnedMonths) true else false

      // 保留的内容
      filterFlag
    }).filter(x => {  // 所有月的最大电力都有效
      val percentThreshold  = 0 // 大于等于0表示最大电力值有效

      val zip = x._4
      // 所有月的最大电力都有效
      val listOf_MAXDL = zip.map(x => x._2)
      val filterFlag = listOf_MAXDL.forall(one => {
        if(one >= percentThreshold) true else false
      })
      // 保留的内容
      filterFlag
    }).map(z => { // 前面找到了运行时长大于给定时长的所有用户(maxMonthNo >= minRunnedMonths),这里只取其小于给定时长的数据
      val zip = z._4
      val newZip = zip.slice(0, minRunnedMonths)
      (z._1,z._2,z._3,newZip, z._5)
    })

    // ----------------------------------
    // 第二层是百分比
    val userCountList = percentList.map(y => {
      val percentThreshold = y

      // 数据集筛选
      val curRdd_L02 = curRdd_L01.filter(z => {  // 百分比检测过滤
        val zip = z._4
        // 最大电力list
        val maxdl_list = zip.map(x => x._2)
        // 最大电力有达到这个百分比吗?
        val filterFlag = {
            val bigThanThisPercentList = maxdl_list.filter(x => x >= percentThreshold)
            val flag = if (bigThanThisPercentList.size > 0) true else false
            flag
          }

        // 保留的内容
        filterFlag
      })

      // 数据集数量
      val curCount = curRdd_L02.count
      curCount
    })

    // 返回值
    userCountList
  })
}
