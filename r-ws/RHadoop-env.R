# -----------------------------------------------------------
# RHadoop

Sys.setenv(JAVA_HOME="/opt/java/jdk1.7.0_55/jre")
# export JAVA_HOME=${JAVA_HOME}/jre
install.packages("rJava")

install.packages("~/Downloads/rhdfs_1.0.8.tar.gz",repos = NULL, type="source")

install.packages("thrift")  #package ‘thrift’ is not available (for R version 3.1.0)
install.packages("~/Downloads/rhbase_1.2.0.tar.gz",repos = NULL, type="source")

install.packages("Rcpp")
install.packages("RJSONIO")
install.packages("bitops")
install.packages("digest")
install.packages("functional")
install.packages("reshape2")
install.packages("stringr")
install.packages("plyr")
install.packages("caTools")
install.packages("~/Downloads/rmr2_3.1.1.tar.gz",repos = NULL, type="source")
install.packages("dplyr")
install.packages("R.methodsS3")
install.packages("Hmisc")
install.packages("~/Downloads/plyrmr_0.3.0.tar.gz",repos = NULL, type="source")

# export JAVA_HOME=${JAVA_HOME}/jre
#install.packages( c('rJava','RJSONIO', 'itertools', 'digest','Rcpp','httr','functional','devtools', 'plyr','reshape2'))
install.packages( c('rJava','RJSONIO', 'itertools', 'digest','Rcpp','httr','functional','devtools', 'plyr','reshape2'))

# -----------------------------------------------------------
# RHadoop hadoop-0.20.2
Sys.setenv(HADOOP_HOME="/opt/hadoop/hadoop-0.20.2")
Sys.setenv(HADOOP_CMD="/opt/hadoop/hadoop-0.20.2/bin/hadoop")
Sys.setenv(JAVA_HOME="/opt/java/jdk1.7.0_55/jre")
## Setting up HADOOP_STREAMING
Sys.setenv(HADOOP_STREAMING="/opt/hadoop/hadoop-0.20.2/contrib/streaming/hadoop-streaming-0.20.2-cdh3u3.jar")
Sys.setenv(HADOOP_HOME_WARN_SUPPRESS="TRUE")

# -----------------------------------------------------------
# explorer RHadoop
library(rJava)
library(rhdfs)
hdfs.init()
library(rmr2)

hdfs.defaults("conf")
hdfs.ls("/")
hdfs.cat("/user/hadoop/input/words-data")
