package tom.spark.utils.evaluation

import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils

trait Evaluator {
  val seed = 42
  
  def nFoldEvaluation(data: RDD[LabeledPoint], n: Int = 10): Array[Evaluation] = {
    val folds = MLUtils.kFold(data, 10, seed)
    folds.map(fold => evaluate(fold._1, fold._2))
  }

  def evaluate(train: RDD[LabeledPoint], test: RDD[LabeledPoint]): Evaluation
}