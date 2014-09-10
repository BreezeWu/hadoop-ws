# -----------------------------------------------------------------------------
# 基本图形化展现
#  ClusterCenters
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
#   修改 中的这两个语句
#				dataSetID <- "s01"  # s98
#   创建图形输出目录    s01_ClusterCenters
#   执行 - linux版本
#       source("~/workspace_github/hadoop-ws/rstudio-ws/Visualizing-of-StationGrid-2014/read-data-of-ClusterCenters.R")
#       source("~/workspace_github/hadoop-ws/rstudio-ws/Visualizing-of-StationGrid-2014/visualizing-of-ClusterCenters.R")
#   执行 - windows版本
# 	    source("J:/home/hadoop/workspace_github/hadoop-ws/rstudio-ws/Visualizing-of-StationGrid-2014/read-data-of-ClusterCenters.R")
# 	    source("J:/home/hadoop/workspace_github/hadoop-ws/Visualizing-of-StationGrid-2014/visualizing-of-ClusterCenters.R")
# -----------------------------------------------------------------------------
# 加载包
library(ggplot2)

# -----------------------------------------------------------------------------
# 当前任务名称 , curTaskName

# *****************************************************************************
# 	函数定义
# *****************************************************************************
# -----------------------------------------------------------------------------
# 图形名字函数
getImageFile <- function(desc, curTaskName, filetype="pdf", subdir=dataSetID) {
  rootFilePathOfImage <- stringr::str_c("output_ClusterCenters_",dataSetID, "/")

  fileHead <- paste(rootFilePathOfImage, curTaskName, sep="")
  filenameOfImage <- paste(fileHead, desc, filetype, sep=".")
  return (filenameOfImage)	# 返回值必须加上括号？
}

# 对 ClusterCenters 进行图形化展现
visualizingClusterCenters <- function(fileDataOfClusterCenters, curTaskName) {
  cat("-----------------------------------------------------------------------------\n")
  cat("\t >>>>> 对ClusterCenters 进行图形化展现 \n")
  
  # -----------------------------------------------------------------------------
  curdata <- fileDataOfClusterCenters

  #str(curdata)
  # -----------------------------------------------------------------------------
  # clusterID 及其 数量
  org <- fileDataOfClusterCenters[[1]]
  curdata <- org[c("clusterID", "counter")]	
  curdata$clusterID <- as.factor(curdata$clusterID)
  #curdata
  
   # ---------------------------
  # 每个簇一个折线
  rownum <- nrow(org)
  for(r in 1:rownum) {
    one <- org[r,]
    # 横表变纵表
    one.v <- melt(one,  id = c("clusterID", "counter"), variable_name = "ym")
    
    p <- ggplot(one.v, aes(x=ym, y=value, group=clusterID))
    p <- p + xlab("month") + ylab("relative volume per month")
    p + geom_line(aes(colour = clusterID))
    
    namePostfix <- paste(curTaskName, r-1, sep="_c")
    ggsave(getImageFile("(2.1)簇中心折线图", namePostfix), width = 10, height = 8)
  }
  
  # ---------------------------
  
  # ---------------------------
  # 基于 y 变量的 value
  p <- ggplot(curdata, aes(x=clusterID, y=counter))
  p+ geom_bar(stat="identity")
  #ggsave("draw-graphys-ggplot2/graphys/s98_m1_k19.geom_bar_counter.pdf", width = 7, height = 6.99) 
  ggsave(getImageFile("(1.1)geom_bar_counter", curTaskName), width = 10, height = 8)
  
  p <- ggplot(curdata, aes(x=clusterID, y=sqrt(counter)))
  p+ geom_bar(stat="identity")
  ggsave(getImageFile("(1.2)geom_bar_counter_sqrt", curTaskName), width = 10, height = 8)
  
  p <- ggplot(curdata, aes(x=clusterID, y=sqrt(sqrt(counter))))
  p+ geom_bar(stat="identity")
  ggsave(getImageFile("(1.3)geom_bart_counter_sqrtsqr", curTaskName), width = 10, height = 8)
  
  # ---------------------------
  # 基于 y 变量的 统计次数
  # 数据中不能指定 y
  p <- ggplot(curdata, aes(x=clusterID))	
  p+ geom_bar(stat="bin")	# p+ geom_bar()
  
  # -----------------------------------------------------------------------------
  # 簇中心的月用电量相对比例
  vpm.v <- fileDataOfClusterCenters[[2]]
  curdata <- vpm.v
  curdata$clusterID <- as.factor(curdata$clusterID)
  curdata$ym <- ordered(curdata$ym)
  #str(curdata)
  
  curdata[c("clusterID","value")]
  data.frame(curdata$clusterID, curdata$ym, sqrt(curdata$value))
  # ---------------------------
  # 折线图
  p <- ggplot(curdata, aes(x=ym, y=value, group=clusterID))
  #p <- p + xlab("年月") + ylab("簇中心的用电量相对比例")			# 中文有问题
  p <- p + xlab("month") + ylab("relative volume per month")
  p + geom_line()
  p + geom_line(aes(colour = clusterID))
  #p + geom_line(aes(colour = clusterID, size=clusterID))
  #p + geom_line(aes(colour = clusterID, size= as.integer(clusterID) %% 5))
  ggsave(getImageFile("(2.1)簇中心折线图", curTaskName), width = 10, height = 8)
   
  p <- ggplot(curdata, aes(x=ym, y=sqrt(value), group=clusterID))
  #p <- p + xlab("年月") + ylab("簇中心的用电量")			# 中文有问题
  p <- p + xlab("month") + ylab("relative volume per month")
  p + geom_line(aes(colour = clusterID))
  ggsave(getImageFile("(2.2)簇中心折线图_sqrt", curTaskName), width = 10, height = 8)
  # ---------------------------
  # 线图
  p <- ggplot(curdata, aes(factor(ym), value))
  p <- p + xlab("年月") + ylab("簇中心的用电量")
  p + geom_boxplot()
  ggsave(getImageFile("(3.1)geom_boxplot", curTaskName), width = 10, height = 8)
  
  p <- ggplot(curdata, aes(factor(ym), sqrt(value)))
  p <- p + xlab("年月") + ylab("簇中心的用电量")
  p + geom_boxplot()
  ggsave(getImageFile("(3.2)geom_boxplot_sqrt", curTaskName), width = 10, height = 8)
  
  cat("\t 对 对ClusterCenters 进行图形化展现 <<<<< \n")
  cat("-----------------------------------------------------------------------------\n")
}

# *****************************************************************************
# 执行可视化
# *****************************************************************************
#library(foreach)

#datasets <- foreach(curdata=datasets, filename=filesVector) %do% {
#    onlyname <- strsplit(filename, "\\.")[[1]][1]
#    
#    onlyname
#    visualizingClusterCenters(curdata[[1]],onlyname)
#}

for (i in 1:length(filesVector) ) {
  i
  dataitem <- datasets[i]; 
  filenameitem <- filesVector[i]
  onlyname <- strsplit(filenameitem, "\\.")[[1]][1]
  curdata <- dataitem[[1]]
  
  visualizingClusterCenters(curdata,onlyname)
}
