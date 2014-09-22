// ----------------------------------------------------------------------------
/**
 * 将特征值变换为特征值,供decistiontree算法使用:
 *  标签, label:  来自 predictRDD
 *  特征值, features:  即将 index 变换为 所需要的 vector(LabeledPoint的features)
 * 
*/
// ----------------------------------------------------------------------------
// 将 Index(id:String, left:IndexLeft, right:IndexRight) hashCode化

// case class 成员最多22个，所以要拆分
case class IndexLeftHashCode(
                             mp_id:Int,
                             mp_no:Int,
                             mp_name:Int,
                             mp_addr:Int,
                             volt_code:Int,
                             app_date:Int,
                             run_date:Int,
                             org_no:Int,
                             mr_sect_no:Int,
                             line_id:Int,
                             tg_id:Int,
                             status_code:Int,
                             cons_id:Int,
                             mp_cap:Int,
                             cons_no:Int,
                             zxzb:Int,
                             cons_name:Int,
                             elec_addr:Int,
                             trade_code:Int,
                             elec_type_code:Int
                             )
case class IndexRightHashCode(
                               contract_cap:Int,
                               run_cap:Int,
                               build_date:Int,
                               ps_date:Int,
                               cancel_date:Int,
                               due_date:Int
                               )
// 索引
case class IndexHashCode(id:String, left:IndexLeftHashCode, right:IndexRightHashCode)

// 将 Index 转换为 IndexHashCode
def ConvertIndex2IndexHashCode(index:Index):IndexHashCode = {
  val l = index.left
  val r = index.right

  IndexHashCode(
    index.id,
    IndexLeftHashCode(
      l.mp_id.hashCode(),
      l.mp_no.hashCode(),
      l.mp_name.hashCode(),
      l.mp_addr.hashCode(),
      l.volt_code.hashCode(),
      l.app_date.hashCode(),
      l.run_date.hashCode(),
      l.org_no.hashCode(),
      l.mr_sect_no.hashCode(),
      l.line_id.hashCode(),
      l.tg_id.hashCode(),
      l.status_code.hashCode(),
      l.cons_id.hashCode(),
      l.mp_cap.hashCode(),
      l.cons_no.hashCode(),
      l.zxzb.hashCode(),
      l.cons_name.hashCode(),
      l.elec_addr.hashCode(),
      l.trade_code.hashCode(),
      l.elec_type_code.hashCode()
    ),
    IndexRightHashCode(
      r.contract_cap.hashCode(),
      r.run_cap.hashCode(),
      r.build_date.hashCode(),
      r.ps_date.hashCode(),
      r.cancel_date.hashCode(),
      r.due_date.hashCode()
    )
  )
}
// ----------------------------------------------------------------------------
// 将属性特征值转换为Vector
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.linalg.Vectors
def ConvertAttributesHashCode2Vector(point:(IndexHashCode, Int)):Vector = {
  val l = point._1.left
  val r = point._1.right
  val runnedDays = point._2

  val arrayOfDouble = Array(
    // left
    //l.mp_id,
    //l.mp_no,
    //l.mp_name,
    //l.mp_addr,
    l.volt_code,
    //l.app_date,
    //l.run_date,
    l.org_no,
    l.mr_sect_no,
    l.line_id,
    //l.tg_id,  // 台区标识/14308
    l.status_code,
    //l.cons_id,
    l.mp_cap,
    //l.cons_no,
    l.zxzb,
    //l.cons_name,
    //l.elec_addr, // 用电客户的用电地址/8909
    l.trade_code,
    l.elec_type_code,

    // right
    r.contract_cap,
    r.run_cap,
    //r.build_date,
    //r.ps_date,
    //r.cancel_date,
    //r.due_date,

    // 其他
    runnedDays
  )

  return Vectors.dense(arrayOfDouble.map(x => x.toDouble))
}

