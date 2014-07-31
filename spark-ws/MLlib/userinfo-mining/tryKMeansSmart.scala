// ----------------------------------------------------------------------------
//     智能化寻找最佳k
//        输入期望最小、最大
//
// 在 spark-shell 中 运行
/*
    // 1. 加载数据 
    // 最后生成 parsedData:org.apache.spark.rdd.RDD[org.apache.spark.mllib.linalg.Vector]
    :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s01.scala
    //:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/create-parsedData-userinfo-s98.scala

    // 2. 加载函数
    :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/tryKMeansSmart.scala

    // 3. 使用函数进行分析
    // (1). 实际数据
    // 数据, 起始k, 最大k, KMeans.train中的maxIterations
    //val resultAccount = tryKMeansSmart(parsedData,1,50,20)	//所有用户数据
    //val resultAccountM1 = tryKMeansSmart(parsedDataM1,1,100,20)
    //val resultAccountM2 = tryKMeansSmart(parsedDataM2,1,100,20)
    // (2). 随机数模拟
    // 若evalWSSSEOfK是随即数模拟
    // val resultAccount = tryKMeansSmart(null,1,50,20)

    // 4. 将结果写入HDFS
    // 参数: sortedType,排序方式	0-默认,即计算k的顺序; 1-按照k从小到大排序; 2-两种排序方式都写入
    //writeAccount2HDFS(resultAccount,2)
    //writeAccount2HDFS(resultAccountM1,2)
    //writeAccount2HDFS(resultAccountM2,2)
    
    // 3-4
	val minK = 2
	val maxK = 60
	val maxIterations = 20 // 当前没有生效
	val resultAccountM2 = tryKMeansSmart(parsedDataM2,minK,maxK,maxIterations)
	val rr2 = writeAccount2HDFS(resultAccountM2,2)
	
	val resultAccountM1 = tryKMeansSmart(parsedDataM1,minK,maxK,maxIterations)
	val rr1 = writeAccount2HDFS(resultAccountM1,2)

	rr2
	rr1

*/
// ---------------------------------------------------------------------------------------------------------------------------
    // 聚类Metric信息
    case class Metric(k:Int, 
        maxIterations: Int = 20, 
        WSSSE: Double, 
        begin: java.util.Date, 
        end: java.util.Date,
        clusters: org.apache.spark.mllib.clustering.KMeansModel
    ) {
        val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
        def toRecord = k +","+ maxIterations +","+ WSSSE +","+ dateFormat.format(begin) +","+ dateFormat.format(end) +","+ clusters
    }
 
    // 统计信息
    // counter: 实际执行次数; tryCounter:尝试次数; metricList:实际执行的metric信息
    case class Account(counter: Int = 0, tryCounter:Int = 0, metricList: List[Metric]) {
        //def toRecordLine = s"${counter},${tryCounter},${metricList}"   // 这样的语句是错误的! s"xxx" 是一种编译时的糖果!
    def toRecordOfMetricList = metricList.map(x=>x.toRecord).foldLeft("")((x,y) => x + "\n" + y)
    def toRecord() = { 
        // 注意: 下面语句, 加号必须放在每行尾部,不能放在每行的首部
        val str = "counter:\t" + counter + "\n" + 
            "tryCounter:\t" + tryCounter + "\n" + 
            "metricList:\t" + toRecordOfMetricList
        str
    }

        // 下面定义错误: error: value unary_+ is not a member of String  // 因为加号在首,会翻译为 unary_+ 函数调用!!!
        //    http://stackoverflow.com/questions/1991240/scala-method-operator-overloading
        // 但stackoverflow的解释看不懂!  (why?????)
        /*
        def toRecordLine = "------------------------------------------------------------------------\n" 
            + "\t counter =\t${counter}\n"
            + "\t tryCounter =\t${tryCounter}\n"
            + "\t metricList =\n\t\t${metricList}\n"
            + "------------------------------------------------------------------------\n"
        */
    }
    
    // Metric对象的比较函数-- 隐含比较函数, 根据WSSSE排序
    /*  参考: <<ScalaByExample.2014.pdf>> p121
        // Here is an example of an implicit conversion function that converts integers into instances of class scala.Ordered:
        implicit def int2ordered(x: Int): Ordered[Int] = new Ordered[Int] {
            def compare(y: Int): Int =
            if (x < y) -1
            else if (x > y) 1
            else 0
        }
    */    
    implicit def Metric2ordered(x: Metric): Ordered[Metric] = new Ordered[Metric] {
        def compare(y: Metric): Int =
	    (x.WSSSE).compare(y.WSSSE)
            /*
            if (x.WSSSE < y.WSSSE) -1
            else if (x.WSSSE > y.WSSSE) 1
            else 0*/
    }

    // ----------------------------------------------------------------------------
    // 三元组辅助函数
    def min(x: Int, y: Int): Int = {
        if (x > y) y
        else x
    }
    
    def max(x: Int, y: Int): Int = {
        if (x > y) x
        else y
    }

    // 三元组, 但是只能使用两个参数创建  ^_^
    case class KTriangle private(low:Int, high: Int, median: Int) {
        def this(x:Int, y: Int) = this(min(x,y),max(x,y), (x+y)/2)
        //def apply(x:Int, y: Int) = new KTriangle(min(x,y),max(x,y), (x+y)/2)    // 这个为什么没有效果呢?
    }

    // 已经在 "case class KTriangle" 中定义了接受两个参数的 this函数, 不能再定义 "object KTriangle", 否则会提示
    //     <console>:229: error: reference to KTriangle is ambiguous;
    //object KTriangle {
    //    def apply(x:Int, y: Int) = new KTriangle(x,y)
    //}
    /*
    // 创建三元组
    def createKTriangle(minK: Int, maxK: Int): KTriangle = {
        val i = min(minK,maxK)
        val j = max(minK,maxK)
        val m = (i + j) / 2 

        new KTriangle(i,j,m)
    }

    // 使用上面函数
     val type02_split1 = createKTriangle(
                sortedKList(index_MinWSSSE -1),
                sortedKList(index_MinWSSSE)
            )         
            return runHelper(type02_split1,curAccount)

    会提示下面错误: (why?????)
    <console>:199: error: type mismatch;
     found   : KTriangle(in class $iwC)
     required: KTriangle(in method tryKMeansSmart)
                   return runHelper(type01_split1,curAccount)
                                    ^

    */
