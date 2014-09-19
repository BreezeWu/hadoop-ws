// ----------------------------------------------------------------------------
/**
 * 将年中月用电量变换为： 月用电量平均值， 月用电量最大值
 * 
*/

// ----------------------------------------------------------------------------
// 求每年的月用电量平均值, 并将其转换为平均值的百分比

// 逐年的月用电量平均值 （同时也用来代表 percent）
case class AverageMonthVolume(y2010:Double, y2011:Double, y2012:Double, y2013:Double, y2014:Double) {
  def toArray():Array[Double] = {
    Array(this.y2010, this.y2011, this.y2012, this.y2013, this.y2014)
  }
}

//type AverageMonthVolume = Array[Double]
// 年度月用电量平均值 以及 年度月用电量平均值(percent)
case class MPVolumeItem_AverageMonthVolume_percent(
	index:Index,
	runnedMonths:Int, // 已运行月份
  runnedDays:Int, // 已运行天数
	maxMonthVolume:Double,    // 最大的年度平均月用点量
	average:AverageMonthVolume, // 年度月用电量平均值
	averagePercent:AverageMonthVolume	// 年度月用电量平均值(percent)
  )

// 求每年的月用电量平均值, 并将其转换为平均值的百分比
def convertMPVolumeItem2AveragePercent(item:MPVolumeItem):MPVolumeItem_AverageMonthVolume_percent = {
  val index = item.index
  val volumes = item.volumes

  def AverageValue(volumes:TwelveVolumes):Double = {
    val sum = if (null == volumes) 0.0 else volumes.reduce((a,b) => a+b)
    val average = sum / 12

    return average
  }
  /*
  val AverageValue = (volumes:TwelveVolumes) => {
    return (if (null == volumes) 0.0 else volumes.reduce((a,b) => if(a > b) a else b)) / 12
  }
  */

  val averageValue_y2010 = AverageValue(volumes.y2010)
  val averageValue_y2011 = AverageValue(volumes.y2011)
  val averageValue_y2012 = AverageValue(volumes.y2012)
  val averageValue_y2013 = AverageValue(volumes.y2013)
  val averageValue_y2014 = AverageValue(volumes.y2014)

  val maxValue = math.max(math.max(math.max(math.max(averageValue_y2010, averageValue_y2011), averageValue_y2012), averageValue_y2013), averageValue_y2014)

  // 转换为 百分比
  def convert2percent(curValue:Double, maxValue:Double):Double = {
    val percent = (((curValue*10000)/maxValue).toInt.toDouble)/100	// ((curValue*100)/maxValue).toInt.toDouble)

    return percent
  }

  val average = AverageMonthVolume(
    averageValue_y2010,
    averageValue_y2011,
    averageValue_y2012,
    averageValue_y2013,
    averageValue_y2014)
  val averagePercent = AverageMonthVolume(
    convert2percent(averageValue_y2010, maxValue),
    convert2percent(averageValue_y2011, maxValue),
    convert2percent(averageValue_y2012, maxValue),
    convert2percent(averageValue_y2013, maxValue),
    convert2percent(averageValue_y2014, maxValue)
  )

  // ----------------------------------------------------------------------------
  // 将 run_date 转换为 到 今天的 时间差
  def ComputeGap2Toady(s:String):(Int,Int) = {
    import java.text.DateFormat
    import java.text.SimpleDateFormat
    import java.util.Date
    import java.util.Calendar

    val sDate = s.split('.')(0)	// "2010-02-22 00:00:00"
    val dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")
    val runDate = dateFormat.parse(sDate)
    val calendarRunDate = Calendar.getInstance()
    val calendarToday = Calendar.getInstance()
    calendarRunDate.setTime(runDate)
    calendarToday.setTime(new Date())

    val gap = calendarToday.getTimeInMillis() - calendarRunDate.getTimeInMillis()
    val calendarGap = Calendar.getInstance()
    calendarGap.setTimeInMillis(gap)

    val year = calendarGap.get(Calendar.YEAR)
    val month = calendarGap.get(Calendar.MONTH)
    val day = calendarGap.get(Calendar.DATE)

    // 时间之间的 月差
    val months = month + (year - 1970)*12
    // 日差(近似)
    val days = months * 30 + day

    return (months, days)
  }
  val gap = ComputeGap2Toady(index.left.run_date)
  val runnedMonths = gap._1
  val runnedDays = gap._2

  // ----------------------------------------------------------------------------
  return MPVolumeItem_AverageMonthVolume_percent(index, runnedMonths, runnedDays,
    maxValue, average, averagePercent)

  /* 测试代码
  val range = Range(1,12+1)
  val volumes0 = for(i <- range) yield (new scala.util.Random()).nextDouble()
  val volumes1 = for(i <- range) yield (new scala.util.Random()).nextDouble()
  val volumes2 = for(i <- range) yield (new scala.util.Random()).nextDouble()
  val volumes3 = for(i <- range) yield (new scala.util.Random()).nextDouble()
  val volumes4 = for(i <- range) yield (new scala.util.Random()).nextDouble()

  val volumes = YearsVolumes(volumes0,volumes1,volumes2,volumes3,volumes4)

  val maxValue = MaxValue(volumes)
  MaxValue(null)
  */
}


