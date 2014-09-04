package org.wuhz.spark.demo

/**
 * Created by hadoop on 9/4/14.
 */
import org.apache.spark.SparkContext._
import org.apache.spark.{SparkConf, SparkContext}

/** Computes an approximation to pi */
object WordCount {
  def main(args: Array[String]) {
    println("-------------------------------------------------------------")
    println(">>>>>> 不排序")

    unsortedWordCount(args);

    println("不排序 <<<<<<")
    println("-------------------------------------------------------------")
    println(">>>>>> 排序")

    sortedWordCount(args);

    println("排序 <<<<<<")
    println("-------------------------------------------------------------")
  }

  def unsortedWordCount(args: Array[String]) {
    if (args.length == 0) {
      System.err.println("Usage: WordCount1 <file1>")
      System.exit(1)
    }

    val conf = new SparkConf().setAppName("WordCount1")
    val sc = new SparkContext(conf)
    sc.textFile(args(0)).flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _).take(10).foreach(println)
    sc.stop()
  }

  def sortedWordCount(args: Array[String]) {
    if (args.length == 0) {
      System.err.println("Usage: WordCount2 <file1>")
      System.exit(1)
    }

    val conf = new SparkConf().setAppName("WordCount2")
    val sc = new SparkContext(conf)
    sc.textFile(args(0)).flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _).map(x=>(x._2,x._1)).sortByKey(false).map(x=>(x._2,x._1)).take(10).foreach(println)
    sc.stop()
  }
}