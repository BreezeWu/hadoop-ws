#win8磁盘路径
export WIN8_F=/media/hadoop/work
export WIN8_E=/media/hadoop/软件

#1.在用户主目录下添加链接
cd ~
ln -s ${WIN8_F} ~/link-win8-f
ln -s ${WIN8_E} ~/link-win8-E

ln -s ${WIN8_F}/工作目录2014/EBooks/VPN-outlook/goagent-goagent-v3.1.18-19-g44210f6/goagent-goagent-44210f6 ~/link-goagent
