Hadoop Common
=============

Typically one machine in the cluster is designated as the NameNode and another machine the as ResourceManager, exclusively. These are the masters.
The rest of the machines in the cluster act as both DataNode and NodeManager. These are the slaves.

The Hadoop daemons are NameNode/DataNode and ResourceManager/NodeManager.

jps: 启动服务后(默认不启动jobhistory, WebAppProxy)
-------------
hadoop@master-hadoop:~$ jps
6722 NodeManager
6193 NameNode
6284 DataNode
6443 SecondaryNameNode
6625 ResourceManager

12845 JobHistoryServer
13463 WebAppProxyServer

jps: 提交任务后, 其中MRAppMaster最后结束运行
---------------------------------------
9684 YarnChild
9685 YarnChild
9686 YarnChild
6193 NameNode
9687 YarnChild
6443 SecondaryNameNode
9688 YarnChild
9689 YarnChild
6625 ResourceManager
6722 NodeManager
6284 DataNode
8651 RunJar
8827 MRAppMaster

jobhistory, WebAppProxy
---------------------------------------
启动jobhistory服务:
$HADOOP_PREFIX/sbin/mr-jobhistory-daemon.sh start historyserver --config $HADOOP_CONF_DIR

启动WebAppProxy服务:
$HADOOP_YARN_HOME/sbin/yarn-daemon.sh start proxyserver --config $HADOOP_CONF_DIR

web UIs
------
请参考<<Hadoop.The.Definitive.Guide.3rd.Edition.May.2012.RETAIL.eBook-ELOHiM>>中的"Hadoop Daemon Addresses and Ports"和"YARN Daemon Addresses and Ports"

NameNode        http://localhost:50070/dfshealth.html#tab-overview

                http://master-hadoop:50070/dfshealth.jsp
                http://master-hadoop:50070/logs/
                http://master-hadoop:50070/explorer.html#/
                
SecondaryNameNode   http://master-hadoop:50090/status.html http://master-hadoop:50090/status.jsp
DataNode        http://master-hadoop:50075/ http://slave01-hadoop:50075/ ...

                http://localhost:50075/blockScannerReport
                http://localhost:50075/blockScannerReport?listblocks
                
JournalNode     ?

ResourceManager http://master-hadoop:8088/cluster
NodeManager     http://master-hadoop:8042/node http://slave01-hadoop:8042/node ...
JobHistory      http://master-hadoop:19888/jobhistory
WebAppProxy     ?

Read-only default configuration
------------------------------
/opt/hadoop/hadoop-2.5.1/share/doc/hadoop/hadoop-project-dist/hadoop-common/core-default.xml
/opt/hadoop/hadoop-2.5.1/share/doc/hadoop/hadoop-project-dist/hadoop-hdfs/hdfs-default.xml
/opt/hadoop/hadoop-2.5.1/share/doc/hadoop/hadoop-mapreduce-client/hadoop-mapreduce-client-core/mapred-default.xml
/opt/hadoop/hadoop-2.5.1/share/doc/hadoop/hadoop-yarn/hadoop-yarn-common/yarn-default.xml

Site-specific configuration: conf/etc/hadoop
------------------------------
core-site.xml, hdfs-site.xml, yarn-site.xml, mapred-site.xml
hadoop-env.sh, yarn-env.sh

WebHDFS
--------

HDFS
========

File system list
----------------

YARN
========

YARN's REST APIs

Hadoop Administor
=================

Monitoring Health of NodeManagers
---------------------------------
Hadoop provides a mechanism by which administrators can configure the NodeManager to run an administrator supplied script periodically to determine if a node is healthy or not.
Administrators can determine if the node is in a healthy state by performing any checks of their choice in the script. If the script detects the node to be in an unhealthy state, it must print a line to standard output beginning with the string ERROR. The NodeManager spawns the script periodically and checks its output. If the script's output contains the string ERROR, as described above, the node's status is reported as unhealthy and the node is black-listed by the ResourceManager. No further tasks will be assigned to this node. However, the NodeManager continues to run the script, so that if the node becomes healthy again, it will be removed from the blacklisted nodes on the ResourceManager automatically. 
