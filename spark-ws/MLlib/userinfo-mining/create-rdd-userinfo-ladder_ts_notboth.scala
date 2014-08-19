// ----------------------------------------------------------------------------
// 从hive获取数据 S98
//		
//	在spark-shell中执行 
//      val taskNamePre = "s98" // s01 # 这个会用来构造表名
//		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-rdd-userinfo-s98-ladder_ts_notboth.scala

//	用户月用电量数据: 
//			第一层划分，数据四大类						DataSetRef
//			第二层划分，阶梯/分时/两者都不是	JoinSelectSet

/*
	一， 第一层划分
-- 数据全集 = 有效数据 + 无效数据
-- 1. 有效数据
-- (1) 单月数据, BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M1 --> rddFromHive_GoodM1
-- (2) 双月数据, BIGDATA_USER_INFO_S98_ONEBIGTABLE_GOOD_M2 --> rddFromHive_GoodM2

-- 2. 无效数据
-- (1) 前三个月都是 0或NULL， BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F3 --> rddFromHive_BadF3
-- (2) 仅前两个月都是 0或NULL, BIGDATA_USER_INFO_S98_ONEBIGTABLE_ZERO_AND_NULL_F2_EXCLUDE_F3  --> rddFromHive_BadF2ExcludeF3


	二， 第二层划分
// 在表 bigdata_ts_or_prcscope_s98_unique 基础上查找三类用户数据
// 	1. 第一类, 阶梯电价
//	2. 第二类，分时电价
//	3. 第三类，既不是阶梯电价也不是分时电价

  不选择 bigdata_ts_or_prcscope_s98 是因为有重复记录，当前的处理办法是只取无重复的数据
	总数	阶梯	分时	都不是
bigdata_ts_or_prcscope_s98	      21498140	18620293	158551	2719296
bigdata_ts_or_prcscope_s98_unique	20837778	18371291	88454	2378033
                             差值	660362	249002	70097	341263
*/
// ----------------------------------------------------------------------------

