v2版本，即使修改了mysql的字符集为UTF8，导入时仍然提示下面错误：

14/07/08 18:12:52 INFO hive.HiveImport: NestedThrowablesStackTrace:
14/07/08 18:12:52 INFO hive.HiveImport: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Specified key was too long; max key length is 767 bytes

所以，在v3版本中，改为使用--query，并重命名列名的方式。

# --------------------------------------
# mysql支持utf-8，同时还需要修改hive数据库的字符集为latin1

mysql -u hive -phive -h master-hadoop
use hive;
alter database hive character set latin1;


