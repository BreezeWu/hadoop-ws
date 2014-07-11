# -----------------------------------------------------------
# RHIPE
Sys.setenv(HADOOP_HOME="/opt/hadoop/hadoop-0.20.2")
Sys.setenv(HADOOP_BIN="/opt/hadoop/hadoop-0.20.2/bin")
Sys.setenv(HADOOP_CONF_DIR="/opt/hadoop/hadoop-0.20.2/conf")

Sys.setenv(PKG_CONFIG_PATH="/usr/local/lib")
Sys.setenv(LD_LIBRARY_PATH="/usr/local/lib")

# -----------------------------------------------------------
#Sys.setenv(JAVA_HOME="/opt/java/jdk1.7.0_55/jre")
#install.packages("rJava")
#library(rJava)

## Loading the RHIPE library
library(Rhipe)
## initializing the RHIPE subsystem, which is used for everything. 
## RHIPE will not work if rhinit is not called.
rhinit()

# # -----------------------------------------------------------
# # mapper
# ## Defining the Map phase
# Map(function(k,v)
#   {
#     ## for generating the random deviates 
#     x <- runif(v)
#     
#     ## for emitting the key-value pairs with key – k and
#     ## value – min and max of generated random deviates.
#     rhcollect(k, c(Min=min(x),Max=max(x))
#   }
#
# # -----------------------------------------------------------
# # Defining the MapReduce job by the rhwatch() method of the RHIPE package:
#   ## Create and running a MapReduce job by following
# job = rhwatch(map=map,input=10,reduce=0,
#               output="/app/Hadoop/RHIPE/test",jobname='test')
# 
# # Reading the MapReduce output from HDFS:
# ## Read the results of job from HDFS
# result <- rhread(job)
# 
# #For displaying the result in a more readable form in the table format, 
# # use the
# following code:
#   ## Displaying the result
# outputdata <- do.call('rbind', lapply(result, "[[", 2))

# -----------------------------------------------------------
# http://piccolboni.info/2011/04/looking-for-map-reduce-language.html
# --------------
m <- expression({
    y <- strsplit(unlist(map.values)," ")
    lapply(y,function(r) rhcollect(r,T))
  })
# --------------
r <- expression(
    pre={
      count=0
    },
  
    reduce={
        count <- sum(as.numeric(unlist(reduce.values)),count)
        },post={
          rhcollect(reduce.key,count)
        }
  )
# --------------
#z=rhmr(map=m,reduce=r,comb=T,inout=c("text","sequence"),ifolder="/tmp/50mil",ofolder='/tmp/tof')
z=rhwatch(map=m,reduce=r,comb=T,inout=c("text","sequence"),ifolder="/tmp/50mil",ofolder='/tmp/tof')
rhex(z)
