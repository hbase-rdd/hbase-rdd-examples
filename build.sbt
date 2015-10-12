import AssemblyKeys._

name := "hbase-rdd-examples"

version := "0.7.0"

scalaVersion := "2.10.6"

assemblySettings

assemblyOption in assembly ~= { _.copy(includeScala = false) }

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-language:postfixOps"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.3.0" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.3.0" % "provided",
  "org.apache.hbase" % "hbase-common" % "1.0.0" % "provided",
  "org.apache.hbase" % "hbase-client" % "1.0.0" % "provided",
  "org.apache.hbase" % "hbase-server" % "1.0.0" % "provided",
  "eu.unicredit" %% "hbase-rdd" % "0.7.0"
)