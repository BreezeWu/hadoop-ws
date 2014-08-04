-- ----------------------------------------------------------------------------
-- 在preprocess-userinfo-s01.sql 的基础上, 得到不同的数据集

-- 双月非销户数据  (大连，沈阳是双月)
CREATE TABLE BIGDATA_USER_INFO_S01_ONEBIGTABLE_M1 AS
select * from BIGDATA_USER_INFO_S01_ONEBIGTABLE where (org_no like "21401%" or org_no like "21408%")
;

-- 单月非销户数据
CREATE TABLE BIGDATA_USER_INFO_S01_ONEBIGTABLE_M2 AS
select * from BIGDATA_USER_INFO_S01_ONEBIGTABLE where (org_no !like "21401%" and org_no !like "21408%")
;

