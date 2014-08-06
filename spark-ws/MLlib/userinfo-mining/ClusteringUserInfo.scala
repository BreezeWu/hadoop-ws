// ----------------------------------------------------------------------------
//     Clustering Users
//    对用户进行聚类,并给出每一个用户的ClusterID
//    input:    perfectK
// ----------------------------------------------------------------------------

// ****************************************************************************
// 辅助函数
// ****************************************************************************
// -----------------------------------------------------------------------
// ClusteredInfo
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

//object ClusterSet {
    // -----------------------------------------------------------------------
    // 计算 ClusterSet 的辅助函数, 为 getClusterCount函数所使用
    def ComputeClusterSet(clusteredInfo:ClusteredInfo):ClusterSet = {
        // 簇个数
        val k = clusteredInfo.getPerfectK()
        // 簇中心点集合
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
//}

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
def writeClusterSetSample2File(clusterSet:ClusterSet, sampleNum:Int, filename:String) = {
    val rootpath = "/home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/result-data/"
    val filepath = rootpath + "sampledata_" + sampleNum + "_" + filename + ".txt"
    
    val file = new java.io.File(filepath)
    val newfile = file.createNewFile()
    
    //val filepath = "/home/hadoop/demo.csv"
    val filewriter = new java.io.FileWriter(file)
    //filewriter.write(filepath)
    //filewriter.write(filepath)
    //filewriter.flush()
    //filewriter.close() 
    
    val k = clusterSet.k
    val clusterCenters = clusterSet.clusterCenters
    val clusterArray = clusterSet.clusterArray
    
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
    
    // 打印某个簇及其样本
    def writerOneClusterSample(oneCluster:OneCluster, sampleNum:Int)
    {
        filewriter.write(s"\n\t簇ID:\t 计数")
        filewriter.write(s"\n\t${oneCluster.clusterID}:\t ${oneCluster.counter}\n")
        filewriter.write(s"\n\t样本数据\n")
        
        val sampleData = oneCluster.points.take(sampleNum)
        sampleData.foreach(x => filewriter.write("\n" + x.getPrintLine()))
    }
    
    filewriter.write("\n----------------------------------------------------------------------------")
    filewriter.write("\n\t 簇及其样本 \n")
    // 打印簇样本
    for (oneCluster <- clusterArray) {
        filewriter.write("\n--------------------------------------")
        writerOneClusterSample(oneCluster, sampleNum)
    }
    filewriter.write("\n=====================================================================================")
    
    filewriter.flush()
    filewriter.close() 
}

// -----------------------------------------------------------------------
// 打印簇中心信息
def writeClusterSetCenters2File(clusterSet:ClusterSet, filename:String) = {
    val rootpath = "/home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/result-data/"
    val filepath = rootpath + "clusterCenters_" + filename + ".csv"
    
    val file = new java.io.File(filepath)
    val newfile = file.createNewFile()
    
    val filewriter = new java.io.FileWriter(file)
    
    val k = clusterSet.k
    val clusterCenters = clusterSet.clusterCenters
    val clusterArray = clusterSet.clusterArray
    
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
// 函数: 聚类并分配簇标号
// ****************************************************************************
//  独立进行聚类并分群
//    // maxIterations :    当前没有生效
def ClusteringUserInfo_Standalone(dataForModel: RDD[Vector], dataIndexed: RDD[ConsVPM], k:Int, maxIterations: Int = 20, sc:org.apache.spark.SparkContext) = {
    // ------------------------------------------------------------------------
    // 执行聚类
    val parKTriangle = new KTriangle(k,k+1)
    val resultAccount = evalWSSSEOfK(dataForModel, parKTriangle.low, maxIterations, Account(0,0,Nil))

    // 聚类结果写入文件
    // val resultWriteHDFS = writeAccount2HDFS(resultAccount,0)
    // ------------------------------------------------------------------------
    // 分类统计
    val model = resultAccount.metricList(0).model
    
    // 将 model 变为 Broadcast Variables
    val broadcastModel = sc.broadcast(model)
    // 再次访问model的方式是 broadcastModel.value
    
    // 利用model识别dataIndexed中各自所属的clusterID, 即为每一个 ConsVPM 获得其 clusterID
    // ConsVPM,ConsVPMClustered 的定义在 create-parsedData-userinfo-s01-v2.scala 文件中
    def ComputeClusterID(consVpm: ConsVPM):ConsVPMClustered = {
        val vector = Vectors.dense(consVpm.vpm)
        val clusterID = broadcastModel.value.predict(vector)
        val consVpmClustered = new ConsVPMClustered(consVpm, clusterID)
        consVpmClustered
    }
    
    // 执行函数
    val dataClustered = dataIndexed.map(x => ComputeClusterID(x))

    // ------------------------------------------------------------------------
    // 函数返回值
    new ClusteredInfo(resultAccount, dataClustered)
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
    val broadcastModel = sc.broadcast(model)
    // 再次访问model的方式是 broadcastModel.value
    
    // 利用model识别dataIndexed中各自所属的clusterID, 即为每一个 ConsVPM 获得其 clusterID
    // ConsVPM,ConsVPMClustered 的定义在 create-parsedData-userinfo-s01-v2.scala 文件中
    def ComputeClusterID(consVpm: ConsVPM):ConsVPMClustered = {
        val vector = Vectors.dense(consVpm.vpm)
        val clusterID = broadcastModel.value.predict(vector)
        val consVpmClustered = new ConsVPMClustered(consVpm, clusterID)
        consVpmClustered
    }
    
    // 执行函数
    val dataClustered = dataIndexed.map(x => ComputeClusterID(x))

    // ------------------------------------------------------------------------
    // 函数返回值
    new ClusteredInfo(account, dataClustered)
}


