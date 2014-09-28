#install.packages("ggplot2")
library(ggplot2)

set.seed(1410) ## 让样本可重复
dsmall <- diamonds[sample(nrow(diamonds), 100), ]

# 可以在source函数中指明文件的编码为utf-8：
source("源文件",echo=TRUE,encoding="utf-8")

# 在使用R语言的write系列函数产生文本文件以便其他软件可以使用这些数据时，也可能会出现乱码问题。比如，R在西文Windows中，write.table、wirte.csv等函数会按照西文系统默认的编码产生文本文件，这使得其中包含的中文就有可能得不到正确的存储。因此，在使用write.table等函数时，最好指定产生的文本文件的编码，这时只需要添加fileEncoding="中文编码名"这个选项就可以了。比如，作业里要求将数据框变量内容存入一个文本文件，假设数据框变量名为df，可以使用如下代码：
write.table(df,file="df.txt",fileEncoding="GBK")

# 。不过，对于中文Windows的Winword、Excel仅支持GB系列的编码，所以，如果生成的是在Excel中应用的CSV文本文件时，还是应采用GB2312或者GBK。

