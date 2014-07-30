# -----------------------------------------------------------------------------
# 使用R转换后的数据,写入文件存储
# -----------------------------------------------------------------------------

# 在R环境中,使用下面语句
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-demo.R")
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/write-data-of-demo.R")

# -----------------------------------------------------------------------------
# FilePath

rootFilePathOfIn <- "~/workspace_github/hadoop-ws/r-ws/result-data/"
rootFilePathOfOut <- stringr::str_c(rootFilePathOfIn,"formated/")

# 数据准备
# mydata = read.csv("~/workspace_github/hadoop-ws/sparkR-ws/data/metrics.csv")  # read csv file  ,首行有列名
#mydata = read.table("j:/home/hadoop/workspace_github/hadoop-ws/r-ws/result-data/metrics.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
mymetrics = read.table("~/workspace_github/hadoop-ws/r-ws/result-data/metrics.csv")  # read table file ,首行无列名(header=FALSE)
mymetrics = read.table("~/workspace_github/hadoop-ws/r-ws/result-data/metrics.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)

mymetrics

# 为mydata设置变量标签
labels_metrics <- c("k", "maxIterations", "WSSSE", "聚类开始时间", "聚类结束时间", "KMeansModel")
names(mymetrics) <- labels_metrics

# 将k, maxIterations 转换为factor
mymetrics$k <- as.factor(mymetrics$k)
mymetrics$maxIterations <- as.factor(mymetrics$maxIterations)

# 取第1,3列
mymetrics[,1-3]

# *****************************************************************************
## clusterCenters
# -----------------------------------------------------------------------------
# 数据准备
#mycenters = read.table("j:/home/hadoop/workspace_github/hadoop-ws/r-ws/result-data/clustercenters.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
myclustercenters = read.table("~/workspace_github/hadoop-ws/r-ws/result-data/clustercenters.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
myclustercenters

# -----------------------------------------------------------------------------
# vpm vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312,vpm201401,vpm201402,vpm201403,vpm201404,vpm201405,vpm201406
# 取前面18列,即从 201301~201406这18个月的月用点量
# 取前面18列,即从 201301~201312这12个月的月用点量
vpm <- myclustercenters[, c(1:12)]

# 将第一列前面的"["并转换为 numeric
#vpm$V1 
x <- vpm$V1
x.substr <- substr(x, 2, nchar(as.character(x)))	# 从第二个字符截取
vpm$V1 <- as.numeric(x.substr)
rm(x)

# 为vpm设置变量标签
# newcolnames <- c("201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312", "201401", "201402", "201403", "201404", "201405", "201406")
newcolnames <- c("201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312")
names(vpm) <- c(newcolnames)	# names(vpm.rowid) <- c(newcolnames,"rowid")
rm(newcolnames)

# -----------------------------------------------------------------------------
# 生成 vpm.v (增加了rowid)
# 将行号变为一列
newcolumn <- factor(as.numeric(rownames(vpm)))
vpm.rowid <- data.frame(vpm, newcolumn)	# 为什么这一句后,列明变为了 x201301?

# 将新列的列名改为rowid
# 重新设置列名
newcolnames <- c("201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312")
names(vpm.rowid) <- c(newcolnames,"rowid")
rm(newcolnames)

# 横表变纵表
library(reshape)
#vpm.v <- melt(vpm ,  id = 'rowid')  				# variable -> value
vpm.v <- melt(vpm.rowid,  id = 'rowid', variable_name = 'ym')		# colnames/ym -> value

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




