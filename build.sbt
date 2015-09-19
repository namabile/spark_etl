lazy val twitter4j = "org.twitter4j" % "twitter4j" % "4.0.4"

lazy val root = 
  (project in file(".")).
    settings(
      organization := "namabile",
      name := "hello",
      version := "1.0",
      scalaVersion := "2.11.1",
      libraryDependencies ++= Seq(twitter4j) 
  )
