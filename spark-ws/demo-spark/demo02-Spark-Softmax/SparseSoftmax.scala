package spark.ml.classification

import java.util.Random
import scala.collection.mutable.HashMap
import scala.io.Source
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.Vector
import java.lang.Math
import org.apache.spark.broadcast.Broadcast

import spark.ml.utils.SparserVector
import spark.ml.utils.DenseMatrix

object SparseSoftmax {
  val labelNum = 10; // 类别数  
  val dimNum = 781; // 维度  
  val iteration = 20; // 迭代次数  
  val alpha = 0.1 // 迭代步长  
  val lambda = 0.1
  val rand = new Random(42)

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
   * exp(w dot x)
   */
  def prob(x: SparserVector, w: Array[Double]): Double = {
    val features = x.elements
    val s = features.keySet.map(k => w(k) * features.get(k).get).reduce(_ + _)
    Math.exp(s)
  }

  def h(x: SparserVector, w: DenseMatrix): Array[Double] = {
    val matrix = w.matrix
    val probs = matrix.map(m => prob(x, m))
    val sum = probs.reduce(_+_)
    probs.map(p=>p/sum).toArray
  }

  /**
   * 计算梯度
   */
  def gradient(p: DataPoint, wb: Broadcast[DenseMatrix]): DenseMatrix = {
    val w = wb.value
    val rowNum = w.rows
    val columnNum = w.columns

    var g = new DenseMatrix(rowNum, columnNum)
    for (j <- 1 to labelNum) {
      val y = if (p.y == j) 1 else 0
      val v = p.x * (y - prob(p.x, w(j - 1))) //here v is Vector, however g(j) is Array[Double], any better way?
      for (k <- 0 until columnNum)
        g(j-1)(k) = v(k)
    }
    g
  }

  /**
   * 根据样本训练参数
   */
  def train(sc: SparkContext, dataPoints: RDD[DataPoint]): DenseMatrix = {
    var w = new DenseMatrix(labelNum, dimNum).rand
    val wb = sc.broadcast(w)
    //开始迭代  
    for (i <- 0 until iteration) {
      val g = dataPoints.map(p => gradient(p, wb)).reduce(_ + _) + w * lambda
      w -= g * alpha
    }
    w
  }

  def predict(w: DenseMatrix, dataPoints: RDD[DataPoint]): Double = {
    def label(probs: Array[Double]): Int = {
      var y = 1
      var maxp = 0.0
      for (i <- 0 until probs.length) {
        if (probs(i) > maxp) {
          maxp = probs(i)
          y = i + 1
        }
      }
      y
    }

    val correct = dataPoints.map(p => {
      val y = label(h(p.x, w))
      if (y == p.y) 1 else 0
    }).reduce(_ + _)

    (correct * 1.0) / dataPoints.count
  }

  def main(args: Array[String]): Unit = {
    val trainfile = "data/mnist/mnist";

    val sc = new SparkContext("local", "LR")
    val trainset = sc.textFile(trainfile, 2).map(parsePoint).cache
    val w = train(sc, trainset)

    val testfile = "data/mnist/mnist.t";
    val testset = sc.textFile(testfile, 2).map(parsePoint).cache
    val accuracy = predict(w, testset)

    println(accuracy)
  }

}