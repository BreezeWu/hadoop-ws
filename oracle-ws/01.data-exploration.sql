select * from BIGDATA_CHANGE_PAYTYPE_PY_S98 where rownum < 100;

select count(*) from BIGDATA_CHANGE_PAYTYPE_PY_S01;
select count(*) from BIGDATA_POWER_STEAL_PERY_S01;

select count(*) from BIGDATA_USER_INFO_S98;

# 用户信息
BIGDATA_USER_INFO_S98
# 月用电量
BIGDATA_ARC_VOLUME_PERM_S98
# 用户逐月缴费欠费信息    ---表不存在
BIGDATA_RCVBL_FLOW_PM_S98

# 用户逐年缴费方式变更次数
BIGDATA_CHANGE_PAYTYPE_PY_S98
# 违约用电次数
BIGDATA_POWER_STEAL_PERY_S98

# 是否阶梯电价    ---表不存在
BIGDATA_TS_OR_PRCSCOPE_S98
# 分时电价电量    ---表不存在
BIGDATA_VOLUME_OF_TS_S98
# 阶梯电价电量    ---表不存在
BIGDATA_VOLUME_OF_PRC_S98

#---------------------meta table -------------
# 行业类型表 ok  550
META_TRADE_TYPE

# 编码对照表 ok  13549  
META_PROP_LIST
# META_PROP_LIST        (ORACLE中为EPSA_LN.SA_PROP_LIST)
SELECT * from EPSA_LN.SA_PROP_LIST META_PROP_LIST where rownum < 10;