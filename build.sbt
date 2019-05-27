name := "hbase-rdd-examples"

version := "0.9.0"

crossScalaVersions := Seq("2.11.12", "2.12.8")

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-language:postfixOps"
)

resolvers ++= Seq(
  "Cloudera repos" at "https://repository.cloudera.com/artifactory/cloudera-repos",
  "Cloudera releases" at "https://repository.cloudera.com/artifactory/libs-release"
)

val sparkVersion = "2.4.3"
val hbaseVersion = "2.1.0-cdh6.2.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.hbase" % "hbase-common" % hbaseVersion % "provided",
  "org.apache.hbase" % "hbase-client" % hbaseVersion % "provided",
  "eu.unicredit" %% "hbase-rdd" % version.value
)