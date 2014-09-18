// ----------------------------------------------------------------------------
/**
 * 将年中月用电量变换为： 月用电量平均值， 月用电量最大值
 * 
*/
 
// 数据集元素(percent)
case class MPVolumeItem_percent(index:Index, volumes:YearsVolumes)

val first2 = mappedRddData.first
//
val date = new java.util.Date((first2.index.left.run_date).split('.')(0))
val date = new java.util.Time(first2.index.left.run_date)

val s = "2010-02-22 00:00:00"

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar

val dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")
val time = dateFormat.parse(s)
val calendar = Calendar.getInstance()
calendar.setTime(time)
val date = calendar.getTime()	// 然后可以比较

