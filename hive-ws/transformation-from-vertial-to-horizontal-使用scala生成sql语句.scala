// ----------------------------------------------------------------------------
// 2. 将 "用户逐月缴费欠费信息" 从纵表变为横表
// 使用SCALA生成SQL语句
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

/*

 最后生成的SQL语句为:

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

*/
