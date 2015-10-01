// vi:syntax=scala

lazy val twitter4j = "org.twitter4j" % "twitter4j-stream" % "4.0.4"
lazy val configFactory = "com.typesafe" % "config" % "1.3.0"
lazy val sparkCore = "org.apache.spark" %% "spark-core" % "1.5.0" % "provided"
lazy val sparkStreaming = "org.apache.spark" %% "spark-streaming" % "1.5.0" % "provided"
lazy val sparkStreamingKafka = "org.apache.spark" %% "spark-streaming-kafka" % "1.5.0"
lazy val avro = "org.apache.avro" % "avro" % "1.7.7"
lazy val hadoopClient = "org.apache.hadoop" % "hadoop-hdfs" % "2.6.0-cdh5.4.7" % "provided"

lazy val root = 
  (project in file(".")).
    settings(
      organization := "com.namabile",
      name := "spark-etl",
      version := "1.0",
      scalaVersion := "2.11.6",
      javaOptions += "-Xmx2g",
      resolvers += "Cloudera CDH 5" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
      libraryDependencies ++= Seq(
        twitter4j, 
        configFactory,
        sparkCore,
        sparkStreaming,
        sparkStreamingKafka,
        avro,
        hadoopClient
      ),
      mergeStrategy in assembly := {
        case PathList("org", "apache", "spark", "unused", "UnusedStubClass.class") => MergeStrategy.first
        case x => (mergeStrategy in assembly).value(x)
    }
  )
