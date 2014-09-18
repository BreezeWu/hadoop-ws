# -----------------------------------------------------------------------------
# 目录

# 2.3 基本用法
# 2.4 图形属性
# 2.5 几何对象
	# 2.5.1 平滑曲线
	# 2.5.2 箱线图和扰动点图
	# 2.5.3 直方图和密度曲线图
	# 2.5.4 条形图
	# 2.5.5 时间序列中的线条图和路径图
# 2.6 分面
# 2.7 其他选项

# -----------------------------------------------------------------------------
# 2.3 基本用法
qplot(carat, price, data = dsmall)
qplot(carat, price, data = diamonds)	## 能看到一些条纹， 似乎是指数的
#qplot(price, carat, data = diamonds)

# 数据变换
qplot(log(carat), log(price), data = diamonds)	# 变成线性关系
# 参数是变量的组合
qplot(carat, x*y*z, data = diamonds)	# 接近线性： 钻石的密度（质量除以体积）是一个常量
# -----------------------------------------------------------------------------
# 2.4 图形属性

# plot中需要将分类变量转换为plot可理解的形式
# 如从 分类变量("苹果", "香蕉", "桃子")转换为颜色向颜色向量("red", "yellow", "green")

# 而qplot可以将这个过程自动完成，并能够自动生成一张图例，用以展示数据取值与图形属性之间的对应关系
# 每一个图形属性都对应了一个称为标度的函数. 颜色，形状，大小，透明度
# 颜色和形状适合于分类变量， 大小适合于连续变量
qplot(carat, price, data = dsmall, colour = color)
qplot(carat, price, data = dsmall, shape = cut)
qplot(carat, price, data = dsmall, size = table)
qplot(carat, price, data = diamonds, alpha = I(1/10))	# 分母表示经过多少次重叠之后颜色将变得不透明
qplot(carat, price, data = diamonds, alpha = I(1/100))
qplot(carat, price, data = diamonds, alpha = I(1/200))

qplot(carat, price, data = dsmall, colour = color, shape = cut, size = table)

# 也可以利用I()来手动设定图形属性
qplot(carat, price, data = dsmall, colour = I("red", "green"))	# 不能正确处理!!!!! Error in I("red", "green") : unused argument ("green")
qplot(carat, price, data = dsmall, colour = I("red"))

# -----------------------------------------------------------------------------
# 2.5 几何对象
# 几何对象描述了应该用何种对象来对数据进行展示，其中有些几何对象关联了相应的统计变换.如直方图就相当于分组计数再加上条形的几何对象。

# 二维分布: 散点图(point，默认)，平滑曲线（smooth），箱线胡须图（boxplot），折线图（path或line，线条图只能从左到右，而路径图可以是任意方向）

# 一维分布
# 连续变量: 直方图(histogram,默认)， 频率多边形(freqpoly)， 密度曲线(density)
# 离散变量： 条形图(bar)

# ======================================
# 2.5.1 平滑曲线
qplot(carat, price, data = dsmall, colour = color, geom = "point") # 默认就是 "point"
qplot(carat, price, data = dsmall, colour = color, geom = "smooth") 

qplot(carat, price, data = dsmall, colour = color, geom = c("point", "smooth")) 
qplot(carat, price, data = diamonds, colour = color, geom = c("point", "smooth")) 	## 多条曲线

qplot(carat, price, data = dsmall, geom = c("point", "smooth"))
qplot(carat, price, data = diamonds, geom = c("point", "smooth")) 	## 一条曲线

#去掉标准误差
qplot(carat, price, data = diamonds, geom = c("point", "smooth"), se = FALSE) 	## 一条曲线

# -------------------------------------
# 使用 method 选择不同的平滑器.
# n较小时(<1000)时使用“loess”，使用的是局部回归方法。平滑程度由span参数控制，取值范围从0（很不平滑）到1（很平滑）
qplot(carat, price, data = dsmall, geom = c("point", "smooth"), span = 0.2)
qplot(carat, price, data = dsmall, geom = c("point", "smooth"), span = 1)

