-- ----------------------------------------------------------------------------
-- 将 cons_id 转换为 string
ALTER TABLE BIGDATA_USER_INFO_S01 CHANGE COLUMN cons_id cons_id String;
ALTER TABLE BIGDATA_ARC_VOLUME_PERM_S01 CHANGE COLUMN cons_id cons_id String;
-- BIGDATA_RCVBL_FLOW_PM_S01 无 cons_id 列
ALTER TABLE BIGDATA_POWER_STEAL_PERY_S01 CHANGE COLUMN cons_id cons_id String;
ALTER TABLE BIGDATA_CHANGE_PAYTYPE_PY_S01 CHANGE COLUMN cons_id cons_id String;
-- BIGDATA_TS_OR_PRCSCOPE_S01  无 cons_id 列
-- BIGDATA_VOLUME_OF_TS_S01  无 cons_id 列
-- BIGDATA_VOLUME_OF_PRC_S01   无 cons_id 列

-- 检查结果
select * from BIGDATA_USER_INFO_S01 limit 10;
select * from BIGDATA_ARC_VOLUME_PERM_S01 limit 10;
select * from BIGDATA_POWER_STEAL_PERY_S01 limit 10;
select * from BIGDATA_CHANGE_PAYTYPE_PY_S01 limit 10;

-- 修改 BIGDATA_USER_INFO_S01 的 volt_code_1 to volt_code
--ALTER TABLE BIGDATA_USER_INFO_S01 CHANGE COLUMN volt_code_1 volt_code String AFTER elec_type_code;