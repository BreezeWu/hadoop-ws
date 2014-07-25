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
#	http://stackoverflow.com/questions/1189759/expert-r-users-whats-in-your-rprofile

#options("width"=160)                # wide display with multiple monitors
#options("digits.secs"=3)            # show sub-second time stamps

#r <- getOption("repos")             # hard code the US repo for CRAN
#r["CRAN"] <- "http://cran.us.r-project.org"
#options(repos = r)
#rm(r)
#options("repos" = c(CRAN = "http://mirror.bjtu.edu.cn/cran"))

## put something this is your .Rprofile to customize the defaults
#setHook(packageEvent("grDevices", "onLoad"),
#        function(...) grDevices::X11.options(width=8, height=8, 
#                                             xpos=0, pointsize=10, 
#                                             #type="nbcairo"))  # Cairo device
#                                             #type="cairo"))    # other Cairo dev
#                                             type="xlib"))      # old default

## from the AER book by Zeileis and Kleiber
#options(prompt="R> ", digits=4, show.signif.stars=FALSE)

#options("pdfviewer"="okular")         # on Linux, use okular as the pdf viewer
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
	library(reshape)
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