// ----------------------------------------------------------------------------
// 	1.第一类, prc_code 是下面内容的是 居民阶梯电价
/*
20201069	城镇居民生活(1kV以下,居民电采暖12-04，无谷)
20201070	居民生活(1kV以下,居民电采暖，不征收附加无谷)
20101001	乡村居民生活(1kV以下,征收附加)
20101005	乡村居民生活(1kV以下,附加费进目录)
20102001	乡村居民生活(1-10kV,附加费进目录)
20202001	城镇居民生活(1-10kV,不征收附加)
20201004	城镇居民生活(1kV以下,居民电采暖12-04)
20201009	城镇居民电采暖(1kV以下,附加费进目录)
20201010	城镇居民生活(1kV以下,附加费进目录)
20201014	城镇居民生活(1kV以下,不征收附加)
20201021	城镇居民生活(1kV以下,征收附加)
20202016	城镇居民生活(1-10kV,附加费进目录)
20202046	城镇居民生活(1-10kV,征收附加)
20203003	城镇居民生活(35-66kV,征收附加)
20203004	城镇居民生活(35-66kV,不征收附加)
20208001	城镇居民生活(20kV,征收附加)
20102006	乡村居民生活(1-10kV,不征收附加)
*/
val ladderList = List(
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
val whereOfLadder_Middle = ladderList.map(x => x._2).reduce((x,y) => (x+ whereOfLadder_Sep + y))
//val whereMiddle = ladderList.map(x => x._2).foldLeft("prc_code = '")((x,y) => (x+ whereOfLadder_Sep + y)) // 需要减去头部多余内容
val whereOfLadder_Postfix = "')"

// ----------------------------------------------------------------------------
// 构造是 阶梯电价 的where 语句
val whereOfLadder = whereOfLadder_Prefix + whereOfLadder_Middle + whereOfLadder_Postfix

// ****************************************************************************
// 2.第一类之外， 
//	(2.1) ts_flag = '是' 属于分时电价用户
//	(2.2) ts_flag != '是' 则既不是分时电价，也不是阶梯电价用户
// ****************************************************************************
// 从剩下数据中， 找出分时用户
// 使用 !${whereOfLadder} 都早语句有问题！
// val selectTs = hiveContext.hql(s"select * from bigdata_ts_or_prcscope_s98 where !${whereOfLadder} and ts_flag = '是'")

// 构造不是阶梯电价 的where语句
val whereOfNotLadder_Prefix = "(prc_code != '"
val whereOfNotLadder_Sep = "' and prc_code != '"
val whereOfNotLadder_Middle = ladderList.map(x => x._2).reduce((x,y) => (x+ whereOfNotLadder_Sep + y))
//val whereMiddle = ladderList.map(x => x._2).foldLeft("prc_code = '")((x,y) => (x+ whereOfNotLadder_Sep + y)) // 需要减去头部多余内容
val whereOfNotLadder_Postfix = "')"
val whereOfNotLadder = whereOfNotLadder_Prefix + whereOfNotLadder_Middle + whereOfNotLadder_Postfix

// ----------------------------------------------------------------------------
// 构造是 分时电价 的where语句
val whereOfTs = s"${whereOfNotLadder} and ts_flag = '是'"
// ----------------------------------------------------------------------------
// 构造是 既不是阶梯电价也不是分时电价 的where语句
val whereOfNotTsNotLadder = s"${whereOfNotLadder} and ts_flag != '是'"

// ****************************************************************************
// 连接OneBigTable a 与 bigdata_ts_or_prcscope_s98_unique b
//	三类 where语句
// ****************************************************************************
// ----------------------------------------------------------------------------
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
// ----------------------------------------------------------------------------
// 第二层分类: 阶梯/分时/NotTsNotLadder
case class DataSetRefItem_L2(index:Int, id:String, tablename:String, where:String)
// 表名
//val tmpTableName = "bigdata_ts_or_prcscope_s98_unique"
// 表名: 根据 taskNamePre 构建 DataSetRef_L2中的表名
val tmpTableName = s"bigdata_ts_or_prcscope_${taskNamePre}_unique"

val DataSetRef_L2 = List(
	DataSetRefItem_L2(0, "Ladder", tmpTableName, whereOfLadder),
	DataSetRefItem_L2(1, "Ts", tmpTableName, whereOfTs),
	DataSetRefItem_L2(2, "NotTsNotLadder", tmpTableName, whereOfNotTsNotLadder)
)
// ----------------------------------------------------------------------------
// 第一层分类: 数据四大类
case class DataSetRefItem_L1(index:Int, id:String, tablename:String)

// 固定为 s98
/*
val DataSetRef_L1 = List(
	DataSetRefItem_L1(0, "GoodM1", "bigdata_user_info_s98_onebigtable_good_m1"),
	DataSetRefItem_L1(1, "GoodM2", "bigdata_user_info_s98_onebigtable_good_m2"),
	DataSetRefItem_L1(2, "BadF2ExcludeF3", "bigdata_user_info_s98_onebigtable_zero_and_null_f2_exclude_f3"),
	DataSetRefItem_L1(3, "BadF3", "bigdata_user_info_s98_onebigtable_zero_and_null_f3")
)*/

// 根据 taskNamePre 构建 DataSetRef_L1
val DataSetRef_L1 = List(
	DataSetRefItem_L1(0, "GoodM1", s"bigdata_user_info_${taskNamePre}_onebigtable_good_m1"),
	DataSetRefItem_L1(1, "GoodM2", s"bigdata_user_info_${taskNamePre}_onebigtable_good_m2"),
	DataSetRefItem_L1(2, "BadF2ExcludeF3", s"bigdata_user_info_${taskNamePre}_onebigtable_zero_and_null_f2_exclude_f3"),
	DataSetRefItem_L1(3, "BadF3", s"bigdata_user_info_${taskNamePre}_onebigtable_zero_and_null_f3")
)

// 第一层与第二层交叉的SQL
case class VpmSqlRef(vpm:String, vpmIndexed:String)
case class SQLMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, sql:VpmSqlRef)

// ----------------------------------------------------------------------------
// 引入自定义transformations
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/transformations-of-userinfo.scala
// ----------------------------------------------------------------------------
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector

case class VpmHiveRDDRef(vpm:org.apache.spark.sql.SchemaRDD, vpmIndexed:org.apache.spark.sql.SchemaRDD)
case class HiveRDDMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, hiveDataRef:VpmHiveRDDRef)

case class VpmParsedRDDRef(vpm: RDD[org.apache.spark.mllib.linalg.Vector], vpmIndexed:RDD[ConsVPM])
case class ParsedRDDMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, parsedRDDRef:VpmParsedRDDRef)

