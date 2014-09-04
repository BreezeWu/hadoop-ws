package org.wuhz.spark.sg.volumetrends

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD

/**
 * Created by hadoop on 9/4/14.
 */

case class ClusteredInfo(
                          account:Account,
                          dataClustered: RDD[ConsVPMClustered]
                          ) {
  def getPerfectK():Int = {
    val perfectK = this.account.getPerfectKandModel._1
    return perfectK
  }
  def getPerfectModel():org.apache.spark.mllib.clustering.KMeansModel = {
    val perfectModel = this.account.getPerfectKandModel._2
    return perfectModel
  }
  // 获得 clusterID数组
  def getClusterIDArray():Array[Int] = {
    val range = Range(0, this.getPerfectK())
    return range.toArray
  }

  /*
  // 获得 clusterCount
  private var mHasCounted = false
  private var mClusterCount:Array[(Int, Int)] = null
  def getClusterCount():Array[(Int, Int)] = {
      // 1.已经计算过
      if (mHasCounted) return mClusterCount;

      // 2.尚未计算过
      //val clusterCount = ComputeClusterCount(this.dataClustered, this.getClusterIDArray())
      val clusterCount = Array((0,0))

      // 2.98 打上计算标记
      mHasCounted = true
      this.mClusterCount = clusterCount

      // 2.99 返回值
      return this.mClusterCount
  }
  */
}
// -----------------------------------------------------------------------
// 某一个簇
case class OneCluster(
                       clusterID:Int,
                       counter:Long,
                       clusterCenter:org.apache.spark.mllib.linalg.Vector,
                       points: RDD[ConsVPMClustered]
                       )
// 簇集合
case class ClusterSet(
                       k:Int,
                       clusterCenters:Array[org.apache.spark.mllib.linalg.Vector],
                       clusterArray:Array[OneCluster]
                       )

object objClustering {
  // -----------------------------------------------------------------------
  // 计算 ClusterSet 的辅助函数
  def ComputeClusterSet(clusteredInfo:ClusteredInfo):ClusterSet = {
    // 簇个数
    val k = clusteredInfo.getPerfectK()

    // 簇中心点集合
    val perfeckModel = clusteredInfo.getPerfectModel()     // 可能为 null

    if(null == perfeckModel) {
      // 虚构 clusterCenters
      // 虚构 clusterArray

      // 簇集合对象
      val fake_clusterSet = new ClusterSet(k, null, null)
      // 返回值
      return fake_clusterSet
    }

    // 下面可以安全执行
    val clusterCenters = clusteredInfo.getPerfectModel().clusterCenters

    // ------------------------------------------------------------------------
    // 构建 clusterList
    // ------------------------------------------------------------------------
    // 辅助函数: 某个 ConsVPMClustered 是该簇吗?
    def isThisCluster(point:ConsVPMClustered, clusterID:Int):Boolean = {
      if (point.clusterID == clusterID) return true
      else return false
    }

    // 簇ID集合
    val clusterIDArray = clusteredInfo.getClusterIDArray()
    // 已分簇的数据
    val dataClustered = clusteredInfo.dataClustered

    val clusterArray = new Array[OneCluster](k)
    for (i <- clusterIDArray) {
      val clusterID = i
      val clusterCenter = clusterCenters(i)

      // 下面这个语句能够在分布式环境中成功执行吗?
      val points = dataClustered.filter(x => isThisCluster(x,i))
      val counter = points.count

      val oneCluster = new OneCluster(clusterID, counter, clusterCenter, points)
      clusterArray(i) = oneCluster
    }

    // 簇集合对象
    val clusterSet = new ClusterSet(k, clusterCenters, clusterArray)

    // 返回值
    return clusterSet
  }

  // -----------------------------------------------------------------------
  // 打印簇样本
  def printSampleData(sampleData:Array[ConsVPMClustered]) = {
    sampleData.foreach(x => x.print)
  }

  // 获得某个簇的样本
  def getSampleFromClusterSet(clusterSet:ClusterSet, clusterID:Int, num:Int):Array[ConsVPMClustered] = {
    // 参数检查
    // <console>:49: error: type mismatch;
    /*
    if (null == clusterSet) {
        println("// ----------------------------------------------------------------------------")
        println("\t\t 坑谁呢? null == clusterSet")
        println("// ----------------------------------------------------------------------------")
        return null
    }
    */
    if (clusterID < 0 || num < 0) {
      println("// ----------------------------------------------------------------------------")
      println(s"\t\t clusterID:${clusterID} 与 num:${num} 都不能小于 0")
      println("// ----------------------------------------------------------------------------")
      return null
    }
    if (clusterID > clusterSet.k) {
      println("// ----------------------------------------------------------------------------")
      println(s"\t\t clusterID:${clusterID} 不能大于 clusterSet.k ${clusterSet.k}")
      println("// ----------------------------------------------------------------------------")
      return null
    }
    if (num > 1000) {
      println("// ----------------------------------------------------------------------------")
      println(s"\t\t num:${num} 不能大于 1000")
      println("// ----------------------------------------------------------------------------")
      return null
    }

    val sampleData = clusterSet.clusterArray(clusterID).points.take(num)
    return sampleData
  }

