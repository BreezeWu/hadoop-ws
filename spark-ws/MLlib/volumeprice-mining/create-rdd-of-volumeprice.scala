// ----------------------------------------------------------------------------
/**
 * hive 表映射
 * 
 * 数据集标识, datasetID: s01 s98
 * 阶梯电量电价表, table_volume_of_ladder
 * 分时电量电价表, table_volume_of_ts
 *
 * 引入依赖
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/hiveselect-of-volumeprice.scala
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/transformations-of-volumeprice.scala
 * 引入
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/create-rdd-of-volumeprice.scala
 */
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/hiveselect-of-volumeprice.scala
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/transformations-of-volumeprice.scala

val datasetID = "s01"
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
/*
// 创建新表
val createtable_of_volumeprice_of_ladder = s"CREATE TABLE BIGDATA_VOLUMEPRICE_${datasetID}_of_ladder AS ${hivesql_of_volumeprice_of_ladder}"
val result_createtable_of_ladder = hiveContext.hql(createtable_of_volumeprice_of_ladder)

val createtable_of_volumeprice_of_ts = s"CREATE TABLE BIGDATA_VOLUMEPRICE_${datasetID}_of_ts AS ${hivesql_of_volumeprice_of_ts}"
val result_createtable_of_ts = hiveContext.hql(createtable_of_volumeprice_of_ts)

// 从创建的新表那里获得数据
val sql_of_volumeprice_of_ladder = s"select * from BIGDATA_VOLUMEPRICE_${datasetID}_of_ladder"
val rddFromHive_volumeprice_of_ladder = hiveContext.hql(sql_of_volumeprice_of_ladder)

val sql_of_volumeprice_of_ts = s"select * from BIGDATA_VOLUMEPRICE_${datasetID}_of_ts"
val rddFromHive_volumeprice_of_ts = hiveContext.hql(sql_of_volumeprice_of_ts)
*/
// ----------------------------------------------------------------------------
// 从 SchemaRDD(rddFromHive) 变换为 MappedRDD:org.apache.spark.rdd.RDD[VolumePriceItemLadder 或者 VolumePriceItemTs]
val mappedData_volumeprice_of_ladder = rddFromHive_volumeprice_of_ladder.map(r => row2VolumePriceItemLadder(r))
val mappedData_volumeprice_of_ts = rddFromHive_volumeprice_of_ts.map(r => row2VolumePriceItemTs(r))

/*
// ----------------------------------------------------------------------------
// 从 SchemaRDD(rddFromHive) 变换为 MappedRDD:org.apache.spark.rdd.RDD[Item_of_*]
val mappedData_volumeprice_of_ladder = rddFromHive_volumeprice_of_ladder.map(r => row2Item_of_ladder(r))
val mappedData_volumeprice_of_ts = rddFromHive_volumeprice_of_ts.map(r => row2Item_of_ts(r))
// ----------------------------------------------------------------------------
// 从 MappedRDD:RDD[Item_of_*] 变换为 MappedRDD:RDD[IndexData]
val indexData_volumeprice_of_ladder = mappedData_volumeprice_of_ladder.map(x => x.convert2IndexData())
val indexData_volumeprice_of_ts = mappedData_volumeprice_of_ts.map(x => x.convert2IndexData())
// ----------------------------------------------------------------------------
// 从 MappedRDD:RDD[Array[Double]] 变换为 MappedRDD:RDD[org.apache.spark.mllib.linalg.Vector]
// 选取 data, 转变为Vector, 构建matrices
val parsedData_volumeprice_of_ladder = indexData_volumeprice_of_ladder.map(x => Vectors.dense(x.data)).cache
val parsedData_volumeprice_of_ts = indexData_volumeprice_of_ts.map(x => Vectors.dense(x.data)).cache
*/

