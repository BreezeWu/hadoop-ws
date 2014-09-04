package org.wuhz.spark.sg.volumetrends

/**
 * Created by hadoop on 9/4/14.
 */

// 第一层分类: 数据四大类
case class DataSetRefItem_L1(index:Int, id:String, tablename:String)
// 第二层分类: 阶梯/分时/NotTsNotLadder
case class DataSetRefItem_L2(index:Int, id:String, tablename:String, where:String)

/**
 * SQL语句对象
 */
object objLadderTsSQL {
  // ----------------------------------------------------------------------------
  // 1.第一类, prc_code 是下面内容的是 居民阶梯电价
  /*
20201069	城镇居民生活(1kV以下,居民电采暖12-04，无谷)
20201070	居民生活(1kV以下,居民电采暖，不征收附加无谷)
20101001	乡村居民生活(1kV以下,征收附加)
20101005	乡村居民生活(1kV以下,附加费进目录)
20102001	乡村居民生活(1-10kV,附加费进目录)
20202001	城镇居民生活(1-10kV,不征收附加)
20201004	城镇居民生活(1kV以下,居民电采暖12-04)
20201009	城镇居民电采暖(1kV以下,附加费进目录)
20201010	城镇居民生活(1kV以下,附加费进目录)
20201014	城镇居民生活(1kV以下,不征收附加)
20201021	城镇居民生活(1kV以下,征收附加)
20202016	城镇居民生活(1-10kV,附加费进目录)
20202046	城镇居民生活(1-10kV,征收附加)
20203003	城镇居民生活(35-66kV,征收附加)
20203004	城镇居民生活(35-66kV,不征收附加)
20208001	城镇居民生活(20kV,征收附加)
20102006	乡村居民生活(1-10kV,不征收附加)
*/
  val ladderList = List(
    (20201069,"城镇居民生活(1kV以下,居民电采暖12-04，无谷)"),
    (20201070,"居民生活(1kV以下,居民电采暖，不征收附加无谷)"),
    (20101001,"乡村居民生活(1kV以下,征收附加)"),
    (20101005,"乡村居民生活(1kV以下,附加费进目录)"),
    (20102001,"乡村居民生活(1-10kV,附加费进目录)"),
    (20202001,"城镇居民生活(1-10kV,不征收附加)"),
    (20201004,"城镇居民生活(1kV以下,居民电采暖12-04)"),
    (20201009,"城镇居民电采暖(1kV以下,附加费进目录)"),
    (20201010,"城镇居民生活(1kV以下,附加费进目录)"),
    (20201014,"城镇居民生活(1kV以下,不征收附加)"),
    (20201021,"城镇居民生活(1kV以下,征收附加)"),
    (20202016,"城镇居民生活(1-10kV,附加费进目录)"),
    (20202046,"城镇居民生活(1-10kV,征收附加)"),
    (20203003,"城镇居民生活(35-66kV,征收附加)"),
    (20203004,"城镇居民生活(35-66kV,不征收附加)"),
    (20208001,"城镇居民生活(20kV,征收附加)"),
    (20102006,"乡村居民生活(1-10kV,不征收附加)")
  )

  // 构造是阶梯电价 where 语句
  val whereOfLadder_Prefix = "(prc_code = '"
  val whereOfLadder_Sep = "' or prc_code = '"
  val whereOfLadder_Middle = ladderList.map(x => x._2).reduce((x,y) => (x+ whereOfLadder_Sep + y))
  //val whereMiddle = ladderList.map(x => x._2).foldLeft("prc_code = '")((x,y) => (x+ whereOfLadder_Sep + y)) // 需要减去头部多余内容
  val whereOfLadder_Postfix = "')"

  // ----------------------------------------------------------------------------
  // 构造是 阶梯电价 的where 语句
  val whereOfLadder = whereOfLadder_Prefix + whereOfLadder_Middle + whereOfLadder_Postfix

