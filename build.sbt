name := "playful-prelaunch"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

val playVersion = "2.3.0"

resolvers += Classpaths.sbtPluginReleases

libraryDependencies ++= {
  Seq(
  	jdbc,
    anorm,
    ws,
    "com.typesafe.play" %% "play" % playVersion,
    "org.postgresql" % "postgresql" % "9.3-1101-jdbc41",
    "org.seleniumhq.selenium" % "selenium-java" % "2.42.2",
    "org.webjars" %% "webjars-play" % playVersion,
    "org.webjars" % "bootstrap" % "3.1.1-1"
  )
}

scalacOptions ++= Seq(
    "-feature",
    "-language:postfixOps"  //This allows the postfix operator * to be enabled
)
