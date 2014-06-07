name := "[TODO-APP-NAME-HERE]"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

resolvers += Classpaths.sbtPluginReleases

libraryDependencies ++= {
  val playVersion = "2.3.0"
  val slickVersion = "2.0.2"
  Seq(
  	jdbc,
  	"com.typesafe.play" %% "play" % playVersion,
  	"com.typesafe.play" %% "play-slick" % "0.6.0.1",
    "postgresql" % "postgresql" % "9.3-1101.jdbc4",
    "org.seleniumhq.selenium" % "selenium-java" % "2.42.2",
    "org.scala-lang" % "scala-swing" % "2.10.4",
    "org.scala-lang" % "scala-reflect" % "2.10.4",
    "org.scala-lang.modules" %% "scala-async" % "0.9.1"
  )
}