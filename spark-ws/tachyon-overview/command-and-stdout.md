hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1$ pwd
/home/hadoop/dev-src-opensource/tachyon-0.4.1
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1$ ls
bin  conf  docs  libexec  LICENSE  logs  pom.xml  README.md  src  target
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1$ echo "使用的是conf/tachyon-env.sh-hdfs-all配置文件"
使用的是conf/tachyon-env.sh-hdfs-all配置文件
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1$ pwd
/home/hadoop/dev-src-opensource/tachyon-0.4.1
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1$ env |grep HADOOP_VERSION
HADOOP_VERSION=2.2.0
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1$ cd bin
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon format
master-hadoop: Formatting Tachyon Worker @ master-hadoop
master-hadoop: Removing local data under folder: /mnt/ramdisk/tachyonworker/
Formatting Tachyon Master @ master-hadoop
Formatting JOURNAL_FOLDER: hdfs://master-hadoop:9000/tachyon/journal/
Formatting UNDERFS_DATA_FOLDER: hdfs://master-hadoop:9000/tmp/tachyon/data
Formatting UNDERFS_WORKERS_FOLDER: hdfs://master-hadoop:9000/tmp/tachyon/workers
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ hdfs dfs -ls /
Found 6 items
drwxr-xr-x   - hadoop supergroup          0 2014-06-16 08:23 /hbase_root
drwxr-xr-x   - hadoop supergroup          0 2014-06-16 06:48 /home
drwxr-xr-x   - hadoop supergroup          0 2014-06-16 07:20 /opt
drwxr-xr-x   - hadoop supergroup          0 2014-07-07 15:18 /tachyon
drwx------   - hadoop supergroup          0 2014-07-07 14:25 /tmp
drwxr-xr-x   - hadoop supergroup          0 2014-06-15 18:59 /user
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ hdfs dfs -ls -R /tachyon
drwxr-xr-x   - hadoop supergroup          0 2014-07-07 15:18 /tachyon/journal
-rwxrwxrwx   3 hadoop supergroup          0 2014-07-07 15:18 /tachyon/journal/_format_1404717510149
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ hdfs dfs -ls /tmp
Found 3 items
drwx------   - hadoop supergroup          0 2014-05-25 15:56 /tmp/hadoop-yarn
drwxr-xr-x   - hadoop supergroup          0 2014-06-16 07:15 /tmp/hive-hadoop
drwxr-xr-x   - hadoop supergroup          0 2014-07-07 15:18 /tmp/tachyon
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ hdfs dfs -ls -R /tmp/tachyon
drwxr-xr-x   - hadoop supergroup          0 2014-07-07 15:18 /tmp/tachyon/data
drwxr-xr-x   - hadoop supergroup          0 2014-07-07 15:18 /tmp/tachyon/workers
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ ls ..
bin  conf  docs  libexec  LICENSE  logs  pom.xml  README.md  src  target
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon-start.sh master
Starting master @ master-hadoop
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon-start.sh worker SudoMount
Formatting RamFS: /mnt/ramdisk (1gb)
Starting worker @ master-hadoop
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ jps
30524 SecondaryNameNode
30294 NameNode
4638 Worker
30379 DataNode
4533 Master
3013 QuorumPeerMain
4665 Jps
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ jps
30524 SecondaryNameNode
30294 NameNode
4638 Worker
30379 DataNode
4753 Jps
4533 Master
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ echo "上面停止了zookeeper: zkServer.sh stop"
上面停止了zookeeper: zkServer.sh stop
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon dfs ls /
Usage: tachyon COMMAND 
where COMMAND is one of:
  format [-s]   	 Format Tachyon (if -s specified, only format if underfs doesn't exist)
  bootstrap-conf	 Generate a config file if one doesn't exist
  tfs           	 Command line input for generic filesystem user client.
  loadufs       	 Load existing files in underlayer filesystem into Tachyon.
  runTest       	 Run a end-to-end test on a Tachyon cluster.
  runTests      	 Run all end-to-end tests on a Tachyon cluster.
  killAll <WORD>	 Kill processes containing the WORD.
  copyDir <PATH>	 Copy the PATH to all worker nodes.
  clearCache    	 Clear OS buffer cache of the machine.
  thriftGen     	 Generate all thrift code.
  version       	 Print Tachyon version and exit.
Commands print help when invoked without parameters.
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon tfs ls /
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon tfs mkdir /wuhz
Successfully created directory /wuhz
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon tfs copyFromLocal ../README.md /wuhz/tackyon-README.md
Copied ../README.md to /wuhz/tackyon-README.md
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon tfs ls -R
Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: -1
	at java.lang.String.substring(String.java:1871)
	at tachyon.command.Utils.getFilePath(Utils.java:67)
	at tachyon.command.TFsShell.ls(TFsShell.java:135)
	at tachyon.command.TFsShell.run(TFsShell.java:535)
	at tachyon.command.TFsShell.main(TFsShell.java:511)
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon tfs ls 
Usage: tfs ls <path>
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon tfs ls /wuhz
791.00 B  07-07-2014 15:22:10:819  In Memory      /wuhz/tackyon-README.md
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon tfs cat /wuhz/tachyon-README.md | less
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon tfs mv /wuhz/tackyon-README.md /wuhz/tachyon-README.md
Renamed /wuhz/tackyon-README.md to /wuhz/tachyon-README.md
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ tachyon tfs cat /wuhz/tachyon-README.md |less
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ echo "------------再次看一下文件系统的内容-------"
------------再次看一下文件系统的内容-------
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ echo "------------再次看一下HDFS文件系统的内容-------"
------------再次看一下HDFS文件系统的内容-------
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ hdfs dfs -ls /tachyon
Found 1 items
drwxr-xr-x   - hadoop supergroup          0 2014-07-07 15:19 /tachyon/journal
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ hdfs dfs -ls -R /tachyon
drwxr-xr-x   - hadoop supergroup          0 2014-07-07 15:19 /tachyon/journal
-rwxrwxrwx   3 hadoop supergroup          0 2014-07-07 15:18 /tachyon/journal/_format_1404717510149
-rwxrwxrwx   3 hadoop supergroup         46 2014-07-07 15:19 /tachyon/journal/image.data
-rwxrwxrwx   3 hadoop supergroup          0 2014-07-07 15:19 /tachyon/journal/log.data
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ hdfs dfs -ls -R /tmp/tachyon
drwxr-xr-x   - hadoop supergroup          0 2014-07-07 15:22 /tmp/tachyon/data
-rwxrwxrwx   3 hadoop supergroup        791 2014-07-07 15:22 /tmp/tachyon/data/3
drwxr-xr-x   - hadoop supergroup          0 2014-07-07 15:22 /tmp/tachyon/workers
drwxr-xr-x   - hadoop supergroup          0 2014-07-07 15:22 /tmp/tachyon/workers/1404717000001
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ hdfs dfs -cat /tmp/tachyon/data/3
Tachyon
=======

The master branch is in version 0.4.2-SNAPSHOT:

Project Homepage: http://www.tachyonproject.org

Previous Releases: https://github.com/amplab/tachyon/tags

Master Branch Document: http://tachyon-project.org/master/

JIRA: https://spark-project.atlassian.net/browse/TACHYON

User Mailing List: https://groups.google.com/forum/?fromgroups#!forum/tachyon-users

## Dependency Information

### Apache Maven
```xml
<dependency>
  <groupId>org.tachyonproject</groupId>
  <artifactId>tachyon</artifactId>
  <version>0.4.1</version>
</dependency>
```

### Apache Ant
```xml
<dependency org="org.tachyonproject" name="tachyon" rev="0.4.1">
  <artifact name="tachyon" type="jar" />
</dependency>
```

### SBT
```
libraryDependencies += "org.tachyonproject" % "tachyon" % "0.4.1"
```
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ 
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ echo "---若速度够快的话,可能在HDFS还看不到文件,因为chache!"
bash: !": event not found
hadoop@master-hadoop:~/dev-src-opensource/tachyon-0.4.1/bin$ 

