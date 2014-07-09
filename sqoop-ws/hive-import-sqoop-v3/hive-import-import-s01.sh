#! /usr/bin/env bash

# 清空中间目录
# hdfs dfs -rm -R /user/hadoop/tmp-for-sqoop-hive
# 查看临时目录
# hdfs dfs -ls -R /user/hadoop/tmp-for-sqoop-hive

# **********oracle 数据的表名必须大写，否则会提示 Imported Failed: Attempted to generate class with no columns!

#查看一下表列表
sqoop --options-file list-tables-oracle.sqoop --verbose

# ------------------------------------------------------------------------------
# 用户信息  [ok]	hive中统计记录数 20289
#	s01中没有"电压 volt_code_2"，且行业使用了名称，需要进行转换 BIGDATA_USER_INFO_S01_v2
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_USER_INFO_S01 --split-by 行业
sqoop --options-file hive-import-oracle-forV3.sqoop --query '
    select 
        用户ID cons_id,客户名称 cons_name,电价代码 prc_code, 地市局编码 org_no,
        用电类别 elec_type_code,供电电压 volt_code_1,合同容量 contract_cap,
        负荷类型 lode_attr_code,当前是否销户 status_code,城镇用户农村用户 urban_rural_flag,
        行业编码 trade_code,高压低压 cust_type_code,用户号 cons_no
    from BIGDATA_USER_INFO_S01_v2
 WHERE $CONDITIONS ' --split-by 行业编码 --boundary-query "
    select min(trade_code),max(trade_code) from META_TRADE_TYPE
" --target-dir /user/hadoop/tmp-for-sqoop-hive/BIGDATA_USER_INFO_S01 --hive-table BIGDATA_USER_INFO_S01 --mapreduce-job-name BIGDATA_USER_INFO_S01

# ------------------------------------------------------------------------------
# 月用电量   [ok]	hive中统计记录数 474354
# 修改列名
# 	alter table BIGDATA_ARC_VOLUME_PERM_S01 rename column 用户ID  to cons_id;
# 	alter table BIGDATA_ARC_VOLUME_PERM_S01 rename column 月用电量  to volume_per_month;
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_ARC_VOLUME_PERM_S01 --split-by 年月
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_ARC_VOLUME_PERM_S01 --split-by YM --boundary-query "
    select '201001','201406' from dual
"  --mapreduce-job-name BIGDATA_ARC_VOLUME_PERM_S01

# 用户逐月缴费欠费信息  [ok]	hive中统计记录数 42
# 修改列名
#		alter table BIGDATA_RCVBL_FLOW_PM_S01 rename column 用户号  to cons_no;
#		alter table BIGDATA_RCVBL_FLOW_PM_S01 rename column 年月  to YM;
#		alter table BIGDATA_RCVBL_FLOW_PM_S01 rename column 实收  to rcved_amt;
#		alter table BIGDATA_RCVBL_FLOW_PM_S01 rename column 应收  to rcvbl_amt;
#		alter table BIGDATA_RCVBL_FLOW_PM_S01 rename column 欠费  to owning_amt;
#	sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_RCVBL_FLOW_PM_S01 --split-by 年月
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_RCVBL_FLOW_PM_S01 --split-by YM


# 违约用电次数 [ok]	85
# 修改列名
#		alter table BIGDATA_POWER_STEAL_PERY_S01 rename column 用户ID  to cons_id;
#		alter table BIGDATA_POWER_STEAL_PERY_S01 rename column 年份  to Y;
#		alter table BIGDATA_POWER_STEAL_PERY_S01 rename column 违约用电次数  to inspect_count;
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_POWER_STEAL_PERY_S01 --split-by 年份

# 下面语句导出结果是42，但应该是85，原因在于原始数据里有年月是空的
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_POWER_STEAL_PERY_S01 --split-by Y

# 改为根据cons_id 85
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_POWER_STEAL_PERY_S01 --split-by cons_id

# 用户逐年缴费方式变更次数 [ok]	217
# 修改列名
#		alter table BIGDATA_CHANGE_PAYTYPE_PY_S01 rename column 用户ID  to cons_id;
#		alter table BIGDATA_CHANGE_PAYTYPE_PY_S01 rename column 年份  to Y;
#		alter table BIGDATA_CHANGE_PAYTYPE_PY_S01 rename column 缴费方式变更次数  to change_payType_count;
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_CHANGE_PAYTYPE_PY_S01 --split-by 年份
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_CHANGE_PAYTYPE_PY_S01 --split-by Y

# ------------------------------------------------------------------------------
#是否阶梯电价   [ok]	hive中统计记录数 20289
# 修改列名
#		alter table BIGDATA_TS_OR_PRCSCOPE_S01 rename column 用户号  to cons_no;
#		alter table BIGDATA_TS_OR_PRCSCOPE_S01 rename column 电价名称  to prc_code;
#		alter table BIGDATA_TS_OR_PRCSCOPE_S01 rename column 是否分时  to ts_flag;
#		alter table BIGDATA_TS_OR_PRCSCOPE_S01 rename column 是否阶梯  to ladder_flag;
# sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_TS_OR_PRCSCOPE_S01 --split-by 是否分时,是否阶梯
#
# TS_FLAG和LADDER_FLAG是汉字'否'和'是'，不能用来分区，新增了列ts_flag_i,ladder_flag_i。
# SQOOP 1.4.4 目前不能支持多列split。
# 	sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_TS_OR_PRCSCOPE_S01 --split-by ts_flag_i,ladder_flag_i
#sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_TS_OR_PRCSCOPE_S01 --split-by ts_flag_i  --boundary-query "
#    select  0000000026,8202921013 from dual
#"  --mapreduce-job-name BIGDATA_TS_OR_PRCSCOPE_S01

# 下面这个速度慢,但对于S01小数据集无所谓
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_TS_OR_PRCSCOPE_S01 --split-by cons_no

#分时电价电量   [ok]	hive中统计记录数 1130
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_VOLUME_OF_TS_S01 --split-by YM
#阶梯电价电量   [??]	hive中统计记录数 176628
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_VOLUME_OF_PRC_S01 --split-by YM

# ------------------------------------------------------------------------------
# 基础数据表只需导入一次

# 行业类型表 META_TRADE_TYPE	 [ok]	550
# sqoop --options-file hive-import-oracle.sqoop --table META_TRADE_TYPE --split-by TRADE_LEVEL

# 编码对照表 EPSA_LN.SA_PROP_LIST	[ok]	13549
# sqoop --options-file hive-import-oracle-forV3.sqoop --query 'SELECT * from EPSA_LN.SA_PROP_LIST WHERE $CONDITIONS' --hive-table META_PROP_LIST --split-by PROP_TYPE_ID --target-dir /user/hadoop/tmp-for-sqoop-hive/META_PROP_LIST
