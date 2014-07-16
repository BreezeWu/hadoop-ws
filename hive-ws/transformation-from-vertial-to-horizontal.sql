-- ----------------------------------------------------------------------------
-- 纵表变横表 hive, mysql, oracle

------------------------
-- 下面语句, mysql中ok,hive中failed
select cons_id, volume_per_month as '201301' from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201301';

-- 下面语句, mysql和hive中都failed
select cons_id, volume_per_month as 201301 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201301';

-- 下面语句, mysql和hive中都ok
select cons_id, volume_per_month from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201301';

-- 但下面语句, mysql中ok,hive中也ok   [可见,hive中列名不能只含有数字]
select cons_id, volume_per_month as vpm201301 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201301';

-- 于是,下面语句在mysql和hive中都ok
    select x.cons_id,ym1301.vpm201301 from (select distinct(cons_id) from BIGDATA_ARC_VOLUME_PERM_S01) x
    left outer join (select cons_id, volume_per_month as vpm201301 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201301') ym1301 on ym1301.cons_id = x.cons_id;

/*
  基于查询创建表的样例  <programming hive.pdf>
CREATE TABLE ca_employees
AS SELECT name, salary, address
FROM employees
WHERE se.state = 'CA';

   对于下面纵表变横表的复杂查询,直接在前面添加一行" CREATE TABLE BIGDATA_ARC_VOLUME_PERM_S01_V AS "即可,若是对后面的添加括号却恰恰失败!!!
*/
-- 于是, 将 "月用电量" 从纵表变为横表的语句为
-- 注意 hive中不支持 \作为换行连接符号;并且由于hive交互窗口中有命令感应,于是也必须将前面的select和第一个from写成一行
/*
 下面写法在mysql中ok,但在hive中failed
 select x.cons_id,ym1301.vpm201301,ym1302.vpm201302,ym1303.vpm201303,ym1304.vpm201304,ym1305.vpm201305,ym1306.vpm201306,
	ym1307.vpm201307,ym1308.vpm201308,ym1309.vpm201309,ym1310.vpm201310,ym1311.vpm201311,ym1312.vpm201312,
	ym1401.vpm201401,ym1402.vpm201402,ym1403.vpm201403,ym1404.vpm201404,ym1405.vpm201405,ym1406.vpm201406 from (select distinct(cons_id) from BIGDATA_ARC_VOLUME_PERM_S01) x
*/
-- CREATE TABLE BIGDATA_ARC_VOLUME_PERM_S01_V AS 
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
    left outer join (select cons_id, volume_per_month as vpm201406 from BIGDATA_ARC_VOLUME_PERM_S01 where ym = '201406') ym1406 on ym1406.cons_id = x.cons_id;
--------------------------

-- ----------------------------------------------------------------------------
-- 下面语句在mysql中失败,   这个是oracle的语法? 没验证,不知道!
select cons_id，
case ym when ‘201301‘ then volume_per_month else 0 end as vpm201301，
case ym when ‘201302‘ then volume_per_month else 0 end as vpm201302，
case ym when ‘201303‘ then volume_per_month else 0 end as vpm201303，
case ym when ‘201304‘ then volume_per_month else 0 end as vpm201304，
case ym when ‘201305‘ then volume_per_month else 0 end as vpm201305，
case ym when ‘201306‘ then volume_per_month else 0 end as vpm201306，
case ym when ‘201307‘ then volume_per_month else 0 end as vpm201307，
case ym when ‘201308‘ then volume_per_month else 0 end as vpm201308，
case ym when ‘201309‘ then volume_per_month else 0 end as vpm201309，
case ym when ‘201310‘ then volume_per_month else 0 end as vpm201310，
case ym when ‘201311‘ then volume_per_month else 0 end as vpm201311，
case ym when ‘201312‘ then volume_per_month else 0 end as vpm201312，
case ym when ‘201401‘ then volume_per_month else 0 end as vpm201401，
case ym when ‘201402‘ then volume_per_month else 0 end as vpm201402，
case ym when ‘201403‘ then volume_per_month else 0 end as vpm201403，
case ym when ‘201404‘ then volume_per_month else 0 end as vpm201404，
case ym when ‘201405‘ then volume_per_month else 0 end as vpm201405，
case ym when ‘201406‘ then volume_per_month else 0 end as vpm201406
from BIGDATA_ARC_VOLUME_PERM_S01
group by cons_id;
