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

  if (tableExists(table, cf)) {
    sc.textFile(file)
      .map({ line =>
        val Array(k, col1, col2, _) = line split "\t"
        val content = Map("col1" -> col1, "col2" -> col2)

        k -> content
      })
      .toHBaseBulk(table, cf)
  }
}