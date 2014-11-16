===================
cpu与内存

# -----------------------------------------------------------------------------
1. cpu
#逻辑CPU个数
cat /proc/cpuinfo | grep "processor" | wc -l

优化值：mapred.tasktracker.map.tasks.maximum = 逻辑处理器数量 或 threads数量
优化值：mapred.tasktracker.reduce.tasks.maximum = (CPU数量 > 2) ? (CPU数量 * 0.50): 1 （mapr的官方建议）

# -----------------------------------------------------------------------------
2. memory
(1) Datanode, Nodemanager所占用的内存
配置: hadoop-env.sh 文件中 HADOOP_HEAPSIZE, 默认值是1000,即1G

(2) 每个yarn.nodemanager可分配的最大内存
配置: yarn-site.xml 文件中 yarn.nodemanager.resource.memory-mb,默认值是8192,即8G

整个YARN集群中的总内存 = yarn.nodemanager.resource.memory-mb * 节点数

# -------------------------------------
(2.1) 每一个map或reduce task的最大内存
配置: mapred-site.xml 文件中 mapred.child.java.opts
注意: 当然,还可以将mapred.child.java.opts更进一步区分为mapreduce.map.memory.mb, mapreduce.reduce.memory.mb

(2.2) 每个yarn.nodemanager中的mapper和reducer个数
配置: mapred-site.xml 文件中 mapreduce.tasktracker.map.tasks.maximum 和 mapreduce.tasktracker.reduce.tasks.maximum,默认值是

在内存上,所使用总内存 = (mapreduce.tasktracker.map.tasks.maximum + mapreduce.tasktracker.reduce.tasks.maximum) * mapred.child.java.opts
# -----------------------------------------------------------------------------

	