// ---------------------------------------------------------------------------------------------------------------------------
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector
    // ****************************************************************************
    // 对某个K进行KMeans聚类
    def evalWSSSEOfK(data: RDD[Vector], k:Int, maxIterations: Int = 20, x:Account) = {
        // metric信息
        val timeBegin = new java.util.Date()
        
        /* 
        // 随机数模拟
        val WSSSEOfK = scala.util.Random.nextDouble //% 9999    // 模拟随机数
        val clustersOfK: org.apache.spark.mllib.clustering.KMeansModel = null
        */
        // 执行KMeans算法
        val clusteringKM = new KMeans()
	clusteringKM.setK(k)
	val model = clusteringKM.run(data)
	val WSSSEOfK = model.computeCost(data)
        //val clustersOfK = KMeans.train(data, k, maxIterations)
        //val WSSSEOfK = clustersOfK.computeCost(data)
        
        // metric信息
        val timeEnd = new java.util.Date()
        
        // metric对象
        val newMetric = Metric(k, maxIterations, WSSSEOfK, timeBegin, timeEnd, model)
        // 添加到 metricList
        val newMetricList = newMetric :: x.metricList
        // 函数返回值
        new Account(x.counter + 1, x.tryCounter + 1, newMetricList)
    }

// ---------------------------------------------------------------------------------------------------------------------------

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector

