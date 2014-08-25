# -----------------------------------------------------------------------------
# 读取spark分析处理后的数据,供画图程序使用
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
# 		source("J:/home/hadoop/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-kmeans-v1-for-standalone-L2-sample.R")
# 得到如下数据对象
#	1. 数据集 GoodM1
#	2. 数据集 GoodM2
#	3. 数据集 BadF2ExcludeF3
#	4. 数据集 BadF3
#
#  特别注意：
#  loadSampleData	:	加载 SampleData 的函数, 但只适用于样本数据
# -----------------------------------------------------------------------------

# *****************************************************************************
# 也可以调整为每次手动选择文件
#	mydata = read.table(file.choose(), header=FALSE, sep=",")
# *****************************************************************************

# FilePath
dataSetID <- "s98_L2k20"
rootFilePathOfIn <- "F:/工作目录2014/职场之数据挖掘/数据挖掘_任务.电力/DM之电力.辽宁/报告.201408/样本分析-第二层聚类/"

# 行: 数据集+单月/双月
#dimRows <- c("s98_GoodM1", "s98_GoodM2", "s98_BadF2ExcludeF3", "s98_BadF3")
dimCols <- c("sample1", "sample2", "sample3", "sample4")	# , "sample5"
# 文件名
filesVector_s98 <- c(
	#s98_GoodM1
		# Ladder
    "s98_L2k20_GoodM1_Ladder_sampledata_200_c7.csv",
    "s98_L2k20_GoodM1_Ladder_sampledata_200_c11.csv",
    "s98_L2k20_GoodM1_Ladder_sampledata_200_c12.csv",
    "s98_L2k20_GoodM1_Ladder_sampledata_200_c19.csv",
    # Ts
    "s98_L2k20_GoodM1_Ts_sampledata_c0.csv",
    "s98_L2k20_GoodM1_Ts_sampledata_c13.csv",
    "s98_L2k20_GoodM1_Ts_sampledata_c18.csv",
    "s98_L2k20_GoodM1_Ts_sampledata_c19.csv",
    # NotTsNotLadder
    "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_c0.csv",
    "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_c2.csv",
    "s98_L2k20_GoodM1_NotTsNotLadder_sampledata_c9.csv",
    ".csv",
    
	#s98_GoodM2
		# Ladder
    "s98_L2k20_GoodM2_Ladder_sampledata_c1.csv",
    "s98_L2k20_GoodM2_Ladder_sampledata_c5.csv",
    "s98_L2k20_GoodM2_Ladder_sampledata_c17.csv",
    ".csv",
    # Ts
    "s98_L2k20_GoodM2_Ts_sampledata_c0.csv",
    "s98_L2k20_GoodM2_Ts_sampledata_c2.csv",
    "s98_L2k20_GoodM2_Ts_sampledata_c5.csv",
    "s98_L2k20_GoodM2_Ts_sampledata_c17.csv",
    # NotTsNotLadder
    "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_c7.csv",
    "s98_L2k20_GoodM2_NotTsNotLadder_sampledata_c19.csv",
    ".csv",
    ".csv",
    
	#BadF2ExcludeF3
		# Ladder
    "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_c0.csv",
    "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_c11.csv",
    "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_c12.csv",
    "s98_L2k20_BadF2ExcludeF3_Ladder_sampledata_c16.csv",
    # Ts
    "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_c2.csv",
    "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_c4.csv",
    "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_c11.csv",
    "s98_L2k20_BadF2ExcludeF3_Ts_sampledata_c13.csv",
    # NotTsNotLadder
    "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_c0.csv",
    "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_c3.csv",
    "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_c7.csv",
    "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_sampledata_c13.csv",
    
	#BadF3
		# Ladder
    "s98_L2k20_BadF3_Ladder_sampledata_c2.csv",
    "s98_L2k20_BadF3_Ladder_sampledata_c3.csv",
    "s98_L2k20_BadF3_Ladder_sampledata_c6.csv",
    "s98_L2k20_BadF3_Ladder_sampledata_c15.csv",
    # Ts
    "s98_L2k20_BadF3_Ts_sampledata_c0.csv",
    "s98_L2k20_BadF3_Ts_sampledata_c8.csv",
    "s98_L2k20_BadF3_Ts_sampledata_c16.csv",
    "s98_L2k20_BadF3_Ts_sampledata_c17.csv",
    # NotTsNotLadder
    "s98_L2k20_BadF3_NotTsNotLadder_sampledata_c0.csv",
    "s98_L2k20_BadF3_NotTsNotLadder_sampledata_c1.csv",
    "s98_L2k20_BadF3_NotTsNotLadder_sampledata_c12.csv",
    "s98_L2k20_BadF3_NotTsNotLadder_sampledata_c17.csv"
	)

