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

library(rhdfs)
hdfs.init()
library(rmr2)
# -------------------------------------------------------------------------
wordcount = 
  function(
    input, 
    output = NULL, 
    pattern = " "){
# -------------------------------------------------------------------------
      wc.map = 
        function(., lines) {
          keyval(
            unlist(
              strsplit(
                x = lines,
                split = pattern)),
            1)
        }

      wc.reduce =
        function(word, counts ) {
          keyval(word, sum(counts))
        }

      mapreduce(
        input = input ,
        output = output,
        input.format = "text",
        map = wc.map,
        reduce = wc.reduce,
        combine = T)
# -------------------------
  }
# -------------------------------------------------------------------------

# -------------------------------------------------------------------------
# apply the wordcount function
# wordcount = 
#   function(
#     input, 
#     output = NULL, 
#     pattern = " ")

#v_words <- c("In this case we just want to read a text file, so the text format will create key value pairs with a NULLkey and a line of text as value. You can easily specify your own input and output formats and even accept and produce binary formats with the functions")
#input <- to.dfs(v_words)
#result <- wordcount(v_words, NULL," ")

# update file to dfs
# hadoop dfs -put words-data input/
result1 <- wordcount('input/words-data', NULL," ")
result2 <- wordcount('/user/hadoop/input/words-data', NULL," ")

result3 <- wordcount('input/words-data', 'output/counts-of-words-data'," ")

result1
result2
result3  # hadoop dfs -ls output/counts-of-words-data/

# -------------------------------------------------------------------------
# get result from dfs
# hadoop dfs -cat /tmp/file21c41e830ecc

# or
result_dfs <- from.dfs("/tmp/file21c44f7709b6")
result_dfs

# or
result_dfs <- from.dfs(result)
result_dfs
