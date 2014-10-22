package org.wuhz.spark.sg.volumetrends

/**
 * Created by HongZe.Wu on 9/4/14.
 */

object objCounting {
  // ----------------------------------------------------------------------------
  // 计算 ParsedRDDMatrix 中各自的数量
  def ComputeRDDCount_ParsedRDDMatrix(parsedRDDMatrix: List[List[ParsedRDDMatrixItem]]):List[List[(Long,Long)]] = {
    def ComputeRDDCount_ParsedRDDList(list:List[ParsedRDDMatrixItem]):List[(Long,Long)] = {
      def ComputeRDDCount_ParsedRDD(item:ParsedRDDMatrixItem):(Long,Long) = {
        val parsedRDDRef = item.parsedRDDRef
        val parsedRDD_vpm = parsedRDDRef.vpm
        val parsedRDD_vpmIndexed = parsedRDDRef.vpmIndexed

        // count
        val countOf_parsedRDD_vpm = parsedRDD_vpm.count
        val countOf_parsedRDD_vpmIndexed = parsedRDD_vpmIndexed.count

        // 结果对象
        val rddCountPair = Tuple2(countOf_parsedRDD_vpm, countOf_parsedRDD_vpmIndexed)
        return rddCountPair
      }

      val result = list.map(y => ComputeRDDCount_ParsedRDD(y))
      return result
    }

    val rddCountMatrix = parsedRDDMatrix.map(x => ComputeRDDCount_ParsedRDDList(x))

    return rddCountMatrix
  }
}