// 我们的 smart函数
def tryKMeansSmart(data: RDD[Vector], minK: Int = 2, maxK: Int, maxIterations: Int = 20):Account = {
    // 用于debug的print
    def DEBUGprint(s:String) = {
        println(s"\n----------------------------------------------------------------------------------\n"
            + s
            + "\n----------------------------------------------------------------------------------\n"
        )
    }
    
    // ****************************************************************************
    // runHelper的辅助函数
    // ----------------------------------------------------------------------------
    // 是否允许继续执行runHelper
    def willingDoMore(x:Account) =  {
        // 结束条件
        //        1.  low == high  ： 在runHelper中处理了
        //        2.  聚类次数(调用KMeans.train)达到最大次数    : 在本函数中处理

        // 30是随便取的一个数，之前测试发现一次调用需要40分钟，一夜之间要跑完，所以不要超过30次调用。
        val maxTryTimes = 30    
        if (x.counter > maxTryTimes || x.tryCounter > maxTryTimes)  {
            println(s"ERROR: ********************* 达到了最大运行上线次数${x.counter} ${x.tryCounter} > ${maxTryTimes} *********************")
            false
        } else
            true
    }
    
    // 在runHelper中,可以执行evalWSSSEOfK吗?
    def canKMeansTrain(triangle: KTriangle, x:Account):Boolean =  {
        // --------------------------------------
        // 三元组判断
        if( triangle.low == triangle.high) {
            println(s"ERROR: 最小k和最大k是左闭右开区间，[${triangle.low}, ${triangle.high}), 不能相等！")
            return false
        }
        
        // 也不能只相差1
        if( triangle.low + 1 == triangle.high) {
            return false
        } 
        
        // --------------------------------------
        // Account判断
        // 达到最大运行上限了吗？ 
        willingDoMore(x)
    }
    
    // 内部嵌套运行的函数
    def runHelper(triangle: KTriangle, x: Account): Account = {    
        // 首次检查
        if (!canKMeansTrain(triangle, x)) {
            DEBUGprint("[runHelper]首次检查时退出：  \n\t KTriangle = " + triangle + "\n \t Account = " + x.toRecord)
            return x
        }
        
        DEBUGprint("[runHelper] 即将对中值[" + triangle.median + "]执行evalWSSSEOfK：  \n\t KTriangle = " + triangle + "\n \t Account = " + x.toRecord)
        
        // ----------------------------------------------------------------------------
        // 第一层
        // 两头的在runHelper之前单独调用了！
        //val iAccount = evalWSSSEOfK(data, triangle.low, maxIterations, x)
        //val jAccount = evalWSSSEOfK(data, triangle.high, maxIterations, iAccount)
        
        // 消除重复, 若有重复计算，也不允许继续执行
        val tmpKList = x.metricList.map(x => x.k).sorted
        val indexOfMedian = tmpKList.indexOf(triangle.median)
        if (indexOfMedian >= 0 ) {    // 若有正确索引，表示已经计算过
            DEBUGprint("[runHelper] 中值k[" + triangle.median + "]已经被计算过, 索引为" + indexOfMedian + 
		"  \n\t KTriangle = " + triangle + "\n \t Account = " + x.toRecord)
            return x
        }
        
        // 对中间值进行计算        
        var kAccount:Account = null
        if (triangle.low +1 < triangle.high) {
             kAccount = evalWSSSEOfK(data, triangle.median, maxIterations, x)
         } else {
             // 尝试也进行计数 
             kAccount = Account(x.counter, x.tryCounter+ 1, x.metricList)
         }
             
         // 当前的Account
         val curAccount = kAccount

        // 再次检查  !!!!!  --- 不需要!
        // ----------------------------------------------------------------------------
        // 第二层 
        // 从 curAccount.metricList 中生成两个新的KTriangle 进行计算
        val curMetricList = curAccount.metricList
        
        // 从 curMetricList 中找到WSSSE最小的三个 (运用了隐含比较函数implicit def Metric2ordered)
        // val sortedMetricList = curMetricList.map(x => x.WSSSE -> x).sorted
        val sortedMetricList = curMetricList.sorted
        
        // 找到WSSSE最小的那个, 获得其k值
        val kValueOfMinWSSSE = sortedMetricList(0).k
        
        // 找到 kValueOfMinWSSSE 的前后两个相邻k
        val sortedKList = curMetricList.map(x => x.k).sorted    //取出目前计算的k,并排序
        val index_MinWSSSE = sortedKList.indexOf(kValueOfMinWSSSE)    // 找到index (WSSSE最小的那个)
        
        DEBUGprint("[runHelper] 即将尝试再次分裂" + 
	    "\n\n\t curMetricList：  \n " + Account(0,0,curMetricList).toRecordOfMetricList +
            "\n\n\t sortedMetricList(by WSSSE)：  \n " + Account(0,0,sortedMetricList).toRecordOfMetricList +
            "\n\n\t sortedKList(by K)：  \n" + sortedKList)
        
        // -------------------------------
        // 第一种情况： 在最前面 ==> 分裂一次
        // 若 index_MinWSSSE 为0, 第一个最小
        if ( index_MinWSSSE == 0)    {
            val type01_split1 = new KTriangle(
        sortedKList(index_MinWSSSE),
        sortedKList(index_MinWSSSE + 1))
            
            DEBUGprint("[runHelper] 第一种情况： 在最前面 ==> 分裂一次  \n\t " + type01_split1)
            // 判断是否需要递归运行                    
            return runHelper(type01_split1,curAccount)
        }
    
        // 第二种情况： 在最后面 ==> 分裂一次
        // 若 index_MinWSSSE 为1, 最后一个最小
        if ( index_MinWSSSE == (sortedKList.size -1)) {
            val type02_split1 = new KTriangle(
                sortedKList(index_MinWSSSE -1),
                sortedKList(index_MinWSSSE))
  
            DEBUGprint("[runHelper] 第二种情况： 在最后面 ==> 分裂一次  \n\t " + type02_split1)          
            // 判断是否需要递归运行         
            return runHelper(type02_split1,curAccount)
        }
        
        // -------------------------------
        // 第三种情况： 在中间 ==> 分裂两次
        {
            // 分裂1
            val littleNeighbour = index_MinWSSSE -1
            val bigNeighbour = index_MinWSSSE +1
            
            val type03_split1 = new KTriangle(
                sortedKList(littleNeighbour),
                sortedKList(index_MinWSSSE))
                
            DEBUGprint("[runHelper] 第三种情况： 在中间 ==> 分裂两次  part1  \n\t " + type03_split1)
            // 判断是否需要递归运行         
            val split1Account = runHelper(type03_split1,curAccount)
            
            // 分裂2
            val type03_split2 = new KTriangle(
                sortedKList(index_MinWSSSE),
                sortedKList(bigNeighbour))
            
            DEBUGprint("[runHelper] 第三种情况： 在中间 ==> 分裂两次  part1  \n\t " + type03_split2)
            
            // 判断是否需要递归运行
            return runHelper(type03_split2,split1Account)
        }
        // ----------------------------------------------------------------------------
    }
    // ****************************************************************************
    
    // ****************************************************************************
    // 函数中的 main函数 ^_^
    // ----------------------------------------------------------------------------
    // ##### 获取参数
    
    // 聚类迭代次数
    // val maxIterations = 20
    
    // 内部变量:    最小最大k处理
    //val minK = 1
    //val maxK = 10
    
    val parKTriangle = new KTriangle(minK,maxK)    //(i,j,m)
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
            evalWSSSEOfK(data, parKTriangle.low, maxIterations, Account(0,0,Nil))
        } else {
            // ----------------------------------------------------------------------------
            // 3. 要运行多次
            // 计算两头的
            val iFirstAccount = evalWSSSEOfK(data, parKTriangle.low, maxIterations, Account(0,0,Nil))
            val iLastAccount = evalWSSSEOfK(data, parKTriangle.high, maxIterations, iFirstAccount)
            
            runHelper(parKTriangle, iLastAccount)        
        }
    }    
    // ****************************************************************************
}

