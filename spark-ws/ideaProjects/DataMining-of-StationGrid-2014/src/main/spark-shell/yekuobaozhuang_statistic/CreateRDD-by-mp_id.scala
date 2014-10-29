/**
 * Created by HongZe.Wu on 10/29/14.
 *
 * 在运行 loadDataFromOneFile.scala 之后, 基于csvRdd, 按照MP_ID合并
 */

// ----------------------------------------------------------------------------
// 获取特定列: 当前状态(01/02/03/04: 正常/新装/变更/销户)正常用户的某些列
val specialCsvRdd = csvRdd.filter(x => {  // 剔除首行
val v = x(colsIndexOf_raw("MP_ID"));
  !v.contentEquals("MP_ID")
}).filter(x => {  // 状态正常
val v = x(colsIndexOf_raw("STATUS_CODE"));
  v.contentEquals("01") || v.contentEquals("02") || v.contentEquals("03")
  //}).filter(x => { // 最大电力不为空: 某些用户的前几个月没有最大电力数据而被剔除
  //  val v = x(colsIndexOf_raw("MAXDL"));
  //  !v.contentEquals("")
}).map(x =>
  Array(
    x(colsIndexOf_raw("MP_ID")), x(colsIndexOf_raw("RUN_DATE")),
    x(colsIndexOf_raw("YM")), x(colsIndexOf_raw("CONTRACT_CAP")), x(colsIndexOf_raw("MAXDL")),
    x(colsIndexOf_raw("ELEC_TYPE_CODE")))
  )

val g_columns_of_special = List("MP_ID", "RUN_DATE", "YM", "CONTRACT_CAP", "MAXDL", "ELEC_TYPE_CODE").map(x => x.toUpperCase)
def colsIndexOf_special(id:String):Int = {
  return g_columns_of_special.indexOf(id.toUpperCase)
}
// ----------------------------------------------------------------------------
// 将用户的多条记录转换为一行
val kvRdd = specialCsvRdd.groupBy(x => x(colsIndexOf_special("MP_ID")))
val recordRdd_forStatistic = kvRdd.map( x => {
  val key = x._1
  val value = x._2

  // 转换为list,并根据YM排序
  val list = value.toList.sortBy(x => x(colsIndexOf_special("YM")))
  // 其他一致的信息, 根据第一行获取
  val typical = list(0)
  val mp_id = typical(colsIndexOf_special("MP_ID"))    // 与key是一致的
  val run_date = typical(colsIndexOf_special("RUN_DATE"))

  // 然后,需要将valueList的内容转换为一行
  val size = list.size  // 记录数量
  val ym_list = list.map(x => x(colsIndexOf_special("YM")))
  val contract_cap_list = list.map(x => x(colsIndexOf_special("CONTRACT_CAP")))
  val maxdl_list = list.map(x => x(colsIndexOf_special("MAXDL")))

  // 其他信息
  val elec_type_code = typical(colsIndexOf_special("ELEC_TYPE_CODE"))

  // 结果
  (mp_id, run_date, ym_list, contract_cap_list, maxdl_list, elec_type_code)
})

// ============================================================================
// ----------------------------------------------------------------------------
// 在合并后的基础上,选择特定的用户
val specialRecordRdd = recordRdd_forStatistic.filter(x => {  // 剔除根本没有最大电力的用户
val maxdl_list = x._5

  // 最大电力所有都是空: ""
  val allIsNull = maxdl_list.forall(x => x.contentEquals(""))
  // 保留的内容
  !allIsNull
}).filter(x => {  // 剔除合同容量发生变化的用户
val contract_cap_list = x._4

  // 首次合同容量
  val contract_cap_first = contract_cap_list(0)

  // 所有月份的合同容量都是第一个月的容量
  val allIsOneFlag = contract_cap_list.forall(x => x.contentEquals(contract_cap_first))
  // 保留的内容
  allIsOneFlag
})

// ----------------------------------------------------------------------------
// 对最大电力进行处理: 转换为合同容量的百分比;若当月没有最大电力,将百分比设置为-100
val specialRecordRdd_MAXDL2Percent = specialRecordRdd.map(x => {
  val mp_id = x._1
  val run_date = x._2
  val ym_list = x._3
  val contract_cap_list = x._4
  val maxdl_list = x._5
  // 其他信息
  val elec_type_code = x._6

  // 将 ym_list 转换为第几月,从1开始
  val new_ym_list = ym_list.indices.map(x => x+1)

  val contract_cap = contract_cap_list(0).toFloat // 所有合同容量都相同
  val new_maxdl_list = maxdl_list.map(x => {
      if(x.contentEquals("")) -1.0*100 // 若当月没有最大电力,将百分比设置为-1
      else (x.toFloat/contract_cap)*100
    })

  // 联合 "第几月"和最大电力百分比
  val zip = new_ym_list.zip(new_maxdl_list)

  // 结果
  //(mp_id, run_date, new_ym_list, contract_cap_list, new_maxdl_list)
  (mp_id, run_date, contract_cap, zip, elec_type_code)
}).cache()

// ----------------------------------------------------------------------------
// 查看样本
/*val f1 = specialRecordRdd.first
f1._1
f1._2
f1._3
f1._4
f1._5

val f2 = specialRecordRdd_MAXDL2Percent.first
f2._1
f2._2
f2._3
f2._4
f2._5*/

