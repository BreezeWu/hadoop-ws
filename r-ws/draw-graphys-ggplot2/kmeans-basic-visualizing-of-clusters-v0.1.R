# -----------------------------------------------------------------------------
# 基本图形化展现
# 最佳k中心点:	myclustercenters vpm vpm.v
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-kmeans-v0.1.R")
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/kmeans-basic-visualizing-of-clusters-v0.1.R")
# -----------------------------------------------------------------------------
# 加载包
library(ggplot2)

# -----------------------------------------------------------------------------
# 当前任务名称
g_curTaskName <- "s98_m1_k19"

# -----------------------------------------------------------------------------
# 图形名字函数
getImageFile <- function(desc, curTaskName = g_curTaskName, filetype="pdf") {
	rootFilePathOfImage <- "~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/graphys/"
	
	fileHead <- paste(rootFilePathOfImage, curTaskName, sep="")
	filenameOfImage <- paste(fileHead, desc, filetype, sep=".")
	return (filenameOfImage)	# 返回值必须加上括号？
}

# -----------------------------------------------------------------------------
# clusterID 及其 数量
curdata <- vpm[c("clusterID", "counter")]
curdata$clusterID <- as.factor(curdata$clusterID)
curdata
# ---------------------------
# 基于 y 变量的 value
p <- ggplot(curdata, aes(x=clusterID, y=counter))
p+ geom_bar(stat="identity")
#ggsave("draw-graphys-ggplot2/graphys/s98_m1_k19.geom_bar_counter.pdf", width = 7, height = 6.99) 
ggsave(getImageFile("geom_bar_counter"))

p <- ggplot(curdata, aes(x=clusterID, y=sqrt(counter)))
p+ geom_bar(stat="identity")
ggsave(getImageFile("geom_bar_counter_sqrt"))

p <- ggplot(curdata, aes(x=clusterID, y=sqrt(sqrt(counter))))
p+ geom_bar(stat="identity")
ggsave(getImageFile("geom_bart_counter_sqrtsqr"))

# ---------------------------
# 基于 y 变量的 统计次数
# 数据中不能指定 y
p <- ggplot(curdata, aes(x=clusterID))	
p+ geom_bar(stat="bin")	# p+ geom_bar()

# -----------------------------------------------------------------------------
# 簇中心的月用电量
curdata <- vpm.v
curdata$clusterID <- as.factor(curdata$clusterID)
curdata$ym <- ordered(curdata$ym)
str(curdata)

curdata[c("clusterID","value")]
data.frame(curdata$clusterID, curdata$ym, sqrt(curdata$value))
# ---------------------------
# 折线图
p <- ggplot(curdata, aes(x=ym, y=value, group=clusterID))
#p <- p + xlab("年月") + ylab("簇中心的用电量")			# 中文有问题
p <- p + xlab("month") + ylab("volume center")
p + geom_line()
p + geom_line(aes(colour = clusterID))
#p + geom_line(aes(colour = clusterID, size=clusterID))
#p + geom_line(aes(colour = clusterID, size= as.integer(clusterID) %% 5))
ggsave(getImageFile("簇中心的月用电量折线图"), width = 10, height = 8)

p <- ggplot(curdata, aes(x=ym, y=sqrt(value), group=clusterID))
#p <- p + xlab("年月") + ylab("簇中心的用电量")			# 中文有问题
p <- p + xlab("month") + ylab("volume center")
p + geom_line(aes(colour = clusterID))
ggsave(getImageFile("簇中心的月用电量折线图_sqrt"), width = 10, height = 8)
# ---------------------------
# 线图
dev.new()
p <- ggplot(curdata, aes(factor(ym), value))
p <- p + xlab("年月") + ylab("簇中心的用电量")
p + geom_boxplot()

# ---------------------------
# 平行坐标图


