# -----------------------------------------------------------------------------
# 查看当前设置

Sys.getenv("R_HOME")
Sys.getenv("HOME")
getwd()
.libPaths()	# 包路径
getOption("repos")

getOption("LANG")

# 是否支持某个功能
capabilities()["tcltk"]

# tcltk2

# -----------------------------------------------------------------------------
# 常用分析诊断语句
# 查看session information
sessionInfo()

# By default ‘traceback()’ prints the call stack of the last uncaught error, 
# i.e., the sequence of calls that lead to the error. 
#  It can also be used to print the current stack or arbitrary lists of deparsed calls.
traceback()

# -----------------------------------------------------------------------------
# 设置

#.libPaths( c( .libPaths(), "~/userLibrary") )
