/**
 * Created by HongZe.Wu on 10/27/14.
 */

// ----------------------------------------------------------------------------
// 方法三所对应的数据集
// 按运行时长分组
val rddList_SEGMENT_raw = minRunnedMonthsList.map(x => {
  val minRunnedMonths = x
  val curRdd_L01_raw = orgRdd.filter(z => {
    // 运行时长过滤
    val zip = z._4

    // zip中的最后一个,其"第几月份"信息
    val maxMonthNo = zip(zip.size - 1)._1 // zip中的最后一个元素中的"第几个月份"信息
    val filterFlag = if (maxMonthNo >= minRunnedMonths) true else false

    // 保留的内容
    filterFlag
  })

  curRdd_L01_raw
})
// 按运行时长分组,但只获取部分值
val rddList_SEGMENT = minRunnedMonthsList.map(x => {
  val minRunnedMonths = x
  val curRdd_L01_raw = orgRdd.filter(z => {
    // 运行时长过滤
    val zip = z._4

    // zip中的最后一个,其"第几月份"信息
    val maxMonthNo = zip(zip.size - 1)._1 // zip中的最后一个元素中的"第几个月份"信息
    val filterFlag = if (maxMonthNo >= minRunnedMonths) true else false

    // 保留的内容
    filterFlag
  })

  val curRdd_L01 = curRdd_L01_raw.map(z => { // 前面找到了运行时长大于给定时长的所有用户(maxMonthNo >= minRunnedMonths),这里只取其小于给定时长的数据
  val zip = z._4
    val newZip = zip.slice(0, minRunnedMonths)
    (z._1,z._2,z._3,newZip, z._5)
  })

  curRdd_L01
})

// 按运行时长分组,并找到第一个月就有正确最大电力的用户
// 前面找到了运行时长大于给定时长的所有用户(maxMonthNo >= minRunnedMonths),这里再找第一个月就有有效最大电力的用户
val rddList_SEGMENT_MAXDL_firstIsRight = rddList_SEGMENT_raw.map(x => {
  val curRdd = x

  val filterRdd = curRdd.filter(z => {
    val percentThreshold  = 0 // 大于等于0表示最大电力值有效

    val zip = z._4
    // 第一个月的最大电力有效
    val firstMAXDL = zip(0)._2
    val filterFlag = if(firstMAXDL >= percentThreshold) true else false
    // 保留的内容
    filterFlag
  })

  filterRdd
})

// 按运行时长分组,并找到所有月的最大电力都有效
// 前面找到了运行时长大于给定时长的所有用户(maxMonthNo >= minRunnedMonths),这里再找所有月的最大电力都有效
val rddList_SEGMENT_MAXDL_allIsRight = rddList_SEGMENT_raw.map(x => {
  val curRdd = x

  val filterRdd = curRdd.filter(z => {
    val percentThreshold  = 0 // 大于等于0表示最大电力值有效

    val zip = z._4
    // 所有月的最大电力都有效
    val listOf_MAXDL = zip.map(x => x._2)
    val filterFlag = listOf_MAXDL.forall(one => {
      if(one >= percentThreshold) true else false
    })
    // 保留的内容
    filterFlag
  })

  filterRdd
})

def getAndPrintFirst(rddNo:Int) = {
  val rddOf_raw = rddList_SEGMENT_raw(rddNo)
  val rddOf_segment = rddList_SEGMENT(rddNo)
  val countOf_raw = rddOf_raw.count
  val countOf_segment = rddOf_segment.count
  val first_raw = rddOf_raw.first
  val first_segment = rddOf_segment.first
  //val first_zip = rddList_SEGMENT_raw(rddNo).first

  println("原始数据: \n" +
    "\t 总数量: " + countOf_raw +
    "\n 第一个实例:" + first_raw)
  println("转换后: \n" +
    "\t 总数量: " + countOf_segment +
    "\n 第一个实例:" + first_segment)
  //(first_raw, first)

  /*  rddList_SEGMENT_raw(0).first
    rddList_SEGMENT(0).first
    val zip = rddList_SEGMENT_raw(0).first

    rddList_SEGMENT_raw(1).first
    rddList_SEGMENT(1).first
    val zip = rddList_SEGMENT_raw(1).first*/
}

getAndPrintFirst(0)
getAndPrintFirst(1)
getAndPrintFirst(2)
getAndPrintFirst(3)
getAndPrintFirst(4)
getAndPrintFirst(5)

// 第一个月的最大电力有效
rddList_SEGMENT_hasMAXDL_atFirst


rddList_SEGMENT_MAXDL_firstIsRight.map(x => x.count).foreach(println)
rddList_SEGMENT_MAXDL_allIsRight.map(x => x.count).foreach(println)


