import java.io.StringReader

import au.com.bytecode.opencsv.CSVReader

/**
 * Created by HongZe.Wu on 10/21/14.
 */

val datasetId = 2012 // 2012

// 文件路径
val inputFile_org = "file:///home/hadoop/dm-data/yekuobaozhuang-maxP/org"
val inputFile_org_windows = inputFile_org + s"/yekuobaozhuang-${datasetId}-windows.csv" //"/yekuobaozhuang-2010-windows.csv"
val inputFile_org_linux = inputFile_org + s"/yekuobaozhuang-${datasetId}-linux.csv"//"/yekuobaozhuang-2010-linux.csv"
val inputFile_percent = "file:///home/hadoop/dm-data/yekuobaozhuang-maxP/convert2percent"
val inputFile_org_sample = "file:///home/hadoop/dm-data/yekuobaozhuang-maxP/org_sample"
val inputFile_org_sample_01 = inputFile_org_sample + "_0.01"

// ----------------------------------------------------------------------------
// 读取原始数据(读单个文件)
val inputFile = inputFile_org_linux
val linesRdd = sc.textFile(inputFile) // 读某一个文件,一行一条记录
// 按照csv格式解析: 这是每行生成一个CSVReader的方法
val csvRdd_javaList = linesRdd.map(x => {
  val reader = new CSVReader(new StringReader(x))
  val csvLine = reader.readAll()
  csvLine
})
// 将Java List转换为Scala List: x => x.toList
import scala.collection.JavaConversions._
val csvRdd = csvRdd_javaList.map(x => {
  val scalaList = x.toList
  scalaList(0)  // 每行一个CSVReader,所以只需要取第一行数据
})
// .map(x => x(0))

// ----------------------------------------------------------------------------
// 定义列及其位置
val cols = List("ROW_ID", "MP_ID", "MP_NO", "MP_NAME", "MP_ADDR", "VOLT_CODE", "APP_DATE", "RUN_DATE", "ORG_NO", "MR_SECT_NO", "LINE_ID", "TG_ID", "STATUS_CODE", "CONS_ID", "MP_CAP", "CONS_NO", "CONS_NAME", "ELEC_ADDR", "TRADE_CODE", "ELEC_TYPE_CODE", "CONTRACT_CAP", "RUN_CAP", "BUILD_DATE", "PS_DATE", "CANCEL_DATE", "DUE_DATE", "YM", "SUMPQ", "MAXDL").map(x => x.toUpperCase)
def colsIndexOf(id:String):Int = {
  return cols.indexOf(id.toUpperCase)
}

// 抽样
//val sampleRdd = linesRdd.sample(false, 0.01)
//sampleRdd.saveAsTextFile(inputFile_org_sample_01)

// ----------------------------------------------------------------------------
// 获取特定列: 当前状态(01/02/03/04: 正常/新装/变更/销户)正常用户的某些列
val specialCsvRdd = csvRdd.filter(x => {  // 剔除首行
  val v = x(colsIndexOf("MP_ID"));
  !v.contentEquals("MP_ID")
}).filter(x => {  // 状态正常
  val v = x(colsIndexOf("STATUS_CODE"));
  v.contentEquals("01") || v.contentEquals("02") || v.contentEquals("03")
//}).filter(x => { // 最大电力不为空: 某些用户的前几个月没有最大电力数据而被剔除
//  val v = x(colsIndexOf("MAXDL"));
//  !v.contentEquals("")
}).map(x =>
  Array(
    x(colsIndexOf("MP_ID")), x(colsIndexOf("RUN_DATE")),
    x(colsIndexOf("YM")), x(colsIndexOf("CONTRACT_CAP")), x(colsIndexOf("MAXDL")),
    x(colsIndexOf("ELEC_TYPE_CODE")))
)

// ----------------------------------------------------------------------------
// 将用户的多条记录转换为一行
val kvRdd = specialCsvRdd.groupBy(x => x(0))
val recordRdd = kvRdd.map( x => {
  val key = x._1
  val value = x._2

  // 转换为list,并根据YM排序
  val list = value.toList.sortBy(x => x(2))  // 根据"YM"从小向大排序: YM是第三个字段,索引为2
  // 其他一致的信息, 根据第一行获取
  val first = list(0)
  val mp_id = first(0)    // 与key是一致的
  val run_date = first(1)

  // 然后,需要将valueList的内容转换为一行
  val size = list.size  // 记录数量
  val ym_list = list.map(x => x(2))
  val contract_cap_list = list.map(x => x(3))
  val maxdl_list = list.map(x => x(4))

  // 其他信息
  val elec_type_code = first(5)

  // 结果
  (mp_id, run_date, ym_list, contract_cap_list, maxdl_list, elec_type_code)
})

// ----------------------------------------------------------------------------
// 在合并后的基础上,选择特定的用户
val specialRecordRdd = recordRdd.filter(x => {  // 剔除根本没有最大电力的用户
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
// 查看样本
val f1 = specialRecordRdd.first
f1._1
f1._2
f1._3
f1._4
f1._5
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
val f2 = specialRecordRdd_MAXDL2Percent.first
f2._1
f2._2
f2._3
f2._4
f2._5