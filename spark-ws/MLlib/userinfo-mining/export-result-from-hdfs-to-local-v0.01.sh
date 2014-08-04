# -----------------------------------------------------------------------------
# 从HDFS将结果数据导出到本地文件系统
# -----------------------------------------------------------------------------

# 参数设置
# 下面四个个每次都要调整
export I_METRICS_FILE_1=/user/spark/clustering/2014-07-31/S01_M2_kmeans_20_metrics_unsorted
export I_METRICS_FILE_2=/user/spark/clustering/2014-07-31/S01_M2_kmeans_20_metrics_sorted
export I_CLUSTERCENTERS_FILE=/user/spark/clustering/2014-07-31/S01_M2_kmeans_20_clusterCenters
# 抽样/全量_单月/双月_用电量/购售电信息
export I_TASK_NAME=S01_M2_kmeans_20

# 下面这个保持不变
export O_DATA_LOCAL_FILEPATH=/home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/result-data/

cd ~/workspace_github/hadoop-ws/spark-ws/MLlib/result-data
# -----------------------------------------------------------------------------
#1. 寻找最佳k值的metric数据
#比如: hdfs dfs -cat hdfs://master-hadoop:9000/user/spark/metric/k-means-2014-07-23-21-14-41_c10_try10/* > metrics.csv

export O_METRICS_1=${I_TASK_NAME}_metrics_unsorted.csv
export O_METRICS_2=${I_TASK_NAME}_metrics_sorted.csv

hdfs dfs -cat hdfs://master-hadoop:9000/${I_METRICS_FILE_1}/* > ${O_DATA_LOCAL_FILEPATH}/${O_METRICS_1}
hdfs dfs -cat hdfs://master-hadoop:9000/${I_METRICS_FILE_2}/* > ${O_DATA_LOCAL_FILEPATH}/${O_METRICS_2}

# -----------------------------------------------------------------------------
# 2. 最佳k聚类结果的中心点数据
# 如: hdfs dfs -cat hdfs://master-hadoop:9000/user/spark/clustercenters/k-means-2014-07-23-21-14-41_bestK43_clusterCenters/* > clustersCenter.csv
export O_CLUSTERCENTERS=${I_TASK_NAME}_clustercenters.csv

hdfs dfs -cat hdfs://master-hadoop:9000/${I_CLUSTERCENTERS_FILE}/* > ${O_DATA_LOCAL_FILEPATH}/${O_CLUSTERCENTERS}

# -----------------------------------------------------------------------------
# 3. 最佳k聚类结果的中心点数据及其分类统计
export I_CLUSTERINFO_FILE=/user/spark/clustering/2014-07-31/S01_M2_kmeans_19_cluster
export I_TASK_NAME=S01_M2_kmeans_20

export O_CLUSTERCENTERS=${I_TASK_NAME}_clusterinfo.csv
hdfs dfs -cat hdfs://master-hadoop:9000/${I_CLUSTERINFO_FILE}/* > ${O_DATA_LOCAL_FILEPATH}/${O_CLUSTERINFO}
# -----------------------------------------------------------------------------
# 重置变量
unset O_METRICS_1
unset O_METRICS_2
unset O_CLUSTERCENTERS
unset O_CLUSTERINFO


