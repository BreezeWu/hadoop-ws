// 假定:全部特征都是连续的

// ----------------------------------------------------------------------------
// 执行决策树算法
import org.apache.spark.mllib.util.MLUtils

// Load and parse the data file.
// Cache the data since we will use it again to compute training error.
// hdfs dfs -mkdir -p sample_libsvm_data.txt data/mllib/
// hdfs dfs -copyFromLocal -p sample_libsvm_data.txt data/mllib/sample_libsvm_data.txt
//val data = MLUtils.loadLibSVMFile(sc, "data/mllib/sample_libsvm_data.txt").cache()

/*
// ----------------------------------------------------------------------------
// 随机数模拟 dense LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

import scala.util.Random
import scala.math.abs
val randomer = new Random(100)

val range = Range(1, 11)
val randomData = for( i<- range) yield {
  val label = abs(randomer.nextInt(2).toDouble)
  val vector = Vectors.dense(Array(
    randomer.nextInt(10),
    randomer.nextInt(20),
    randomer.nextInt(100),
    randomer.nextInt(79),
    randomer.nextInt(110),
    randomer.nextInt(899)
  ).map(x => scala.math.abs(x).toDouble))
  new LabeledPoint(label, vector)
}
val data = sc.parallelize(randomData)
 */


// ----------------------------------------------------------------------------
// 把所有都当作连续的
// Train a DecisionTree model.
//  Empty categoricalFeaturesInfo indicates all features are continuous.
val numClasses = perfectK // 50 //2
val categoricalFeaturesInfo = Map[Int, Int]()
val impurity = "gini"
val maxDepth = 5
val maxBins = 100

val dataForDecisionTree = labeledPointRdd
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

