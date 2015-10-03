// vi:syntax=scala

lazy val deps = Seq(
  "org.twitter4j" % "twitter4j-stream" % "4.0.4",
  "com.typesafe" % "config" % "1.3.0",
  "org.apache.spark" %% "spark-core" % "1.5.0" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.5.0" % "provided",
  "org.apache.spark" %% "spark-sql" % "1.5.0",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.5.0",
  "org.apache.hadoop" % "hadoop-hdfs" % "2.6.0-cdh5.4.7" % "provided",
  "org.apache.parquet" % "parquet-common" % "1.8.1" % "provided",
  "org.apache.parquet" % "parquet-avro" % "1.8.1"
)

val meta = """META.INF(.)*""".r

lazy val root = 
  (project in file(".")).
    settings(
      organization := "com.namabile",
      name := "spark-etl",
      version := "1.0",
      scalaVersion := "2.11.6",
      javaOptions += "-Xmx2g",
      resolvers += "Cloudera CDH 5" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
      libraryDependencies ++= deps,
      mergeStrategy in assembly := { 
        case PathList("org", "apache", "spark", "unused", "UnusedStubClass.class") => MergeStrategy.first
        case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
        case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
        case PathList("org", "apache", xs @ _*) => MergeStrategy.last
        case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
        case PathList("com", "google", "common", "base", xs @ _*) => MergeStrategy.last
        case PathList("plugin.properties") => MergeStrategy.last
        case meta(_) => MergeStrategy.discard
        case x => (mergeStrategy in assembly).value(x)
    }
  )
