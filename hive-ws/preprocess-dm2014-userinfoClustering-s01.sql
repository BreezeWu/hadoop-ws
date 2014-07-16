-- ----------------------------------------------------------------------------
-- 1. 原始数据来自 ~/workspace_github/hadoop-ws/ws-setup/mysql-relative 目录下create-tables-of-dm2014.sql, data.2014-06-16.utf-8.tar.gz.NULLfile,import-csv-into-dm2014.sql,mysql-user-db.sql等脚本
-- 2. 该数据预处理为spark脚本 demo-spark-03-mllib-02-kmeans.scala 使用
-- 3. 脚本执行有两种思路,(1)直接在hive中执行; (2) 在spark-shell中利用 spark SQL执行

-- ## 用户信息表 BIGDATA_USER_INFO_S01
-- ## 月用电量 BIGDATA_ARC_VOLUME_PERM_S01
-- ## 用户逐月缴费欠费信息 BIGDATA_RCVBL_FLOW_PM_S01
-- ## 违约用电次数 BIGDATA_POWER_STEAL_PERY_S01
-- ## 是否阶梯电价 BIGDATA_TS_OR_PRCSCOPE_S01

-- ----------------------------------------------------------------------------
-- 1. 将 "月用电量" 从纵表变为横表
-- 将 "月用电量" 从纵表变为横表, 创建了一张新表 BIGDATA_ARC_VOLUME_PERM_S01_V 
-- 注意, hive 中 select 以及第一张表必须在一行
CREATE TABLE BIGDATA_ARC_VOLUME_PERM_S01_V AS
select x.cons_id,ym1301.vpm201301,ym1302.vpm201302,ym1303.vpm201303,ym1304.vpm201304,ym1305.vpm201305,ym1306.vpm201306,ym1307.vpm201307,ym1308.vpm201308,ym1309.vpm201309,ym1310.vpm201310,ym1311.vpm201311,ym1312.vpm201312,ym1401.vpm201401,ym1402.vpm201402,ym1403.vpm201403,ym1404.vpm201404,ym1405.vpm201405,ym1406.vpm201406 from (select distinct(cons_id) from BIGDATA_ARC_VOLUME_PERM_S01) x
    left outer join (select cons_id, volume_per_month as vpm201301 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201301') ym1301 on ym1301.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201302 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201302') ym1302 on ym1302.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201303 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201303') ym1303 on ym1303.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201304 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201304') ym1304 on ym1304.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201305 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201305') ym1305 on ym1305.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201306 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201306') ym1306 on ym1306.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201307 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201307') ym1307 on ym1307.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201308 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201308') ym1308 on ym1308.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201309 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201309') ym1309 on ym1309.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201310 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201310') ym1310 on ym1310.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201311 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201311') ym1311 on ym1311.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201312 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201312') ym1312 on ym1312.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201401 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201401') ym1401 on ym1401.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201402 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201402') ym1402 on ym1402.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201403 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201403') ym1403 on ym1403.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201404 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201404') ym1404 on ym1404.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201405 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201405') ym1405 on ym1405.cons_id = x.cons_id
    left outer join (select cons_id, volume_per_month as vpm201406 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201406') ym1406 on ym1406.cons_id = x.cons_id
;
--------------------------

-- ----------------------------------------------------------------------------
-- 2. 







