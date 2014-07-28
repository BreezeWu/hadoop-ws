===================
数据文件命名约定

1. 抽样与全量
s01	抽样数据
s98	全量数据

2. 单月与双月
m1	单月	
m2	双月	BIGDATA_USER_INFO_S01_2013M2_V_FOR_CLUSTERING

===================
具体数据处理脚本
(1) preprocess-dm2014-userinfoClustering-s01.sql		数据初始处理

(2) get-userinfoClustering-s01-all.sql			获取所有用户数据 (2013年1月~2014年6月, 非销户)
(3) get-userinfoClustering-s01-m1.sql			获取单月用户数据 (2013年1月~2013年12月, 非销户)
(4) get-userinfoClustering-s01-m2.sql			获取双月用户数据(沈阳21401,大连21408)  (2013年1月~2013年12月, 非销户)

===================
执行步骤(在hive交互环境中):

-- 1. 选择是 s01 还是 s98
-- 下面的脚本以选择 s01为例.

-- 2. 数据准备
source /home/hadoop/workspace_github/hadoop-ws/hive-ws/preprocess-dm2014-userinfoClustering-s01.sql;
--结果输出到 BIGDATA_ARC_VOLUME_PERM_S01_V, BIGDATA_RCVBL_FLOW_PM_S01_V 等横表

-- 3. 获得数据
-- 所有数据
--source /home/hadoop/workspace_github/hadoop-ws/hive-ws/get-userinfoClustering-s01-all.sql;
--结果输出到 BIGDATA_USER_INFO_S01_V_FOR_CLUSTERING

-- 单月数据
source /home/hadoop/workspace_github/hadoop-ws/hive-ws/get-userinfoClustering-s01-m1.sql;
--结果输出到 BIGDATA_USER_INFO_S01_2013M1_V_FOR_CLUSTERING

-- 双月数据
source /home/hadoop/workspace_github/hadoop-ws/hive-ws/get-userinfoClustering-s01-m2.sql;
--结果输出到 BIGDATA_USER_INFO_S01_2013M2_V_FOR_CLUSTERING

