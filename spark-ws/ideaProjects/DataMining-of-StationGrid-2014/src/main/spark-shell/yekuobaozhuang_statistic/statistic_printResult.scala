/**
 * Created by HongZe.Wu on 10/22/14.
 * 输出统计结果
 */

// ----------------------------------------------------------------------------
// 运行结果集
userCountListList
ELEC_TYPE_CODE_userCountListList
// 将上面结果集变换为百分比
// ----------------------------------------------------------------------------
// 打印各个运行结果集的函数
val splitLineL1 = "----------------------------------------------------------------------------"
val splitLineL2 = "-------------------------------------"
def printLevelInfo(s:String) = {
  println(s"===${s}===")
}

// 打印L1L2
def printUserCountListList(L1L2:List[List[Long]], info:String = null) {
  println(splitLineL1)  // 分割线
  if (null == info) printLevelInfo("第一层与第二层") else printLevelInfo(info)
  println(splitLineL2+"\n")

  // 表头
  print("L1/L2      \t")
  percentList.foreach(x => print(x + "%\t"))
  print("\n"+splitLineL2)

  // 表内容
  // 累积统计
//  val indices_L1 = runnedMonthsList.indices
//  indices_L1.foreach(index_L1 => {
//    val runnedMonths = runnedMonthsList(index_L1)
//    print(s"\n${runnedMonths},(0->${runnedMonths}]个月\t")  // 首例

  // 区间统计
  val indices_L1 = runnedMonthsIntervalList.indices // runnedMonthsList.indices
  indices_L1.foreach(index_L1 => {
    val runnedMonthsInterval = runnedMonthsIntervalList(index_L1) // runnedMonthsList(index_L1)
    print(s"\n(${runnedMonthsInterval._1}->${runnedMonthsInterval._2}]个月:\t")  // 首例

    // 其他列
    L1L2(index_L1).foreach(x => {
      print(x + "\t")
    })
  })

  println("\n"+splitLineL1)  // 分割线
}
// ----------------------------------------------------------------------------
// 执行打印
printUserCountListList(userCountListList, s"数据集_${datasetId}_所有用电类别")
val indices_elec_type_code_list = elec_type_code_list.indices
indices_elec_type_code_list.foreach(index => {
  val elec_type_code = elec_type_code_list(index)
  val info = s"数据集_${datasetId}_用电类别_${elec_type_code}"
  printUserCountListList(ELEC_TYPE_CODE_userCountListList(index),info)
})
