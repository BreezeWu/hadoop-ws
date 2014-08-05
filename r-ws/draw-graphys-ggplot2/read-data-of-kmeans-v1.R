# -----------------------------------------------------------------------------
# 读取spark分析处理后的数据,供画图程序使用
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
# 		source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-kmeans-v1.R")
# 得到如下数据对象
#	1. 数据集 GoodM1
#				(1)	fileData_GoodM1_metrics_unsorted
#				(2)	fileData_GoodM1_metrics_sorted
#				(3)	fileData__GoodM1_clusterCenters   目前无效
#				(4)	fileData_GoodM1_cluster
#				(5)	fileData_GoodM1_clusterSpecial
#	2. 数据集 GoodM2
#	3. 数据集 BadF3
#	4. 数据集 BadF2ExcludeF3
#
#  特别注意：
#  loadMetrics	:	加载 metrics 的函数
# 		适用于 _metrics_unsorted 和 _metrics_sorted
#  loadCluster	:	加载 Cluster 的函数
# 		适用于 _cluster 和 _clusterSpecial, 但不能加载 _clusterCenters
#	!!!!! 当前没有加载 _clusterCenters 的函数
# -----------------------------------------------------------------------------

fileData_GoodM1_metrics_unsorted <- loadMetrics(file_GoodM1_metrics_unsorted)
fileData_GoodM1_metrics_sorted <- loadMetrics(file_GoodM1_metrics_sorted)
#fileData__GoodM1_clusterCenters <- ?????
fileData_GoodM1_cluster <- loadCluster(file_GoodM1_cluster)
fileData_GoodM1_clusterSpecial <- loadCluster(file_GoodM1_clusterSpecial)
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
dimRows <- c("S98_GoodM1", "S98_GoodM2", "S98_BadF3", "S98_BadF2ExcludeF3")
dimCols <- c("unsorted", "sorted", "clustercenters", "cluster", "cluster0fSpecial")
# 文件名
filesVector <- c(
    "S98_GoodM1_kmeans_199_metrics_unsorted.csv",  "S98_GoodM1_kmeans_199_metrics_sorted.csv",  "S98_GoodM1_kmeans_199_clusterCenters.csv",  "S98_perfectK20_GoodM1_kmeans_20_cluster.csv",  "S98_perfectK20_GoodM1_kmeans_20_cluster.csv",
    "S98_GoodM2_kmeans_200_metrics_unsorted.csv",  "S98_GoodM2_kmeans_200_metrics_sorted.csv",  "S98_GoodM2_kmeans_200_clusterCenters.csv",  "S98_perfectK20_GoodM2_kmeans_20_cluster.csv",  "S98_perfectK20_GoodM2_kmeans_20_cluster.csv",
    "S98_BadF3_kmeans_200_metrics_unsorted.csv",  "S98_BadF3_kmeans_200_metrics_sorted.csv",  "S98_BadF3_kmeans_200_clusterCenters.csv",  "S98_perfectK20_BadF3_kmeans_20_cluster.csv",  "S98_perfectK20_BadF3_kmeans_20_cluster.csv",
    "S98_BadF2ExcludeF3_kmeans_200_metrics_unsorted.csv",  "S98_BadF2ExcludeF3_kmeans_200_metrics_sorted.csv",  "S98_BadF2ExcludeF3_kmeans_200_clusterCenters.csv",  "S98_perfectK20_BadF2ExcludeF3_kmeans_20_cluster.csv",  "S98_perfectK20_BadF2ExcludeF3_kmeans_20_cluster.csv"
	)
# 构建矩阵
filesMatrix <- matrix(filesVector, byrow=TRUE, nrow=length(dimRows), ncol=length(dimCols),dimnames=list(dimRows, dimCols))
#str(filesMatrix)


