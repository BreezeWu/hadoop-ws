/*
 * 封装函数
 *
 * (1)对数据进行聚类, 使用聚类结果给数据打标签；(2) 使用决策树算法进行分类
// 元素数据和标签数据: 目前使用的是 百分比数据
// 变换顺序(原始值): rddFromHive(或从parquetFile) => mappedRddData => mappedRddData_percent => dataForClustering:averageRdd => predictRDD
// 变换顺序(百分比): rddFromHive(或从parquetFile) => mappedRddData => mappedRddData_percent => dataForClustering:averageRdd_percent => predictRDD

val first0 = rddFromHive.first
val first1 = mappedRddData.first
val first2 = mappedRddData_percent.first
val first3 = averageRdd_percent.first  // dataForClustering
val first4 = predictRdd_Clustering.first

rddFromHive // org.apache.spark.sql.SchemaRDD
mappedRddData // org.apache.spark.rdd.RDD[MPVolumeItem]
mappedRddData_percent // org.apache.spark.rdd.RDD[MPVolumeItem_AverageMonthVolume_percent]
averageRdd_percent // org.apache.spark.rdd.RDD[org.apache.spark.mllib.linalg.Vector] // dataForClustering
predictRdd_Clustering // org.apache.spark.rdd.RDD[Int]

first0
first1
first2
first3
first4
 */
// ============================================================================
// ----------------------------------------------------------------------------
// 封装"准备聚类数据"
// 准备聚类数据: (1) 只取参与计算的数据；(2) 转变为Vector, 构建matrices
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector
def BuildClusteringData(data:RDD[MPVolumeItem_AverageMonthVolume_percent]):RDD[Vector] = {
  import org.apache.spark.mllib.linalg.Vectors

  // 取 平均值(年度月用电量)数据
  //val averageRdd = data.map(x => x.average).map(y => Vectors.dense(y.toArray)).cache()
  // 取 平均值(年度月用电量)数据的百分比
  val averageRdd_percent = data.map(x => x.averagePercent).map(y => Vectors.dense(y.toArray)).cache()

  val dataForClustering = averageRdd_percent // averageRdd

  dataForClustering
}

// ----------------------------------------------------------------------------
// 封装聚类操作过程
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector
def ClusteringData(data:RDD[Vector], k:Int, maxIterations:Int = 20):RDD[Int] = {
  val k = 50;
  //val maxIterations = 20 // 当前没有生效

  val env = System.getenv()
  val pwd = env.get("PWD")

  // 聚类结果统计信息
  val resultClusterCountInfo_Standalone = ComputeClusterCount_Standalone(data, k, maxIterations)
  // 中心点和metric信息
  val resultW2LocalFile_Standalone = writeAccount2LocalFile(resultClusterCountInfo_Standalone.account, "yekuobaozhuang", pwd)
  // 统计信息
  val resultWClusterCountInfo2HDFS_Standalone = writeClusterCountInfo2LocalFile(resultClusterCountInfo_Standalone, "yekuobaozhuang", pwd)

  // ----------------------------------------------------------------------------
  // 分类后的数据
  // 类标签的数据
  val predictRdd_Clustering = resultClusterCountInfo_Standalone.predict

  predictRdd_Clustering
}

