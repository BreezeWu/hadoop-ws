# -----------------------------------------------------------------------------
# 目录 
# 用图层构建图像

# 4.1 简介
# 4.2 创建绘图对象
# 4.3 图层
# 4.4 数据
# 4.5 图形属性映射
	# 4.5.1 图和图层
	# 4.5.2 设定和映射
	# 4.5.3 分组
	# 4.5.4 匹配图形属性和图形对象
# 4.6 几何对象
# 4.7 统计变换
# 4.8 位置调整
# 4.9 整合
# -----------------------------------------------------------------------------
# 4.1 简介
# -----------------------------------------------------------------------------
# 4.2 创建绘图对象

# 数据和图形属性映射
p <- ggplot(diamonds, aes(carat, price, colour = cut)) # 未添加图层之前无法显示
# -----------------------------------------------------------------------------
# 4.3 图层
# 添加一个图层： 这里只设定一个几何对象
p <- p + layer(geom = "point")
# 显示
p
print(p) # 也能显示
# 查看元数据
summary(p)

### 完整图层选项
#layer(geom, geom_params, stat, stat_params, data, mapping, position)
p <- ggplot(diamonds, aes(x = carat))
p1 <- p + layer (
  geom = "bar",
  geom_params = list(fill = "steelblue"),
  stat = "bin",
  stat_params = list(binwidth = 2)
  )
p1
# p2 与上面等价的快捷函数
p2 <- p + geom_histogram(binwidth = 2, fill = "steelblue")
p2
# -----------------------------------------------------------------------------
# 4.4 数据
# -----------------------------------------------------------------------------
# 4.5 图形属性映射

# ----------------------------------
#4.5.1 图和图层

# 1. 初始化时设定
p <- ggplot(mtcars)
summary(p)
# 2. 使用 + 修改
p <- p + aes(wt, hp)
summary(p)

# 修改图层属性的例子
p <- ggplot(mtcars, aes(x = mpg, y = wt))
p0 <- p + geom_point()
p0
# 在新图层中进行扩充或修改
p1 <- p + geom_point(aes(colour = factor(cyl))) # 添加
p2 <- p + geom_point(aes(y = disp)) # 修改
p3 <- p + geom_point(aes(y = null)) # 删除
p1
p2 # 点变了，但y轴标签未改变
p3 # 没有图形了
summary(p0)
summary(p1)
summary(p2)
summary(p3)
# ----------------------------------
# 4.5.2 设定和映射

# 1. 设定： 将图形属性设置为一个值
p <- ggplot(mtcars, aes(x = mpg, y = wt))
p + geom_point(colour ="darkblue") # 设置点的颜色为 颜色darkblue
p + geom_point(colour ="darkbluexx") # 设置点的颜色为 darkbluexx， 但没有“darkbluexx”这种颜色，报错！

# 2. 映射： 将图形属性映射到一个变量
# 下面语句映射点的颜色到 一个含有字符串"darkblue" 的变量，该变量是离散的且只有一个值，所以渲染为默认的桃红色
p + geom_point(aes(colour = "darkblue")) 

# 注意： qplot() 也有这样的区别，可以将某个值放到 I()里来实现映射
qplot(data = mtcars, x = mpg, y = wt, geom = "point", color = "darkblue") # 映射，点是桃红色
qplot(data = mtcars, x = mpg, y = wt, geom = "point", color = "darkbluexx") # 映射，点是桃红色
qplot(data = mtcars, x = mpg, y = wt, geom = "point", color = I("darkblue")) # 设置，点是深蓝色
qplot(data = mtcars, x = mpg, y = wt, geom = "point", color = I("darkbluexx")) # 设置，报错

# ----------------------------------
# 4.5.3 分组

# ==================
# "1. 个体几何对象"：表现一个观测 与 "2. 群体几何对象"：表现多个观测
# “3. 线条和路径”：介于这两者之间，每条线都由许多线段组成，而每条线段又代表两个点

# 分组用于控制“哪些观测值用哪种图形元素”

# 分组的默认值： 图形中所有离散型变来那个的交互作用。如果没有离散型变量或不能正确分组，就需要自定义分组结构。
library(nlme) 
summary(Oxboys) # Oxboys数据集

# ==================
# 1.多个分组与单个图形属性: 这类图也称为“细面图”（spaghetti plot）

# 每条线代表一个男孩，横轴时间，纵轴身高
p1_v1 <- ggplot(Oxboys, aes(age, height, group = Subject)) + geom_line()
p1_v2 <- ggplot(Oxboys, aes(age, height)) + geom_line(aes(group = Subject))
# 下面是不分组的
p2 <- ggplot(Oxboys, aes(age, height)) + geom_line() # 等价于  group  = 1
p1_v1
p1_v2
summary(p1_v1) # 有默认分组属性
summary(p1_v2) # 无默认分组属性

