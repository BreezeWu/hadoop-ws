# -----------------------------------------------------------------------------
# 

# -----------------------------------------------------------------------------
# 结构与类型识别

# 1. 结构
# shows the structure of an object.
#      Compactly display the internal *str*ucture of an R object
str(mydata)

# 2. 类型
# show the data type of an object.
#      Get or set the type or storage mode of an object.
mode(mydata)
storage.mode(x)
# ‘mode(x) <- "newmode"’ changes the ‘mode’ of object ‘x’ to ‘newmode’. 
# This is only supported if there is an appropriate ‘as.newmode’ function

# -----------------------------------------------------------------------------
# 展现对象的统计概要

# --------------------------------
# 单变量
# ‘summary’ is a generic function used to produce result summaries of the results of various model fitting functions.
# The function invokes particular ‘methods’ which depend on the ‘class’ of the first argument.
summary(mydata)
# summary区别对待连续型变量和类别型变量

library(Hmisc)
describe(mydata)

# --------------------------------
# 变量间
table()		#The table() function is useful for summarizing one or more categorical variables
with(students, table(Sex))  # install.packages(Lock5Data);library(Lock5Data); data(SleepStudy)

heart = read.table("heart-rate", header = TRUE)
students = read.csv("students.csv")


# -----------------------------------------------------------------------------
# 标签
#	http://www.statmethods.net/input/valuelabels.html

# --------------------------------
# 1. Variable Labels
# If you use the Hmisc package, you can take advantage of some labeling features.
library(Hmisc)
label(mydata$myvar) <- "Variable label for variable myvar" 	# 设置标签
# Unfortunately the label is only in effect for functions provided by the Hmisc package, such as describe(). 

#Your other option is to use the variable label as the variable name and then refer to the variable by position index.
names(mydata)[3] <- "This is the label for variable 3"
mydata[3] # list the variable

# --------------------------------
# 2. Value Labels

# To understand value labels in R, you need to understand the data structure factor.
# You can use the factor function to create your own value lables.

# variable v1 is coded 1, 2 or 3
# we want to attach value labels 1=red, 2=blue, 3=green

v1 <- c("low", "middle", "low", "low", "low", "low", "middle", "low", "middle")
v2 <- v1
mydata <- data.frame(v1, v2)

# 下面语句不能成功 因为1,2,3 原来的值不存在! 所以,v2的所有值都变为 <NA>
mydata$v2 <- factor(mydata$v2, levels = c(1,2,3), labels = c("red", "blue", "green"))

# 下面语句成功 
mydata$v2 <- v2
mydata$v2 <- factor(mydata$v2, levels = c("low", "middle"), labels = c("a", "b"))
mydata
# 下面语句成功, 但有部分值("middle")变成了  <NA>
mydata$v2 <- v2
mydata$v2 <- factor(mydata$v2, levels = c("low", "xxxxx"), labels = c("a", "b"))
mydata

# 下面语句失败 多了"xxxxx" -> "c" 并不影响!
#	Error: unexpected string constant in "mydata$v2 <- factor(mydata$v2, levels = c("low", "middle" "xxxxx""
mydata$v2 <- v2
mydata$v2 <- factor(mydata$v2, levels = c("low", "middle","xxxxx"), labels = c("a", "b", "c"))
mydata

# 下面语句成功,但有报警. 因为 "middle","xxxxx"映射为了 "b"
#	Error: unexpected string constant in "mydata$v2 <- factor(mydata$v2, levels = c("low", "middle" "xxxxx""
mydata$v2 <- v2
mydata$v2 <- factor(mydata$v2, levels = c("low", "middle","xxxxx"), labels = c("a", "b", "b"))
mydata

# 下面语句成功,但结果值是 "一个值标签1"和"一个值标签2"
mydata$v2 <- v2
mydata$v2 <- factor(mydata$v2, levels = c("low", "middle"), labels = c("一个值标签"))
mydata

# 下面语句可以成功 ???
mydata$v1 <- factor(mydata$v1, levels = c(1,2,3,4,5,6,7,8,9), labels = c("red", "blue", "green","red", "blue", "green","red", "blue", "green"))
mydata$v1 <- factor(mydata$v1, levels = c(1,2,3,4,5,6,7,8,9), labels = c("a", "b", "c","d", "e", "f","g", "h", "i"))
mydata$v1 <- factor(mydata$v1, levels = c("low", "middle", "low"), labels = c("red", "blue", "green"))

# variable y is coded 1, 3 or 5 
# we want to attach value labels 1=Low, 3=Medium, 5=High

mydata$v1 <- ordered(mydata$y,
levels = c(1,3, 5),
labels = c("Low", "Medium", "High"))
Use the factor() function for nominal data and the ordered() function for ordinal data. R statistical and graphic functions will then treat the data appriopriately.
Note: factor and ordered are used the same way, with the same arguments. The former creates factors and the later creates ordered factors.
# -----------------------------------------------------------------------------
#  Useful Functions

mydata			# print mydata 
head(mydata, n=10)	# print first 10 rows of mydata
tail(mydata, n=5)	# print last 5 rows of mydata

length(object) # number of elements or components
str(object)    # structure of an object 
class(object)  # class or type of an object
names(object)  # names

c(object,object,...)       # combine objects into a vector
cbind(object, object, ...) # combine objects as columns
rbind(object, object, ...) # combine objects as rows 

object     # prints the object

ls()       # list current objects
rm(object) # delete an object

newobject <- edit(object) # edit copy and save as newobject 
fix(object)               # edit in place

dim(object)		# dimensions of an object

