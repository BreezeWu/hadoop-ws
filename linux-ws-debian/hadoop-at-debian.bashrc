# ~/.bashrc: executed by bash(1) for non-login shells.
# see /usr/share/doc/bash/examples/startup-files (in the package bash-doc)
# for examples

# If not running interactively, don't do anything
case $- in
    *i*) ;;
      *) return;;
esac

# don't put duplicate lines or lines starting with space in the history.
# See bash(1) for more options
HISTCONTROL=ignoreboth

# append to the history file, don't overwrite it
shopt -s histappend

# for setting history length see HISTSIZE and HISTFILESIZE in bash(1)
HISTSIZE=1000
HISTFILESIZE=2000

# check the window size after each command and, if necessary,
# update the values of LINES and COLUMNS.
shopt -s checkwinsize

# If set, the pattern "**" used in a pathname expansion context will
# match all files and zero or more directories and subdirectories.
#shopt -s globstar

# make less more friendly for non-text input files, see lesspipe(1)
#[ -x /usr/bin/lesspipe ] && eval "$(SHELL=/bin/sh lesspipe)"

# set variable identifying the chroot you work in (used in the prompt below)
if [ -z "${debian_chroot:-}" ] && [ -r /etc/debian_chroot ]; then
    debian_chroot=$(cat /etc/debian_chroot)
fi

# set a fancy prompt (non-color, unless we know we "want" color)
case "$TERM" in
    xterm-color) color_prompt=yes;;
esac

# uncomment for a colored prompt, if the terminal has the capability; turned
# off by default to not distract the user: the focus in a terminal window
# should be on the output of commands, not on the prompt
#force_color_prompt=yes

if [ -n "$force_color_prompt" ]; then
    if [ -x /usr/bin/tput ] && tput setaf 1 >&/dev/null; then
	# We have color support; assume it's compliant with Ecma-48
	# (ISO/IEC-6429). (Lack of such support is extremely rare, and such
	# a case would tend to support setf rather than setaf.)
	color_prompt=yes
    else
	color_prompt=
    fi
fi

if [ "$color_prompt" = yes ]; then
    PS1='${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\w\[\033[00m\]\$ '
else
    PS1='${debian_chroot:+($debian_chroot)}\u@\h:\w\$ '
fi
unset color_prompt force_color_prompt

# If this is an xterm set the title to user@host:dir
case "$TERM" in
xterm*|rxvt*)
    PS1="\[\e]0;${debian_chroot:+($debian_chroot)}\u@\h: \w\a\]$PS1"
    ;;
*)
    ;;
esac

# enable color support of ls and also add handy aliases
if [ -x /usr/bin/dircolors ]; then
    test -r ~/.dircolors && eval "$(dircolors -b ~/.dircolors)" || eval "$(dircolors -b)"
    alias ls='ls --color=auto'
    #alias dir='dir --color=auto'
    #alias vdir='vdir --color=auto'

    #alias grep='grep --color=auto'
    #alias fgrep='fgrep --color=auto'
    #alias egrep='egrep --color=auto'
fi

# some more ls aliases
#alias ll='ls -l'
#alias la='ls -A'
#alias l='ls -CF'

# Alias definitions.
# You may want to put all your additions into a separate file like
# ~/.bash_aliases, instead of adding them here directly.
# See /usr/share/doc/bash-doc/examples in the bash-doc package.

if [ -f ~/.bash_aliases ]; then
    . ~/.bash_aliases
fi

# enable programmable completion features (you don't need to enable
# this, if it's already enabled in /etc/bash.bashrc and /etc/profile
# sources /etc/bash.bashrc).
if ! shopt -oq posix; then
  if [ -f /usr/share/bash-completion/bash_completion ]; then
    . /usr/share/bash-completion/bash_completion
  elif [ -f /etc/bash_completion ]; then
    . /etc/bash_completion
  fi
fi

# -----------------------------------------------------------------------------
# ant
export ANT_HOME=/opt/ant/apache-ant-1.9.4/
export PATH=${ANT_HOME}/bin:${PATH}

