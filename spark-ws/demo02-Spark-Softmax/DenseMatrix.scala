package spark.ml.utils

import java.util.Random

/**
 * 密集矩阵，用于封装模型参数
 */
class DenseMatrix(rowNum: Int, columnNum: Int) extends Serializable{

  var matrix = Array.ofDim[Double](rowNum, columnNum)
  
  def rows(): Int = {
    rowNum
  }
  
  def columns(): Int = {
    columnNum
  }

  def apply(i: Int): Array[Double] = {
    matrix(i)
  }

  /**
   * 构造0矩阵
   */
  def zeros(): DenseMatrix = {
    for (i <- 0 until rowNum) {
      for (j <- 0 until columnNum) {
        matrix(i)(j) = 0
      }
    }
    this
  }

  /**
   * 随机初始化矩阵的值
   */
  def rand(): DenseMatrix = {
    val rand = new Random(42)
    for (i <- 0 until rowNum) {
      for (j <- 0 until columnNum) {
        matrix(i)(j) = rand.nextDouble
      }
    }
    this
  }

  def set(i: Int, j: Int, value: Double) {
    matrix(i)(j) = value
  }

  def get(i: Int, j: Int): Double = {
    matrix(i)(j)
  }

  def +(scalar: Double): DenseMatrix = {
    for (i <- 0 until rowNum) yield {
      for (j <- 0 until columnNum) yield {
        matrix(i)(j) += scalar
      }
    }
    this
  }

  def -(scalar: Double): DenseMatrix = {
    this - scalar
  }

  def +(other: DenseMatrix): DenseMatrix = {
    for (i <- 0 until rowNum) yield {
      for (j <- 0 until columnNum) yield {
        matrix(i)(j) += other(i)(j)
      }
    }
    this
  }

  def -(other: DenseMatrix): DenseMatrix = {
    this + (other * (-1))
  }

  def *(scalar: Double): DenseMatrix = {
    for (i <- 0 until rowNum) yield {
      for (j <- 0 until columnNum) yield {
        matrix(i)(j) *= scalar
      }
    }
    this
  }
}

object DenseMatrix {

  def main(args: Array[String]): Unit = {}

}