name := "[TODO-APP-NAME-HERE]"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.2"

resolvers += Classpaths.sbtPluginReleases

libraryDependencies ++= {
  val playVersion = "2.2.3"
  val slickVersion = "2.0.2"
  Seq(
  	jdbc,
  	"com.typesafe.play" %% "play" % playVersion,
  	"com.typesafe.play" %% "play-slick" % "0.6.0.1",
    "postgresql" % "postgresql" % "9.1-901.jdbc4",
    "org.seleniumhq.selenium" % "selenium-java" % "2.35.0",
    "org.scala-lang" % "scala-swing" % "2.10.3",
    "org.scala-lang" % "scala-reflect" % "2.10.3",
    "org.scala-lang.modules" %% "scala-async" % "0.9.0-M2"
  )
}