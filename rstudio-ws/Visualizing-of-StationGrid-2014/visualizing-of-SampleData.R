# -----------------------------------------------------------------------------
# 基本图形化展现
#  SampleData
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
#   修改 中的这两个语句
#				dataSetID <- "s01"  # s98
#   创建图形输出目录    s01_SampleData
#   执行 - linux版本
#       source("~/workspace_github/hadoop-ws/rstudio-ws/Visualizing-of-StationGrid-2014/read-data-of-SampleData.R")
#       source("~/workspace_github/hadoop-ws/rstudio-ws/Visualizing-of-StationGrid-2014/visualizing-of-SampleData.R")
#   执行 - windows版本
# 	    source("J:/home/hadoop/workspace_github/hadoop-ws/rstudio-ws/Visualizing-of-StationGrid-2014/read-data-of-SampleData.R")
# 	    source("J:/home/hadoop/workspace_github/hadoop-ws/Visualizing-of-StationGrid-2014/visualizing-of-SampleData.R")
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
  rootFilePathOfImage <- stringr::str_c("output_SampleData_",dataSetID, "/")

  fileHead <- paste(rootFilePathOfImage, curTaskName, sep="")
  filenameOfImage <- paste(fileHead, desc, filetype, sep=".")
  return (filenameOfImage)	# 返回值必须加上括号？
}

# 对 SampleData 进行图形化展现
visualizingSampleData <- function(fileDataOfSampleData, curTaskName) {
  cat("-----------------------------------------------------------------------------\n")
  cat("\t >>>>> 对SampleData 进行图形化展现 \n")
  
  # -----------------------------------------------------------------------------
  # 用户标识信息
  vpm <- fileDataOfSampleData[[1]]
  curdata <- vpm[c("cons_no", "cons_id", "maxValue")]	
  curdata$cons_id <- as.factor(curdata$cons_id)
  curdata
  # ---------------------------
  # -----------------------------------------------------------------------------
  # 月用电量
  vpm.v <- fileDataOfSampleData[[2]]
  curdata <- vpm.v
  curdata$cons_id <- as.factor(curdata$cons_id)
  curdata$ym <- ordered(curdata$ym)
  #str(curdata)
  
  curdata[c("cons_id","value")]
  data.frame(curdata$cons_id, curdata$ym, sqrt(curdata$value))
  # ---------------------------
  # 折线图
  p <- ggplot(curdata, aes(x=ym, y=value, group=cons_id))
  #p <- p + xlab("年月") + ylab("relative volume per month")			# 中文有问题
  p <- p + xlab("month") + ylab("relative volume per month")
  p + geom_line()
  p + geom_line(aes(colour = cons_id))
  #p + geom_line(aes(colour = cons_id, size=cons_id))
  #p + geom_line(aes(colour = cons_id, size= as.integer(cons_id) %% 5))
  ggsave(getImageFile("(2.1)样本数据的折线图", curTaskName), width = 10, height = 8)
  
  p <- ggplot(curdata, aes(x=ym, y=sqrt(value), group=cons_id))
  #p <- p + xlab("年月") + ylab("relative volume per month")			# 中文有问题
  p <- p + xlab("month") + ylab("relative volume per month")
  p + geom_line(aes(colour = cons_id))
  ggsave(getImageFile("(2.2)样本数据的折线图_sqrt", curTaskName), width = 10, height = 8)
  # ---------------------------
  # 线图
  p <- ggplot(curdata, aes(factor(ym), value))
  p <- p + xlab("年月") + ylab("relative volume per month")
  p + geom_boxplot()
  ggsave(getImageFile("(3.1)geom_boxplot", curTaskName), width = 10, height = 8)
  
  p <- ggplot(curdata, aes(factor(ym), sqrt(value)))
  p <- p + xlab("年月") + ylab("relative volume per month")
  p + geom_boxplot()
  ggsave(getImageFile("(3.2)geom_boxplot_sqrt", curTaskName), width = 10, height = 8)
  
  cat("\t 对 对SampleData 进行图形化展现 <<<<< \n")
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
#    visualizingSampleData(curdata[[1]],onlyname)
#}

for (i in 1:length(filesVector) ) {
  i
  dataitem <- datasets[i]; 
  filenameitem <- filesVector[i]
  onlyname <- strsplit(filenameitem, "\\.")[[1]][1]
  curdata <- dataitem[[1]]
  
  visualizingSampleData(curdata,onlyname)
}
