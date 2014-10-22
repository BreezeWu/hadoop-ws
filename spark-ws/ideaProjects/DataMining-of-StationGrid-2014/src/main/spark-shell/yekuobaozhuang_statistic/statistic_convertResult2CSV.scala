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
// 将各个运行结果集转换为csv文件的函数
def printStepInfo(s:String) = {
  println(s"正在处理...===${s}===")
}
// 打印L1L2
def convertUserCountListList2CSV(L1L2:List[List[Long]], filepath:String, info:String = null) {
  if (null == info) printStepInfo("第一层与第二层") else printStepInfo(info)

  // 文件的完整路径
  val fullfile = filepath + "/" + info + ".csv"
  // 文件句柄-----begin
  val file = new java.io.File(fullfile)
  val flag = file.createNewFile()
  if (!flag) {
    println(s"===========创建文件[${fullfile}]失败了, 返回值是${flag}============")
    return
  }
  val filewriter = new java.io.FileWriter(file)

  // 表头
  filewriter.write("区间右值,L1/L2")
  percentList.foreach(x => filewriter.write("," + x + "%"))
  //filewriter.write("\n")

  // 表内容
  val indices_L1 = runnedMonthsIntervalList.indices // runnedMonthsList.indices
  indices_L1.foreach(index_L1 => {
    val runnedMonthsInterval = runnedMonthsIntervalList(index_L1) // runnedMonthsList(index_L1)
    filewriter.write(s"\n${runnedMonthsInterval._2},(${runnedMonthsInterval._1}->${runnedMonthsInterval._2}]个月")  // 首例

    // 其他列
    L1L2(index_L1).foreach(x => {
      filewriter.write("," + x)
    })
  })

  // 文件句柄-----end
  filewriter.flush()
  filewriter.close()
}
// ----------------------------------------------------------------------------
// 执行函数
val filepath = "/home/hadoop/dm-data/yekuobaozhuang-maxP/output_statistic"

convertUserCountListList2CSV(userCountListList, filepath, s"数据集_${datasetId}_所有用电类别")
val indices_elec_type_code_list = elec_type_code_list.indices
indices_elec_type_code_list.foreach(index => {
  val elec_type_code = elec_type_code_list(index)
  val info = s"数据集_${datasetId}_用电类别_${elec_type_code}"
  convertUserCountListList2CSV(ELEC_TYPE_CODE_userCountListList(index), filepath, info)
})
