#! /usr/bin/env Rscript
# To identify the type of the script, here it is RScript

# To disable the warning massages to be printed
options(warn=-1)

# -----------------------------   Installing R-3.1.1   ----------------------------- #
# 1. For a backport of R 3.1.1 to wheezy, simply add something like
#
#   deb http://<favorite-cran-mirror>/bin/linux/debian wheezy-cran3/
# to the file /etc/apt/sources.list on your computer. You need to substitute <favorite-cran-mirror> by one of the mirror URLs listed in
#
#   http://cran.r-project.org/mirrors.html

# 2. 然后执行下面命令
#	sudo apt-get update
#	sudo apt-get install r-base r-base-dev

# -----------------------------   Installing RHadoop   ----------------------------- #
# <<Big Data Analytics with R and Hadoop.pdf>>

# -----------------------------------------------------------
# 1. Installing the R packages
install.packages( c('rJava','RJSONIO', 'itertools', 'digest','Rcpp','httr','functional','devtools', 'plyr','reshape2'))

# 或者,通过linux命令行安装 ... 好像不成功呢!  ????
#Rscript -e "install.packages(c('rJava','RJSONIO', 'itertools', 'digest','Rcpp','httr','functional','devtools', 'plyr','reshape2')))"

# -----------------------------------------------------------
# 2. Setting environment variables

# 在R交互环境中设置
# RHadoop hadoop-2.2.0
#Sys.setenv(HADOOP_CMD="/opt/hadoop/hadoop-0.20.2/bin/hadoop")
#Sys.setenv(JAVA_HOME="/opt/java/jdk1.7.0_55/jre")
## Setting up HADOOP_STREAMING
#Sys.setenv(HADOOP_STREAMING="/opt/hadoop/hadoop-0.20.2/contrib/streaming/hadoop-streaming-0.20.2-cdh3u3.jar")
#Sys.setenv(HADOOP_HOME_WARN_SUPPRESS="TRUE")

# 在系统环境变量中设置
#export HADOOP_CMD=${HADOOP_HOME}/bin/hadoop
#export HADOOP_STREAMING=${HADOOP_HOME}/share/hadoop/tools/lib/hadoop-streaming-2.2.0.jar
# ...其他省略...

# -----------------------------------------------------------
# 3. Installing RHadoop [rhdfs, rmr, rhbase]

# 3.1  Download RHadoop packages from GitHub repository of Revolution Analytics: 
#	https://github.com/RevolutionAnalytics/rhdfs
#	https://github.com/RevolutionAnalytics/rhbase
#	https://github.com/RevolutionAnalytics/rmr2
#	https://github.com/RevolutionAnalytics/plyrmr
#	https://github.com/RevolutionAnalytics/quickcheck

# 3.2.  Installing packages.
# (1)	利用tar.gz包
#	R CMD INSTALL rmr-2.2.2.tar.gz
#	R CMD INSTALL rmr-2.2.2.tar.gz
#	R CMD INSTALL rhbase-1.2.0.tar.gz

# (2)	利用zip包, Installing packages from ZIP files
#	方法一: Rscript -e "install.packages('foo.zip', repos = NULL)"
#	方法二: To install packages from source in a Unix-alike use
#		    R CMD INSTALL -l /path/to/library pkg1 pkg2 ...

# (3) 在R中安装
library(devtools)
install_github("rmr2", "RevolutionAnalytics", subdir="pkg")	# 出现冲突

# 以安装rhdfs为例,我这次安装实际执行命令如下
# 	unzip rhdfs-master.zip; cd rhdfs-master/build
#	R CMD INSTALL rhdfs_1.0.8.tar.gz
# ...其他类推...
#	R CMD INSTALL rmr2_3.1.2.tar.gz
#	R CMD INSTALL plyrmr_0.3.0.tar.gz # ERROR: dependencies ‘dplyr’, ‘R.methodsS3’, ‘Hmisc’ are not available for package ‘plyrmr’
#					  # 	install.packages( c('dplyr','R.methodsS3', 'Hmisc'))



# -----------------------------   explorer RHadoop   ----------------------------- #
# explorer RHadoop
library(rJava)
library(rhdfs)
hdfs.init()
library(rmr2)

hdfs.defaults("conf")
hdfs.ls("/")

hdfs.cat("/user/hadoop/input/passwd")	# hdfs.cat("/user/hadoop/input/*") 这条语句失败!不支持*

# -----------------------------   wordcount using RHadoop   ----------------------------- #
## loading the libraries
library(rhdfs)
library(rmr2)
## initializing the RHadoop
hdfs.init()
# defining the input data
small.ints = to.dfs(1:10)
## Defining the MapReduce job
mapreduce(
  # defining input parameters as small.ints hdfs object, map parameter as
  # function to calculate the min and max for generated random deviates.
  input = small.ints, 
  map = function(k, v)
  {
    lapply(seq_along(v), function(r){
      x <- runif(v[[r]])
      keyval(r,c(max(x),min(x)))
    })})

# --------------------------------------------------------------------------
# to read the result of the executed MapReduce Job
output <- from.dfs("/tmp/file21c4712aaaeb") #  /tmp/file21c466fb8ef1 /tmp/file21c4712aaaeb
output
table_output <- do.call('rbind', lapply(output$val, "[[",2))
table_output
# view small.ints
small.ints

# -------------------------------------------------------------------------
# comparision
# small.ints2 = 1:10
# lapply(small.ints2, function(x) x^2)





