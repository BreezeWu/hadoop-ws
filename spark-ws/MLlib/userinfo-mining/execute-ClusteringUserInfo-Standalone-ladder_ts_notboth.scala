// ----------------------------------------------------------------------------
/*
// 对用户进行分群: 独立模式
//		并不依赖于 execute-tryKMeansSmart-ClusterCount.scala 的执行
//
//	在spark-shell中执行 

val taskNamePre = "s98" // s01 # 这个会用来构造表名
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/execute-ClusteringUserInfo-Standalone-ladder_ts_notboth.scala
*/
// ****************************************************************************
// 1. 加载数据与函数
// ****************************************************************************
// (1)样本数据  暂无样本数据脚本
// (2)全量数据
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-rdd-userinfo-ladder_ts_notboth.scala

// 2. 加载函数
// 聚类函数
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/tryKMeansSmart.scala
// 根据聚类模型计算ClusterCount --已经在 execute-s98-v1.scala/execute-s01-v1.scala 中加载
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/ComputeClusterCount.scala

// 给索引数据分配clusterID
:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/ClusteringUserInfo.scala

// ****************************************************************************
// 基于 ParsedRDDMatrix 执行分析任务
// ****************************************************************************

case class AnalyzeResultMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, clusterSet:ClusterSet)

// ----------------------------------------------------------------------------
// ParsedRDDMatrix 分析函数
//		方式2： ClusteringUserInfo_Standalone 模式
// ----------------------------------------------------------------------------
/*
2525  parsedRDDMatrix(2).length
2527  val list = parsedRDDMatrix(2)
2528  list(0)
2529  list(0).item_L1
2530  list(0).item_L2
2531  item
2532  val item = list(0)
2533  val parsedRDDRef = item.parsedRDDRef
2534  val parsedRDD_vpm = parsedRDDRef.vpm
2535  parsedRDDRef.first
2536  val parsedRDD_vpm = parsedRDDRef.vpm
2537  val parsedRDD_vpmIndexed = parsedRDDRef.vpmIndexed
2538  parsedRDDRef
2539  parsedRDD_vpm
2540  parsedRDD_vpmIndexed
2541  val vpm = parsedRDD_vpm.first   // [0.0,0.0,0.0,0.0,0.0,260.0,0.0,0.0,0.0,149.0,0.0,17.0]
2542  val consVpm = parsedRDD_vpmIndexed.first  //  ConsVPM = ConsVPM(Index(733013030,0733013030),[D@30c875c0)
2543  consVpm.vpm   // Array[Double] = Array(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)  怎么全是零呢?
2544  :history

2555  vpm.size          // 12
2556  consVpm.vpm.size  //  12

// ----------------------------------------------------------------------------
val indexL1 = 1
val indexL2 = 1
// ----------------------------------------------------------------------------
val parsedRDDMatrix = ParsedRDDMatrix
val list = parsedRDDMatrix(indexL1)
val item = list(indexL2)

// 对于 ParsedRDD
val parsedRDDRef = item.parsedRDDRef
val parsedRDD_vpm = parsedRDDRef.vpm
val parsedRDD_vpmIndexed = parsedRDDRef.vpmIndexed
// 数量
val countOf_parsedRDD_vpm = parsedRDD_vpm.count                 // 20
val countOf_parsedRDD_vpmIndexed = parsedRDD_vpmIndexed.count    // 20
// first
val parsedRDD_first_vpm = parsedRDD_vpm.first
        // [0.0,0.0,4290.0,18553.0,25221.0,23007.0,20379.0,20282.0,18158.0,21185.0,16748.0,26341.0]
val parsedRDD_first_vpmIndexed = parsedRDD_vpmIndexed.first
        //  ConsVPM = ConsVPM(Index(748000949,7480009497),[D@7c294d8a)

// 回显对象
parsedRDD_first_vpm
parsedRDD_first_vpmIndexed.index
parsedRDD_first_vpmIndexed.vpm
        // Array(0.0, 0.0, 4290.0, 18553.0, 25221.0, 23007.0, 20379.0, 20282.0, 18158.0, 21185.0, 16748.0, 26341.0)

parsedRDD_first_vpm.size
parsedRDD_first_vpmIndexed.vpm.size
// ----------------------------------------------------------------------------
			// 建立模型
			val clusteredInfo_Standalone = ClusteringUserInfo_Standalone(parsedRDD_vpm, parsedRDD_vpmIndexed, perfectK, maxIterations, sc) 
			// 计算分群数据
			val clusterSet_Standalone = ComputeClusterSet(clusteredInfo_Standalone)
			
			val analyzeResultMatrixItem = AnalyzeResultMatrixItem(item.item_L1, item.item_L2, clusterSet_Standalone)

// 测试 ClusteringUserInfo_Standalone
val dataForModel = parsedRDD_vpm
val dataIndexed = parsedRDD_vpmIndexed
val k = perfectK

    val parKTriangle = new KTriangle(k,k+1)
    val resultAccount = evalWSSSEOfK(dataForModel, parKTriangle.low, maxIterations, Account(0,0,Nil))
    
    val model = resultAccount.metricList(0).model
*/
def analyzeParsedRDDMatrix_Standalone(parsedRDDMatrix: List[List[ParsedRDDMatrixItem]], perfectK:Int, maxIterations: Int = 20, sc:org.apache.spark.SparkContext): List[List[AnalyzeResultMatrixItem]] = {	
	def executeParsedRDDList(list:List[ParsedRDDMatrixItem], perfectK:Int, maxIterations: Int):List[AnalyzeResultMatrixItem] = {
		def executeParsedRDD(item:ParsedRDDMatrixItem, perfectK:Int, maxIterations: Int):AnalyzeResultMatrixItem = {
			val parsedRDDRef = item.parsedRDDRef
			val parsedRDD_vpm = parsedRDDRef.vpm
			val parsedRDD_vpmIndexed = parsedRDDRef.vpmIndexed

			// 打印一些信息
			println("----------------------------------------------------------------------------")
			// 时间信息
			val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
			val timeBegin = new java.util.Date()
			println(s">>>>>>>>>\t 开始分析任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)}")
			
			// 建立模型
			val clusteredInfo_Standalone = ClusteringUserInfo_Standalone(parsedRDD_vpm, parsedRDD_vpmIndexed, perfectK, maxIterations, sc) 
			// 计算分群数据
			val clusterSet_Standalone = ComputeClusterSet(clusteredInfo_Standalone)
			
			// 打印一些信息
			val timeEnd = new java.util.Date()
			println(s"结束分析任务: ${item.item_L1.id}-${item.item_L2.id}: ${dateFormat.format(timeBegin)} -> ${dateFormat.format(timeEnd)}\t <<<<<<<<<")
			println("----------------------------------------------------------------------------")
			
			// 结果对象			
			val analyzeResultMatrixItem = AnalyzeResultMatrixItem(item.item_L1, item.item_L2, clusterSet_Standalone)
			return analyzeResultMatrixItem
		}
		
		val result = list.map(y => executeParsedRDD(y, perfectK, maxIterations))
		return result
	}
	
	val analyzeRDDMatrix = parsedRDDMatrix.map(x => executeParsedRDDList(x, perfectK, maxIterations))
	
	return analyzeRDDMatrix
}

