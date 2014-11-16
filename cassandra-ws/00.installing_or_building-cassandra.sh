# -----------------------------------------------------------------------------
# 1. installing cassandra 2.1.2
# according to http://wiki.apache.org/cassandra/GettingStarted

mkdir /home/hadoop/data-cassandra-2.1.2/data
mkdir /home/hadoop/data-cassandra-2.1.2/commitlog
mkdir /home/hadoop/data-cassandra-2.1.2/saved_caches

# 备份原来的配置文件
# cp ${CASSANDRA_HOME}/conf/cassandra.yaml ${CASSANDRA_HOME}/conf/cassandra.yaml-org
# 连接到这里的配置文件
export CONF_SPARK_ECOSYSTEM=/home/hadoop/workspace_github/hadoop-ws/conf-spark-ecosystem
export CASSANDRA_VERSION=2.1.2
# ln -s ${CONF_SPARK_ECOSYSTEM}/cassandra-${CASSANDRA_VERSION}.yaml ${CASSANDRA_HOME}/conf/cassandra.yaml

# start at foreground: Press "Control-C" to stop Cassandra.
cassandra -f

# start at background
cassandra
# You can stop the process by killing it, using 'pkill -f CassandraDaemon', for example.

# Using cqlsh
cqlsh

# -----------------------------------------------------------------------------
# cqlsh
# For clarity, we will omit the cqlsh prompt in the following examples.

# First, create a keyspace -- a namespace of tables.
CREATE KEYSPACE mykeyspace
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

# Second, authenticate to the new keyspace:
USE mykeyspace;

# Third, create a users table:
CREATE TABLE users (
  user_id int PRIMARY KEY,
  fname text,
  lname text
);

# Now you can store data into users:
INSERT INTO users (user_id,  fname, lname)
  VALUES (1745, 'john', 'smith');
INSERT INTO users (user_id,  fname, lname)
  VALUES (1744, 'john', 'doe');
INSERT INTO users (user_id,  fname, lname)
  VALUES (1746, 'john', 'smith');

# Now let's fetch the data you inserted:
SELECT * FROM users;
# 此时,不能带索引查询
SELECT * FROM users WHERE lname = 'smith';
    # code=2200 [Invalid query] message="No indexed columns present in by-columns clause with Equal operator"

# You can retrieve data about users whose last name is smith by creating an index, then querying the table as follows:
CREATE INDEX ON users (lname);
SELECT * FROM users WHERE lname = 'smith';

# -----------------------------------------------------------------------------
# 其他: 驱动程序
# spark-cassandra-connector
#   https://github.com/datastax/spark-cassandra-connector