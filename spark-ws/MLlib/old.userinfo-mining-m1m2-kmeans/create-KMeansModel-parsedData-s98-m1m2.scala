// begin ------------------------------ 从hive取数据 ### S98类型的表-用户的数据###
// 2013年单月用户数据 
//	数据源: BIGDATA_USER_INFO_S98_2013M1_V_FOR_CLUSTERING

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)

// ----------------------------------------------------------------------------
// 单月数据 m1
// 获取 月用电量(vpm), 实收(rcved_amt), 应收(rcvbl_amt), 欠费(owning_amt)
// val rddFromHiveM1 = hiveContext.hql("SELECT  vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312,rcved_amt201301,rcved_amt201302,rcved_amt201303,rcved_amt201304,rcved_amt201305,rcved_amt201306,rcved_amt201307,rcved_amt201308,rcved_amt201309,rcved_amt201310,rcved_amt201311,rcved_amt201312,rcvbl_amt201301,rcvbl_amt201302,rcvbl_amt201303,rcvbl_amt201304,rcvbl_amt201305,rcvbl_amt201306,rcvbl_amt201307,rcvbl_amt201308,rcvbl_amt201309,rcvbl_amt201310,rcvbl_amt201311,rcvbl_amt201312,owning_amt201301,owning_amt201302,owning_amt201303,owning_amt201304,owning_amt201305,owning_amt201306,owning_amt201307,owning_amt201308,owning_amt201309,owning_amt201310,owning_amt201311,owning_amt201312  FROM BIGDATA_USER_INFO_S98_2013M1_V_FOR_CLUSTERING x")  // 没有 cons_id

// 获取 月用电量(vpm)
val rddFromHiveM1 = hiveContext.hql("SELECT  vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312 FROM BIGDATA_USER_INFO_S98_2013M1_V_FOR_CLUSTERING x")  // 没有 cons_id

// 获取 月用电量(vpm) 年度汇总
//val rddFromHiveM1 = hiveContext.hql("SELECT sum(vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312)  FROM BIGDATA_USER_INFO_S98_2013M1_V_FOR_CLUSTERING x")  // 没有 cons_id

// ----------------------------------------------------------------------------
// 双月数据 m2

// 获取 月用电量(vpm), 实收(rcved_amt), 应收(rcvbl_amt), 欠费(owning_amt)
//val rddFromHiveM2 = hiveContext.hql("SELECT  vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312,rcved_amt201301,rcved_amt201302,rcved_amt201303,rcved_amt201304,rcved_amt201305,rcved_amt201306,rcved_amt201307,rcved_amt201308,rcved_amt201309,rcved_amt201310,rcved_amt201311,rcved_amt201312,rcvbl_amt201301,rcvbl_amt201302,rcvbl_amt201303,rcvbl_amt201304,rcvbl_amt201305,rcvbl_amt201306,rcvbl_amt201307,rcvbl_amt201308,rcvbl_amt201309,rcvbl_amt201310,rcvbl_amt201311,rcvbl_amt201312,owning_amt201301,owning_amt201302,owning_amt201303,owning_amt201304,owning_amt201305,owning_amt201306,owning_amt201307,owning_amt201308,owning_amt201309,owning_amt201310,owning_amt201311,owning_amt201312  FROM BIGDATA_USER_INFO_S98_2013M2_V_FOR_CLUSTERING x")  // 没有 cons_id

// 获取 月用电量(vpm)
val rddFromHiveM2 = hiveContext.hql("SELECT  vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312 FROM BIGDATA_USER_INFO_S98_2013M2_V_FOR_CLUSTERING x")  // 没有 cons_id

// ----------------------------------------------------------------------------
// 数据变换函数
// ------------------------ begin: 转换为vector,最后变为matrices
// 定义函数:对一个org.apache.spark.sql.Row中每一个列的处理函数
// 函数原型为: g: (y: Any)Double
def g(y:Any):Double = y match {
	case null => 0		// 将null转换为0
	case i:Int => i.toDouble	// 将Int转换为Double
	case d:Double => d
}

// 定义函数: 对每一个org.apache.spark.sql.Row对象的处理函数
// 原型为: f: (x: org.apache.spark.sql.Row)Seq[Double]
def f(x:org.apache.spark.sql.Row) = {
	x.map(y => g(y))
}

// ----------------------------------------------------------------------------
// 数据变换结果

// ------------------------------------
// 双月数据 m1
// 转换为vector,最后变为matrices
val parsedDataM1 = rddFromHiveM1.map(x => f(x)).	// 去掉null,转换为Double
	map(x => Vectors.dense(x.toArray))	// 转变为Vector, 构建matrices

// ------------------------------------
// 双月数据 m2
// 转换为vector,最后变为matrices
val parsedDataM2 = rddFromHiveM2.map(x => f(x)).	// 去掉null,转换为Double
	map(x => Vectors.dense(x.toArray))	// 转变为Vector, 构建matrices

// 测试 g(y)
// 对一个org.apache.spark.sql.Row中每一个列的处理
// rddFromHive.first.map(y => g(y))

// --------------------------------
// val clustersOfK: org.apache.spark.mllib.clustering.KMeansModel = clusters
