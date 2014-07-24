# -----------------------------------------------------------------------------
# 数据类型 与 数据结构

# 目录

# 数据类型信息	foundamental-data-type_structure-01.R
# 数据结构信息	foundamental-data-type_structure-01.R
# 数据结构 part1	foundamental-data-type_structure-01.R

# 数据结构 part2	foundamental-data-type_structure-02.R

# 数据类型 	???

# -----------------------------------------------------------------------------
# 数据结构 Data Structure  part2
# 见 foundamental-data-type_structure-01.R

# -----------------------------------------------------------------------------
# 数据类型 Data Types 
# .....还在后面呢! ....

# http://statistics.ats.ucla.edu/stat/r/modules/factor_variables.htm

# --------------------------------
# 非factor
ses <- c("low", "middle", "low", "low", "low", "low", "middle", "low", "middle",
    "middle", "middle", "middle", "middle", "high", "high", "low", "middle",
    "middle", "low", "high")	# 一共20个元素

is.factor(ses)
## [1] FALSE
is.character(ses)
## [1] TRUE

# --------------------------------
# 普通因子: 无序
# Creating a factor variable ses.f.bad.order based on ses.
# ---------------
ses.f.bad.order <- factor(ses)

is.factor(ses.f.bad.order)
## [1] TRUE
levels(ses.f.bad.order)
## [1] "high"   "low"    "middle"		# 这里, 标签是无序的,但变量依然是普通因子
						# 人类看他们是无序的, R看他们也是无序的	

# ---------------
ses.f <- factor(ses, levels = c("low", "middle", "high"))

is.factor(ses.f)
## [1] TRUE
levels(ses.f)
## [1] "low"    "middle" "high"			# 这里, 标签有序的,但变量依然是普通因子
						# 人类看他们是有序的, R看他们却是无序的

# --------------------------------
# 有序因子: 有序
ses.order <- ordered(ses, levels = c("low", "middle", "high"))
ses			# 没有levels
ses.f.bad.order		# Levels: high low middle
ses.f			# Levels: low middle high
ses.order		# Levels: low < middle < high	# 人类看他们是有序的, R看他们也是有序的

# --------------------------------
# 有用的 factor 函数
ses.f <- ses.f.new
read <- c(34, 39, 63, 44, 47, 47, 57, 39, 48, 47, 34, 37, 47, 47, 39, 47,
    47, 50, 28, 60)

# combining all the variables in a data frame
combo <- data.frame(schtyp, schtyp.f, ses, ses.f, read)

# -------------
# Tables are much easier to interpret when using factor variables because they add useful labels to the table and they arrange the factors in a more understandable order.

table(ses, schtyp)
##         schtyp
## ses      0 1
##   high   2 1
##   low    6 2
##   middle 2 7

table(ses.f, schtyp.f)
##         schtyp.f
## ses.f    private public
##   low          6      2
##   middle       2      7
##   high         2      1

# -------------
#Graphics are another area that benefits from the use of factor variables. As in the tables the factor variable will indicate a better ordering of the graphs as well as add useful labels.
library(lattice)
bwplot(schtyp ~ read | ses, data = combo, layout = c(2, 2))


