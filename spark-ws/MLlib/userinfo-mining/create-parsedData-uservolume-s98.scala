// ----------------------------------------------------------------------------
// 从hive获取数据 S01
//	用户电价电量信息 	BIGDATA_USER_VOLUME_S01_ONEBIGTABLE

// ----------------------------------------------------------------------------
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
// ----------------------------------------------------------------------------

