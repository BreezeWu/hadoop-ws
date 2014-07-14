// ----------------------------------------------------------------------------
// Spark SQL Programming Guide
// RDDs
// 	http://spark.apache.org/docs/latest/sql-programming-guide.html

// val sc: SparkContext // An existing SparkContext.
val sqlContext = new org.apache.spark.sql.SQLContext(sc)

// createSchemaRDD is used to implicitly convert an RDD to a SchemaRDD.
import sqlContext.createSchemaRDD

// Define the schema using a case class.
// Note: Case classes in Scala 2.10 can support only up to 22 fields. To work around this limit, 
// you can use custom classes that implement the Product interface.
//case class Person(name: String, age: Int)
case class VolumePerMonth(cons_id:String, ym:String, volume_per_month:Int)

// Create an RDD of Person objects and register it as a table.
//val people = sc.textFile("examples/src/main/resources/people.txt").map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt))
//people.registerAsTable("people")

// hive的默认分割符是CTRL+A,  即SOH,Start of heading,十进制的1,8进制的01,16进制的0x01
// ASCII Control Codes 请参见 
//	http://academic.evergreen.edu/projects/biophysics/technotes/program/ascii_ctrl.htm
//	或 http://techurls.tripod.com/dha.htm
val delimiterInt:Int = 0x01;
val delimiterChar:Char = delimiterInt.toChar
val delimiter:String = delimiterChar.toString;

val db_VolumePM = sc.textFile("/user/hive/warehouse/bigdata_arc_volume_perm_s01/*").map(_.split(delimiter)).map(p => VolumePerMonth(p(0), p(1), p(2).trim.toInt))
db_VolumePM.registerAsTable("VPM")


// SQL statements can be run by using the sql methods provided by sqlContext.
//val teenagers = sqlContext.sql("SELECT name FROM people WHERE age >= 13 AND age <= 19")
val vpm201202 = sqlContext.sql("SELECT * FROM VPM WHERE ym=201202")
val vpm_all = sqlContext.sql("SELECT * FROM VPM")

vpm201202.count  // 15920
vpm_all.count	// 474354

// The results of SQL queries are SchemaRDDs and support all the normal RDD operations.
// The columns of a row in the result can be accessed by ordinal.
//teenagers.map(t => "Name: " + t(0)).collect().foreach(println)
vpm201202.first.map(t => "Name: " + t)
//vpm201202.first.map(t => "Name: " + t).collect().foreach(println)

// ----------------------------------------------------------------------------
// Parquet Files

val hdfsFilePathOfSpark = "/user/spark/demo/bigdata_arc_volume_perm_s01/"

// The RDD is implicitly converted to a SchemaRDD by createSchemaRDD, allowing it to be stored using Parquet.
//people.saveAsParquetFile("people.parquet")
vpm201202.saveAsParquetFile(hdfsFilePathOfSpark + "vpm201202.parquet")

/*
hadoop@master-hadoop:~/workspace_github/hadoop-ws/spark-ws$ hdfs dfs -ls -R /user/spark/
drwxr-xr-x   - hadoop supergroup          0 2014-07-09 23:31 /user/spark/demo
drwxr-xr-x   - hadoop supergroup          0 2014-07-09 23:31 /user/spark/demo/bigdata_arc_volume_perm_s01
drwxr-xr-x   - hadoop supergroup          0 2014-07-09 23:31 /user/spark/demo/bigdata_arc_volume_perm_s01/vpm201202.parquet
-rw-r--r--   1 hadoop supergroup          0 2014-07-09 23:31 /user/spark/demo/bigdata_arc_volume_perm_s01/vpm201202.parquet/_SUCCESS
-rw-r--r--   1 hadoop supergroup        941 2014-07-09 23:31 /user/spark/demo/bigdata_arc_volume_perm_s01/vpm201202.parquet/_metadata
-rw-r--r--   1 hadoop supergroup        200 2014-07-09 23:31 /user/spark/demo/bigdata_arc_volume_perm_s01/vpm201202.parquet/part-r-1.parquet
-rw-r--r--   1 hadoop supergroup      97117 2014-07-09 23:31 /user/spark/demo/bigdata_arc_volume_perm_s01/vpm201202.parquet/part-r-2.parquet
-rw-r--r--   1 hadoop supergroup        200 2014-07-09 23:31 /user/spark/demo/bigdata_arc_volume_perm_s01/vpm201202.parquet/part-r-3.parquet
-rw-r--r--   1 hadoop supergroup        200 2014-07-09 23:31 /user/spark/demo/bigdata_arc_volume_perm_s01/vpm201202.parquet/part-r-4.parquet
*/

// Read in the parquet file created above.  Parquet files are self-describing so the schema is preserved.
// The result of loading a Parquet file is also a SchemaRDD.
//val parquetFile = sqlContext.parquetFile("people.parquet")

vpm_all.saveAsParquetFile(hdfsFilePathOfSpark + "vpm_all.parquet")
val parquetFile = sqlContext.parquetFile(hdfsFilePathOfSpark + "vpm_all.parquet")

//Parquet files can also be registered as tables and then used in SQL statements.
/*
parquetFile.registerAsTable("parquetFile")
val teenagers = sqlContext.sql("SELECT name FROM parquetFile WHERE age >= 13 AND age <= 19")
teenagers.map(t => "Name: " + t(0)).collect().foreach(println)
*/
parquetFile.registerAsTable("parquetFile")
val vpm201301 = sqlContext.sql("SELECT * FROM parquetFile WHERE ym=201301")
vpm201301.count  // 16609

