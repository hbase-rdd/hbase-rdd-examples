package unicredit.example

import org.apache.spark.{ SparkContext, SparkConf }
import unicredit.spark.hbase._


object WriteMultiCf extends App {
  val name = "Example of write on multiple column families"

  lazy val sparkConf = new SparkConf().setAppName(name)
  lazy val sc = new SparkContext(sparkConf)
  implicit val config = HBaseConfig() // Assumes hbase-site.xml is on classpath

  sc.textFile("test-input")
    .map({ line =>
      val Array(k, col1, col2, col3) = line split "\t"
      val content = Map(
        "cf1" -> Map("col1" -> col1, "col2" -> col2),
        "cf2" -> Map("col3" -> col3)
      )

      k -> content
    })
    .tohbase("test-table")
}