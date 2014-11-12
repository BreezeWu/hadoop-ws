# --------------------------------------------------------------------
### (1)standalone: linux shell CMD;  
chmod u+x ga*.R
cat gadata_sample.csv | ga_mapper.R |sort | ga_reducer.R

# --------------------------------------------------------------------
### (2)Distrubuted, From a command prompt
cd ~/workspace_github/hadoop-ws/helloworld-hadoop/wordcount-streaming-of-R
# put data into hdfs (create user's dir: hdfs dfs -mkdir -p .)
hdfs dfs -mkdir -p ga
hdfs dfs -put gadata_sample.csv ga
# run
# hadoop-0.20.2*
hadoop jar ${HADOOP_HOME}/contrib/streaming/hadoop-streaming-0.20.2*.jar \
  -input ga/gadata_sample.csv \
  -output ga/output1  \
  -file ga_mapper.R  \
  -mapper ga_mapper.R \
  -file ga_reducer.R \
  -reducer  ga_reducer.R

# hadoop-2.2.0 hadoop-2.5.1
hadoop jar ${HADOOP_HOME}/share/hadoop/tools/lib/hadoop-streaming-${HADOOP_VERSION}.jar \
  -input ga/gadata_sample.csv \
  -output ga/output1  \
  -file ga_mapper.R  \
  -mapper ga_mapper.R \
  -file ga_reducer.R \
  -reducer  ga_reducer.R

# print result
hdfs dfs -cat ga/output1/*

# --------------------------------------------------------------------
### (3)Distrubuted, From a command prompt
# Executing the Hadoop streaming job from R or an RStudio console

# 进入脚本所在目录执行R
cd ~/workspace_github/hadoop-ws/helloworld-hadoop/wordcount-streaming-of-R
R

# 在R中
setwd("~/workspace_github/hadoop-ws/helloworld-hadoop/wordcount-streaming-of-R")
system(paste("hadoop jar", 
             #"${HADOOP_HOME}/contrib/streaming/hadoop-streaming-0.20.2*.jar",
             "${HADOOP_HOME}/share/hadoop/tools/lib/hadoop-streaming-2.5.1.jar",
             "-input ga/gadata_sample.csv",
             "-output output2",
             "-file ga_mapper.R",
             "-mapper ga_mapper.R", 
             "-file ga_reducer.R",
             "-reducer ga_reducer.R"))

# get result via rhdfs
library(rhdfs)
hdfs.init()
hdfs.cat("ga/output2/part-00000")

# or get result via system command
dir <- system("hadoop dfs -ls ga/output",intern=TRUE)
out <- system("hadoop dfs -cat ga/output2/part-00000",intern=TRUE)
out

