# -----------------------------------------------------------------------------
# ggplot2 

# ���ذ�
library(ggplot2)

# ����ͼ�ζ���
save(p, file = "plot.rdata")
# ����ͼ�ζ���
load("plot.rdata")
# ��ͼƬ�����png��ʽ
ggsave("plot.png", width = 5, height = 5) 