vpm201301.printSchema()
/*
scala> vpm_all.printSchema()
root
 |-- cons_id: StringType
 |-- ym: StringType
 |-- volume_per_month: IntegerType
*/

// ----------------------------------------------------------------------------
// JSON Datasets

val anotherPeopleRDD = sc.parallelize(
  """{"name":"Yin","address":{"city":"Columbus","state":"Ohio"}}""" :: Nil)
val anotherPeople = sqlContext.jsonRDD(anotherPeopleRDD)

anotherPeople.printSchema()

// ---------------------------------
anotherPeople.registerAsTable("anotherPeople")

/* 下面语句好像错了,跳过! ^_^
// SQL statements can be run by using the sql methods provided by sqlContext.
val hasThisPeople = sqlContext.sql("SELECT * FROM anotherPeople WHERE name = Yin")
val hasnotThisPeople = sqlContext.sql("SELECT * FROM anotherPeople WHERE name = Yinnn")
*/

// ----------------------------------------------------------------------------
// Hive Tables
/*
However, since Hive has a large number of dependencies, it is not included in the default Spark assembly. 
In order to use Hive you must first run ‘SPARK_HIVE=true sbt/sbt assembly/assembly’ (or use -Phive for maven). 
This command builds a new assembly jar that includes Hive. 

Note that this Hive assembly jar must also be present on all of the worker nodes, as they will need access to the Hive serialization and deserialization libraries (SerDes) in order to acccess data stored in Hive.
Configuration of Hive is done by placing your hive-site.xml file in conf/.
*/

/*
// sc is an existing SparkContext.
val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)

hiveContext.hql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING)")
hiveContext.hql("LOAD DATA LOCAL INPATH 'examples/src/main/resources/kv1.txt' INTO TABLE src")

// Queries are expressed in HiveQL
hiveContext.hql("FROM src SELECT key, value").collect().foreach(println)
*/

/*
	下面语句要执行成功,需要重新编译SPARK
	--------------------
	我们编译含有hive功能的spark,其语句如下:

	cd ${SPARK_HOME}

	export MAVEN_OPTS="-Xmx2g -XX:MaxPermSize=512M -XX:ReservedCodeCacheSize=512m"
	mvn -Phive -Pyarn -Phadoop-2.2 -Dhadoop.version=2.2.0 -DskipTests clean package
*/
// sc is an existing SparkContext.
val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)

// 要成功执行下面语句,需要:
//	1.将hive的配置文件 ${HIVE_HOME}/conf/hive-site.xml 拷贝到${SPARK_HOME}/conf目录下
//		ln -s ${HIVE_HOME}/conf/hive-site.xml ${SPARK_HOME}/conf/hive-site.xml
//	2. 启动hive server, 并尝试用hive连接
//		hive --service hiveserver
//		hive -h master-hadoop
//	3.把mysql驱动让spark能够识别到
//		下面这句不起作用 
//			ln -s  /opt/java_cp/mysql-connector-java-5.1.27.jar ${SPARK_HOME}/lib_managed/mysql-connector-java-5.1.27.jar
			这一句也失败! export ADD_JARS=/opt/java_cp/mysql-connector-java-5.1.27.jar
//		下面这样才可以(然后再执行 ./bin/spark-shell)
//			export SPARK_CLASSPATH=/opt/java_cp/mysql-connector-java-5.1.27.jar


// cons_id = 25257000, 25280011
hiveContext.hql("FROM bigdata_arc_volume_perm_s01 SELECT cons_id, ym, volume_per_month WHERE ym = 201401 AND cons_id = 25280011").collect().foreach(println)

// 某个用户的1条数据
hiveContext.hql("SELECT cons_id, ym, volume_per_month FROM bigdata_arc_volume_perm_s01 WHERE ym = 201401 AND cons_id = 25280011").collect().foreach(println)
// 2个用户的各1条数据
hiveContext.hql("SELECT cons_id, ym, volume_per_month FROM bigdata_arc_volume_perm_s01 WHERE ym = 201401 AND (cons_id = 25280011 OR cons_id = 25257000)").collect().foreach(println)
// 2个用户很多个月份的数据
hiveContext.hql("SELECT cons_id, ym, volume_per_month FROM bigdata_arc_volume_perm_s01 WHERE (cons_id = 25280011 OR cons_id = 25257000)").collect().foreach(println)
// 2个用户很多个月份的数据--按照cons_id排序
hiveContext.hql("SELECT cons_id, ym, volume_per_month FROM bigdata_arc_volume_perm_s01 WHERE (cons_id = 25280011 OR cons_id = 25257000) order by cons_id").collect().foreach(println)

// ----------------------------------------------------------------------------
// Writing Language-Integrated Relational Queries
// 这个...高端了,所以,省略了 ....

/*
// sc is an existing SparkContext.
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
// Importing the SQL context gives access to all the public SQL functions and implicit conversions.
import sqlContext._
val people: RDD[Person] = ... // An RDD of case class objects, from the first example.

// The following is the same as 'SELECT name FROM people WHERE age >= 10 AND age <= 19'
val teenagers = people.where('age >= 10).where('age <= 19).select('name)
teenagers.map(t => "Name: " + t(0)).collect().foreach(println)
*/

