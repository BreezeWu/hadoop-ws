package org.wuhz.spark.sg.volumetrends

/**
 * Created by hadoop on 9/4/14.
 */

// Define the schema using a case class.
// Note: Case classes in Scala 2.10 can support only up to 22 fields. To work around this limit,
// you can use custom classes that implement the Product interface.

// 索引
case class Index(cons_id:String, cons_no:String, maxVpm:Double= -1.0)
// 用户每年的月用电量
case class ConsVPM(index:Index, vpm:Array[Double]) {
  def getPrintLine():String = {
    val headStr = s"${this.index.cons_id}, ${this.index.cons_no}, ${this.index.maxVpm},||"
    val line = this.vpm.foldLeft(headStr)((x,y) => x + ", " + y)
    return line
  }
  def print() = {
    println(this.getPrintLine())
  }
}
// 具有簇ID的"用户每年的月用电量"
case class ConsVPMClustered(consVpm: ConsVPM, clusterID:Int) {
  def getPrintLine():String = {
    val line = this.consVpm.getPrintLine() + s", ||,  ${this.clusterID}"
    return line
  }
  def print() = {
    println(this.getPrintLine())
  }
}

object objUtil {
  /**
   * 将Any 转换为 Double
   * @param y
   * @return
   */
  def any2Double(y:Any):Double = y match {
    case null => 0			// 将null转换为0
    case i:Int => i.toDouble	// 将Int转换为Double
    case d:Double => d
  }
  /**
   * 转换 org.apache.spark.sql.Row 中的值(Any)为 Double: 原值
   *
   * 原型为: sqlRow2Double: (row: org.apache.spark.sql.Row)Seq[Double]
   */
  def sqlRow2Double(row:org.apache.spark.sql.Row) = {
    row.map(y => any2Double(y))
  }


  /**
   * 测试代码

// 数据变换函数运用举例
// 转换为vector,最后变为matrices
val parsedData_M1 = rddFromHive.map(x => sqlRow2Double(x)).	// 去掉null,转换为Double
	map(x => Vectors.dense(x.toArray))		// 转变为Vector, 构建matrices

// 测试 any2Double(y)
// 对一个org.apache.spark.sql.Row中每一个列的处理
rddFromHive.first.map(y => any2Double(y))

   *
   */

  // 转换函数: 从hive中的用户每年用电量表到数据对象的映射
  def row2ConsVPM(row:org.apache.spark.sql.Row) = {
    val p = row
    val g = any2Double(_)

    ConsVPM(
      Index(p(0).toString,p(1).toString),
      Array(g(p(2)), g(p(3)), g(p(4)), g(p(5)), g(p(6)), g(p(7)), g(p(8)), g(p(9)), g(p(10)), g(p(11)), g(p(12)), g(p(13)))
    )
  }

  /**
   * 转换 org.apache.spark.sql.Row 中的值(Any)为 Double: 百分值
   *
   * 原型为: sqlRow2Double: (row: org.apache.spark.sql.Row)Seq[Double]
   */
  def sqlRow2Double_percent(row:org.apache.spark.sql.Row, range:Int = 100):Seq[Double] = {
    val doubleRow = row.map(y => any2Double(y))

    val maxValue = doubleRow.reduce((a,b) => if(a > b) a else b)
    //val percentRow = doubleRow.map(x => ((x*range)/maxValue).toInt.toDouble)
    
    val percentRow = if (maxValue ==0 ) {
        doubleRow.map(x => 0.0)
    } else {
        doubleRow.map(x => ((x*range)/maxValue).toInt.toDouble)
    }

    return percentRow
  }

  // 转换函数: 从hive中的用户每年用电量表到数据对象的映射: 百分值
  def row2ConsVPM_percent(row:org.apache.spark.sql.Row, range:Int = 100) = {
    val p = row
    val g = any2Double(_)

    val array = Array(g(p(2)), g(p(3)), g(p(4)), g(p(5)), g(p(6)), g(p(7)), g(p(8)), g(p(9)), g(p(10)), g(p(11)), g(p(12)), g(p(13)))
    val maxValue = array.reduce((a,b) => if(a > b) a else b)
    //val newArray = array.map( x => (((x*range)/maxValue).toInt.toDouble))
    val newArray = if (maxValue ==0 ) {
      array.map( x => 0.0)
    } else {
      array.map( x => (((x*range)/maxValue).toInt.toDouble))
    }

    val index = Index(p(0).toString,p(1).toString, maxValue)

    ConsVPM(index, newArray)
  }
}
