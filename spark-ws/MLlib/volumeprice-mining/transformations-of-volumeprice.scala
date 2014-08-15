// ----------------------------------------------------------------------------
/**
 * hive 表映射
 * 
 * 阶梯电量电价表, Item_of_ladder
 * 分时电量电价表, Item_of_ts
 *
 * 引入
 *      :load  /home/hadoop/workspace_github/hadoop-ws/spark-ws/MLlib/volume-price-mining/transformations-of-volumeprice.scala
 */
 
// index + data
case class IndexData(index:Array[String], data:Array[Double])

// 阶梯电量电价表: 用户号, 年月, 一阶电量, 一阶电价, 二阶电量, 二阶电价, 三阶电量, 三阶电价,
case class Item_of_ladder(cons_no:String, ym:String, volume1:Int, price1:Double, 
    volume2:Int, price2:Double, volume3:Int, price3:Double) {
    
    // 转换为 index + data
    def convert2IndexData():IndexData = {
        IndexData(Array(cons_no,ym), Array(volume1, price1, volume2, price2, volume3, price3))
    }
}
    
// 分时电量电价表: 用户号, 年月, 尖峰电量, 尖峰电价, 峰电量, 峰电价, 平电量, 平电价, 谷电量, 谷电价, 脊骨电量, 脊骨电价
// 当前数据没有 尖峰和脊骨 信息
case class Item_of_ts(cons_no:String, ym:String, 
    volume_top:Int, price_top:Double, 
    volume_high:Int, price_high:Double, volume_median:Int, price_median:Double, volume_low:Int, price_low:Double, 
    volume_bottom:Int, price_bottom:Double) {
    
    // 转换为 index + data
    def convert2IndexData():IndexData = {
        IndexData(
            Array(cons_no,ym), 
            Array(volume_top, price_top,
                volume_high, price_high, volume_median, price_median, volume_low, price_low, 
                volume_bottom, price_bottom)
        )
    }
}

// ----------------------------------------------------------------------------
/**
 * 从 hive hiveContext row 转换到 case class
 */
def Any2String(x:Any):String = x match {
    case null => ""			// 将null转换为0
    case s:String => s
}
def Any2Int(x:Any):Int = x match {
    case null => 0			// 将null转换为0
    case i:Int => i
}
def Any2Double(x:Any):Double = x match {
    case null => 0			// 将null转换为0.0
    case i:Int => i.toDouble
    case d:Double => d
}

def row2Item_of_ladder(r:org.apache.spark.sql.Row):Item_of_ladder = {
    Item_of_ladder(Any2String(r(0)), Any2String(r(1)),
        Any2Int(r(2)), Any2Double(r(3)), Any2Int(r(4)), Any2Double(r(5)), Any2Int(r(6)), Any2Double(r(7))
        )
}
def row2Item_of_ts(r:org.apache.spark.sql.Row):Item_of_ts = {   
    Item_of_ts(Any2String(r(0)), Any2String(r(1)),
        Any2Int(r(2)), Any2Double(r(3)),
        Any2Int(r(4)), Any2Double(r(5)), Any2Int(r(6)), Any2Double(r(7)), Any2Int(r(8)), Any2Double(r(9)),
        Any2Int(r(10)), Any2Double(r(11))
        )
}



