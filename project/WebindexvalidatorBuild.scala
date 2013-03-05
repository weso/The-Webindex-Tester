import sbt._
import sbt.Keys._

object WebindexvalidatorBuild extends Build {

  val junitV = "4.11"
  val cucumberV = "1.0.9"
  val scalatestV = "2.0.M5b"
  val scalaV = "2.9.2"

  lazy val webindexvalidator = Project(
    id = "webindex-validator",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "WebIndex-Validator",
      organization := "webindex-validator",
      version := "0.10-SNAPSHOT",
      scalaVersion := scalaV,
      libraryDependencies += "junit" % "junit" % junitV,
      libraryDependencies += "org.scalatest" %% "scalatest" % scalatestV % "test",
      libraryDependencies += "info.cukes" % "cucumber-jvm" % cucumberV % "test",
      libraryDependencies += "info.cukes" % "cucumber-core" % cucumberV % "test",
      libraryDependencies += "info.cukes" % "cucumber-scala" % cucumberV % "test",
      libraryDependencies += "info.cukes" % "cucumber-junit" % cucumberV % "test",
      
      libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.31.0",
        
      libraryDependencies += "com.hp.hpl.jena" % "jena" % "2.6.4",
      libraryDependencies += "com.hp.hpl.jena" % "arq" % "2.8.8",
      libraryDependencies += "org.scardf" % "scardf" % "0.5" from "http://scardf.googlecode.com/files/scardf-0.5.jar",
      
      resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
      resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/")
    )
}