#! /usr/bin/env bash

# 清空中间目录
# hdfs dfs -rm -R /user/hadoop/tmp-for-sqoop-hive
# 查看临时目录
# hdfs dfs -ls -R /user/hadoop/tmp-for-sqoop-hive

# **********oracle 数据的表名必须大写，否则会提示 Imported Failed: Attempted to generate class with no columns!

#查看一下表列表
sqoop --options-file list-tables-oracle.sqoop --verbose

# ------------------------------------------------------------------------------
# 用户信息  [ok]	hive中统计记录数 21275800
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_USER_INFO_S98 --split-by 行业
sqoop --options-file hive-import-oracle-sqlquery.sqoop --query '
    select 
        用户ID cons_id,客户名称 cons_name,电价代码 prc_code, 地市局编码 org_no,
        用电类别 elec_type_code,供电电压 volt_code_1,合同容量 contract_cap,
        负荷类型 lode_attr_code,当前是否销户 status_code,城镇用户农村用户 urban_rural_flag,
        行业 trade_code,高压低压 cust_type_code,用户号 cons_no,电压 volt_code_2
    from BIGDATA_USER_INFO_S98
 WHERE $CONDITIONS ' --split-by 行业 --boundary-query "
    select min(trade_code),max(trade_code) from META_TRADE_TYPE
" --target-dir /user/hadoop/tmp-for-sqoop-hive/BIGDATA_USER_INFO_S98 --hive-table BIGDATA_USER_INFO_S98 --mapreduce-job-name BIGDATA_USER_INFO_S98

# ------------------------------------------------------------------------------
# 月用电量   [ok]	hive中统计记录数 283132624
# 修改列名
# 	alter table BIGDATA_ARC_VOLUME_PERM_S98 rename column 用户ID  to cons_id;
# 	alter table BIGDATA_ARC_VOLUME_PERM_S98 rename column 月用电量  to volume_per_month;
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_ARC_VOLUME_PERM_S98 --split-by 年月
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_ARC_VOLUME_PERM_S98 --split-by YM --boundary-query "
    select '201301','201406' from dual
"  --mapreduce-job-name BIGDATA_ARC_VOLUME_PERM_S98

# 用户逐月缴费欠费信息  [ok]	hive中统计记录数 1258497
# 修改列名
#		alter table BIGDATA_RCVBL_FLOW_PM_S98 rename column 用户号  to cons_no;
#		alter table BIGDATA_RCVBL_FLOW_PM_S98 rename column 年月  to YM;
#		alter table BIGDATA_RCVBL_FLOW_PM_S98 rename column 实收  to rcved_amt;
#		alter table BIGDATA_RCVBL_FLOW_PM_S98 rename column 应收  to rcvbl_amt;
#		alter table BIGDATA_RCVBL_FLOW_PM_S98 rename column 欠费  to owning_amt;
#	sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_RCVBL_FLOW_PM_S98 --split-by 年月
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_RCVBL_FLOW_PM_S98 --split-by YM


# 违约用电次数 [ok]	41833
# 修改列名
#		alter table BIGDATA_POWER_STEAL_PERY_S98 rename column 用户ID  to cons_id;
#		alter table BIGDATA_POWER_STEAL_PERY_S98 rename column 年份  to Y;
#		alter table BIGDATA_POWER_STEAL_PERY_S98 rename column 违约用电次数  to inspect_count;
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_POWER_STEAL_PERY_S98 --split-by 年份
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_POWER_STEAL_PERY_S98 --split-by Y

# 用户逐年缴费方式变更次数 [ok]	227788
# 修改列名
#		alter table BIGDATA_CHANGE_PAYTYPE_PY_S98 rename column 用户ID  to cons_id;
#		alter table BIGDATA_CHANGE_PAYTYPE_PY_S98 rename column 年份  to Y;
#		alter table BIGDATA_CHANGE_PAYTYPE_PY_S98 rename column 缴费方式变更次数  to change_payType_count;
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_CHANGE_PAYTYPE_PY_S98 --split-by 年份
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_CHANGE_PAYTYPE_PY_S98 --split-by Y

# ------------------------------------------------------------------------------
#是否阶梯电价   [ok]	hive中统计记录数 21498140
# 修改列名
#		alter table BIGDATA_TS_OR_PRCSCOPE_S98 rename column 用户号  to cons_no;
#		alter table BIGDATA_TS_OR_PRCSCOPE_S98 rename column 电价名称  to prc_code;
#		alter table BIGDATA_TS_OR_PRCSCOPE_S98 rename column 是否分时  to ts_flag;
#		alter table BIGDATA_TS_OR_PRCSCOPE_S98 rename column 是否阶梯  to ladder_flag;
# sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_TS_OR_PRCSCOPE_S98 --split-by 是否分时,是否阶梯
#
# TS_FLAG和LADDER_FLAG是汉字'否'和'是'，不能用来分区，新增了列ts_flag_i,ladder_flag_i。
# SQOOP 1.4.4 目前不能支持多列split。
# 	sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_TS_OR_PRCSCOPE_S98 --split-by ts_flag_i,ladder_flag_i
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_TS_OR_PRCSCOPE_S98 --split-by cons_no  --boundary-query "
    select  0000000026,8202921013 from dual
"  --mapreduce-job-name BIGDATA_TS_OR_PRCSCOPE_S98

# 下面这个速度慢
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_TS_OR_PRCSCOPE_S98 --split-by cons_no

# ------------------------------------------------
# 分时电价电量 和 阶梯电价电量 的全量表列名为中文,需要转换为英文
# 	参见 "sql-oracle.sql"
#分时电价电量 ---表不存在
# sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_VOLUME_OF_TS_S98 --split-by YM
#阶梯电价电量  ---表不存在
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_VOLUME_OF_PRC_S98 --split-by YM

# ------------------------------------------------------------------------------
# 行业类型表 META_TRADE_TYPE	 [ok]	550
sqoop --options-file hive-import-oracle.sqoop --table META_TRADE_TYPE --split-by TRADE_LEVEL

# 编码对照表 EPSA_LN.SA_PROP_LIST	[ok]	13549
sqoop --options-file hive-import-oracle-sqlquery.sqoop --query 'SELECT * from EPSA_LN.SA_PROP_LIST WHERE $CONDITIONS' --hive-table META_PROP_LIST --split-by PROP_TYPE_ID --target-dir /user/hadoop/tmp-for-sqoop-hive/META_PROP_LIST
