/* Copyright 2015 UniCredit S.p.A.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package unicredit.example

import org.apache.spark.{SparkConf, SparkContext}
import unicredit.spark.hbase._


object ReadTSCf extends App {
  val name = "Example of read from HBase table (whole column family)"

  lazy val sparkConf = new SparkConf().setAppName(name)
  lazy val sc = new SparkContext(sparkConf)
  implicit val config = HBaseConfig() // Assumes hbase-site.xml is on classpath

  sc.hbaseTS[String]("test-table", Set("cf1", "cf2"))
    .map({ case (k, v) =>
      val cf1 = v("cf1")
      val col1 = cf1("col1")
      val col2 = cf1("col2")
      val col3 = v("cf2")("col3")

      List(k, col1, col2, col3) mkString "\t"
    })
    .saveAsTextFile("test-output")
}