// ----------------------------------------------------------------------------
// 将 AnalyzeResultMatrix 的簇样本信息写入文件
def writeAnalyzeResultMatrix_Sample2File(analyzeResultMatrix:List[List[AnalyzeResultMatrixItem]], sampleNum:Int, filenameHead:String) = {
	def writeSampleOfAnalyzeResultList(list:List[AnalyzeResultMatrixItem]) = {
		def writeSampleOfAnalyzeResult(item:AnalyzeResultMatrixItem) = {
			val clusterSet = item.clusterSet
			val k = clusterSet.k 
			val filenameID = s"k${k}_${item.item_L1.id}_${item.item_L2.id}"
			
			writeClusterSetSample2File(clusterSet, sampleNum, filenameHead + filenameID)
			// 结果对象 : 无	
		}
		
		list.map(y => writeSampleOfAnalyzeResult(y))
		// 结果对象 : 无	
	}
	
	// 不再是map，而是foreach
	//analyzeResultMatrix.map(x => writeSampleOfAnalyzeResultList(x))
	analyzeResultMatrix.foreach(x => writeSampleOfAnalyzeResultList(x))	
}
// ----------------------------------------------------------------------------
// 将 AnalyzeResultMatrix 的簇中心信息写入文件
def writeAnalyzeResultMatrix_ClusterCenters2File(analyzeResultMatrix:List[List[AnalyzeResultMatrixItem]], sampleNum:Int, filenameHead:String) = {
	def writeClusterCentersOfAnalyzeResultList(list:List[AnalyzeResultMatrixItem]) = {
		def writeClusterCentersOfAnalyzeResult(item:AnalyzeResultMatrixItem) = {
			val clusterSet = item.clusterSet
			val k = clusterSet.k 
			val filenameID = s"k${k}_${item.item_L1.id}_${item.item_L2.id}"
			
			writeClusterSetCenters2File(clusterSet, filenameHead + filenameID)
			// 结果对象 : 无	
		}
		
		list.map(y => writeClusterCentersOfAnalyzeResult(y))
		// 结果对象 : 无	
	}
	
	// 不再是map，而是foreach
	//analyzeResultMatrix.map(x => writeClusterCentersOfAnalyzeResultList(x))
	analyzeResultMatrix.foreach(x => writeClusterCentersOfAnalyzeResultList(x))	
}


