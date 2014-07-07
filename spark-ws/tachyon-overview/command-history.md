 2094  pwd
 2095  ls
 2096  echo "使用的是conf/tachyon-env.sh-hdfs-all配置文件"
 2097  pwd
 2098  env |grep HADOOP_VERSION
 2099  cd bin
 2100  tachyon format
 2101  hdfs dfs -ls /
 2102  hdfs dfs -ls -R /tachyon
 2103  hdfs dfs -ls /tmp
 2104  hdfs dfs -ls -R /tmp/tachyon
 2105  ls ..
 2106  tachyon-start.sh master
 2107  tachyon-start.sh worker SudoMount
 2108  jps
 2109  echo "上面停止了zookeeper: zkServer.sh stop"
 2110  tachyon dfs ls /
 2111  tachyon tfs ls /
 2112  tachyon tfs mkdir /wuhz
 2113  tachyon tfs copyFromLocal ../README.md /wuhz/tackyon-README.md
 2114  tachyon tfs ls -R
 2115  tachyon tfs ls 
 2116* 
 2117  tachyon tfs cat /wuhz/tachyon-README.md | less
 2118  tachyon tfs mv /wuhz/tackyon-README.md /wuhz/tachyon-README.md
 2119  tachyon tfs cat /wuhz/tachyon-README.md |less
 2120  echo "------------再次看一下文件系统的内容-------"
 2121  echo "------------再次看一下HDFS文件系统的内容-------"
 2122  hdfs dfs -ls /tachyon
 2123  hdfs dfs -ls -R /tachyon
 2124  hdfs dfs -ls -R /tmp/tachyon
 2125  hdfs dfs -cat /tmp/tachyon/data/3
 2126  history