# ==================
# 2.不同图层上的不同分组
# 有的图层表示个体水平的数据，有个的图层则展示更大组群的统计信息

# 在男孩成长轨迹上添加一条光滑线条
p1 <- p1_v1 # p1_v2
# 无语法错误，但与预期不符合： 为每一个男孩添加了一条光滑线条
p1_1_1 <- p1 + geom_smooth(aes(group = Subject), method = "lm", se = F) 
p1_1_2 <- p1 + geom_smooth(method = "lm", se = F)  # 使用默认的分组属性
# 光滑线条图层上设置了不同的分组属性
p1_2 <- p1 + geom_smooth(aes(group = 1), method = "lm", se = F) 

p1_1_1
p1_1_2
p1_2
summary(p1_1_1)
summary(p1_1_2)
summary(p1_2)

# ==================
# 3. 修改默认分组
# 图像含有离散型变量，而你想绘制链接所有分组的线条，那么可以采用绘制使用 交互作用图、轮廓图、平行坐标图所用的策略。

# 绘制各个测量时期的身高的箱线图
# 下图不需要设定组图形属性，因为 Occasion 是一个离散型变量，所以默认的分组变量就是 Occasion
#  即 分组变量默认是X轴
boysbox <- ggplot(Oxboys, aes(Occasion, height)) + geom_boxplot()
str(Oxboys) # Occasion 是有序因子

# 在boysbox 的基础上添加个体轨迹
boysbox_lines <- boysbox + geom_line(aes(group = Subject), colour = "#3366FF")

boysbox
boysbox_lines
summary(boysbox)
summary(boysbox_lines)

# 个体的身高变化箱线图
boysbox2 <- ggplot(Oxboys, aes(Subject, height)) + geom_boxplot()
str(Oxboys) # Subject 是有序因子
# 添加时间线条
boysbox2 + geom_line(aes(group = Occasion), colour = "#3366FF")

# # 若 x 轴不是有序因子呢？_也能画出箱线图
# myOxboys <- Oxboys
# myOxboys$Subject <- as.factor(as.numeric(myOxboys$Subject))
# str(myOxboys)
# boysbox2 <- ggplot(Oxboys, aes(Subject, height)) + geom_boxplot()
# boysbox2

# ----------------------------------
# 4.5.4 匹配图形属性和图形对象

# 如何将个体图形属性映射给整体的图形属性

# 线条和路径遵循差一原则： 观测点比线段数多一，第一条线段将使用第一条观察的图形属性，第二条线段将使用第二条观测的图形属性，依次类推。
# 即 最后一条观测的图形属性不会被用电。

xgrid <- with(df, seq(min(x), max(x), length = 50))
interp <- data.frame(
	x = xgrid,
	y = approx(df$x, df$y, xout = xgrid)$y,
	colour = approx(df$x, df$colour, xout = xgrid)$y
	)
qplot(x, y, data = df, colour = colour, size = I(5) + geom_line(data = interp, size = 2)
# ----------------------------------
# ----------------------------------
# -----------------------------------------------------------------------------
# 4.6 几何对象

# 每一个几何对象都有一个默认的统计变换； 并且每一个统计变换都有一个默认的几何对象。

# 表4.2 ggplot2中的几何对象			# p75
# 表4.3 默认的统计变换和图形属性	# p76

# 等高曲线 
# no proper 'z' matrix specified
ggplot(Oxboys, aes(Subject, height, z = Occasion)) + geom_contour()
# 色带图
# -----------------------------------------------------------------------------
# 4.7 统计变换

# 为了阐明在图形中的意义，一个统计变换必须是一个位置尺度不变量，
# 即f(x+a) = f(x) +a 并且 f(b*x) = b*f(x) ，这样才能保证当改变图形的标度时，数据变换保持不变。

# 统计变换会生成新的变量，这些新生成的变量（generated variable）可以被直接调用。
# 但为了与原数据集的变量发生变量重名，生成变量的名字必须要用 .. 围起来。
ggplot(diamonds, aes(carat)) + geom_histogram(aes(y = ..density..), binwidth = 0.1)
ggplot(diamonds, aes(carat)) + geom_histogram(aes(y = ..count..), binwidth = 0.1)
ggplot(diamonds, aes(carat)) + geom_histogram(aes(y = ..x..), binwidth = 0.1)

# -----------------------------------------------------------------------------
# 4.8 位置调整
p <- ggplot(diamonds, aes(clarity, fill = cut))
p + geom_histogram()
p + geom_histogram(aes(y = ..density..), binwidth = 0.1)
p + geom_histogram(aes(y = ..density..), binwidth = 0.1, position = "stack")
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
