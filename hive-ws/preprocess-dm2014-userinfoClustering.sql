-- ----------------------------------------------------------------------------
-- 1. 原始数据来自 ~/workspace_github/hadoop-ws/ws-setup/mysql-relative 目录下create-tables-of-dm2014.sql, data.2014-06-16.utf-8.tar.gz.NULLfile,import-csv-into-dm2014.sql,mysql-user-db.sql等脚本
-- 2. 该数据预处理为spark脚本 demo-spark-03-mllib-02-kmeans.scala 使用
-- 3. 脚本执行有两种思路,(1)直接在hive中执行; (2) 在spark-shell中利用 spark SQL执行

-- ## 用户信息表 BIGDATA_USER_INFO_S01
    select 
        cons_id,cons_name,prc_code, org_no,
        elec_type_code,volt_code,contract_cap,
        lode_attr_code,status_code,urban_rural_flag,
        trade_code,cust_type_code,cons_no
    from BIGDATA_USER_INFO_S01




