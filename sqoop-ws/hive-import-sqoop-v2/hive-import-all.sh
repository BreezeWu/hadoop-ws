#! /usr/bin/env bash

# 清空中间目录
# hdfs dfs -rm -R /user/hadoop/tmp-for-sqoop-hive
# 查看临时目录
# hdfs dfs -ls -R /user/hadoop/tmp-for-sqoop-hive

# **********oracle 数据的表名必须大写，否则会提示 Imported Failed: Attempted to generate class with no columns!

#查看一下表列表
sqoop --options-file list-tables-oracle.sqoop --verbose

#导入用户信息  ---error!!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_USER_INFO_S98 --split-by 行业

#月用电量 ---error!!!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_ARC_VOLUME_PERM_S98 --split-by 年月

#用户逐月缴费欠费信息  ---error!!!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_RCVBL_FLOW_PM_S98 --split-by 年月

#违约用电次数 [ok]	41833
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_POWER_STEAL_PERY_S98 --split-by 年份
#用户逐年缴费方式变更次数 [ok]	41833
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_CHANGE_PAYTYPE_PY_S98 --split-by 年份

# 下面三个表仅导入了 “ 21407 营口的 ”
#是否阶梯电价 ---表不存在
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_TS_OR_PRCSCOPE_S98 --split-by 是否分时,是否阶梯
#分时电价电量 ---表不存在
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_VOLUME_OF_TS_S98 --split-by YM
#阶梯电价电量  ---表不存在
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_VOLUME_OF_PRC_S98 --split-by YM

# --------------------------------
# 行业类型表 META_TRADE_TYPE	 [ok]	550
sqoop --options-file hive-import-oracle.sqoop --table META_TRADE_TYPE --split-by TRADE_LEVEL

# 编码对照表 EPSA_LN.SA_PROP_LIST	[ok]	13549
# 跨shema导入数据hive会提示数据库不存在
# 	14/07/08 15:23:33 INFO hive.HiveImport: FAILED: SemanticException [Error 10072]: Database does not exist: EPSA_LN
#		14/07/08 15:23:33 ERROR tool.ImportTool: Encountered IOException running import job: java.io.IOException: Hive exited with status 88
# sqoop --options-file hive-import-oracle.sqoop --table EPSA_LN.SA_PROP_LIST --split-by PROP_TYPE_ID

# 在oracle 创建一个视图，基于视图导入数据	
# create view META_PROP_LIST as (select * from EPSA_LN.SA_PROP_LIST)
sqoop --options-file hive-import-oracle.sqoop --table META_PROP_LIST --split-by PROP_TYPE_ID

# 或者，使用--query-dir （但同时，必须指定--target-dir,并去掉--warehouse-dir）
# 注意：下面使用的--options-file是common-oracle.sqoop, 并指定了--hive-import
#sqoop import --hive-import --hive-table META_PROP_LIST-02 --options-file common-oracle.sqoop --query "SELECT * from EPSA_LN.SA_PROP_LIST META_PROP_LIST02 WHERE $CONDITIONS" --split-by PROP_TYPE_ID --target-dir /user/hadoop/tmp-for-sqoop-hive/META_PROP_LIST-02
# 出错了！  Encountered IOException running import job: java.io.IOException: Query [SELECT * from EPSA_LN.SA_PROP_LIST META_PROP_LIST02 WHERE ] must contain '$CONDITIONS' in WHERE clause.

