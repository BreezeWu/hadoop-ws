import org.apache.spark.SparkContext

/**
 * Created by HongZe.Wu on 1/22/15.
 */

val sc:SparkContext = new SparkContext();

val path_originalData = "/mnt/win-f/工作目录2014/职场之数据挖掘/数据挖掘_任务.电力/DM之电力.北京国网/201501_北京电能质量系统监测项目/01-电能质量综合评价/电能质量数据-当期-csv格式数据"
val list_filename = List("1电网频率合格率.csv", "2电网电压合格率.csv", "3供电电压合格率.csv", "4输变电系统可靠率.csv", "5供电可靠率.csv")

val file_data1 = "1电网频率合格率.csv"
val file_data2 = "2电网电压合格率.csv"
val file_data3 = "3供电电压合格率.csv"
val file_data4 = "4输变电系统可靠率.csv"
val file_data5 = "5供电可靠率.csv"
val file_metadata_datelist = "日期2014.csv" //"日期12-15.csv"
val file_metadata_idInfo = "meta单位编码与名称.csv"

val list_originalRDD = list_filename.map( filename:String => {
  val fullpath = "file://" + path_originalData + "/" + filename
  val dataRdd = sc.textFile(fullpath)
  dataRdd
})

import java.io.File
val f = new File(fullpath)


