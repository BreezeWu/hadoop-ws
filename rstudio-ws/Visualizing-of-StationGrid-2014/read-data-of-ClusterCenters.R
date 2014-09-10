# -----------------------------------------------------------------------------
# 读取spark分析处理后的数据,供画图程序使用
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
#   	source("~/workspace_github/hadoop-ws/rstudio-ws/Visualizing-of-StationGrid-2014/read-data-of-ClusterCenters.R")
#
# -----------------------------------------------------------------------------

# *****************************************************************************
# 也可以调整为每次手动选择文件
#	mydata = read.table(file.choose(), header=FALSE, sep=",")
# *****************************************************************************

# FilePath
# dataSetID <- "s01"  # s98
rootFilePathOfIn <- stringr::str_c("input_ClusterCenters_",dataSetID, "/")

# 文件名
filesVector_s01 <- c(
  "s01_L2k20_GoodM2_NotTsNotLadder_clusterCenters.csv",
  "s01_L2k20_BadF3_Ladder_clusterCenters.csv",
  "s01_L2k20_GoodM1_Ts_clusterCenters.csv",
  "s01_L2k20_GoodM2_Ladder_clusterCenters.csv",
  "s01_L2k20_GoodM1_NotTsNotLadder_clusterCenters.csv",
  "s01_L2k20_BadF2ExcludeF3_NotTsNotLadder_clusterCenters.csv",
  "s01_L2k20_BadF2ExcludeF3_Ladder_clusterCenters.csv",
  "s01_L2k20_GoodM2_Ts_clusterCenters.csv",
  "s01_L2k20_GoodM1_Ladder_clusterCenters.csv",
  "s01_L2k20_BadF3_Ts_clusterCenters.csv",
  "s01_L2k20_BadF3_NotTsNotLadder_clusterCenters.csv",
  "s01_L2k20_BadF2ExcludeF3_Ts_clusterCenters.csv"
)
filesVector_s98 <- c(
  "s98_L2k20_GoodM2_NotTsNotLadder_clusterCenters.csv",
  "s98_L2k20_BadF3_Ladder_clusterCenters.csv",
  "s98_L2k20_GoodM1_Ts_clusterCenters.csv",
  "s98_L2k20_GoodM2_Ladder_clusterCenters.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_clusterCenters.csv",
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_clusterCenters.csv",
  "s98_L2k20_BadF2ExcludeF3_Ladder_clusterCenters.csv",
  "s98_L2k20_GoodM2_Ts_clusterCenters.csv",
  "s98_L2k20_GoodM1_Ladder_clusterCenters.csv",
  "s98_L2k20_BadF3_Ts_clusterCenters.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_clusterCenters.csv",
  "s98_L2k20_BadF2ExcludeF3_Ts_clusterCenters.csv",
  
  "s98_L2k20_BadF2ExcludeF3_NotTsNotLadder_clusterCenters_v2.csv",
  "s98_L2k20_BadF3_Ladder_clusterCenters_v2.csv",
  "s98_L2k20_BadF3_NotTsNotLadder_clusterCenters_v2.csv",
  "s98_L2k20_GoodM1_Ladder_clusterCenters_v2.csv",
  "s98_L2k20_GoodM1_NotTsNotLadder_clusterCenters_v2.csv",
  "s98_L2k20_GoodM1_Ts_clusterCenters_v2.csv",
  "s98_L2k20_GoodM2_NotTsNotLadder_clusterCenters_v2.csv"
)

if ("s01" == dataSetID) filesVector <- filesVector_s01
if ("s98" == dataSetID) filesVector <- filesVector_s98

# *****************************************************************************
# 本次要读取的文件名
# *****************************************************************************
filenames <- paste(rootFilePathOfIn, filesVector, sep="")

# *****************************************************************************
# 函数定义
# *****************************************************************************
# -----------------------------------------------------------------------------
# 加载 ClusterCenters 的函数
# 		适用于 _metrics_unsorted 和 _metrics_sorted
loadClusterCenters <- function(filename) {
  # 读取文件
  filedata = read.table(filename, header=TRUE, sep=",")	 # read table file ,首行无列名(header=FALSE)
  # filedata
    
  # 为 filedata 设置变量标签
  labels_filedata <- c("clusterID", "counter", "201301", "201302", "201303", "201304", "201305", "201306", "201307", "201308", "201309", "201310", "201311", "201312")
  names(filedata) <- labels_filedata
  
  # 原始数据
  org <- filedata  
  # -----------------------------------------------------------------------------
  # 横表变纵表
  library(reshape)
  # 'pointsNum'是没有必要的,但为了保留它作为单独的一列
  v <- melt(org,  id = c("clusterID", "counter"), variable_name = "ym")	# colnames/ym -> value
  
  # -----------------------------------------------------------------------------
  #  行列转换
  t <- as.data.frame(t(org))  
  # 将 行名 成为新列 ym
  ym <- rownames(t)
  t <- data.frame(t, ym)
  
  # 返回值
  # return (filedata)
  mylist <- list(org, v, t)
  return (mylist)  
}

# *****************************************************************************
# 加载数据到变量中
# *****************************************************************************
library(foreach)

datasets <- foreach(name=filenames) %do% loadClusterCenters(name)
