# -----------------------------------------------------------------------------
# ggplot2 包自带的数据及其演示
#	<<ppt.ggplot2.2007-vanderbilt.pdf>> 
#	http://ggplot2.org/resources/2007-vanderbilt.pdf

# *****************************************************************************
# -----------------------------------------------------------------------------
# 下面两个等价
qplot(diamonds$carat, diamonds$price)
qplot(carat, price, data = diamonds)

# 下面这个增加了颜色信息 根据另一个列 clarity
qplot(carat, price, data = diamonds,colour=clarity)

# 这个干了个啥?  多了一根回归线,同时,图的尺寸也缩小了
qplot(carat, price, data = diamonds,geom=c("point", "smooth"), method=lm)

# 下面两个是单变量图形
qplot(carat, data = diamonds,geom="histogram")
qplot(carat, data = diamonds,geom="histogram", binwidth = 100)	# 自定义了X轴跨度,于是 全变成一个柱子了!

# *****************************************************************************
# -----------------------------------------------------------------------------
# how to make a plot

length <- c(2,1,4,9)
width <- c(3,2,5,10)
depth <- c(4,1,15,80)
trt <- c("a","a","b","b")

mydata <- data.frame(length,width, depth,trt)

# Want a scatterplot of length vs width
# What is a scatterplot?
#	1. geom 	: 	Represent observations with points
#	2. scales	:	Linear scaling of x and y axes (scales)
#	3.		:	Cartesian coordinate system

# data ==> mapping (x,y,colour)
# mapping ==> physical "drawing" units 	# Scales: Need to convert to physical “drawing” units (and coordinate system)

# Geoms	
# Guides(from scales and coordinate systems)
# Plot

# *****************************************************************************
#-------------------------------------------------
# 组件 (简化版本)
#-------------------------------------------------
# ● Components
#• Data
#• Geometric object (geom)
#• Statistical transformation (stat)
#• Scales
#• Coordinate system
#• (+ Position adjustment, facetting)

# ● Histogram
#• Data: your data
#• Geom: bar
#• Stat: bin
#• Scale: linear
#• Coordinate system: Cartesian

# ● Scatterplot 
#• Data: your data
#• Geom: point ●
#• Stat: identity
#• Scale: linear
#• Coordinate system: Cartesian

#-------------------------------------------------
# 组件 (严谨版本)
#-------------------------------------------------
# Layers
# 	Previous description is a bit of a simplification

# ● Actually have: defaults + layers + scales + coordinate system
# ● Layer = data + mapping + geom + stat + position

# ● 即:
#	• Defaults: ????
#	• Layers: = data + mapping + geom + stat + position
#	• Scale: linear
#	• Coordinate system: Cartesian

# ● data(数据), mapping, geom, stat, position等组合为一个layer,可以有多个layer
#	子组1: 	data, mapping
#	子组2:   geom, stat, position
# ● 这些个layers,共享相同的 Defaults, Scale, Coordinate system.

#-------------------------------------------------
# 组件 (严谨版本, 实例阐释)
#-------------------------------------------------
# qplot 是从 ggplot 而来的

# 下面定义了 "数据"及其相关的layer 
# ggplot(data, mapping) +
#	layer(
#	stat = "",
#	geom = "",
#	position = "",
#	geom_parms = list(),
#	stat_params = list(),
#	)

# 快捷方式定义 geom 和 stat
# ● Usually won't write out the full specification, but use a shortcut:
#	• geom_smooth()
#	• stat_summary()
# ● Every geom has a default statistic, every statistic a default geom (but can override)

#-------------------------------------------------
# Examples 多变量
#-------------------------------------------------

d <- ggplot(diamonds,aes(x=carat, y=price))	# 数据和坐标  aes指的是..?????
# aes: Generate aesthetic mappings that describe how variables in the data are mapped to visual properties (aesthetics) of geoms.
# 	aes(x = mpg, y = wt)
#	aes(x = mpg ^ 2, y = wt / cyl)

d + geom_point()				# 画图 两变量的点图,所以是 scatter plot	类似 qplot(carat, price, data = diamonds) 
d + geom_point(aes(colour = clarity))		# 画图 两变量的点图,且颜色根据carat变化	类似 qplot(carat, price, data = diamonds,colour=clarity)
#d + geom_point(aes(colour = color)) + scale_colour_brewer()	# 这个没有画出来 why(?????) Error: Continuous value supplied to discrete scale
d + geom_point(aes(colour = clarity)) + scale_colour_brewer()	# 而这个ok!

