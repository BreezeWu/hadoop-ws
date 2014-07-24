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
# Sample Rprofile.site file 

# Things you might want to change
# options(papersize="a4") 
# options(editor="vi") 
# options(pager="internal")

# R interactive prompt 
# options(prompt="> ")
# options(continue="+ ") 

# to prefer HTML help 
# options(htmlhelp=TRUE) 

# General options 
options(tab.width = 2) 
options(width = 130)
options(graphics.record=TRUE) 

.First <- function(){
 #library(Hmisc)
 library(ggplot2)
 library(SparkR)
 cat("\nWelcome at", date(), "\n") 
}

.Last <- function(){ 
 cat("\nGoodbye at ", date(), "\n")
}


# -----------------------------------------------------------------------------
options("width"=160)                # wide display with multiple monitors
options("digits.secs"=3)            # show sub-second time stamps

r <- getOption("repos")             # hard code the US repo for CRAN
r["CRAN"] <- "http://cran.us.r-project.org"
options(repos = r)
rm(r)

## put something this is your .Rprofile to customize the defaults
setHook(packageEvent("grDevices", "onLoad"),
        function(...) grDevices::X11.options(width=8, height=8, 
                                             xpos=0, pointsize=10, 
                                             #type="nbcairo"))  # Cairo device
                                             #type="cairo"))    # other Cairo dev
                                             type="xlib"))      # old default

## from the AER book by Zeileis and Kleiber
options(prompt="R> ", digits=4, show.signif.stars=FALSE)

options("pdfviewer"="okular")         # on Linux, use okular as the pdf viewer

# -----------------------------------------------------------------------------
# 打印一些环境信息
print("----------------------")
print("--- R_HOME")
Sys.getenv("R_HOME")
print("--- HOME")
Sys.getenv("HOME")
print("--- working directory")
getwd()
print("--- libPaths")
.libPaths()	# 包路径
print("----------------------")
