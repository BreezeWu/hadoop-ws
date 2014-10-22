package org.wuhz.spark.sg.volumetrends

/**
 * Created by HongZe.Wu on 9/4/14.
 */
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.rdd.RDD

// 第一层与第二层交叉的SQL
case class VpmSqlRef(vpm:String, vpmIndexed:String)
case class SQLMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, sql:VpmSqlRef)

// HiveRDD
case class VpmHiveRDDRef(vpm:org.apache.spark.sql.SchemaRDD, vpmIndexed:org.apache.spark.sql.SchemaRDD)
case class HiveRDDMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, hiveDataRef:VpmHiveRDDRef)

// ParsedRDD
case class VpmParsedRDDRef(vpm: RDD[org.apache.spark.mllib.linalg.Vector], vpmIndexed:RDD[ConsVPM])
case class ParsedRDDMatrixItem(item_L1:DataSetRefItem_L1, item_L2: DataSetRefItem_L2, parsedRDDRef:VpmParsedRDDRef)

object objCreateRDD {
  // ----------------------------------------------------------------------------
  // 构建第一层与第二层交叉的SQLMatrix
  // ----------------------------------------------------------------------------
  def buildSQLMatrix(dataSetRef_L1:List[DataSetRefItem_L1], dataSetRef_L2:List[DataSetRefItem_L2]):List[List[SQLMatrixItem]] = {
    // 变成 另外一个表的 语句
    def GetSelectOfAlias(sAlias:String, sOrigin:String, sSep:String = ","):String = {
      ;//sOrigin.split("")
      val sArray = sOrigin.split(sSep)
      val result = sArray.map(x => sAlias +"."+ x).reduce((x,y) => x + sSep + y)
      return result

      // 下面语句失败 [等价于 sArray.reduce((x,y) => "a."+ x + "," + "a."+ y) ]
      /*
      // 结果是  String = a.a.a.a.a.a.a.a.a.a.a.vpm201301,a.vpm201302,a.vpm201303,a.vpm201304,a.vpm201305,a.vpm201306,a.vpm201307,a.vpm201308,a.vpm201309,a.vpm201310,a.vpm201311,a.vpm201312
      // why?????
      val sAlias = "a"
      def AliasColumn(col:String) = sAlias +"."+ col
      sArray.reduce((x,y) => (AliasColumn(x)) + sSep + (AliasColumn(y)))
      */
      // 下面语句也失败: 编译不通过!
      /*
      sArray.reduce((x,y) => (x => sAlias +"."+ x) + sSep + (y => sAlias +"."+ y))
      sArray.reduce((x,y) => (x => sAlias +"."+ x) + sSep + (y:String => sAlias +"."+ y))
      */
    }

    // ID
    val idInfo = "cons_id, cons_no"
    // 月用电量(vpm)
    val vpmStrOf2013 = "vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312"

    val vpmIndexedStrOf2013 = "cons_id, cons_no, vpm201301,vpm201302,vpm201303,vpm201304,vpm201305,vpm201306,vpm201307,vpm201308,vpm201309,vpm201310,vpm201311,vpm201312"
    // 月用电量(vpm)
    val vpmStrOf2013_a = GetSelectOfAlias("a", vpmStrOf2013)
    val vpmIndexedStrOf2013_a = GetSelectOfAlias("a", vpmIndexedStrOf2013)

    // 构造 joinSelectSQL
    def buildJoinSelectSQL(table_a:String, table_b:String, sSelect:String, sWhere:String):String = {
      val joinSelectSql = s"select ${sSelect} from ${table_a} a left semi join (select * from ${table_b} where ${sWhere}) b on a.cons_no = b.cons_no"

      return joinSelectSql
    }

    // 构造 joinSelectSQL
    def buildSqlListForL1(item_L1:DataSetRefItem_L1):List[SQLMatrixItem] = {
      val sSelect = vpmStrOf2013_a
      val sSelectIndexed = vpmIndexedStrOf2013_a
      val sqlMatrixItemList = dataSetRef_L2.map(y =>
        SQLMatrixItem(item_L1, y, VpmSqlRef(
          buildJoinSelectSQL(item_L1.tablename, y.tablename, sSelect, y.where),
          buildJoinSelectSQL(item_L1.tablename, y.tablename, sSelectIndexed, y.where)
        ))
      )

      return sqlMatrixItemList
    }

    // DataSetRef_L2 与 DataSetRef_L1 的笛卡尔乘积
    val sqlMatrix = dataSetRef_L1.map(x => buildSqlListForL1(x))

    /*
    for(item_L1 <- dataSetRef_L1) {
      println("L1:" + item_L1.id)
      for(item_L2 <- dataSetRef_L2) {
        println("L2:" + item_L2.id)
        val joinSelectSql = buildJoinSelectSQL(item_L1.tablename, item_L2.tablename, vpmStrOf2013_a, item_L2.where)

        println(joinSelectSql)
      }
    }*/

    return sqlMatrix
  }

