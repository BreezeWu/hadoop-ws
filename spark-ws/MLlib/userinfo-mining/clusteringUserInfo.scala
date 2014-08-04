// ----------------------------------------------------------------------------
//     Clustering Users
//    对用户进行聚类,并给出每一个用户的ClusterID
//    input:    perfectK
// ----------------------------------------------------------------------------
/*
    使用方法
    1. 加载数据 
    (1)样本数据
        :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s01.scala
    (2)全量数据
        :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s98.scala

    2. 加载函数
    聚类函数
        :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/tryKMeansSmart.scala
    根据聚类模型分配clusterID
        :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/clusteringUserInfo.scala

    3. 运行封装函数
        val perfectK = 19
        val maxIterations = 20    // 该参数当前无效
        val taskName = "S01_M2"
        val resultClusterInfo = ClusteringUserInfo(parsedDataM2, perfectK, maxIterations, taskName) 

	// 写入文件
	val resultWriteHDFS = writeClusterInfo2HDFS(resultClusterInfo, taskName)  

	// 最佳K 和 最佳K的model
	val account = resultClusterInfo.account.getPerfectKandModel() 

	// linux下查看执行结果 注意替换"S01_M2"
	hdfs dfs -cat hdfs://master-hadoop:9000/user/spark/clustering/2014-07-31/S01_M2_kmeans_19_cluster/\*

	// 如果提示文件已经存在,可给taskName一个新名字
*/

// ---------------------------------------------------------------------------------------------------------------------------
// 辅助函数
case class ClusterInfo(
    account:Account,
    clusterCount: Array[(Int, Int)]
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
    Tuple2(clusterInfo, hdfsClusterInfoPath)
}

// ---------------------------------------------------------------------------------------------------------------------------
// 函数: 聚类并分配簇标号
//    // maxIterations :    当前没有生效
def ClusteringUserInfo(data: RDD[Vector], k:Int, maxIterations: Int = 20) = {
    // ------------------------------------------------------------------------
    // 执行聚类
    val parKTriangle = new KTriangle(k,k+1)
    val resultAccount = evalWSSSEOfK(data, parKTriangle.low, maxIterations, Account(0,0,Nil))

    // 聚类结果写入文件
    // val resultWriteHDFS = writeAccount2HDFS(resultAccount,0)
    // ------------------------------------------------------------------------
    // 分类统计
    val model = resultAccount.metricList(0).model
    // 数据所属的簇
    val predict = model.predict(data)
    // 类wordcount: 得到各个簇的成员数量
    val clusterCount = predict.map(x=>(x,1)).reduceByKey(_+_).collect().sorted

    // ------------------------------------------------------------------------
    // 函数返回值
    new ClusterInfo(resultAccount, clusterCount)
}

// ---------------------------------------------------------------------------------------------------------------------------
// 函数: 从Account中获得最佳模型
def getClusteringUserInfoFromAccount(data: RDD[Vector], account:Account) = {
    // ------------------------------------------------------------------------
    // 取得任务的开始日期,最佳K，最佳K对应的模型
    val dateString = account.getBeginDateString()
    val perfectK = account.getPerfectKandModel._1
    val perfectModel = account.getPerfectKandModel._2
    // ------------------------------------------------------------------------
    // 分类统计
    val model = perfectModel
    // 数据所属的簇
    val predict = model.predict(data)
    // 类wordcount: 得到各个簇的成员数量
    val clusterCount = predict.map(x=>(x,1)).reduceByKey(_+_).collect().sorted

    // ------------------------------------------------------------------------
    // 函数返回值
    new ClusterInfo(account, clusterCount)
}


