#!/bin/bash
export SPARK_JAVA_OPTS="$*"
#MASTER=spark://ec2-99-99-99-99:7077 /usr/share/spark/bin/spark-shell
${SPARK_HOME}/bin/spark-shell


# 执行方法
# my-spark-starter-script -Dspark-cores-max=12 -Dspark.executor.memory=26000m
# my-spark-starter-script -Dspark-cores-max=12 -Dspark.executor.memory=1G

# 或者,直接如下方式
#	MASTER=spark://<spark_hostname>:7077 SPARK_DRIVER_MEMORY=1g SPARK_EXECUTOR_MEMORY=1g spark-shell
# 又如:  SPARK_DRIVER_MEMORY=1g SPARK_EXECUTOR_MEMORY=1g spark-shell

