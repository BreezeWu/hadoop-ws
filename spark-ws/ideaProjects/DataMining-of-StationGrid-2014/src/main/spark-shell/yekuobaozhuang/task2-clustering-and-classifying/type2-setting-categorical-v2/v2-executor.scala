/**
 * Created by hadoop on 9/28/14.
 */

/*
 * 执行封装函数
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
// 聚类参数
case class ClusteringParams(k:Int, maxIterations:Int = 20)

// 分类参数
case class ClassificationParams(numClasses:Int,
                                categoricalFeaturesInfo:Map[Int, Int],
                                impurity:String,
                                maxDepth:Int,
                                maxBins:Int)

// 封装(1)聚类(2)决策分析 过程
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector
def Process(clusteringParams:ClusteringParams,
            classificationParams:ClassificationParams,
            data:RDD[MPVolumeItem_AverageMonthVolume_percent],
            taskname:String = "") ={

  // ============================================================================
  // ----------------------------------------------------------------------------
  // (1) 聚类
  // 执行"准备聚类数据"
  val dataForClustering = BuildClusteringData(data)
  // 执行聚类,得到类标签
  val predictRdd_Clustering = ClusteringData(dataForClustering, clusteringParams.k, clusteringParams.maxIterations, taskname)

  // ============================================================================
  // ----------------------------------------------------------------------------
  // (2) 决策树分析
  // 调用"将分类特征数据转换为Vector"
  val labeledPointRdd = BuildClassificationData(data, predictRdd_Clustering)
  // 运行"得到分类特征的Distinct信息"
  val featuresDistinctInfo = ComputeFeaturesDistinctInfo(data)
  // ----------------------------------------------------------------------------
  // 决策书算法参数
  // 传入的参数
  val numClasses = classificationParams.numClasses // 50 //2
  //  Empty categoricalFeaturesInfo indicates all features are continuous.
  //val categoricalFeaturesInfo = Map[Int, Int]() // 需要重新计算
  val impurity = classificationParams.impurity
  val maxDepth = classificationParams.maxDepth
  // val maxBins = 100    // 需要重新计算, 该值必须大于各个分类属性的最大值

  // 计算决策树算法所需要的 categoricalFeaturesInfo 参数
  val categoricalFeaturesInfo = GetCategoricalFeaturesInfo(featuresDistinctInfo)

  // 计算决策树算法所需要的 maxBins
  val maxCategories = categoricalFeaturesInfo.values.reduce((a,b) => scala.math.max(a,b))
  val maxBins = maxCategories + 1
  // 但maxBins 太大会导致 java.lang.OutOfMemoryError: Java heap space
  // 通过"(1)选择参与分类的属性;(2)将属性变为连续的,即调整categoricalFeaturesInfo"这两种方法 进而调整 maxBins
  // ----------------------------------------------------------------------------
  // 特征值的分类值, 其数组编号就是特征编号: 与 categoricalFeaturesInfo 匹配
  val distinctFeatureValues = featuresDistinctInfo.map(x => (x.featureID, x.isCategorical, x.featureValuesMap))
  // 说明 distinctFeatureValues 和 categoricalFeaturesInfo 的大小相同, 代表的是参与决策树分析的特征数量
  // categoricalFeaturesInfo 存储的是 各个特征的distinct数量: 特征ID -> 不重复的特征值数量. 若某个特征不是分类的,则找不到该数据
  // distinctFeatureValues 存储的是 (特征索引, 是否分类属性, 各个特征的distinct value的map)
  // 如 distinctFeatureValues(0) 存储的就是第一个属性的信息

  // 对 labeledPointRdd 中的分类特征,将hashCode值变为 特征值的分类值
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
  }).cache()
  // ----------------------------------------------------------------------------
  // 执行决策树算法
  import org.apache.spark.mllib.tree.DecisionTree
  val model = DecisionTree.trainClassifier(dataForDecisionTree, numClasses, categoricalFeaturesInfo, impurity,
    maxDepth, maxBins)

  // Evaluate model on training instances and compute training error
  val labelAndPreds = dataForDecisionTree.map { point =>
    val prediction = model.predict(point.features)
    (point.label, prediction)
  }
  val trainErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / dataForDecisionTree.count
  println("Training Error = " + trainErr)
  println("Learned classification tree model:\n" + model)

  // ----------------------------------------------------------------------------
  // 一些返回值
  (model, labelAndPreds,
    dataForClustering, dataForDecisionTree,
    featuresDistinctInfo,
    categoricalFeaturesInfo)
}

// ============================================================================
// 实际聚类参数
val perfectK = 50;
val maxIterations = 20 // 当前没有生效
val clusteringParams = ClusteringParams(perfectK, maxIterations)
// ----------------------------------------------------------------------------
// 实际分类参数
val numClasses = perfectK // 50 //2
//  Empty categoricalFeaturesInfo indicates all features are continuous.
val categoricalFeaturesInfo = Map[Int, Int]() // 在封装函数中重新设置
val impurity = "gini"
val maxDepth = 5
val maxBins = 100    // 该值必须大于各个分类属性的最大值, 在封装函数中重新设置
// java.lang.IllegalArgumentException: requirement failed: maxBins (= 100) should be greater than max categories in categorical features (>= 350)
// 但maxBins 太大会导致 java.lang.OutOfMemoryError: Java heap space
// 通过"(1)选择参与分类的属性;(2)将属性变为连续的,即调整categoricalFeaturesInfo"这两种方法 进而调整 maxBins

val classificationParams = ClassificationParams(numClasses, null, impurity, maxDepth, maxBins)
// ============================================================================
// 在数据集上分别运行
val taskname = ""
// 所有数据上
//val result_all = Process(clusteringParams, classificationParams, mappedRddData_percent, taskname)

// 根据用户状态划分数据集
val dataRddOf_status01 = mappedRddData_percent.filter( x=> x.index.left.status_code == "01")
/*val dataRddOf_status02 = mappedRddData_percent.filter( x=> x.index.left.status_code == "02")
val dataRddOf_status03 = mappedRddData_percent.filter( x=> x.index.left.status_code == "03")
val dataRddOf_status04 = mappedRddData_percent.filter( x=> x.index.left.status_code == "04")*/
val c1 = dataRddOf_status01.count
/*val c2 = dataRddOf_status02.count
val c3 = dataRddOf_status03.count
val c4 = dataRddOf_status04.count
c1  // 10752
c2  // 3535
c3  // 6
c4  // 1384*/
c1

val result_c1 = Process(clusteringParams, classificationParams, dataRddOf_status01, taskname+"_status01")
//val result_c2 = Process(clusteringParams, classificationParams, dataRddOf_status02, taskname+"_status02")
//val result_c3 = Process(clusteringParams, classificationParams, dataRddOf_status03, taskname+"_status03")
//val result_c4 = Process(clusteringParams, classificationParams, dataRddOf_status04, taskname+"_status04")