  // -----------------------------------------------------------------------
  // 打印簇以及样本
  def printClusterSetSample(clusterSet:ClusterSet, sampleNum:Int) = {
    val k = clusterSet.k
    val clusterCenters = clusterSet.clusterCenters
    val clusterArray = clusterSet.clusterArray

    println("======================================================================================")
    println("----------------------------------------------------------------------------")
    println(s"\t簇个数:\t ${k}")

    println("--------------------------------------")
    println(s"\t簇ID:\t 计数\n")
    // 打印簇ID及其个数
    for (oneCluster <- clusterArray) {
      println(s"\t${oneCluster.clusterID}:\t ${oneCluster.counter}")
    }

    println("--------------------------------------")
    println("\t 簇中心 \n")
    // 打印簇中心
    // for (center <- clusterCenters) {  //想要打印出ID和计数
    val range = Range(0, clusterCenters.length)
    for (i <- range) {
      val id = i
      val counter = clusterArray(i).counter
      val sHead = s"${id},${counter},||"
      val centerStr = clusterCenters(i).toArray.foldLeft(sHead)((x,y) => x + "," + y)
      println(centerStr)
    }

    println("----------------------------------------------------------------------------")
    println("\t 打印每个簇及其样本信息 \n")

    // 打印某个簇及其样本
    def printOneClusterSample(oneCluster:OneCluster, sampleNum:Int)
    {
      println(s"\t簇ID:\t 计数")
      println(s"\t${oneCluster.clusterID}:\t ${oneCluster.counter}\n")
      println(s"\t样本数据\n")

      val sampleData = oneCluster.points.take(sampleNum)
      printSampleData(sampleData)
    }

    println("// ----------------------------------------------------------------------------")
    println("\t 簇及其样本 \n")
    // 打印簇样本
    for (oneCluster <- clusterArray) {
      println("--------------------------------------")
      printOneClusterSample(oneCluster, sampleNum)
    }
    println("// =====================================================================================")
  }

  // -----------------------------------------------------------------------
  // 打印簇以及样本
  def writeClusterSetSample2File(clusterSet:ClusterSet, sampleNum:Int, filename:String, dir:String = null):Unit = {
    val rootpath = if (dir != null) dir else "/home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/result-data/"
    val filepath = rootpath + "/" + filename + "_sampledata_" + sampleNum + ".txt"
    val filepath_prefix = rootpath + "/" + filename + "_sampledata_" + sampleNum

    val file = new java.io.File(filepath)
    val isCreated = file.createNewFile()

    //val filepath = "/home/hadoop/demo.csv"
    val filewriter = new java.io.FileWriter(file)
    //filewriter.write(filepath)
    //filewriter.write(filepath)
    //filewriter.flush()
    //filewriter.close()

    val k = clusterSet.k
    val clusterCenters = clusterSet.clusterCenters
    val clusterArray = clusterSet.clusterArray

    // 检查是否 虚假 Cluster
    if ( null == clusterCenters || null == clusterArray) {
      filewriter.write("\n======================================================================================")
      filewriter.write("\n\t 这个簇的 null == clusterCenters || null == clusterArray .")
      filewriter.write("\n======================================================================================")

      filewriter.flush()
      filewriter.close()
      return
    }

    filewriter.write("\n======================================================================================")
    filewriter.write("\n----------------------------------------------------------------------------")
    filewriter.write(s"\n\t簇个数:\t ${k}")

    filewriter.write("\n--------------------------------------")
    filewriter.write(s"\n\t簇ID:\t 计数\n")
    // 打印簇ID及其个数
    for (oneCluster <- clusterArray) {
      filewriter.write(s"\n\t${oneCluster.clusterID}:\t ${oneCluster.counter}")
    }

    filewriter.write("\n--------------------------------------")
    filewriter.write("\n\t 簇中心 \n")
    // 打印簇中心
    // for (center <- clusterCenters) {  //想要打印出ID和计数
    val range = Range(0, clusterCenters.length)
    for (i <- range) {
      val id = i
      val counter = clusterArray(i).counter
      val sHead = s"${id},${counter},||"
      val centerStr = clusterCenters(i).toArray.foldLeft(sHead)((x,y) => x + "," + y)
      filewriter.write("\n" + centerStr)
    }

    filewriter.write("\n----------------------------------------------------------------------------")
    filewriter.write("\n\t 打印每个簇及其样本信息 \n")

    // 函数: 打印某个簇及其样本
    def writerOneClusterSample(oneCluster:OneCluster, sampleNum:Int)
    {
      filewriter.write(s"\n\t簇ID:\t 计数")
      filewriter.write(s"\n\t${oneCluster.clusterID}:\t ${oneCluster.counter}\n")
      filewriter.write(s"\n\t样本数据\n")

      val sampleData = oneCluster.points.take(sampleNum)
      sampleData.foreach(x => filewriter.write("\n" + x.getPrintLine()))
    }
    // 函数: 打印某个簇及其样本(单独一个文件)
    def writerOneClusterSample2File(oneCluster:OneCluster, sampleNum:Int, filepathPrefix:String)
    {
      val filepath_inner = filepathPrefix + "_ID" + oneCluster.clusterID + "_" + oneCluster.counter + ".csv"

      val file_inner = new java.io.File(filepath_inner)
      val isCreated_inner = file_inner.createNewFile()
      val filewriter_inner = new java.io.FileWriter(file_inner)

      val sampleData = oneCluster.points.take(sampleNum)
      sampleData.foreach(x => filewriter_inner.write(x.getPrintLine() + "\n"))

      filewriter_inner.flush()
      filewriter_inner.close()
    }

    filewriter.write("\n----------------------------------------------------------------------------")
    filewriter.write("\n\t 簇及其样本 \n")
    // 打印簇样本
    for (oneCluster <- clusterArray) {
      filewriter.write("\n--------------------------------------")
      writerOneClusterSample(oneCluster, sampleNum)
      writerOneClusterSample2File(oneCluster, sampleNum, filepath_prefix)
    }
    filewriter.write("\n=====================================================================================")

    filewriter.flush()
    filewriter.close()
  }

