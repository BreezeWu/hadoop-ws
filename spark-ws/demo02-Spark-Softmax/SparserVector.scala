package spark.ml.utils

import scala.collection.mutable.HashMap
import org.apache.spark.util.Vector

/**
 * 定义一个基于HashMap的稀疏向量
 */
class SparserVector(dimNum: Int) {
  var elements = new HashMap[Int, Double]

  def insert(index: Int, value: Double) {
    elements += index -> value;
  }

  def *(scale: Double): Vector = {
    var x = new Array[Double](dimNum)
    elements.keySet.foreach(k => x(k) = scale * elements.get(k).get);
    Vector(x)
  }
}

object SparserVector {

  def main(args: Array[String]): Unit = {}

}