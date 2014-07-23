// ----------------------------------------------------------------------------
// spark-shell的启动
//		为什么下面语句只启动4个Executor？
//		SPARK_EXECUTOR_INSTANCES=7 SPARK_EXECUTOR_MEMORY=2g SPARK_DRIVER_MEMORY=1g spark-shell


// 在 spark-shell 中 :load 该文件
//		:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/create-KMeansModel-parsedData.scala
//		:load /home/hadoop/workspace_github/hadoop-ws/spark-ws/demo-spark-03-mllib-kmeans-s98-smart.scala
//		tryKMeans_Smart(parsedData,1,10,20)
// 智能化寻找最佳k
//		输入期望最小、最大、和中值。在小区间去寻找！

// ---------------------------------------------------------------------------------------------------------------------------
	// 聚类Profiling信息
	case class Profiling(k:Int, 
		maxIterations: Int = 20, 
		WSSSE: Double, 
		begin: java.util.Date, 
		end: java.util.Date,
		clusters: org.apache.spark.mllib.clustering.KMeansModel	// 放在最后是因为可能不获取
	)
	
	// 累计计数器
	case class Account(counter: Int = 0, tryCounter:Int = 0, profilingList: List[Profiling])
	
	// Profiling对象的比较函数-- 隐含比较函数
	/*  <<ScalaByExample.2014.pdf>> p121
	// Here is an example of an implicit conversion function that converts integers into instances of class scala.Ordered:
	implicit def int2ordered(x: Int): Ordered[Int] = new Ordered[Int] {
		def compare(y: Int): Int =
		if (x < y) -1
		else if (x > y) 1
		else 0
	}
	*/
	
	implicit def Profiling2ordered(x: Profiling): Ordered[Profiling] = new Ordered[Profiling] {
		def compare(y: Profiling): Int =
			if (x.WSSSE < y.WSSSE) -1
			else if (x.WSSSE > y.WSSSE) 1
			else 0
	}
// ---------------------------------------------------------------------------------------------------------------------------

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector

