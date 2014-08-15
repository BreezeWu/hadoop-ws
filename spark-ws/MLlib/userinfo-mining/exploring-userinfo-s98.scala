// ----------------------------------------------------------------------------
// 使用Spark SQL， 探索数据数量
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)

// ****************************************************************************
// 在表 bigdata_ts_or_prcscope_s98_unique 基础上查找三类用户数据
// 	1. 第一类, 阶梯电价
//	2. 第二类，分时电价
//	3. 第三类，既不是阶梯电价也不是分时电价

/*
  不选择 bigdata_ts_or_prcscope_s98 是因为有重复记录，当前的处理办法是只取无重复的数据
	总数	阶梯	分时	都不是
bigdata_ts_or_prcscope_s98	      21498140	18620293	158551	2719296
bigdata_ts_or_prcscope_s98_unique	20837778	18371291	88454	2378033
                             差值	660362	249002	70097	341263
*/
// ****************************************************************************
/*
val sqlCount1 = hiveContext.hql("select count(*) from bigdata_ts_or_prcscope_s98")
sqlCount1.collect()		// 21498140

val sqlCount2 = hiveContext.hql("select count(*) from bigdata_ts_or_prcscope_s98_unique")
sqlCount2.collect()		// 20837778
*/
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

// 构造是阶梯电价的 where 语句
val whereOfLadder_Prefix = "(prc_code = '"
val whereOfLadder_Sep = "' or prc_code = '"
val whereOfLadder_Middle = ladderList.map(x => x._2).reduce((x,y) => (x+ whereOfLadder_Sep + y))
//val whereMiddle = ladderList.map(x => x._2).foldLeft("prc_code = '")((x,y) => (x+ whereOfLadder_Sep + y)) // 需要减去头部多余内容
val whereOfLadder_Postfix = "')"
val whereOfLadder = whereOfLadder_Prefix + whereOfLadder_Middle + whereOfLadder_Postfix

// ----------------------------------------------------------------------------
// 找出阶梯用户
// val selectLadder = hiveContext.hql(s"select * from bigdata_ts_or_prcscope_s98 where ${whereOfLadder}")
val selectLadder = hiveContext.hql(s"select * from bigdata_ts_or_prcscope_s98_unique where ${whereOfLadder}")
val selectLadderCount = selectLadder.count		// 18620293 -> 18371291
if (0 != selectLadderCount) {	
	selectLadder.first		//
	//select2.collect	//
	selectLadder.take(10)
}

// ----------------------------------------------------------------------------
// 获得各个阶梯电价码的用户数量
// 计算某一个电价码的数量
def ComputeCountOfPrcCode(prcCodeName:String):(Long,org.apache.spark.sql.SchemaRDD) = {
	// 使用外部 hiveContext
	val selectSql = hiveContext.hql(s"select * from bigdata_ts_or_prcscope_s98_unique where prc_code = '${prcCodeName}'")
	
	val count = selectSql.count
	return (count, selectSql)
}
// 计算 ladderList 中每个电价码的数量
def ComputeCountedLadderList(ladderList:List[(Int, String)]):List[(Int,String,(Long,org.apache.spark.sql.SchemaRDD))] = {
	val CountedLadderList = ladderList.map(x => (x._1, x._2, ComputeCountOfPrcCode(x._2)))
	
	return CountedLadderList
}
// 打印函数
def printComputeCountedLadderList(countedLadderList:List[(Int,String,(Long,org.apache.spark.sql.SchemaRDD))]) = {
	//countedLadderList.foreach(x => println(s"${x._1},${x._2} : ${x._3._1}"))
	println("计数,\t编码,\t名称")
	countedLadderList.foreach(x => println(s"${x._3._1},${x._1},${x._2}"))
}

// 打印出各个阶梯电价码的用户数量
val CountedLadderList = ComputeCountedLadderList(ladderList)
printComputeCountedLadderList(CountedLadderList)

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
// 构造是 分时电价 的where语句
val whereOfTs = s"${whereOfNotLadder} and ts_flag = '是'"

// ----------------------------------------------------------------------------
// 找出是 分时电价的数据
// val selectTs = hiveContext.hql(s"select * from bigdata_ts_or_prcscope_s98 where ${whereOfNotLadder} and ts_flag = '是'")
val selectTs = hiveContext.hql(s"select * from bigdata_ts_or_prcscope_s98_unique where ${whereOfNotLadder} and ts_flag = '是'")
val selectTsCount = selectTs.count		// 158551 ->	88454
if (0 != selectTsCount) {	
	selectTs.first		//
	//select2.collect	//
	selectTs.take(10)
}

// ----------------------------------------------------------------------------
// 找出是 既不是阶梯电价也不是分时电价的数据

// 构造是 既不是阶梯电价也不是分时电价 的where语句
val whereOfNotTsNotLadder = s"${whereOfNotLadder} and ts_flag != '是'"

// val selectNotTsNotLadder = hiveContext.hql(s"select * from bigdata_ts_or_prcscope_s98 where ${whereOfNotTsNotLadder}")
val selectNotTsNotLadder = hiveContext.hql(s"select * from bigdata_ts_or_prcscope_s98_unique where ${whereOfNotTsNotLadder}")
val selectNotTsNotLadderCount = selectNotTsNotLadder.count		// 2719296 -> 2378033
if (0 != selectNotTsNotLadderCount) {	
	selectNotTsNotLadder.first		// 
	//select2.collect	//
	selectNotTsNotLadder.take(10)
}

// ----------------------------------------------------------------------------
// 计算L1*L2的年用电量

// 只对vpm进行计算，不计算vpmIndexed;但结构上保留
case class YearSum_ParsedRDDRef(yearSum_Vpm: Double], yearSum_VpmIndexed:Double)
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
			println(s"开始计算年用电量任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)} -> ${dateFormat.format(timeEnd)}\t <<<<<<<<<")
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




