# -----------------------------------------------------------------------------
# 基本图形化展现
# 最佳k中心点:	myclustercenters vpm vpm.v
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-kmeans.R")
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/kmeans-basic-visualizing-of-clusters.R")
# -----------------------------------------------------------------------------
# 加载包
library(ggplot2)

# ---------------------------
# 簇中心的月用电量折线图
dev.new()
p <- ggplot(vpm.v, aes(as.integer(ym),value))
p <- p + xlab("年月") + ylab("簇中心的用电量")
p + geom_line(aes(colour = clusterID))

# ---------------------------
# 簇中心的月用电量箱线图
dev.new()
p <- ggplot(vpm.v, aes(factor(ym), value))
p <- p + xlab("年月") + ylab("簇中心的用电量")
p + geom_boxplot()

# ---------------------------
# 平行坐标图


