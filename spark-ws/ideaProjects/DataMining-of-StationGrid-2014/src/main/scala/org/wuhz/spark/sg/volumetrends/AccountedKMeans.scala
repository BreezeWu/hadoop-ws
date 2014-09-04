package org.wuhz.spark.sg.volumetrends

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.rdd.RDD

/**
 * Created by hadoop on 9/4/14.
 */

// 聚类Metric信息
case class Metric(k:Int,
                  maxIterations: Int = 20,
                  WSSSE: Double,
                  begin: java.util.Date,
                  end: java.util.Date,
                  model: org.apache.spark.mllib.clustering.KMeansModel
                   ) {
  val dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")    // "dd-MM-yyyy" "yyyy-MM-dd" "yyyy-MM-dd-HH-mm-ss"
  def toRecord = k +","+ maxIterations +","+ WSSSE +","+ dateFormat.format(begin) +","+ dateFormat.format(end) +","+ model
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

    if (0==this.metricList.size) return Tuple2(0, null)

    // Account中的metricList按照WSSSE从小到大排序
    val sortedMetricList = this.metricList.sorted

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

// 三元组, 但是只能使用两个参数创建  ^_^
/*
case class KTriangle private(low:Int, high: Int, median: Int)
// 在 spark-shell中"object KTriangle"和"case class"需要使用 :paster 特殊处理才可以
object KTriangle {
  val getMin = (x: Int, y: Int) => if (x > y) y else x
  val getMax = (x: Int, y: Int) => if (x > y) x else y

  def apply(x: Int, y: Int) = new KTriangle(getMin(x,y),getMax(x,y), (x+y)/2)
}*/
// 在 spark-shell中"object KTriangle"和"case class"需要使用 :paster 特殊处理才可以
// 所以改为下面语句, 同时生成时,需要添加 new
// 三元组, 但是只能使用两个参数创建  ^_^
case class KTriangle private(low:Int, high: Int, median: Int) {
  def this(x:Int, y: Int) = {
    this(
      if (x > y) y else x,
      if (x > y) x else y,
      (x+y)/2)
  }
}

object objAccountedKMeans {
  // ****************************************************************************
  // 对某个K进行KMeans聚类
  def evalWSSSEOfK(data: RDD[org.apache.spark.mllib.linalg.Vector], k:Int, maxIterations: Int = 20, x:Account):Account = {
    // metric信息
    val timeBegin = new java.util.Date()

    /*
    // 随机数模拟
    val WSSSEOfK = scala.util.Random.nextDouble //% 9999    // 模拟随机数
    val modelOfK: org.apache.spark.mllib.clustering.KMeansModel = null
    */
    // 执行KMeans算法
    // 检查数据集的数量, 如果 count不大于K,则会出现错误
    /*
    java.lang.ArrayIndexOutOfBoundsException: -1
      at org.apache.spark.mllib.clustering.LocalKMeans$$anonfun$kMeansPlusPlus$1.apply$mcVI$sp(LocalKMeans.scala:62)
      at scala.collection.immutable.Range.foreach$mVc$sp(Range.scala:141)
      at org.apache.spark.mllib.clustering.LocalKMeans$.kMeansPlusPlus(LocalKMeans.scala:49)
      at org.apache.spark.mllib.clustering.KMeans$$anonfun$20.apply(KMeans.scala:297)
      at org.apache.spark.mllib.clustering.KMeans$$anonfun$20.apply(KMeans.scala:294)
      */
    val count = data.count
    if (k > count) {    // 此时不能运行后续命令,否则回报错!
      // 返回原 Account, 而原 Account 中 metricList可能为Nil
      return x
    }

    val clusteringKM = new KMeans()
    clusteringKM.setK(k)
    val model = clusteringKM.run(data)
    val WSSSEOfK = model.computeCost(data)
    //val modelOfK = KMeans.train(data, k, maxIterations)
    //val WSSSEOfK = modelOfK.computeCost(data)

    // metric信息
    val timeEnd = new java.util.Date()

    // metric对象
    val newMetric = Metric(k, maxIterations, WSSSEOfK, timeBegin, timeEnd, model)
    // 添加到 metricList
    val newMetricList = newMetric :: x.metricList
    // 函数返回值
    new Account(x.counter + 1, x.tryCounter + 1, newMetricList)
  }
}
