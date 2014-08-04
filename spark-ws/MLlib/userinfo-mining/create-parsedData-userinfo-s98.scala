// ----------------------------------------------------------------------------
// 从hive获取数据 S98
//	用户信息和售电信息	BIGDATA_USER_INFO_S98_ONEBIGTABLE

/*
-- 数据全集 = 有效数据 + 无效数据
-- 1. 有效数据
-- (1) 单月数据, BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M1 --> rddFromHiveGoodM1
-- (2) 双月数据, BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M2 --> rddFromHiveGoodM2

-- 2. 无效数据
-- (1) 前三个月都是 0或NULL， BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F3 --> rddFromHiveBadF3
-- (2) 仅前两个月都是 0或NULL, BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F2_EXCLUDE_F3  --> rddFromHiveBadF2ExcludeF3
*/
// ----------------------------------------------------------------------------
// ID
val idInfo = "cons_id, cons_no"

// 月用电量(vpm)
val vpmStrOf2013 = "vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312"
// 实收(rcved_amt)
val rcved_amtStrOf2013 = "rcved_amt201301,rcved_amt201302,rcved_amt201303,rcved_amt201304,rcved_amt201305,rcved_amt201306,rcved_amt201307,rcved_amt201308,rcved_amt201309,rcved_amt201310,rcved_amt201311,rcved_amt201312"
// 应收(rcvbl_amt)
val rcvbl_amtStrOf2013 = "rcvbl_amt201301,rcvbl_amt201302,rcvbl_amt201303,rcvbl_amt201304,rcvbl_amt201305,rcvbl_amt201306,rcvbl_amt201307,rcvbl_amt201308,rcvbl_amt201309,rcvbl_amt201310,rcvbl_amt201311,rcvbl_amt201312"
// 欠费(owning_amt)
val owning_amtStrOf2013 = "owning_amt201301,owning_amt201302,owning_amt201303,owning_amt201304,owning_amt201305,owning_amt201306,owning_amt201307,owning_amt201308,owning_amt201309,owning_amt201310,owning_amt201311,owning_amt201312"
// 查询语句
val whereStr = ""

val vpmStr = vpmStrOf2013

// ----------------------------------------------------------------------------
// 不包括索引 
// 只获得 ${vpmStrOf2013}
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)

// 1. 有效数据
// 单月
val rddFromHiveGoodM1  = hiveContext.hql(s"SELECT ${idInfo}, ${vpmStrOf2013} FROM BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M1")
// 双月
val rddFromHiveGoodM2  = hiveContext.hql(s"SELECT ${idInfo}, ${vpmStrOf2013} FROM BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M2")

// 2. 无效数据
// 前3各月都是 0/NULL
val rddFromHiveBadF3  = hiveContext.hql(s"SELECT ${idInfo}, ${vpmStrOf2013} FROM BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F3")
// 仅前两个月都是 0或NULL
val rddFromHiveBadF2ExcludeF3  = hiveContext.hql(s"SELECT ${idInfo}, ${vpmStrOf2013} FROM BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F2_EXCLUDE_F3")

// ----------------------------------------------------------------------------
// 数据变换函数
// ----------------------------------------------------------------------------
// 定义函数:对一个org.apache.spark.sql.Row中每一个列的处理函数
// 函数原型为: g: (y: Any)Double
def g(y:Any):Double = y match {
	case null => 0			// 将null转换为0
	case i:Int => i.toDouble	// 将Int转换为Double 
	case d:Double => d
}

// 定义函数: 对每一个org.apache.spark.sql.Row对象的处理函数
// 原型为: f: (x: org.apache.spark.sql.Row)Seq[Double]
def f(x:org.apache.spark.sql.Row) = {
	x.map(y => g(y))
}

// ------------------------------------
// 数据变换函数运用举例
// 双月数据 m1
// 转换为vector,最后变为matrices
//val parsedDataM1 = rddFromHiveM1.map(x => f(x)).	// 去掉null,转换为Double
//	map(x => Vectors.dense(x.toArray))		// 转变为Vector, 构建matrices

// 测试 g(y)
// 对一个org.apache.spark.sql.Row中每一个列的处理
// rddFromHive.first.map(y => g(y))

// 应用于 rddFromHive
/*
val rddFromHiveM1 = rddFromHiveGoodM1
val parsedDataM1 = rddFromHiveM1.map(x => f(x)).	// 去掉null,转换为Double
	map(x => Vectors.dense(x.toArray))		// 转变为Vector, 构建matrices
*/
// ----------------------------------------------------------------------------
// 数据变换
// ----------------------------------------------------------------------------
// Define the schema using a case class.
// Note: Case classes in Scala 2.10 can support only up to 22 fields. To work around this limit, 
// you can use custom classes that implement the Product interface.
//case class Part1(v0:Any, v1:Any)
//case class Part2(v0:Any, v1:Any, v2:Any, v3:Any, v4:Any, v5:Any, v6:Any, v7:Any, v8:Any, v9:Any, v10:Any, v11:Any)

// part1 part2
case class Part1(v1:Any, v2:Any)
case class Part2(v3:Any, v4:Any, v5:Any, v6:Any, v7:Any, v8:Any, v9:Any, v10:Any, v11:Any, v12:Any, v13:Any, v14:Any)
case class Part1andPart2(part1:Part1, part2:Part2)

// 索引和VPM
case class VPM(vpm201301:Double,vpm201302:Double,vpm201303:Double,vpm201304:Double,vpm201305:Double,vpm201306:Double,vpm201307:Double,vpm201308:Double,vpm201309:Double,vpm201310:Double,vpm201311:Double,vpm201312:Double)
case class ConsVpm(cons_id:String, cons_no:String, vpm:VPM)

// 转换函数
// Part2 --> VPM :	使用了前面的 g(x)
def Part2toVPM(x:Part2):VPM = {
	VPM(g(x.v3), g(x.v4), g(x.v5), g(x.v6), g(x.v7), g(x.v8), g(x.v9), g(x.v10), g(x.v11), g(x.v12), g(x.v13), g(x.v14))
}
// Part1andPart2 --> ConsVpm
def Part1andPart2toConsVpm(x: Part1andPart2):ConsVpm = {
	ConsVpm(x.part1.v1.toString, x.part1.v2.toString, Part2toVPM(x.part2))
}
	
val rddPairGoodM1 = rddFromHiveGoodM1.map(p => Part1andPart2(Part1(p(0), p(1)), 
	Part2(p(2), p(3), p(4), p(5), p(6), p(7), p(8), p(9), p(10), p(11), p(12), p(13))))
val rddConsVpmGoodM1 = rddPairGoodM1.map( x => Part1andPart2toConsVpm(x))
// ------------------------------------------------
// createSchemaRDD is used to implicitly convert an RDD to a SchemaRDD.
import sqlContext.createSchemaRDD
// 注册为表
rddPairGoodM1.registerAsTable("GoodM1")

// val sc: SparkContext // An existing SparkContext.
val sqlContext = new org.apache.spark.sql.SQLContext(sc)

 rddFromHiveGoodM1.registerAsTable("GoodM1")
val vpmGoodM1 = sqlContext.sql(s"SELECT ${vpmStrOf2013} FROM GoodM1")
val vpm_all = sqlContext.sql("SELECT * FROM VPM")

val rddFromHiveGoodM1  = hiveContext.hql(s"SELECT ${vpmStrOf2013} FROM GoodM1")