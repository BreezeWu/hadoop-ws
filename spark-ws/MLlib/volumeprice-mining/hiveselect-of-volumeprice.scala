// ----------------------------------------------------------------------------
/**
 * hive的select SQL语句
 * 
 * 引入
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volume-price-mining/hiveselect-of-volumeprice.scala
 */

// ****************************************************************************
//  一, where语句
// ****************************************************************************
// ----------------------------------------------------------------------------
// 	1.第一类, prc_code 是下面内容的是 居民阶梯电价
val ladderPriceCodeList = List(
(20201069,"城镇居民生活(1kV以下,居民电采暖12-04，无谷)"),
(20201070,"居民生活(1kV以下,居民电采暖，不征收附加无谷)"),
(20101001,"乡村居民生活(1kV以下,征收附加)"),
(20101005,"乡村居民生活(1kV以下,附加费进目录)"),
(20102001,"乡村居民生活(1-10kV,附加费进目录)"),
(20202001,"城镇居民生活(1-10kV,不征收附加)"),
(20201004,"城镇居民生活(1kV以下,居民电采暖12-04)"),
(20201009,"城镇居民电采暖(1kV以下,附加费进目录)"),
(20201010,"城镇居民生活(1kV以下,附加费进目录)"),
(20201014,"城镇居民生活(1kV以下,不征收附加)"),
(20201021,"城镇居民生活(1kV以下,征收附加)"),
(20202016,"城镇居民生活(1-10kV,附加费进目录)"),
(20202046,"城镇居民生活(1-10kV,征收附加)"),
(20203003,"城镇居民生活(35-66kV,征收附加)"),
(20203004,"城镇居民生活(35-66kV,不征收附加)"),
(20208001,"城镇居民生活(20kV,征收附加)"),
(20102006,"乡村居民生活(1-10kV,不征收附加)")
)

// 构造是阶梯电价 where 语句
val whereOfLadder_Prefix = "(prc_code = '"
val whereOfLadder_Sep = "' or prc_code = '"
val whereOfLadder_Middle = ladderPriceCodeList.map(x => x._2).reduce((x,y) => (x+ whereOfLadder_Sep + y))
val whereOfLadder_Postfix = "')"

// ----------------------------------------------------------------------------
// 构造是 阶梯电价 的where 语句
val whereOfLadder = whereOfLadder_Prefix + whereOfLadder_Middle + whereOfLadder_Postfix

// ----------------------------------------------------------------------------
// 2.第一类之外， 
//	(2.1) ts_flag = '是' 属于分时电价用户
//	(2.2) ts_flag != '是' 则既不是分时电价，也不是阶梯电价用户

// 构造不是阶梯电价 的where语句
val whereOfNotLadder_Prefix = "(prc_code != '"
val whereOfNotLadder_Sep = "' and prc_code != '"
val whereOfNotLadder_Middle = ladderPriceCodeList.map(x => x._2).reduce((x,y) => (x+ whereOfNotLadder_Sep + y))
val whereOfNotLadder_Postfix = "')"
val whereOfNotLadder = whereOfNotLadder_Prefix + whereOfNotLadder_Middle + whereOfNotLadder_Postfix

// ----------------------------------------------------------------------------
// 构造是 分时电价 的where语句
val whereOfTs = s"${whereOfNotLadder} and ts_flag = '是'"
// ----------------------------------------------------------------------------
// 构造是 既不是阶梯电价也不是分时电价 的where语句
val whereOfNotLadderNotTs = s"${whereOfNotLadder} and ts_flag != '是'"

// ****************************************************************************
//  二, 封装的函数
// ****************************************************************************
// 变成 另外一个表的 语句
def GetSelectOfAlias(sAlias:String, sOrigin:String, sSep:String = ","):String = {
	val sArray = sOrigin.split(sSep)
	val result = sArray.map(x => sAlias +"."+ x).reduce((x,y) => x + sSep + y)
	return result
}

// 构造 joinSelectSQL
def buildSelectSQL_LeftSemiJoin(table_a:String, table_a_select:String, table_b:String, table_b_where:String, on:String):String = {
    // "select ${table_a_select} from ${table_a} a left semi join (select * from ${table_b} where ${table_b_where}) b on a.cons_no = b.cons_no"
	val joinSelectSql = s"select ${table_a_select} from ${table_a} a left semi join (select * from ${table_b} where ${table_b_where}) b ${on}"

	return joinSelectSql
}

def getHiveSelectSQL_of_volumeprice_of_ladder(table_a:String, table_b:String):String = {
    // 阶梯电价电量信息
    // 注意: 分割符号为",",中间没有任何别的字符
    val columns_of_ladder = "cons_no,ym,u_pq_1,u_pq_1_prc,u_pq_2,u_pq_2_prc,u_pq_3,u_pq_3_prc"
    val columns_a_of_ladder = GetSelectOfAlias("a", columns_of_ladder)  // join时表a格式

    val table_a_select = columns_a_of_ladder
    val table_b_where = whereOfLadder
    val on = "on a.cons_no = b.cons_no"
    return buildSelectSQL_LeftSemiJoin(table_a, table_a_select, table_b, table_b_where, on)
}
def getHiveSelectSQL_of_volumeprice_of_ts(table_a:String, table_b:String):String = {
    // 分时电价电量信息
    // 注意: 分割符号为",",中间没有任何别的字符
    val columns_of_ts = "cons_no,ym,kwh_volume_top,kwh_prc_top,kwh_volume_high,kwh_prc_high,kwh_volume_mean,kwh_prc_mean,kwh_volume_low,kwh_prc_low,kwh_volume_bottom,kwh_prc_bottom"
    val columns_a_of_ts = GetSelectOfAlias("a", columns_of_ts)  // join时表a格式

    val table_a_select = columns_a_of_ts
    val table_b_where = whereOfTs
    val on = "on a.cons_no = b.cons_no"
    return buildSelectSQL_LeftSemiJoin(table_a, table_a_select, table_b, table_b_where, on)
}
/* 
def getHiveSelectSQL_of_volumeprice_of_NotLadderNotTs(table_a:String, table_b:String):String = {
    val table_a_select = columns_a_of_???
    val table_b_where = whereOfNotLadderNotTs
    val on = "on a.cons_no = b.cons_no"
    return buildSelectSQL_LeftSemiJoin(table_a, table_a_select, table_b, table_b_where, on)
}*/





