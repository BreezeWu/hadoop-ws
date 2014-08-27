===
<big>总览</big>
1. 在idea中开发程序,并编译成jar.
2. 利用spark-submit提交任务
===
<big>1. 在idea中开发程序,并编译成jar</big>
请参见文章
(1) 使用IntelliJ IDEA开发Spark1.0.0应用程序 http://blog.csdn.net/book_mmicky/article/details/25714549

===
<big>2. 利用spark-submit提交任务</big>

input:  input/words-data/* 或者 hdfs://master-hadoop:9000/user/hadoop/input/words-data/*

在linux terminal下:

# -----------------------------------------------------------------------------
# 检测环境
type spark-submit
# hadoop@master-hadoop:~/workspace_github/hadoop-ws/spark-ws$ type spark-submit
#spark-submit is /home/hadoop/workspace_github/spark-master-sbt/bin/spark-submit

# 进入jar包所在目录
cd ${SPARK_HOME}
cd ~/dev-src-opensource/my-spark
#hadoop@master-hadoop:~/dev-src-opensource/my-spark$ ls
#Learning-Spark-2014-NonSBT.jar

# 执行(完整命令) WordCount1
spark-submit --master local --class org.wuhz.learning.spark.simpleapp.WordCount1 --executor-memory 500m Learning-Spark-2014-NonSBT.jar hdfs://master-hadoop:9000/user/hadoop/input/words-data

# 执行(参数形式)
export input_data=hdfs://master-hadoop:9000/user/hadoop/input/words-data/*
export task_jar=Learning-Spark-2014-NonSBT.jar
export task_main_class=org.wuhz.learning.spark.simpleapp.WordCount1
#spark-submit --master local --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data}
# SparkPi
#export task_main_class=org.wuhz.learning.spark.simpleapp.SparkPi
#spark-submit --master local[10] --class ${task_main_class} --executor-memory 500m ${task_jar}  12
# -----------------------------------------------------------------------------
# 完成执行方式 WordCount1
export task_main_class=org.wuhz.learning.spark.simpleapp.WordCount1 #结果不排序
export task_main_class=org.wuhz.learning.spark.simpleapp.WordCount2 #结果排序

# local模式
spark-submit --master local --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data}
#spark-submit --master local[10] --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data}

# spark://模式
spark-submit --master spark://master-hadoop:7077 --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data}

# yarn-client模式
spark-submit --master yarn-client --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data}

# yarn-cluster模式
spark-submit --master yarn-cluster --class ${task_main_class} --executor-memory 500m ${task_jar} ${input_data}
# -----------------------------------------------------------------------------
===
参考文章:
1. 使用IntelliJ IDEA开发Spark1.0.0应用程序 http://blog.csdn.net/book_mmicky/article/details/25714549
2. 应用程序部署工具spark-submit http://blog.csdn.net/book_mmicky/article/details/25714545
