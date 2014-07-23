# -----------------------------------------------------------------------------
#	在R中使用 sparkR 包
#
#	http://amplab-extras.github.io/SparkR-pkg/

# -----------------------------------------------------------------------------
# -------------------------   Installing sparkR   --------------------------- #
# ################### 在R之中安装遇到的问题: stackoverflow中有相同的问题
# 	http://stackoverflow.com/questions/23287685/devtoolsinstall-github-error-in-function-type-msg-aserror-true-not-se
install.packages("devtools")
library("devtools")		# 执行这句时遇到了问题
install_github("devtools")	# 执行这句时也遇到了问题
traceback()			# 诊断语句

## 解决办法如下
# linux 命令行
sudo apt-get install libcurl4-openssl-dev
# R命令行
install.packages('RCurl')
# 然后再执行下面语句  ---OK!
library("devtools")		# OK!
install_github("devtools")	# FAIL!  错误如下(why????)
#	Downloading master.zip from https://github.com/hadley/devtools/archive/master.zip
#	Error in function (type, msg, asError = TRUE)  : 
#	  Problem with the SSL CA cert (path? access rights?)

## 在linux名程行执行
./faq-devtools.sh devtools hadley    

## 再次在R命令行中执行下面语句可成功!	
install.packages("devtools")				# 为什么要再次执行一遍呢?
install.packages('RCurl')				# 为什么要再次执行一遍呢?
library("devtools")					# 为什么要再次执行一遍呢?
install_github("devtools")				# 为什么要再次执行一遍呢?
install_github("amplab-extras/SparkR-pkg", subdir="pkg")

library(SparkR)						# ok
#	> library(SparkR)
#	Loading required package: rJava
#	[SparkR] Initializing with classpath /home/hadoop/R/x86_64-unknown-linux-gnu-library/3.1/SparkR/sparkr-assembly-0.1.jar

# -----------------------------------------------------------------------------
# 在R环境中运用 sparkR 

# 创建sc
library(SparkR)
sc <- sparkR.init(master="local")	# sparkR 只支持local模式

lines <- textFile(sc, "hdfs://master-hadoop:9000/user/spark/metric/k-means-2014-07-23-19-44-16c_8_try8/*")
wordsPerLine <- lapply(lines, function(line) { length(unlist(strsplit(line, ","))) })

# -----------------------------------------------------------------------------
# Serializing closures
# SparkR automatically serializes the necessary variables to execute a function on the cluster. For example if you use some global variables in a function passed to lapply, SparkR will automatically capture these variables and copy them to the cluster. An example of using a random weight vector to initialize a matrix is shown below


   lines <- textFile(sc, "hdfs://data.txt")
   initialWeights <- runif(n=D, min = -1, max = 1)
   createMatrix <- function(line) {
     as.numeric(unlist(strsplit(line, " "))) %*% t(initialWeights)
   }
   # initialWeights is automatically serialized
   matrixRDD <- lapply(lines, createMatrix)

# -----------------------------------------------------------------------------
# Using existing R packages 
# 在分布式环境中,指出需要加载的包 includePackage
# SparkR also allows easy use of existing R packages inside closures. The includePackage command can be used to indicate packages that should be loaded before every closure is executed on the cluster. For example to use the Matrix in a closure applied on each partition of an RDD, you could run

  generateSparse <- function(x) {
    # Use sparseMatrix function from the Matrix package
    sparseMatrix(i=c(1, 2, 3), j=c(1, 2, 3), x=c(1, 2, 3))
  }
  includePackage(sc, Matrix)
  sparseMat <- lapplyPartition(rdd, generateSparse)
