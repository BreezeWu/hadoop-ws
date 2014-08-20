// ----------------------------------------------------------------------------
/**
 * hive 表映射
 * 
 * 阶梯电量电价表, Item_of_ladder
 * 分时电量电价表, Item_of_ts
 *
 * 注意:  必须使用 spark-1.0.0-SNAPSHOT-20140718.tar.gz 这个版本
 *
 * 引入
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volumeprice-mining/transformations-of-volumeprice.scala
 */
 
// index + data
case class IndexVolumePriceItem(cons_no:String, ym:String)
case class VolumePriceItemLadder(index:IndexVolumePriceItem, data:Array[Double])
case class VolumePriceItemTs(index:IndexVolumePriceItem, data:Array[Double])

// 转换函数
// row --> VolumePriceItemLadder
def row2VolumePriceItemLadder(p:org.apache.spark.sql.Row) = {
	// 定义函数:对一个org.apache.spark.sql.Row中每一个列的处理函数
	// 函数原型为: g: (y: Any)Double
	def g(y:Any):Double = y match {
		case null => 0			// 将null转换为0
		case i:Int => i.toDouble	// 将Int转换为Double 
		case d:Double => d
	}
	/*
	VolumePriceItemLadder(
	    IndexVolumePriceItem(p(0).toString,p(1).toString),
	    Array(g(p(2)), g(p(3)), g(p(4)), g(p(5)), g(p(6)), g(p(7)))
	)*/
	val p_ = p
	VolumePriceItemLadder(
	    IndexVolumePriceItem(p_(0).toString,p_(1).toString),
	    Array(g(p_(2)), g(p_(3)), g(p_(4)), g(p_(5)), g(p_(6)), g(p_(7)))	    
	)
}

// row --> VolumePriceItem_Ts
def row2VolumePriceItemTs(p:org.apache.spark.sql.Row) = {
	// 定义函数:对一个org.apache.spark.sql.Row中每一个列的处理函数
	// 函数原型为: g: (y: Any)Double
	def g(y:Any):Double = y match {
		case null => 0			// 将null转换为0
		case i:Int => i.toDouble	// 将Int转换为Double 
		case d:Double => d
	}
	VolumePriceItemTs(
	    IndexVolumePriceItem(p(0).toString,p(1).toString),
	    Array(g(p(2)), g(p(3)), g(p(4)), g(p(5)), g(p(6)), g(p(7)), g(p(8)), g(p(9)), g(p(10)), g(p(11)))
	)
}

