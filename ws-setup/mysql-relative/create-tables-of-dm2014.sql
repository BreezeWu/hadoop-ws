--创建电力样例数据表
-------------------------------------------------------------------------------
-- ﻿用户ID,客户名称,电价代码,地市局编码,用电性质,用电类别,供电电压,合同容量,负荷类型,当前是否销户,城镇用户农村用户,出账周期,行业,高压低压,用户号
-- cons_id,cons_name,prc_code,org_no,unkown_ydxz,elec_type_code,volt_code,contract_cap,lode_attr_code,status_code,urban_rural_flag,unkown_czzq,trade_code,cust_type_code,cons_no
--drop table BIGDATA_USER_INFO_S01;
CREATE TABLE BIGDATA_USER_INFO_S01
(
  cons_id      int(16)                        NOT NULL,
  cons_name      VARCHAR(256),
  prc_code      VARCHAR(8),
  org_no     VARCHAR(16),
  unkown_ydxz      CHAR(1),
  elec_type_code      VARCHAR(256),
  volt_code      VARCHAR(256),
  contract_cap      float(16,6),
  lode_attr_code      VARCHAR(256),
  status_code    VARCHAR(6),
  urban_rural_flag  VARCHAR(256),
  unkown_czzq      CHAR(1),
  trade        VARCHAR(60),
  cust_type_code      VARCHAR(256),
  cons_no       VARCHAR(16)
);


/*
-- mysql修改列明
alter table BIGDATA_USER_INFO_S01 change column trade_code trade VARCHAR(60);
-- 增加一列并更新
alter table BIGDATA_USER_INFO_S01 add  column trade_code varchar(5);

--更新数据
update BIGDATA_USER_INFO_S01 a set a.trade_code = (select x.trade_code from META_TRADE_TYPE x where trim(x.trade) = trim(a.trade));
select * from BIGDATA_USER_INFO_S01 limit 2;
select * from META_TRADE_TYPE limit 2;
*/

/*
    select 
        用户ID cons_id,客户名称 cons_name,电价代码 prc_code, 地市局编码 org_no,
        用电类别 elec_type_code,供电电压 volt_code_1,合同容量 contract_cap,
        负荷类型 lode_attr_code,当前是否销户 status_code,城镇用户农村用户 urban_rural_flag,
        行业编码 trade_code,高压低压 cust_type_code,用户号 cons_no
    from BIGDATA_USER_INFO_S01_v2
*/
-------------------------------------------------------------------------------
-- 月用电量
--drop table BIGDATA_ARC_VOLUME_PERM_S01;
CREATE TABLE BIGDATA_ARC_VOLUME_PERM_S01
(
  cons_id  Int(16),
  ym    VARCHAR(6),
  volume_per_month Int
);

-- 用户逐月缴费欠费信息
--drop table BIGDATA_RCVBL_FLOW_PM_S01;
CREATE TABLE BIGDATA_RCVBL_FLOW_PM_S01
(
  cons_no  VARCHAR(16),
  ym   VARCHAR(6),
  rcved_amt   FLOAT,
  rcvbl_amt   FLOAT,
  owning_amt  FLOAT
);

--违约用电次数(逐年)
--drop table BIGDATA_POWER_STEAL_PERY_S01;
CREATE TABLE BIGDATA_POWER_STEAL_PERY_S01
(
  cons_id    INT(16)                          NOT NULL,
  y      VARCHAR(8),
  inspect_count  INT
);

-- 缴费方式变更次数(逐年)
--drop table BIGDATA_CHANGE_PAYTYPE_PY_S01;
CREATE TABLE BIGDATA_CHANGE_PAYTYPE_PY_S01
(
  cons_id    INT(16)                          NOT NULL,
  y        VARCHAR(4),
  change_payType_count  INT
);

-- 是否阶梯电价
--drop table BIGDATA_TS_OR_PRCSCOPE_S01;
CREATE TABLE BIGDATA_TS_OR_PRCSCOPE_S01
(
  cons_no   VARCHAR(16),
  prc_code  VARCHAR(256),
  ts_flag  VARCHAR(2),
  ladder_flag  VARCHAR(2)
);

--分时电量电价
--drop table BIGDATA_VOLUME_OF_TS_S01;
CREATE TABLE BIGDATA_VOLUME_OF_TS_S01
(
  cons_no VARCHAR(16),
  YM VARCHAR(6),
  kwh_volume_top   INT,
  kwh_prc_top   FLOAT,
  kwh_volume_high   INT,
  kwh_prc_high   FLOAT,
  kwh_volume_mean   INT,
  kwh_prc_mean   FLOAT,
  kwh_volume_low   INT,
  kwh_prc_low   FLOAT,
  kwh_volume_bottom   INT,
  kwh_prc_bottom   FLOAT
);

-- 阶梯电价电量
--drop table BIGDATA_VOLUME_OF_PRC_S01;
CREATE TABLE BIGDATA_VOLUME_OF_PRC_S01
(
  CONS_NO VARCHAR(16),
  YM  VARCHAR(6),
  u_pq_1   INT,
  prc_u_pq_1   FLOAT,
  u_pq_2   INT,
  prc_u_pq_2   FLOAT,
  u_pq_3   INT,
  prc_u_pq_4   FLOAT
);
-------------------------------------------------------------------------------
--行业信息表
--drop table META_TRADE_TYPE;
CREATE TABLE META_TRADE_TYPE
(
  TRADE_CODE      varchar(5)              NOT NULL,
  SERIAL          int(5),
  TRADE           varchar(60)             NOT NULL,
  RPT_TAG         varchar(5),
  ARREAR_TRADE    varchar(3),
  VISIBLE_TAG     varchar(3),
  SUPERIOR_TRADE  varchar(5),
  DEGREE_TRADE    varchar(5),
  TRADE_LEVEL     int                       DEFAULT 99                    NOT NULL,
  SUPER0          varchar(5),
  SUPER1          varchar(5),
  SUPER2          varchar(5),
  SUPER3          varchar(5),
  SUPER4          varchar(5),
  SUPER5          varchar(5)
);
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------