  // -----------------------------------------------------------------------
  // 打印簇中心信息
  def writeClusterSetCenters2File(clusterSet:ClusterSet, filename:String, dir:String = null):Unit = {
    val rootpath = if (dir != null) dir else "/home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/result-data/"
    val filepath = rootpath + "/" + filename + "_clusterCenters" + ".csv"

    val file = new java.io.File(filepath)
    val newfile = file.createNewFile()

    val filewriter = new java.io.FileWriter(file)

    val k = clusterSet.k
    val clusterCenters = clusterSet.clusterCenters
    val clusterArray = clusterSet.clusterArray

    // 检查是否 虚假 Cluster
    if ( null == clusterCenters || null == clusterArray) {
      filewriter.write("\n======================================================================================")
      filewriter.write("\n\t 这个簇的 null == clusterCenters || null == clusterArray .")
      filewriter.write("\n======================================================================================")

      filewriter.flush()
      filewriter.close()
      return
    }

    // 打印簇中心
    // for (center <- clusterCenters) {  //想要打印出ID和计数
    val range = Range(0, clusterCenters.length)
    for (i <- range) {
      val id = i
      val counter = clusterArray(i).counter
      val sHead = s"${id},${counter}"
      val centerStr = clusterCenters(i).toArray.foldLeft(sHead)((x,y) => x + "," + y)
      filewriter.write(centerStr + "\n")
    }

    filewriter.flush()
    filewriter.close()
  }

  // ****************************************************************************
  // 将 broadcastModelRef 放回 Account, 其实是copy一个Account了！
  // 避免大集群下 model被复制多份！
  // ****************************************************************************
  def cloneAccountRepalcePerfeckModelRef(source:Account, kOfModel:Int, broadcastModelRef:org.apache.spark.broadcast.Broadcast[org.apache.spark.mllib.clustering.KMeansModel]):Account = {
    val counter = source.counter
    val tryCounter = source.tryCounter
    val metricList = source.metricList

    // 关键在于 metricList 替换
    // 条件替换函数
    def smartClone(curMetric:Metric, toBeReplacingK:Int):Metric = {
      if (curMetric.k == toBeReplacingK) {
        // 这里就使用 broadcastModelRef.value , 会有效果吗?
        val newMetric = Metric(curMetric.k, curMetric.maxIterations, curMetric.WSSSE, curMetric.begin, curMetric.end, broadcastModelRef.value)
        return newMetric
      } else {
        return curMetric
      }
    }

    val newMetricList = metricList.map(x => smartClone(x, kOfModel))

    // 结果对象
    return Account(counter, tryCounter, newMetricList)
  }

