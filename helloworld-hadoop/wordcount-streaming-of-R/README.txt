# --------------------------------------------------------------------
### (1)standalone: linux shell CMD;  
chmod u+x ga*.R
cat gadata_sample.csv | ga_mapper.R |sort | ga_reducer.R

# --------------------------------------------------------------------
### (2)Distrubuted, From a command prompt
cd ~/workspace_R
# update script
hadoop dfs -put ga .
# run 
hadoop jar ${HADOOP_HOME}/contrib/streaming/hadoop-streaming-0.20.2*.jar \
  -input ga/gadata_sample.csv \
  -output ga/output1  \
  -file ~/workspace_R/ga/ga_mapper.R  \
  -mapper ga_mapper.R \
  -file ~/workspace_R/ga/ga_reducer.R \
  -reducer  ga_reducer.R

# hadoop-2.2.0
hadoop jar ${HADOOP_HOME}/share/hadoop/tools/lib/hadoop-streaming-2.2.0.jar \
  -input ga/gadata_sample.csv \
  -output ga/output1  \
  -file ~/workspace_R/ga/ga_mapper.R  \
  -mapper ga_mapper.R \
  -file ~/workspace_R/ga/ga_reducer.R \
  -reducer  ga_reducer.R


# --------------------------------------------------------------------
### (3)Distrubuted, From a command prompt
# Executing the Hadoop streaming job from R or an RStudio console

system(paste("hadoop jar", 
             "${HADOOP_HOME}/contrib/streaming/hadoop-streaming-0.20.2*.jar",
             "-input ga/gadata_sample.csv", 
             "-output ga/output2", 
             "-file ~/workspace_R/ga/ga_mapper.R",
             "-mapper ga_mapper.R", 
             "-file ~/workspace_R/ga/ga_reducer.R", 
             "-reducer ga_reducer.R"))

# get result via rhdfs
library(rhdfs)
hdfs.init()
hdfs.cat("ga/output2/part-00000")

# or get result via system command
dir <- system("hadoop dfs -ls ga/output",intern=TRUE)
out <- system("hadoop dfs -cat ga/output2/part-00000",intern=TRUE)

