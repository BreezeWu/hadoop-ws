/**
 * 从 parquetFile 创建RDD
 *
 * Created by hadoop on 9/28/14.
 */

// ----------------------------------------------------------------------------
// 1. 读取
import org.apache.spark.sql.SQLContext

val sqlContext = new SQLContext(sc)
val parquet = sqlContext.parquetFile("file:///home/hadoop/dm-data/rddFromHive-parquet/")
val rddFromHive = parquet
// ----------------------------------------------------------------------------
// 2. 变换
// 从 SchemaRDD 变换为 MappedRDD:org.apache.spark.rdd.RDD[MPVolumeItem]
val mappedRddData = rddFromHive.map(r => convertTableRow2TableObject(r))