# -----------------------------------------------------------------------------
# User specific aliases and functions
export JAVA_HOME=/opt/java/jdk1.8.0_45
export JRE_HOME=/opt/java/jdk1.8.0_45/jre
export PATH=$JAVA_HOME/bin:$JRE_HOME/jre/bin:${PATH}
#export CLASSPATH=.:$CLASSPATH:$JAVA_HOME/lib:$JRE_HOME/lib
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib

# /opt/JAVACP
export JAVACP_HOME=/opt/java_cp
export CLASSPATH=/opt/java_cp/mongo-java-driver-2.6.3.jar:/opt/java_cp/mysql-connector-java-5.1.27.jar:/opt/java_cp/ojdbc6_g.jar:/opt/java_cp/ojdbc6.jar:${CLASSPATH}

# -----------------------------------------------------------------------------
# 不同的hadoop版本定义
#export HADOOP_VERSION=0.20.2
#export HADOOP_VERSION=1.2.1
#export HADOOP_VERSION=2.2.0
export HADOOP_VERSION=2.6.0
export HADOOP_HOME=/opt/hadoop/hadoop-${HADOOP_VERSION}
export PATH=${HADOOP_HOME}/bin:${HADOOP_HOME}/sbin:${PATH}
export CLASSPATH=${HADOOP_HOME}:${HADOOP_HOME}/lib:${CLASSPATH}

# hadoop 0.x, 1.x
#export HADOOP_CONF_DIR=${HADOOP_HOME}/conf
# hadoop 2.x
export HADOOP_CONF_DIR=${HADOOP_HOME}/etc/hadoop

### only for hadoop 2.x
### BEGIN>>>
export HADOOP_INSTALL=${HADOOP_HOME}  
export HADOOP_PREFIX=${HADOOP_INSTALL}
#export PATH=$PATH:$HADOOP_INSTALL/bin:$HADOOP_INSTALL/sbin 
export HADOOP_MAPRED_HOME=$HADOOP_INSTALL  
export HADOOP_COMMON_HOME=$HADOOP_INSTALL  
export HADOOP_HDFS_HOME=$HADOOP_INSTALL  
export HADOOP_YARN_HOME=$HADOOP_INSTALL
export HADOOP_COMMON_LIB_NATIVE_DIR=${HADOOP_INSTALL}/lib/native
export HADOOP_OPTS="-Djava.library.path=${HADOOP_INSTALL}/lib"
### <<<END

# -----------------------------------------------------------------------------
# zookeeper
export ZOOKEEPER_HOME=/opt/zookeeper/zookeeper-3.4.6
export PATH=${ZOOKEEPER_HOME}/bin:${PATH}

# -----------------------------------------------------------------------------
# hbase
#export HBASE_HOME=/opt/hbase/hbase-0.98.3-hadoop1
#export HBASE_HOME=/opt/hbase/hbase-0.98.3-hadoop2
export HBASE_HOME=/opt/hbase/hbase-1.0.0
export PATH=${HBASE_HOME}/bin:${PATH}

#export HBASE_MANAGES_ZK=false  #不使用HBase自带的Zookeeper,这个必须在hbase-env.sh中配置

# -----------------------------------------------------------------------------
# hive
export HIVE_HOME=/opt/hive/apache-hive-1.1.0-bin
export PATH=${HIVE_HOME}/bin:${HIVE_HOME}/hcatalog/sbin:${PATH}

# hive web interface
export ANT_LIB=/opt/ant/apache-ant-1.9.4/lib
# 启动服务
# hive --service hwi
# In order to access the Hive Web Interface, go to <Hive Server Address>:9999/hwi on your web browser.
# -----------------------------------------------------------------------------
# pig
export PIG_HOME=/opt/pig/pig-0.12.1
export PATH=${PIG_HOME}/bin:${PATH}
export CLASSPATH=$CLASSPATH:$PIG_HOME/lib

# -----------------------------------------------------------------------------
# maven
export M2_HOME=/opt/maven/apache-maven-3.3.1/
export PATH=${M2_HOME}/bin:${PATH}
export MAVEN_OPTS="-Xmx2g -XX:MaxPermSize=512M -XX:ReservedCodeCacheSize=512m"

