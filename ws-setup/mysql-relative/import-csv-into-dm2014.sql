--##############################################################################
--# 样例
--#	mysqlimport  #ignore-lines=1 #fields-terminated-by=,
--#	columns='ID,Name,Phone,Address' #local -u root -p
--#	Database /path/to/csvfile/TableName.csv 
--#
--#######################################-
--# 注意事项
--# 	Loads tables from text files in various formats.  The base name of the
--#	text file must be the name of the table that should be used.
--#	If one uses sockets to connect to the MySQL server, the server will open and
--#	read the text file directly. In other cases the client will open the text
--#	file. The SQL command 'LOAD DATA INFILE' is used to import the rows.
--
--export MYDATA_HOME=/home/hadoop/workspace_github/hadoop-ws/ws-setup/mysql-relative/data.2014-06-16.utf-8
--
--# 行业信息表
--mysqlimport #ignore-lines=1 #fields-terminated-by=, #local -u wuhz -pwuhz -h master-hadoop dm2014 ${MYDATA_HOME}/META_TRADE_TYPE.utf-8.txt
--
--##############################################################################
--### -- 用户信息表 ########出错了! 全部改为 load data infile 方式
--mysqlimport #ignore-lines=1 #fields-terminated-by=, #local -u wuhz -pwuhz -h master-hadoop dm2014 ${MYDATA_HOME}/BIGDATA_USER_INFO_S01.utf-8.txt

-------------------------------------------------------------------------------
-- 在mysql命令行中
-----------------------------------------
-- meta
load data infile '/home/hadoop/workspace_github/hadoop-ws/ws-setup/mysql-relative/data.2014-06-16.utf-8/META_TRADE_TYPE.txt'   
into table META_TRADE_TYPE
fields terminated by ','  optionally enclosed by '"' escaped by '"'   
lines terminated by '\r\n' IGNORE 1 LINES;   

-----------------------------------------
-- 用户信息表 
load data infile '/home/hadoop/workspace_github/hadoop-ws/ws-setup/mysql-relative/data.2014-06-16.utf-8/BIGDATA_USER_INFO_S01.txt'   
into table BIGDATA_USER_INFO_S01
fields terminated by ','  optionally enclosed by '"' escaped by '"'   
lines terminated by '\r\n' IGNORE 1 LINES;   

-- 月用电量 
load data infile '/home/hadoop/workspace_github/hadoop-ws/ws-setup/mysql-relative/data.2014-06-16.utf-8/BIGDATA_ARC_VOLUME_PERM_S01.txt'   
into table BIGDATA_ARC_VOLUME_PERM_S01 
fields terminated by ','  optionally enclosed by '"' escaped by '"'   
lines terminated by '\r\n'  IGNORE 1 LINES;   

-- 用户逐月缴费欠费信息
load data infile '/home/hadoop/workspace_github/hadoop-ws/ws-setup/mysql-relative/data.2014-06-16.utf-8/BIGDATA_RCVBL_FLOW_PM_S01.txt'   
into table BIGDATA_RCVBL_FLOW_PM_S01 
fields terminated by ','  optionally enclosed by '"' escaped by '"'   
lines terminated by '\r\n'  IGNORE 1 LINES; 

--违约用电次数(逐年)
load data infile '/home/hadoop/workspace_github/hadoop-ws/ws-setup/mysql-relative/data.2014-06-16.utf-8/BIGDATA_POWER_STEAL_PERY_S01.txt'   
into table BIGDATA_POWER_STEAL_PERY_S01
fields terminated by ','  optionally enclosed by '"' escaped by '"'   
lines terminated by '\r\n'  IGNORE 1 LINES; 

-- 缴费方式变更次数(逐年)
load data infile '/home/hadoop/workspace_github/hadoop-ws/ws-setup/mysql-relative/data.2014-06-16.utf-8/BIGDATA_CHANGE_PAYTYPE_PY_S01.txt'   
into table BIGDATA_CHANGE_PAYTYPE_PY_S01
fields terminated by ','  optionally enclosed by '"' escaped by '"'   
lines terminated by '\r\n'  IGNORE 1 LINES; 

-- 是否阶梯电价
load data infile '/home/hadoop/workspace_github/hadoop-ws/ws-setup/mysql-relative/data.2014-06-16.utf-8/BIGDATA_TS_OR_PRCSCOPE_S01.txt'   
into table BIGDATA_TS_OR_PRCSCOPE_S01
fields terminated by ','  optionally enclosed by '"' escaped by '"'   
lines terminated by '\r\n'  IGNORE 1 LINES; 

--分时电量电价
load data infile '/home/hadoop/workspace_github/hadoop-ws/ws-setup/mysql-relative/data.2014-06-16.utf-8/BIGDATA_VOLUME_OF_TS_S01.txt'   
into table BIGDATA_VOLUME_OF_TS_S01
fields terminated by ','  optionally enclosed by '"' escaped by '"'   
lines terminated by '\r\n'  IGNORE 1 LINES; 

-- 阶梯电价电量
load data infile '/home/hadoop/workspace_github/hadoop-ws/ws-setup/mysql-relative/data.2014-06-16.utf-8/BIGDATA_VOLUME_OF_PRC_S01.txt'   
into table BIGDATA_VOLUME_OF_PRC_S01
fields terminated by ','  optionally enclosed by '"' escaped by '"'   
lines terminated by '\r\n'  IGNORE 1 LINES; 
-------------------------------------------------------------------------------