// 我们的 smart函数
def tryKMeans_Smart(data: RDD[Vector], minK: Int = 2, maxK: Int, maxIterations: Int = 20):Account = {
	// ****************************************************************************
	// ----------------------------------------------------------------------------
	// 辅助函数
	def min(x: Int, y: Int): Int = {
		if (x > y) y
		else x
	}
	
	def max(x: Int, y: Int): Int = {
		if (x > y) x
		else y
	}
	// ----------------------------------------------------------------------------
	// ##### 内部变量（从参数获取）
	
	// 聚类迭代次数
	// val maxIterations = 20
	val m_numIterations = maxIterations //20
	
	// 内部变量:	最小最大k处理
	//val minK = 1
	//val maxK = 10
	
	val i = min(minK,maxK)
	val j = max(minK,maxK)
	val m = (i + j) / 2 

	// 三元组， 三角形
	case class KTriangle(low:Int, high: Int, median: Int)
	val parKTriangle = KTriangle(i,j,m)
		
	// 利用样例数据创建一个KMeansModel. 参见"create-KMeansModel-parsedData.scala"
	//val clustersOfK: org.apache.spark.mllib.clustering.KMeansModel = clusters
	// ----------------------------------------------------------------------------
	
//    // ---------------------------------------------------------------------------------------------------------------------------
//    	// ----------------------------------------------------------------------------
//    	// ##### 内部变量（其他非参数获取部分）
//    	
//    	// 聚类Profiling信息
//    	case class Profiling(k:Int, 
//    		m_numIterations: Int = 20, 
//    		WSSSE: Double, 
//    		begin: java.util.Date, 
//    		end: java.util.Date,
//    		clusters: org.apache.spark.mllib.clustering.KMeansModel	// 放在最后是因为可能不获取
//    	)
//    	
//    	// 累计计数器
//    	case class Account(counter: Int = 0, tryCounter:Int = 0, profilingList: List[Profiling])
//    	
//    	// Profiling对象的比较函数-- 隐含比较函数
//    	/*  <<ScalaByExample.2014.pdf>> p121
//    	// Here is an example of an implicit conversion function that converts integers into instances of class scala.Ordered:
//    	implicit def int2ordered(x: Int): Ordered[Int] = new Ordered[Int] {
//    		def compare(y: Int): Int =
//    		if (x < y) -1
//    		else if (x > y) 1
//    		else 0
//    	}
//    	*/
//    	
//    	implicit def Profiling2ordered(x: Profiling): Ordered[Profiling] = new Ordered[Profiling] {
//    		def compare(y: Profiling): Int =
//    			if (x.WSSSE < y.WSSSE) -1
//    			else if (x.WSSSE > y.WSSSE) 1
//    			else 0
//    	}
//    // ---------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------
	// 完全tryKMeans_Smart使用的函数, 需要使用tryKMeans_Smart的内部变量
	
	// 对某个K进行KMeans聚类
	def evalWSSSEOfK(k:Int, x:Account) = {
		// profiling信息
		val timeBegin = new java.util.Date()
		
		//val WSSSEOfK = scala.util.Random.nextDouble //% 9999	// 模拟随机数
		val clustersOfK = KMeans.train(data, k, m_numIterations)
		val WSSSEOfK = clustersOfK.computeCost(data)
		
		// profiling信息
		val timeEnd = new java.util.Date()
		
		// profiling对象
		val newProfiling = Profiling(k, m_numIterations, WSSSEOfK, timeBegin, timeEnd, clustersOfK)
		// 添加到 profilingList
		val newProfilingList = newProfiling :: x.profilingList
		// 函数返回值
		Account(x.counter + 1, x.tryCounter + 1, newProfilingList)
	}
	
	// ****************************************************************************
	// 真正的 smart 处理
	// ****************************************************************************
	// 是否允许递归
	def willingDoMore(x:Account) =  {
		// 结束条件
		//		1.  low == high  ： 在runHelper中处理了
		//	    2.  聚类次数(调用KMeans.train)达到最大次数	: 在本函数中处理

		// 30是随便取的一个数，之前测试发现一次调用需要40分钟，一夜之间要跑完，所以不要超过30次调用。
		val maxTryTimes = 30	
		if (x.counter > maxTryTimes || x.tryCounter > maxTryTimes)  {
			println(s"ERROR: ********************* 达到了最大运行上线次数${x.counter} ${x.tryCounter} > ${maxTryTimes} *********************")
			false
		} else
			true
	}
	
	// 是否可以调用evalWSSSEOfK的KTriangle
	def canKMeansTrain(triangle: KTriangle, x:Account):Boolean =  {
		if( triangle.low == triangle.high) {
			println(s"ERROR: 最小k和最大k是左闭右开区间，[${triangle.low}, ${triangle.high}), 不能相等！")
			return false
		}
		
		// 也不能相等
		if( triangle.low + 1 == triangle.high) {
			return false
		} 
		
		// 达到最大运行上限了吗？ 
		willingDoMore(x)
	}
	
	// 内部嵌套运行的函数
	def runHelper(triangle: KTriangle, x: Account): Account = {	
		// 首次检查
		if (!canKMeansTrain(triangle, x)) {
					println(s"----------------------------------------------------------------------------------")
					println(s"-------------------------- 此时退出：  [${triangle.low}, ${triangle.high}, ${triangle.median})  ------------------------------")
					println(s"-------------------------- \t  ${x} ------------------------------")
					println(s"----------------------------------------------------------------------------------")
			return x
		}
			
		println(s"----------------------------------------------------------------------------------")
		println(s"-------------此时继续：  [${triangle.low}, ${triangle.high}, ${triangle.median}) -----------------")
		println(s"-------------------------- \t  ${x} ------------------------------")
		println(s"----------------------------------------------------------------------------------")
		
		// ----------------------------------------------------------------------------
		// 第一层
		// 两头的在runHelper之前单独调用了！
		//val iAccount = evalWSSSEOfK(triangle.low, x)
		//val jAccount = evalWSSSEOfK(triangle.high, iAccount)
		
		// 消除重复, 若有重复计算，也不允许继续执行
		val tmpKList = x.profilingList.map(x => x.k).sorted
		val indexOfMedian = tmpKList.indexOf(triangle.median)
		if (indexOfMedian >= 0 ) {	// 若有正确索引，表示已经计算过
			return x
		}
		
		// 对中间值进行计算		
		var kAccount = Account(0,0,Nil)
		if (triangle.low +1 < triangle.high) {
 			kAccount = evalWSSSEOfK(triangle.median, x)
 		} else {
 			// 尝试也进行计数 
 			kAccount = Account(x.counter, x.tryCounter+ 1, x.profilingList)
 		}
 			
 		// 当前的Account
 		val curAccount = kAccount

		// 再次检查  !!!!!
		/*
		if (!canKMeansTrain(triangle, curAccount)) {
		
					println(s"----------------------------------------------------------------------------------")
					println(s"-------------------------- 此时退出：  [${triangle.low}, ${triangle.high}, ${triangle.median})  ------------------------------")
					println(s"-------------------------- \t  ${x} ------------------------------")
					println(s"----------------------------------------------------------------------------------")
					
			return curAccount
		}
		*/
		// ----------------------------------------------------------------------------
		// 第二层 
		// 从 curAccount.profilingList 中生成两个新的KTriangle 进行计算
		val curProfilingList = curAccount.profilingList
		
		// 从 curProfilingList 中找到WSSSE最小的三个 (运用了隐含比较函数implicit def Profiling2ordered)
		// val sortedProfilingList = curProfilingList.map(x => x.WSSSE -> x).sorted
		val sortedProfilingList = curProfilingList.sorted
		
		// 找到WSSSE最小的那个, 获得其k值
		val kValueOfMinWSSSE = sortedProfilingList(0).k
		
		// 找到 kValueOfMinWSSSE 的前后两个相邻k
		val sortedKList = curProfilingList.map(x => x.k).sorted	//取出目前计算的k,并排序
		val index_MinWSSSE = sortedKList.indexOf(kValueOfMinWSSSE)	// 找到index (WSSSE最小的那个)
		
		println("------------------------------- curProfilingList -------------------")
		curProfilingList.foreach(println)
		println("------------------------------- sortedProfilingList -------------------")
		sortedProfilingList.foreach(println)
		println("------------------------------- sortedKList -------------------")
		println(s"-------------------------- \t  ${sortedKList} ------------------------------")
		
		// -------------------------------
		// 第一种情况： 在最前面 ==> 分裂一次
		// 若 index_MinWSSSE 为0, 第一个最小
		if ( index_MinWSSSE == 0)	{
			val type01_split1 = KTriangle(
				sortedKList(index_MinWSSSE),
				sortedKList(index_MinWSSSE + 1),
				 (sortedKList(index_MinWSSSE)+sortedKList(index_MinWSSSE + 1))/2
			)
			
			// 判断是否需要递归运行
			//if (canKMeansTrain(type01_split1,curAccount))  runHelper(type01_split1,curAccount)
			
			println(s"----------------------------------------------------------------------------------")
			println(s"-------------第一种情况： 在最前面 ==> 分裂一次 -----------------")
			println(s"------------- \t ${type01_split1}-----------------")
			
			return runHelper(type01_split1,curAccount)
		}
	
		// 第二种情况： 在最后面 ==> 分裂一次
		// 若 index_MinWSSSE 为1, 最后一个最小
		if ( index_MinWSSSE == (sortedKList.size -1)) {
			val type02_split1 = KTriangle(
				sortedKList(index_MinWSSSE -1),
				sortedKList(index_MinWSSSE),
				 (sortedKList(index_MinWSSSE - 1)+sortedKList(index_MinWSSSE))/2
			)
			
			// 判断是否需要递归运行
			//if (canKMeansTrain(type02_split1,curAccount))  runHelper(type02_split1,curAccount)
			
			println(s"----------------------------------------------------------------------------------")
			println(s"------------- 第二种情况： 在最后面 ==> 分裂一次 -----------------")
			println(s"------------- \t ${type02_split1}-----------------")
			
			return runHelper(type02_split1,curAccount)
		}
		
		// -------------------------------
		// 第三种情况： 在中间 ==> 分裂两次
		{
			// 分裂1
			val littleNeighbour = index_MinWSSSE -1
			val bigNeighbour = index_MinWSSSE +1
			
			val type03_split1 = KTriangle(
				sortedKList(littleNeighbour),
				sortedKList(index_MinWSSSE),
				 (sortedKList(littleNeighbour)+sortedKList(index_MinWSSSE))/2
			)
				
			println(s"----------------------------------------------------------------------------------")
			println(s"------------- 第三种情况： 在中间 ==> 分裂两次  part1 -----------------")
			println(s"------------- \t ${type03_split1}-----------------")
			
			// 判断是否需要递归运行
			//if (canKMeansTrain(type03_split1))  runHelper(type03_split1,curAccount)
			val split1Account = runHelper(type03_split1,curAccount)
			
			// 分裂2
			val type03_split2 = KTriangle(
				sortedKList(index_MinWSSSE),
				sortedKList(bigNeighbour),
				 (sortedKList(littleNeighbour)+sortedKList(bigNeighbour))/2
			)
			
			
			println(s"----------------------------------------------------------------------------------")
			println(s"------------- 第三种情况： 在中间 ==> 分裂两次  part2 -----------------")
			println(s"------------- \t ${type03_split2}-----------------")
			
			// 判断是否需要递归运行
			//if (canKMeansTrain(type03_split2,split1Account))  runHelper(type03_split2,split1Account)
			//runHelper(type03_split2,curAccount)
			runHelper(type03_split2,split1Account)
		}
		// ----------------------------------------------------------------------------
	}
	// ****************************************************************************
	
	// ****************************************************************************
	// 函数中的 main函数 ^_^
	// ----------------------------------------------------------------------------
	// 1. 参数错误
	if( parKTriangle.low == parKTriangle.high) {
			println(s"ERROR: 最小k和最大k是左闭右开区间，[${parKTriangle.low}, ${parKTriangle.high}), 不能相等！")
			
			// 返回值
			Account(0,0,Nil)
	}  else {
		// ----------------------------------------------------------------------------
		// 2. 运行一次
		// 如果 high = low + 1, 则只需要计算low即可
		if (parKTriangle.low + 1 == parKTriangle.high ) {
			evalWSSSEOfK(parKTriangle.low, Account(0,0,Nil))
		} else {
			// ----------------------------------------------------------------------------
			// 3. 要运行多次
			// 计算两头的
			val iFirstAccount = evalWSSSEOfK(parKTriangle.low, Account(0,0,Nil))
			val iLastAccount = evalWSSSEOfK(parKTriangle.high, iFirstAccount)
			
			runHelper(parKTriangle, iLastAccount)		
		}
	}	
	// ****************************************************************************
}

