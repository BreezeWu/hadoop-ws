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

## original data
original_data = rbinom(32, n = 50, prob = 0.4)
groups = original_data
groups

## a simple tapply example:
resutl_tapply <-tapply(groups, groups, length)
resutl_tapply

## Now to the mapreduce equivalent:
library(rhdfs)
hdfs.init()
library(rmr2)

groups = to.dfs(groups)
# from.dfs(
#   mapreduce(
#     input = groups, 
#     map = function(., v) keyval(v, 1), 
#     reduce = 
#       function(k, vv) 
#         keyval(k, length(vv))))
resutl_mapred = from.dfs(  # to read the result of the executed MapReduce Job
  mapreduce(
    input = groups, 
    map = function(., v) keyval(v, 1), 
    reduce = 
      function(k, vv) 
        keyval(k, length(vv)))
  )
resutl_mapred
# --------------------------------------------------------------------------
original_data
resutl_tapply
resutl_mapred

