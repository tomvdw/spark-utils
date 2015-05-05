package tom.spark.utils

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext

case class CsvReader(rawData: RDD[String], separator: String = ",") {

  private lazy val indexedRawData: RDD[(String, Long)] = rawData.zipWithIndex()
  
  def getHeaderString: String = {
    val firstRow = indexedRawData.filter(_._2 == 0)
    val rowValue = firstRow.map(_._1).collect()
    rowValue.apply(0)
  }
  
  def getHeaders: Array[String] = getHeaderString.split(separator)
  
  def getIndexedDataRows = indexedRawData.filter(_._2 > 0)
  def getDataRows = getIndexedDataRows.map(_._1)
}