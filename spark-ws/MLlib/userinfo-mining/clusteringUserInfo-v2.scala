// ----------------------------------------------------------------------------
//     Clustering Users
//    对用户进行聚类,并给出每一个用户的ClusterID
//    input:    perfectK
// ----------------------------------------------------------------------------

// ****************************************************************************
// 辅助函数
// ****************************************************************************
case class ClusterInfo(
    account:Account,
    clusterCount: Array[(Int, Int)],
    predict:RDD[Int]
)

def writeClusterInfo2HDFS(clusterInfo:ClusterInfo, taskName:String = "feelingLucky") = {
    // ------------------------------------------------------------------------
    // 簇ID:     从0开始计数, 就是簇中心的indices
    // 簇中心:     account.getPerfectKandModel._2.clusterCenters
    // 族成员数量:    clusterCount

    // 参数导入
    val clusterCount = clusterInfo.clusterCount
    val clusterCenters = clusterInfo.account.getPerfectKandModel._2.clusterCenters

    // 数据变换
    val zip = clusterCount.zip(clusterCenters.map(_.toArray))

    // RDD化
    val zipRDD = sc.parallelize(zip)

    // 转换函数
    //  val y= zipRDD.first
    //  f(y)
    def f(y: ((Int, Int), Array[Double])) = {
         val part1 = s"${(y._1)._1},${(y._1)._2}"
         val part2 = (y._2).foldLeft(part1)(_+","+_)

         part2    
    }
    // 执行转换函数
    val distData = zipRDD.map(y => f(y))

    // ------------------------------------------------------------------------
    // 写入文件
    // 取得任务的开始日期
    val dateString = clusterInfo.account.getBeginDateString()
    val perfectK = clusterInfo.account.getPerfectKandModel._1

    // 文件路径
    val filepath = "/user/spark/clustering/" + dateString
    val filename = taskName + "_kmeans_" + perfectK + "_cluster"
    val hdfsClusterInfoPath = filepath + "/" + filename

    // 写入文件
    distData.saveAsTextFile(hdfsClusterInfoPath)

    // ------------------------------------------------------------------------
    // 函数返回值
    Tuple2(hdfsClusterInfoPath, clusterInfo)
}

// ****************************************************************************
// 函数: 聚类并分配簇标号
// ****************************************************************************
//  独立进行聚类并分群
//    // maxIterations :    当前没有生效
def ClusteringUserInfo_Standalone(dataForModel: RDD[Vector], dataIndexed: RDD[Vector], k:Int, maxIterations: Int = 20) = {
    // ------------------------------------------------------------------------
    // 执行聚类
    val parKTriangle = new KTriangle(k,k+1)
    val resultAccount = evalWSSSEOfK(dataForModel, parKTriangle.low, maxIterations, Account(0,0,Nil))

    // 聚类结果写入文件
    // val resultWriteHDFS = writeAccount2HDFS(resultAccount,0)
    // ------------------------------------------------------------------------
    // 分类统计
    val model = resultAccount.metricList(0).model
    // 数据所属的簇
    val predict = model.predict(dataIndexed)
    // 类wordcount: 得到各个簇的成员数量
    val clusterCount = predict.map(x=>(x,1)).reduceByKey(_+_).collect().sorted

    // ------------------------------------------------------------------------
    // 函数返回值
    new ClusterInfo(resultAccount, clusterCount, predict)
}

// ****************************************************************************
// 函数: 利用最佳K的 KMeansModel进行聚类,即从Account中获得最佳模型
// ****************************************************************************
def ClusteringUserInfo_FromAccount(dataIndexed: RDD[Vector], account:Account) = {
    // ------------------------------------------------------------------------
    // 取得任务的开始日期,最佳K，最佳K对应的模型
    val dateString = account.getBeginDateString()
    val perfectK = account.getPerfectKandModel._1
    val perfectModel = account.getPerfectKandModel._2
    // ------------------------------------------------------------------------
    // 分类统计
    val model = perfectModel
    // 数据所属的簇
    val predict = model.predict(dataIndexed)
    // 类wordcount: 得到各个簇的成员数量
    val clusterCount = predict.map(x=>(x,1)).reduceByKey(_+_).collect().sorted

    // ------------------------------------------------------------------------
    // 函数返回值
    new ClusterInfo(account, clusterCount,predict)
}


