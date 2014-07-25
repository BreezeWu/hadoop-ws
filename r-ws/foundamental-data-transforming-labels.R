# -----------------------------------------------------------------------------
# 数据变换

# 行号信息
# 标签

# -----------------------------------------------------------------------------
# 将行号信息变为一列  (为了将来melt!)

df <- data.frame(time = 1:10,
                 a = cumsum(rnorm(10)),
                 b = cumsum(rnorm(10)),
                 c = cumsum(rnorm(10)))

rownums <- as.numeric(rownames(df))
df <- data.frame(rownums, df)

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

