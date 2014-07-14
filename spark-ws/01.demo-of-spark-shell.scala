// ----------------------------------------------------------------------------
// 定义函数
def funcWordCount(fileName:String, filePath:String = ".", fileDelimiter:String = " ") = {
	val textFile = sc.textFile(filePath + "/" + fileName)
	val listOfWords = textFile.flatMap(_.split(fileDelimiter))

	// listOfWords.map(x -> 1)
	val words_counts = listOfWords.map(word => (word,1)).reduceByKey(_ + _)

	// 输出文件为输入文件路径尾部添加output
	val outputFilePath = if (filePath(filePath.length - 1) != '/') filePath else filePath.substring(0,filePath.length - 1 - 1)
	// 写入文件
	val resultFile = s"$outputFilePath-output/wordcount-result-${scala.util.Random.nextInt}"
	words_counts.saveAsTextFile(resultFile)	
	resultFile
}

// 文件
val fileName = "micmiu-01.txt"
val filePath = "input/words-data" // 用户默认目录就是 "hdfs://master-hadoop:9000/user/hadoop/", 可以省略

# 调用函数清空结果目录
#	 hdfs dfs -rm -R output/wordcount-of-*
funcWordCount(fileName, filePath)
# 查看结果
# 	hdfs dfs -ls -R input/words-data/output-wordcount-result-11865972
// ----------------------------------------------------------------------------
// 统计超大数据
val fileName = "*"
val filePath = "/user/hive/warehouse/bigdata_arc_volume_perm_s01/"
val fileDelimiter = "^A"
funcWordCount(fileName, filePath, fileDelimiter)

// ----------------------------------------------------------------------------
// 能正常处理 ctrl+A分割的数据么?

// hive的默认分割符是CTRL+A,  即SOH,Start of heading,十进制的1,8进制的01,16进制的0x01
// ASCII Control Codes 请参见 
//	http://academic.evergreen.edu/projects/biophysics/technotes/program/ascii_ctrl.htm
//	或 http://techurls.tripod.com/dha.htm
val delimiterInt:Int = 0x01;
val delimiterChar:Char = delimiterInt.toChar
val delimiter:String = delimiterChar.toString;

val fileName = "part-m-00000"
val filePath = "/user/hive/warehouse/bigdata_power_steal_pery_s01/"
val fileDelimiter = delimiter

val textFile = sc.textFile(filePath + "/" + fileName)
val listOfWords = textFile.flatMap(_.split(fileDelimiter))

// listOfWords.map(x -> 1)
val words_counts = listOfWords.map(word => (word,1)).reduceByKey(_ + _)
words_counts.collect()

// reduceByKey(_ + _)等价于 reduceByKey((a, b) => a + b)
val words_counts2 = listOfWords.map(word => (word,1)).reduceByKey((a, b) => a + b)
words_counts2.collect()
// ----------------------------------------------------------------------------
//	http://blog.csdn.net/sunflower_cao/article/details/26708797

val inFile = sc.textFile("dm-data/spark-data/spam.data");  
/*
// 或者
val file = sc.addFile("spam.data")  
import org.apache.spark.SparkFiles;  
val inFile = sc.textFile(SparkFiles.get("spam.data"))  
*/

inFile.first()  
val nums = inFile.map(x => x.split(' ').map(_.toDouble))  
nums.first()  