  // ****************************************************************************
  // 2.第一类之外，
  //	(2.1) ts_flag = '是' 属于分时电价用户
  //	(2.2) ts_flag != '是' 则既不是分时电价，也不是阶梯电价用户
  // ****************************************************************************
  // 从剩下数据中， 找出分时用户
  // 使用 !${whereOfLadder} 都早语句有问题！
  // val selectTs = hiveContext.hql(s"select * from bigdata_ts_or_prcscope_s98 where !${whereOfLadder} and ts_flag = '是'")

  // 构造不是阶梯电价 的where语句
  val whereOfNotLadder_Prefix = "(prc_code != '"
  val whereOfNotLadder_Sep = "' and prc_code != '"
  val whereOfNotLadder_Middle = ladderList.map(x => x._2).reduce((x,y) => (x+ whereOfNotLadder_Sep + y))
  //val whereMiddle = ladderList.map(x => x._2).foldLeft("prc_code = '")((x,y) => (x+ whereOfNotLadder_Sep + y)) // 需要减去头部多余内容
  val whereOfNotLadder_Postfix = "')"
  val whereOfNotLadder = whereOfNotLadder_Prefix + whereOfNotLadder_Middle + whereOfNotLadder_Postfix

  // ----------------------------------------------------------------------------
  // 构造是 分时电价 的where语句
  val whereOfTs = s"${whereOfNotLadder} and ts_flag = '是'"
  // ----------------------------------------------------------------------------
  // 构造是 既不是阶梯电价也不是分时电价 的where语句
  val whereOfNotTsNotLadder = s"${whereOfNotLadder} and ts_flag != '是'"

}

/**
 * 数据集对象
 */
object objDataSet {
  // ****************************************************************************
  // 连接OneBigTable a 与 bigdata_ts_or_prcscope_s98_unique b
  //	三类 where语句
  // ****************************************************************************
  // ----------------------------------------------------------------------------
  // 第一层分类: 数据四大类
  def BuildDataSetL1(datasetID:String):List[DataSetRefItem_L1] = {
    val tablePrefix = "bigdata_user_info_"
    val tablePostfix = "_onebigtable_"

    // 根据 datasetID 构建 DataSetRef_L1
    // 如 "bigdata_user_info_s01_onebigtable_good_m1", "bigdata_user_info_s98_onebigtable_good_m1"
    val DataSetRef_L1 = List(
      DataSetRefItem_L1(0, "GoodM1", s"${tablePrefix}${datasetID}${tablePostfix}good_m1"),
      DataSetRefItem_L1(1, "GoodM2", s"${tablePrefix}${datasetID}${tablePostfix}good_m2"),
      DataSetRefItem_L1(2, "BadF2ExcludeF3", s"${tablePrefix}${datasetID}${tablePostfix}zero_and_null_f2_exclude_f3"),
      DataSetRefItem_L1(3, "BadF3", s"${tablePrefix}${datasetID}${tablePostfix}zero_and_null_f3")
    )

    return DataSetRef_L1
  }

  // ----------------------------------------------------------------------------
  // 第二层分类: 阶梯/分时/NotTsNotLadder
  def BuildDataSetL2(datasetID:String):List[DataSetRefItem_L2] = {
    // 表名
    //val tmpTableName = "bigdata_ts_or_prcscope_s98_unique"
    // 表名: 根据 datasetID 构建 DataSetRef_L2中的表名
    val tmpTableName = s"bigdata_ts_or_prcscope_${datasetID}_unique"

    val DataSetRef_L2 = List(
      DataSetRefItem_L2(0, "Ladder", tmpTableName, objLadderTsSQL.whereOfLadder),
      DataSetRefItem_L2(1, "Ts", tmpTableName, objLadderTsSQL.whereOfTs),
      DataSetRefItem_L2(2, "NotTsNotLadder", tmpTableName, objLadderTsSQL.whereOfNotTsNotLadder)
    )

    return DataSetRef_L2
  }
}
