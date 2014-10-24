/**
 * Created by Hongze.Wu on 10/24/14.
 * 基于 specialRecordRdd_MAXDL2Percent 这个RDD对象进行统计分析
 * 回答这个问题: 用户的最大电力是否已经达到合同容量的某个百分比
 *
 * 方法二, 区间统计: 运行时长在等于给定时长区间的所有用户
 * (左开右闭区间,第一个代表所有,所以第一个值要小于0, 这里取-1)
 */

import org.apache.spark.rdd.RDD
// 按照运行时长进行分组
// 1.混合统计: 运行时长小于等于给定时长的所有用户
// 2.区间统计: 运行时长在等于给定时长区间的所有用户(左开右闭区间,第一个代表所有,所以第一个值要小于0, 这里取-1)
val interval = 3  // 三个月一个区间
val runnedMonthsIntervalList = List(-1/*0*/,3,6,9,12,15,18,21,24,27,30,33,36,39,42,45,48,51,54,57,60).
    map(x => (x, x+interval)) .:+ (63, 900) //(63, Int.MaxValue)
// 3.分段统计: 找到运行时长大于给定时长的所有用户,但只取其小于给定时长的数据

// 根据 (1)运行时长与(2)最大电力达到的百分比 进行统计
def ComputeUserCountListList_INTERVAL(specialRecordRdd:RDD[(String, String, Float, scala.collection.immutable.IndexedSeq[(Int, Double)], String)],
                                      runnedMonthsIntervalList:List[(Int, Int)],
                                      percentList:List[Int]): List[List[Long]] = {
  runnedMonthsIntervalList.map(x => {
    // 第一层是运行时长区间
    val runnedMonthsInterval = x

    val userCountList = percentList.map(y => {
      // 第二层是百分比
      val percentThreshold = y

      // 数据集筛选
      val curRdd = specialRecordRdd.filter(z => { // 运行时长过滤
        val zip = z._4

        // zip中的最后一个,其"第几月份"信息在当前runnedMonthsInterval区间吗?
        val maxMonthNo = zip(zip.size - 1)._1 // zip中的最后一个元素中的"第几个月份"信息
        val filterFlag = if (maxMonthNo > runnedMonthsInterval._1 &&
            maxMonthNo <= runnedMonthsInterval._2) true else false

        // 保留的内容
        filterFlag
      }).filter(z => {  // 百分比检测过滤
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
      val curCount = curRdd.count
      curCount
    })

    // 返回值
    userCountList
  })
}
