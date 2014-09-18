# -----------------------------------------------------------------------------
# 目录

# 3.3 绘制散点图
# 3.4 更复杂的图形示例
# 3.5 图层语法组件
	# 3.5.1 图层
	# 3.5.2 标度
	# 3.5.3 坐标系
# 3.6 数据结构
# -----------------------------------------------------------------------------
# 3.3 绘制散点图
qplot(displ, hwy, data = mpg, colour = factor(cyl))
qplot(displ, hwy, data = mpg, colour = factor(cyl), goem = "point")
# 下面语法正确但无意义
qplot(displ, hwy, data = mpg, colour = factor(cyl), geom = "line")
qplot(displ, hwy, data = mpg, colour = factor(cyl), geom = "bar", stat = "identity")
qplot(displ, data = mpg, colour = factor(cyl), geom = "bar", stat = "bin")

# 下面语句有语法错误
qplot(displ, hwy, data = mpg, colour = factor(cyl), geom = "bar", stat = "bin")
#Error : Mapping a variable to y and also using stat="bin".
#With stat="bin", it will attempt to set the y value to the count of cases in each group.
#This can result in unexpected behavior and will not be allowed in a future version of ggplot2.
#If you want y to represent counts of cases, use stat="bin" and don't map a variable to y.
#  If you want y to represent values in the data, use stat="identity".
#  See ?geom_bar for examples. (Defunct; last used in version 0.9.2)

# 含有多个几何对象的图
qplot(displ, hwy, data = mpg, colour = factor(cyl), goem = c("point", "smooth"))

newmpg <- mpg
#newmpg$hwy <- as.integer(newmpg$hwy)
newmpg$hwy <- as.numeric(newmpg$hwy)
newmpg$cyl <- ordered(newmpg$cyl)
qplot(displ, hwy, data = newmpg, colour = factor(cyl), goem = c("point", "smooth"))

str(mpg)
str(newmpg)
str(dsmall)

qplot(carat, price, data = dsmall, colour = color, geom = c("point", "smooth"))
# -----------------------------------------------------------------------------
# 3.4 更复杂的图形示例
qplot(displ, hwy, data = mpg, facets = . ~ year) + geom_smooth()
qplot(displ, hwy, data = mpg, facets = . ~ year, colour = factor(cyl)) + geom_smooth()

# 平滑曲线层拟合了一条穿过数据中间位置的平滑曲线。添加该图层需要在在我们前面介绍过的流程里再添加一步： 将数据映射到图形属性后，要对其进行统计变换（对数据进行有效的处理）
# 其他有用的统计变换包括1维和2维的封箱（binning）,求组平均（group means），分位数回归（quantile regression）和等高线（contouring）

# =============================
# 标度变换（Scaling）实际上出现在三个地方： 标度转换（transforming），标度训练(training)，标度映射(mapping)
# 标度转换先于统计变换。如取对数，去平方根，取倒数。
# -----------------------------------------------------------------------------
# 3.5 图层语法组件

# 图层 = 数据+映射+统计变换+几何形状+位置调整
# 一个图形 = 一个默认数据集和到图形属性的映射 + 1～n个图层 + 标度 + 一个坐标系统 + 分面设定
# 其中： 图层中的数据和映射是可选的，但必须有 统计变换、几何形状和位置调整
# 		每个图形属性都对应一个标度。

# ====================================
# 1. 图层
# (1) 数据和图形对象映射 （可选）
# (2) 一种统计变换
# (3) 一种几何对象
# (4) 一种位置调整方式
# ====================================
# 2. 标度
# 控制数据到图形属性的映射，并且图形上所用的每一个图形属性都对应着一个标度。每个标度都作用域图形中的所有数据，以确保从数据到图形属性映射的一致性。

# ====================================
# 3.5.3 坐标系
# coordinator,可将对象的位置映射到图形平面上。如笛卡尔(Cartesian)，半对数(semi-log)，极坐标(polar)
# 坐标系可以同时影响所有的位置变量。
# 与标度不同，坐标系还可以改变几何对象的外观。如在极坐标系中，条形看起来像扇形。
# 标度变换是在统计变换前执行的，而坐标变换是在此之后执行的。

# ====================================
# 3.5.4 分面
# 分面是条件绘图（conditioned plots）和网格绘图（trellised plots）的一般形式，通过它你可以方便地展示数据的不同子集。
# 特别是当验证在不同条件下模型是否保持一致时，分面绘图是一个非常强大的工具。

# -----------------------------------------------------------------------------
# 3.6 数据结构

# R的图形数据结构：
#	（0） 默认的数据集和映射
#	（1） 图层
#	（2） 标度
#	（3） 坐标
#	（4） 分面
#	（5） options， 用来存储图形主题选项。

# 绘图的两种方式
#	1. 一步到位： gplot()函数
#	2. 逐层叠加： ggplot()函数+ 图层函数

# 得到一个图形对象时，可以对它进行如下处理：
#（1）呈现到屏幕上， 调用print()
#（2）保存到磁盘，调用ggsave()
#（3）查看结构，调用summary()
#（4）将缓存副本保存到磁盘，调用save()。然后可以使用load()函数来重现图

p <- qplot(displ, hwy ,data=mpg, colour=factor(cyl))
p <- qplot(displ, hwy, data = mpg, facets = . ~ year, colour = factor(cyl)) + geom_smooth()
print(p)
summary(p)
## 保存图形对象
save(p, file="plot.data")
## 读入图形对象
load("plot.data")
## 将图形保存为png图片格式
ggsave("plot.png", width = 5, height = 5)
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
