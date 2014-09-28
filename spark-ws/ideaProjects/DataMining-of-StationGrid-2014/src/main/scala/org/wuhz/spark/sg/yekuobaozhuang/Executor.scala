package org.wuhz.spark.sg.yekuobaozhuang

import org.apache.spark.sql.Row
import org.wuhz.spark.sg.yekuobaozhuang.data.{Mapper, Loader}

/**
 * Created by hadoop on 9/28/14.
 */
object Executor {
  val parquetFile = "file:///home/hadoop/dm-data/rddFromHive-parquet/"

  val loader = new Loader()
  val mapper = new Mapper()
  val rddFromParquet = loader.loadParquetFile(parquetFile)
  //val mappedRddData = rddFromParquet.map(r => mapper.convertTableRecord2Object(r))
  val mappedRddData = rddFromParquet.map(r => convert2TableObject(mapper, r))

  val convert2TableObject(mapper:Mapper, r:Row) = {
    mapper.convertTableRecord2Object(r)
  }
}