# -----------------------------------------------------------------------------
# scala
#export SCALA_HOME=/opt/scala/scala-2.10.4
#export SCALA_HOME=/opt/scala/scala-2.11.0
export SCALA_HOME=/opt/scala/scala-2.11.6
export PATH=${SCALA_HOME}/bin:${PATH}

# -----------------------------------------------------------------------------
# spark

# spark-1.3.1 [pre-built]
#export SPARK_HOME=/opt/spark/spark-1.3.1-bin-hadoop2.6
export SPARK_HOME=/home/hduser/workspace_src/spark-1.3.1
export SPARK_EXAMPLES_JAR=${SPARK_HOME}/examples/target/scala-2.10/spark-examples-1.1.0-SNAPSHOT-hadoop2.2.0.jar

# spark-1.3.1 [src]
#export SPARK_HOME=/home/hadoop/workspace_src/spark-1.3.1
#export SPARK_EXAMPLES_JAR=${SPARK_HOME}/examples/target/scala-2.10/spark-examples-1.1.0-SNAPSHOT-hadoop2.2.0.jar

# spark-1.1
# hive yarn hadoop-2.2.0
#export SPARK_HOME=/home/hadoop/workspace_github/spark-master-sbt
#export SPARK_HOME=/home/hadoop/workspace_github/spark-master-maven
#export SPARK_EXAMPLES_JAR=${SPARK_HOME}/examples/target/scala-2.10/spark-examples-1.1.0-SNAPSHOT-hadoop2.2.0.jar

# spark-1.1.0-SNAPSHOT-20140718
#export SPARK_HOME=/home/hadoop/workspace_github/spark-1.0.0-SNAPSHOT-20140718
#export SPARK_EXAMPLES_JAR=${SPARK_HOME}/examples/target/scala-2.10/spark-examples-1.1.0-SNAPSHOT-hadoop2.2.0.jar

# spark-1.0
# hive yarn hadoop-2.2.0
#export SPARK_HOME=/home/hadoop/workspace_github/spark-branch-1.0
#export SPARK_EXAMPLES_JAR=${SPARK_HOME}/examples/target/scala-2.10/spark-examples-1.0.3-SNAPSHOT-hadoop2.2.0.jar

# spark-bin-1.0
#export SPARK_HOME=/opt/spark/spark-1.0.0-bin-hadoop2

# spark-bin-0.9.1
#export SPARK_HOME=/opt/spark/spark-0.9.1-bin-hadoop1
#export SPARK_HOME=/opt/spark/spark-0.9.1-bin-hadoop2
#export SPARK_EXAMPLES_JAR=${SPARK_HOME}/examples/target/spark-examples_2.10-0.9.1.jar

# ADD_JARS
export SPARK_CLASSPATH=/opt/java_cp/mysql-connector-java-5.1.27.jar

# 设置了${SPARK_HOME}/sbin这个后,会利用SPARK的脚本启动HDFS
#export PATH=${SPARK_HOME}/bin:${SPARK_HOME}/sbin:${PATH}
export PATH=${SPARK_HOME}/bin:${PATH}

#export SPARK_MASTER_IP=127.0.0.1

# MASTER 
# This can be a mesos:// or spark:// URL, "yarn-cluster" or "yarn-client" to run on YARN, and "local" to run locally with one thread, or "local[N]" to run locally with N threads.
# 如果设置了master不是local,则需要事先启动对应服务才能够启动${SPARK_HOME}/bin/spark-shell

# 下面设置就不需要执行任何额外的服务
#export MASTER=local[*]
	# 如果不使用MASTER,可以使用类似下面的命令
	# ${SPARK_HOME}/./bin/spark-shell --master local[2]
export MASTER=local

# 下面配置需要先执行 hadoop的 start-yarn.sh
#export MASTER=yarn-client

# 下面配置需要先执行 ${SPARK_HOME}/sbin/start-all.sh
#export MASTER=spark://master-hadoop:7077

export SPARK_SCALA_VERSION=2.11

