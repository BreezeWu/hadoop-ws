spark quick start - Standalone Applications
	http://spark.apache.org/docs/latest/quick-start.html

# Your directory layout should look like this
$ find .
.
./simple.sbt
./src
./src/main
./src/main/scala
./src/main/scala/SimpleApp.scala

# Package a jar containing your application
$ sbt package
...
[info] Packaging {..}/{..}/target/scala-2.10/simple-project_2.10-1.0.jar

# Use spark-submit to run your application
先把文件上传到hdfs

hdfs dfs -mkdir -p input/spark-data/
hdfs dfs -put README.md input/spark-data/

# ------------------------------------
${SPARK_HOME}/bin/spark-submit \
  --class "SimpleApp" \
  --master local[4] \
  target/scala-2.10/simple-project_2.10-1.0.jar
...
Lines with a: 46, Lines with b: 23

# ------------------------------------
${SPARK_HOME}/bin/spark-submit \
  --class "SimpleApp" \
  target/scala-2.10/simple-project_2.10-1.0.jar
