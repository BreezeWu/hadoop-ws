import java.io.StringReader

import au.com.bytecode.opencsv.CSVReader

/**
 * Created by HongZe.Wu on 10/21/14.
 */

// 文件路径
val inputFile_org = "file:///home/hadoop/dm-data/yekuobaozhuang-maxP/org"
val inputFile_org_windows = inputFile_org + "/yekuobaozhuang-windows.csv"
val inputFile_org_linux = inputFile_org + "/yekuobaozhuang-linux.csv"
val inputFile_percent = "file:///home/hadoop/dm-data/yekuobaozhuang-maxP/convert2percent"
val inputFile_org_sample = "file:///home/hadoop/dm-data/yekuobaozhuang-maxP/org_sample"
val inputFile_org_sample_01 = inputFile_org_sample + "_0.01"

// ----------------------------------------------------------------------------
// 读取原始数据(读目录)
val inputFile = inputFile_org //inputFile_org_sample_01 //inputFile_org
val inputRdd = sc.wholeTextFiles(inputFile) // 读某个目录,一个文件一条记录
val linesRdd = inputRdd.map(x => x._2).flatMap(x =>x.split("\n")) // 转换为行
// 按照csv格式解析
// 转换为csv格式,并去掉每个值两头的引号
def trim(s:String):String = {
  val first = 0
  val last = s.length - 1
  val zip = s.zipWithIndex
  val yieldResult = for(x <- zip;
                        val v = x._1;
                        val k = x._2;
                        if(k != first && k != last)
  ) yield v

  val newString = if(0 == yieldResult.size) "" else new String(yieldResult.toArray)
  newString
}
val csvRdd = linesRdd.map(x => x.split(",").map(y => trim(y)))
csvRdd.first  // 执行此句,触发action, 于是前面的语句可能会内存溢出
// ----------------------------------------------------------------------------
/*
// 另一种处理方法
import java.io.StringReader
import au.com.bytecode.opencsv.CSVReader
// case class Person(name: String, favoriteAnimal: String)
val result = inputRdd.flatMap{ case (_, txt) =>
  val reader = new CSVReader(new StringReader(txt));

  // reader.readAll().map(x => Person(x(0), x(1)))  // java.util.List 没有map方法

  val javaList = reader.readAll() // 可能会导致内存溢出,启动spark-shell时增加内存,如: SPARK_DRIVER_MEMORY=3G spark-shell
  import scala.collection.JavaConversions._
  javaList.toArray
  //val scalaList = javaList.toArray[String]().map(x => Person(x(0), x(1)))
  //scalaList
}
*/
// ----------------------------------------------------------------------------
// 定义列及其位置
// ... 其他内容请参见 "loadDataFromOneFile.scala"