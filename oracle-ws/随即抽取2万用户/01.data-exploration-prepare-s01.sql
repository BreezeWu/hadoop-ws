--# -----------------------------------------------------------------------------
--# 用户信息
--# BIGDATA_USER_INFO_s01
--select * from BIGDATA_USER_INFO_s01 where rownum < 10;
--select min(用户ID),max(用户ID) from BIGDATA_USER_INFO_s01
--select min(trade_code),max(trade_code) from META_TRADE_TYPE
--select trade_code from META_TRADE_TYPE
--# 用电性质 和 出账周期 没有信息
--    select 
--        用户ID cons_id,客户名称 cons_name,电价代码 prc_code, 地市局编码 org_no,
--        用电类别 elec_type_code,供电电压 volt_code_1,合同容量 contract_cap,
--        负荷类型 lode_attr_code,当前是否销户 status_code,城镇用户农村用户 urban_rural_flag,
--        行业 trade_code,高压低压 cust_type_code,用户号 cons_no,电压 volt_code_2
--    from BIGDATA_USER_INFO_s01
--    where rownum < 10;

-- 需要将行业转换为行业编码
create table BIGDATA_USER_INFO_s01_v2 as
(
    select 
        用户ID,客户名称,电价代码, 地市局编码,
        用电类别,供电电压,合同容量,
        负荷类型,当前是否销户,城镇用户农村用户,
        --行业,
        (select trade_code from epm_ln.sa_c_trade_type e where e.trade= 行业) 行业编码,
        高压低压,用户号
    from BIGDATA_USER_INFO_s01
);
    
--# 月用电量
--BIGDATA_ARC_用户IDVOLUME_PERM_s01
alter table BIGDATA_ARC_VOLUME_PERM_s01 rename column 用户ID  to cons_id;
alter table BIGDATA_ARC_VOLUME_PERM_s01 rename column 年月  to YM;
alter table BIGDATA_ARC_VOLUME_PERM_s01 rename column 月用电量  to volume_per_month;

--# 用户逐月缴费欠费信息 
--BIGDATA_RCVBL_FLOW_PM_s01

alter table BIGDATA_A_RCVBL_FLOW_PM_s01 rename to BIGDATA_RCVBL_FLOW_PM_s01;
alter table BIGDATA_RCVBL_FLOW_PM_s01 rename column 用户号  to cons_no;
alter table BIGDATA_RCVBL_FLOW_PM_s01 rename column 年月  to YM;
alter table BIGDATA_RCVBL_FLOW_PM_s01 rename column 实收  to rcved_amt;
alter table BIGDATA_RCVBL_FLOW_PM_s01 rename column 应收  to rcvbl_amt;
alter table BIGDATA_RCVBL_FLOW_PM_s01 rename column 欠费  to owning_amt;
--select '201001','201406' from dual

--# 违约用电次数
--BIGDATA_POWER_STEAL_PERY_s01

alter table BIGDATA_POWER_STEAL_PERY_s01 rename column 用户ID  to cons_id;
alter table BIGDATA_POWER_STEAL_PERY_s01 rename column 年份  to Y;
alter table BIGDATA_POWER_STEAL_PERY_s01 rename column 违约用电次数  to inspect_count;

--# 用户逐年缴费方式变更次数
--BIGDATA_CHANGE_PAYTYPE_PY_s01

alter table BIGDATA_CHANGE_PAYTYPE_PY_s01 rename column 用户ID  to cons_id;
alter table BIGDATA_CHANGE_PAYTYPE_PY_s01 rename column 年份  to Y;
alter table BIGDATA_CHANGE_PAYTYPE_PY_s01 rename column 缴费方式变更次数  to change_payType_count;


--# 是否阶梯电价    
--BIGDATA_TS_OR_PRCSCOPE_s01
---最小最大cons_no 0000000026    8202921013
-- SELECT MIN(cons_no), MAX(cons_no) FROM BIGDATA_TS_OR_PRCSCOPE_s01

