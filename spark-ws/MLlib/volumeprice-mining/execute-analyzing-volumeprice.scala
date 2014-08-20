// ----------------------------------------------------------------------------
/**
 * 执行分析
 * 
 * 阶梯电量电价表, parsedData_volumeprice_of_ladder
 * 分时电量电价表, parsedData_volumeprice_of_ts
 *
 * 引入依赖
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/hiveselect-of-volumeprice.scala
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/transformations-of-volumeprice.scala
 *
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/create-rdd-of-volumeprice.scala
 * 引入
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/execute-analyzing-volumeprice.scala
 */

// ----------------------------------------------------------------------------
// 思路一: 共现矩阵 协同分析

// ----------------------------------------------------------------------------
// 思路二: 

// sc is an existing SparkContext.
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
// createSchemaRDD is used to implicitly convert an RDD to a SchemaRDD.
import sqlContext.createSchemaRDD

// Define the schema using a case class.
// Note: Case classes in Scala 2.10 can support only up to 22 fields. To work around this limit,
// you can use custom classes that implement the Product interface.
case class Person(name: String, age: Int)

// Create an RDD of Person objects and register it as a table.
// 默认是 hdfs
//val people = sc.textFile("~/workspace_github/hadoop-ws/spark-ws/MLlib/result-data/s98_L2k20_clusterCenters/s98_L2k20_GoodM1_Ladder_clusterCenters.csv")
// 本地
val people = sc.textFile("file://home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/result-data/s98_L2k20_clusterCenters/s98_L2k20_GoodM1_Ladder_clusterCenters.csv")
val people = sc.textFile("~/workspace_github/hadoop-ws/spark-ws/MLlib/result-data/s98_L2k20_clusterCenters/s98_L2k20_GoodM1_Ladder_clusterCenters.csv").map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt))
people.registerTempTable("people")

// SQL statements can be run by using the sql methods provided by sqlContext.
val teenagers = sqlContext.sql("SELECT name FROM people WHERE age >= 13 AND age <= 19")

// The results of SQL queries are SchemaRDDs and support all the normal RDD operations.
// The columns of a row in the result can be accessed by ordinal.
teenagers.map(t => "Name: " + t(0)).collect().foreach(println)