// ----------------------------------------------------------------------------
// 构建第一层与第二层交叉的SQLMatrix
// ----------------------------------------------------------------------------
def buildSQLMatrix(dataSetRef_L1:List[DataSetRefItem_L1], dataSetRef_L2:List[DataSetRefItem_L2]):List[List[SQLMatrixItem]] = {
	// 变成 另外一个表的 语句
	def GetSelectOfAlias(sAlias:String, sOrigin:String, sSep:String = ","):String = {
		;//sOrigin.split("")
		val sArray = sOrigin.split(sSep)
		val result = sArray.map(x => sAlias +"."+ x).reduce((x,y) => x + sSep + y)
		return result
		
		// 下面语句失败 [等价于 sArray.reduce((x,y) => "a."+ x + "," + "a."+ y) ]
		/*
		// 结果是  String = a.a.a.a.a.a.a.a.a.a.a.vpm201301,a.vpm201302,a.vpm201303,a.vpm201304,a.vpm201305,a.vpm201306,a.vpm201307,a.vpm201308,a.vpm201309,a.vpm201310,a.vpm201311,a.vpm201312
		// why?????
		val sAlias = "a"
		def AliasColumn(col:String) = sAlias +"."+ col
		sArray.reduce((x,y) => (AliasColumn(x)) + sSep + (AliasColumn(y)))
		*/
		// 下面语句也失败: 编译不通过!
		/*
		sArray.reduce((x,y) => (x => sAlias +"."+ x) + sSep + (y => sAlias +"."+ y))
		sArray.reduce((x,y) => (x => sAlias +"."+ x) + sSep + (y:String => sAlias +"."+ y))
		*/
	}
	
	// ID
	val idInfo = "cons_id, cons_no"	
	// 月用电量(vpm)
	val vpmStrOf2013 = "vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312"
	
	val vpmIndexedStrOf2013 = "cons_id, cons_no, vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312"
	// 月用电量(vpm)
	val vpmStrOf2013_a = GetSelectOfAlias("a", vpmStrOf2013)
	val vpmIndexedStrOf2013_a = GetSelectOfAlias("a", vpmIndexedStrOf2013)
	
	// 构造 joinSelectSQL
	def buildJoinSelectSQL(table_a:String, table_b:String, sSelect:String, sWhere:String):String = {
		val joinSelectSql = s"select ${sSelect} from ${table_a} a left semi join (select * from ${table_b} where ${sWhere}) b on a.cons_no = b.cons_no"
	
		return joinSelectSql
	}	

	// 构造 joinSelectSQL
	def buildSqlListForL1(item_L1:DataSetRefItem_L1):List[SQLMatrixItem] = {		
		val sSelect = vpmStrOf2013_a
		val sSelectIndexed = vpmIndexedStrOf2013_a
		val sqlMatrixItemList = dataSetRef_L2.map(y => 
			SQLMatrixItem(item_L1, y, VpmSqlRef(
						buildJoinSelectSQL(item_L1.tablename, y.tablename, sSelect, y.where),
						buildJoinSelectSQL(item_L1.tablename, y.tablename, sSelectIndexed, y.where)
					))
			)
	
		return sqlMatrixItemList
	}
	
	// DataSetRef_L2 与 DataSetRef_L1 的笛卡尔乘积
	val sqlMatrix = dataSetRef_L1.map(x => buildSqlListForL1(x))
	
	/*
	for(item_L1 <- dataSetRef_L1) {
		println("L1:" + item_L1.id)
		for(item_L2 <- dataSetRef_L2) {
			println("L2:" + item_L2.id)
			val joinSelectSql = buildJoinSelectSQL(item_L1.tablename, item_L2.tablename, vpmStrOf2013_a, item_L2.where)
			
			println(joinSelectSql)
		}
	}*/
	
	return sqlMatrix
}

// ----------------------------------------------------------------------------
// 构建第一层与第二层交叉的 HiveRDDMatrix
// 利用 SqlMatrix 构建 Hive数据
// ----------------------------------------------------------------------------
def buildHiveRDDMatrix(sqlMatrix:List[List[SQLMatrixItem]]):List[List[HiveRDDMatrixItem]] = {
	//val hiveRDDMatrix = List[List[HiveRDDMatrixItem]]()
	
	def buildHiveRDDList(list:List[SQLMatrixItem]):List[HiveRDDMatrixItem] = {
		def buildHiveRDD(item:SQLMatrixItem):HiveRDDMatrixItem = {
			val sql_vpm = item.sql.vpm
			val sql_vpmIndexed = item.sql.vpmIndexed
			
			val rddFromHive_vpm  = hiveContext.hql(sql_vpm)
			val rddFromHive_vpmIndexed  = hiveContext.hql(sql_vpmIndexed)
			
			val hiveRDDMatrixItem = HiveRDDMatrixItem(item.item_L1, item.item_L2, VpmHiveRDDRef(rddFromHive_vpm,rddFromHive_vpmIndexed))
			
			return hiveRDDMatrixItem
		}
		
		val result = list.map(y => buildHiveRDD(y))
		return result
	}
	
	val hiveRDDMatrix = sqlMatrix.map(x => buildHiveRDDList(x))
	
	return hiveRDDMatrix
}

