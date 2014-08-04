# -----------------------------------------------------------------------------
# 数据类型 与 数据结构

# 目录

# 数据类型信息	foundamental-data-type_structure-01.R
# 数据结构信息	foundamental-data-type_structure-01.R
# 数据结构 part1	foundamental-data-type_structure-01.R

# 数据结构 part2	foundamental-data-type_structure-02.R

# 数据类型 	???

# --------------------------------------
# 数据类型信息
# show the data type of an object.
#      Get or set the type or storage mode of an object.
mode(mydata)		# 类似的有 typeof class
storage.mode(x)
# ‘mode(x) <- "newmode"’ changes the ‘mode’ of object ‘x’ to ‘newmode’. 
# This is only supported if there is an appropriate ‘as.newmode’ function

attributes(mydata)

# --------------------------------------
# 数据结构信息
# shows the structure of an object.
#      Compactly display the internal *str*ucture of an R object
str(mydata)

# 对于因子,有levels方法
levels(somefactor)
# 对于非因子,返回NULL
#	> levels(schtyp.f)
#	[1] "private" "protect" "public" 
#	> levels(schtyp.o)
#	[1] "private" "protect" "public" 
#	> levels(schtyp)
#	NULL


# --------------------------------------
# 举例
> mode(ggplot)
[1] "function"
> typeof(ggplot)
[1] "closure"
> str(ggplot)
function (data = NULL, ...)  

> x <- c(1,2,3)
> mode(x)
[1] "numeric"
> str(x)	# 这是不一样的!
 num [1:3] 1 2 3

# -----------------------------------------------------------------------------
# 数据结构 Data Structure part1
# --------------------------------------
# 1. Vectors
a <- c(1,2,5.3,6,-2,4) # numeric vector
b <- c("one","two","three") # character vector
c <- c(TRUE,TRUE,TRUE,FALSE,TRUE,FALSE) #logical vector

# Refer to elements of a vector using subscripts.
a[c(2,4)] # 2nd and 4th elements of vector

# --------------------------------------
# 2. Matrices
# All columns in a matrix must have the same mode(numeric, character, etc.) and the same length. 
# The general format is
mymatrix <- matrix(vector, nrow=r, ncol=c, byrow=FALSE, 
  	dimnames=list(char_vector_rownames, char_vector_colnames))
# byrow=TRUE indicates that the matrix should be filled by rows. 
# byrow=FALSE indicates that the matrix should be filled by columns (the default). 
# dimnames provides optional labels for the columns and rows.

# generates 5 x 4 numeric matrix 
y<-matrix(1:20, nrow=5,ncol=4)
y

# another example
cells <- c(1,26,24,68)
rnames <- c("R1", "R2")
cnames <- c("C1", "C2") 
mymatrix <- matrix(cells, nrow=2, ncol=2, byrow=TRUE,
  dimnames=list(rnames, cnames))

# --------------------------------------
# 3. Arrays
# Arrays are similar to matrices but can have more than two dimensions. See help(array) for details.

# --------------------------------------
# 4. Data Frames
# A data frame is more general than a matrix, in that different columns can have different modes (numeric, character, factor, etc.). This is similar to SAS and SPSS datasets.
d <- c(1,2,3,4)
e <- c("red", "white", "red", NA)
f <- c(TRUE,TRUE,TRUE,FALSE)
mydata <- data.frame(d,e,f)
names(mydata) <- c("ID","Color","Passed") # variable names

# There are a variety of ways to identify the elements of a data frame .
myframe[3:5] # columns 3,4,5 of data frame
myframe[c("ID","Age")] # columns ID and Age from data frame
myframe$X1 # variable x1 in the data frame

# --------------------------------------
# 5. Lists
# An ordered collection of objects (components). A list allows you to gather a variety of (possibly unrelated) objects under one name.
# example of a list with 4 components - 
# a string, a numeric vector, a matrix, and a scaler 

a <- c(1,2,5.3,6,-2,4) # numeric vector
y<-matrix(1:20, nrow=5,ncol=4)
# 组合为List
w <- list(name="Fred", mynumbers=a, mymatrix=y, age=5.3)

# example of a list containing two lists 
v <- c(list1,list2)

# Identify elements of a list using the [[]] convention.
mylist[[2]] # 2nd component of the list
mylist[["mynumbers"]] # component named mynumbers in list

# --------------------------------------
# 5. Factors
# nominal variable 和 ordinal variable
# Tell R that a variable is nominal by making it a factor. The factor stores the nominal values as a vector of integers in the range [ 1... k ] (where k is the number of unique values in the nominal variable), and an internal vector of character strings (the original values) mapped to these integers.

# -------
# 5.1 普通因子

# variable gender with 20 "male" entries and 
# 30 "female" entries 
gender <- c(rep("male",20), rep("female", 30)) 
gender <- factor(gender) 
# stores gender as 20 1s and 30 2s and associates
# 1=female, 2=male internally (alphabetically)
# R now treats gender as a nominal variable 
summary(gender)

# -------
# 5.2 有序因子
# An ordered factor is used to represent an ordinal variable.
# variable rating coded as "large", "medium", "small'
rating <- ordered(rating)
# recodes rating to 1,2,3 and associates
# 1=large, 2=medium, 3=small internally
# R now treats rating as ordinal

# 举例
# ---------------命令
schtyp <- sample(0:2, 20, replace = TRUE)
schtyp.f <- factor(schtyp, labels = c("private", "protect", "public"))
schtyp.o <- factor(schtyp, labels = c("private", "protect", "public"), ordered=TRUE) # 或者, schtyp.o2 <- ordered(schtyp, labels = c("private", "protect", "public"))

schtyp
schtyp.f
schtyp.o

str(schtyp)
str(schtyp.f)
str(schtyp.o)

# ---------------输出
> schtyp
 [1] 1 1 2 2 0 2 0 2 2 1 0 0 1 1 2 0 2 2 2 1
> schtyp.f
 [1] protect protect public  public  private public  private public  public 
[10] protect private private protect protect public  private public  public 
[19] public  protect
Levels: private protect public
> schtyp.o
 [1] protect protect public  public  private public  private public  public 
[10] protect private private protect protect public  private public  public 
[19] public  protect
Levels: private < protect < public
> 
> str(schtyp)
 int [1:20] 1 1 2 2 0 2 0 2 2 1 ...
> str(schtyp.f)
 Factor w/ 3 levels "private","protect",..: 2 2 3 3 1 3 1 3 3 2 ...	# "private","protect",..
> str(schtyp.o)
 Ord.factor w/ 3 levels "private"<"protect"<..: 2 2 3 3 1 3 1 3 3 2 ...	# "private"<"protect"<..



# 注意事项: 默认中按照 ordial variables类型进行处理
# R will treat factors as nominal variables and ordered factors as ordinal variables in statistical proceedures and graphical analyses. You can use options in the factor( ) and ordered( ) functions to control the mapping of integers to strings (overiding the alphabetical ordering). You can also use factors to create value labels. For more on factors see the UCLA page.