# *****************************************************************************
# metrics
# *****************************************************************************
# 本次要读取的文件名
# GoodM1
file_GoodM1_metrics_unsorted <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,1])
file_GoodM1_metrics_sorted <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,2])
file_GoodM1_clusterCenters <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,3])
file_GoodM1_cluster <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,4])
file_GoodM1_clusterSpecial <- stringr::str_c(rootFilePathOfIn, filesMatrix[1,5])
# GoodM2
file_GoodM2_metrics_unsorted <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,1])
file_GoodM2_metrics_sorted <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,2])
file_GoodM2_clusterCenters <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,3])
file_GoodM2_cluster <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,4])
file_GoodM2_clusterSpecial <- stringr::str_c(rootFilePathOfIn, filesMatrix[2,5])
# BadF3
file_BadF3_metrics_unsorted <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,1])
file_BadF3_metrics_sorted <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,2])
file_BadF3_clusterCenters <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,3])
file_BadF3_cluster <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,4])
file_BadF3_clusterSpecial <- stringr::str_c(rootFilePathOfIn, filesMatrix[3,5])
# BadF2ExcludeF3
file_BadF2ExcludeF3_metrics_unsorted <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,1])
file_BadF2ExcludeF3_metrics_sorted <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,2])
file_BadF2ExcludeF3_clusterCenters <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,3])
file_BadF2ExcludeF3_cluster <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,4])
file_BadF2ExcludeF3_clusterSpecial <- stringr::str_c(rootFilePathOfIn, filesMatrix[4,5])

# *****************************************************************************
# 函数定义
# *****************************************************************************
# -----------------------------------------------------------------------------
# 加载 metrics 的函数
# 		适用于 _metrics_unsorted 和 _metrics_sorted
loadMetrics <- function(filename) {
	# 读取文件
	mymetrics = read.table(filename, header=FALSE, sep=",")	 # read table file ,首行无列名(header=FALSE)
	#mymetrics
	
	# 为 mymetrics 设置变量标签
	labels_metrics <- c("k", "maxIterations", "WSSSE", "聚类开始时间", "聚类结束时间", "KMeansModel")
	names(mymetrics) <- labels_metrics
	
	# 将k, maxIterations 转换为factor
	mymetrics$k <- as.factor(mymetrics$k)
	mymetrics$maxIterations <- as.factor(mymetrics$maxIterations)
	
	# 取第1,3列
	#mymetrics[,1-3]
	return (mymetrics)
}

# -----------------------------------------------------------------------------
# 加载 Cluster 的函数
# 		适用于 _cluster 和 _clusterSpecial
#		特别注意: 不能加载 _clusterCenters
loadCluster <- function(filename) {
	# 读取文件
	myclustercenters = read.table(filename, header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
	#str(myclustercenters)

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
	
	# 返回值
	mylist <- list(vpm, vpm.v, vpm.t)
	return (mylist)
}

# *****************************************************************************
# 加载数据到变量中
# *****************************************************************************
# GoodM1
fileData_GoodM1_metrics_unsorted <- loadMetrics(file_GoodM1_metrics_unsorted)
fileData_GoodM1_metrics_sorted <- loadMetrics(file_GoodM1_metrics_sorted)
#fileData__GoodM1_clusterCenters <- ?????
fileData_GoodM1_cluster <- loadCluster(file_GoodM1_cluster)
fileData_GoodM1_clusterSpecial <- loadCluster(file_GoodM1_clusterSpecial)

# GoodM2
fileData_GoodM2_metrics_unsorted <- loadMetrics(file_GoodM2_metrics_unsorted)
fileData_GoodM2_metrics_sorted <- loadMetrics(file_GoodM2_metrics_sorted)
#fileData__GoodM2_clusterCenters <- ?????
fileData_GoodM2_cluster <- loadCluster(file_GoodM2_cluster)
fileData_GoodM2_clusterSpecial <- loadCluster(file_GoodM2_clusterSpecial)

# BadF3
fileData_BadF3_metrics_unsorted <- loadMetrics(file_BadF3_metrics_unsorted)
fileData_BadF3_metrics_sorted <- loadMetrics(file_BadF3_metrics_sorted)
#fileData__BadF3_clusterCenters <- ?????
fileData_BadF3_cluster <- loadCluster(file_BadF3_cluster)
fileData_BadF3_clusterSpecial <- loadCluster(file_BadF3_clusterSpecial)

# BadF2ExcludeF3
fileData_BadF2ExcludeF3_metrics_unsorted <- loadMetrics(file_BadF2ExcludeF3_metrics_unsorted)
fileData_BadF2ExcludeF3_metrics_sorted <- loadMetrics(file_BadF2ExcludeF3_metrics_sorted)
#fileData__BadF2ExcludeF3_clusterCenters <- ?????
fileData_BadF2ExcludeF3_cluster <- loadCluster(file_BadF2ExcludeF3_cluster)
fileData_BadF2ExcludeF3_clusterSpecial <- loadCluster(file_BadF2ExcludeF3_clusterSpecial)

# *****************************************************************************
# 其他存档
# *****************************************************************************
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





