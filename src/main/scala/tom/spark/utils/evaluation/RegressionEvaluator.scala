package tom.spark.utils.evaluation

import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD

trait RegressionEvaluator extends Evaluator {
  
  def evaluate(train: RDD[LabeledPoint], test: RDD[LabeledPoint]): ClassificationEvaluation

}