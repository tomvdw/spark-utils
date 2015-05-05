package tom.spark.utils.evaluation

import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint

trait ClassificationEvaluator extends Evaluator {

  def evaluate(train: RDD[LabeledPoint], test: RDD[LabeledPoint]): ClassificationEvaluation

}