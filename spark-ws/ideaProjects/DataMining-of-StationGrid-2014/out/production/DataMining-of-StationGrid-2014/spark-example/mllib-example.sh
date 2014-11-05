# 启动dfs
start-dfs.sh

# 将数据上传到hdfs
cd ~/workspace_github/spark-master-sbt
hdfs dfs -copyFromLocal -f -p data

# 设置spark环境变量
export MASTER=local[*]
# 运行实例
run-example org.apache.spark.examples.mllib.LinearRegression data/mllib/sample_linear_regression_data.txt
# 或者
spark-submit --class org.apache.spark.examples.mllib.LinearRegression \
               examples/target/scala-*/spark-examples-*.jar \
               data/mllib/sample_linear_regression_data.txt

