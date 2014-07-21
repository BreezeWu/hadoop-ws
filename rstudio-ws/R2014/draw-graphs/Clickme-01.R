#! /usr/bin/env Rscript
# To identify the type of the script, here it is RScript

# To disable the warning massages to be printed
options(warn=-1)

# -----------------------------       Clickme      ----------------------------- #
#	https://github.com/nachocab/clickme

# 安装包
install.packages("devtools") # you don't need to run this command if you already have the devtools package installed.
library("devtools")
devtools::install_github("clickme", "nachocab")

# 加载包

# 准备数据

# 其他处理

# 画图
vioplot(x, ..., range=1.5, h=NULL, ylim=NULL, names=NULL, horizontal=FALSE,
	col="???", border="black", lty=1, lwd=1, rectCol="black", colMed="white",
	pchMed=19, at, add=FALSE, wex=1, drawRect=TRUE)

# 默认用法(数据)
usage(boxplot, "default")

boxplot(
	x, ..., range = 1.5, width = NULL,
	varwidth = FALSE, notch = FALSE, outline = TRUE,
	names, plot = TRUE, border = par("fg"), col = NULL,
	log = "", pars = list(boxwex = 0.8, staplewex = 0.5,
	outwex = 0.5), horizontal = FALSE, add = FALSE,
	at = NULL
)
# 参数说明
#	varwidth为逻辑值，若为TRUE，那么箱子的宽度与样本量的平方根成比例，这在多批数据同时画多个箱线图时比较有用
#	notch也是一个有用的逻辑参数，它决定了是否在箱子上画凹槽，凹槽所表示的实际上是中位数的一个区间估计
#	horizontal为逻辑值，设定箱线图是否水平放置；
#	add设置是否将箱线图添加到现有图形上（例：图5.34）；
#	其它参数参见boxplot函数


# 公式用法
usage(boxplot, "formula")
boxplot(formula, data = NULL, ..., subset,na.action = NULL)

# 举例
par(mar = c(2, 2.5, 0.2, 0.1))
boxplot(count ~ spray, data = InsectSprays, col = "lightgray", 3 + horizontal = TRUE, pch = 4)

# 举例
x <- c(1,2,4,10,2,4,5,7,9)
y <- c(1,2,4,10,2,4,5,7,9)
boxplot(x,y)  # boxplot(x,y, horizontal=FALSE)
boxplot(x,y, horizontal=TRUE)

# -----------------------------       小提琴图      ----------------------------- #

# 加载包
library(vioplot)
usage(vioplot)

# 准备数据

# 其他处理

# 画图
vioplot(x, ..., range=1.5, h=NULL, ylim=NULL, names=NULL, horizontal=FALSE,
	col="???", border="black", lty=1, lwd=1, rectCol="black", colMed="white",
	pchMed=19, at, add=FALSE, wex=1, drawRect=TRUE)

# -----------------------------      平行坐标图      ----------------------------- #

# ggplot2
library(ggplot2)

# 准备数据
x = iris

# 其他处理
# 压缩列名,避免图中x标签太挤
names(x)[1:4] = abbreviate(names(iris)[1:4])
print(
	ggpcp(x, vars= names(x)[-5])
	+ geom_line(size = 1.2, aes(colour= Species))
)

