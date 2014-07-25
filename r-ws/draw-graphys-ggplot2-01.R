# -----------------------------------------------------------------------------
#	parallel coordinate plot 
#

# *****************************************************************************
# 启动 sparkR
#	MASTER=local ${SPARKR_HOME}/sparkR

#> getwd()
#[1] "/home/hadoop/workspace_github/hadoop-ws/sparkR-ws"

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
#    每次选择文件
	mydata = read.table(file.choose(), header=FALSE, sep=",")
# *****************************************************************************
# -----------------------------------------------------------------------------
## metrics
# -----------------------------------------------------------------------------
# 数据准备
# mydata = read.csv("~/workspace_github/hadoop-ws/sparkR-ws/data/metrics.csv")  # read csv file  ,首行有列名
#mydata = read.table("j:/home/hadoop/workspace_github/hadoop-ws/r-ws/data/metrics.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
mydata = read.table("~/workspace_github/hadoop-ws/r-ws/data/metrics.csv")  # read table file ,首行无列名(header=FALSE)
mydata = read.table("~/workspace_github/hadoop-ws/r-ws/data/metrics.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)

mydata

# 为mydata设置变量标签
labels_metrics <- c("k", "maxIterations", "WSSSE", "聚类开始时间", "聚类结束时间", "KMeansModel")
names(mydata) <- labels_metrics

# 将k转换为factor
mydata$k.f <- as.factor(mydata$k)

# 取第1,3列
mydata[,1-3]

# -----------------------------------------------------------------------------
# 画图
# -----------------------------------------------
# Bar Graphs		# mydata 数据画"Bar Graphs"是无意义的,只是为了演示语法
require(ggplot2)
## Loading required package: ggplot2
ggplot(mydata, aes(x = k)) + geom_bar()
ggplot(mydata, aes(x = WSSSE)) + geom_bar()

# -----------------------------------------------
# 折线图
library(ggplot2)
mtcars$cylinder <- as.factor(mtcars$cyl)
qplot(k.f, WSSSE, data=mydata, geom=c("boxplot", "jitter"),
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
#newcolnames <- c("201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312", "201401", "201402", "201403", "201404", "201405", "201406")
newcolnames <- c("13/1", "13/2", "13/3", "13/4", "13/5", "13/6", "13/7", "13/8", "13/9", "13/10", "13/11", "13/12", "14/1", "14/2", "14/3", "14/4", "14/5", "14/6")
names(vpm) <- c(newcolnames,"rowid")
rm(newcolnames)

# 横表变纵表

library(reshape)
#vpm.v <- melt(vpm ,  id = 'rowid')  				# variable -> value
vpm.v <- melt(vpm ,  id = 'rowid', variable_name = 'colnames')		# colnames -> value

# 画图的列
vpm.v$ym <- as.factor(vpm.v$colnames)
# -----------------------------------------------------------------------------
# 画图
# ------------------------------------------------
# 每一个列的箱线图
# 图例-箱线图 << R in action >>

# 图形输出到另外一个目录
#jpeg("graphys/clustercenters.s01.vpm.boxplot.簇中心的月用电量箱线图.jpg") # jpeg图很模糊
#pdf.options(encoding='ISOLatin2.enc')						# 不能解决中文名报错的问题
#pdf.options(encoding = "CP1250")						# 不能解决中文名报错的问题
#pdf(c("graphys/clustercenters.s01.vpm.boxplot.簇中心的月用电量箱线图.pdf"))	# 中文名字会报错! 

pdf("graphys/clustercenters.s01.vpm.boxplot.pdf")

library(ggplot2)
qplot(ym, value, data=vpm.v, geom=c("boxplot", "jitter"),
      fill=ym, 
      main="簇中心的月用电量箱线图",
      xlab= "年月",
        ylab="簇中心的用电量")

# 重置图形输出
dev.off()

ggplot(df.vertical, aes(time,value)) + geom_line(aes(colour = series))
ggplot(vpm.v, aes(rowid,value)) + geom_line(aes(colour = ym))

ggplot(vpm.v, aes(ym,value)) + geom_line(aes(colour = rowid))
# ------------------------------------------------
# *****************************************************************************
# 图例1
# 图形输出到另外一个目录
#pdf("graphys/legend01.pdf")
jpeg("graphys/legend01.jpg")

# 将colour参数设置在散点图第一层
p <- ggplot(data=mpg,aes(x=displ,y=hwy,colour=factor(cyl)))
p + geom_point() + geom_smooth()

# 重置图形输出
dev.off()

# -----------------------------------------------
# 图例2
# 图形输出到另外一个目录
#pdf("graphys/legend02.pdf")
jpeg("graphys/legend01.jpg")

# 对整体数据进行平滑，可将colour参数设置在散点图层内而非第一层，这样第三层的平滑图形就不会受到colour参数的影响。
p <- ggplot(mpg,aes(x=displ,y=hwy))
p + geom_point(aes(colour=factor(cyl))) + geom_smooth()

# 重置图形输出
dev.off()
# -----------------------------------------------
# 图例-箱线图 << R in action >>
library(ggplot2)
mtcars$cylinder <- as.factor(mtcars$cyl)
qplot(cylinder, mpg, data=mtcars, geom=c("boxplot", "jitter"),
      fill=cylinder, 
      main="Box plots with superimposed data points",
      xlab= "Number of Cylinders",
        ylab="Miles per Gallon")

# 图例-散点图 (增加了各自的回归线和置信区间带) << R in action >>
library(ggplot2)
transmission <- factor(mtcars$am, levels=c(0, 1), 
                       labels=c("Automatic",  "Manual"))
qplot(wt,mpg, data=mtcars, 
      color=transmission, shape=transmission,
      geom=c("point", "smooth"),
      method="lm", formula=y~x, 
      xlab="Weight", ylab="Miles Per Gallon", 
      main="Regression Example")

# 图例-分面(栅栏)图 (分面散点图) << R in action >>
library(ggplot2)
mtcars$cyl <- factor(mtcars$cyl, levels=c(4, 6, 8),
                     labels=c("4 cylinders", "6 cylinders", "8 cylinders"))
mtcars$am <- factor(mtcars$am, levels=c(0, 1), 
                    labels=c("Automatic",  "Manual"))
qplot(wt,mpg, data=mtcars, facets=am~cyl, size=hp)

# 图例-歌手身高密度图  << R in action >>
library(ggplot2)
data(singer, package="lattice")
qplot(height, data=singer, geom=c("density"),
      facets=voice.part~., fill=voice.part)

# 图例-为每一个列画图
require(ggplot2)
require(reshape)
df <- data.frame(time = 1:10,
                 a = cumsum(rnorm(10)),
                 b = cumsum(rnorm(10)),
                 c = cumsum(rnorm(10)))
df.vertical <- melt(df ,  id = 'time', variable_name = 'series')
# plot on same grid, each series colored differently -- 
# good if the series have same scale
ggplot(df.vertical, aes(time,value)) + geom_line(aes(colour = series))

# or plot on different plots
ggplot(df.vertical, aes(time,value)) + geom_line() + facet_grid(series ~ .)
