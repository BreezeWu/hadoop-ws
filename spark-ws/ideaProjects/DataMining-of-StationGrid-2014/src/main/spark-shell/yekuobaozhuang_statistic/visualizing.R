# {一、环境准备和基本参数} ------------------------------
# 1.加载包 ----------
library(ggplot2)
# 2.基本参数：数据集源文件清单 ----------
#g_baseWorkDir <- "J:/home/hadoop/dm-data/yekuobaozhuang-maxP/output_statistic"
g_baseWorkDir <- "/home/hadoop/dm-data/yekuobaozhuang-maxP/output_statistic"
#g_baseWorkDir_output <- paste(g_baseWorkDir, "output_ggplot2", sep="/")
g_dataList_dirname <- c("方法三统计结果_2012_用户所有最大电力都有效_230_使用一个数据集分析各个月",
                  "方法三统计结果_2012_用户所有最大电力都有效_360",
                  "方法三统计结果_2012_不检查是否最大电力都有效_3252")
g_dataList_fullpath <- lapply(g_dataList_dirname, function(x) paste(g_baseWorkDir, x, sep="/"))
g_dataList_fullpath_and_files <- lapply(g_dataList_fullpath, function(x) {
    files <- list.files(x[[1]], pattern = "*.csv", include.dirs = FALSE)
    fullpath_and_files <- list(x, files)

    return (fullpath_and_files)
  })
g_dataList_fullpathfiles <- lapply(g_dataList_fullpath_and_files, function(x) {
  fullpath <- x[[1]]
  files <- x[[2]]
  fullpathfiles <- lapply(files, function(y) paste(fullpath, y, sep="/"))
  return (fullpathfiles)
})

# 数据集类型-某数据集的文件名
g_dataList_fullpathfiles[[1]][[1]]
# g_dataList_fullpathfiles[[2]][[1]]
# g_dataList_fullpathfiles[[3]][[1]]
# 数据集类型-该类型数据的源文件路径+该类型数据的数据集文件名列表
g_dataList_fullpath_and_files[[1]][[1]] #该类型数据的源文件路径
g_dataList_fullpath_and_files[[1]][[2]] #该类型数据的数据集文件名列表

# g_dataList_fullpath_and_files[[2]][[1]]
# g_dataList_fullpath_and_files[[2]][[2]]
# g_dataList_fullpath_and_files[[3]][[1]]
# g_dataList_fullpath_and_files[[3]][[2]]

# 2.基本参数：数据集结构 ----------
g_colnames_raw <- c("运行时长", "运行时长描述", "所有用户数量", #"-1000%"
                    "0%", "10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%", "120%", "150%", "200%", "400%", "1000%")
g_colnames_special_percent <- c("运行时长", "运行时长描述", "所有用户数量","20%", "50%", "70%", "90%", "100%")

# g_colnames_raw <- c("运行时长", "运行时长描述", "所有用户数量", #"-1000%"
#                     "p0", "p10", "p20", "p30", "p40", "p50", "p60", "p70", "p80", "p90", "p100", "p120", "p150", "p200", "p400", "p1000")
# g_colnames_special_percent <- c("运行时长", "运行时长描述", "所有用户数量","p20", "p50", "p70", "p90", "p100")
# {二、函数定义} ------------------------------
# 1.读取数据 ----------
loadCsvData <- function(filename) {
  filedata = read.table(filename, header=TRUE, sep=",")	 # 首行有列名

  # 为 filedata 设置变量标签
  labels_filedata <- g_colnames_raw
  names(filedata) <- labels_filedata

  return (filedata)
}

# filename = g_dataList_fullpathfiles[[1]][[2]]
# data <- loadCsvData(filename)
# str(data)

# 2.筛选数据 ----------
# filteringData <- function(data, columns = g_colnames_special_percent) {
#   fileteredData <- data[,columns]
#   return (fileteredData)
# }

# 3.结果数据筛选和可视化 ----------
# data <- g_dataList_raw[[1]][[1]][[2]]
# filterdata <- filteringData(data)
library(ggplot2)
library(reshape2)
visualizingData <- function(data, filename_pdf, special_columns = g_colnames_special_percent) {
  # 筛选数据
  filterData <- data[,special_columns] # 取特定列
  filterData$运行时长 <- ordered(filterData$运行时长)
  filterData <- subset(filterData, (运行时长 <= 21) & (运行时长 > 0))
  # 将横表转换为纵表
  meltData <- melt.data.frame(filterData, id = c("运行时长", "运行时长描述", "所有用户数量"))

  # 作图: x轴的中文不成正确显示
  p <- ggplot(data = meltData, mapping = aes(x = 运行时长, y = value, group = variable, colour = variable))
  p <- p + layer(geom = "point", mapping = aes(size = value))
  p <- p + layer(geom = "line")
  #p

  # 转存为PDF文件
  ggsave(filename_pdf, p, width = 15, height = 6.99)
}

# {三、运行} ------------------------------
# 0.准备工作,如创建输出目录 ----------
createDirFlagList <- lapply(g_dataList_dirname, function(x) {
  thisFullPathDir <- paste(
    paste(g_baseWorkDir, x, sep="/"),
    "output_ggplot2",
    sep="/")
  flag <- dir.create(thisFullPathDir)
  return (flag)
})

# 1.读取文件并构建数据集 ----------
# g_dataList_raw 是一个 list:
#   最高层, list: 不同数据集类型
#   次高层, list: 该数据集类型下的各个数据集
#   底  层, list: 数据集id(即filename), 数据集数据
# 如: g_dataList_raw[[1]]
#     g_dataList_raw[[1]][[1]]
#     g_dataList_raw[[1]][[1]][[1]] # 数据集ID
#     g_dataList_raw[[1]][[1]][[2]] # 数据集数据
#
# x <- g_dataList_fullpath_and_files[[1]]
# y <- files[[1]]
g_dataList_raw <- lapply(g_dataList_fullpath_and_files, function(x) {
  fullpath <- x[[1]]
  files <- x[[2]]

  list_Of_id_and_data <- lapply(files, function(y) {
    thisFile <- as.character(y[[1]]) #y[[1]]
    thisId <- thisFile

    thisFullpathfile <- paste(fullpath, thisFile, sep="/")
    thisData <- loadCsvData(thisFullpathfile)

    #return (list(thisId, thisData))
    id_and_data <- list(thisId, thisData)
    return (id_and_data)
  })

  result <- list(fullpath, list_Of_id_and_data)
  return (result)
})

#str(g_dataList_raw)
# x <- g_dataList_fullpath_and_files[[1]]
# x
# fullpath <- x[[1]]
# files <- x[[2]]
# y <- files[[1]]
# y

# 2.可视化和转存为文件 ----------
# x <- g_dataList_raw[[1]]
# y <- thisDataList[[1]]
g_dataList_visualizingResult <- lapply(g_dataList_raw, function(x) {
  fullpath <- x[[1]]
  thisDataList <- x[[2]]

  visualizingResultList <- lapply(thisDataList, function(y) {
    dataSetID <- y[[1]]
    data <- y[[2]]

    fullpath_ggplot2 <- paste(fullpath, "output_ggplot2", sep="/")
    fullfile_ggplot2 <- paste(fullpath_ggplot2, dataSetID, sep="/")
    filename_pdf <- paste(fullfile_ggplot2, "pdf", sep=".")

    visualizingData(data, filename_pdf, g_colnames_special_percent)
  })

  return (visualizingResultList)
})
# -----------------------------------------------------------------------------
