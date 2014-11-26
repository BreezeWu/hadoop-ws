# 所有配置文件统一放在该位置,具体部署过程中,链接到该目下的相关目录

# ln -s命令如下:
#	(约定: 软件安装在/opt目录下,用户目录为/home/hadoop, github工程放在~/workspace_github/hadoop-ws目录下)

# ------------------------------------------------------
# 准备环境变量
export TMP_CONF_DIR_SOURCE_SPARK=/home/hadoop/workspace_github/hadoop-ws/conf-spark-ecosystem
# 目标依赖于各个环境变量

# ------------------------------------------------------
# 执行链接: 备份 + ln

# cassandra
#mv ${CASSANDRA_HOME}/conf ${CASSANDRA_HOME}/conf.bak
#ln -s ${TMP_CONF_DIR_SOURCE_SPARK}/cassandra-2.1.2-conf ${CASSANDRA_HOME}/conf

# spark jobserver
#mv ${SPARKJOBSERVER_HOME}/job-server/config ${SPARKJOBSERVER_HOME}/job-server/config.bak
#ln -s ${TMP_CONF_DIR_SOURCE_SPARK}/spark-jobserver-conf ${SPARKJOBSERVER_HOME}/job-server/config

# ------------------------------------------------------
# 观察链接结果
ls -al ${CASSANDRA_HOME} ${SPARKJOBSERVER_HOME}/job-server




