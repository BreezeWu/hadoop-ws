-- ----------------------------------------------------------------------------
-- 数据探索 by hive

-- ----------------------------------------------------------------------------
-- 用户信息表
-- 表记录数	21273963
select count(cons_id) from BIGDATA_USER_INFO_S98;
-- 所有用户	20940685
select count(distinct cons_id) from BIGDATA_USER_INFO_S98;
-- 所有未销户用户	20294691
select count(distinct cons_id) from BIGDATA_USER_INFO_S98 where status_code !=9;
-- 所有已销户  647787 = 20941155 - 20293368

-- 0
select count(*) from BIGDATA_USER_INFO_S98 where cons_id is NULL;

-- 未销户用户中 cons_id 的数量
-- 
select count(distinct cons_id) from BIGDATA_USER_INFO_S98 where status_code !=9;
-- 未销户用户中 cons_no 的数量
-- 
select count(distinct cons_no) from BIGDATA_USER_INFO_S98 where status_code !=9;

-- --------------------------
-- 找出 用户信息表中 cons_id 出现多次的记录
-- 20940685
select count(*) from COUNTER_OF_BIGDATA_USER_INFO_S98;
-- 记录数大于等于2的就是重复的
select count(cons_id) from COUNTER_OF_BIGDATA_USER_INFO_S98 where counter >= 2;
-- 所有状态的用户数 	310279
-- 非销户用户数		288328

-- 当前处理办法是删除所有重复的数据
-- hive中不能删除，办法是重建数据
-- 不重复的 20006363
select count(cons_id) from COUNTER_OF_BIGDATA_USER_INFO_S98 where counter = 1;

-- 有重复的 288328
select count(cons_id) from COUNTER_OF_BIGDATA_USER_INFO_S98 where counter > 1;
-- ----------------------------------------------------------------------------
-- 未销户用户的 DISTICT ID 表
-- --------------------------
-- 只含有ID信息的表
-- 20294691
select count(*) from BIGDATA_USER_INFO_S98_ALIVE_DISTINCT_ID_ONLYID;
-- 20294691 
select count(distinct cons_id) from BIGDATA_USER_INFO_S98_ALIVE_DISTINCT_ID_ONLYID;

-- --------------------------
-- 含有ID和NO信息的表
-- 20006363
select count(*) from BIGDATA_USER_INFO_S98_ALIVE_DISTINCT_ID;
-- 20006363
select count(distinct cons_id) from BIGDATA_USER_INFO_S98_ALIVE_DISTINCT_ID;

-- 找出 cons_id 出现多次的记录
-- 20294691
--CREATE TABLE COUNTER_OF_BIGDATA_USER_INFO_S98_ALIVE_DISTINCT_ID AS
--select cons_id, count(cons_id) as counter from BIGDATA_USER_INFO_S98_ALIVE_DISTINCT_ID group by cons_id;
-- 记录数大于等于2的就是重复的	288328
-- 找出一些重复的样例
--select * as counter from COUNTER_OF_BIGDATA_USER_INFO_S98_ALIVE_DISTINCT_ID where counter >= 2 limit 10;
/*
100022  2
101191  2
101192  2
101972  2
101988  2
102585  2
103323  2
10560   2
106007  2
106848  2
*/

-- 找到 cons_id 是 0或NULL 的记录数
-- 0
select count(*) from BIGDATA_USER_INFO_S98_ALIVE_DISTINCT_ID where cons_id = 0 or  cons_id is NULL;
-- 找到 cons_no 是 0或NULL 的记录数
-- 0
select count(*) from BIGDATA_USER_INFO_S98_ALIVE_DISTINCT_ID where cons_no = 0 or cons_no is NULL;

-- ----------------------------------------------------------------------------
-- BIGDATA_POWER_STEAL_PERY_S98
-- 10056
select count(*) from BIGDATA_POWER_STEAL_PERY_S98 where y = '2013';
-- 5028
select count(distinct cons_id) from BIGDATA_POWER_STEAL_PERY_S98 where y = '2013';

-- 0
select count(*) from BIGDATA_POWER_STEAL_PERY_S98_Y2013_UNIQUE;
-- 0	所以，后面创建 BIGONETABLE 时不再使用该表
select count(distinct cons_id)  from BIGDATA_POWER_STEAL_PERY_S98_Y2013_UNIQUE;
-- ----------------------------------------------------------------------------
-- BIGDATA_ARC_VOLUME_PERM_S98_H
-- 20350807
select count(*) from BIGDATA_ARC_VOLUME_PERM_S98_H;
-- 20350807
select count(distinct cons_id) from BIGDATA_ARC_VOLUME_PERM_S98_H;
-- ----------------------------------------------------------------------------
-- BIGDATA_RCVBL_FLOW_PM_S98_H
-- 1251099
select count(*) from BIGDATA_RCVBL_FLOW_PM_S98_H;
-- 1251099
select count(distinct cons_no) from BIGDATA_RCVBL_FLOW_PM_S98_H;
-- ----------------------------------------------------------------------------
-- BIGDATA_TS_OR_PRCSCOPE_S98
-- 21498140
select count(*) from BIGDATA_TS_OR_PRCSCOPE_S98;
-- 21153252
select count(distinct cons_no) from BIGDATA_TS_OR_PRCSCOPE_S98;
-- 20837778
select count(*) from BIGDATA_TS_OR_PRCSCOPE_S98_UNIQUE;
-- 20837778
select count(distinct cons_no) from BIGDATA_TS_OR_PRCSCOPE_S98_UNIQUE;
-- ----------------------------------------------------------------------------
-- ONEBIGTABLE 的数量
-- 20006363
select count(cons_id) from BIGDATA_USER_INFO_S98_ONEBIGTABLE;
-- 20006363
select count(distinct cons_id) from BIGDATA_USER_INFO_S98_ONEBIGTABLE;

-- ----------------------------------------------------------------------------
-- 无效用户数据
-- 第一个月是0或null
-- 7645124
select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  (vpm201301 = 0 or vpm201301 is NULL);	
-- 前两个月都是0或null
-- 5468947
select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE  where (vpm201301 = 0 or vpm201301 is NULL) and  (vpm201302 = 0 or vpm201302 is NULL);
-- 前三个月都是0或null
-- 5071220
select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE where (vpm201301 = 0 or vpm201301 is NULL) and  (vpm201302 = 0 or vpm201302 is NULL) and  (vpm201303 = 0 or vpm201303 is NULL) ;
-- ----------------------------------------------------------------------------
-- 执行完 get-userinfo-s98.sql 之后
-- 无效数据
-- 1. 前三个月都是0 + 前三个月都是NULL
-- 5071220
select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F3;

-- 2. 剩下的 前两个月都是0 + 前两个月都是NULL
-- 397727
select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F2_EXCLUDE_F3;

-- 已经被 F3 和 F2_EXCLUDE_F3 识别出来的数据
-- 5468947 = 5071220 + 397727
-- select count(*) from BIGDATA_USER_INFO_S98_DISTINCT_ID_ZERO_AND_NULL_F2F3;

-- 3. 剩下的 仅 第一个月是0/NULL  (也即单月数据)
--2176177
select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F1_EXCLUDE_F2F3;

-- ----------------------------------------------------------------------------
-- 有效数据
-- 12361239
--select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD;


-- 第一月为0 或 NULL
select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE_M1_EXCLUDE_F2F3;
-- 3.2 剩下的 双月数据
-- 第二月为0 或 NULL
select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE_M2_EXCLUDE_F2F3;
