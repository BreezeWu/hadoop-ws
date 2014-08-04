# --------------------------------------------------------------------------
# 使用方法
#	在spark-shell中执行
#		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/execute-s98.sh

# linux下查看执行结果 注意替换"S01_M2"
cd /home/hadoop/workspace_github/hadoop-ws/r-ws/result-data
hdfs dfs -cat /user/spark/clustering/2014-07-31/S98_M1_kmeans_19_cluster\/* > S98_M1_kmeans_19_cluster.csv

