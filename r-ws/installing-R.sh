# 下载R包

# 安装依赖包
# 下面的是在CentOS中,且非联网安装; 如果联网,应该会自动安装依赖包!
sudo yum install kernel-headers
sudo yum install glibc-headers
sudo yum install glibc-devel
sudo yum install libgomp
sudo yum install mpfr
sudo yum install cpp
sudo yum install ppl
sudo yum install cloog-ppl
sudo yum install gcc
sudo yum install libstdc++
sudo yum install gcc-c++

# 编译,make和安装
./configure --enable-R-shlib
make
sudo make install

# -----------------------------------------------------------------------------
# R Commander
install.packages("Rcmdr", dependencies = TRUE)
library(Rcmdr)
# -----------------------------------------------------------------------------
# RStudio


