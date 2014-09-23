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
// ----------------------------------------------------------------------------
// 执行"准备聚类数据"
val dataForClustering = BuildClusteringData(mappedRddData_percent)
// ----------------------------------------------------------------------------
// 运行聚类操作
val perfectK = 50;
val maxIterations = 20 // 当前没有生效
val predictRdd_Clustering = ClusteringData(dataForClustering, perfectK, maxIterations)

// ============================================================================
// ----------------------------------------------------------------------------
// 调用"将分类特征数据转换为Vector"
val labeledPointRdd = BuildClassificationData(mappedRddData_percent)
// ----------------------------------------------------------------------------
// 运行"得到分类特征的Distinct信息"
val featuresDistinctInfo = ComputeFeaturesDistinctInfo(mappedRddData_percent)
// ----------------------------------------------------------------------------
// 决策书算法参数
// 计算决策树算法所需要的 categoricalFeaturesInfo 参数
val categoricalFeaturesInfo = GetCategoricalFeaturesInfo(featuresDistinctInfo)

// 其他决策树参数
val numClasses = perfectK // 50 //2
//  Empty categoricalFeaturesInfo indicates all features are continuous.
//val categoricalFeaturesInfo = Map[Int, Int]() // 前面已经计算
val impurity = "gini"
val maxDepth = 5
// val maxBins = 100    // 该值必须大于各个分类属性的最大值
// java.lang.IllegalArgumentException: requirement failed: maxBins (= 100) should be greater than max categories in categorical features (>= 350)
val maxCategories = categoricalFeaturesInfo.values.reduce((a,b) => scala.math.max(a,b))
val maxBins = maxCategories + 1
// 但maxBins 太大会导致 java.lang.OutOfMemoryError: Java heap space
// 通过"(1)选择参与分类的属性;(2)将属性变为连续的,即调整categoricalFeaturesInfo"这两种方法 进而调整 maxBins

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