qplot(carat, price, data = dsmall, geom = c("point", "smooth"), span = 0.2, method = "loess")
qplot(carat, price, data = dsmall, geom = c("point", "smooth"), span = 1, method = "loess")

# n较大时使用"gam", 使用 formula = y~s(x)来调用mgcv包你和一个广义可加模型。与在lm中使用样条相类似。
# 下面语句执行非常慢, 最后无拟合曲线
#qplot(carat, price, data = diamonds, geom = c("point", "smooth"), span = 0.2, method = "loess")
#qplot(carat, price, data = diamonds, geom = c("point", "smooth"), span = 1, method = "loess")

library(mgcv)

qplot(carat, price, data = diamonds, geom = c("point", "smooth"), method = "gam")	## 直线
# 不太大的数据
qplot(carat, price, data = diamonds, geom = c("point", "smooth"), method = "gam", formula = y~s(x))	## 曲线
# 较大的数据
qplot(carat, price, data = diamonds, geom = c("point", "smooth"), method = "gam", formula = y~s(x, bs = "cs"))	## 曲线

qplot(carat, price, data = dsmall, geom = c("point", "smooth"), method = "gam", formula = y~s(x))	## 曲线

# 拟合线性模型 "lm"，默认是一条直线，但可以
# 通过指定 formula = y~poly(x,2)来拟合一个二次多项式
# 或 加载splines包以使用自然样条： formula = y~ns(x,2)
#  另一个参数是自由度： 自由度取值越大，曲线的波动也越大。

qplot(carat, price, data = diamonds, geom = c("point", "smooth"), method = "lm")	## 直线
qplot(carat, price, data = diamonds, geom = c("point", "smooth"), method = "lm", formula = y~poly(x,2))	## 拟合二次多项式

library(splines)
qplot(carat, price, data = diamonds, geom = c("point", "smooth"), method = "lm", formula = y~ns(x,5))

# "rlm"与lm类似，但采用了一种更稳健的拟合算法。使得结果对异常值不太敏感。这是mass包的一部分。

# ======================================
# 2.5.2 箱线图和扰动点图
# 在数据集中，包含了一个分类变量和一个或多个连续变量，可以使用箱线图(boxplot)和扰动点图(jitter)来可视化连续变量如何随着分类变量的水平变化而变化。

# 钻石每克拉的价格随着颜色的变化情况
qplot(color, price/carat, data = diamonds, geom = "jitter")
qplot(color, price/carat, data = diamonds, geom = "boxplot")

# 扰动点图中的图形重叠问题可以通过半透明颜色来部分解决，也就是使用alpha参数, 如此，可以在扰动途中看到数据集中在哪里
qplot(color, price/carat, data = diamonds, geom = "jitter", alpha=I(1/5))
qplot(color, price/carat, data = diamonds, geom = "jitter", alpha=I(1/50))
qplot(color, price/carat, data = diamonds, geom = "jitter", alpha=I(1/200))

# 对于绕点图， 可以像一般散点图那样对其他图形属性进行控制，如size，colour，shape
# 对于箱线图， colour控制外框线的颜色，用fill设置填充颜色，以及用size调节线的粗细
qplot(color, price/carat, data = diamonds, geom = "jitter", colour = cut, alpha=I(1/5))
qplot(color, price/carat, data = diamonds, geom = "jitter", alpha=I(1/50))
qplot(color, price/carat, data = diamonds, geom = "jitter", alpha=I(1/200))

qplot(color, price/carat, data = dsmall, geom = "jitter", colour = cut, shape = clarity, alpha=I(1/5))

# ======================================
# 2.5.3 直方图和密度曲线图
# 展示单个变量的分布
# 注意： 密度曲线有一个隐含的假设，即曲线应该是无界、连续和平滑的
qplot(carat, data = diamonds, geom = "histogram")
qplot(carat, data = diamonds, geom = "density")