// ----------------------------------------------------------------------------
// 从HiveRDDMatrix 变换为 ParsedRDDMatrix
// ----------------------------------------------------------------------------
def transform2ParsedRDDMatrix(hiveRDDMatrix:List[List[HiveRDDMatrixItem]]):List[List[ParsedRDDMatrixItem]] = {	
	def transformHiveRDDList(list:List[HiveRDDMatrixItem]):List[ParsedRDDMatrixItem] = {
		def transformHiveRDD(item:HiveRDDMatrixItem):ParsedRDDMatrixItem = {
			val hiveDataRef = item.hiveDataRef
			val hiveData_vpm = hiveDataRef.vpm
			val hiveData_vpmIndexed = hiveDataRef.vpmIndexed
			
			// 变换 rddFromHive_* (应用数据)
			val parsedData_vpm = hiveData_vpm.map(x => f(x)).	// 去掉null,转换为Double
				map(x => Vectors.dense(x.toArray))		// 转变为Vector, 构建matrices
				
			// 变换 rddFromHiveIndexed_* (应用数据)
			val parsedData_vpmIndexed  = hiveData_vpmIndexed.map(r => row2ConsVPM(r))

			// 结果对象			
			val parsedRDDMatrixItem = ParsedRDDMatrixItem(item.item_L1, item.item_L2, VpmParsedRDDRef(parsedData_vpm,parsedData_vpmIndexed))
			
			return parsedRDDMatrixItem
		}
		
		val result = list.map(y => transformHiveRDD(y))
		return result
	}
	
	val parsedRDDMatrix = hiveRDDMatrix.map(x => transformHiveRDDList(x))
	
	return parsedRDDMatrix
}

// ----------------------------------------------------------------------------
// 打印 ParsedRDDMatrix 中各自的数量
def ComputeRDDCount_ParsedRDDMatrix(parsedRDDMatrix: List[List[ParsedRDDMatrixItem]]):List[List[(Long,Long)]] = {
	def ComputeRDDCount_ParsedRDDList(list:List[ParsedRDDMatrixItem]):List[(Long,Long)] = {
		def ComputeRDDCount_ParsedRDD(item:ParsedRDDMatrixItem):(Long,Long) = {
			val parsedRDDRef = item.parsedRDDRef
			val parsedRDD_vpm = parsedRDDRef.vpm
			val parsedRDD_vpmIndexed = parsedRDDRef.vpmIndexed
			
			// count
			val countOf_parsedRDD_vpm = parsedRDD_vpm.count
			val countOf_parsedRDD_vpmIndexed = parsedRDD_vpmIndexed.count
			
			// 结果对象			
			val rddCountPair = Tuple2(countOf_parsedRDD_vpm, countOf_parsedRDD_vpmIndexed)
			return rddCountPair
		}
		
		val result = list.map(y => ComputeRDDCount_ParsedRDD(y))
		return result
	}
	
	val rddCountMatrix = parsedRDDMatrix.map(x => ComputeRDDCount_ParsedRDDList(x))
	
	return rddCountMatrix
}


// ----------------------------------------------------------------------------
// 计算L1*L2的年用电量合计

// 只对vpm进行计算，不计算vpmIndexed;但结构上保留
case class YearSum_ParsedRDDRef(yearSum_Vpm: Double, yearSum_VpmIndexed:Double)
case class YearSum_ParsedRDDMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, yearSumRef:YearSum_ParsedRDDRef)

