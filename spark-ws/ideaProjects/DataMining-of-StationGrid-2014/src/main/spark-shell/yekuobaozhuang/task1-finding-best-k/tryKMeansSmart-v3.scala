/**
 * Created by HongZe.Wu on 9/28/14.
 */

// ----------------------------------------------------------------------------
//     智能化寻找最佳k
//        输入期望最小、最大
//
// 在 spark-shell 中 运行  (请参见 execute-s01-v1.scala execute-s98-v1.scala 中的"任务1： 寻找最佳K")
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
    //val resultAccount = tryKMeansSmart(parsedData,1,50,20)    //所有用户数据
    //val resultAccountM1 = tryKMeansSmart(parsedDataM1,1,100,20)
    //val resultAccountM2 = tryKMeansSmart(parsedDataM2,1,100,20)
    // (2). 随机数模拟
    // 若evalWSSSEOfK是随即数模拟
    // val resultAccount = tryKMeansSmart(null,1,50,20)

    // 4. 将结果写入HDFS
    // 参数: sortedType,排序方式    0-默认,即计算k的顺序; 1-按照k从小到大排序; 2-两种排序方式都写入
    //writeAccount2HDFS(resultAccount,2)
    //writeAccount2HDFS(resultAccountM1,2)
    //writeAccount2HDFS(resultAccountM2,2)

    // 3-4
    val minK = 2
    val maxK = 20
    val maxIterations = 20 // 当前没有生效
    val taskName = "S01_M2"
    val resultAccountM2 = tryKMeansSmart(parsedDataM2,minK,maxK,maxIterations)
    val rr2 = writeAccount2HDFS(resultAccountM2,2,taskName)

    val resultAccountM1 = tryKMeansSmart(parsedDataM1,minK,maxK,maxIterations)
    val rr1 = writeAccount2HDFS(resultAccountM1,2,taskName)

    rr2
    rr1

	// 最佳K 和 最佳K的model
	val account = resultAccountM2
	val perfect = account.getPerfectKandModel()