  // ****************************************************************************
  // 函数: 聚类并分配簇标号
  // ****************************************************************************
  //  独立进行聚类并分群
  //    // maxIterations :    当前没有生效
  def ClusteringUserInfo_Standalone(dataForModel: RDD[org.apache.spark.mllib.linalg.Vector], dataIndexed: RDD[ConsVPM], k:Int, maxIterations: Int = 20, sc:org.apache.spark.SparkContext):ClusteredInfo = {
    // ------------------------------------------------------------------------
    // 执行聚类
    val parKTriangle = new KTriangle(k,k+1) // KTriangle(k,k+1)
    val resultAccount = objAccountedKMeans.evalWSSSEOfK(dataForModel, parKTriangle.low, maxIterations, Account(0,0,Nil))

    // 聚类结果写入文件
    // val resultWriteHDFS = writeAccount2HDFS(resultAccount,0)
    // ------------------------------------------------------------------------
    // 分类统计  注意 evalWSSSEOfK 可能会返回 Account(0,0,Nil), 即metricList是Nil
    //
    if (Nil == resultAccount.metricList) {
      def fakeComputeClusterID(consVpm: ConsVPM):ConsVPMClustered = {
        val vector = Vectors.dense(consVpm.vpm)
        val clusterID = 9999999
        val consVpmClustered = new ConsVPMClustered(consVpm, clusterID)
        consVpmClustered
      }

      // 虚假
      val fake_dataClustered = dataIndexed.map(x => fakeComputeClusterID(x))
      // 虚假 Metric
      val fake_metric = Metric(k, maxIterations, 0,  new java.util.Date(),  new java.util.Date(), null)
      // 虚假 metricList
      val fake_account = Account(0,0,List(fake_metric))

      return new ClusteredInfo(fake_account, fake_dataClustered)
    }

    // 此时 model 是有效的
    val model = resultAccount.metricList(0).model

    // 将 model 变为 Broadcast Variables
    val broadcastModelRef = sc.broadcast(model)
    // 再次访问model的方式是 broadcastModelRef.value

    val newAccount = cloneAccountRepalcePerfeckModelRef(resultAccount, k, broadcastModelRef)

    // 利用model识别dataIndexed中各自所属的clusterID, 即为每一个 ConsVPM 获得其 clusterID
    // ConsVPM,ConsVPMClustered 的定义在 create-parsedData-userinfo-s01-v2.scala 文件中
    def ComputeClusterID(consVpm: ConsVPM):ConsVPMClustered = {
      val vector = Vectors.dense(consVpm.vpm)
      val clusterID = broadcastModelRef.value.predict(vector)
      val consVpmClustered = new ConsVPMClustered(consVpm, clusterID)
      consVpmClustered
    }

    // 执行函数
    val dataClustered = dataIndexed.map(x => ComputeClusterID(x))

    // ------------------------------------------------------------------------
    // 函数返回值
    //new ClusteredInfo(resultAccount, dataClustered)
    new ClusteredInfo(newAccount, dataClustered)
  }

  // ****************************************************************************
  // 函数: 利用最佳K的 KMeansModel进行聚类,即从Account中获得最佳模型
  // ****************************************************************************
  def ClusteringUserInfo_FromAccount(dataIndexed: RDD[ConsVPM], account:Account, sc:org.apache.spark.SparkContext) = {
    // ------------------------------------------------------------------------
    // 取得任务的开始日期,最佳K，最佳K对应的模型
    val dateString = account.getBeginDateString()
    val perfectK = account.getPerfectKandModel._1
    val perfectModel = account.getPerfectKandModel._2
    // ------------------------------------------------------------------------
    // 分类统计
    val model = perfectModel

    // 将 model 变为 Broadcast Variables
    val broadcastModelRef = sc.broadcast(model)
    // 再次访问model的方式是 broadcastModel.value

    val newAccount = cloneAccountRepalcePerfeckModelRef(account, perfectK, broadcastModelRef)

    // 利用model识别dataIndexed中各自所属的clusterID, 即为每一个 ConsVPM 获得其 clusterID
    // ConsVPM,ConsVPMClustered 的定义在 create-parsedData-userinfo-s01-v2.scala 文件中
    def ComputeClusterID(consVpm: ConsVPM):ConsVPMClustered = {
      val vector = Vectors.dense(consVpm.vpm)
      val clusterID = broadcastModelRef.value.predict(vector)
      val consVpmClustered = new ConsVPMClustered(consVpm, clusterID)
      consVpmClustered
    }

    // 执行函数
    val dataClustered = dataIndexed.map(x => ComputeClusterID(x))

    // ------------------------------------------------------------------------
    // 函数返回值
    //new ClusteredInfo(resultAccount, dataClustered)
    new ClusteredInfo(newAccount, dataClustered)
  }
}
