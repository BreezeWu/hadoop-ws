# --------------------------------------------------------------------------
# 该脚本被 printlnHdfsExportCmd.scala 替代
# 使用方法
#	在spark-shell中执行
#		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/execute-s98.sh

# linux下查看执行结果 注意替换"hdfsfile_*"
cd /home/hadoop/workspace_github/hadoop-ws/r-ws/result-data

#hdfs dfs -cat /user/spark/clustering/2014-07-31/S98_M1_kmeans_19_cluster\/* > S98_M1_kmeans_19_cluster.csv

# 寻求最佳K
export hdfsfile_GoodM1=/user/spark/clustering/2014-08-04/S01_GoodM1_kmeans_200_cluster
export hdfsfile_GoodM2=/user/spark/clustering/2014-08-04/S01_GoodM2_kmeans_200_cluster
export hdfsfile_BadF3=/user/spark/clustering/2014-08-04/S01_BadF3_kmeans_196_cluster
export hdfsfile_BadF2ExcludeF3=/user/spark/clustering/2014-08-04/S01_BadF2ExcludeF3_kmeans_198_cluster

# 人工指定最佳K
export hdfsfile_GoodM1=/user/spark/clustering/2014-08-05/S01_perfectK20_GoodM1_kmeans_20_cluster
export hdfsfile_GoodM2=/user/spark/clustering/2014-08-05/S01_perfectK20_GoodM2_kmeans_20_cluster
export hdfsfile_BadF3=/user/spark/clustering/2014-08-05/S01_perfectK20_BadF3_kmeans_20_cluster
export hdfsfile_BadF2ExcludeF3=/user/spark/clustering/2014-08-05/S01_perfectK20_BadF2ExcludeF3_kmeans_20_cluster

export filedirname=`dirname $fullfilename`
# ---------------------------------------------
# 下面语句并不是每次都重新执行,所以要改为function
#export filebasename=`basename ${fullfilename}`.csv
function getFileBaseName() {
#    local result="`basename ${fullfilename}`.csv"
#    echo "$result"
    local  __resultvar=$1
    local  myresult="`basename ${fullfilename}`.csv"
    eval $__resultvar="'$myresult'"
}

getFileBaseName filebasename
echo ${filebasename}
# ---------------------------------------------

export fullfilename=${hdfsfile_GoodM1};getFileBaseName filebasename;
hdfs dfs -cat ${fullfilename}\/* > ${filebasename}
export fullfilename=${hdfsfile_GoodM2};getFileBaseName filebasename;
hdfs dfs -cat ${fullfilename}\/* > ${filebasename}
export fullfilename=${hdfsfile_BadF3};getFileBaseName filebasename;
hdfs dfs -cat ${fullfilename}\/* > ${filebasename}
export fullfilename=${hdfsfile_BadF2ExcludeF3};getFileBaseName filebasename;
hdfs dfs -cat ${fullfilename}\/* > ${filebasename}

