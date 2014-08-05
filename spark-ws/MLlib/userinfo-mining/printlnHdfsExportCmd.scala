// ----------------------------------------------------------------------------
// 打印出hdfs导数的脚本
// --------------------------
// 使用方法
//	在spark-shell中执行  (在执行完 execute-s98-v1.scala 之后)
//		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/printlnHdfsExportCmd.scala
// --------------------------
// 求最佳K的
// metrics
val listFiles_metrics_unsorted = List(resultW2HDFS_GoodM1._1._1, resultW2HDFS_GoodM2._1._1, resultW2HDFS_BadF3._1._1, resultW2HDFS_BadF2ExcludeF3._1._1)
val listFiles_metrics_sorted = List(resultW2HDFS_GoodM1._1._2, resultW2HDFS_GoodM2._1._2, resultW2HDFS_BadF3._1._2, resultW2HDFS_BadF2ExcludeF3._1._2)
// clusterCenters
val listFiles_clusterCenters = List(resultW2HDFS_GoodM1._2, resultW2HDFS_GoodM2._2, resultW2HDFS_BadF3._2, resultW2HDFS_BadF2ExcludeF3._2)
// clusterInfo
val listFiles_clusterInfo = List(resultWClusterInfo2HDFS_GoodM1._2, resultWClusterInfo2HDFS_GoodM2._2, resultWClusterInfo2HDFS_BadF3._2, resultWClusterInfo2HDFS_BadF2ExcludeF3._2)

// --------------------------
def getExportCmd(sourceFile:String) = {
	val hdfsCatHead = "hdfs dfs -cat "
	val hdfsCatTail = "/* > "
	
	val hdfsSourceFile = sourceFile
	val fileObj = new java.io.File(sourceFile)
	val localDestFile = fileObj.getName() + ".csv"
	
	val result = s"${hdfsCatHead} ${hdfsSourceFile}${hdfsCatTail} ${localDestFile}"
	result
}
//val sourceFile = "/user/spark/clustering/2014-08-04/S01_GoodM1_kmeans_200_cluster"
//getExportCmd(sourceFile)
// --------------------------
// 函数： 打印出HDFS导出命令
def  printlnExportCmd()
{
	println("// ----------------------------------------------------------------------------")
	println("// 打印出hdfs导数的脚本\n\n")
	println("cd /home/hadoop/workspace_github/hadoop-ws/r-ws/result-data\n")
	listFiles_metrics_unsorted.foreach(x => println(getExportCmd(x)))
	listFiles_metrics_sorted.foreach(x => println(getExportCmd(x)))
	listFiles_clusterCenters.foreach(x => println(getExportCmd(x)))
	listFiles_clusterInfo.foreach(x => println(getExportCmd(x)))
	println("// ----------------------------------------------------------------------------")
}

// ----------------------------------------------------------------------------
// 执行函数
// --------------------------
// 寻找最佳K
// 打印hdfs导出命令
printlnExportCmd()

// ----------------------------------------------------------------------------
// --------------------------
// 手工指定最佳K的
// 在执行完 "任务3： 手工指定最佳K"之后
// clusterInfoSpecial
val listFiles_clusterInfoSpecial = List(resultWClusterInfoSpecial2HDFS_GoodM1._2, resultWClusterInfoSpecial2HDFS_GoodM2._2, resultWClusterInfoSpecial2HDFS_BadF3._2, resultWClusterInfoSpecial2HDFS_BadF2ExcludeF3._2)

// --------------------------
// 函数： 打印出R文件矩阵
def printlnRFileMatrixes()
{
	val listFiles = List("unsorted", "sorted", "clustercenters", "cluster", "cluster0fSpecial")
	val listDataType = List("GoodM1", "GoodM2", "BadF3", "BadF2ExcludeF3")
	val length = listDataType.length
	val indexRange = Range(0,length)
	
	println("// ----------------------------------------------------------------------------")
	println("// 打印出R文件矩阵\n\n")
	for (index <- indexRange)
	{
		val filename1 = (new java.io.File(listFiles_metrics_unsorted(index) + ".csv")).getName()
		val filename2 = (new java.io.File(listFiles_metrics_sorted(index) + ".csv")).getName()
		val filename3 = (new java.io.File(listFiles_clusterCenters(index) + ".csv")).getName()
		val filename4 = (new java.io.File(listFiles_clusterInfo(index) + ".csv")).getName()
		val filename5 = (new java.io.File(listFiles_clusterInfoSpecial(index) + ".csv")).getName()
		
		val sep = "\",  \""
		val line = "    \"" + s"${filename1}${sep}${filename2}${sep}${filename3}${sep}${filename4}${sep}${filename5}" + "\""
		println(line + ",")
	}
	
	println("// ----------------------------------------------------------------------------")
}
// 打印hdfs导出命令
def printlnExportCmdOfSpecial() {
	println("// ----------------------------------------------------------------------------")
	println("// 打印hdfs导出命令\n\n")
	println("cd /home/hadoop/workspace_github/hadoop-ws/r-ws/result-data\n")
	listFiles_clusterInfoSpecial.foreach(x => println(getExportCmd(x)))
	println(" ----------------------------------------------------------------------------")
}
printlnExportCmdOfSpecial()

// 打印R矩阵
printlnRFileMatrixes()
println(" ----------------------------------------------------------------------------")


