# -----------------------------------------------------------------------------
# 基本图形化展现
#	过程度量数据:	mymetrics	
#	最佳k中心点:	myclustercenters vpm vpm.v	
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
#   修改 中的这两个语句
#				dataSetID <- "s98_L2k20_clusterCenters"  # s01
#				filesVector <- filesVector_s98_standalone_L2 #filesVector_s98 # filesVector_s01
#   创建图形输出目录    graphys/s98_L2k20_clusterCenters
#   执行 - windows版本
# 	source("J:/home/hadoop/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-kmeans-v1-for-standalone-L2.R")
# 	source("J:/home/hadoop/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/kmeans-basic-visualizing-of-clusters-v1-for-standalone-L2-winpatch.R")
# -----------------------------------------------------------------------------
# 加载包
library(ggplot2)

# -----------------------------------------------------------------------------
# 当前任务名称 , curTaskName

# *****************************************************************************
# 	函数定义
# *****************************************************************************
# -----------------------------------------------------------------------------
# 图形名字函数
getImageFile <- function(desc, curTaskName, filetype="pdf", subdir=dataSetID) {
	#rootFilePathOfImage <- "~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/graphys/"
	# linux版本
	rootFilePathOfImage <- stringr::str_c("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/graphys/",dataSetID, "/")
	# windows版本
	rootFilePathOfImage <- stringr::str_c("F:/工作目录2014/职场之数据挖掘/数据挖掘_任务.电力/DM之电力.辽宁/报告.201408/样本分析-第二层聚类/",dataSetID, "/")

	fileHead <- paste(rootFilePathOfImage, curTaskName, sep="")
	filenameOfImage <- paste(fileHead, desc, filetype, sep=".")
	return (filenameOfImage)	# 返回值必须加上括号？
}

# 对 metrics 进行图形化展现
visualizingMetrics <- function(fileDataOfMetrics, curTaskName) {
	cat("-----------------------------------------------------------------------------\n")
	cat("\t visualizingCluster 函数还未实现啊！！！！\n")
	cat("-----------------------------------------------------------------------------\n")
}

# 对 cluster clusterInfo 进行图形化展现
visualizingCluster <- function(fileDataOfCluster, curTaskName) {
	cat("-----------------------------------------------------------------------------\n")
	cat("\t >>>>> 对 cluster clusterInfo 进行图形化展现 \n")

	# -----------------------------------------------------------------------------
	# 簇中心的月用电量
	vpm.v <- fileDataOfCluster[[2]]
	curdata <- vpm.v
	curdata$clusterID <- as.factor(curdata$clusterID)
	curdata$counter_linesize <- as.factor(as.integer(sqrt(sqrt(sqrt(curdata$counter)))/2)) # as.factor(as.integer(sqrt(sqrt(curdata$counter))/2))
	curdata$ym <- ordered(curdata$ym)
	#str(curdata)

	# ---------------------------
	# 折线图
	p <- ggplot(curdata, aes(x=ym, y=value, group=clusterID))
	p <- p + xlab("month") + ylab("volume center")
	p + geom_line()
	#p + geom_line(aes(colour = clusterID))
	
	# 线的大小是簇数量的函数
	p + geom_line(aes(colour = clusterID, size= counter_linesize))
	ggsave(getImageFile("(2.1)簇中心的月用电量折线图_簇数量函数作为线粗细", curTaskName), width = 40, height = 20)
	
	# -----
	p <- ggplot(curdata, aes(x=ym, y=sqrt(value), group=clusterID))
	p <- ggplot(curdata, aes(x=ym, y=sqrt(sqrt(value)), group=clusterID))
	p <- p + xlab("month") + ylab("volume center")
	#p + geom_line(aes(colour = clusterID))
		
	# 线的大小是簇数量的函数
	p + geom_line(aes(colour = clusterID, size= counter_linesize))
	ggsave(getImageFile("(2.2)簇中心的月用电量折线图_sqrt_簇数量函数作为线粗细", curTaskName), width = 40, height = 20)	

	cat("\t 对 cluster clusterInfo 进行图形化展现 <<<<< \n")
	cat("-----------------------------------------------------------------------------\n")
}

# 快捷函数
visualizingCluster_clusterSpecial <- function(fileDataOfCluster, curTaskName) {
	newTaskName <- paste(curTaskName, "_clusterSpecial", sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingCluster(fileDataOfCluster,newTaskName)
}

visualizingCluster_clusterSpecial_Ladder <- function(fileDataOfCluster, curTaskName) {
	newTaskName <- paste(curTaskName, "_clusterSpecial_Ladder", sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingCluster(fileDataOfCluster,newTaskName)
}

visualizingCluster_clusterSpecial_Ts <- function(fileDataOfCluster, curTaskName) {
	newTaskName <- paste(curTaskName, "_clusterSpecial_Ts", sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingCluster(fileDataOfCluster,newTaskName)
}

visualizingCluster_clusterSpecial_NotTsNotLadder <- function(fileDataOfCluster, curTaskName) {
	newTaskName <- paste(curTaskName, "_clusterSpecial_NotTsNotLadder", sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingCluster(fileDataOfCluster,newTaskName)
}

# *****************************************************************************
# 	函数应用
# *****************************************************************************
# GoodM1
curTaskName <- paste(dataSetID, "GoodM1", sep="_")
visualizingCluster_clusterSpecial_Ladder(fileData_GoodM1_clusterSpecial_Ladder, curTaskName)
visualizingCluster_clusterSpecial_Ts(fileData_GoodM1_clusterSpecial_Ts, curTaskName)
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_GoodM1_clusterSpecial_NotTsNotLadder, curTaskName)

# GoodM2
curTaskName <- paste(dataSetID, "GoodM2", sep="_")

visualizingCluster_clusterSpecial_Ladder(fileData_GoodM2_clusterSpecial_Ladder, curTaskName)
visualizingCluster_clusterSpecial_Ts(fileData_GoodM2_clusterSpecial_Ts, curTaskName)
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_GoodM2_clusterSpecial_NotTsNotLadder, curTaskName)

# BadF3
curTaskName <- paste(dataSetID, "BadF3", sep="_")
visualizingCluster_clusterSpecial_Ladder(fileData_BadF3_clusterSpecial_Ladder, curTaskName)
visualizingCluster_clusterSpecial_Ts(fileData_BadF3_clusterSpecial_Ts, curTaskName)
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_BadF3_clusterSpecial_NotTsNotLadder, curTaskName)

# BadF2ExcludeF3
curTaskName <- paste(dataSetID, "BadF2ExcludeF3", sep="_")
visualizingCluster_clusterSpecial_Ladder(fileData_BadF2ExcludeF3_clusterSpecial_Ladder, curTaskName)
visualizingCluster_clusterSpecial_Ts(fileData_BadF2ExcludeF3_clusterSpecial_Ts, curTaskName)
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder, curTaskName)
