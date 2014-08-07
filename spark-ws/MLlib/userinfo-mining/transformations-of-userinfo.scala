// ----------------------------------------------------------------------------
// 为了从 Hive 中得到 parsedData 或 parsedDataIndexed 数据的transformations

// 引入方法
//:load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/userinfo-mining/transformations-of-userinfo.scala

// ----------------------------------------------------------------------------
// 数据变换函数:  应用于 rddFromHive_* (应用数据)
// ----------------------------------------------------------------------------
// 定义函数:对一个org.apache.spark.sql.Row中每一个列的处理函数
// 函数原型为: g: (y: Any)Double
def g(y:Any):Double = y match {
	case null => 0			// 将null转换为0
	case i:Int => i.toDouble	// 将Int转换为Double 
	case d:Double => d
}

// 定义函数: 对每一个org.apache.spark.sql.Row对象的处理函数
// 原型为: f: (x: org.apache.spark.sql.Row)Seq[Double]
def f(x:org.apache.spark.sql.Row) = {
	x.map(y => g(y))
}

// ------------------------------------
// 数据变换函数运用举例
// 双月数据 m1
// 转换为vector,最后变为matrices
//val parsedData_M1 = rddFromHive_M1.map(x => f(x)).	// 去掉null,转换为Double
//	map(x => Vectors.dense(x.toArray))		// 转变为Vector, 构建matrices

// 测试 g(y)
// 对一个org.apache.spark.sql.Row中每一个列的处理
// rddFromHive_.first.map(y => g(y))


// ----------------------------------------------------------------------------
// 数据变换函数:  应用于 rddFromHiveIndexed_* (应用数据)
// ----------------------------------------------------------------------------
// Define the schema using a case class.
// Note: Case classes in Scala 2.10 can support only up to 22 fields. To work around this limit, 
// you can use custom classes that implement the Product interface.

// 索引和VPM
case class Index(cons_id:String, cons_no:String)
/*
case class VPM(v1:Double, v2:Double, v3:Double, v4:Double, v5:Double, v6:Double, v7:Double, v8:Double, v9:Double, v10:Double, v11:Double, v12:Double)
case class ConsVPM(index:Index, vpm:VPM)
// VPM --> Array
def VPM2Array(x:VPM):Array[Double] = {
    Array(x.v1, x.v2, x.v3, x.v4, x.v5, x.v6, x.v7, x.v8, x.v9, x.v10, x.v11, x.v12)
}
*/
case class ConsVPM(index:Index, vpm:Array[Double]) {
    def getPrintLine():String = {
        val headStr = s"${this.index.cons_id}, ${this.index.cons_no}, ||"
        val line = this.vpm.foldLeft(headStr)((x,y) => x + ", " + y)
        return line     
    }
    def print() = {
        println(this.getPrintLine())        
    }
}
// 测试语句: 
//val x = ConsVPM(Index("cons_id","cons_no"), Array(0,1,2,4.3))
//x.print
case class ConsVPMClustered(consVpm: ConsVPM, clusterID:Int) {
    def getPrintLine():String = {
        val line = this.consVpm.getPrintLine() + s", ||,  ${this.clusterID}"
        return line     
    }
    def print() = {
        println(this.getPrintLine())        
    }
}
// 测试语句
//val y = ConsVPMClustered(x, 10)
//y.print

// 转换函数
// row --> ConsVPM :	内聚了一个函数，不再使用了前面的 g(x)
def row2ConsVPM(p:org.apache.spark.sql.Row) = {
	
	// 定义函数:对一个org.apache.spark.sql.Row中每一个列的处理函数
	// 函数原型为: g: (y: Any)Double
	def g(y:Any):Double = y match {
		case null => 0			// 将null转换为0
		case i:Int => i.toDouble	// 将Int转换为Double 
		case d:Double => d
	}
	
	ConsVPM(
	    Index(p(0).toString,p(1).toString),
	    //VPM(g(p(2)), g(p(3)), g(p(4)), g(p(5)), g(p(6)), g(p(7)), g(p(8)), g(p(9)), g(p(10)), g(p(11)), g(p(12)), g(p(13)))
	    Array(g(p(2)), g(p(3)), g(p(4)), g(p(5)), g(p(6)), g(p(7)), g(p(8)), g(p(9)), g(p(10)), g(p(11)), g(p(12)), g(p(13)))
	)
}
//val parsedDataIndexed_GoodM1  = rddFromHiveIndexed_GoodM1.map(r => row2ConsVPM(r))
