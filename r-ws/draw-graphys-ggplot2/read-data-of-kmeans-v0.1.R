# -----------------------------------------------------------------------------
# 读取spark分析处理后的数据,供画图程序使用
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-kmeans-v0.1.R")
# -----------------------------------------------------------------------------

# *****************************************************************************
# KMeans算法数据
#	过程度量数据:	mymetrics	
#	最佳k中心点:	myclustercenters vpm vpm.v	

# 也可以调整为每次手动选择文件
#	mydata = read.table(file.choose(), header=FALSE, sep=",")
# *****************************************************************************

# FilePath
rootFilePathOfIn <- "~/workspace_github/hadoop-ws/r-ws/result-data/"
rootFilePathOfOut <- stringr::str_c(rootFilePathOfIn,"formated/")

# 行: 数据集+单月/双月
dimRows <- c("S01_M2", "S98_M1_k19")
dimCols <- c("unsorted", "sorted", "cluster")
# 文件名
filesVector <- c(
	"S01_M2_kmeans_20_metrics_unsorted.csv", "S01_M2_kmeans_20_metrics_sorted.csv", "S01_M2_kmeans_19_cluster.csv",
	"", "", "S98_M1_kmeans_19_cluster.csv"
	)
# 构建矩阵
filesMatrix <- matrix(filesVector, byrow=TRUE, nrow=length(dimRows), ncol=length(dimCols),dimnames=list(dimRows, dimCols))
str(filesMatrix)


# *****************************************************************************
# metrics
# *****************************************************************************
# 本次要读取的文件名
filenameOfmetrics <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,1])	# [1,1]
filenameOfmetrics

# 读取文件
# mydata = read.csv(filename)  # read csv file  ,首行有列名
# mymetrics = read.table(filenameOfmetrics)  			# read table file ,首行无列名(header=FALSE)
mymetrics = read.table(filenameOfmetrics, header=FALSE, sep=",")	# read table file ,首行无列名(header=FALSE)
#mymetrics

# 为 mymetrics 设置变量标签
labels_metrics <- c("k", "maxIterations", "WSSSE", "聚类开始时间", "聚类结束时间", "KMeansModel")
names(mymetrics) <- labels_metrics

# 将k, maxIterations 转换为factor
mymetrics$k <- as.factor(mymetrics$k)
mymetrics$maxIterations <- as.factor(mymetrics$maxIterations)

# 取第1,3列
#mymetrics[,1-3]

# *****************************************************************************
# clusterCenters
# *****************************************************************************
# 本次要读取的文件名
filenameOfClusterCenters <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,3])	# [1,3]
filenameOfClusterCenters

# 读取文件
myclustercenters = read.table(filenameOfClusterCenters, header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
str(myclustercenters)

# --------------------------------------------------------------
# 将第一列前面的"["去掉,并转换为 numeric
#tmpX <- myclustercenters$V1		# 注意:此时的tmpX是向量
#tmpX.substr <- substr(tmpX, 2, nchar(as.character(tmpX)))	# 从第二个字符截取
#myclustercenters$V1 <- as.numeric(tmpX.substr)
#rm(tmpX)

# 将最后一列后面的"["去掉,并转换为 numeric
#tmpLen <- length(names(myclustercenters))
#tmpY <- myclustercenters[tmpLen]		# 注意: 此时的tmpY是data.frame(只有一列)
#names(tmpY) <- c("tmpColID")		# 重命名列名
#tmpZ <- tmpY$tmpColID			# 应用新列名获取数据: 此时的tmpZ是向量
#tmpZ.substr <- substr(tmpZ, 1, nchar(as.character(tmpZ)) -1 )	# 截取到倒数第二个字符
#myclustercenters[tmpLen] <- as.numeric(tmpZ.substr)
#rm(tmpY)
#rm(tmpZ)

# -----------------------------------------------------------------------------
# vpm vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312,vpm201401,vpm201402,vpm201403,vpm201404,vpm201405,vpm201406
# 取前面 2+12 列,即从 201301~201312这12个月的月用点量
vpm <- myclustercenters[, c(1:(2+12))]

# 为vpm设置变量标签
newcolnames <- c("clusterID", "counter", "201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312")
names(vpm) <- c(newcolnames)	# names(vpm.clusterID) <- c(newcolnames,"clusterID")
rm(newcolnames)

# -----------------------------------------------------------------------------
# 生成 vpm.v (增加了clusterID)  新格式已经有clusterID
# 将行号变为一列
#newcolumn <- factor(as.numeric(rownames(vpm)))
#vpm.clusterID <- data.frame(vpm, newcolumn)	# 为什么这一句后,列明变为了 x201301?

# 将新列的列名改为clusterID
# 重新设置列名
#newcolnames <- c("201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312")
#names(vpm.clusterID) <- c(newcolnames,"clusterID")
#rm(newcolnames)

# 将 "clusterID" 变为 factor?
# -----------------------------------------------------------------------------
# 横表变纵表
library(reshape)
# 'pointsNum'是没有必要的,但为了保留它作为单独的一列
vpm.v <- melt(vpm,  id = c("clusterID", "counter"), variable_name = "ym")	# colnames/ym -> value

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




