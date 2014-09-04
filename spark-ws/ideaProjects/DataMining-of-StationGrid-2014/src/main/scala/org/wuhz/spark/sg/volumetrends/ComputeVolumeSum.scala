package org.wuhz.spark.sg.volumetrends

/**
 * Created by hadoop on 9/4/14.
 */


// 月用电量
// 只对vpm进行计算，不计算vpmIndexed;但结构上保留
case class MonthSum_ParsedRDDRef(monthSum_Vpm: Array[Double], monthSum_VpmIndexed:Array[Double])
case class MonthSum_ParsedRDDMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, monthSumRef:MonthSum_ParsedRDDRef)

// 年用电量
// 只对vpm进行计算，不计算vpmIndexed;但结构上保留
case class YearSum_ParsedRDDRef(yearSum_Vpm: Double, yearSum_VpmIndexed:Double)
case class YearSum_ParsedRDDMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, yearSumRef:YearSum_ParsedRDDRef)

object objComputerOfSum {
  /**
   * 计算L1*L2的年用电量合计
   * @param parsedRDDMatrix
   * @return
   */
  def computeYearSum_ParsedRDDMatrix_Standalone(parsedRDDMatrix: List[List[ParsedRDDMatrixItem]]): List[List[YearSum_ParsedRDDMatrixItem]] = {
    def computeYearSum_ParsedRDDList(list:List[ParsedRDDMatrixItem]):List[YearSum_ParsedRDDMatrixItem] = {
      def computeYearSum_ParsedRDD(item:ParsedRDDMatrixItem):YearSum_ParsedRDDMatrixItem = {
        val parsedRDDRef = item.parsedRDDRef
        val parsedRDD_vpm = parsedRDDRef.vpm
        // val parsedRDD_vpmIndexed = parsedRDDRef.vpmIndexed

        // 打印一些信息
        println("----------------------------------------------------------------------------")
        // 时间信息
        val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
        val timeBegin = new java.util.Date()
        println(s">>>>>>>>>\t 开始计算年用电量任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)}")

        // 计算某一个用户的年用电量
        def computerYearSum_OneUser(x:Array[Double]):Double = {
          x.foldLeft(0.0)((x,y) => x+y )
        }

        // 计算结果
        val yearSum_Vpm = parsedRDD_vpm.map(x => computerYearSum_OneUser(x.toArray)).reduce(
          (y1, y2) => y1 + y2
        )
        // 创建结果对象(x)
        val yearSum_ParsedRDDRef = YearSum_ParsedRDDRef(yearSum_Vpm,0)

        // 打印一些信息
        val timeEnd = new java.util.Date()
        println(s"结束计算年用电量任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)} -> ${dateFormat.format(timeEnd)}\t <<<<<<<<<")
        println("----------------------------------------------------------------------------")

        // 结果对象
        val yearSum_ParsedRDDMatrixItem = YearSum_ParsedRDDMatrixItem(item.item_L1, item.item_L2, yearSum_ParsedRDDRef)
        return yearSum_ParsedRDDMatrixItem
      }

      val result = list.map(y => computeYearSum_ParsedRDD(y))
      return result
    }

    val yearSum_ParsedRDDMatrix = parsedRDDMatrix.map(x => computeYearSum_ParsedRDDList(x))

    return yearSum_ParsedRDDMatrix
  }

  /**
   * 计算L1*L2的月用电量合计
   * @param parsedRDDMatrix
   * @return
   */
  def computeMonthSum_ParsedRDDMatrix_Standalone(parsedRDDMatrix: List[List[ParsedRDDMatrixItem]]): List[List[MonthSum_ParsedRDDMatrixItem]] = {
    def computeMonthSum_ParsedRDDList(list:List[ParsedRDDMatrixItem]):List[MonthSum_ParsedRDDMatrixItem] = {
      def computeMonthSum_ParsedRDD(item:ParsedRDDMatrixItem):MonthSum_ParsedRDDMatrixItem = {
        val parsedRDDRef = item.parsedRDDRef
        val parsedRDD_vpm = parsedRDDRef.vpm
        // val parsedRDD_vpmIndexed = parsedRDDRef.vpmIndexed

        // 打印一些信息
        println("----------------------------------------------------------------------------")
        // 时间信息
        val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
        val timeBegin = new java.util.Date()
        println(s">>>>>>>>>\t 开始计算月用电量合计任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)}")

        // 计算某一个用户的年用电量
        def addMonthSum(x:Array[Double], y:Array[Double]):Array[Double] = {
          val zipXY = x.zip(y)
          return zipXY.map(z => z._1 + z._2)
        }

        // 计算结果
        val monthSum_Vpm = parsedRDD_vpm.map(x => x.toArray).reduce( (y1,y2) => addMonthSum(y1,y2))
        // 创建结果对象(x)
        val monthSum_ParsedRDDRef = MonthSum_ParsedRDDRef(monthSum_Vpm,Array[Double]())

        // 打印一些信息
        val timeEnd = new java.util.Date()
        println(s"结束计算月用电量合计任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)} -> ${dateFormat.format(timeEnd)}\t <<<<<<<<<")
        println("----------------------------------------------------------------------------")

        // 结果对象
        val monthSum_ParsedRDDMatrixItem = MonthSum_ParsedRDDMatrixItem(item.item_L1, item.item_L2, monthSum_ParsedRDDRef)
        return monthSum_ParsedRDDMatrixItem
      }

      val result = list.map(y => computeMonthSum_ParsedRDD(y))
      return result
    }

    val monthSum_ParsedRDDMatrix = parsedRDDMatrix.map(x => computeMonthSum_ParsedRDDList(x))

    return monthSum_ParsedRDDMatrix
  }
  }