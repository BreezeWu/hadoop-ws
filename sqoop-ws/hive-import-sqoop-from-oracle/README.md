===================
说明

1. 运行脚本
hive-import-s01.sh		抽样数据s01
hive-import-s98.sh		全量数据s98

hive-import-oracle.sqoop		导入hive参数文件,非自定义查询
hive-import-oracle-sqlquery.sqoop	导入hive参数文件,供自定义查询语句使用,如导入"编码对照表"和"用户信息"

# 下面两个测试使用
options-common-oracle.sqoop	通用oracle配置文件
options-import-oracle.sqoop	导入HDFS参数文件,非自定义查询

2. 约定
脚本所需要的oracle密码文件存放位置(HDFS):	/user/hadoop/sqoop.password

===================
# -----------------------------------------------------------------------------
# 错误处理历史信息:
v2版本，即使修改了mysql的字符集为UTF8，导入时仍然提示下面错误：

14/07/08 18:12:52 INFO hive.HiveImport: NestedThrowablesStackTrace:
14/07/08 18:12:52 INFO hive.HiveImport: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Specified key was too long; max key length is 767 bytes

所以，在v3版本中，改为使用--query，并重命名列名的方式。

# --------------------------------------
# mysql支持utf-8，同时还需要修改hive数据库的字符集为latin1

mysql -u hive -phive -h master-hadoop
use hive;
alter database hive character set latin1;


