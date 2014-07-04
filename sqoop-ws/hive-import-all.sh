#! /usr/bin/env bash

#查看一下表列表
sqoop --options-file list-tables-oracle.sqoop --verbose

#导入用户信息  ---error!!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_USER_INFO_S01 --split-by 行业

#月用电量 ---error!!!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_ARC_VOLUME_PERM_S01 --split-by 年月
#用户逐月缴费欠费信息 ---ok!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_A_RCVBL_FLOW_PM_S01 --split-by 年月
#违约用电次数 ---ok!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_POWER_STEAL_PERY_S01 --split-by 年份
#用户逐年缴费方式变更次数 --ok!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_CHANGE_PAYTYPE_PY_S01 --split-by 年份
#是否阶梯电价 ---error!!!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_TS_OR_PRCSCOPE_S01 --split-by 是否分时,是否阶梯
#分时电价电量 ---error!!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_VOLUME_OF_TS_S01 --split-by YM
#阶梯电价电量  ---error!!
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_VOLUME_OF_PRC_S01 --split-by YM

