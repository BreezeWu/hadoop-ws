package org.wuhz.spark.sg.volumetrends

/**
 * Created by HongZe.Wu on 9/4/14.
 */
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/** Computes an approximation to pi */
object Executer {
  def main(args: Array[String]) {
    println("-------------------------------------------------------------")
    println("@ Analyzing")
    println("\n参数列表为:")
    args.foreach(println)
    println("-------------------------------------------------------------")

    if (args.length <2 ||
        !(args(0) == "s01" || args(0) == "s98")) {
      System.err.println("illegal args(0): ${args(0)}")
      System.err.println("Usage: Executer s01/s98 100/1000/1000")
      System.exit(1)
    }
    if (!(args(1) == "1" || args(1) == "100" || args(1) == "1000" || args(1) == "10000")) {
      System.err.println("illegal args(1): ${args(1)}")
      System.err.println("Usage: Executer s01/s98 1/100/1000/1000")
      System.exit(1)
    }

    val datasetID = args(0)
    val percent = args(1).toInt

    // 创建 sc
    val conf = new SparkConf().setAppName("Analyzing VolumeTrends")
    val sc = new SparkContext(conf)

    import org.apache.spark.mllib.linalg.Vectors
    import org.apache.spark.mllib.linalg.distributed.RowMatrix

    val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)

    // 数据集对象
    val DataSetRef_L1 = objDataSet.BuildDataSetL1(args(0))
    val DataSetRef_L2 = objDataSet.BuildDataSetL2(args(0))
    // 计算SQLMatrix
    val SqlMatrix = objCreateRDD.buildSQLMatrix(DataSetRef_L1, DataSetRef_L2)
    // 计算HiveRDDMatrix
    val HiveRDDMatrix = objCreateRDD.buildHiveRDDMatrix(hiveContext, SqlMatrix)

    // 变换为 ParsedRDDMatrix : 原值或者 百分比
    val ParsedRDDMatrix =
      if (1== percent) objCreateRDD.transform2ParsedRDDMatrix(HiveRDDMatrix)  // 原值
      else objCreateRDD.transform2ParsedRDDMatrix_percent(HiveRDDMatrix, percent) // 百分比

    // ----------------------------------------------------------------------------
    // 在 ParsedRDDMatrix 上执行分析任务
    val perfectK = 20;
    val maxIterations = 20 // 当前没有生效

    // 执行分析
    val AnalyzeResultMatrix = objAnalyzer.analyzeParsedRDDMatrix_Standalone(ParsedRDDMatrix, perfectK, maxIterations, sc)

    // 测试分析
    // 计算 ParsedRDDMatrix 中各个RDD 的count
    val rddCountMatrix_ParsedRDD = objCounting.ComputeRDDCount_ParsedRDDMatrix(ParsedRDDMatrix)
    //  List(List((11723,11723), (39,39), (995,995)), List((2037,2037), (1,1), (85,85)), List((324,324), (2,2), (52,52)), List((3624,3624), (20,20), (734,734)))

    // ----------------------------------------------------------------------------
    val head = datasetID + "_L2"
    val env = System.getenv()
    val pwd = env.get("PWD")
    // 将簇样本信息写入文件
    val sampleNum = 200
    objAnalyzer.writeAnalyzeResultMatrix_Sample2File(AnalyzeResultMatrix, sampleNum, head, pwd)

    // 单独将簇中心信息写入文件
    objAnalyzer.writeAnalyzeResultMatrix_ClusterCenters2File(AnalyzeResultMatrix, head, pwd)

    // ----------------------------------------------------------------------------
    // 计算年用电量合计
    val YearSum_ParsedRDDMatrix = objComputerOfSum.computeYearSum_ParsedRDDMatrix_Standalone(ParsedRDDMatrix)
    // 运行打印函数
    YearSum_ParsedRDDMatrix.foreach(list => list.foreach(printYearSum))
    // ----------------------------------------------------------------------------
    // 计算月用电量合计
    val MonthSum_ParsedRDDMatrix = objComputerOfSum.computeMonthSum_ParsedRDDMatrix_Standalone(ParsedRDDMatrix)
    // 运行打印函数
    MonthSum_ParsedRDDMatrix.foreach(list => list.foreach(printMonthSum))

    // 退出程序
    sc.stop()
  }

  // 打印出 L1*L2的年用电量
  def printYearSum(x:YearSum_ParsedRDDMatrixItem) = {
    val id_L1 = x.item_L1.id
    val id_L2 = x.item_L2.id
    val yearSum = x.yearSumRef.yearSum_Vpm

    println(s"${id_L1}-${id_L2}: ${yearSum}")
  }

  // 打印出 L1*L2的月用电量合计
  def printMonthSum(x:MonthSum_ParsedRDDMatrixItem) = {
    val id_L1 = x.item_L1.id
    val id_L2 = x.item_L2.id
    val monthSum = x.monthSumRef.monthSum_Vpm

    println(s"${id_L1}-${id_L2}: ${monthSum}")
  }
}