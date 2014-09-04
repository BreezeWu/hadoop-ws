package org.wuhz.spark.sg.volumetrends

/**
 * Created by hadoop on 9/4/14.
 */

// ****************************************************************************
// 基于 ParsedRDDMatrix 执行分析任务
// ****************************************************************************
case class AnalyzeResultMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, clusterSet:ClusterSet)

object objAnalyzer {
  def analyzeParsedRDDMatrix_Standalone(parsedRDDMatrix: List[List[ParsedRDDMatrixItem]], perfectK:Int, maxIterations: Int = 20, sc:org.apache.spark.SparkContext): List[List[AnalyzeResultMatrixItem]] = {
    def executeParsedRDDList(list:List[ParsedRDDMatrixItem], perfectK:Int, maxIterations: Int):List[AnalyzeResultMatrixItem] = {
      def executeParsedRDD(item:ParsedRDDMatrixItem, perfectK:Int, maxIterations: Int):AnalyzeResultMatrixItem = {
        val parsedRDDRef = item.parsedRDDRef
        val parsedRDD_vpm = parsedRDDRef.vpm
        val parsedRDD_vpmIndexed = parsedRDDRef.vpmIndexed

        // 打印一些信息
        println("----------------------------------------------------------------------------")
        // 时间信息
        val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
        val timeBegin = new java.util.Date()
        println(s">>>>>>>>>\t 开始分析任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)}")

        // 建立模型
        val clusteredInfo_Standalone = objClustering.ClusteringUserInfo_Standalone(parsedRDD_vpm, parsedRDD_vpmIndexed, perfectK, maxIterations, sc)
        // 计算分群数据
        val clusterSet_Standalone = objClustering.ComputeClusterSet(clusteredInfo_Standalone)

        // 打印一些信息
        val timeEnd = new java.util.Date()
        println(s"结束分析任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)} -> ${dateFormat.format(timeEnd)}\t <<<<<<<<<")
        println("----------------------------------------------------------------------------")

        // 结果对象
        val analyzeResultMatrixItem = AnalyzeResultMatrixItem(item.item_L1, item.item_L2, clusterSet_Standalone)
        return analyzeResultMatrixItem
      }

      val result = list.map(y => executeParsedRDD(y, perfectK, maxIterations))
      return result
    }

    val analyzeRDDMatrix = parsedRDDMatrix.map(x => executeParsedRDDList(x, perfectK, maxIterations))

    return analyzeRDDMatrix
  }

  // ----------------------------------------------------------------------------
  // 将 AnalyzeResultMatrix 的簇样本信息写入文件
  def writeAnalyzeResultMatrix_Sample2File(analyzeResultMatrix:List[List[AnalyzeResultMatrixItem]], sampleNum:Int, filenameHead:String, dir:String) = {
    def writeSampleOfAnalyzeResultList(list:List[AnalyzeResultMatrixItem]) = {
      def writeSampleOfAnalyzeResult(item:AnalyzeResultMatrixItem) = {
        val clusterSet = item.clusterSet
        val k = clusterSet.k
        val filenameID = s"k${k}_${item.item_L1.id}_${item.item_L2.id}"

        objClustering.writeClusterSetSample2File(clusterSet, sampleNum, filenameHead + filenameID, dir)
        // 结果对象 : 无
      }

      list.map(y => writeSampleOfAnalyzeResult(y))
      // 结果对象 : 无
    }

    // 不再是map，而是foreach
    //analyzeResultMatrix.map(x => writeSampleOfAnalyzeResultList(x))
    analyzeResultMatrix.foreach(x => writeSampleOfAnalyzeResultList(x))
  }
  // ----------------------------------------------------------------------------
  // 将 AnalyzeResultMatrix 的簇中心信息写入文件
  def writeAnalyzeResultMatrix_ClusterCenters2File(analyzeResultMatrix:List[List[AnalyzeResultMatrixItem]], filenameHead:String, dir:String) = {
    def writeClusterCentersOfAnalyzeResultList(list:List[AnalyzeResultMatrixItem]) = {
      def writeClusterCentersOfAnalyzeResult(item:AnalyzeResultMatrixItem) = {
        val clusterSet = item.clusterSet
        val k = clusterSet.k
        val filenameID = s"k${k}_${item.item_L1.id}_${item.item_L2.id}"

        objClustering.writeClusterSetCenters2File(clusterSet, filenameHead + filenameID, dir)
        // 结果对象 : 无
      }

      list.map(y => writeClusterCentersOfAnalyzeResult(y))
      // 结果对象 : 无
    }

    // 不再是map，而是foreach
    //analyzeResultMatrix.map(x => writeClusterCentersOfAnalyzeResultList(x))
    analyzeResultMatrix.foreach(x => writeClusterCentersOfAnalyzeResultList(x))
  }

}
