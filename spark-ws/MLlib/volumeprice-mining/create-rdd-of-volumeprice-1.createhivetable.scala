// ----------------------------------------------------------------------------
/**
 * hive 表: 第一步, 创建 阶梯电价电量表 和 分时电价电量表
 *
 * 样本数据:    bigdata_volumeprice_s01_of_ladder bigdata_volumeprice_s01_of_ts
 * 全量数据:    bigdata_volumeprice_s98_of_ladder bigdata_volumeprice_s98_of_ts
 * 
 * 数据集标识, 变量datasetID: s01 s98
 * 阶梯电量电价表, 变量table_volume_of_ladder
 * 分时电量电价表, 变量table_volume_of_ts
 *
//引入依赖
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/hiveselect-of-volumeprice.scala
// 选择数据集
val datasetID = "s01"
//引入并执行
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/create-rdd-of-volumeprice-1.createhivetable.scala
 */

//引入依赖
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/hiveselect-of-volumeprice.scala
 
//val datasetID = "s01"
// 阶梯电量电价表
val table_volumeprice_of_ladder = s"bigdata_volume_of_ladder_${datasetID}"
// 分时电量电价表
val table_volumeprice_of_ts = s"bigdata_volume_of_ts_${datasetID}"
// 阶梯/分时标志基础表, 需要二次处理
// val talbe_flag_of_ladder_ts = s"bigdata_ts_or_prcscope_${datasetID}" // cons_no 不唯一
val table_flag_of_ladder_ts = s"bigdata_ts_or_prcscope_${datasetID}_unique"    // 剔除实行多个电价码的用户,所以 cons_no唯一

// ----------------------------------------------------------------------------
/**
 * hive的select SQL语句
 */
// 引入依赖
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/hiveselect-of-volumeprice.scala

val table_b = table_flag_of_ladder_ts

val table_a_ladder = table_volumeprice_of_ladder
val hivesql_of_volumeprice_of_ladder = getHiveSelectSQL_of_volumeprice_of_ladder(table_a_ladder, table_b)

val table_a_ts = table_volumeprice_of_ts
val hivesql_of_volumeprice_of_ts = getHiveSelectSQL_of_volumeprice_of_ts(table_a_ts, table_b)

// ----------------------------------------------------------------------------
/**
 * 构造 rddFromHive
 *
 */

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)

val rddFromHive_volumeprice_of_ladder  = hiveContext.hql(hivesql_of_volumeprice_of_ladder)
val rddFromHive_volumeprice_of_ts  = hiveContext.hql(hivesql_of_volumeprice_of_ts)

// ----------------------------------------------------------------------------
// 创建新表
val createtable_of_volumeprice_of_ladder = s"CREATE TABLE BIGDATA_VOLUMEPRICE_${datasetID}_of_ladder AS ${hivesql_of_volumeprice_of_ladder}"
val result_createtable_of_ladder = hiveContext.hql(createtable_of_volumeprice_of_ladder)

val createtable_of_volumeprice_of_ts = s"CREATE TABLE BIGDATA_VOLUMEPRICE_${datasetID}_of_ts AS ${hivesql_of_volumeprice_of_ts}"
val result_createtable_of_ts = hiveContext.hql(createtable_of_volumeprice_of_ts)

