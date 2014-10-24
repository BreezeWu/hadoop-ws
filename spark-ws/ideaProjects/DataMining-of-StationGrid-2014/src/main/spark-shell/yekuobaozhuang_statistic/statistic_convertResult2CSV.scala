/**
 * Created by HongZe.Wu on 10/22/14.
 * 输出统计结果
 */

// ----------------------------------------------------------------------------
// 将各个运行结果集转换为csv文件的函数
def printStepInfo(s:String) = {
  println(s"正在处理...===${s}===")
}
// 打印L1L2
def convertUserCountListList2CSV(L1L2:List[List[Any]], filepath:String, info:String = null) {
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
  // 累积统计
//  val indices_L1 = runnedMonthsList.indices
//  indices_L1.foreach(index_L1 => {
//    val runnedMonths = runnedMonthsList(index_L1)
//    filewriter.write(s"\n${runnedMonths},(0->${runnedMonths}]个月")  // 首例

  // 区间统计
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
// 转换分组下的统计结果: 函数定义
def printSubsetUserCountListList(filepath:String, isInterval:Boolean, metaList:List[String], subsetUserCountListList:List[List[List[Any]]]) = {
  val indices_metaList = metaList.indices
  indices_metaList.foreach(index => {
    val elec_type_code = elec_type_code_list(index)
    val strOfInterval = if(isInterval) "区间" else "累计"
    val info = s"数据集_${datasetId}_用电类别_区间_${elec_type_code}"
    convertUserCountListList2CSV(subsetUserCountListList(index), filepath, info)
  })
}
// ----------------------------------------------------------------------------
// 执行函数
val filepath = "/home/hadoop/dm-data/yekuobaozhuang-maxP/output_statistic"

// 原始值
convertUserCountListList2CSV(userCountListList, filepath, s"数据集_${datasetId}_累积_所有用电类别")
printSubsetUserCountListList(filepath, true, elec_type_code_list, userCountListList_ELEC_TYPE_CODE)
// 百分比
convertUserCountListList2CSV(userCountListList, filepath, s"数据集_${datasetId}_累积_所有用电类别")
printSubsetUserCountListList(filepath, true, elec_type_code_list, userCountListList_ELEC_TYPE_CODE)
