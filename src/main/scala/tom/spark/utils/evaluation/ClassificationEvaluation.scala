package tom.spark.utils.evaluation

import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics

case class ClassificationEvaluation(predict: (Vector => Double), data: RDD[LabeledPoint])
  extends Evaluation(predict, data) {
  
  lazy val classificationMetrics = new BinaryClassificationMetrics(predictions)
  
  lazy val correctPredictions = predictions.filter(r => r._1 == r._2)
  lazy val incorrectPredictions = predictions.filter(r => r._1 != r._2)
  
  val numberOfCorrectPredictions = correctPredictions.count()
  val numberOfIncorrectPredictions = incorrectPredictions.count()
  
  val errorPercentage = numberOfIncorrectPredictions.toDouble / numberOfTestCases

}