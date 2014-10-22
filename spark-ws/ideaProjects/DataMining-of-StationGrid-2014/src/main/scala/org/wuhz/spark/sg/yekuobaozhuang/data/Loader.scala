package org.wuhz.spark.sg.yekuobaozhuang.data

import org.apache.spark.sql.{SchemaRDD, SQLContext}

/**
 * Created by HongZe.Wu on 9/28/14.
 */
class Loader {
  def loadParquetFile(parquetFile:String):SchemaRDD = {
    val sqlContext = new SQLContext(sc)
    val parquetData = sqlContext.parquetFile(parquetFile)

    return parquetData
  }
}
