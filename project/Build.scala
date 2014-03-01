import sbt._

object FuckingBuild extends Build {
  val appName = "fuckingaboutwithspray"
  val appVersion = "1.0"
  val root = Project (appName, base = file("."))
}
