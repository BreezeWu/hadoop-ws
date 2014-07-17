// ----------------------------------------------------------------------------
// 使用SCALA生成SQL语句

// -------------------------------- 
// 从聚类数据表BIGDATA_USER_INFO_S01_V_FOR_CLUSTERING中选择列
val range2013 = Range(201301, 201313)
val range2014 = Range(201401, 201407)
val rangeYM = range2013.++(range2014)

// 原始表名
val sTABLE = "BIGDATA_USER_INFO_S01_V_FOR_CLUSTERING"
// 前面的语句	注意,这里添加了大括号 ${sTABLE}
val sHead = s"SELECT "

// 各个月的月用电量, 已收,应收,欠费信息
var sVPM = "";	// rcved_amt
var sX = "";	// rcved_amt
var sY = "";	// rcvbl_amt
var sZ = "";	// owning_amt
for(ym <- rangeYM) {
    sVPM = s"$sVPM,vpm$ym"
    sX = s"$sX,rcved_amt$ym"
    sY = s"$sY,rcvbl_amt$ym"
    sZ = s"$sZ,owning_amt$ym"
}

// from语句
val sFrom = s" FROM ${sTABLE} x"

// 
var sEnd = "";

// 打印所有
// 去掉$sVPM的第一个 ,
val sVPM_cutted = sVPM.substring(1, sVPM.length)
print(s"\n $sHead $sVPM_cutted $sX  $sY  $sZ $sFrom \n $sEnd ;\n") // 注意:是sVPM_cutted,而不是sVPM!

/*

 最后生成的SQL语句为:

SELECT  vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312,vpm201401,vpm201402,vpm201403,vpm201404,vpm201405,vpm201406 ,rcved_amt201301,rcved_amt201302,rcved_amt201303,rcved_amt201304,rcved_amt201305,rcved_amt201306,rcved_amt201307,rcved_amt201308,rcved_amt201309,rcved_amt201310,rcved_amt201311,rcved_amt201312,rcved_amt201401,rcved_amt201402,rcved_amt201403,rcved_amt201404,rcved_amt201405,rcved_amt201406  ,rcvbl_amt201301,rcvbl_amt201302,rcvbl_amt201303,rcvbl_amt201304,rcvbl_amt201305,rcvbl_amt201306,rcvbl_amt201307,rcvbl_amt201308,rcvbl_amt201309,rcvbl_amt201310,rcvbl_amt201311,rcvbl_amt201312,rcvbl_amt201401,rcvbl_amt201402,rcvbl_amt201403,rcvbl_amt201404,rcvbl_amt201405,rcvbl_amt201406  ,owning_amt201301,owning_amt201302,owning_amt201303,owning_amt201304,owning_amt201305,owning_amt201306,owning_amt201307,owning_amt201308,owning_amt201309,owning_amt201310,owning_amt201311,owning_amt201312,owning_amt201401,owning_amt201402,owning_amt201403,owning_amt201404,owning_amt201405,owning_amt201406  FROM BIGDATA_USER_INFO_S01_V_FOR_CLUSTERING x

*/
