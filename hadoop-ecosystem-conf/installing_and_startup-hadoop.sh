# -----------------------------------------------------------------------------
### 一. 安装

# -----------------------------------------------------------------------------
### 二. 启动服务
# -------------------------------------
# 1. 方式一
# Start Hadoop Daemons by running the following commands:
#For namenode:
hadoop-daemon.sh start namenode
#For datanode:
hadoop-daemon.sh start datanode
#For Resource Manager:
yarn-daemon.sh start resourcemanager
#For node manager:
yarn-daemon.sh start nodemanager
#For Job History Server :
mr-jobhistory-daemon.sh start historyserver

# -------------------------------------
# 2. 方式二
# Hadoop can also be started using the following commands from /usr/local/hadoop/sbin/ foler:
start-dfs.sh
start-yarn.sh

# -----------------------------------------------------------------------------
### 三. 运行样例

