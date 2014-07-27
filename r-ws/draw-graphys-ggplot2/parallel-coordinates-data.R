# -----------------------------------------------------------------------------
#	parallel coordinate plot 
#
# 安装包
#install.packages("ggplot2")
#library("ggplot2")

# 图例
#	http://stackoverflow.com/questions/3942508/implementation-of-parallel-coordinates
D <- data.frame(Gain = rnorm(20),  
                Trader = factor(LETTERS[1:4]), 
                Day = factor(rep(1:5, each = 4)))
ggplot(D) + 
  geom_line(aes(x = Trader, y = Gain, group = Day, color = Day))

# 使用我的数据
#    每次手动选择文件
#	mydata = read.table(file.choose(), header=FALSE, sep=",")
# *****************************************************************************
# -----------------------------------------------------------------------------
## metrics
# -----------------------------------------------------------------------------
# 数据准备
# mydata = read.csv("~/workspace_github/hadoop-ws/sparkR-ws/data/metrics.csv")  # read csv file  ,首行有列名
#mydata = read.table("j:/home/hadoop/workspace_github/hadoop-ws/r-ws/data/metrics.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
mymetrics = read.table("~/workspace_github/hadoop-ws/r-ws/data/metrics.csv")  # read table file ,首行无列名(header=FALSE)
mymetrics = read.table("~/workspace_github/hadoop-ws/r-ws/data/metrics.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)

mymetrics

# 为mydata设置变量标签
labels_metrics <- c("k", "maxIterations", "WSSSE", "聚类开始时间", "聚类结束时间", "KMeansModel")
names(mymetrics) <- labels_metrics

# 将k转换为factor
mymetrics$k.f <- as.factor(mymetrics$k)

# 取第1,3列
mymetrics[,1-3]

# -----------------------------------------------------------------------------
# 画图
# -----------------------------------------------
# Bar Graphs		# mymetrics 数据画"Bar Graphs"是无意义的,只是为了演示语法
require(ggplot2)
## Loading required package: ggplot2
ggplot(mymetrics, aes(x = k)) + geom_bar()
ggplot(mymetrics, aes(x = WSSSE)) + geom_bar()

# -----------------------------------------------
# 折线图
library(ggplot2)
qplot(k.f, WSSSE, data=mymetrics, geom=c("boxplot", "jitter"),
      fill=cylinder, 
      main="K效能图",
      xlab= "K",
        ylab="WSSSE")

# *****************************************************************************
## clusterCenters
# -----------------------------------------------------------------------------
# 数据准备
#mycenters = read.table("j:/home/hadoop/workspace_github/hadoop-ws/r-ws/data/clustercenters.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
myclustercenters = read.table("~/workspace_github/hadoop-ws/r-ws/data/clustercenters.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
myclustercenters

# vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312,vpm201401,vpm201402,vpm201403,vpm201404,vpm201405,vpm201406
# 取前面18列,即从 201301~201406这18个月的月用点量
vpm <- myclustercenters[, c(1:18)]

# 将第一列前面的"["并转换为 numeric
#vpm$V1 
x <- vpm$V1
x.substr <- substr(x, 2, nchar(x))	# 从第二个字符截取
vpm$V1 <- as.numeric(x.substr)
rm(x)

# 将行号变为一列
rownums <- as.numeric(rownames(vpm))
vpm <- data.frame(vpm, factor(rownums))	# 因子化 

# 为vpm设置变量标签
newcolnames <- c("201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312", "201401", "201402", "201403", "201404", "201405", "201406")
#newcolnames <- c("13/1", "13/2", "13/3", "13/4", "13/5", "13/6", "13/7", "13/8", "13/9", "13/10", "13/11", "13/12", "14/1", "14/2", "14/3", "14/4", "14/5", "14/6")
names(vpm) <- c(newcolnames,"rowid")
rm(newcolnames)

# 横表变纵表

library(reshape)
#vpm.v <- melt(vpm ,  id = 'rowid')  				# variable -> value
vpm.v <- melt(vpm ,  id = 'rowid', variable_name = 'colnames')		# colnames -> value

# 画图的列
vpm.v$ym <- as.factor(vpm.v$colnames)