// ----------------------------------------------------------------------------
// 获取属性特征值Vector的distinct,计数,以及CategoricalFeaturesInfo
import org.apache.spark.rdd.RDD
def ComputeCategoricalFeaturesInfo(attributesRdd:RDD[MPVolumeItem_AverageMonthVolume_percent]
                                    //):Array[_ >: (Int, Boolean, (Int, Int), Array[(String, Int)], scala.collection.immutable.Map[Int,(Int, String)])] = {
                                    ):Array[_ >: (Int, Boolean, (Int, Int), Array[(String, Int)], scala.collection.immutable.Map[Int,(Int, String)])] = {
  // 获得各个属性信息
  val tmpRDDRef = attributesRdd.map(x => x.index)
  // ----------------------------------------------------------------------------
  // left
  // mp_id
  // mp_no
  // mp_name
  val featureValue_mp_addr = tmpRDDRef.map(x => x.left.mp_addr).distinct().collect().map(x => (x, x.hashCode())) // 计量点地址/8968
  val featureValue_volt_code = tmpRDDRef.map(x => x.left.volt_code).distinct().collect().map(x => (x, x.hashCode())) // 电压等级/3
  // app_date
  // run_date
  val featureValue_org_no = tmpRDDRef.map(x => x.left.org_no).distinct().collect().map(x => (x, x.hashCode())) // 供电电位编号/23
  val featureValue_mr_sect_no = tmpRDDRef.map(x => x.left.mr_sect_no).distinct().collect().map(x => (x, x.hashCode())) // 抄表段编号/2105
  val featureValue_line_id = tmpRDDRef.map(x => x.left.line_id).distinct().collect().map(x => (x, x.hashCode())) // 线路标识/1863
  val featureValue_tg_id = tmpRDDRef.map(x => x.left.tg_id).distinct().collect().map(x => (x, x.hashCode())) // 台区标识/14308
  val featureValue_status_code = tmpRDDRef.map(x => x.left.status_code).distinct().collect().map(x => (x, x.hashCode())) // 用户状态:正常,当前新装,当前变更,已销户/4
  val featureValue_cons_id = tmpRDDRef.map(x => x.left.cons_id).distinct().collect().map(x => (x, x.hashCode())) // 用户内部索引/12993
  val featureValue_mp_cap = tmpRDDRef.map(x => x.left.mp_cap).distinct().collect().map(x => (x, x.hashCode())) // 计量点容量/350
  val featureValue_cons_no = tmpRDDRef.map(x => x.left.cons_no).distinct().collect().map(x => (x, x.hashCode())) // 用户号/12993
  val featureValue_zxzb = tmpRDDRef.map(x => x.left.zxzb).distinct().collect().map(x => (x, x.hashCode())) // 专线转变:1转变,2专线,/3
  //cons_name
  val featureValue_elec_addr = tmpRDDRef.map(x => x.left.elec_addr).distinct().collect().map(x => (x, x.hashCode())) // 用电客户的用电地址/8909
  val featureValue_trade_code = tmpRDDRef.map(x => x.left.trade_code).distinct().collect().map(x => (x, x.hashCode())) // 行业代码/313
  val featureValue_elec_type_code = tmpRDDRef.map(x => x.left.elec_type_code).distinct().collect().map(x => (x, x.hashCode())) // 营销系统中的用电类别/12

  // ----------------------------------------------------------------------------
  // right
  val featureValue_contract_cap = tmpRDDRef.map(x => x.right.contract_cap).distinct().collect().map(x => (x, x.hashCode())) // 合同容量/347
  val featureValue_run_cap = tmpRDDRef.map(x => x.right.run_cap).distinct().collect().map(x => (x, x.hashCode())) // 运行容量/338
  //val featureValue_build_date = tmpRDDRef.map(x => x.right.build_date).distinct().collect().map(x => (x, x.hashCode())) // 安装日期/12790
  val featureValue_ps_date = tmpRDDRef.map(x => x.right.ps_date).distinct().collect().map(x => (x, x.hashCode())) // 首次送电日期/
  //val featureValue_cancel_date = tmpRDDRef.map(x => x.right.cancel_date).distinct().collect().map(x => (x, x.hashCode())) // 销户归档日期/
  //val featureValue_due_date = tmpRDDRef.map(x => x.right.due_date).distinct().collect().map(x => (x, x.hashCode())) // 临时用电客户约定的用电到期日期/1963

  // ----------------------------------------------------------------------------
  // 其他
  val featureValue_runnedDays = attributesRdd.map(x => x.runnedDays).distinct().collect().map(x => (x, x.hashCode())) // 运行时长/14

  // ----------------------------------------------------------------------------
  // 获取属性特征值Vector的 distinct: true 代表是 分类属性, false 代表是连续属性
  val arrayOf_hashCodeDistinct = Array(
    // left
    //(featureValue_mp_id, true),
    //(featureValue_mp_no, true),
    //(featureValue_mp_name, true),
    //(featureValue_mp_addr, true),
    (featureValue_volt_code, true),
    //(featureValue_app_date, true),
    //(featureValue_run_date, true),
    (featureValue_org_no, true),
    (featureValue_mr_sect_no, true),
    (featureValue_line_id, true),
    //(featureValue_tg_id, true),
    (featureValue_status_code, true),
    //(featureValue_cons_id, true),
    (featureValue_mp_cap, true),
    //(featureValue_cons_no, true),
    (featureValue_zxzb, true),
    //(featureValue_cons_name, true),
    //(featureValue_elec_addr, true),
    (featureValue_trade_code, true),
    (featureValue_elec_type_code, true),

    // right
    (featureValue_contract_cap, true),
    (featureValue_run_cap, true),
    //(featureValue_build_date, true),
    //(featureValue_ps_date, true),
    //(featureValue_cancel_date, true),
    //(featureValue_due_date, true),

    // 其他
    (featureValue_runnedDays, false) // runnedDays 这个是连续的
  )

  // ----------------------------------------------------------------------------
  // 获取属性特征值hashCode的 categoricalFeaturesInfo
  val zipWithIndex = arrayOf_hashCodeDistinct.zipWithIndex
  val arrayOf_categoricalFeaturesInfo = zipWithIndex.map(x => {
    val index = x._2    // 特征编号/属性编号 如0
    val featureValuesArrayAndFlag = x._1 // 特征值数组和特征值属性 如((Array((AC00101,-477842160), (AC03802,-477746059), (AC02202,-477781616)),true),0)

    // 特征值数组
    val featureValuesArray = featureValuesArrayAndFlag._1
    val categoricalFlag = featureValuesArrayAndFlag._2

    // 特征编码 -> 特征值数量,即特征值数组的大小
    val index2size = index -> featureValuesArray.size // 如 0->3

    // 对 特征值数组 转换为 map, 特征值的分类值定义为原数组的索引.后续对数据处理时也基于此规则处理
    val zipWithIndex_featureValues = featureValuesArray.zipWithIndex
    val featureValuesMap = zipWithIndex_featureValues.map(fv => {
      val i = fv._2  // 特征值编号/某属性的取值的编号
      val featureValueAndHashCode = fv._1 // 特征值及其hashCode (AC00101,-477842160)

      // 特征值 -> 特征值的分类值
      //val featureValue2CategoricalId = featureValueAndHashCode._1 -> i
      // 特征值的hashCode -> (特征值的分类值, 原始值)
      val featureValue2CategoricalId = featureValueAndHashCode._2 -> (i, featureValueAndHashCode._1)

      // 结果
      featureValue2CategoricalId
    }).toMap

    // 将 subArray转换为
    (index, categoricalFlag, index2size, featureValuesArray, featureValuesMap)
  })

  // 此时  arrayOf_categoricalFeaturesInfo: (Int, Boolean, (Int, Int), Array[(String, Int)], scala.collection.immutable.Map[Int,(Int, String)])
  arrayOf_categoricalFeaturesInfo
}

// ----------------------------------------------------------------------------
// 获取属性特征值Vector的 categoricalFeaturesInfo
// Array[(String, Int)]
// categoricalFeaturesInfo


// ----------------------------------------------------------------------------
/*
// 将 属性数据 特征值化
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector
def FeaturelizingAttributes(point:RDD[Index]):RDD[Vector] = {
  // 获得相应特征的散裂值
}

// 将索引数据变换为特征向量
import org.apache.spark.mllib.linalg.Vector
def ConvertAttributes2Features(attributes:Index):Vector = {
  val l = attributes.left
  val r = attributes.right
}

// 将 Index 和 聚类标号 转换为 LabeledPoint
import org.apache.spark.mllib.regression.LabeledPoint
def ConvertZipRDDItem2LabeledPoint(point:org.apache.spark.rdd.RDD[(MPVolumeItem_AverageMonthVolume_percent, Int)]):LabeledPoint = {
  val item = point._1
  val label = point._2
  val attributes = item.index

  val features = ConvertAttributes2Features(attributes)

  val labeledPoint = new LabeledPoint(label, features)

  return labeledPoint
}
*/
