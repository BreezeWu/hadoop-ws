// ----------------------------------------------------------------------------
// 将hive中的数据转换为逗号分隔
// 引入包
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

// 数据源
val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
val rddFromHive = hiveContext.hql("SELECT cons_id,contract_cap FROM bigdata_user_info_s01")

// 查看第一条数据
 rddFromHive.first	// res1: org.apache.spark.sql.Row = [2.5374088E7,2.0]
 val row1 = rddFromHive.first
 row1(0)
 row1(1)
// -----------------------------------------------------------
	// 结果输出路径  如： /user/spark/tmpWorkDir/2014-07-21-10-53-57
	val sparkRoot = "/user/spark/"
	val sparkTmpWD = sparkRoot + "tmpWorkDir/"
	
	// 加上时间戳
	import java.util.Date
	val format = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")	// "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
	val dateString = format.format(new java.util.Date())
	
	val tmpOutPutFilePath = sparkTmpWD +dateString
// -----------------------------------------------------------
// 数据转换与保存
// 对于res1: org.apache.spark.sql.Row，写出来的结果是 [2.5502125E7,2.0] 格式
//rddFromHive.saveAsTextFile(tmpOutPutFilePath)

// 需要将第一列文本化  cons_id是用户标识，是文本，不是数值  
// ????? 不知道怎么转换啊！
//val convertedData = rddFromHive.map(x => Tuple2(x(0), x(1)) )
//convertedData.saveAsTextFile(tmpOutPutFilePath)
//tmpFilePath

// Tuple2导出的数据，前后有小括号
val convertedData = rddFromHive.map(x => x(0) + "," + x(1) )
convertedData.first
convertedData.saveAsTextFile(tmpOutPutFilePath)

// 输出文件的路径
tmpFilePath



