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

// ----------------------------------------------------------------------------
// 按照运行时长进行分组
val curApproach = APPROACH_CHAOS
val orgRdd = specialRecordRdd_MAXDL2Percent
val orgRddList = specialRecordRddList_MAXDL2Percent_of_ELEC_TYPE_CODE
// ------------------------------------
def executerOf_CHAOS(orgRdd:RDD[(String, String, Float, scala.collection.immutable.IndexedSeq[(Int, Double)], String)],
                     orgRddList:List[RDD[(String, String, Float, scala.collection.immutable.IndexedSeq[(Int, Double)], String)]],
                     curApproach:Int) {
  // 执行统计
  val userCountListList = ComputeUserCountListList_CHAOS(orgRdd, minRunnedMonthsList, percentList)
  val userCountListList_ELEC_TYPE_CODE = orgRddList.map(oneRdd => {
    ComputeUserCountListList_CHAOS(oneRdd, minRunnedMonthsList, percentList)
  })

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
}
