
# -------------------------------------------------------------
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