filesVector <- filesVector_s98	# filesVector_s98_GoodM1

# 构建矩阵
dimRows <- c("s98_GoodM1_Ladder", "s98_GoodM1_Ts", "s98_GoodM1_NotTsNotLadder",
						"s98_GoodM2_Ladder", "s98_GoodM2_Ts", "s98_GoodM2_NotTsNotLadder", 
						"s98_BadF2ExcludeF3_Ladder", "s98_BadF2ExcludeF3_Ts", "s98_BadF2ExcludeF3_NotTsNotLadder", 
						"s98_BadF3_Ladder", "s98_BadF3_Ts", "s98_BadF3_NotTsNotLadder"
						)
filesMatrix <- matrix(filesVector, byrow=TRUE, nrow=length(dimRows), ncol=length(dimCols),dimnames=list(dimRows, dimCols))

#str(filesMatrix)

# *****************************************************************************
# 函数定义
# *****************************************************************************
# -----------------------------------------------------------------------------
# 加载 SampleData 的函数
loadSampleData <- function(filename) {
	# 读取文件
	mysampledata = read.table(filename, header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
	#str(mysampledata)

	# -----------------------------------------------------------------------------
	# vpm vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312,vpm201401,vpm201402,vpm201403,vpm201404,vpm201405,vpm201406
	# 取前面 2+12 列,即从 201301~201312这12个月的月用点量
	vpm <- mysampledata[, c(1:(3+12))]
	
	# 为vpm设置变量标签
	newcolnames <- c("cons_id", "cons_no", "sep", "201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312")
	names(vpm) <- c(newcolnames)	# names(vpm.clusterID) <- c(newcolnames,"clusterID")
	rm(newcolnames)
	
	# -----------------------------------------------------------------------------
	# 横表变纵表
	library(reshape2)
	# 'pointsNum'是没有必要的,但为了保留它作为单独的一列
	# vpm.v <- melt(vpm,  id = c("cons_id", "cons_no", "sep"), variable_name = "ym")	# colnames/ym -> value
	# reshape2 中 variable_name 无效
	vpm.v <- melt(vpm,  id = c("cons_id", "cons_no", "sep"))

	# 为vpm.v设置变量标签
	newcolnames <- c("cons_id", "cons_no", "sep", "ym", "value")
	names(vpm.v) <- c(newcolnames)
	rm(newcolnames)
	
	# 画图的列
	#vpm.v$ym <- as.factor(vpm.v$colnames)
	
	# -----------------------------------------------------------------------------
	#  行列转换
	#	vpm.t	从vpm进行转换
	#vpm.t <- t(vpm)			# 这种方法,值变为了 character vector
	vpm.t <- as.data.frame(t(vpm))	
	
	# 将 行名 成为新列 ym
	#vpm.t <- data.frame(vpm.t, factor(rownames(vpm)))	# 不需要因子化,所以使用下面的语句即可
	
	#vpm.t <- data.frame(vpm.t, rownames(vpm.t))	# 最后附加的列明是rownames.vpm.t. 所以改为下面两句
	ym <- rownames(vpm.t)
	vpm.t <- data.frame(vpm.t, ym)
	
	# 返回值
	mylist <- list(vpm, vpm.v, vpm.t)
	return (mylist)
}

# *****************************************************************************
# 文件名
# *****************************************************************************
# 本次要读取的文件名
# GoodM1
file_GoodM1_clusterSpecial_Ladder_s1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,1])
file_GoodM1_clusterSpecial_Ladder_s2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,2])
file_GoodM1_clusterSpecial_Ladder_s3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,3])
file_GoodM1_clusterSpecial_Ladder_s4 <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,4])

file_GoodM1_clusterSpecial_Ts_s1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,1])
file_GoodM1_clusterSpecial_Ts_s2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,2])
file_GoodM1_clusterSpecial_Ts_s3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,3])
file_GoodM1_clusterSpecial_Ts_s4 <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,4])

file_GoodM1_clusterSpecial_NotTsNotLadder_s1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,1])
file_GoodM1_clusterSpecial_NotTsNotLadder_s2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,2])
file_GoodM1_clusterSpecial_NotTsNotLadder_s3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,3])

# GoodM2
file_GoodM2_clusterSpecial_Ladder_s1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,1])
file_GoodM2_clusterSpecial_Ladder_s2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,2])
file_GoodM2_clusterSpecial_Ladder_s3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,3])

file_GoodM2_clusterSpecial_Ts_s0 <- stringr::str_c(rootFilePathOfIn, filesMatrix[5,1])
file_GoodM2_clusterSpecial_Ts_s2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[5,2])
file_GoodM2_clusterSpecial_Ts_s5 <- stringr::str_c(rootFilePathOfIn, filesMatrix[5,3])
file_GoodM2_clusterSpecial_Ts_s17 <- stringr::str_c(rootFilePathOfIn, filesMatrix[5,4])

