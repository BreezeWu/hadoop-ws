# -----------------------------------------------------------------------------
# 从HDFS将结果数据导出到本地文件系统
# -----------------------------------------------------------------------------

# 参数设置
# 下面三个每次都要调整
export METRICS_FILE_NAME=/user/spark/metric/k-means-2014-07-28-15-57-39_c15_try15
export CLUSTERCENTERS_FILE_NAME=/user/spark/clustercenters/k-means-2014-07-28-15-57-39_bestK992_clusterCenters
# 抽样/全量_单月/双月_用电量/购售电信息
export RESULT_DESC=S01_M1_VPM_AMT
# 下面这个保持不变
export RESULT_DATA_LOCAL_FILEPATH=/home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/result-data/

#1. 寻找最佳k值的metric数据
#比如: hdfs dfs -cat hdfs://master-hadoop:9000/user/spark/metric/k-means-2014-07-23-21-14-41_c10_try10/* > metrics.csv
cd ~/workspace_github/hadoop-ws/r-ws/data
export RESULT_FILE_PATH=${METRICS_FILE_NAME}

export RESULT_FILE_PATH_0=${RESULT_FILE_PATH}_0
export RESULT_FILE_PATH_1=${RESULT_FILE_PATH}_1
export RESULT_DESC_0=${RESULT_DESC}_0
export RESULT_DESC_1=${RESULT_DESC}_1

hdfs dfs -cat hdfs://master-hadoop:9000/${RESULT_FILE_PATH_0}/* > ${RESULT_DATA_LOCAL_FILEPATH}/metrics_${RESULT_DESC_0}.csv
hdfs dfs -cat hdfs://master-hadoop:9000/${RESULT_FILE_PATH_1}/* > ${RESULT_DATA_LOCAL_FILEPATH}/metrics_${RESULT_DESC_1}.csv

# -----------------------------------------------------------------------------
# 2. 最佳k聚类结果的中心点数据
# 如: hdfs dfs -cat hdfs://master-hadoop:9000/user/spark/clustercenters/k-means-2014-07-23-21-14-41_bestK43_clusterCenters/* > clustersCenter.csv
export RESULT_FILE_PATH=${CLUSTERCENTERS_FILE_NAME}
hdfs dfs -cat hdfs://master-hadoop:9000/${RESULT_FILE_PATH}/* > ${RESULT_DATA_LOCAL_FILEPATH}/clustersCenter_${RESULT_DESC}.csv

# -----------------------------------------------------------------------------
# 重置变量
unset METRICS_FILE_NAME
unset CLUSTERCENTERS_FILE_NAME
unset RESULT_DESC

unset RESULT_FILE_PATH
unset RESULT_FILE_PATH_0
unset RESULT_FILE_PATH_1

unset RESULT_DATA_LOCAL_FILEPATH


