-- ----------------------------------------------------------------------------
-- 在preprocess-userinfo-S98.sql 的基础上, 得到不同的数据集

-- 数据全集 = 有效数据 + 无效数据
-- 1. 有效数据
-- (1) 单月数据, BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M1： 第一个月既不为0，也不为NULL
-- (2) 双月数据, BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M2： 第一个月为0或NULL， 但第二个月不为 0或NULL. 即 BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F1_EXCLUDE_F2F3

-- 2. 无效数据
-- (1) 前三个月都是 0或NULL， BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F3
-- (2) 仅前两个月都是 0或NULL, BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F2_EXCLUDE_F3
-- ----------------------------------------------------------------------------
-- 无效用户数据
-- 3806417
-- select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  vpm201301 = 0;	
-- 4172315
-- select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  vpm201302 = 0;
-- 2975321
-- select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  vpm201301 = 0 and vpm201302 = 0;
-- 前三个月都是0
-- 2644068
-- select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  vpm201301 = 0 and vpm201302 = 0 and vpm201303 = 0;
-- 数据抽样  前三个月都是0
-- select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  vpm201301 = 0 and vpm201302 = 0 and vpm201303 = 0 limit 10;

-- 4408668
-- select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  vpm201301 is NULL;
-- 4342483
-- select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  vpm201302 is NULL;
-- 2093682
-- select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  vpm201301 is NULL and vpm201302 is NULL;
-- 前三个月都是NULL
-- 2006913
-- select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  vpm201301 is NULL and vpm201302 is NULL and vpm201303 is NULL;
-- 数据抽样  前三个月都是NULL
-- select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE where  vpm201301 is NULL and vpm201302 is NULL and vpm201303 is NULL limit 10;

-- ----------------------------------------------------------------------------
-- 无效数据
-- 1. 前三个月都是0 + 前三个月都是NULL
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F3 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE where (vpm201301 = 0 or vpm201301 is NULL) and  (vpm201302 = 0 or vpm201302 is NULL) and  (vpm201303 = 0 or vpm201303 is NULL) 
;
-- 5071220
-- select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F3;

-- 2. 剩下的 仅 前两个月都是0/NULL
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F2_EXCLUDE_F3 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE where (vpm201301 = 0 or vpm201301 is NULL) and  (vpm201302 = 0 or vpm201302 is NULL) and !(
    (vpm201301 = 0 or vpm201301 is NULL) and  (vpm201302 = 0 or vpm201302 is NULL) and  (vpm201303 = 0 or vpm201303 is NULL)
)
;
-- 397727
-- select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F2_EXCLUDE_F3;

-- 已经被 F3 和 F2_EXCLUDE_F3 识别出来的数据
--CREATE TABLE BIGDATA_USER_INFO_S98_DISTINCT_ID_ZERO_AND_NULL_F2F3 AS
--select cons_id from BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F3 UNION ALL select cons_id from --BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F2_EXCLUDE_F3
--;
-- 5468947 = 5071220 + 397727
--select count(*) from BIGDATA_USER_INFO_S98_DISTINCT_ID_ZERO_AND_NULL_F2F3;

-- 3. 剩下的 仅 第一个月是0/NULL
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F1_EXCLUDE_F2F3 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE where (vpm201301 = 0 or vpm201301 is NULL)  and !(
    (vpm201301 = 0 or vpm201301 is NULL) and  (vpm201302 = 0 or vpm201302 is NULL)
) and !(
    (vpm201301 = 0 or vpm201301 is NULL) and  (vpm201302 = 0 or vpm201302 is NULL) and  (vpm201303 = 0 or vpm201303 is NULL)
)
;
-- 2176177
--select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F1_EXCLUDE_F2F3;

-- ----------------------------------------------------------------------------
-- 有效数据
-- (1) 单月数据： 第一个月既不为0，也不为NULL
-- (2) 双月数据： 第一个月为0或NULL， 但第二个月不为 0或NULL. 即 BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F1_EXCLUDE_F2F3

-- 双月数据
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M2 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F1_EXCLUDE_F2F3
;
-- 2176177

-- 单月数据
-- 
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M1 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE where !(vpm201301 = 0 or vpm201301 is NULL)
;
-- 12361239
--select count(*) from BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M1;
-- ----------------------------------------------------------------------------
-- 大连和沈阳 M1
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_DLSY_M1 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M1 where (org_no like "21401%" or org_no like "21408%")
;
-- 大连和沈阳 M2
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_DLSY_M2 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M2 where (org_no like "21401%" or org_no like "21408%")
;

-- 大连和沈阳之外 M1
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_NOTDLSY_M1 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M1 where (org_no !like "21401%" and org_no !like "21408%")
;
-- 大连和沈阳之外 M2
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_NOTDLSY_M2 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M2 where (org_no !like "21401%" and org_no !like "21408%")
;

-- ----------------------------------------------------------------------------
-- 重新导入时删除表
-- 1. 前三个月都是0 + 前三个月都是NULL	5801833
-- drop table BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F3;
-- 2. 剩下的 前两个月都是0 + 前两个月都是NULL
-- drop table BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F2_EXCLUDE_F3;
-- 已经被 F3 和 F2_EXCLUDE_F3 识别出来的数据
-- drop table BIGDATA_USER_INFO_S98_DISTINCT_ID_ZERO_AND_NULL_F2F3;
-- 剩下的 仅 第一个月为 0或null
-- drop table BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F1_EXCLUDE_F2F3

--单月数据
-- drop table BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M1;
-- 双月数据
-- drop table BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M2;

-- drop table BIGDATA_USER_INFO_S98_ONEBIGTABLE_DLSY_M1;
-- drop table BIGDATA_USER_INFO_S98_ONEBIGTABLE_DLSY_M2;
-- drop table BIGDATA_USER_INFO_S98_ONEBIGTABLE_NOTDLSY_M1;
-- drop table BIGDATA_USER_INFO_S98_ONEBIGTABLE_NOTDLSY_M2;