# 对于密度曲线图而言， adjust参数控制了曲线的平滑程度（adjust取值越大，曲线越平滑）
# 对于直方图， binwidth参数通过设定组距来调节平滑度(切分位置同样可以通过breaks参数进行显式的制定)
# 绘制直方图或密度曲线时，对平滑程度进行试验非常重要。如在直方图中，应该尝试多种组距。
qplot(carat, data = diamonds, geom = "histogram")
qplot(carat, data = diamonds, geom = "histogram", binwidth = 1)
qplot(carat, data = diamonds, geom = "histogram", binwidth = 0.1)
qplot(carat, data = diamonds, geom = "histogram", binwidth = 0.01)

# 不同组之间的分布进行对比，需要添加一个图形映射。
# 当一个分类变量被映射到某个图形属性上，集合对象会自动按这个变量进行拆分。
qplot(carat, data = diamonds, geom = "histogram", colour = color)
qplot(carat, data = diamonds, geom = "density", fill = color)

qplot(carat, data = diamonds, geom = "histogram", binwidth = 0.1, colour = color)
qplot(carat, data = diamonds, geom = "histogram", binwidth = 0.01, colour = color)


# ======================================
# 2.5.4 条形图
# 在离散变量的情形下，条形图与直方图类似。
qplot(color, data = diamonds, geom = "bar")
qplot(color, data = diamonds, geom = "bar", weight = carat)
qplot(color, data = diamonds, geom = "bar", weight = carat) + scale_y_continuous("carat")

# ======================================
# 2.5.5 时间序列中的线条图和路径图
# 线条图的x轴一般是时间，它展示了单个变量随时间变化的情况。
# 路径图则展示了两个变量随时间联动的情况，时间反映在点的顺序上。

# 1. 时序图/线条图
# 失业水平随时间变化的两张线条图。他们是用geom ="line"进行绘制的。
# 失业率的变化
qplot(date, unemploy/pop, data = economics, geom = "line")
# 失业星期数的中位数
qplot(date, uempmed, data = economics, geom = "line")

# 路径图
year <- function(x) as.POSIXlt(x)$year + 1900
qplot(unemploy/pop, uempmed, data = economics, geom = c("point", "path"))
qplot(unemploy/pop, uempmed, data = economics, geom = c("point", "path"), colour = year(date))
# -----------------------------------------------------------------------------
# 2.6 分面
# qplot()默认的分面方法是将图形拆分成若干个窗格，这可以通过形如 row_var ~ col_var 的表达式进行指定。
# 若只想指定一行或一列，可以使用 . 作为占位符。例如 row_var ~ . 会创建一个单列多行的图形矩阵

# 以颜色为条件的重量的直方图（原始值）
qplot(carat, data = diamonds, facets = color ~ ., geom = "histogram", binwidth = 0.1, xlim = c(0,3))
# 下面这句提示 undefined columns selected
qplot(carat, data = diamonds, facets = color ~ 2, geom = "histogram", binwidth = 0.1, xlim = c(0,3))
# 注意：下面语句中的data是dsmall
qplot(carat, data = dsmall, facets = color ~ table, geom = "histogram", binwidth = 0.1, xlim = c(0,3))

# 以颜色为条件的重量的直方图（比例） : ..density.. 告诉ggplot2将密度而不是频数映射到y轴。
qplot(carat, ..density.., data = diamonds, facets = color ~ ., geom = "histogram", binwidth = 0.1, xlim = c(0,3))
# -----------------------------------------------------------------------------
# 2.7 其他选项
# 标签
qplot(carat, price, data = dsmall,
	xlab = "Weight (carats)", ylab = "Price ($)",
	main = "Price-Weight relationship"
)
qplot(carat, price, data = dsmall,
	xlab = "Price ($)", ylab = "Weight (carats)",
	main = "价格-重量 相关图"
)

# 数据区域选择
qplot(carat, price/carat, data = dsmall,
	xlab = "Weight (carats)", ylab = expression(frac(price,carat)),
	main = "Price-Weight relationship@数据集dsmall",
	xlim = c(.2, 1)
)

# 将输入变量求log: 这个其实有歧义,因为x轴y轴还是显示原值
qplot(carat, price, data = dsmall, log = "xy")
# 与下面语句是不同的
qplot(log(carat), log(price), data = dsmall)


# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
