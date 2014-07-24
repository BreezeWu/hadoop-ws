
1. 寻找最佳k值的metric数据
hdfs dfs -cat hdfs://master-hadoop:9000//user/spark/metric/k-means-2014-07-23-21-14-41_c10_try10/* > metrics.csv

2. 最佳k聚类结果的中心点数据
hdfs dfs -cat hdfs://master-hadoop:9000/user/spark/clustercenters/k-means-2014-07-23-21-14-41_bestK43_clusterCenters/* > clustersCenter.csv
