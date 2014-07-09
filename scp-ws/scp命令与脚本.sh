scp ~/.bashrc hadoop@slave-hadoop-01:~/.bashrc
ssh hadoop@slave-hadoop-01 "source ~/.bashrc"

# 做不到远程使用sudo执行命令。还有别的法子吗？
#ssh hadoop@slave-hadoop-01 "sudo usermod -a -G root hadoop"

# 拷贝目录到远程 （需要事先在目标机器上保证hadoop加入root组，且/opt可读写）
# 添加hadoop用户到root用户组			sudo usermod -a -G root hadoop
# root用户组可以具有/opt的写权限	sudo chmod g+w /opt
# 按目录拷贝是很慢的，建议使用压缩后再解压的方式
# ----------------按目录拷贝
# scp -r /opt/hadoop hadoop@slave-hadoop-01:/opt
# ----------------压缩后再解压的方式
# 压缩
cd /opt; tar -zcf hadoop.tar.gz hadoop;
# 传输
scp /opt/hadoop.tar.gz hadoop@slave-hadoop-01:/opt
## 解压缩
#ssh hadoop@slave-hadoop-01 "cd /opt; tar -zxf hadoop.tar.gz"
#ssh hadoop@slave-hadoop-01 "cd /opt; tar -zxf hadoop.tar.gz; rm hadoop.tar.gz;"
## 删除远程包
#ssh hadoop@slave-hadoop-01 "rm /opt/hadoop.tar.gz"
# 删除本地包
#cd /opt; rm hadoop.tar.gz

# ----------------压缩后再解压的方式
# /home/hadoop/workspace_github/hadoop-ws/scp-ws/myshell-scp.sh
#! /bin/bash
# export VAR_SCP=hadoop
cd /opt; tar -zcf ${VAR_SCP}.tar.gz ${VAR_SCP}
scp /opt/${VAR_SCP}.tar.gz hadoop@slave-hadoop-01:/opt
ssh hadoop@slave-hadoop-01 "cd /opt; tar -zxf ${VAR_SCP}.tar.gz"
ssh hadoop@slave-hadoop-01 "rm /opt/${VAR_SCP}.tar.gz"
cd /opt; rm ${VAR_SCP}.tar.gz
export VAR_SCP=不存在的VAR_SCP变量

export VAR_SCP=hadoop
/home/hadoop/workspace_github/hadoop-ws/scp-ws/myshell-scp.sh

export VAR_SCP=jdk
/home/hadoop/workspace_github/hadoop-ws/scp-ws/myshell-scp.sh

export VAR_SCP=mahout
/home/hadoop/workspace_github/hadoop-ws/scp-ws/myshell-scp.sh

export VAR_SCP=java_cp
/home/hadoop/workspace_github/hadoop-ws/scp-ws/myshell-scp.sh

export VAR_SCP=scala
/home/hadoop/workspace_github/hadoop-ws/scp-ws/myshell-scp.sh

export VAR_SCP=spark
/home/hadoop/workspace_github/hadoop-ws/scp-ws/myshell-scp.sh

export VAR_SCP=zookeeper
/home/hadoop/workspace_github/hadoop-ws/scp-ws/myshell-scp.sh
export VAR_SCP=不存在的VAR_SCP变量
