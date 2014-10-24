/**
 * Created by HongZe.Wu on 10/22/14.
 * 输出统计结果
 */

// ----------------------------------------------------------------------------
// 将各个运行结果集转换为csv文件的函数
def printStepInfo(s:String) = {
  println(s"正在处理...===${s}===")
}
def convertUserCountListList2CSV(countListList:List[List[Any]],
                                 info:String = null,
                                 filepath:String,
                                 curApproach:Int) {
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
  val result = curApproach match {
    case APPROACH_CHAOS => {
      val indices_L1 = minRunnedMonthsList.indices
      indices_L1.foreach(index_L1 => {
        val runnedMonths = minRunnedMonthsList(index_L1)
        filewriter.write(s"\n${runnedMonths},(0->${runnedMonths}]个月")  // 首例

        // 其他列
        countListList(index_L1).foreach(x => {
          filewriter.write("," + x)
        })
      })
    }
    case APPROACH_INTERVAL => {
      val indices_L1 = runnedMonthsIntervalList.indices // runnedMonthsList.indices
      indices_L1.foreach(index_L1 => {
        val runnedMonthsInterval = runnedMonthsIntervalList(index_L1) // runnedMonthsList(index_L1)
        filewriter.write(s"\n${runnedMonthsInterval._2},(${runnedMonthsInterval._1}->${runnedMonthsInterval._2}]个月")  // 首例

        // 其他列
        countListList(index_L1).foreach(x => {
          filewriter.write("," + x)
        })
      })
    }
    case APPROACH_SEGMENT => {
      val indices_L1 = maxRunnedMonthsList.indices
      indices_L1.foreach(index_L1 => {
        val runnedMonths = maxRunnedMonthsList(index_L1)
        filewriter.write(s"\n${runnedMonths},(0->${runnedMonths}]个月")  // 首例

        // 其他列
        countListList(index_L1).foreach(x => {
          filewriter.write("," + x)
        })
      })
    }
    case _ => {println(s"undefined curApproach[${curApproach}}]"); Nil}
  }

  // 文件句柄-----end
  filewriter.flush()
  filewriter.close()
}

// ----------------------------------------------------------------------------
// 转换分组下的统计结果: 分组数据集的统计结果List
def convertSubsetUserCountListList(filepath:String,
                                 isInterval:Boolean,
                                 isPercent:Boolean,
                                 metaList:List[String],
                                 subsetUserCountListList:List[List[List[Any]]],
                                 curApproach:Int) = {
  val indices_metaList = metaList.indices
  indices_metaList.foreach(index => {
    val elec_type_code = metaList(index)
    val strOfIsInterval = if(isInterval) "时长区间" else "小于给定时长"
    val strOfIsPercent = if(isPercent) "百分比" else "原始值"
    val info = s"数据集_${datasetId}_用电类别_${elec_type_code}_${strOfIsInterval}_approach_${curApproach}_${strOfIsPercent}"
    convertUserCountListList2CSV(subsetUserCountListList(index), info, filepath, curApproach)
  })
}
