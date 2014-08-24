# -----------------------------------------------------------------------------
# 从源代码编译 spark
#	http://spark.apache.org/docs/latest/building-with-maven.html

cd ${SPARK_HOME}

# 链接 hive配置文件
cd ${SPARK_HOME}/conf
ln -s /opt/hive/apache-hive-0.13.1-bin/conf/hive-site.xml hive-site.xml

# -----------------------------------------------------------------------------
# maven 方式编译
export MAVEN_OPTS="-Xmx2g -XX:MaxPermSize=512M -XX:ReservedCodeCacheSize=512m"

# hadoop-2.2.0 + yarn 功能
mvn -Pyarn -Phadoop-2.2 -Dhadoop.version=2.2.0 -DskipTests clean package

# 编译具有hive功能的
mvn -Phive -Pyarn -Phadoop-2.2 -Dhadoop.version=2.2.0 -DskipTests clean package

# -----------------------------------------------------------------------------
# sbt 方式编译
# 若是编译1.0版本,使用下面语句, 否则总是编译默认的hadoop-1.0.4相应版本
#  SPARK_HIVE=true SPARK_YARN=true SPARK_HADOOP_VERSION=2.2.0 sbt/sbt clean assembly

# 若是编译1.1版本,使用下面语句
sbt/sbt -Phive -Pyarn -Phadoop-2.2 -Dhadoop.version=2.2.0 clean assembly
# 编译结果 ~/workspace_github/spark/assembly/target/scala-2.10/spark-assembly-1.1.0-SNAPSHOT-hadoop2.2.0.jar

# -----------------------------------------------------------------------------
# 编译docs

cd docs
jekyll
# 编译结果 /home/hadoop/workspace_github/spark/target/javaunidoc
# 编译结果 /home/hadoop/workspace_github/spark/target/scala-2.10/unidoc

# -----------------------------------------------------------------------------
# 构建 idea 环境

./sbt/sbt -Phive -Pyarn -Phadoop-2.2 -Dhadoop.version=2.2.0 gen-idea
# 编译结果 未执行成功!

# -----------------------------------------------------------------------------
# 启动服务	on YARN
#	http://spark.apache.org/docs/latest/running-on-yarn.html
#	资源调度上: yarn

# There are two deploy modes that can be used to launch Spark applications on YARN. 
#	In yarn-cluster mode, the Spark driver runs inside an application master process which is managed by YARN on the cluster, and the client can go away after initiating the application. 
#	In yarn-client mode, the driver runs in the client process, and the application master is only used for requesting resources from YARN.

# 环境变量MASTER的设置
#	Unlike in Spark standalone and Mesos mode, in which the master’s address is specified in the “master” parameter, 
#	in YARN mode the ResourceManager’s address is picked up from the Hadoop configuration. Thus, the master parameter is simply “yarn-client” or “yarn-cluster”.
export MASTER=yarn-client
export MASTER=yarn-cluster

cd ${SPARK_HOME}

# -----------------------------------------
# in yarn-cluster mode:
#./bin/spark-submit --class path.to.your.Class --master yarn-cluster [options] <app jar> [app options]
./bin/spark-submit --class org.apache.spark.examples.SparkPi \
    --master yarn-cluster \
    --num-executors 3 \
    --driver-memory 4g \
    --executor-memory 2g \
    --executor-cores 1	\
    ./examples/target/spark-examples_2.10-1.1.0-SNAPSHOT.jar \
    10

# -----------------------------------------
# in yarn-client mode, do the same, but replace “yarn-cluster” with “yarn-client”. 
./bin/spark-submit --class org.apache.spark.examples.SparkPi \
    --master yarn-client \
    --num-executors 3 \
    --driver-memory 4g \
    --executor-memory 2g \
    --executor-cores 1	\
    ./examples/target/spark-examples_2.10-1.1.0-SNAPSHOT.jar \
    10

# -----------------------------------------
# To run spark-shell:
# 注意:  Cluster deploy mode is not applicable to Spark shells.
# 	客户端程序,即Driver.
#		Every SparkContext launches a web UI, by default on port 4040

./bin/spark-shell --master yarn-client
# 再启动一个 此时,其webui 变为 4041
./bin/spark-shell --master yarn-client


# -----------------------------------------------------------------------------
# 启动服务	on mesos
#	资源调度上: mesos

# -----------------------------------------------------------------------------
# 启动服务	on standalone

# 下面这些命令就是为standalone服务的
# ./sbin/start-master.sh - Starts a master instance on the machine the script is executed on.
# ./sbin/start-slaves.sh - Starts a slave instance on each machine specified in the conf/slaves file.
# ./sbin/start-all.sh - Starts both a master and a number of slaves as described above.
# ./sbin/stop-master.sh - Stops the master that was started via the bin/start-master.sh script.
# ./sbin/stop-slaves.sh - Stops all slave instances on the machines specified in the conf/slaves file.
# ./sbin/stop-all.sh - Stops both the master and the slaves as described above.

# --------------------------------------
# standalone 非cluster模式

# master -------------
#	http://master-hadoop:8080
#	spark://master-hadoop:7077
./sbin/start-master.sh

# client -------------
# 客户端连接 第一个客户端
./bin/spark-class org.apache.spark.deploy.worker.Worker spark://master-hadoop:7077 
# 第二个客户端 不指定"--webui-port 8082"也可以,客户链接时会提示出现冲突,但spakr会自动向后调整
./bin/spark-class org.apache.spark.deploy.worker.Worker spark://master-hadoop:7077 --webui-port 8082

# 停止
./sbin/stop-master.sh

# --------------------------------------
# standalone cluster
#	资源调度上: only supports a simple FIFO scheduler across applications

# master -------------
./sbin/start-master.sh
# client -------------
# 基于conf/slaves配置文件启动slave
./sbin/start-slaves.sh

# -----------------------------------------------------------------------------
# 监控
#	http://spark.apache.org/docs/latest/monitoring.html

# 1.运行时监控
#	very SparkContext launches a web UI, by default on port 4040

# 2.运行后监控,即Viewing After the Fact
#	f Spark is run on Mesos or YARN, start a the history server by executing:
./sbin/start-history-server.sh <base-logging-directory>
# This creates a web interface at http://<server-url>:18080 by default. 







