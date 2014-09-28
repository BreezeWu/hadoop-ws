/**
 * 从 hive 中读取数据, 并导出到本地parquet备份文件
 */

// ----------------------------------------------------------------------------
// 1.读取
val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)

val tableName = "bigdata_mpvolume_onerow"	// 表名
val selectSql = s"select * from ${tableName}"
val rddFromHive = hiveContext.hql(selectSql)

// val first = rddFromHive.first

// ----------------------------------------------------------------------------
// 2.变换
// 从 SchemaRDD 变换为 MappedRDD:org.apache.spark.rdd.RDD[MPVolumeItem]
:load convertTableRow2TableObject.scala
val mappedRddData = rddFromHive.map(r => convertTableRow2TableObject(r))

// ----------------------------------------------------------------------------
// 3. 转存
// parquet File
rddFromHive.saveAsParquetFile("rddFromHive-parquet")
// Obj File
mappedRddData.saveAsParquetFile("mappedRddData-MPVolumeItem-obj")

