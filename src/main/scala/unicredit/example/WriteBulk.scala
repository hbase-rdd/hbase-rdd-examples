package unicredit.example

import org.apache.spark.{ SparkContext, SparkConf }
import unicredit.spark.hbase._


object WriteBulk extends App {
  val name = "Example of write using HFiles"

  lazy val sparkConf = new SparkConf().setAppName(name)
  lazy val sc = new SparkContext(sparkConf)
  implicit val config = HBaseConfig() // Assumes hbase-site.xml is on classpath
  val table = "test-table-bulk"
  val file = "test-input"
  val cf = "cf1"

  if (prepareTable(table, cf, file, "1G", null, takeSnapshot = false)) {
    sc.textFile(file)
      .map({ line =>
        val Array(k, col1, col2, _) = line split "\t"
        val content = Map("col1" -> col1, "col2" -> col2)

        k -> content
      })
      .loadToHBase(table, "cf1")
  }
}