# -----------------------------------------------------------------------------
# spark jobserver
export SPARKJOBSERVER_HOME=/home/hadoop/workspace_github/spark-jobserver
export PATH=${SPARKJOBSERVER_HOME}/bin:${PATH}

# -----------------------------------------------------------------------------
# sparkR
export SPARKR_HOME=/home/hadoop/workspace_github/SparkR-pkg
export PATH=${SPARKR_HOME}:${PATH}
export YARN_CONF_DIR=${HADOOP_CONF_DIR}

# -----------------------------------------------------------------------------
# cassandra
export CASSANDRA_HOME=/opt/cassandra/apache-cassandra-2.1.2
export PATH=${CASSANDRA_HOME}/bin:${PATH}

export CASSANDRA_VERSION=2.1.2

# -----------------------------------------------------------------------------
# mesos
export MESOS_HOME=/opt/mesos/mesos-0.19.0/build
export PATH=${MESOS_HOME}/bin:${PATH}

# -----------------------------------------------------------------------------
# mahout
#export MAHOUT_HOME=/opt/mahout/mahout-0.5
#export MAHOUT_HOME=/opt/mahout/mahout-distribution-0.8
#export MAHOUT_HOME=/opt/mahout/mahout-svn
#export MAHOUT_HOME=/home/hadoop/dev-src-opensource/svn_mahout/trunk
#export MAHOUT_HOME=/home/hadoop/workspace_mahout1.0/mahout-trunk
export MAHOUT_HOME=/home/hadoop/dev-src-opensource/mahout
export PATH=${MAHOUT_HOME}/bin:${PATH}
# 本地模式还是分布式模式
#export MAHOUT_LOCAL=false
#export MAHOUT_INPUT=/home/hadoop/workspace_dm/input
#export MAHOUT_OUTPUT=/home/hadoop/workspace_dm/output

# -----------------------------------------------------------------------------
# IDE工具

# idea-IC
export IDEA_IC=/opt/idea-IC/idea-IC-135.909
export PATH=${IDEA_IC}/bin:${PATH}

# eclipse
#export ECLIPSE_HOME=/opt/eclipse/eclipse-jee-kepler-SR2
export ECLIPSE_HOME=/opt/eclipse/scala-SDK-3.0.4-2.11-linux
export PATH=$ECLIPSE_HOME:$PATH

# netbeans
export NETBEANS_HOME=/usr/local/netbeans-8.0
export PATH=${NETBEANS_HOME}/bin:$PATH
export WS_NETBEANS=~/NetBeansProjects

# -----------------------------------------------------------------------------
# R语言
# 不要定义R_HOME
#export R_HOME=/home/hadoop/dev-src-opensource/R-latest-20140528
#export PATH=${R_HOME}/bin:${PATH}
# R_WD
export R_WD=/home/hadoop/workspace_github/hadoop-ws/r-ws

# 解决R画图时warning
#> plot(1,1)
#Warning messages:
#1: In (function (display = "", width, height, pointsize, gamma, bg,  :
#  locale not supported by Xlib: some X ops will operate in C locale
#2: In (function (display = "", width, height, pointsize, gamma, bg,  :
#  X cannot set locale modifiers
# 解决办法: 设置linux locale
export LC_CTYPE=en_HK.UTF-8
export LC_ALL=en_HK.UTF-8

# RHadoop
export HADOOP_CMD=${HADOOP_HOME}/bin/hadoop
#export HADOOP_STREAMING=${HADOOP_HOME}/share/hadoop/tools/lib/hadoop-streaming-2.2.0.jar
export HADOOP_STREAMING=${HADOOP_HOME}/share/hadoop/tools/lib/hadoop-streaming-${HADOOP_VERSION}.jar

# RHIPE --begin
#export HADOOP_BIN=${HADOOP_HOME}/bin
#export PKG_CONFIG_PATH=/usr/local/lib
#export LD_LIBRARY_PATH=/usr/local/lib

