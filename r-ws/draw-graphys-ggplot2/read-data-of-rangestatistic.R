# -----------------------------------------------------------------------------
# 读取spark分析处理后的数据,供画图程序使用
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
# 		source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-rangestatistic.R")
#
# -----------------------------------------------------------------------------

# *****************************************************************************
# 也可以调整为每次手动选择文件
#	mydata = read.table(file.choose(), header=FALSE, sep=",")
# *****************************************************************************

# FilePath
# dataSetID <- "s01"  # s98
# linux版本
rootFilePathOfIn <- stringr::str_c("~/workspace_github/hadoop-ws/r-ws/result-data/",dataSetID, "_RangeStatistic/")
# windows版本
#rootFilePathOfIn <- stringr::str_c("J:/home/hadoop/workspace_github/hadoop-ws/r-ws/result-data/",dataSetID, "_RangeStatistic/")

# 行与列
dimRows <- c("yearVolume", "yearSplit", "yearMoney")
dimCols <- c("counter1", "counter2", "counter3")
# 文件名
filesVector_s01 <- c(
    "RangeStatistic_s01_yearVolume_counter1.csv",
    "RangeStatistic_s01_yearVolume_counter2.csv",
    "RangeStatistic_s01_yearVolume_counter3.csv",

    "RangeStatistic_s01_yearSplit_counter1.csv",
    "RangeStatistic_s01_yearSplit_counter2.csv",
    "RangeStatistic_s01_yearSplit_counter3.csv",
    
    "RangeStatistic_s01_yearMoney_counter1.csv",
    "RangeStatistic_s01_yearMoney_counter2.csv",
    "RangeStatistic_s01_yearMoney_counter3.csv"
	)
filesVector_s98 <- c(
    "RangeStatistic_s98_yearVolume_counter1.csv",
    "RangeStatistic_s98_yearVolume_counter2.csv",
    "RangeStatistic_s98_yearVolume_counter3.csv",

    "RangeStatistic_s98_yearSplit_counter1.csv",
    "RangeStatistic_s98_yearSplit_counter2.csv",
    "RangeStatistic_s98_yearSplit_counter3.csv",
    
    "RangeStatistic_s98_yearMoney_counter1.csv",
    "RangeStatistic_s98_yearMoney_counter2.csv",
    "RangeStatistic_s98_yearMoney_counter3.csv"
	)

if ("s01" == dataSetID) filesVector <- filesVector_s01
if ("s98" == dataSetID) filesVector <- filesVector_s98

# 构建矩阵
#filesMatrix <- matrix(filesVector, byrow=TRUE, nrow=length(dimRows), ncol=length(dimCols),dimnames=list(dimRows, dimCols))

#str(filesMatrix)

# *****************************************************************************
# 本次要读取的文件名
# *****************************************************************************
#filenameMatrix <- stringr::str_c(rootFilePathOfIn, filesMatrix) # 不会返回 Matrix
filenames <- paste(rootFilePathOfIn, filesVector, sep="")

# *****************************************************************************
# 函数定义
# *****************************************************************************
# -----------------------------------------------------------------------------
# 加载 RangeStatistic 的函数
# 		适用于 _metrics_unsorted 和 _metrics_sorted
loadRangeStatistic <- function(filename) {
	# 读取文件
	filedata = read.table(filename, header=TRUE, sep=",")	 # read table file ,首行无列名(header=FALSE)
	# filedata
	
	# 为 filedata 设置变量标签
	labels_filedata <- c("index", "begin", "end", "count")
	names(filedata) <- labels_filedata
	
	# 将index 转换为factor
	# filedata$index <- as.factor(filedata$index)
	
	return (filedata)
}

# *****************************************************************************
# 加载数据到变量中
# *****************************************************************************
library(foreach)

datasets <- foreach(name=filenames) %do% loadRangeStatistic(name)

