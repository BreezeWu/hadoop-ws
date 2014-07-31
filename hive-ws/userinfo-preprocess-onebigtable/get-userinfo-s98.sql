-- ----------------------------------------------------------------------------
-- 在preprocess-userinfo-S98.sql 的基础上, 得到不同的数据集

-- 单月非销户数据
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_M1 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE where (org_no like "21401%" or org_no like "21408%")
;

-- 双月非销户数据
CREATE TABLE BIGDATA_USER_INFO_S98_ONEBIGTABLE_M2 AS
select * from BIGDATA_USER_INFO_S98_ONEBIGTABLE where (org_no !like "21401%" and org_no !like "21408%")
;

