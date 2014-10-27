/**
 * Created by HongZe.Wu on 10/22/14.
 * 输出统计结果
 */

// ----------------------------------------------------------------------------
// 打印各个运行结果集的函数
val splitLineL1 = "----------------------------------------------------------------------------"
val splitLineL2 = "-------------------------------------"
def printLevelInfo(s:String) = {
  println(s"===${s}===")
}

// ----------------------------------------------------------------------------
// 打印统计结果
def printUserCountListList(countListList:List[List[Any]],
                           info:String = null,
                           curApproach:Int) {
  println(splitLineL1)  // 分割线
  if (null == info) printLevelInfo("第一层与第二层") else printLevelInfo(info)
  println(splitLineL2+"\n")

  // 表头
  print("L1/L2      \t")
  percentList.foreach(x => print(x + "%\t"))
  print("\n"+splitLineL2)

  // 表内容
  val result = curApproach match {
    case APPROACH_CHAOS => {
      val indices_L1 = minRunnedMonthsList.indices
      indices_L1.foreach(index_L1 => {
        val runnedMonths = minRunnedMonthsList(index_L1)
        print(s"\n${runnedMonths},(0->${runnedMonths}]个月\t")  // 首例

        // 其他列
        countListList(index_L1).foreach(x => {
          print(x + "\t")
        })
      })
    }
    case APPROACH_INTERVAL => {
      val indices_L1 = runnedMonthsIntervalList.indices // runnedMonthsList.indices
      indices_L1.foreach(index_L1 => {
        val runnedMonthsInterval = runnedMonthsIntervalList(index_L1) // runnedMonthsList(index_L1)
        print(s"\n(${runnedMonthsInterval._1}->${runnedMonthsInterval._2}]个月:\t") // 首例

        // 其他列
        countListList(index_L1).foreach(x => {
          print(x + "\t")
        })
      })
    }
    case APPROACH_SEGMENT => {
      val indices_L1 = maxRunnedMonthsList.indices
      indices_L1.foreach(index_L1 => {
        val runnedMonths = maxRunnedMonthsList(index_L1)
        print(s"\n${runnedMonths},>=${runnedMonths}个月\t")  // 首例

        // 其他列
        countListList(index_L1).foreach(x => {
          print(x + "\t")
        })
      })
    }
    case _ => {println(s"undefined curApproach[${curApproach}}]"); Nil}
  }

  println("\n"+splitLineL1) // 分割线
}
// ----------------------------------------------------------------------------
// 打印统计结果: 分组数据集的统计结果List
def printSubsetUserCountListList(metaList:List[String],
                                 subsetUserCountListList:List[List[List[Any]]],
                                 curApproach:Int) = {
  val indices_metaList = metaList.indices
  indices_metaList.foreach(index => {
    val elec_type_code = metaList(index)
    val info = s"数据集_${datasetId}_用电类别_${elec_type_code}_approach_${curApproach}"
    printUserCountListList(subsetUserCountListList(index),info, curApproach)
  })
}
