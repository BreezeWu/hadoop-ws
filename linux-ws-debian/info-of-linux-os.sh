# -----------------------------------------------------------------------------
# /proc/cpuinfo中的字段及其含义

1.拥有相同 physical id 的所有逻辑处理器共享同一个物理插座。每个 physical id 代表一个唯一的物理封装。
2.Siblings 表示位于这一物理封装上的逻辑处理器的数量。它们可能支持也可能不支持超线程（HT）技术。

3.每个 core id 均代表一个唯一的处理器内核。所有带有相同 core id 的逻辑处理器均位于同一个处理器内核上。

4.如果有一个以上逻辑处理器拥有相同的 core id 和 physical id，则说明系统支持超线程（HT）技术。     ????????
5.如果有两个或两个以上的逻辑处理器拥有相同的 physical id，但是 core id 不同，则说明这是一个多内核处理器。cpu cores 条目也可以表示是否支持多内核。 ~即,一个物理封装中有多个处理器内核.
# -----------------------------------------------
[一个物理封装, 多个逻辑处理器]
[多个逻辑处理器,构成一个处理器内核]
# -----------------------------------------------
物理封装		'physical id'
逻辑处理器 	'processor'

处理器内核	'core id'
('cpu cores'表示的是某个物理封装/物理CPU中Core的个数,即有多少个处理器内核,也即 核数;)


若是非多内核处理器,则每个逻辑处理器的'physical id'和'core id'是一致的.
若是多内核处理器,则某一个'physical id'下有不同的'core id'.
# -----------------------------------------------
举个例子: 我的lenovo V480c	

# -----------------------------------------------------------------------------
# 从/proc/cpuinfo中获得CPU信息

# -----------------------------------------------
echo "physical CPU number:"
#物理CPU个数：
cat /proc/cpuinfo | grep "physical id" | sort | uniq | wc -l

echo "logical CPU number:"
#逻辑CPU个数
cat /proc/cpuinfo | grep "processor" | wc -l
# -----------------------------------------------

echo "core number in a physical CPU:"
# 每个物理CPU中Core的个数： 
cat /proc/cpuinfo | grep "cpu cores" | uniq | awk -F: '{print $2}'
# 查看每个physical cpu上core id的数量,即为每个物理CPU上的core的个数
cat /proc/cpuinfo | grep "core id"
# -----------------------------------------------

# 每个物理cpu中逻辑cpu(可能是core、threads或both)的个数 (超线程是打开的)
cat /proc/cpuinfo | grep "siblings" | uniq 
#或者
cat /proc/cpuinfo | grep "processor" | wc -l

# -----------------------------------------------------------------------------
# hadoop中对应的配置
#	http://heipark.iteye.com/blog/1146838

# mapred.tasktracker.map.tasks.maximum ：一个tasktracker最多可以同时运行的map任务数量
#	默认值：2
#	优化值：mapred.tasktracker.map.tasks.maximum = 逻辑处理器数量 或 threads数量

# 逻辑cpu数量是 4


# mapred.tasktracker.reduce.tasks.maximum
# 官方解释：The maximum number of reduce tasks that will be run  simultaneously by a task tracker.
# 我的理解：一个task tracker最多可以同时运行的reduce任务数量
# 默认值：2
# 优化值： (CPU数量 > 2) ? (CPU数量 * 0.50): 1 （mapr的官方建议）

# 设置为2


# -----------------------------------------------------------------------------
# 

# -----------------------------------------------------------------------------
# 
