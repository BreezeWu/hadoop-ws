// package spark.ml.classification

import java.util.Random  
import scala.collection.mutable.HashMap  
import scala.io.Source  
import org.apache.spark.SparkContext  
import org.apache.spark.rdd.RDD;  
import org.apache.spark.util.Vector  
import java.lang.Math  
import org.apache.spark.broadcast.Broadcast  

//import spark.ml.utils.SparserVector
//import SparserVector;
  
object SparseLR {
  val labelNum = 2; // 类别数  
  val dimNum = 124; // 维度  
  val iteration = 10; // 迭代次数  
  val alpha = 0.1 // 迭代步长  
  val lambda = 0.1  
  val rand = new Random(42)  
  var w = Vector(dimNum, _ => rand.nextDouble) //用随机数初始化参数  
  
  /** 
   * 定义一个数据点 
   */  
  case class DataPoint(x: SparserVector, y: Int)  
  
  /** 
   * 解析一个训练样本，构造DataPoint结构 
   * @param 训练样本 
   */  
  def parsePoint(line: String): DataPoint = {  
    var features = new SparserVector(dimNum)  
    val fields = line.split(" ")  
    val y = fields(0).toInt  
    fields.filter(_.contains(":")).foreach(f => {  
      val feature = f.split(":")  
      features.insert(feature(0).toInt, feature(1).toDouble)  
    })  
    DataPoint(features, y)  
  }  
  
  /** 
   * 读样本文件，构造训练数据 
   */  
  def genearteDataPoints(filename: String): Array[DataPoint] = {  
    val dataPoints = Source.fromFile(filename).getLines.map(parsePoint).toArray  
    dataPoints  
  }  
  
  /**
   * sigmod函数
   * */
  def sigmod(x: SparserVector): Double = {  
    val features = x.elements  
    val s = features.keySet.map(k => w(k) * features.get(k).get).reduce(_ + _)  
    1 / (1 + Math.exp(-s))  
  }  
  
  /** 
   * 根据样本训练参数 
   */  
  def train(sc: SparkContext, dataPoints: RDD[DataPoint]) {  
    val sampleNum = dataPoints.count  
  
    //开始迭代  
    for (i <- 0 until iteration) {  
      val g = dataPoints.map(p => p.x * (sigmod(p.x) - p.y)).reduce(_ + _) + lambda * w  
      w -= alpha * g  
  
//      println("iteration " + i + ": g = " + g)  
//      println("iteration " + i + ": w = " + w)  
    }  
  }  
  
  /** 
   * 根据训练出的参数进行预测 
   */  
  def predict(dataPoints: RDD[DataPoint]): Double = {  
    val correct = dataPoints.map(p => {  
      val label = if (sigmod(p.x) > 0.5) 1 else 0  
      if (label == p.y) 1 else 0  
    }).reduce(_ + _)  
  
    (correct * 1.0) / dataPoints.count  
  }  
  
  def main(args: Array[String]): Unit = {  
    //val trainfile = "data/a8a.train";  
    val trainfile = "dm-data/spark-data/a8a";
  
    //val sc = new SparkContext(args(0), "LR")  
    //val sc = new SparkContext("local", "LR")  
    val sc = new SparkContext("yarn-cluster", "LR")
    val trainset = sc.textFile(trainfile, 2).map(parsePoint).cache  
  
    train(sc, trainset)  
  
    //val testfile = "data/a8a.test";  
    val testfile = "dm-data/spark-data/a8a.t";
    val testset = sc.textFile(testfile, 2).map(parsePoint).cache  
    val accuracy = predict(testset)  
  
    println(accuracy)  
  }  
}  
