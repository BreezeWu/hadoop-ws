/**
 * Created by HongZe.Wu on 9/28/14.
 */
// 假定: 存在离散属性

// ----------------------------------------------------------------------------
// 执行决策树算法

// ----------------------------------------------------------------------------
// 指出哪些是分类的
// Train a DecisionTree model.
//  Empty categoricalFeaturesInfo indicates all features are continuous.
val numClasses = perfectK // 50 //2
//val categoricalFeaturesInfo = Map[Int, Int]() // 后面定义

val impurity = "gini"
val maxDepth = 5
// val maxBins = 100    // 后面定义
// java.lang.IllegalArgumentException: requirement failed: maxBins (= 100) should be greater than max categories in categorical features (>= 350)

// ============================================
// 获得各个属性信息转换为特征值的分类值

// 获得各个属性信息
val attributesRdd = mappedRddData_percent
val featuresDistinctInfo = ComputeFeaturesDistinctInfo(attributesRdd)

// 得到 categoricalFeaturesInfo
val categoricalFeaturesInfo_array = for{
  x <- featuresDistinctInfo;

  if(x.isCategorical)  // 只有 是分类属性时 才生成
} yield {x.id2size}
val categoricalFeaturesInfo = categoricalFeaturesInfo_array.toMap

// 决策树参数
val maxCategories = categoricalFeaturesInfo.values.reduce((a,b) => scala.math.max(a,b))
val maxBins = maxCategories + 1
// maxBins 太大导致 java.lang.OutOfMemoryError: Java heap space
// 调整 categoricalFeaturesInfo, 进而调整 maxBins

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
  }).cache()

  // 结果
  new LabeledPoint(label, Vectors.dense(newFeatures))
})

// ----------------------------------------------------------------------------
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


