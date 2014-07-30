# -----------------------------------------------------------------------------
# 使用R转换后的数据,写入文件存储
# -----------------------------------------------------------------------------
# 运行方法: 在R环境中,使用下面语句
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/read-data-of-demo.R")
# 	source("~/workspace_github/hadoop-ws/r-ws/draw-graphys-ggplot2/write-data-of-demo.R")
# -----------------------------------------------------------------------------

# FilePath
rootFilePathOfIn <- "~/workspace_github/hadoop-ws/r-ws/result-data/"
rootFilePathOfOut <- stringr::str_c(rootFilePathOfIn,"formated/")

