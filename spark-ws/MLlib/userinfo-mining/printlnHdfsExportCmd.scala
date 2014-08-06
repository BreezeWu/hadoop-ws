// ----------------------------------------------------------------------------
// 打印出hdfs导数的脚本 以及  打印出R文件矩阵
// --------------------------
// 使用方法
//	在spark-shell中执行  (在执行完 execute-tryKMeansSmart-ClusterCount.scala 之后)
//		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/printlnHdfsExportCmd.scala
// --------------------------

﻿// ----------------------------------------------------------------------------
// 任务1： 寻找最佳K
// metrics
val listFiles_metrics_unsorted = List(resultW2HDFS_GoodM1._1._1, resultW2HDFS_GoodM2._1._1, resultW2HDFS_BadF3._1._1, resultW2HDFS_BadF2ExcludeF3._1._1)
val listFiles_metrics_sorted = List(resultW2HDFS_GoodM1._1._2, resultW2HDFS_GoodM2._1._2, resultW2HDFS_BadF3._1._2, resultW2HDFS_BadF2ExcludeF3._1._2)
// clusterCenters
val listFiles_clusterCenters = List(resultW2HDFS_GoodM1._2, resultW2HDFS_GoodM2._2, resultW2HDFS_BadF3._2, resultW2HDFS_BadF2ExcludeF3._2)

// 任务2： 计算簇数量（方式一，利用任务1的计算结果获得最佳K和最佳模型)
// ClusterCountInfo
val listFiles_ClusterCountInfo = List(resultWClusterCountInfo2HDFS_GoodM1._1, resultWClusterCountInfo2HDFS_GoodM2._1, resultWClusterCountInfo2HDFS_BadF3._1, resultWClusterCountInfo2HDFS_BadF2ExcludeF3._1)

// 任务2： 计算簇数量（方式二，手工指定最佳K，重新计算模型，然后...）
val listFiles_ClusterCountInfo_Special = List(resultWClusterCountInfo2HDFS_Special_GoodM1._1, resultWClusterCountInfo2HDFS_Special_GoodM2._1, resultWClusterCountInfo2HDFS_Special_BadF3._1, resultWClusterCountInfo2HDFS_Special_BadF2ExcludeF3._1)

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
    println(s"mkdir /home/hadoop/workspace_github/hadoop-ws/r-ws/result-data/${taskNamePre}")
	println(s"cd /home/hadoop/workspace_github/hadoop-ws/r-ws/result-data/${taskNamePre}\n")
	listFiles_metrics_unsorted.foreach(x => println(getExportCmd(x)))
	listFiles_metrics_sorted.foreach(x => println(getExportCmd(x)))
	listFiles_clusterCenters.foreach(x => println(getExportCmd(x)))
	listFiles_ClusterCountInfo.foreach(x => println(getExportCmd(x)))
	listFiles_ClusterCountInfo_Special.foreach(x => println(getExportCmd(x)))
	println("// ----------------------------------------------------------------------------")
}

// ----------------------------------------------------------------------------
// 执行函数
// --------------------------
// 打印hdfs导出命令
printlnExportCmd()

// ----------------------------------------------------------------------------
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
		val filename4 = (new java.io.File(listFiles_ClusterCountInfo(index) + ".csv")).getName()
		val filename5 = (new java.io.File(listFiles_ClusterCountInfo_Special(index) + ".csv")).getName()
		
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
	listFiles_ClusterCountInfo_Special.foreach(x => println(getExportCmd(x)))
	println(" ----------------------------------------------------------------------------")
}
//printlnExportCmdOfSpecial()

// 打印R矩阵
printlnRFileMatrixes()
println(" ----------------------------------------------------------------------------")


