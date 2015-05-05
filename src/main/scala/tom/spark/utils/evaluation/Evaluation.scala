package tom.spark.utils.evaluation

import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.rdd.RDD
import org.apache.spark.rdd.RDD.doubleRDDToDoubleRDDFunctions
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics

abstract class Evaluation(predict: (Vector => Double), data: RDD[LabeledPoint]) {

  lazy val numberOfTestCases = data.count()
  
  lazy val predictions = data.map { point =>
    (point.label, predict(point.features))
  }.cache()

}