# -----------------------------------------------------------------------------
# 基本图形化展现	这个是在windows上执行
#	过程度量数据:	mymetrics	
#	最佳k中心点:	myclustercenters vpm vpm.v	
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
#   修改 中的这两个语句
#				dataSetID <- "s98_L2k20"
#				filesVector <- filesVector_s98_standalone_L2 #filesVector_s98 # filesVector_s01
#
#   创建图形输出目录    graphys/s98_L2k20_clusterCenters
#
#   执行
#		source("J:/home/hadoop/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-kmeans-v1-for-standalone-L2-sample.R")
# 	source("J:/home/hadoop/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/kmeans-basic-visualizing-of-clusters-v1-for-standalone-L2-sample.R")
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
	rootFilePathOfImage <- stringr::str_c("F:/工作目录2014/职场之数据挖掘/数据挖掘_任务.电力/DM之电力.辽宁/报告.201408/样本分析-第二层聚类/",dataSetID, "_")
	
	fileHead <- paste(rootFilePathOfImage, curTaskName, sep="")
	filenameOfImage <- paste(fileHead, desc, filetype, sep=".")
	return (filenameOfImage)	# 返回值必须加上括号？
}

# 对 cluster clusterInfo 进行图形化展现
visualizingCluster <- function(fileDataOfCluster, curTaskName) {
	cat("-----------------------------------------------------------------------------\n")
	cat("\t >>>>> 对 cluster clusterInfo 进行图形化展现 \n")

	# -----------------------------------------------------------------------------
	# 用户标识信息
	vpm <- fileDataOfCluster[[1]]
	curdata <- vpm[c("cons_no", "cons_id")]	
	curdata$cons_id <- as.factor(curdata$cons_id)
	curdata
	# ---------------------------
	# -----------------------------------------------------------------------------
	# 月用电量
	vpm.v <- fileDataOfCluster[[2]]
	curdata <- vpm.v
	curdata$cons_id <- as.factor(curdata$cons_id)
	curdata$ym <- ordered(curdata$ym)
	#str(curdata)
	
	curdata[c("cons_id","value")]
	data.frame(curdata$cons_id, curdata$ym, sqrt(curdata$value))
	# ---------------------------
	# 折线图
	p <- ggplot(curdata, aes(x=ym, y=value, group=cons_id))
	#p <- p + xlab("年月") + ylab("样本数据的用电量")			# 中文有问题
	p <- p + xlab("month") + ylab("volume center")
	p + geom_line()
	p + geom_line(aes(colour = cons_id))
	#p + geom_line(aes(colour = cons_id, size=cons_id))
	#p + geom_line(aes(colour = cons_id, size= as.integer(cons_id) %% 5))
	ggsave(getImageFile("(2.1)样本数据的月用电量折线图", curTaskName), width = 10, height = 8)
	
	p <- ggplot(curdata, aes(x=ym, y=sqrt(value), group=cons_id))
	#p <- p + xlab("年月") + ylab("样本数据的用电量")			# 中文有问题
	p <- p + xlab("month") + ylab("volume center")
	p + geom_line(aes(colour = cons_id))
	ggsave(getImageFile("(2.2)样本数据的月用电量折线图_sqrt", curTaskName), width = 10, height = 8)
	# ---------------------------
	# 线图
	p <- ggplot(curdata, aes(factor(ym), value))
	p <- p + xlab("年月") + ylab("样本数据的用电量")
	p + geom_boxplot()
	ggsave(getImageFile("(3.1)geom_boxplot", curTaskName), width = 10, height = 8)
	
	p <- ggplot(curdata, aes(factor(ym), sqrt(value)))
	p <- p + xlab("年月") + ylab("样本数据的用电量")
	p + geom_boxplot()
	ggsave(getImageFile("(3.2)geom_boxplot_sqrt", curTaskName), width = 10, height = 8)
	# ---------------------------
	# 平行坐标图

	
	cat("\t 对 cluster clusterInfo 进行图形化展现 <<<<< \n")
	cat("-----------------------------------------------------------------------------\n")
}

# 快捷函数
visualizingCluster_clusterSpecial <- function(fileDataOfCluster, curTaskName) {
	newTaskName <- paste(curTaskName, "_clusterSpecial", sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingCluster(fileDataOfCluster,newTaskName)
}

visualizingCluster_clusterSpecial_Ladder <- function(fileDataOfCluster, curTaskName, sampleID) {
	newTaskName <- paste(curTaskName, "_Ladder_sample_", sampleID, sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingCluster(fileDataOfCluster,newTaskName)
}

visualizingCluster_clusterSpecial_Ts <- function(fileDataOfCluster, curTaskName, sampleID) {
	newTaskName <- paste(curTaskName, "_Ts_sample_", sampleID, sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingCluster(fileDataOfCluster,newTaskName)
}

visualizingCluster_clusterSpecial_NotTsNotLadder <- function(fileDataOfCluster, curTaskName, sampleID) {
	newTaskName <- paste(curTaskName, "_NotTsNotLadder_sample_", sampleID, sep="")
	cat("\t --------- \t ", newTaskName, "\n")
	visualizingCluster(fileDataOfCluster,newTaskName)
}

# *****************************************************************************
# 	函数应用
# *****************************************************************************
# GoodM1
curTaskName <- paste(dataSetID, "GoodM1", sep="_")
# 阶梯
visualizingCluster_clusterSpecial_Ladder(fileData_GoodM1_clusterSpecial_Ladder_s1, curTaskName, "s1")
visualizingCluster_clusterSpecial_Ladder(fileData_GoodM1_clusterSpecial_Ladder_s2, curTaskName, "s2")
visualizingCluster_clusterSpecial_Ladder(fileData_GoodM1_clusterSpecial_Ladder_s3, curTaskName, "s3")
visualizingCluster_clusterSpecial_Ladder(fileData_GoodM1_clusterSpecial_Ladder_s4, curTaskName, "s4")
# 分时
visualizingCluster_clusterSpecial_Ts(fileData_GoodM1_clusterSpecial_Ts_s1, curTaskName, "s1")
visualizingCluster_clusterSpecial_Ts(fileData_GoodM1_clusterSpecial_Ts_s2, curTaskName, "s2")
visualizingCluster_clusterSpecial_Ts(fileData_GoodM1_clusterSpecial_Ts_s3, curTaskName, "s3")
visualizingCluster_clusterSpecial_Ts(fileData_GoodM1_clusterSpecial_Ts_s4, curTaskName, "s4")
# 既不是阶梯也不是分时
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_GoodM1_clusterSpecial_NotTsNotLadder_s1, curTaskName, "s1")
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_GoodM1_clusterSpecial_NotTsNotLadder_s2, curTaskName, "s2")
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_GoodM1_clusterSpecial_NotTsNotLadder_s3, curTaskName, "s3")