#-------------------------------------------------
# Examples 单变量
#-------------------------------------------------
ggplot(diamonds) + geom_histogram(aes(x=price))	# 合并为一个语句

# 或者
d <- ggplot(diamonds,aes(x=carat))
d + geom_histogram()

# *****************************************************************************
# -----------------------------------------------------------------------------
# Data + mapping
# -----------------------------------------------------------------------------
#	• Data and mappings usually stay the same on a plot, so they are stored as defaults:
#	• ggplot(data, mapping = aes(x=x, y=y))
#	• aes function describes relationship, doesn't supply data

# -----------------------------------------------------------------------------
# Geoms ~ 图形几何形状
# -----------------------------------------------------------------------------
#	• Geoms define the basic "shape" of the elements on the plot
#	• Basics: point, line, polygon, bar, text
#	• Composite: boxplot, pointrange
#	• Statistic: histogram, smooth, density

# -----------------------------------------------------------------------------
# Statistics ~ 数据变形
# -----------------------------------------------------------------------------
# ● We haven't used explicitly, but they underlie many of the layers we have been creating 
#	- some geoms are really statistics in disguise:
#	• geom_histogram = stat_bin + geom_bar
#	• geom_smooth = stat_smooth + geom_ribbon
#	• geom_density = stat_density + geom_ribbon
#
# ● Separate transformation of data from its graphical representation

# -----------------------------------------------------------------------------
# 举例
# Variations on a histogram
p <- ggplot(diamonds, aes(x=price))	# defaults

p + geom_histogram()
p + stat_bin(geom="area")
p + stat_bin(geom="point")
p + stat_bin(geom="line")

p + geom_histogram(aes(fill = clarity))
p + geom_histogram(aes(y = ..density..))





# *****************************************************************************
# 一个复杂的例子
#	http://stackoverflow.com/questions/22317455/error-discrete-value-supplied-to-continuous-scale-when-manually-dodgeing-a-p

Data <- structure(list(StudyArea = structure(c(1L, 1L, 1L, 1L, 1L, 1L, 
1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 3L, 3L, 3L, 3L, 3L, 
3L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 
2L, 2L, 3L, 3L, 3L, 3L, 3L, 3L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 2L, 
2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 3L, 3L, 3L, 3L, 3L, 3L), .Label = c("Cali", 
"Colo", "Pata"), class = "factor"), ID = structure(c(1L, 2L, 
3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 
17L, 18L, 19L, 20L, 21L, 22L, 23L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 
8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 
21L, 22L, 23L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 
12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L, 22L, 23L), .Label = c("CAL_F01", 
"CAL_F17", "CAL_F19", "CAL_F23", "CAL_F43", "CAL_M33", "CAL_M36", 
"COL_P01", "COL_P03", "COL_P05", "COL_P06", "COL_P07", "COL_P08", 
"COL_P09", "COL_P10", "COL_P12", "COL_P13", "PAT_F03", "PAT_F04", 
"PAT_F05", "PAT_M02", "PAT_M03", "PAT_M04"), class = "factor"), 
    Obs = c(22L, 50L, 8L, 15L, 54L, 30L, 11L, 90L, 53L, 9L, 42L, 
    72L, 40L, 60L, 58L, 20L, 37L, 50L, 67L, 20L, 19L, 58L, 5L, 
    22L, 50L, 8L, 15L, 54L, 30L, 11L, 90L, 53L, 9L, 42L, 72L, 
    40L, 60L, 58L, 20L, 37L, 50L, 67L, 20L, 19L, 58L, 5L, 22L, 
    50L, 8L, 15L, 54L, 30L, 11L, 90L, 53L, 9L, 42L, 72L, 40L, 
    60L, 58L, 20L, 37L, 50L, 67L, 20L, 19L, 58L, 5L), variable = structure(c(1L, 
    1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 
    1L, 1L, 1L, 1L, 1L, 1L, 1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 
    2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 
    3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L, 
    3L, 3L, 3L, 3L, 3L, 3L, 3L, 3L), .Label = c("CaliPred", "ColoPred", 
    "PataPred"), class = "factor"), value = c(NA, NA, NA, NA, 
    NA, NA, NA, 77, 55, 5, 38, 84, 47, 65, 61, 25, 43, 34, 66, 
    18, 15, 34, 5, 18, 49, 6, 13, 31, 27, 12, NA, NA, NA, NA, 
    NA, NA, NA, NA, NA, NA, 32, 60, 18, 14, 32, 5, 30, 53, 7, 
    15, 49, 32, 14, 86, 62, 5, 48, 93, 52, 73, 68, 28, 47, NA, 
    NA, NA, NA, NA, NA), SE = c(NA, NA, NA, NA, NA, NA, NA, 6.31488929298672, 
    5.497653791366, 1.64290834339061, 4.51808782802661, 6.87794805206761, 
    5.48323374891446, 6.07617986453451, 5.50465952513403, 3.46640687133807, 
    4.67925642640691, 3.89436451780002, 5.95337845105602, 3.16947277410086, 
    2.65632106571484, 4.54262669561671, 1.48630436319955, 3.38512609777978, 
    4.89449176574693, 1.22686235133486, 2.55142136173085, 4.65844412010727, 
    3.84103820176873, 2.38653357702158, NA, NA, NA, NA, NA, NA, 
    NA, NA, NA, NA, 3.92516789504607, 5.92416243616404, 3.14626294785635, 
    2.67848085040132, 4.48377790891048, 1.49789328005297, 3.43538336956585, 
    4.86558548401014, 0.927955791077904, 2.51531952706457, 5.07056696779931, 
    3.85815493933999, 2.34767760968492, 6.39770960804122, 5.58054623577558, 
    1.64529737748105, 4.38254693434801, 6.95044087111244, 5.54875633450289, 
    6.16583286016463, 5.32461337007243, 3.47652151729081, 4.68257865863345, 
    NA, NA, NA, NA, NA, NA), id = c(0, 0, 0, 0, 0, 0, 0, -7.1, 
    -7.1, -7.1, -7.1, -7.1, -7.1, -7.1, -7.1, -7.1, -7.1, -17.1, 
    -17.1, -17.1, -17.1, -17.1, -17.1, -0.1, -0.1, -0.1, -0.1, 
    -0.1, -0.1, -0.1, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, 
    -16.9, -16.9, -16.9, -16.9, -16.9, -16.9, 0.1, 0.1, 0.1, 
    0.1, 0.1, 0.1, 0.1, -6.9, -6.9, -6.9, -6.9, -6.9, -6.9, -6.9, 
    -6.9, -6.9, -6.9, -17.7, -17.7, -17.7, -17.7, -17.7, -17.7
    )), .Names = c("StudyArea", "ID", "Obs", "variable", "value", 
"SE", "id"), row.names = c(NA, -69L), class = "data.frame")

