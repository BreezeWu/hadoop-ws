# .Rprofile -- commands to execute at the beginning of each R session
#
# You can use this file to load packages, set options, etc.
#
# NOTE: changes in this file won't be reflected until after you quit
# and start a new session
#

# github处理
# [linux] 为env-R.Rprofile创建一个链接到用户根目录
#	ln -s ~/workspace_github/hadoop-ws/r-ws/env-R.Rprofile ~/.Rprofile

# -----------------------------------------------------------------------------
## See http://gettinggeneticsdone.blogspot.com/2013/06/customize-rprofile.html
 
## Load packages
#library(BiocInstaller)
 
## Don't show those silly significanct stars
#options(show.signif.stars=FALSE)
 
## Do you want to automatically convert strings to factor variables in a data.frame?
## WARNING!!! This makes your code less portable/reproducible.
options(stringsAsFactors=FALSE)
 
## Get the sqldf package to play nicely on OSX. No longer necessary with R 3.0.0
## From http://stackoverflow.com/questions/8219747/sqldf-package-in-r-querying-a-data-frame
## options(sqldf.driver="SQLite")
# options(gsubfn.engine = "R")
 
## Don't ask me for my CRAN mirror every time
# options("repos" = c(CRAN = "http://cran.rstudio.com/"))
options("repos" = c(CRAN = "http://mirror.bjtu.edu.cn/cran"))

## .First() run at the start of every R session. 
## Use to load commonly used packages? 
.First <- function() {
	library(ggplot2)
	library(SparkR)
	cat("\nSuccessfully loaded .Rprofile at", date(), "\n")
}
 
## .Last() run at the end of the session
.Last <- function() {
	# save command history here?
	cat("\nGoodbye at ", date(), "\n")
}

# -----------------------------------------------------------------------------
# 打印一些环境信息
cat("----------------------\n")
cat("R_HOME\n")
Sys.getenv("R_HOME")
cat("HOME\n")
Sys.getenv("HOME")
cat("working directory\n")
getwd()
cat(".libPaths\n")
.libPaths()	# 包路径
cat("----------------------\n\n")
