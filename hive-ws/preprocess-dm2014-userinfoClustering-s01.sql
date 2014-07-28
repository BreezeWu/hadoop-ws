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
-- 2. 将 "用户逐月缴费欠费信息" 从纵表变为横表
/*
-- 使用SCALA生成SQL语句
val range2013 = Range(201301, 201313)
val range2014 = Range(201401, 201407)
val rangeYM = range2013.++(range2014)

// 原始表名
val sTABLE = "BIGDATA_RCVBL_FLOW_PM_S01"
// 前面的语句, 注意,这里添加了大括号 ${sTABLE}
val sHead = s"CREATE TABLE ${sTABLE}_V AS \n select x.cons_no"

// 各个月的已收,应收,欠费信息
var sX = "";	// rcved_amt
var sY = "";	// rcvbl_amt
var sZ = "";	// owning_amt
for(ym <- rangeYM) {
    sX = s"$sX,ym$ym.rcved_amt$ym"
    sY = s"$sY,ym$ym.rcvbl_amt$ym"
    sZ = s"$sZ,ym$ym.owning_amt$ym"
}

// from语句
val sFrom = s" from (select distinct(cons_no) from ${sTABLE}) x"

// left outer join 语句
var sJoin = "";
for(ym <- rangeYM) {
    sJoin = s"$sJoin    left outer join (select cons_no, rcved_amt as rcved_amt$ym, rcvbl_amt as rcvbl_amt$ym, owning_amt as owning_amt$ym from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '$ym') ym$ym on ym$ym.cons_no = x.cons_no\n"
}

// 打印所有
// print(sHead,sX,sY,sZ,sJoin, "\n;")  // 这个打印有问题,会多,号
// print(s"$sHead  $sX \t $sY \t $sZ \t $sFrom \t $sJoin ;\n") 这个看得更加明白, 但在hive中执行会有问题,因为TAB会触发命令提示
print(s"\n $sHead $sX  $sY  $sZ $sFrom \n $sJoin ;\n")
*/
CREATE TABLE BIGDATA_RCVBL_FLOW_PM_S01_V AS 
 select x.cons_no ,ym201301.rcved_amt201301,ym201302.rcved_amt201302,ym201303.rcved_amt201303,ym201304.rcved_amt201304,ym201305.rcved_amt201305,ym201306.rcved_amt201306,ym201307.rcved_amt201307,ym201308.rcved_amt201308,ym201309.rcved_amt201309,ym201310.rcved_amt201310,ym201311.rcved_amt201311,ym201312.rcved_amt201312,ym201401.rcved_amt201401,ym201402.rcved_amt201402,ym201403.rcved_amt201403,ym201404.rcved_amt201404,ym201405.rcved_amt201405,ym201406.rcved_amt201406  ,ym201301.rcvbl_amt201301,ym201302.rcvbl_amt201302,ym201303.rcvbl_amt201303,ym201304.rcvbl_amt201304,ym201305.rcvbl_amt201305,ym201306.rcvbl_amt201306,ym201307.rcvbl_amt201307,ym201308.rcvbl_amt201308,ym201309.rcvbl_amt201309,ym201310.rcvbl_amt201310,ym201311.rcvbl_amt201311,ym201312.rcvbl_amt201312,ym201401.rcvbl_amt201401,ym201402.rcvbl_amt201402,ym201403.rcvbl_amt201403,ym201404.rcvbl_amt201404,ym201405.rcvbl_amt201405,ym201406.rcvbl_amt201406  ,ym201301.owning_amt201301,ym201302.owning_amt201302,ym201303.owning_amt201303,ym201304.owning_amt201304,ym201305.owning_amt201305,ym201306.owning_amt201306,ym201307.owning_amt201307,ym201308.owning_amt201308,ym201309.owning_amt201309,ym201310.owning_amt201310,ym201311.owning_amt201311,ym201312.owning_amt201312,ym201401.owning_amt201401,ym201402.owning_amt201402,ym201403.owning_amt201403,ym201404.owning_amt201404,ym201405.owning_amt201405,ym201406.owning_amt201406  from (select distinct(cons_no) from BIGDATA_RCVBL_FLOW_PM_S01) x 
     left outer join (select cons_no, rcved_amt as rcved_amt201301, rcvbl_amt as rcvbl_amt201301, owning_amt as owning_amt201301 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201301') ym201301 on ym201301.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201302, rcvbl_amt as rcvbl_amt201302, owning_amt as owning_amt201302 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201302') ym201302 on ym201302.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201303, rcvbl_amt as rcvbl_amt201303, owning_amt as owning_amt201303 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201303') ym201303 on ym201303.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201304, rcvbl_amt as rcvbl_amt201304, owning_amt as owning_amt201304 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201304') ym201304 on ym201304.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201305, rcvbl_amt as rcvbl_amt201305, owning_amt as owning_amt201305 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201305') ym201305 on ym201305.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201306, rcvbl_amt as rcvbl_amt201306, owning_amt as owning_amt201306 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201306') ym201306 on ym201306.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201307, rcvbl_amt as rcvbl_amt201307, owning_amt as owning_amt201307 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201307') ym201307 on ym201307.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201308, rcvbl_amt as rcvbl_amt201308, owning_amt as owning_amt201308 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201308') ym201308 on ym201308.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201309, rcvbl_amt as rcvbl_amt201309, owning_amt as owning_amt201309 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201309') ym201309 on ym201309.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201310, rcvbl_amt as rcvbl_amt201310, owning_amt as owning_amt201310 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201310') ym201310 on ym201310.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201311, rcvbl_amt as rcvbl_amt201311, owning_amt as owning_amt201311 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201311') ym201311 on ym201311.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201312, rcvbl_amt as rcvbl_amt201312, owning_amt as owning_amt201312 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201312') ym201312 on ym201312.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201401, rcvbl_amt as rcvbl_amt201401, owning_amt as owning_amt201401 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201401') ym201401 on ym201401.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201402, rcvbl_amt as rcvbl_amt201402, owning_amt as owning_amt201402 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201402') ym201402 on ym201402.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201403, rcvbl_amt as rcvbl_amt201403, owning_amt as owning_amt201403 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201403') ym201403 on ym201403.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201404, rcvbl_amt as rcvbl_amt201404, owning_amt as owning_amt201404 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201404') ym201404 on ym201404.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201405, rcvbl_amt as rcvbl_amt201405, owning_amt as owning_amt201405 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201405') ym201405 on ym201405.cons_no = x.cons_no
    left outer join (select cons_no, rcved_amt as rcved_amt201406, rcvbl_amt as rcvbl_amt201406, owning_amt as owning_amt201406 from BIGDATA_RCVBL_FLOW_PM_S01 where ym = '201406') ym201406 on ym201406.cons_no = x.cons_no
