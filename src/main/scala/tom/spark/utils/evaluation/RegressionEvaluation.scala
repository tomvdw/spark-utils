package tom.spark.utils.evaluation

import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD

case class RegressionEvaluation(predict: (Vector => Double), data: RDD[LabeledPoint])
  extends Evaluation(predict, data) {

  lazy val squaredErrors = {
    predictions.map {
      case (actualValue, prediction) => math.pow((actualValue - prediction), 2)
    }
  }

  lazy val MSE = squaredErrors.mean()

}