// ----------------------------------------------------------------------------
// 将月用点量转换为该用户最大值的百分必
// 数据集元素(percent)
case class MPVolumeItem_percent(index:Index, volumes:YearsVolumes, max:Double)

// 将 YearsVolumes 变换为百分比形式: 暂不使用
def convertMPVolumeItem2Percent(item:MPVolumeItem):MPVolumeItem_percent = {
  val index = item.index
  val volumes = item.volumes
  
  def MaxValue(volumes:TwelveVolumes):Double = {
	  return if (null == volumes) 0.0 else volumes.reduce((a,b) => if(a > b) a else b)
  }
  /*
  val MaxValue = (volumes:TwelveVolumes) => {
    if (null == volumes) 0.0 else volumes.reduce((a,b) => if(a > b) a else b)
  }
  */
  
  val maxValue_y2010 = MaxValue(volumes.y2010)
  val maxValue_y2011 = MaxValue(volumes.y2011)
  val maxValue_y2012 = MaxValue(volumes.y2012)
  val maxValue_y2013 = MaxValue(volumes.y2013)
  val maxValue_y2014 = MaxValue(volumes.y2014)
  
  val maxValue = math.max(math.max(math.max(math.max(maxValue_y2010, maxValue_y2011), maxValue_y2012), maxValue_y2013), maxValue_y2014)

  // 转换为 百分比
  def convert2percent(volumes:TwelveVolumes, maxValue:Double):TwelveVolumes = {
    if (null == volumes)  return zeroTwelveVolumes

    val percentRow = if (maxValue == 0) {
      volumes.map(x => 0.0)
    } else {
      volumes.map(x => (((x*10000)/maxValue).toInt.toDouble)/100)	// ((x*100)/maxValue).toInt.toDouble)
    }
    return percentRow
  }
  
  val newVolumes = YearsVolumes(
		convert2percent(volumes.y2010, maxValue),
		convert2percent(volumes.y2011, maxValue),
		convert2percent(volumes.y2012, maxValue),
		convert2percent(volumes.y2013, maxValue),
		convert2percent(volumes.y2014, maxValue)
	)

  return MPVolumeItem_percent(index, newVolumes, maxValue)
  
  /* 测试代码
  val range = Range(1,12+1)
  val volumes0 = for(i <- range) yield (new scala.util.Random()).nextDouble()
  val volumes1 = for(i <- range) yield (new scala.util.Random()).nextDouble()
  val volumes2 = for(i <- range) yield (new scala.util.Random()).nextDouble()
  val volumes3 = for(i <- range) yield (new scala.util.Random()).nextDouble()
  val volumes4 = for(i <- range) yield (new scala.util.Random()).nextDouble()
  
  val volumes = YearsVolumes(volumes0,volumes1,volumes2,volumes3,volumes4)
  
  val maxValue = MaxValue(volumes)
  MaxValue(null)
  */
}






