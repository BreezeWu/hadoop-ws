# 目前仅有 MASTER=local 模式是成功的.
# 问题:
#   (1) 不能启动 standalone 和 yarn-client/yarn-master 模式
#   (2) 不能访问 hdfs的文件

# ========================
# 运行方式一: 在 R 中
# library(SparkR)

# ========================
# 运行方式二: 在 sparkR 中
# 不需要再加载 SparkR; 正常来说,也不需要创建 sc
# sparkR
# export MASTER=local; sparkR
# export MASTER=spark://master-hadoop:7077; sparkR  # 需要事先启动spark standalone

# ------------------------------------------------
# 创建 sc
# sc <- sparkR.init(master="local")	# sparkR 只支持local模式
#sc <- sparkR.init(master="yarn-client")	# sparkR 不支持yarn-client模式?
  # java.util.NoSuchElementException: None.get
#sc <- sparkR.init(master="spark://master-hadoop:7077")
  # 14/10/29 15:38:27 WARN TaskSchedulerImpl: Initial job has not accepted any resources; check your cluster UI to ensure that workers are registered and have sufficient memory
  # 14/10/29 15:38:59 ERROR AppClient$ClientActor: All masters are unresponsive! Giving up.
  # 14/10/29 15:38:59 ERROR SparkDeploySchedulerBackend: Spark cluster looks dead, giving up.
#sc <- sparkR.init(master="spark://master-hadoop:7077",
    sparkEnvir=list(spark.executor.memory="50M"))
  # 问题同上

# ------------------------------------------------
# 一个 wordcount 样例
file <- "file:///home/hadoop/workspace_github/SparkR-pkg/README.md" # ok!
file <- "/home/hadoop/workspace_github/SparkR-pkg/README.md"    # ok!
file <- "hdfs://master-hadoop:9000/user/hadoop/input/README.md"   # failed!
    # org.apache.hadoop.ipc.RemoteException: Server IPC version 9 cannot communicate with client version 4
lines <- textFile(sc,file) # textFile(sc,args[[2]])
words <- flatMap(lines,
    function(line) {
        strsplit(line," ")[[1]]
     })
wordCount <- lapply(words,
    function(word) {
        list(word, 1L)
    })
counts <- reduceByKey(wordCount, "+",2L)
output <- collect(counts)
output
