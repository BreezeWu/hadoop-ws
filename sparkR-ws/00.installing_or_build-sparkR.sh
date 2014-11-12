# -----------------------------------------------------------------------------
#	独立运行 sparkR
#
#	http://amplab-extras.github.io/SparkR-pkg/

# -------------------------   Installing sparkR   --------------------------- #
# ------------------------ 方式一: 克隆并编译 sparkR ######
# 1. clone sparkR from github
git clone git@github.com:amplab-extras/SparkR-pkg.git

# 2. 编译

# 注意:
# (1) 这个要与spark的编译版本匹配!!!
# (2) 这个要与hadoop的编译版本匹配!!!

# (一) sbt
# To develop SparkR, you can build the scala package and the R package using
# 如: SPARK_HADOOP_VERSION=2.0.0-mr1-cdh4.2.0 ./install-dev.sh

# (1)非yarn模式
### {下面语句编译后访问HDFS提示"org.apache.hadoop.ipc.RemoteException: Server IPC version 9 cannot communicate with client version 4"}
#SPARK_HADOOP_VERSION=2.2.0 ./install-dev.sh

# 下面语句编译的 sparkr-assembly-0.1.jar 很大! (why?)
# 访问hdfs时报错: org.apache.hadoop.ipc.RemoteException: Server IPC version 9 cannot communicate with client version 4
#SPARK_YARN_VERSION=2.2.0 SPARK_HADOOP_VERSION=2.2.0 ./install-dev.sh
SPARK_YARN_VERSION=2.5.1 SPARK_HADOOP_VERSION=2.5.1 ./install-dev.sh

# (2)yarn-client
#USE_YARN=1 SPARK_YARN_VERSION=2.2.0 SPARK_HADOOP_VERSION=2.2.0 ./install-dev.sh
    # sbt.ResolveException: unresolved dependency: org.apache.hadoop#hadoop-yarn-server-web-proxy;2.2.0: not found
#USE_YARN=1 SPARK_YARN_VERSION=2.4.0 SPARK_HADOOP_VERSION=2.4.0 ./install-dev.sh
USE_YARN=1 SPARK_YARN_VERSION=2.5.1 SPARK_HADOOP_VERSION=2.5.1 ./install-dev.sh

# (二) maven
# 默认编译是使用sbt, 也可以选择使用maven
# SPARK_HADOOP_VERSION=2.2.0 USE_MAVEN=1 ./install-dev.sh
### {下面语句ok: 非yarn模式}
USE_MAVEN=1 USE_YARN=1 SPARK_YARN_VERSION=2.5.1 SPARK_HADOOP_VERSION=2.5.1 ./install-dev.sh
### {下面语句ok: yarn-client}
USE_MAVEN=1 USE_YARN=1 SPARK_YARN_VERSION=2.2.0 SPARK_HADOOP_VERSION=2.2.0 ./install-dev.sh

# 3. 在R中安装"rJava" (若已经安装,可跳过)
# install.packages("rJava")

# ------------------------ 方式二: 在R环境中安装sparkR包 ######
# If you wish to try out SparkR, you can use install_github from the devtools package to directly install the package.
library(devtools)
install_github("amplab-extras/SparkR-pkg", subdir="pkg")

# 后续使用时,需要在R中自己创建sc
library(SparkR)
sc <- sparkR.init(master="local")

# -------------------------    Running sparkR    --------------------------- #
# ------------------------ 方式一: 启动 sparkR ######
# If you have cloned and built SparkR, you can start using it by launching the SparkR shell with
./sparkR

# 或者,启动时指定相关参数. 如,指明使用1G内存
SPARK_MEM=1g ./sparkR

YARN_CONF_DIR=${YARN_CONF_DIR} MASTER=yarn-client ./sparkR

# ------------------------ 遇到的问题 begin >>>>>
# 1. 若设置环境变量 MASTER=yarn-client ,执行./sparkR遇到下面问题 (why?????)	// sparkR 只支持local模式
#	
#14/07/21 19:31:36 INFO Slf4jLogger: Slf4jLogger started
#14/07/21 19:31:37 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
#Error in .jcall("RJavaTools", "Ljava/lang/Object;", "invokeMethod", cl,  : 
#  org.apache.spark.SparkException: YARN mode not available ?

# 2. 若设置环境变量 MASTER=local ,执行./sparkR则正常
#14/07/21 19:32:04 INFO Slf4jLogger: Slf4jLogger started
#14/07/21 19:32:05 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
#
# Welcome to SparkR!
# Spark context is available as sc
# ------------------------ 遇到的问题 end <<<<<

# 运行样例程序
./sparkR examples/pi.R local[2]  

# run the unit-tests for SparkR by running
#./run-tests.sh

# ------------------------ 方式二: 在R语言中加载SparkR包 ######
########## 在sparkR中 ##########
#	http://amplab-extras.github.io/SparkR-pkg/

# 创建sc对象
library(SparkR)
sc <- sparkR.init(master="local")

# 创建sc对象
library(SparkR)
sc <- sparkR.init(master="spark://<master>:7077",
                  sparkEnvir=list(spark.executor.memory="1g"))

# // sparkR 只支持local模式
# sc <- sparkR.init(master="yarn-client")

# -------------------------    explore sparkR    --------------------------- #
# ---------------------------------------
# 其他样例
#	http://amplab-extras.github.io/SparkR-pkg/
sc <- sparkR.init("local")	# 启动R时已经创建
lines <- textFile(sc, "input/words-data/*")
wordsPerLine <- lapply(lines, function(line) { length(unlist(strsplit(line, " "))) })

#
sc <- sparkR.init("local")
lines <- textFile(sc, "hdfs://master-hadoop:9000/user/hadoop/input/README.md")
wordsPerLine <- lapply(lines, function(line) { length(unlist(strsplit(line, " "))) })
collect(wordsPerLine)
