// ----------------------------------------------------------------------------
/**
 * 从hive 表创建RDD
 *
 */
// ----------------------------------------------------------------------------
// 创建SchemaRDD
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)

val tableName = "bigdata_mpvolume_onerow"	// 表名
val selectSql = s"select * from ${tableName}"
val rddFromHive = hiveContext.hql(selectSql)

val first = rddFromHive.first

// ----------------------------------------------------------------------------
// 变换
// 从 SchemaRDD 变换为 MappedRDD:org.apache.spark.rdd.RDD[MPVolumeItem]
val mappedRddData = rddFromHive.map(r => row2MPVolumeItem(r))

//val count_of_ladder = mappedData_volumeprice_of_ladder.count
//val count_of_ts = mappedData_volumeprice_of_ts.count

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

