import org.apache.spark.rdd.RDD

/**
 * Created by Hongze.Wu on 10/24/14.
 */

// 先执行 loadDataFromOneFile.scala, 得到 specialRecordRdd_MAXDL2Percent

// ------------------------------------------------------------------------
// 数据分组
// 用电类别: ELEC_TYPE_CODE
// 用电类别列表
val elec_type_code_list = specialRecordRdd_MAXDL2Percent.map(x => x._5).distinct().collect().sorted.toList
// 其他分组: 按照用电类别 ELEC_TYPE_CODE
val specialRecordRddList_MAXDL2Percent_of_ELEC_TYPE_CODE = elec_type_code_list.map(x => {
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

  // 返回值
  this_Rdd
}).toList

// ------------------------------------
def executerOf_statistic(orgRdd:RDD[(String, String, Float, scala.collection.immutable.IndexedSeq[(Int, Double)], String)],
                     orgRddList:List[RDD[(String, String, Float, scala.collection.immutable.IndexedSeq[(Int, Double)], String)]],
                     curApproach:Int) {
  // 执行统计
  val (userCountListList, userCountListList_ELEC_TYPE_CODE) = curApproach match {
    case APPROACH_CHAOS => {
      (ComputeUserCountListList_CHAOS(orgRdd, maxRunnedMonthsList, percentList),
        orgRddList.map(oneRdd => {
          ComputeUserCountListList_CHAOS(oneRdd, maxRunnedMonthsList, percentList)
        })
        )
    }
    case APPROACH_INTERVAL => {
      (ComputeUserCountListList_INTERVAL(orgRdd, runnedMonthsIntervalList, percentList),
        orgRddList.map(oneRdd => {
          ComputeUserCountListList_INTERVAL(oneRdd, runnedMonthsIntervalList, percentList)
        }))
    }
    case APPROACH_SEGMENT => {
      (ComputeUserCountListList_SEGMENT(orgRdd, minRunnedMonthsList, percentList),
        orgRddList.map(oneRdd => {
          ComputeUserCountListList_SEGMENT(oneRdd, minRunnedMonthsList, percentList)
        }))
    }
  }

  // ------------------------------------
  // 转换为百分比
  val userCountListList_percent = convertCountListList2Percent(userCountListList)
  val userCountListList_percent_ELEC_TYPE_CODE = userCountListList_ELEC_TYPE_CODE.map(x => convertCountListList2Percent(x))
  // ------------------------------------
  // 将结果打印在标准输出
  // 原始值
  printUserCountListList(userCountListList, s"数据集_${datasetId}_所有用电类别_原始值_${curApproach}", curApproach)
  printSubsetUserCountListList(elec_type_code_list, userCountListList_ELEC_TYPE_CODE, curApproach)
  // 百分比
  printUserCountListList(userCountListList_percent, s"数据集_${datasetId}_所有用电类别_百分比_${curApproach}", curApproach)
  printSubsetUserCountListList(elec_type_code_list, userCountListList_percent_ELEC_TYPE_CODE, curApproach)

  // ------------------------------------
  // 将结果输出到csv文件
  val filepath = "/home/hadoop/dm-data/yekuobaozhuang-maxP/output_statistic"
  // 原始值
  convertUserCountListList2CSV(userCountListList, s"数据集_${datasetId}_所有用电类别_原始值_${curApproach}", filepath, curApproach)
  convertSubsetUserCountListList(filepath, true, false, elec_type_code_list, userCountListList_ELEC_TYPE_CODE, curApproach)
  // 百分比
  convertUserCountListList2CSV(userCountListList_percent, s"数据集_${datasetId}_所有用电类别_百分比_${curApproach}", filepath, curApproach)
  convertSubsetUserCountListList(filepath, true, true,elec_type_code_list, userCountListList_percent_ELEC_TYPE_CODE, curApproach)

  // 返回值
  (userCountListList, userCountListList_ELEC_TYPE_CODE)
}

// ----------------------------------------------------------------------------
// 按照运行时长进行分组
val orgRdd = specialRecordRdd_MAXDL2Percent
val orgRddList = specialRecordRddList_MAXDL2Percent_of_ELEC_TYPE_CODE

// 1.混合统计: 运行时长小于等于给定时长的所有用户
// 2.区间统计: 运行时长在等于给定时长区间的所有用户(左开右闭区间,第一个代表所有,所以第一个值要小于0, 这里取-1)
// 3.分段统计: 找到运行时长大于给定时长的所有用户,但只取其小于给定时长的数据
val curApproach = APPROACH_SEGMENT  // APPROACH_CHAOS // APPROACH_INTERVAL // APPROACH_SEGMENT
// ----------------------------------------------------------------------------
// 按照运行时长进行分组

(userCountListList, userCountListList_ELEC_TYPE_CODE) =
  executerOf_statistic(orgRdd, orgRddList, APPROACH_CHAOS)

// ----------------------------------------------------------------------------
// 查找特定的样本
def findInstance(id:String) = {
  val rawInstance = specialCsvRdd.filter(x => x(0).contentEquals(id)).collect()
  val orgInstance = orgRdd.filter(x => x._1.contentEquals(id))

  // 直接打印: 中间可能掺杂spark的信息
  strRaw = rawInstance.foreach(x => {x.foreach(y => print(y + "\t")); print("\n")})
  orgInstance.collect()
}

findInstance("800573101")


