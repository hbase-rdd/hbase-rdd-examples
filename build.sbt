import AssemblyKeys._

name := "hbase-rdd-examples"

version := "0.8.0"

crossScalaVersions := Seq("2.10.6", "2.11.8")

assemblySettings

assemblyOption in assembly ~= { _.copy(includeScala = false) }

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-language:postfixOps"
)

resolvers ++= Seq(
  "Cloudera repos" at "https://repository.cloudera.com/artifactory/cloudera-repos",
  "Cloudera releases" at "https://repository.cloudera.com/artifactory/libs-release"
)

val sparkVersion = "1.5.0"
val hbaseVersion = "1.0.0-cdh5.5.2"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.hbase" % "hbase-common" % hbaseVersion % "provided",
  "org.apache.hbase" % "hbase-client" % hbaseVersion % "provided",
  "eu.unicredit" %% "hbase-rdd" % version.value
)