*/
// ---------------------------------------------------------------------------------------------------------------------------
// 聚类Metric信息
case class Metric(k:Int,
                  maxIterations: Int = 20,
                  WSSSE: Double,  // 集內均方差和, 或叫 簇内
                  SSR: Double,  // 组间平方和, 或叫 簇间
                  evaluator:Double, // 评价函数
                  begin: java.util.Date,
                  end: java.util.Date,
                  model: org.apache.spark.mllib.clustering.KMeansModel
                   ) {
  val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
  def toRecord = k +","+ maxIterations +","+ WSSSE +","+ SSR +","+ evaluator +
      ","+ dateFormat.format(begin) +","+ dateFormat.format(end) +","+ model
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

  def getBeginDateString():String = {
    if (0==this.metricList.size) return ""

    val beginDate = this.metricList(this.metricList.size - 1).begin

    import java.util.Date
    val fileFormat = new java.text.SimpleDateFormat("yyyy-MM-dd") // "yyyy-MM-dd-HH-mm-ss"
    val dateString = fileFormat.format(beginDate)
    dateString
  }

  def getPerfectKandModel():(Int,org.apache.spark.mllib.clustering.KMeansModel) = {
    if (0==this.metricList.size) return Tuple2(0, null)

    // Account中的metricList按照WSSSE从小到大排序
    val sortedMetricList = this.metricList.sortBy(i => i.WSSSE) //.sorted

    // 第一个里面就有最优的k
    val perfectK = sortedMetricList(0).k
    val perfectModel = sortedMetricList(0).model

    // 返回
    Tuple2(perfectK, perfectModel)
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
// 计算中心点/质心
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector
def ComputeGravityCenter(data: RDD[Vector]):(Vector,Long) = {
  import org.apache.spark.mllib.linalg.Vector
  import org.apache.spark.mllib.linalg.DenseVector

  def add2Vector(a:Vector, b:Vector):Vector = {
    val zip = a.toArray.zip(b.toArray)
    new DenseVector(zip.map(x => x._1+x._2))
  }

  val sumVector = data.reduce((a,b) => add2Vector(a,b))
  val count = data.count()
  val gravityVector = new DenseVector(sumVector.toArray.map(x => x/count))

  return (gravityVector, count)
}

// 计算 SSR
import org.apache.spark.mllib.linalg.Vector
def ComputeSSR(gravityVector:Vector, clusterCenters: Array[Vector]):Double = {
  def ComputeOneCenter(gravityVector:Vector, point:Vector):Double = {
    val zip = gravityVector.toArray.zip(point.toArray)
    val gapSquare = zip.map(x => {val gap = x._1 - x._2; gap*gap}).reduce((a,b)=> a+b)
    scala.math.sqrt(gapSquare)
  }

  clusterCenters.map(x => ComputeOneCenter(gravityVector, x)).reduce((a,b) => a+b)
}

// 根据 N(总样本个数), K, SSR 和 WSSSE 计算KMeans聚类的评价
// 区间应该是从 1到无穷大, 认为只要达到3就很好了
def ComputeEvaluator(n:Long, k:Long, SSR:Double, WSSSE:Double):Double = {
  if (((n-k)<=0) || (k <=1 )) return Long.MaxValue.toDouble //1.0/0 Infinity
  return (WSSSE/(n-k)) / (SSR/(k-1))
}

// ---------------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------
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

  // 数据集质心 和 数量
  val (gravityVector, dataCount) = ComputeGravityCenter(data)

  // 检查数据集的数量, 如果 count不大于K,则会出现错误
  /*
  java.lang.ArrayIndexOutOfBoundsException: -1
    at org.apache.spark.mllib.clustering.LocalKMeans$$anonfun$kMeansPlusPlus$1.apply$mcVI$sp(LocalKMeans.scala:62)
    at scala.collection.immutable.Range.foreach$mVc$sp(Range.scala:141)
    at org.apache.spark.mllib.clustering.LocalKMeans$.kMeansPlusPlus(LocalKMeans.scala:49)
    at org.apache.spark.mllib.clustering.KMeans$$anonfun$20.apply(KMeans.scala:297)
    at org.apache.spark.mllib.clustering.KMeans$$anonfun$20.apply(KMeans.scala:294)
    */
  if (minK > dataCount) {    // 此时不能运行后续命令,否则回报错!
    DEBUGprint(s"minK:${minK} > count:${dataCount}")
    return Account(0,0,Nil)
  }

  import org.apache.spark.rdd.RDD
  import org.apache.spark.mllib.linalg.Vector
  // ****************************************************************************
  // 对某个K进行KMeans聚类
  def evalWSSSEOfK_inner(dataCount:Long, gravityVector:Vector, data: RDD[Vector], k:Int, maxIterations: Int = 20, x:Account):Account = {
    // metric信息
    val timeBegin = new java.util.Date()

    /*
    // 随机数模拟
    val WSSSEOfK = scala.util.Random.nextDouble //% 9999    // 模拟随机数
    val modelOfK: org.apache.spark.mllib.clustering.KMeansModel = null
    */
    // 执行KMeans算法
    import org.apache.spark.mllib.clustering.KMeans

    val clusteringKM = new KMeans()
    clusteringKM.setK(k)
    val model = clusteringKM.run(data)
    val WSSSEOfK = model.computeCost(data)
    //val modelOfK = KMeans.train(data, k, maxIterations)
    //val WSSSEOfK = modelOfK.computeCost(data)

    // metric信息
    val timeEnd = new java.util.Date()
    val clusterCenters = model.clusterCenters
    val SSR = ComputeSSR(gravityVector, clusterCenters)
    val evaluator = ComputeEvaluator(dataCount, k, SSR, WSSSEOfK)

    // metric对象
    val newMetric = Metric(k, maxIterations, WSSSEOfK, SSR, evaluator, timeBegin, timeEnd, model)
    // 添加到 metricList
    val newMetricList = newMetric :: x.metricList
    // 函数返回值
    new Account(x.counter + 1, x.tryCounter + 1, newMetricList)
  }
  // 部分应用（Partial application）
  val evalWSSSEOfK = evalWSSSEOfK_inner(dataCount, gravityVector, _: RDD[Vector], _:Int, _:Int, _:Account)

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

    // 从 curMetricList 中找到WSSSE最小的三个
    //val sortedMetricList = curMetricList.sortBy(i => i.WSSSE)
    // 从 curMetricList 中找到 evaluator 最小的三个
    val sortedMetricList = curMetricList.sortBy(i => i.evaluator)

    // 找到WSSSE最小的那个, 获得其k值
    val kValueOfMinEvaluator = sortedMetricList(0).k

    // 找到 kValueOfMinWSSSE 的前后两个相邻k
    val sortedKList = curMetricList.map(x => x.k).sorted    //取出目前计算的k,并排序
    val index_MinEvaluator = sortedKList.indexOf(kValueOfMinEvaluator)    // 找到index (WSSSE最小的那个)

    DEBUGprint("[runHelper] 即将尝试再次分裂" +
      "\n\n\t curMetricList：  \n " + Account(0,0,curMetricList).toRecordOfMetricList +
      "\n\n\t sortedMetricList(by WSSSE)：  \n " + Account(0,0,sortedMetricList).toRecordOfMetricList +
      "\n\n\t sortedKList(by K)：  \n" + sortedKList)

    // -------------------------------
    // 第一种情况： 在最前面 ==> 分裂一次
    // 若 index_MinEvaluator 为0, 第一个最小
    if ( index_MinEvaluator == 0)    {
      val type01_split1 = new KTriangle(
        sortedKList(index_MinEvaluator),
        sortedKList(index_MinEvaluator + 1))

      DEBUGprint("[runHelper] 第一种情况： 在最前面 ==> 分裂一次  \n\t " + type01_split1)
      // 判断是否需要递归运行
      return runHelper(type01_split1,curAccount)
    }

    // 第二种情况： 在最后面 ==> 分裂一次
    // 若 index_MinEvaluator 为(sortedKList.size -1), 最后一个最小
    if ( index_MinEvaluator == (sortedKList.size -1)) {
      val type02_split1 = new KTriangle(
        sortedKList(index_MinEvaluator -1),
        sortedKList(index_MinEvaluator))

      DEBUGprint("[runHelper] 第二种情况： 在最后面 ==> 分裂一次  \n\t " + type02_split1)
      // 判断是否需要递归运行
      return runHelper(type02_split1,curAccount)
    }

    // -------------------------------
    // 第三种情况： 在中间 ==> 分裂两次
    {
      // 分裂1
      val littleNeighbour = index_MinEvaluator -1
      val bigNeighbour = index_MinEvaluator +1

      val type03_split1 = new KTriangle(
        sortedKList(littleNeighbour),
        sortedKList(index_MinEvaluator))

      DEBUGprint("[runHelper] 第三种情况： 在中间 ==> 分裂两次  part1  \n\t " + type03_split1)
      // 判断是否需要递归运行
      val split1Account = runHelper(type03_split1,curAccount)

      // 分裂2
      val type03_split2 = new KTriangle(
        sortedKList(index_MinEvaluator),
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
//     sortedType,排序方式    0-默认,即计算k的顺序; 1-按照k从小到大排序
def writeAccount2HDFS(account: Account, sortedType:Int = 0, taskName:String = "feelingLucky") = {
  // 取得任务的开始日期,最佳K，最佳K对应的模型
  val dateString = account.getBeginDateString()
  val perfectK = account.getPerfectKandModel._1
  val perfectModel = account.getPerfectKandModel._2

  // 文件路径
  val filepath = "/user/spark/clustering/" + dateString

  val filenameMetric = taskName + "_kmeans_" + perfectK + "_metrics"
  val hdfsMetricInfoPath = filepath + "/" + filenameMetric

  val filenameClusterCenters = taskName + "_kmeans_" + perfectK + "_clusterCenters"
  val hdfsClusterCentersInfoPath = filepath + "/" + filenameClusterCenters

  // ------------------------------------------------------------------------
  // 度量数据
  // metrics
  val hdfsPathsOfMetric = Tuple2(hdfsMetricInfoPath + "_unsorted", hdfsMetricInfoPath + "_sorted")

  // 日期格式化
  val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"

  // 处理排序方式
  // (1) 默认排序
  if (sortedType == 0 ) {
    val distData = sc.parallelize(
      account.metricList.reverse.
        map(y => s"${y.k}, ${y.maxIterations}, ${y.WSSSE}, ${dateFormat.format(y.begin)}, ${dateFormat.format(y.end)}, ${y.model}")
    )
    distData.saveAsTextFile(hdfsPathsOfMetric._1)
  }
  // (2) 按照WSSSE大小排序
  if (sortedType == 1 )  {
    val distData = sc.parallelize(
      account.metricList.sortBy(x => x.WSSSE).
        map(y => s"${y.k}, ${y.maxIterations}, ${y.WSSSE}, ${dateFormat.format(y.begin)}, ${dateFormat.format(y.end)}, ${y.model}")
    )
    distData.saveAsTextFile(hdfsPathsOfMetric._2)
  }
  // (3) 处理排序方式 两种都写入
  if (sortedType == 2 ) {
    // 默认排序
    val distData = sc.parallelize(
      account.metricList.reverse.
        map(y => s"${y.k}, ${y.maxIterations}, ${y.WSSSE}, ${dateFormat.format(y.begin)}, ${dateFormat.format(y.end)}, ${y.model}")
    )
    distData.saveAsTextFile(hdfsPathsOfMetric._1)

    // 按照WSSSE大小排序
    val distData2 = sc.parallelize(
      account.metricList.sortBy(x => x.WSSSE).
        map(y => s"${y.k}, ${y.maxIterations}, ${y.WSSSE}, ${dateFormat.format(y.begin)}, ${dateFormat.format(y.end)}, ${y.model}")
    )
    distData.saveAsTextFile(hdfsPathsOfMetric._2)
  }

  // ------------------------------------------------------------------------
  // 最佳K的中心数据
  // 将簇中心转换为RDD,并写入文件
  val distData = sc.parallelize(perfectModel.clusterCenters)

  // 写入文件
  distData.saveAsTextFile(hdfsClusterCentersInfoPath)

  // ------------------------------------------------------------------------
  // 函数返回值
  Tuple2(hdfsPathsOfMetric, hdfsClusterCentersInfoPath)
}

def writeAccount2LocalFile(account: Account, taskName:String = "feelingLucky", dir:String = null) = {
  // 取得任务的开始日期,最佳K，最佳K对应的模型
  //val dateString = account.getBeginDateString()
  val perfectK = account.getPerfectKandModel._1
  val perfectModel = account.getPerfectKandModel._2

  val rootpath = if (dir != null) dir else System.getenv().get("PWD")
  // 文件路径
  val filenameMetric = taskName + "_kmeans_" + perfectK + "_metrics"
  val fullfilePrefix = rootpath + "/" + filenameMetric

  val filenameClusterCenters = taskName + "_kmeans_" + perfectK + "_clusterCenters"
  val hdfsClusterCentersInfoPath = rootpath + "/" + filenameClusterCenters

  // 写函数
  def writeFunction(data:List[String], fullname:String) = {
    val file = new java.io.File(fullname)
    val flag = file.createNewFile()  // 创建文件
    val filewriter = new java.io.FileWriter(file)

    //执行写
    data.foreach(x => filewriter.write(x+"\n"))

    filewriter.flush()
    filewriter.close()
  }

  // ------------------------------------------------------------------------
  // 度量数据
  // metrics
  val fullfiles = List(fullfilePrefix + "_executeOrder" + ".csv",
    fullfilePrefix + "_sortedByWSSSE" + ".csv",
    fullfilePrefix + "_sortedBySSR" + ".csv",
    fullfilePrefix + "_sortedByEvaluator" + ".csv")

  // 日期格式化
  val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"

  // 处理排序方式 两种都写入
  // 默认排序
  val distData1 = account.metricList.reverse.map(y => y.toRecord)
  writeFunction(distData1, fullfiles(0))

  // 按照WSSSE排序
  val distData2 = account.metricList.sortBy(i => i.WSSSE).map(y => y.toRecord)
  writeFunction(distData2, fullfiles(1))

  // 按照 SSR 排序
  val distData3 = account.metricList.sortBy(i => i.SSR).map(y => y.toRecord)
  writeFunction(distData3, fullfiles(2))

  // 按照 evaluator 排序
  val distData4 = account.metricList.sortBy(i => i.evaluator).map(y => y.toRecord)
  writeFunction(distData4, fullfiles(3))
  // ------------------------------------------------------------------------
  /*    // 最佳K的中心数据
      val centers = perfectModel.clusterCenters
      val length = centers.length
      val distData = for( i<- Range(0, length)) yield {
        val zeroValue = i
        i+","+centers(i).toArray.map(x=>x.toString).reduce((a,b) => a.toString+","+b.toString)
      }

      // 写入文件
      writeFunction(distData.toList, hdfsClusterCentersInfoPath)*/

  // ------------------------------------------------------------------------
  // 函数返回值
  fullfiles
}