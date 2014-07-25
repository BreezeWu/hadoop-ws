# -----------------------------------------------------------------------------
# 数据处理  字符串


# In R, a string is considered to be a character vector, but an R character vector would be an array of strings in any other programming language. Consider the following example:
str = 'string'
str[1] 			# This evaluates to 'string'.

# To get access to the individual characters in an R string, you need to use the substr function:
str = 'string'
substr(str, 1, 1) 	# This evaluates to 's'.

# For the same reason, you can’t use length to find the number of characters in a string. You have to use nchar instead.

# to put the characters back together again into strings. You can do this using paste
# 下面语句报错:	(why?????)
#	Error in paste(str1, str) : 
#	  cannot coerce type 'closure' to vector of type 'character'

str1 = 'first'
str2 = 'second'
print(paste(str1, str))

substr("Mary has a little lamb.", start=3, stop=12) 

# ----------------------------------------
# 对某一个 character vector进行处理
x <- "[1908.7666666666667"
x.substr <- substr(x, 2, nchar(x))	# 从第二个字符截取

# ----------------------------------------
# 对一系列 character vector进行处理
# 将第一列前面的"["并转换为 numeric
#vpm$V1 
x <- c("[1908.7666666666667", "[9345.291666666666", "[53.80459272097054" )
x.substr <- substr(x, 2, nchar(x))	# 从第二个字符截取

