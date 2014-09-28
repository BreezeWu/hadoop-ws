/**
 * 数据预处理和转换
 *
 */

// 求每年的月用电量平均值, 并将其转换为平均值的百分比
// 注意: 不是 convertTableObject2Percent() !
val mappedRddData_percent = mappedRddData.map(r => convertTableObject2AveragePercent(r))
//val first = mappedRddData_percent.first
