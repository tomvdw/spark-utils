package tom.spark.utils.csv

import org.apache.spark.rdd.RDD
import java.io.PrintWriter
import java.io.File

object CsvWriter {
  def write(path: String, data: RDD[String], header: String = "") {
    val printWriter = new PrintWriter(new File(path))
    if (!header.isEmpty()) printWriter.write(header + "\n")
    data.foreach { line => printWriter.write(line + "\n") }
    printWriter.close()
  }
}