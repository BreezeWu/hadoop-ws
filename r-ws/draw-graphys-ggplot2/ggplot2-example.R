# -----------------------------------------------------------------------------
# 基本图形化展现

# -----------------------------------------------------------------------------
# 图形输出到另外一个文件
#jpeg("graphys/clustercenters.s01.vpm.boxplot.簇中心的月用电量箱线图.jpg") # jpeg图很模糊
#pdf.options(encoding='ISOLatin2.enc')						# 不能解决中文名报错的问题
#pdf.options(encoding = "CP1250")						# 不能解决中文名报错的问题
#pdf(c("graphys/clustercenters.s01.vpm.boxplot.簇中心的月用电量箱线图.pdf"))	# 中文名字会报错! 

pdf("graphys/clustercenters.s01.vpm.boxplot.pdf")
# 重置图形输出
dev.off()
# -----------------------------------------------------------------------------
# 安装包
#install.packages("ggplot2")
#library("ggplot2")

#------------------------------------------------------------------------------
# 平行坐标图

# -------------------------------------
# 图例
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
#------------------------------------------------------------------------------
# 箱线图
p <- ggplot(mtcars, aes(factor(cyl), mpg))
p + geom_boxplot()

# 


# --------------------------------------------
# 方法一
p <- ggplot(vpm.v, aes(x=ym))		# defaults
p + geom_boxplot(aes(y=value))			# ok
p + geom_boxplot(aes(y=value, colour = rowid))	# ok but WRONG?????

p + stat_bin(geom="area")
p + stat_bin(geom="point")
p + stat_bin(geom="line")

p + geom_histogram(aes(fill = rowid))
p + geom_histogram(aes(y = ..density..))
# --------------------------------------------
# 方法二:  与方法一等价
p <- ggplot(vpm.v, aes(x=ym,y=value))	# defaults
p + geom_boxplot()			# ok
p + geom_boxplot(aes(colour = rowid))	# ok but WRONG?????
# --------------------------------------------
p + geom_histogram()
p + stat_bin(geom="area")
p + stat_bin(geom="point")
p + stat_bin(geom="line")

p + geom_histogram(aes(fill = clarity))
p + geom_histogram(aes(y = ..density..))
# ------------------------------------------------
dev.new()

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