file_GoodM2_clusterSpecial_NotTsNotLadder_c7 <- stringr::str_c(rootFilePathOfIn, filesMatrix[6,1])
file_GoodM2_clusterSpecial_NotTsNotLadder_c19 <- stringr::str_c(rootFilePathOfIn, filesMatrix[6,2])

# BadF2ExcludeF3
file_BadF2ExcludeF3_clusterSpecial_Ladder_c0 <- stringr::str_c(rootFilePathOfIn, filesMatrix[7,1])
file_BadF2ExcludeF3_clusterSpecial_Ladder_c11 <- stringr::str_c(rootFilePathOfIn, filesMatrix[7,2])
file_BadF2ExcludeF3_clusterSpecial_Ladder_c12 <- stringr::str_c(rootFilePathOfIn, filesMatrix[7,3])
file_BadF2ExcludeF3_clusterSpecial_Ladder_c16 <- stringr::str_c(rootFilePathOfIn, filesMatrix[7,4])

file_BadF2ExcludeF3_clusterSpecial_Ts_c2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[8,1])
file_BadF2ExcludeF3_clusterSpecial_Ts_c4 <- stringr::str_c(rootFilePathOfIn, filesMatrix[8,2])
file_BadF2ExcludeF3_clusterSpecial_Ts_c11 <- stringr::str_c(rootFilePathOfIn, filesMatrix[8,3])
file_BadF2ExcludeF3_clusterSpecial_Ts_c13 <- stringr::str_c(rootFilePathOfIn, filesMatrix[8,4])

file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c0 <- stringr::str_c(rootFilePathOfIn, filesMatrix[9,1])
file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[9,2])
file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c7 <- stringr::str_c(rootFilePathOfIn, filesMatrix[9,3])
file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c13 <- stringr::str_c(rootFilePathOfIn, filesMatrix[9,4])

# BadF3
file_BadF3_clusterSpecial_Ladder_c2 <- stringr::str_c(rootFilePathOfIn, filesMatrix[10,1])
file_BadF3_clusterSpecial_Ladder_c3 <- stringr::str_c(rootFilePathOfIn, filesMatrix[10,2])
file_BadF3_clusterSpecial_Ladder_c6 <- stringr::str_c(rootFilePathOfIn, filesMatrix[10,3])
file_BadF3_clusterSpecial_Ladder_c15 <- stringr::str_c(rootFilePathOfIn, filesMatrix[10,4])

file_BadF3_clusterSpecial_Ts_c0 <- stringr::str_c(rootFilePathOfIn, filesMatrix[11,1])
file_BadF3_clusterSpecial_Ts_c8 <- stringr::str_c(rootFilePathOfIn, filesMatrix[11,2])
file_BadF3_clusterSpecial_Ts_c16 <- stringr::str_c(rootFilePathOfIn, filesMatrix[11,3])
file_BadF3_clusterSpecial_Ts_c17 <- stringr::str_c(rootFilePathOfIn, filesMatrix[11,4])

file_BadF3_clusterSpecial_NotTsNotLadder_c0 <- stringr::str_c(rootFilePathOfIn, filesMatrix[12,1])
file_BadF3_clusterSpecial_NotTsNotLadder_c1 <- stringr::str_c(rootFilePathOfIn, filesMatrix[12,2])
file_BadF3_clusterSpecial_NotTsNotLadder_c12 <- stringr::str_c(rootFilePathOfIn, filesMatrix[12,3])
file_BadF3_clusterSpecial_NotTsNotLadder_c17 <- stringr::str_c(rootFilePathOfIn, filesMatrix[12,4])

# *****************************************************************************
# 加载数据到变量中
# *****************************************************************************
# GoodM1
fileData_GoodM1_clusterSpecial_Ladder_s1 <- loadSampleData(file_GoodM1_clusterSpecial_Ladder_s1)
fileData_GoodM1_clusterSpecial_Ladder_s2 <- loadSampleData(file_GoodM1_clusterSpecial_Ladder_s2)
fileData_GoodM1_clusterSpecial_Ladder_s3 <- loadSampleData(file_GoodM1_clusterSpecial_Ladder_s3)
fileData_GoodM1_clusterSpecial_Ladder_s4 <- loadSampleData(file_GoodM1_clusterSpecial_Ladder_s4)

fileData_GoodM1_clusterSpecial_Ts_s1 <- loadSampleData(file_GoodM1_clusterSpecial_Ts_s1)
fileData_GoodM1_clusterSpecial_Ts_s2 <- loadSampleData(file_GoodM1_clusterSpecial_Ts_s2)
fileData_GoodM1_clusterSpecial_Ts_s3 <- loadSampleData(file_GoodM1_clusterSpecial_Ts_s3)
fileData_GoodM1_clusterSpecial_Ts_s4 <- loadSampleData(file_GoodM1_clusterSpecial_Ts_s4)

