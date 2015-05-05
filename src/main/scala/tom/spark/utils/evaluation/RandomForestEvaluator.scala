package tom.spark.utils.evaluation

import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.mllib.util.MLUtils

class RandomForestEvaluator(
  val numClasses: Int,
  val categoricalFeaturesInfo: Map[Int, Int],
  val numTrees: Int = 100,
  val featureSubsetStrategy: String = "auto",
  val impurity: String = "gini",
  val maxDepth: Int = 5,
  val maxBins: Int = 32) extends ClassificationEvaluator {

  def evaluate(train: RDD[LabeledPoint], test: RDD[LabeledPoint]): ClassificationEvaluation = {
    val model = trainClassifier(train)
    ClassificationEvaluation(model.predict, test)
  }

  private def trainClassifier(train: RDD[LabeledPoint]) = {
    RandomForest.trainClassifier(train, numClasses, categoricalFeaturesInfo, numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)
  }

}