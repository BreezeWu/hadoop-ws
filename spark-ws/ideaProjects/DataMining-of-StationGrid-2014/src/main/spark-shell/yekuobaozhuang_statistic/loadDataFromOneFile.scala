/**
 * Created by HongZe.Wu on 10/21/14.
 */

val datasetId = 2012 // 2010 //2012

// 文件路径
val inputFile_org = "file:///home/hadoop/dm-data/yekuobaozhuang-maxP/org"
val inputFile_org_windows = inputFile_org + s"/yekuobaozhuang-${datasetId}-windows.csv" //"/yekuobaozhuang-2010-windows.csv"
val inputFile_org_linux = inputFile_org + s"/yekuobaozhuang-${datasetId}-linux.csv"//"/yekuobaozhuang-2010-linux.csv"
val inputFile_percent = "file:///home/hadoop/dm-data/yekuobaozhuang-maxP/convert2percent"
val inputFile_org_sample = "file:///home/hadoop/dm-data/yekuobaozhuang-maxP/org_sample"
val inputFile_org_sample_01 = inputFile_org_sample + "_0.01"

// ----------------------------------------------------------------------------
// 读取原始数据(读单个文件)
val inputFile = inputFile_org_linux
val linesRdd = sc.textFile(inputFile) // 读某一个文件,一行一条记录
// 按照csv格式解析: 这是每行生成一个CSVReader的方法
import java.io.StringReader
import au.com.bytecode.opencsv.CSVReader
val csvRdd_javaList = linesRdd.map(x => {
  val reader = new CSVReader(new StringReader(x))
  val csvLine = reader.readAll()
  csvLine
})
// 将Java List转换为Scala List: x => x.toList
import scala.collection.JavaConversions._
val csvRdd = csvRdd_javaList.map(x => {
  val scalaList = x.toList
  scalaList(0)  // 每行一个CSVReader,所以只需要取第一行数据
})
// .map(x => x(0))

// ----------------------------------------------------------------------------
// 定义列及其位置
val g_columns_of_raw = List("ROW_ID", "MP_ID", "MP_NO", "MP_NAME", "MP_ADDR", "VOLT_CODE", "APP_DATE", "RUN_DATE", "ORG_NO", "MR_SECT_NO", "LINE_ID", "TG_ID", "STATUS_CODE", "CONS_ID", "MP_CAP", "CONS_NO", "CONS_NAME", "ELEC_ADDR", "TRADE_CODE", "ELEC_TYPE_CODE", "CONTRACT_CAP", "RUN_CAP", "BUILD_DATE", "PS_DATE", "CANCEL_DATE", "DUE_DATE", "YM", "SUMPQ", "MAXDL").map(x => x.toUpperCase)
def colsIndexOf_raw(id:String):Int = {
  return g_columns_of_raw.indexOf(id.toUpperCase)
}

// 抽样
//val sampleRdd = linesRdd.sample(false, 0.01)
//sampleRdd.saveAsTextFile(inputFile_org_sample_01)
