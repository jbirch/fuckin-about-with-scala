name := "fuckingaboutwithspray"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-Xfatal-warnings", "-language:higherKinds", "-Xlint")

resolvers ++= Seq(
  "spray-repo"          at  "https://repo.spray.io",
  "sonatype-releases"   at  "https://oss.sonatype.org/content/repositories/releases",
  "scalaz-bintray"      at  "https://dl.bintray.com/scalaz/releases"
)

libraryDependencies ++=  {
  val sprayV  = "1.3.3"
  val akkaV   = "2.3.9"
  val specs2V = "3.3.1"
  Seq(
    "io.spray"          %%  "spray-can"             % sprayV,
    "io.spray"          %%  "spray-routing"         % sprayV,
    "io.spray"          %%  "spray-testkit"         % sprayV  % "test",
    "com.typesafe.akka" %%  "akka-actor"            % akkaV,
    "org.specs2"        %%  "specs2"                % specs2V % "test"
  )
}

Revolver.settings

org.scalastyle.sbt.ScalastylePlugin.Settings

net.virtualvoid.sbt.graph.Plugin.graphSettings