  // ----------------------------------------------------------------------------
  // 构建第一层与第二层交叉的 HiveRDDMatrix
  // 利用 SqlMatrix 构建 Hive数据
  // ----------------------------------------------------------------------------
  def buildHiveRDDMatrix(hiveContext:org.apache.spark.sql.hive.HiveContext, sqlMatrix:List[List[SQLMatrixItem]]):List[List[HiveRDDMatrixItem]] = {
    //val hiveRDDMatrix = List[List[HiveRDDMatrixItem]]()
    def buildHiveRDDList(list:List[SQLMatrixItem]):List[HiveRDDMatrixItem] = {
      def buildHiveRDD(item:SQLMatrixItem):HiveRDDMatrixItem = {
        val sql_vpm = item.sql.vpm
        val sql_vpmIndexed = item.sql.vpmIndexed

        val rddFromHive_vpm  = hiveContext.hql(sql_vpm)
        val rddFromHive_vpmIndexed  = hiveContext.hql(sql_vpmIndexed)

        val hiveRDDMatrixItem = HiveRDDMatrixItem(item.item_L1, item.item_L2, VpmHiveRDDRef(rddFromHive_vpm,rddFromHive_vpmIndexed))

        return hiveRDDMatrixItem
      }

      val result = list.map(y => buildHiveRDD(y))
      return result
    }

    val hiveRDDMatrix = sqlMatrix.map(x => buildHiveRDDList(x))

    return hiveRDDMatrix
  }

  // ----------------------------------------------------------------------------
  // 从HiveRDDMatrix 变换为 ParsedRDDMatrix
  // 原始值
  // ----------------------------------------------------------------------------
  def transform2ParsedRDDMatrix(hiveRDDMatrix:List[List[HiveRDDMatrixItem]]):List[List[ParsedRDDMatrixItem]] = {
    def transformHiveRDDList(list:List[HiveRDDMatrixItem]):List[ParsedRDDMatrixItem] = {
      def transformHiveRDD(item:HiveRDDMatrixItem):ParsedRDDMatrixItem = {
        val hiveDataRef = item.hiveDataRef
        val hiveData_vpm = hiveDataRef.vpm
        val hiveData_vpmIndexed = hiveDataRef.vpmIndexed

        // 变换 rddFromHive_* (应用数据)
        val parsedData_vpm = hiveData_vpm.map(x => objUtil.sqlRow2Double(x)).	// 去掉null,转换为Double
          map(x => Vectors.dense(x.toArray)).cache()		// 转变为Vector, 构建matrices

        // 变换 rddFromHiveIndexed_* (应用数据)
        val parsedData_vpmIndexed  = hiveData_vpmIndexed.map(r => objUtil.row2ConsVPM(r))

        // 结果对象
        val parsedRDDMatrixItem = ParsedRDDMatrixItem(item.item_L1, item.item_L2, VpmParsedRDDRef(parsedData_vpm,parsedData_vpmIndexed))

        return parsedRDDMatrixItem
      }

      val result = list.map(y => transformHiveRDD(y))
      return result
    }

    val parsedRDDMatrix = hiveRDDMatrix.map(x => transformHiveRDDList(x))

    return parsedRDDMatrix
  }

  // ----------------------------------------------------------------------------
  // 从HiveRDDMatrix 变换为 ParsedRDDMatrix
  // 百分比
  // ----------------------------------------------------------------------------
  def transform2ParsedRDDMatrix_percent(hiveRDDMatrix:List[List[HiveRDDMatrixItem]], percent:Int = 100):List[List[ParsedRDDMatrixItem]] = {
    def transformHiveRDDList(list:List[HiveRDDMatrixItem]):List[ParsedRDDMatrixItem] = {
      def transformHiveRDD(item:HiveRDDMatrixItem):ParsedRDDMatrixItem = {
        val hiveDataRef = item.hiveDataRef
        val hiveData_vpm = hiveDataRef.vpm
        val hiveData_vpmIndexed = hiveDataRef.vpmIndexed

        // 变换 rddFromHive_* (应用数据)
        val parsedData_vpm = hiveData_vpm.map(x => objUtil.sqlRow2Double_percent(x, percent)).	// 去掉null,转换为Double
          map(x => Vectors.dense(x.toArray)).cache()		// 转变为Vector, 构建matrices

        // 变换 rddFromHiveIndexed_* (应用数据)
        val parsedData_vpmIndexed  = hiveData_vpmIndexed.map(r => objUtil.row2ConsVPM_percent(r, percent))

        // 结果对象
        val parsedRDDMatrixItem = ParsedRDDMatrixItem(item.item_L1, item.item_L2, VpmParsedRDDRef(parsedData_vpm,parsedData_vpmIndexed))

        return parsedRDDMatrixItem
      }

      val result = list.map(y => transformHiveRDD(y))
      return result
    }

    val parsedRDDMatrix = hiveRDDMatrix.map(x => transformHiveRDDList(x))

    return parsedRDDMatrix
  }
}


