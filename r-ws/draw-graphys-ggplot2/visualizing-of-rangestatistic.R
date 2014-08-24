# -----------------------------------------------------------------------------
# 基本图形化展现
#	RangeStatistic
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
#   修改 中的这两个语句
#				dataSetID <- "s01"  # s98
#   创建图形输出目录    s01_RangeStatistic
#   执行 - linux版本
#       source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-rangestatistic.R")
#       source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/visualizing-of-rangestatistic.R")
#   执行 - windows版本
# 	    source("J:/home/hadoop/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-rangestatistic.R")
# 	    source("J:/home/hadoop/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/visualizing-of-rangestatistic.R")
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
	rootFilePathOfImage <- stringr::str_c("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/graphys/",dataSetID, "_RangeStatistic/")
	# windows版本
	# rootFilePathOfImage <- stringr::str_c("F:/工作目录2014/职场之数据挖掘/数据挖掘_任务.电力/DM之电力.辽宁/报告.201408/样本分析-第二层聚类/",dataSetID, "/")

	fileHead <- paste(rootFilePathOfImage, curTaskName, sep="")
	filenameOfImage <- paste(fileHead, desc, filetype, sep=".")
	return (filenameOfImage)	# 返回值必须加上括号？
}

# 对 RangeStatistic 进行图形化展现
visualizingRangeStatistic <- function(fileDataOfRangeStatistic, curTaskName) {
	cat("-----------------------------------------------------------------------------\n")
	cat("\t >>>>> 对RangeStatistic 进行图形化展现 \n")

	# -----------------------------------------------------------------------------
	curdata <- fileDataOfRangeStatistic
	curdata$index <- as.factor(curdata$index)
	curdata$begin <- ordered(curdata$begin)
	#str(curdata)

	# ---------------------------
	# 基于 y 变量的 value
	p <- ggplot(curdata, aes(x=begin, y=count))
	p+ geom_bar(stat="identity")
	#ggsave("draw-graphys-ggplot2/graphys/s98_m1_k19.geom_bar_counter.pdf", width = 7, height = 6.99) 
	ggsave(getImageFile("(1).geom_bar_count", curTaskName), width = 15, height = 6.99)
	
#	p <- ggplot(curdata, aes(x=begin, y=sqrt(count)))
#	p+ geom_bar(stat="identity")
#	ggsave(getImageFile("(2).geom_bar_count_sqrt", curTaskName))
	
#	p <- ggplot(curdata, aes(x=begin, y=sqrt(sqrt(count))))
#	p+ geom_bar(stat="identity")
#	ggsave(getImageFile("(3).geom_bart_count_sqrtsqr", curTaskName))

	cat("\t 对 对RangeStatistic 进行图形化展现 <<<<< \n")
	cat("-----------------------------------------------------------------------------\n")
}

# *****************************************************************************
# 执行可视化
# *****************************************************************************
#library(foreach)

#datasets <- foreach(curdata=datasets, filename=filesVector) %do% {
#    onlyname <- strsplit(filename, "\\.")[[1]][1]
#    
#    onlyname
#    visualizingRangeStatistic(curdata[[1]],onlyname)
#}

for (i in 1:length(filesVector) ) {
    i
    dataitem <- datasets[i]; 
    filenameitem <- filesVector[i]
    onlyname <- strsplit(filenameitem, "\\.")[[1]][1]
    curdata <- dataitem[[1]]
    
    visualizingRangeStatistic(curdata,onlyname)
}

