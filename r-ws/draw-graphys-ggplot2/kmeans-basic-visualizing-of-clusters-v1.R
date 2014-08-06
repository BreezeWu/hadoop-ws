# -----------------------------------------------------------------------------
# 基本图形化展现
#	过程度量数据:	mymetrics	
#	最佳k中心点:	myclustercenters vpm vpm.v	
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
#   修改 中的这两个语句
#				dataSetID <- "s98"  # s01
#				filesVector <- filesVector_s98 # filesVector_s01
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-kmeans-v1.R")
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/kmeans-basic-visualizing-of-clusters-v1.R")
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
	rootFilePathOfImage <- stringr::str_c("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/graphys/",dataSetID, "/")
	
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
	# clusterID 及其 数量
	vpm <- fileDataOfCluster[[1]]
	curdata <- vpm[c("clusterID", "counter")]	
	curdata$clusterID <- as.factor(curdata$clusterID)
	curdata
	# ---------------------------
	# 基于 y 变量的 value
	p <- ggplot(curdata, aes(x=clusterID, y=counter))
	p+ geom_bar(stat="identity")
	#ggsave("draw-graphys-ggplot2/graphys/s98_m1_k19.geom_bar_counter.pdf", width = 7, height = 6.99) 
	ggsave(getImageFile("(1.1)geom_bar_counter", curTaskName))
	
	p <- ggplot(curdata, aes(x=clusterID, y=sqrt(counter)))
	p+ geom_bar(stat="identity")
	ggsave(getImageFile("(1.2)geom_bar_counter_sqrt", curTaskName))
	
	p <- ggplot(curdata, aes(x=clusterID, y=sqrt(sqrt(counter))))
	p+ geom_bar(stat="identity")
	ggsave(getImageFile("(1.3)geom_bart_counter_sqrtsqr", curTaskName))
	
	# ---------------------------
	# 基于 y 变量的 统计次数
	# 数据中不能指定 y
	p <- ggplot(curdata, aes(x=clusterID))	
	p+ geom_bar(stat="bin")	# p+ geom_bar()
	
	# -----------------------------------------------------------------------------
	# 簇中心的月用电量
	vpm.v <- fileDataOfCluster[[2]]
	curdata <- vpm.v
	curdata$clusterID <- as.factor(curdata$clusterID)
	curdata$ym <- ordered(curdata$ym)
	#str(curdata)
	
	curdata[c("clusterID","value")]
	data.frame(curdata$clusterID, curdata$ym, sqrt(curdata$value))
	# ---------------------------
	# 折线图
	p <- ggplot(curdata, aes(x=ym, y=value, group=clusterID))
	#p <- p + xlab("年月") + ylab("簇中心的用电量")			# 中文有问题
	p <- p + xlab("month") + ylab("volume center")
	p + geom_line()
	p + geom_line(aes(colour = clusterID))
	#p + geom_line(aes(colour = clusterID, size=clusterID))
	#p + geom_line(aes(colour = clusterID, size= as.integer(clusterID) %% 5))
	ggsave(getImageFile("(2.1)簇中心的月用电量折线图", curTaskName), width = 10, height = 8)
	
	p <- ggplot(curdata, aes(x=ym, y=sqrt(value), group=clusterID))
	#p <- p + xlab("年月") + ylab("簇中心的用电量")			# 中文有问题
	p <- p + xlab("month") + ylab("volume center")
	p + geom_line(aes(colour = clusterID))
	ggsave(getImageFile("(2.2)簇中心的月用电量折线图_sqrt", curTaskName), width = 10, height = 8)
	# ---------------------------
	# 线图
	p <- ggplot(curdata, aes(factor(ym), value))
	p <- p + xlab("年月") + ylab("簇中心的用电量")
	p + geom_boxplot()
	ggsave(getImageFile("(3.1)geom_boxplot", curTaskName))
	
	p <- ggplot(curdata, aes(factor(ym), sqrt(value)))
	p <- p + xlab("年月") + ylab("簇中心的用电量")
	p + geom_boxplot()
	ggsave(getImageFile("(3.2)geom_boxplot_sqrt", curTaskName))
	# ---------------------------
	# 平行坐标图

	
	cat("\t 对 cluster clusterInfo 进行图形化展现 <<<<< \n")
	cat("-----------------------------------------------------------------------------\n")
}

# 快捷函数
visualizingMetrics_unsorted <- function(fileDataOfMetrics, curTaskName) {
	newTaskName <- paste(curTaskName, "_metrics_unsorted", sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingMetrics(fileDataOfMetrics,newTaskName)
}
visualizingMetrics_sorted <- function(fileDataOfMetrics, curTaskName) {
	newTaskName <- paste(curTaskName, "_metrics_sorted", sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingMetrics(fileDataOfMetrics,newTaskName)
}
visualizingCluster_cluster <- function(fileDataOfCluster, curTaskName) {
	newTaskName <- paste(curTaskName, "_cluster", sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingCluster(fileDataOfCluster,newTaskName)
}
visualizingCluster_clusterSpecial <- function(fileDataOfCluster, curTaskName) {
	newTaskName <- paste(curTaskName, "_clusterSpecial", sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingCluster(fileDataOfCluster,newTaskName)
}

# *****************************************************************************
# 	函数应用
# *****************************************************************************
# GoodM1
#curTaskName <- "s98_GoodM1"
curTaskName <- paste(dataSetID, "GoodM1", sep="_")
visualizingMetrics_unsorted(fileData_GoodM1_metrics_unsorted, curTaskName)
visualizingMetrics_sorted(fileData_GoodM1_metrics_sorted, curTaskName)
#visualizingClusterCenters(...)
visualizingCluster_cluster(fileData_GoodM1_cluster, curTaskName)
visualizingCluster_clusterSpecial(fileData_GoodM1_clusterSpecial, curTaskName)

# GoodM2
curTaskName <- paste(dataSetID, "GoodM2", sep="_")
visualizingMetrics_unsorted(fileData_GoodM2_metrics_unsorted, curTaskName)
visualizingMetrics_sorted(fileData_GoodM2_metrics_sorted, curTaskName)
#visualizingClusterCenters(...)
visualizingCluster_cluster(fileData_GoodM2_cluster, curTaskName)
visualizingCluster_clusterSpecial(fileData_GoodM2_clusterSpecial, curTaskName)

# BadF3
curTaskName <- paste(dataSetID, "BadF3", sep="_")
visualizingMetrics_unsorted(fileData_BadF3_metrics_unsorted, curTaskName)
visualizingMetrics_sorted(fileData_BadF3_metrics_sorted, curTaskName)
#visualizingClusterCenters(...)
visualizingCluster_cluster(fileData_BadF3_cluster, curTaskName)
visualizingCluster_clusterSpecial(fileData_BadF3_clusterSpecial, curTaskName)

# BadF2ExcludeF3
curTaskName <- paste(dataSetID, "BadF2ExcludeF3", sep="_")
visualizingMetrics_unsorted(fileData_BadF2ExcludeF3_metrics_unsorted, curTaskName)
visualizingMetrics_sorted(fileData_BadF2ExcludeF3_metrics_sorted, curTaskName)
#visualizingClusterCenters(...)
visualizingCluster_cluster(fileData_BadF2ExcludeF3_cluster, curTaskName)
visualizingCluster_clusterSpecial(fileData_BadF2ExcludeF3_clusterSpecial, curTaskName)
