/**
 * Created by HongZe.Wu on 10/29/14.
 *
 * 一, 基于 recordRdd_by_CONS_ID_merged, 生成用于统计分析的 specialRecordRdd_by_CONS_ID_MAXDL2Percent
 */

// ----------------------------------------------------------------------------
// 一, 生成用于统计分析的 recordRdd_forStatistic
// 按照 (mp_id, run_date, ym_list, contract_cap_list, maxdl_list, elec_type_code) 格式
//  获得用于统计分析的数据集, 当然,这里的第一个字段是CONS_ID
val recordRdd_forStatistic_by_CONS_ID = recordRdd_by_CONS_ID_merged.map( x => {
  val typical = x._1
  val ym_list = x._2
  val mergedListList = x._3

  // 获取指定字段信息
  val cons_id = typical(colsIndexOf_raw("CONS_ID"))
  val run_date = typical(colsIndexOf_raw("RUN_DATE"))

  val contract_cap_list = mergedListList.
    filter(z => z._1.contentEquals("CONTRACT_CAP"))(0)._2
  val maxdl_list = mergedListList.
    filter(z => z._1.contentEquals("MAXDL"))(0)._2

  // 其他信息
  val elec_type_code = typical(colsIndexOf_raw("ELEC_TYPE_CODE"))

  // 结果
  (cons_id, run_date, ym_list, contract_cap_list, maxdl_list, elec_type_code)
})
// ----------------------------------------------------------------------------
// 在合并后的基础上,选择特定的用户
val specialRecordRdd_by_CONS_ID = recordRdd_forStatistic_by_CONS_ID.filter(x => {  // 剔除根本没有最大电力的用户
val maxdl_list = x._5

  // 最大电力所有都是空: "" => 最大电力是负值
  val allIsNull = maxdl_list.forall(x => x < 0)// x.contentEquals(""))
  // 保留的内容
  !allIsNull
}).filter(x => {  // 剔除合同容量发生变化的用户
val contract_cap_list = x._4

  // 首次合同容量
  val contract_cap_first = contract_cap_list(0)

  // 所有月份的合同容量都是第一个月的容量
  val allIsOneFlag = contract_cap_list.forall(x => x == contract_cap_first)
  // 保留的内容
  allIsOneFlag
})

// ----------------------------------------------------------------------------
// 对最大电力进行处理: 转换为合同容量的百分比;若当月没有最大电力,将百分比设置为-100
val specialRecordRdd_by_CONS_ID_MAXDL2Percent = specialRecordRdd_by_CONS_ID.map(x => {
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
      if(x < 0 ) -1.0*100 // 若当月没有最大电力,将百分比设置为-1
      else (x.toFloat/contract_cap)*100
    })

  // 联合 "第几月"和最大电力百分比
  val zip = new_ym_list.zip(new_maxdl_list)

  // 结果
  //(mp_id, run_date, new_ym_list, contract_cap_list, new_maxdl_list)
  (mp_id, run_date, contract_cap, zip, elec_type_code)
}).cache()

// ----------------------------------------------------------------------------
val specialRecordRdd = specialRecordRdd_by_CONS_ID
val specialRecordRdd_MAXDL2Percent = specialRecordRdd_by_CONS_ID_MAXDL2Percent
