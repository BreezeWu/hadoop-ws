package org.wuhz.spark.sg.yekuobaozhuang.data

/**
 * Created by hadoop on 9/28/14.
 */
class Creater(tablename:String) {
  val tableName = "bigdata_mpvolume_onerow"	// 表名
  val selectSql = s"select * from ${tableName}"
  val rddFromHive = hiveContext.hql(selectSql)

  val first = rddFromHive.first

}
