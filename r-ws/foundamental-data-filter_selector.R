# -----------------------------------------------------------------------------
# 数据过滤与选择
# 	即,过滤掉或不选择,是一种处理missing data的方法
#	http://www.statmethods.net/input/missingdata.html

# Missing Data
# In R, missing values are represented by the symbol NA (not available) . Impossible values (e.g., dividing by zero) are represented by the symbol NaN (not a number). Unlike SAS, R uses the same symbol for character and numeric data.

# -----------------------------------------------
# Testing for Missing Values

x <- c(1,2,3)
is.na(x) # returns TRUE of x is missing
y <- c(1,2,3,NA)
is.na(y) # returns a vector (F F F T)

# -----------------------------------------------
# Recoding Values to Missing
# recode 99 to missing for variable v1
# select rows where v1 is 99 and recode column v1 

v1 <- c("low", "middle", "low", "low", "low", "low", "middle", "low", "middle")
v2 <- v1
mydata <- data.frame(v1, v2)
mydata

# 选择
mydata$v2[mydata$v2 == "low"]
# 选择性更新
mydata$v2[mydata$v2 == "low"]  <- NA
mydata

mydata <- data.frame(v1, v2)
mydata$v1[mydata$v2 == "low"]  <- NA
mydata

# -----------------------------------------------
# Excluding Missing Values from Analyses
# Arithmetic functions on missing values yield missing values.
x <- c(1,2,NA,3)
mean(x) # returns NA
mean(x, na.rm=TRUE) # returns 2

x <- c(1,2,NA,3, 1/0)
x[x!=Inf]
x[x!=Inf && x!=NA]	# 所有的都变为NA了

# The function complete.cases() returns a logical vector indicating which cases are complete.
# list rows of data that have missing values 
mydata[!complete.cases(mydata),]

# The function na.omit() returns the object with listwise deletion of missing values.
# create new dataset without missing data 
newdata <- na.omit(mydata)

# Advanced Handling of Missing Data
# Most modeling functions in R offer options for dealing with missing values. You can go beyond pairwise of listwise deletion of missing values through methods such as multiple imputation. Good implementations that can be accessed through R include Amelia II, Mice, and mitools.