fileData_GoodM1_clusterSpecial_NotTsNotLadder_s1 <- loadSampleData(file_GoodM1_clusterSpecial_NotTsNotLadder_s1)
fileData_GoodM1_clusterSpecial_NotTsNotLadder_s2 <- loadSampleData(file_GoodM1_clusterSpecial_NotTsNotLadder_s2)
fileData_GoodM1_clusterSpecial_NotTsNotLadder_s3 <- loadSampleData(file_GoodM1_clusterSpecial_NotTsNotLadder_s3)

# GoodM2
fileData_GoodM2_clusterSpecial_Ladder_s1 <- loadSampleData(file_GoodM2_clusterSpecial_Ladder_s1)
fileData_GoodM2_clusterSpecial_Ladder_s2 <- loadSampleData(file_GoodM2_clusterSpecial_Ladder_s2)
fileData_GoodM2_clusterSpecial_Ladder_s3 <- loadSampleData(file_GoodM2_clusterSpecial_Ladder_s3)

fileData_GoodM2_clusterSpecial_Ts_s0 <- loadSampleData(file_GoodM2_clusterSpecial_Ts_s0)
fileData_GoodM2_clusterSpecial_Ts_s2 <- loadSampleData(file_GoodM2_clusterSpecial_Ts_s2)
fileData_GoodM2_clusterSpecial_Ts_s5 <- loadSampleData(file_GoodM2_clusterSpecial_Ts_s5)
fileData_GoodM2_clusterSpecial_Ts_s17 <- loadSampleData(file_GoodM2_clusterSpecial_Ts_s17)

fileData_GoodM2_clusterSpecial_NotTsNotLadder_c7 <- loadSampleData(file_GoodM2_clusterSpecial_NotTsNotLadder_c7)
fileData_GoodM2_clusterSpecial_NotTsNotLadder_c19 <- loadSampleData(file_GoodM2_clusterSpecial_NotTsNotLadder_c19)

# BadF2ExcludeF3
fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c0 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ladder_c0)
fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c11 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ladder_c11)
fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c12 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ladder_c12)
fileData_BadF2ExcludeF3_clusterSpecial_Ladder_c16 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ladder_c16)

fileData_BadF2ExcludeF3_clusterSpecial_Ts_c2 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ts_c2)
fileData_BadF2ExcludeF3_clusterSpecial_Ts_c4 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ts_c4)
fileData_BadF2ExcludeF3_clusterSpecial_Ts_c11 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ts_c11)
fileData_BadF2ExcludeF3_clusterSpecial_Ts_c13 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_Ts_c13)

fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c0 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c0)
fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c3 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c3)
fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c7 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c7)
fileData_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c13 <- loadSampleData(file_BadF2ExcludeF3_clusterSpecial_NotTsNotLadder_c13)

# BadF3
fileData_BadF3_clusterSpecial_Ladder_c2 <- loadSampleData(file_BadF3_clusterSpecial_Ladder_c2)
fileData_BadF3_clusterSpecial_Ladder_c3 <- loadSampleData(file_BadF3_clusterSpecial_Ladder_c3)
fileData_BadF3_clusterSpecial_Ladder_c6 <- loadSampleData(file_BadF3_clusterSpecial_Ladder_c6)
fileData_BadF3_clusterSpecial_Ladder_c15 <- loadSampleData(file_BadF3_clusterSpecial_Ladder_c15)

fileData_BadF3_clusterSpecial_Ts_c0 <- loadSampleData(file_BadF3_clusterSpecial_Ts_c0)
fileData_BadF3_clusterSpecial_Ts_c8 <- loadSampleData(file_BadF3_clusterSpecial_Ts_c8)
fileData_BadF3_clusterSpecial_Ts_c16 <- loadSampleData(file_BadF3_clusterSpecial_Ts_c16)
fileData_BadF3_clusterSpecial_Ts_c17 <- loadSampleData(file_BadF3_clusterSpecial_Ts_c17)

fileData_BadF3_clusterSpecial_NotTsNotLadder_c0 <- loadSampleData(file_BadF3_clusterSpecial_NotTsNotLadder_c0)
fileData_BadF3_clusterSpecial_NotTsNotLadder_c1 <- loadSampleData(file_BadF3_clusterSpecial_NotTsNotLadder_c1)
fileData_BadF3_clusterSpecial_NotTsNotLadder_c12 <- loadSampleData(file_BadF3_clusterSpecial_NotTsNotLadder_c12)
fileData_BadF3_clusterSpecial_NotTsNotLadder_c17 <- loadSampleData(file_BadF3_clusterSpecial_NotTsNotLadder_c17)