# 下面语句提示错误
p <-ggplot(Data , aes(x=ID))+
  geom_point(aes(y=value, x=as.numeric(factor(ID))+id, color=variable),size=3, shape=1)+
  geom_errorbar(aes(ymin=value-SE, ymax=value+SE, x=as.numeric(factor(ID))+id, color=variable),lty = 2, cex=1, width = 0.5)+
  geom_point(aes(y=Obs),shape="*",size=12)+
  facet_wrap(~StudyArea,scales="free",ncol=1) +
  theme(axis.text.x=element_text(angle=30, hjust=1))
pp <- p + scale_color_manual(name="Area", 
                        values=c("red", "blue","darkgreen"), 
                        breaks=c("Pred", "Pred", "Pred"))+
        theme(legend.position="bottom")
pp

# 下面语句OK
library(ggplot2)
gg <- na.omit(Data)
p <-ggplot(gg, aes(x=ID))+
  geom_point(aes(y=value, color=variable),size=3,position=position_dodge(1))+
  geom_errorbar(aes(ymin=value-SE, ymax=value+SE, color=variable), position=position_dodge(1),cex=1, width = .5)+
  geom_point(aes(y=Obs),shape="*",size=12)+
  facet_wrap(~StudyArea,scales="free",ncol=1) +
  theme(axis.text.x=element_text(angle=30, hjust=1))+
  scale_color_manual(name="Area", values=c("red", "blue","darkgreen"))+
  theme(legend.position ="bottom")
p

# 下面语句OK 与上面的相比,旋转了90度
p <-ggplot(gg, aes(x=ID))+
  geom_point(aes(y=value, color=variable),size=3,position=position_dodge(1))+
  geom_errorbar(aes(ymin=value-SE, ymax=value+SE, color=variable), position=position_dodge(1),cex=1, width = .5)+
  geom_point(aes(y=Obs),shape="*",size=8)+
  facet_grid(~StudyArea,scales="free") +
  theme(axis.text.x=element_text(angle=-90, vjust=.3))+
  scale_color_manual(name="",values=c("red", "blue","darkgreen"))+
  theme(legend.position ="bottom")
p