alter table BIGDATA_TS_OR_PRCSCOPE_s01 rename column 用户号  to cons_no;
alter table BIGDATA_TS_OR_PRCSCOPE_s01 rename column 电价名称  to prc_code;
alter table BIGDATA_TS_OR_PRCSCOPE_s01 rename column 是否分时  to ts_flag;
alter table BIGDATA_TS_OR_PRCSCOPE_s01 rename column 是否阶梯  to ladder_flag;

--alter table BIGDATA_TS_OR_PRCSCOPE_s01 add (ts_flag_i int);
--alter table BIGDATA_TS_OR_PRCSCOPE_s01 add (ladder_flag_i int);
--
--update table BIGDATA_TS_OR_PRCSCOPE_s01 set ts_flag_i = 0 where  ts_flag = '否';
--update table BIGDATA_TS_OR_PRCSCOPE_s01 set ts_flag_i = 1 where  ts_flag = '是';
--update table BIGDATA_TS_OR_PRCSCOPE_s01 set ladder_flag_i = 0 where  ladder_flag = '否';
--update table BIGDATA_TS_OR_PRCSCOPE_s01 set ladder_flag_i = 1 where  ladder_flag = '是';

--# 分时电价电量    ---表不存在
--BIGDATA_VOLUME_OF_TS_s01
alter table BIGDATA_VOLUME_OF_TS_s01 rename column 尖峰电量  to kwh_volume_top;
alter table BIGDATA_VOLUME_OF_TS_s01 rename column 尖峰电价  to kwh_prc_top;
alter table BIGDATA_VOLUME_OF_TS_s01 rename column 峰电量  to kwh_volume_high;
alter table BIGDATA_VOLUME_OF_TS_s01 rename column 峰电价  to kwh_prc_high;
alter table BIGDATA_VOLUME_OF_TS_s01 rename column 平电量  to kwh_volume_mean;
alter table BIGDATA_VOLUME_OF_TS_s01 rename column 平电价  to kwh_prc_mean;
alter table BIGDATA_VOLUME_OF_TS_s01 rename column 谷电量  to kwh_volume_low;
alter table BIGDATA_VOLUME_OF_TS_s01 rename column 谷电价  to kwh_prc_low;
alter table BIGDATA_VOLUME_OF_TS_s01 rename column 脊骨电量  to kwh_volume_bottom;
alter table BIGDATA_VOLUME_OF_TS_s01 rename column 脊骨电价  to kwh_prc_bottom;

--# 阶梯电价电量    ---表不存在
--BIGDATA_VOLUME_OF_PRC_s01
alter table BIGDATA_VOLUME_OF_PRC_s01 rename column 一阶电量  to u_pq_1;
alter table BIGDATA_VOLUME_OF_PRC_s01 rename column 一阶电价  to prc_u_pq_1;
alter table BIGDATA_VOLUME_OF_PRC_s01 rename column 二阶电量  to u_pq_2;
alter table BIGDATA_VOLUME_OF_PRC_s01 rename column 二阶电价  to prc_u_pq_2;
alter table BIGDATA_VOLUME_OF_PRC_s01 rename column 三阶电量  to u_pq_3;
alter table BIGDATA_VOLUME_OF_PRC_s01 rename column 三阶电价  to prc_u_pq_3;

--#---------------------meta table -------------
--# 行业类型表 ok  550
--META_TRADE_TYPE
--
--# 编码对照表 ok  13549  
--META_PROP_LIST
--# META_PROP_LIST        (ORACLE中为 EPSA_LN.SA_PROP_LIST)
--SELECT * from EPSA_LN.SA_PROP_LIST META_PROP_LIST where rownum < 10;

--# -----------------------------------------------------------------------------
--# 地市局编码的    238
--select count(*) from EPSA_LN.sa_org
--select * from EPSA_LN.sa_org
--select min(org_no),max(org_no) from EPSA_LN.sa_org
--select org_no from EPSA_LN.sa_org