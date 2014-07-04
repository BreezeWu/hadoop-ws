# .bashrc

# Source global definitions
if [ -f /etc/bashrc ]; then
	. /etc/bashrc
fi

# User specific aliases and functions

#基本环境变量及JDK环境变量
export JAVA_HOME=/opt/jdk/jdk1.7.0_55
export JRE_HOME=/opt/jdk/jdk1.7.0_55/jre
export JAVA=$JAVA_HOME/jre/bin/java
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:${PATH}
export CLASSPATH=.:$CLASSPATH:$JAVA_HOME/lib:$JRE_HOME/lib
# /opt/java_cp
export CLASSPATH=/opt/java_cp/mysql-connector-java-5.1.27.jar:/opt/java_cp/ojdbc6.jar:/opt/java_cp/ojdbc6_g.jar:${CLASSPATH}

#不同版本Hadoop定义
export HADOOP_VERSION=2.2.0
export HADOOP_HOME=/opt/hadoop/hadoop-${HADOOP_VERSION}
export PATH=${HADOOP_HOME}/bin:${HADOOP_HOME}/sbin:${PATH}
# hadoop 2.x
export HADOOP_CONF_DIR=${HADOOP_HOME}/etc/hadoop
export HADOOP_INSTALL=${HADOOP_HOME}  
export HADOOP_PREFIX=${HADOOP_INSTALL}
#export PATH=$PATH:$HADOOP_INSTALL/bin:$HADOOP_INSTALL/sbin 
export HADOOP_MAPRED_HOME=$HADOOP_INSTALL  
export HADOOP_COMMON_HOME=$HADOOP_INSTALL  
export HADOOP_HDFS_HOME=$HADOOP_INSTALL  
export YARN_HOME=$HADOOP_INSTALL  

# sqoop
#export SQOOP_HOME=/opt/sqoop/sqoop-2.0.0-SNAPSHOT-bin-hadoop200
#export SQOOP_HOME=/opt/sqoop/sqoop-1.99.3-bin-hadoop200
export SQOOP_HOME=/opt/sqoop/sqoop-1.4.4.bin__hadoop-2.0.4-alpha
#export SQOOP_HOME=/opt/sqoop/sqoop-1.4.4.bin__hadoop-0.20
export PATH=${SQOOP_HOME}/bin:${PATH}
export CATALINA_BASE=${SQOOP_HOME}/server
export LOGDIR=${SQOOP_HOME}/logs/ 

# hive
#export HIVE_HOME=/opt/hive/hive-0.7.1
export HIVE_HOME=/opt/hive/apache-hive-0.13.1-bin
export PATH=${HIVE_HOME}/bin:${HIVE_HOME}/hcatalog/sbin:${PATH}

# zookeeper
export ZOOKEEPER_HOME=/opt/zookeeper/zookeeper-3.4.6
export PATH=${ZOOKEEPER_HOME}/bin:${PATH}

# hbase
#export HBASE_HOME=/opt/hbase/hbase-0.98.3-hadoop1
export HBASE_HOME=/opt/hbase/hbase-0.98.3-hadoop2
export PATH=${HBASE_HOME}/bin:${PATH}

#R
#export LANG=C
export LD_LIBRARY_PATH=/usr/local/lib64/R/lib
export CFLAGS="-fPIC"
export CXXFLAGS="-fPIC"
# rstudio
export LC_ALL="en_US.UTF-8"

# mahout
export MAHOUT_HOME=/opt/mahout/mahout-trunk-1.0-snapshot
export PATH=${MAHOUT_HOME}/bin:${PATH}

# others
export SCALA_HOME=/opt/scala/scala-2.11.0
export PATH=${SCALA_HOME}/bin:${PATH}
export SPARK_HOME=/opt/spark/spark-1.0.0-bin-hadoop2
export PATH=${SPARK_HOME}/bin:${PATH}