# GoodM2
curTaskName <- paste(dataSetID, "GoodM2", sep="_")
# 阶梯
visualizingCluster_clusterSpecial_Ladder(fileData_GoodM2_clusterSpecial_Ladder_s1, curTaskName, "s1")
visualizingCluster_clusterSpecial_Ladder(fileData_GoodM2_clusterSpecial_Ladder_s2, curTaskName, "s2")
visualizingCluster_clusterSpecial_Ladder(fileData_GoodM2_clusterSpecial_Ladder_s3, curTaskName, "s3")
# 分时
visualizingCluster_clusterSpecial_Ts(fileData_GoodM2_clusterSpecial_Ts_s0, curTaskName, "s0")
visualizingCluster_clusterSpecial_Ts(fileData_GoodM2_clusterSpecial_Ts_s2, curTaskName, "s2")
visualizingCluster_clusterSpecial_Ts(fileData_GoodM2_clusterSpecial_Ts_s5, curTaskName, "s5")
visualizingCluster_clusterSpecial_Ts(fileData_GoodM2_clusterSpecial_Ts_s17, curTaskName, "s17")
# 既不是阶梯也不是分时
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_GoodM2_clusterSpecial_NotTsNotLadder_c7, curTaskName, "c7")
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_GoodM2_clusterSpecial_NotTsNotLadder_c19, curTaskName, "c19")

# BadF2ExcludeF3
curTaskName <- paste(dataSetID, "BadF2ExcludeF3", sep="_")
# 阶梯
visualizingCluster_clusterSpecial_Ladder(fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c0, curTaskName, "c0")
visualizingCluster_clusterSpecial_Ladder(fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c11, curTaskName, "c11")
visualizingCluster_clusterSpecial_Ladder(fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c12, curTaskName, "c12")
visualizingCluster_clusterSpecial_Ladder(fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c16, curTaskName, "c16")
# 分时
visualizingCluster_clusterSpecial_Ts(fileData_BadF2ExcludeF3_clusterSpecial_Ts_c2, curTaskName, "c2")
visualizingCluster_clusterSpecial_Ts(fileData_BadF2ExcludeF3_clusterSpecial_Ts_c4, curTaskName, "c4")
visualizingCluster_clusterSpecial_Ts(fileData_BadF2ExcludeF3_clusterSpecial_Ts_c11, curTaskName, "c11")
visualizingCluster_clusterSpecial_Ts(fileData_BadF2ExcludeF3_clusterSpecial_Ts_c13, curTaskName, "c13")
# 既不是阶梯也不是分时
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c0, curTaskName, "c0")
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c3, curTaskName, "c3")
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c7, curTaskName, "c7")
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c13, curTaskName, "c13")

# BadF3
curTaskName <- paste(dataSetID, "BadF3", sep="_")
# 阶梯
visualizingCluster_clusterSpecial_Ladder(fileData_BadF3_clusterSpecial_Ladder_c2, curTaskName, "c2")
visualizingCluster_clusterSpecial_Ladder(fileData_BadF3_clusterSpecial_Ladder_c3, curTaskName, "c3")
visualizingCluster_clusterSpecial_Ladder(fileData_BadF3_clusterSpecial_Ladder_c6, curTaskName, "c6")
visualizingCluster_clusterSpecial_Ladder(fileData_BadF3_clusterSpecial_Ladder_c15, curTaskName, "c15")
# 分时
visualizingCluster_clusterSpecial_Ts(fileData_BadF3_clusterSpecial_Ts_c0, curTaskName, "c0")
visualizingCluster_clusterSpecial_Ts(fileData_BadF3_clusterSpecial_Ts_c8, curTaskName, "c8")
visualizingCluster_clusterSpecial_Ts(fileData_BadF3_clusterSpecial_Ts_c16, curTaskName, "c16")
visualizingCluster_clusterSpecial_Ts(fileData_BadF3_clusterSpecial_Ts_c17, curTaskName, "c17")
# 既不是阶梯也不是分时
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_BadF3_clusterSpecial_NotTsNotLadder_c0, curTaskName, "c0")
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_BadF3_clusterSpecial_NotTsNotLadder_c1, curTaskName, "c1")
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_BadF3_clusterSpecial_NotTsNotLadder_c12, curTaskName, "c12")
visualizingCluster_clusterSpecial_NotTsNotLadder(fileData_BadF3_clusterSpecial_NotTsNotLadder_c17, curTaskName, "c17")