def computeYearSum_ParsedRDDMatrix_Standalone(parsedRDDMatrix: List[List[ParsedRDDMatrixItem]]): List[List[YearSum_ParsedRDDMatrixItem]] = {	
	def computeYearSum_ParsedRDDList(list:List[ParsedRDDMatrixItem]):List[YearSum_ParsedRDDMatrixItem] = {
		def computeYearSum_ParsedRDD(item:ParsedRDDMatrixItem):YearSum_ParsedRDDMatrixItem = {
			val parsedRDDRef = item.parsedRDDRef
			val parsedRDD_vpm = parsedRDDRef.vpm
			// val parsedRDD_vpmIndexed = parsedRDDRef.vpmIndexed

			// 打印一些信息
			println("----------------------------------------------------------------------------")
			// 时间信息
			val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
			val timeBegin = new java.util.Date()
			println(s">>>>>>>>>\t 开始计算年用电量任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)}")
			
			// 计算某一个用户的年用电量
			def computerYearSum_OneUser(x:Array[Double]):Double = {
				x.foldLeft(0.0)((x,y) => x+y )
			}
			
			// 计算结果
			val yearSum_Vpm = parsedRDD_vpm.map(x => computerYearSum_OneUser(x.toArray)).reduce(
				(y1, y2) => y1 + y2
			)
			// 创建结果对象(x)
			val yearSum_ParsedRDDRef = YearSum_ParsedRDDRef(yearSum_Vpm,0)
			
			// 打印一些信息
			val timeEnd = new java.util.Date()
			println(s"结束计算年用电量任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)} -> ${dateFormat.format(timeEnd)}\t <<<<<<<<<")
			println("----------------------------------------------------------------------------")
			
			// 结果对象			
			val yearSum_ParsedRDDMatrixItem = YearSum_ParsedRDDMatrixItem(item.item_L1, item.item_L2, yearSum_ParsedRDDRef)
			return yearSum_ParsedRDDMatrixItem
		}
		
		val result = list.map(y => computeYearSum_ParsedRDD(y))
		return result
	}
	
	val yearSum_ParsedRDDMatrix = parsedRDDMatrix.map(x => computeYearSum_ParsedRDDList(x))
	
	return yearSum_ParsedRDDMatrix
}

// ----------------------------------------------------------------------------
// 计算L1*L2的月用电量合计

// 只对vpm进行计算，不计算vpmIndexed;但结构上保留
case class MonthSum_ParsedRDDRef(monthSum_Vpm: Array[Double], monthSum_VpmIndexed:Array[Double])
case class MonthSum_ParsedRDDMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, monthSumRef:MonthSum_ParsedRDDRef)

def computeMonthSum_ParsedRDDMatrix_Standalone(parsedRDDMatrix: List[List[ParsedRDDMatrixItem]]): List[List[MonthSum_ParsedRDDMatrixItem]] = {	
	def computeMonthSum_ParsedRDDList(list:List[ParsedRDDMatrixItem]):List[MonthSum_ParsedRDDMatrixItem] = {
		def computeMonthSum_ParsedRDD(item:ParsedRDDMatrixItem):MonthSum_ParsedRDDMatrixItem = {
			val parsedRDDRef = item.parsedRDDRef
			val parsedRDD_vpm = parsedRDDRef.vpm
			// val parsedRDD_vpmIndexed = parsedRDDRef.vpmIndexed

			// 打印一些信息
			println("----------------------------------------------------------------------------")
			// 时间信息
			val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
			val timeBegin = new java.util.Date()
			println(s">>>>>>>>>\t 开始计算月用电量合计任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)}")
			
			// 计算某一个用户的年用电量
			def addMonthSum(x:Array[Double], y:Array[Double]):Array[Double] = {
				val zipXY = x.zip(y)
				return zipXY.map(z => z._1 + z._2)
			}
			
			// 计算结果
			val monthSum_Vpm = parsedRDD_vpm.map(x => x.toArray).reduce( (y1,y2) => addMonthSum(y1,y2))
			// 创建结果对象(x)
			val monthSum_ParsedRDDRef = MonthSum_ParsedRDDRef(monthSum_Vpm,Array[Double]())
			
			// 打印一些信息
			val timeEnd = new java.util.Date()
			println(s"结束计算月用电量合计任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)} -> ${dateFormat.format(timeEnd)}\t <<<<<<<<<")
			println("----------------------------------------------------------------------------")
			
			// 结果对象			
			val monthSum_ParsedRDDMatrixItem = MonthSum_ParsedRDDMatrixItem(item.item_L1, item.item_L2, monthSum_ParsedRDDRef)
			return monthSum_ParsedRDDMatrixItem
		}
		
		val result = list.map(y => computeMonthSum_ParsedRDD(y))
		return result
	}
	
	val monthSum_ParsedRDDMatrix = parsedRDDMatrix.map(x => computeMonthSum_ParsedRDDList(x))
	
	return monthSum_ParsedRDDMatrix
}


// 计算SQLMatrix
val SqlMatrix = buildSQLMatrix(DataSetRef_L1, DataSetRef_L2)
// 计算HiveRDDMatrix
val HiveRDDMatrix = buildHiveRDDMatrix(SqlMatrix)
// 变换为 ParsedRDDMatrix
val ParsedRDDMatrix = transform2ParsedRDDMatrix(HiveRDDMatrix)

