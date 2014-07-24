# -----------------------------------------------------------
# RHadoop hadoop-0.20.2
Sys.setenv(HADOOP_HOME="/opt/hadoop/hadoop-0.20.2")
Sys.setenv(HADOOP_CMD="/opt/hadoop/hadoop-0.20.2/bin/hadoop")
Sys.setenv(JAVA_HOME="/opt/java/jdk1.7.0_55/jre")
## Setting up HADOOP_STREAMING
Sys.setenv(HADOOP_STREAMING="/opt/hadoop/hadoop-0.20.2/contrib/streaming/hadoop-streaming-0.20.2-cdh3u3.jar")
Sys.setenv(HADOOP_HOME_WARN_SUPPRESS="TRUE")
# -----------------------------------------------------------
# My first mapreduce job
# https://github.com/RevolutionAnalytics/rmr2/blob/master/docs/tutorial.md

## a simple lapply example:
small.ints = 1:1000
resutl1 <- sapply(small.ints, function(x) x^2)
small.ints
resutl1

## Now to the mapreduce equivalent:
library(rhdfs)
hdfs.init()

library(rmr2)
#rmr.options(hdfs.tempdir=)

small.ints = to.dfs(1:1000)
mapreduce(
  input = small.ints, 
  map = function(k, v) cbind(v, v^2)
  )
# --------------------------------------------------------------------------
# to read the result of the executed MapReduce Job
output <- from.dfs("/tmp/file21c417dfb111") #  /tmp/file21c466fb8ef1 /tmp/file21c4712aaaeb
output
#table_output <- do.call('rbind', lapply(output$val, "[[",1))
#table_output
# view small.ints
resutl1
