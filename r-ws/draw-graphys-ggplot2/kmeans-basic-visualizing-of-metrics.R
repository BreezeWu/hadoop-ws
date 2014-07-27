# -----------------------------------------------------------------------------
# 基本图形化展现
#

# -----------------------------------------------------------------------------
# 安装包
#install.packages("ggplot2")
#library("ggplot2")

# *****************************************************************************
# -----------------------------------------------------------------------------
# 过程度量数据:	mymetrics
# -----------------------------------------------------------------------------

# -----------------------------------------------
# Bar Graphs		# mymetrics 数据画"Bar Graphs"是无意义的,只是为了演示语法
require(ggplot2)
## Loading required package: ggplot2
ggplot(mymetrics, aes(x = k)) + geom_bar()		# 默认计数是 range/30, 即 50/30=1.6
ggplot(mymetrics, aes(x = k)) + geom_bar(binwidth = 1)	# 所有的计数都变为了1
ggplot(mymetrics, aes(x = k)) + geom_bar(binwidth = 10)

ggplot(mymetrics, aes(x = WSSSE)) + geom_bar()
ggplot(mymetrics, aes(x = WSSSE)) + geom_bar(binwidth = 0.0000001)	# 这个不被接受!

# -----------------------------------------------
# 折线图
library(ggplot2)
qplot(k, WSSSE, data=mymetrics, geom=c("boxplot", "jitter"),
      fill=k, 
      main="K效能图",
      xlab= "K",
        ylab="WSSSE")