// ---------------------------------------------------------------------------------------------------------------------------
// 写入HDFS
// 	sortedType,排序方式	0-默认,即计算k的顺序; 1-按照k从小到大排序
def writeAccount2HDFS(x: Account, sortedType:Int = 0) = {
    // 取得任务的开始日期
    val beginDate = x.metricList(x.counter - 1).begin
    
    // ----------------- 写入文件 文件名后面有日期
    import java.util.Date
    val fileFormat = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
    val dateString = fileFormat.format(beginDate) 

    val sparkRoot = "/user/spark/"
    val dmMetric = sparkRoot + "metric/"

    // ------------------------------------------------------------------------
    // 度量数据
    // metrics
    val kmeansMetricPath = dmMetric + "k-means-" + dateString +"_c"+ x.counter +"_try"+ x.tryCounter // + "_" + sortedType
  
    // 处理数据
    // 日期格式化
    val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"       

    // 处理排序方式
    // (1) 默认排序
    if (sortedType == 0 ) {
        val distData = sc.parallelize(
            x.metricList.reverse.
                map(y => s"${y.k}, ${y.maxIterations}, ${y.WSSSE}, ${dateFormat.format(y.begin)}, ${dateFormat.format(y.end)}, ${y.clusters}")
        )
        distData.saveAsTextFile(kmeansMetricPath + "_0")
    } 
    // (2) 按照k大小排序
    if (sortedType == 1 )  {
        val distData = sc.parallelize(
            x.metricList.map(x => x.k -> x).sorted.map(x => x._2).
            map(y => s"${y.k}, ${y.maxIterations}, ${y.WSSSE}, ${dateFormat.format(y.begin)}, ${dateFormat.format(y.end)}, ${y.clusters}")
        )
        distData.saveAsTextFile(kmeansMetricPath + "_1")
    } 
    // (3) 处理排序方式 两种都写入
    if (sortedType == 2 ) {
	// 默认排序
        val distData = sc.parallelize(
            x.metricList.reverse.
                map(y => s"${y.k}, ${y.maxIterations}, ${y.WSSSE}, ${dateFormat.format(y.begin)}, ${dateFormat.format(y.end)}, ${y.clusters}")
        )
        distData.saveAsTextFile(kmeansMetricPath + "_0")

	// 按照k排序
        val distData2 = sc.parallelize(
            x.metricList.map(x => x.k -> x).sorted.map(x => x._2).
            map(y => s"${y.k}, ${y.maxIterations}, ${y.WSSSE}, ${dateFormat.format(y.begin)}, ${dateFormat.format(y.end)}, ${y.clusters}")
        )
        distData2.saveAsTextFile(kmeansMetricPath + "_1")
    }

    // ------------------------------------------------------------------------
    // 最佳K的中心数据
    // bestK_clusterCenters
    // Account中的metricList按照WSSSE从小到大排序
    val sortedMetricList = x.metricList.sorted
        
    // 第一个里面就有最优的k
    val bestK = sortedMetricList(0).k
    val bestClusters = sortedMetricList(0).clusters

    // 将簇中心转换为RDD,并写入文件
    val clustersData = sc.parallelize(bestClusters.clusterCenters)

    // 文件路径
    val dmClusterCenters = sparkRoot + "clustercenters/"
    val kmeansClusterCentersPath = dmClusterCenters + "k-means-" + dateString +"_bestK" + bestK + "_clusterCenters"

    // 写入文件
    clustersData.saveAsTextFile(kmeansClusterCentersPath)

    // ------------------------------------------------------------------------
    // 函数返回值
    Tuple2(kmeansMetricPath + "_" + sortedType, kmeansClusterCentersPath)
}

