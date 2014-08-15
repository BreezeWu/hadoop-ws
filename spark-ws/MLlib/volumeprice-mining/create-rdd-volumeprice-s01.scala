// ----------------------------------------------------------------------------
/**
 * hive 表映射
 * 
 * 数据集标识, datasetID: s01 s98
 * 阶梯电量电价表, table_volume_of_ladder
 * 分时电量电价表, table_volume_of_ts
 */

val datasetID = "s01"
val table_volume_of_ladder = s"bigdata_volume_of_ladder_${datasetID}"
val table_volume_of_ts = s"bigdata_volume_of_ts_${datasetID}"

// 阶梯电量电价表: 用户号, 年月, 一阶电量, 一阶电价, 二阶电量, 二阶电价, 三阶电量, 三阶电价,
case class item_of_ladder(cons_no:String, ym:String, volume1:Int, price1:Double, 
    volume2:Int, price2:Double, volume3:Int, price3:Double)
    
// 分时电量电价表: 用户号, 年月, 尖峰电量, 尖峰电价, 峰电量, 峰电价, 平电量, 平电价, 谷电量, 谷电价, 脊骨电量, 脊骨电价
// 当前数据没有 尖峰和脊骨 信息
case class item_of_ladder(cons_no:String, ym:String, 
    volume_top:Int, price_top:Double, 
    volume_high:Int, price_high:Double, volume_median:Int, price_median:Double, volume_low:Int, price_low:Double, 
    volume_bottom:Int, price_bottom:Double)

﻿// ----------------------------------------------------------------------------

