# -----------------------------------------------------------------------------
# ggplot2 

# 加载包
library(ggplot2)

# 保存图形对象
save(p, file = "plot.rdata")
# 读入图形对象
load("plot.rdata")
# 将图片保存成png格式
ggsave("plot.png", width = 5, height = 5) 