;
-- ----------------------------------------------------------------------------
-- 下面数据在各个 get-userinfoClustering-....sql 脚本中执行
-- ----------------------------------------------------------------------------
-- 2. 将多个表合并为一个表, 这里只获取数值型数据(即加/减/乘/除具有含义的列)
-- 创建唯一cons_id和cons_no

-- 下面语句执行后,会有一行(NULL,NULL), 但在mysql中没有这个值!
-- hive, 20289	mysql, 20288
/*
CREATE TABLE BIGDATA_USER_INFO_S01_IDNO_MAP AS
select x.cons_id, a.cons_no from (select distinct cons_id from BIGDATA_USER_INFO_S01) x
    left outer join BIGDATA_USER_INFO_S01 a on a.cons_id= x.cons_id
;
*/

/*
-- 找出那个没有对应的cons_id或cons_no
select count(distinct cons_id) from BIGDATA_USER_INFO_S01;	// 20288
select count(distinct cons_no) from BIGDATA_USER_INFO_S01;	// 20187
*/

-- ----------------------------------------------------------------------------
-- 创建一个大横表
-- ## 用户信息表 BIGDATA_USER_INFO_S01  一个列也不能取?	x
-- ## 月用电量(横表) BIGDATA_ARC_VOLUME_PERM_S01_V	a
-- ## 用户逐月缴费欠费信息(横表) BIGDATA_RCVBL_FLOW_PM_S01_V	b
-- ## 违约用电次数 BIGDATA_POWER_STEAL_PERY_S01	c
-- ## 是否阶梯电价 BIGDATA_TS_OR_PRCSCOPE_S01	d
--	用户信息表 BIGDATA_USER_INFO_S01			没有可计算数值数据,未从该表取数 x.*,
--	是否阶梯电价 BIGDATA_TS_OR_PRCSCOPE_S01	d	没有可计算数值数据,未从该表取数 d.*,
/*
CREATE TABLE BIGDATA_USER_INFO_S01_V_FOR_CLUSTERING AS
select a.*,b.*,c.inspect_count from BIGDATA_USER_INFO_S01_IDNO_MAP x 
    left outer join BIGDATA_ARC_VOLUME_PERM_S01_V a on a.cons_id = x.cons_id
    left outer join BIGDATA_RCVBL_FLOW_PM_S01_V b on trim(b.cons_no) = trim(x.cons_no)
    left outer join (select * from BIGDATA_POWER_STEAL_PERY_S01 where y = '2013') c on c.cons_id = x.cons_id
;


-- 查看表结构
desc BIGDATA_USER_INFO_S01_V_FOR_CLUSTERING;
*/
