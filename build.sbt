
name := "hive_p2"

version := "0.1"

scalaVersion := "2.12.3"

resolvers += "conjars" at "http://conjars.org/repo"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

mainClass in assembly := Some("com.example.UserAgentParser")

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % "2.12.3",
  "org.apache.hadoop" % "hadoop-common" % "2.7.3" % "provided",
  "org.apache.hive" % "hive-exec" % "2.3.0" % "provided",
  "eu.bitwalker" % "UserAgentUtils" % "1.20",
  "org.scalatest" % "scalatest_2.12" % "3.0.3" % "test"
)