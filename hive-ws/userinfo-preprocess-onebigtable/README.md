===================
数据文件命名约定

1. 抽样与全量
s01	抽样数据
s98	全量数据

2. 信息类别
用户信息	 userinfo
用户电价电量信息  uservolume

3. 自定义查询表
如单月与双月, 在 get-userinfo-s01.sh / get-userinfo-s98.sh 脚本中进行筛选

===================
具体数据处理脚本
(1) 用户信息
# BIGDATA_USER_INFO_S01_ONEBIGTABLE 或者 BIGDATA_USER_INFO_S98_ONEBIGTABLE
preprocess-userinfo-s01.sql
preprocess-userinfo-s98.sql

(2) 用户用电量信息

===================
执行步骤(在hive交互环境中):

-- 1. 选择是 s01 还是 s98
-- 下面的脚本以选择 s01为例.

-- 2. 数据准备
source /home/hadoop/workspace_github/hadoop-ws/hive-ws/userinfo-preprocess-onebigtable/preprocess-userinfo-s01.sql;
source /home/hadoop/workspace_github/hadoop-ws/hive-ws/userinfo-preprocess-onebigtable/get-userinfo-s01.sql;

--source /home/hadoop/workspace_github/hadoop-ws/hive-ws/userinfo-preprocess-onebigtable/preprocess-userinfo-s98.sql;
--source /home/hadoop/workspace_github/hadoop-ws/hive-ws/userinfo-preprocess-onebigtable/get-userinfo-s98.sql;



