package tom.spark.utils

import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.rdd.RDD
import org.apache.spark.rdd.RDD.doubleRDDToDoubleRDDFunctions
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics

case class Evaluation(predict: (Vector => Double), data: RDD[LabeledPoint]) {

  lazy val numberOfTestCases = data.count()
  
  lazy val predictions = data.map { point =>
    (point.label, predict(point.features))
  }.cache()

  lazy val squaredErrors = {
    predictions.map {
      case (actualValue, prediction) => math.pow((actualValue - prediction), 2)
    }
  }

  lazy val MSE = squaredErrors.mean()
  
  lazy val classificationMetrics = new BinaryClassificationMetrics(predictions)
  
  lazy val correctPredictions = predictions.filter(r => r._1 == r._2)
  lazy val incorrectPredictions = predictions.filter(r => r._1 != r._2)
  
  val numberOfCorrectPredictions = correctPredictions.count()
  val numberOfIncorrectPredictions = incorrectPredictions.count()
  
  val errorPercentage = numberOfIncorrectPredictions.toDouble / numberOfTestCases

}