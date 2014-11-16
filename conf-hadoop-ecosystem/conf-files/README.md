# 所有配置文件统一放在该位置,具体部署过程中,链接到该目下的相关目录

# ln -s命令如下:
#	(约定: 软件安装在/opt目录下,用户目录为/home/hadoop, github工程放在~/workspace_github/hadoop-ws目录下)

# ------------------------------------------------------
# 准备环境变量
export TMP_CONF_DIR_SOURCE=/home/hadoop/workspace_github/hadoop-ws/conf-hadoop-ecosystem/conf-files
# 目标依赖于各个环境变量

# ------------------------------------------------------
# 备份原有配置文件
# mv ${HADOOP_HOME}/conf ${HADOOP_HOME}/conf.bak
# mv ${HADOOP_HOME}/etc/hadoop ${HADOOP_HOME}/etc/hadoop.bak
# mv ${SQOOP_HOME}/conf	${SQOOP_HOME}/conf.bak
# mv ${HIVE_HOME}/conf	${HIVE_HOME}/conf.bak
# mv ${SPARK_HOME}/conf	${SPARK_HOME}/conf.bak
# mv ${ZOOKEEPER_HOME}/conf	${ZOOKEEPER_HOME}/conf.bak
# mv ${HBASE_HOME}/conf	${HBASE_HOME}/conf.bak
# ------------------------------------------------------
# 执行链接

# hadoop-0.20.2-cdh3u3
#mv ${HADOOP_HOME}/conf ${HADOOP_HOME}/conf.bak
#ln -s ${TMP_CONF_DIR_SOURCE}/hadoop-0.20.2-cdh3u3-conf ${HADOOP_HOME}/conf

# hadoop-2.2.0 hadoop-2.5.1
#mv ${HADOOP_HOME}/etc/hadoop ${HADOOP_HOME}/etc/hadoop.bak
#ln -s ${TMP_CONF_DIR_SOURCE}/hadoop-2.2.0-conf ${HADOOP_HOME}/etc/hadoop
#ln -s ${TMP_CONF_DIR_SOURCE}/hadoop-2.5.1-conf ${HADOOP_HOME}/etc/hadoop
ln -s ${TMP_CONF_DIR_SOURCE}/hadoop-${HADOOP_VERSION}-conf ${HADOOP_HOME}/etc/hadoop

# sqoop-1.4.4.bin__hadoop-2.0.4-alpha
#mv ${SQOOP_HOME}/conf	${SQOOP_HOME}/conf.bak
ln -s ${TMP_CONF_DIR_SOURCE}/sqoop-1.4.4.bin__hadoop-2.0.4-alpha-conf ${SQOOP_HOME}/conf

# hive-0.13.1
#mv ${HIVE_HOME}/conf ${HIVE_HOME}/conf.bak
ln -s ${TMP_CONF_DIR_SOURCE}/hive-0.13.1-conf ${HIVE_HOME}/conf

# spark-1.0.1
#mv ${SPARK_HOME}/conf ${SPARK_HOME}/conf.bak
ln -s ${TMP_CONF_DIR_SOURCE}/spark-1.0.1-conf ${SPARK_HOME}/conf

# zookeeper-3.4.6
#mv ${ZOOKEEPER_HOME}/conf ${ZOOKEEPER_HOME}/conf.bak
ln -s ${TMP_CONF_DIR_SOURCE}/zookeeper-3.4.6-conf ${ZOOKEEPER_HOME}/conf

# hbase-0.98.3-hadoop2
#mv ${HBASE_HOME}/conf ${HBASE_HOME}/conf.bak
ln -s ${TMP_CONF_DIR_SOURCE}/hbase-0.98.3-hadoop2-conf ${HBASE_HOME}/conf

# ------------------------------------------------------
# 观察链接结果
ls -al $HADOOP_HOME $HADOOP_HOME/etc/ $HBASE_HOME $ZOOKEEPER_HOME $SPARK_HOME $HIVE_HOME