#export PATH=/opt/protobuf/protobuf-2.4.1/bin:${PATH}
### To get in to the extracted protocol buffer directory
#cd protobuf-2.4.1
## For making install the protocol buffer
#./configure # --prefix=...
#make
#make install
# RHIPE --end
# -----------------------------------------------------------------------------
# sqoop
#export SQOOP_HOME=/opt/sqoop/sqoop-2.0.0-SNAPSHOT-bin-hadoop200
#export SQOOP_HOME=/opt/sqoop/sqoop-1.99.3-bin-hadoop200
export SQOOP_HOME=/opt/sqoop/sqoop-1.4.4.bin__hadoop-2.0.4-alpha
#export SQOOP_HOME=/opt/sqoop/sqoop-1.4.4.bin__hadoop-0.20
export PATH=${SQOOP_HOME}/bin:${PATH}
export CATALINA_BASE=${SQOOP_HOME}/server
export LOGDIR=${SQOOP_HOME}/logs/

# -----------------------------------------------------------------------------
# flume
export FLUME_HOME=/opt/flume/apache-flume-1.5.0-bin
export PATH=${FLUME_HOME}/bin:${PATH}
export FLUME_LOG_DIR=${FLUME_HOME}/logs

# -----------------------------------------------------------------------------
# workspace
export WS_DM=/home/hadoop/workspace_dm
export EBOOK_HOME=/mnt/win-f/百度云盘/EBooks/EBooks.EBooks.大数据与数据挖掘
export DM_HOME=/mnt/win-f/dev/sda7/百度云盘/职场之数据挖掘
export WS_R=~/workspace_R

# -----------------------------------------------------------------------------
# oracle sqldeveloper 
# sqldeveloper.sh
export SQLDEVELOPER_HOME=/opt/sqldeveloper/sqldeveloper-4.0.2.15.21
export PATH=${SQLDEVELOPER_HOME}:${PATH}

# -----------------------------------------------------------------------------
# mongodb
export MONGO_HOME=/opt/mongodb/mongodb-linux-x86_64-2.6.3
export PATH=${MONGO_HOME}/bin:${PATH}

# -----------------------------------------------------------------------------
# h2o
export H2O_HOME=/opt/h2o/h2o-2.4.4.3
export PATH=${H2O_HOME}/bin:${PATH}

# -----------------------------------------------------------------------------
# AWS
#export AWS_ACCESS_KEY_ID=
#export AWS_SECRET_ACCESS_KEY=
#

# -----------------------------------------------------------------------------
# hadoop-ws的配置文件
export CONF_HADOOP_ECOSYSTEM=/home/hadoop/workspace_github/hadoop-ws/conf-hadoop-ecosystem
export CONF_SPARK_ECOSYSTEM=/home/hadoop/workspace_github/hadoop-ws/conf-spark-ecosystem

# -----------------------------------------------------------------------------
# goagent 作为linux代理服务器  
#export http_proxy="http://localhost:8087"
#export https_proxy="http://localhost:8087"

# 参见 http://my.oschina.net/tsl0922/blog/134755
# https需要添加CA.crt到系统
# sudo cp ~/link-goagent/local/CA.crt /usr/share/ca-certificates/goagent.crt
# sudo chmod a+r /usr/share/ca-certificates/goagent.crt
# sudo dpkg-reconfigure ca-certificates

# -----------------------------------------------------------------------------
# git
# git config --global core.excludesfile ~/.gitignore_global
# git 的其他配置
# 	credential.helper=cache --timeout=3600000
# 	user.name=Breeze Wu
# 	user.email=dawningbreezee@gmail.com
# 	core.autocrlf=false
# 	core.excludesfile=/home/hadoop/.gitignore_global
# 	color.ui=true
# 	alias.lg=log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit
# 	core.repositoryformatversion=0
# 	core.filemode=true
# 	core.bare=false
# 	core.logallrefupdates=true
# 	remote.origin.url=git@github.com:BreezeWu/hadoop-ws.git
# 	remote.origin.fetch=+refs/heads/*:refs/remotes/origin/*
# 	branch.master.remote=origin
# 	branch.master.merge=refs/heads/master
#

# -----------------------------------------------------------------------------
# wine
export WINEPREFIX=$HOME/wine32
export WINEARCH=win32

# -----------------------------------------------------------------------------
# 添加当前目录到PATH
export PATH=.:${PATH}