// ----------------------------------------------------------------------------
// 在 ParsedRDDMatrix 上执行分析任务
val perfectK = 3;
val maxIterations = 20 // 当前没有生效

// 执行分析
val AnalyzeResultMatrix = analyzeParsedRDDMatrix_Standalone(ParsedRDDMatrix, perfectK, maxIterations, sc) 

// 测试分析
// 计算 ParsedRDDMatrix 中各个RDD 的count
val rddCountMatrix_ParsedRDD = ComputeRDDCount_ParsedRDDMatrix(ParsedRDDMatrix)
        //  List(List((11723,11723), (39,39), (995,995)), List((2037,2037), (1,1), (85,85)), List((324,324), (2,2), (52,52)), List((3624,3624), (20,20), (734,734)))

// ****************************************************************************
// 交互式查询样本
//	 	1.下面矩阵是 DataSetRefItem_L1 * DataSetRefItem_L2 的矩阵，即 4*3
//				DataSetRef_L1 (index:Int, id:String, tablename:String)
//				DataSetRef_L2 (index:Int, id:String, tablename:String, where:String)
//		2.矩阵
//						SqlMatrix:	查询Hive的SQL String |sql| VpmSqlRef(vpm:String, vpmIndexed:String)
//				HiveRDDMatrix:	Hive数据集句柄 |hiveDataRef| VpmHiveRDDRef(vpm:SchemaRDD, vpmIndexed:SchemaRDD)
//			ParsedRDDMatrix:  RDD数据集句柄 |parsedRDDRef| VpmParsedRDDRef(vpm: RDD[Vector], vpmIndexed:RDD[ConsVPM])
//	AnalyzeResultMatrix:	群分后的数据 clusterSet:ClusterSet
// ****************************************************************************
// ----------------------------------------------------------------------------
// (1) 单月数据 GoodM1-Ladder
val x = AnalyzeResultMatrix(0)(0).clusterSet
x.k
x.clusterCenters
x.clusterArray
getSampleFromClusterSet(x,0,10) // 从簇0中寻找10个样本
getSampleFromClusterSet(x,3,50) // 从簇0中寻找50个样本
getSampleFromClusterSet(x,x.k+1,2) // 从簇0中寻找2个样本  应该报错!

// ----------------------------------------------------------------------------
val head = taskNamePre + "_L2"
// 将簇样本信息写入文件
val sampleNum = 200
writeAnalyzeResultMatrix_Sample2File(AnalyzeResultMatrix, sampleNum, head)

// 单独将簇中心信息写入文件
writeAnalyzeResultMatrix_ClusterCenters2File(AnalyzeResultMatrix, head)
// ----------------------------------------------------------------------------
// 计算年用电量合计
val YearSum_ParsedRDDMatrix = computeYearSum_ParsedRDDMatrix_Standalone(ParsedRDDMatrix) 
// 打印出 L1*L2的年用电量
def printYearSum(x:YearSum_ParsedRDDMatrixItem) = {
	val id_L1 = x.item_L1.id
	val id_L2 = x.item_L2.id
	val yearSum = x.yearSumRef.yearSum_Vpm
	
	println(s"${id_L1}-${id_L2}: ${yearSum}")
}
/* def printYearSumOfList(y:List[YearSum_ParsedRDDMatrixItem]) = {
	y.foreach(printYearSum)
}*/
// 运行打印函数
YearSum_ParsedRDDMatrix.foreach(list => list.foreach(printYearSum))

// ----------------------------------------------------------------------------
// 计算月用电量合计
val MonthSum_ParsedRDDMatrix = computeMonthSum_ParsedRDDMatrix_Standalone(ParsedRDDMatrix)
// 打印出 L1*L2的月用电量合计
def printMonthSum(x:MonthSum_ParsedRDDMatrixItem) = {
	val id_L1 = x.item_L1.id
	val id_L2 = x.item_L2.id
	val monthSum = x.monthSumRef.monthSum_Vpm
	
	println(s"${id_L1}-${id_L2}: ${monthSum}")
}
/* def printMonthSumOfList(y:List[MonthSum_ParsedRDDMatrixItem]) = {
	y.foreach(printMonthSum)
}*/
// 运行打印函数
MonthSum_ParsedRDDMatrix.foreach(list => list.foreach(printMonthSum))