// ============================================================================
// ----------------------------------------------------------------------------
// 封装"将分类特征数据转换为Vector"
// 将分类特征数据转换为Vector (1)将属性信息转换为hashCode (2) 使用hashCode转换为Vector
def BuildClassificationData(data:RDD[MPVolumeItem_AverageMonthVolume_percent], predictData:RDD[Int]) = {
  // 获得各个属性信息hashCode化,这个hash值就作为特征值
  val attributesRdd_hashCode = data.map(x => (
    ConvertIndex2IndexHashCode(x.index),
    OtherFeatures(x.runnedDays.toDouble)))

  // 将属性特征值转换为Vector
  val featuresRdd = attributesRdd_hashCode.map(x => ConvertAttributesHashCode2Vector(x))

  // 将分类标签和特征值Vector整合为 LabeledPoint
  import org.apache.spark.mllib.regression.LabeledPoint

  val zipRDD = featuresRdd.zip(predictData)
  val labeledPointRdd = zipRDD.map(x => new LabeledPoint(x._2, x._1)).cache()

/*  val a = attributesRdd_hashCode.first
  val f = featuresRdd.first
  val p = predictRdd_Clustering.first
  val l = labeledPointRdd.first

  f
  p
  l*/
}
// ----------------------------------------------------------------------------
// 将分类特征Vector中, 将"是分类属性的特征"转换为其对应的特征值编号
// ----------------------------------------------------------------------------
// 封装"得到分类特征的Distinct信息": 见 函数 ComputeFeaturesDistinctInfo
// ----------------------------------------------------------------------------
// 封装"计算决策树算法所需要的 categoricalFeaturesInfo 参数"
import scala.collection.immutable.Map
def GetCategoricalFeaturesInfo(featuresDistinctInfo:Array[FeaturesDistinctInfo]):Map[Int,Int] = {
  val categoricalFeaturesInfo_array = for{
    x <- featuresDistinctInfo;

    if(x.isCategorical)  // 只有 是分类属性时 才生成
  } yield {x.id2size}

  val categoricalFeaturesInfo = categoricalFeaturesInfo_array.toMap
  categoricalFeaturesInfo
}

// ----------------------------------------------------------------------------
// 封装'将分类特征Vector中, 将"是分类属性的特征"转换为其对应的特征值编号'
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint
def ConvertAttributesVector2CategoricalValueID(labeledPointRdd:RDD[LabeledPoint],
                                               featuresDistinctInfo:Array[FeaturesDistinctInfo]):RDD[LabeledPoint] = {
  // 特征值的分类值, 其数组编号就是特征编号: 与 categoricalFeaturesInfo 匹配
  val distinctFeatureValues = featuresDistinctInfo.map(x => (x.featureID, x.isCategorical, x.featureValuesMap))
  // 说明 distinctFeatureValues 和 categoricalFeaturesInfo 的大小相同, 代表的是参与决策树分析的特征数量
  // categoricalFeaturesInfo 存储的是 各个特征的distinct数量: 特征ID -> 不重复的特征值数量. 若某个特征不是分类的,则找不到该数据
  // distinctFeatureValues 存储的是 (特征索引, 是否分类属性, 各个特征的distinct value的map)
  // 如 distinctFeatureValues(0) 存储的就是第一个属性的信息

  // 对 labeledPointRdd 中的分类特征,利用distinctFeatureValues, 将hashCode值变为 特征值的分类值
  val dataForDecisionTree = labeledPointRdd.map(x => {
    // 此时 x 是 val x = labeledPointRdd.first
    // 如, org.apache.spark.mllib.regression.LabeledPoint = (26.0,[-4.77746059E8,-3.53568873E8,-8.50998663E8,1.55301713E8,1537.0,4.8611737E7,50.0,1603554.0,51510.0,1.481941315E9,1.481941315E9,1708.0])

    val label = x.label
    val features = x.features

    // 此时 features.size 一定等于 distinctFeatureValues.size, 且顺序相同
    val zipWithIndex = features.toArray.zipWithIndex

    val newFeatures = zipWithIndex.map(y => {
      // 此时y, 如 val y = zipWithIndex(0)  实例如: (-4.77746059E8, (特征索引, 是否分类属性, 各个特征的distinct value的map))
      val featureHashCode = y._1
      val featureID = y._2

      // 在 distinctFeatureValues 中查找看 featureID 是否分类属性
      val isCategorical = distinctFeatureValues(featureID)._2

      val newValue = if (isCategorical) { // 分类属性
      val featureValuesMap = distinctFeatureValues(featureID)._3

        // 得到其 分类值
        val mapValue = featureValuesMap.get(featureHashCode).get
        val categoricalID = mapValue._1
        //val originValue = mapValue._2
        categoricalID
      } else {  // 连续属性,保持原来的值
        featureHashCode
      }

      // 返回值
      newValue
    })

    // 结果
    new LabeledPoint(label, Vectors.dense(newFeatures))
  })

  // 返回值
  dataForDecisionTree
}

// ============================================================================