def writeAccount2HDFS(x: Account) = {
	// 取得任务的开始日期
	val beginDate = x.profilingList(x.counter - 1).begin
	
	// ----------------- 写入文件 文件名后面有日期
	import java.util.Date
	val fileFormat = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")	// "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
	val dateString = fileFormat.format(beginDate) 

	val sparkRoot = "/user/spark/"
	val dmProfiling = sparkRoot + "profiling/"
	val kmeansProfilingPath = dmProfiling + "k-means-" + dateString

	val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")	// "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
	//val distData = sc.parallelize(x.profilingList.reverse.
	//	map(y => s"${y.k}  ${y.maxIterations} ${y.WSSSE} [${dateFormat.format(y.begin)},${dateFormat.format(y.end)}] ${y.clusters} ")
	//)
	
		val distData = sc.parallelize(x.profilingList.reverse.
		map(y => s"${y.k}, ${y.maxIterations}, ${y.WSSSE}, ${dateFormat.format(y.begin)}, ${dateFormat.format(y.end)}, ${y.clusters} ")
	)
	
	distData.saveAsTextFile(kmeansProfilingPath)
	
	// 函数返回值
	Tuple2(kmeansProfilingPath, distData)
}

// ------------------------------------------------------------

// 进行分析
val resultAccount = tryKMeans_Smart(parsedData,1,50,20)

// 打印结果
writeAccount2HDFS(resultAccount)


