spark-ws目录规划

HDFS上的根目录 /user/spark
hdfs dfs -ls -R /user/spark/
=========
profiling	Data Mining结果输出: 算法评估, 如多次尝试不同的k,获得其WSSSE(Within Set Sum of Squared Errors),以便找出拐点
model		Data Mining结果输出: 模型

举例:  hdfs dfs -ls -R hdfs://master-hadoop:9000/user/spark/
hdfs://master-hadoop:9000/user/spark/model
hdfs://master-hadoop:9000/user/spark/model/k-means-2014-07-16-14-48-06.perfectK-4.clusterCenters
hdfs://master-hadoop:9000/user/spark/model/k-means-2014-07-16-14-48-06.perfectK-4.clusterCenters/_SUCCESS
hdfs://master-hadoop:9000/user/spark/model/k-means-2014-07-16-14-48-06.perfectK-4.clusterCenters/part-00000
hdfs://master-hadoop:9000/user/spark/model/k-means-2014-07-16-14-55-09.perfectK-4.clusterCenters
hdfs://master-hadoop:9000/user/spark/model/k-means-2014-07-16-14-55-09.perfectK-4.clusterCenters/_SUCCESS
hdfs://master-hadoop:9000/user/spark/model/k-means-2014-07-16-14-55-09.perfectK-4.clusterCenters/part-00000
hdfs://master-hadoop:9000/user/spark/profiling
hdfs://master-hadoop:9000/user/spark/profiling/k-means-2014-07-16-14-17-38
hdfs://master-hadoop:9000/user/spark/profiling/k-means-2014-07-16-14-17-38/_SUCCESS
hdfs://master-hadoop:9000/user/spark/profiling/k-means-2014-07-16-14-17-38/part-00000

=========

=========

=========

=========
