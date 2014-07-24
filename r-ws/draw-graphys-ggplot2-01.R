# -----------------------------------------------------------------------------
#	parallel coordinate plot 
#

# 启动 sparkR
#	MASTER=local ${SPARKR_HOME}/sparkR

#> getwd()
#[1] "/home/hadoop/workspace_github/hadoop-ws/sparkR-ws"

# 安装包
install.packages("ggplot2")
library("ggplot2")

# 图例
#	http://stackoverflow.com/questions/3942508/implementation-of-parallel-coordinates
D <- data.frame(Gain = rnorm(20),  
                Trader = factor(LETTERS[1:4]), 
                Day = factor(rep(1:5, each = 4)))
ggplot(D) + 
  geom_line(aes(x = Trader, y = Gain, group = Day, color = Day))

# 使用我的数据
# -----------------------------------------------
## metrics
# mydata = read.csv("~/workspace_github/hadoop-ws/sparkR-ws/data/metrics.csv")  # read csv file  ,首行有列名
mydata = read.table("~/workspace_github/hadoop-ws/sparkR-ws/data/metrics.csv")  # read table file ,首行无列名(header=FALSE)
mydata = read.table("~/workspace_github/hadoop-ws/sparkR-ws/data/metrics.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
mydata
# 取第1,3列
mydata[,1-3]
# -----------------------------------------------
## clusterCenters
mycenters = read.table("~/workspace_github/hadoop-ws/sparkR-ws/data/clustercenters.csv", header=FALSE, sep=",")  # read table file ,首行无列名(header=FALSE)
mycenters

# 图例1
# 将colour参数设置在散点图第一层
p <- ggplot(data=mpg,aes(x=displ,y=hwy,colour=factor(cyl)))
p + geom_point() + geom_smooth()

# 图例2
# 对整体数据进行平滑，可将colour参数设置在散点图层内而非第一层，这样第三层的平滑图形就不会受到colour参数的影响。
p <- ggplot(mpg,aes(x=displ,y=hwy))
p + geom_point(aes(colour=factor(cyl))) + geom_smooth()

