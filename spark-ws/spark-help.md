# ------------------------------------------------------------------------------------------
$SPARK_HOME/bin/spark-shell --help

# -----------------
hadoop@master-hadoop:~/workspace_hadoop/spark-ws$ $SPARK_HOME/bin/spark-shell --help
Usage: ./bin/spark-shell [options]
Spark assembly has been built with Hive, including Datanucleus jars on classpath
Options:
  --master MASTER_URL         spark://host:port, mesos://host:port, yarn, or local.
  --deploy-mode DEPLOY_MODE   Where to run the driver program: either "client" to run
                              on the local machine, or "cluster" to run inside cluster.
  --class CLASS_NAME          Your application's main class (for Java / Scala apps).
  --name NAME                 A name of your application.
  --jars JARS                 Comma-separated list of local jars to include on the driver
                              and executor classpaths.
  --py-files PY_FILES         Comma-separated list of .zip, .egg, or .py files to place
                              on the PYTHONPATH for Python apps.
  --files FILES               Comma-separated list of files to be placed in the working
                              directory of each executor.
  --properties-file FILE      Path to a file from which to load extra properties. If not
                              specified, this will look for conf/spark-defaults.conf.

  --driver-memory MEM         Memory for driver (e.g. 1000M, 2G) (Default: 512M).
  --driver-java-options       Extra Java options to pass to the driver.
  --driver-library-path       Extra library path entries to pass to the driver.
  --driver-class-path         Extra class path entries to pass to the driver. Note that
                              jars added with --jars are automatically included in the
                              classpath.

  --executor-memory MEM       Memory per executor (e.g. 1000M, 2G) (Default: 1G).

 Spark standalone with cluster deploy mode only:
  --driver-cores NUM          Cores for driver (Default: 1).
  --supervise                 If given, restarts the driver on failure.

 Spark standalone and Mesos only:
  --total-executor-cores NUM  Total cores for all executors.

 YARN-only:
  --executor-cores NUM        Number of cores per executor (Default: 1).
  --queue QUEUE_NAME          The YARN queue to submit to (Default: "default").
  --num-executors NUM         Number of executors to launch (Default: 2).
  --archives ARCHIVES         Comma separated list of archives to be extracted into the
                              working directory of each executor.
# ------------------------------------------------------------------------------------------
# ------------------------------------------------------------------------------------------
# ------------------------------------------------------------------------------------------
# ------------------------------------------------------------------------------------------

