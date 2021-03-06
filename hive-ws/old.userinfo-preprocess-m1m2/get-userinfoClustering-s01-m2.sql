-- ----------------------------------------------------------------------------
-- 获得 2013年全年,双月用户数据 (沈阳21401,大连21408)

-- 抽样查看数据
select * from BIGDATA_USER_INFO_S01 where (org_no like "21401%" or org_no like "21408%") and status_code = "非销户" limit 10;

-- 创建唯一cons_id和cons_no
-- 下面语句执行后,会有一行(NULL,NULL), 但在mysql中没有这个值!
-- hive, 20289	mysql, 20288
CREATE TABLE BIGDATA_USER_INFO_S01_2013M2_IDNO_MAP AS
select x.cons_id, a.cons_no from (select distinct cons_id from BIGDATA_USER_INFO_S01 where (org_no like "21401%" or org_no like "21408%") and status_code = "非销户") x
    left outer join BIGDATA_USER_INFO_S01 a on a.cons_id= x.cons_id
;

-- 创建一个大横表
-- ## 用户信息表 BIGDATA_USER_INFO_S01  一个列也不能取?	x
-- ## 月用电量(横表) BIGDATA_ARC_VOLUME_PERM_S01_V	a
-- ## 用户逐月缴费欠费信息(横表) BIGDATA_RCVBL_FLOW_PM_S01_V	b
-- ## 违约用电次数 BIGDATA_POWER_STEAL_PERY_S01	c
-- ## 是否阶梯电价 BIGDATA_TS_OR_PRCSCOPE_S01	d
--	用户信息表 BIGDATA_USER_INFO_S01			没有可计算数值数据,未从该表取数 x.*,
--	是否阶梯电价 BIGDATA_TS_OR_PRCSCOPE_S01	d	没有可计算数值数据,未从该表取数 d.*,
CREATE TABLE BIGDATA_USER_INFO_S01_2013M2_V_FOR_CLUSTERING AS
select a.*,b.*,c.inspect_count,d.org_no,d.elec_type_code,d.volt_code,d.urban_rural_flag,d.trade_code from BIGDATA_USER_INFO_S01_2013M2_IDNO_MAP x 
    left outer join BIGDATA_ARC_VOLUME_PERM_S01_V a on a.cons_id = x.cons_id
    left outer join BIGDATA_RCVBL_FLOW_PM_S01_V b on trim(b.cons_no) = trim(x.cons_no)
    left outer join (select * from BIGDATA_POWER_STEAL_PERY_S01 where y = '2013') c on c.cons_id = x.cons_id
    left outer join BIGDATA_USER_INFO_S01 d on d.cons_id = x.cons_id
;


-- 查看表结构
desc BIGDATA_USER_INFO_S01_2013M2_V_FOR_CLUSTERING;
