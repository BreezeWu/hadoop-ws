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
options("width"=160)                # wide display with multiple monitors
options("digits.secs"=3)            # show sub-second time stamps

r <- getOption("repos")             # hard code the US repo for CRAN
#r["CRAN"] <- "http://cran.us.r-project.org"
r["CRAN"] <- "http://mirror.bjtu.edu.cn/cran"
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
 
## Create a new invisible environment for all the functions to go in so it doesn't clutter your workspace.
.env <- new.env()
 
## Returns a logical vector TRUE for elements of X not in Y
.env$"%nin%" <- function(x, y) !(x %in% y) 
 
## Returns names(df) in single column, numbered matrix format.
.env$n <- function(df) matrix(names(df)) 
 
## Single character shortcuts for summary() and head().
.env$s <- base::summary
.env$h <- utils::head
 
## ht==headtail, i.e., show the first and last 10 items of an object
.env$ht <- function(d) rbind(head(d,10),tail(d,10))
 
## Show the first 5 rows and first 5 columns of a data frame or matrix
.env$hh <- function(d) if(class(d)=="matrix"|class(d)=="data.frame") d[1:5,1:5]
 
## Read data on clipboard.
.env$read.cb <- function(...) {
  ismac <- Sys.info()[1]=="Darwin"
  if (!ismac) read.table(file="clipboard", ...)
  else read.table(pipe("pbpaste"), ...)
}
 
## Strip row names from a data frame (stolen from plyr)
.env$unrowname <- function(x) {
    rownames(x) <- NULL
    x
}
 
## List objects and classes (from @_inundata, mod by ateucher)
.env$lsa <- function() {
{
    obj_type <- function(x) class(get(x, envir = .GlobalEnv)) # define environment
    foo = data.frame(sapply(ls(envir = .GlobalEnv), obj_type))
    foo$object_name = rownames(foo)
    names(foo)[1] = "class"
    names(foo)[2] = "object"
    return(unrowname(foo))
}
 
## List all functions in a package (also from @_inundata)
.env$lsp <-function(package, all.names = FALSE, pattern) {
    package <- deparse(substitute(package))
    ls(
        pos = paste("package", package, sep = ":"),
        all.names = all.names,
        pattern = pattern
    )
}
 
## Open Finder to the current directory on mac
.env$macopen <- function(...) if(Sys.info()[1]=="Darwin") system("open .")
.env$o       <- function(...) if(Sys.info()[1]=="Darwin") system("open .")
 
## Attach all the variables above
attach(.env)

# -------------------------------------------------------------
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
# 打印一些环境信息   在这个高级版本里面,这些命令都看不到结果了! (why?????)
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
