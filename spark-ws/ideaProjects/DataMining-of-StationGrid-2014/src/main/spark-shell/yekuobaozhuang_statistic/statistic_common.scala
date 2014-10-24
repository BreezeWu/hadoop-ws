import org.apache.spark.rdd.RDD

/**
 * Created by HongZe.Wu on 10/21/14.
 * 基于 specialRecordRdd_MAXDL2Percent 这个RDD对象进行统计分析
 */

// ----------------------------------------------------------------------------
// 基于 specialRecordRdd_MAXDL2Percent 这个RDD对象进行统计分析

// 最大电力达到合同容量的百分比
val percentList = List(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 120, 150, 200, 400, 1000)
// 运行时长+百分比的用户数量百分比: 第一层是运行时长,第二层是百分比
//val userCountList:List[List[Int]]

// 按照运行时长进行分组
// 1.混合统计: 运行时长小于等于给定时长的所有用户
val APPROACH_CHAOS = 1
// 2.区间统计: 运行时长在等于给定时长区间的所有用户(左开右闭区间,第一个代表所有,所以第一个值要小于0, 这里取-1)
val APPROACH_INTERVAL = 2
// 3.分段统计: 找到运行时长大于给定时长的所有用户,但只取其小于给定时长的数据
val APPROACH_SEGMENT = 3

// ----------------------------------------------------------------------------
// 将统计结果集变换为百分比
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
