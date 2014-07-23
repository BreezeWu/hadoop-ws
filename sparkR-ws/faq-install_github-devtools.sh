#!/bin/bash                                                                     
# usage (eg):                                                                   
#    r_install_github devtools hadley 
# 我的脚本名为faq-devtools.sh, 所以语句是下面语句
#     ./faq-devtools.sh devtools hadley    

# 解决install_github("devtools")	# FAIL!  错误如下(why????)
#	Downloading master.zip from https://github.com/hadley/devtools/archive/master.zip
#	Error in function (type, msg, asError = TRUE)  : 
#	  Problem with the SSL CA cert (path? access rights?)
# 相关URL
#	http://stackoverflow.com/questions/23287685/devtoolsinstall-github-error-in-function-type-msg-aserror-true-not-se                                      

cd /tmp && \
mkdir R_install_github  && \
cd R_install_github && \
wget https://github.com/$2/$1/archive/master.zip && \
unzip master.zip
R CMD build $1-master && \
R CMD INSTALL $1*.tar.gz && \
cd /tmp && \
rm -rf R_install_github




