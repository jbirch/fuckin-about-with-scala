name := "fuckingaboutwithspray"

scalaVersion := "2.10.4-RC3"

scalacOptions ++= Seq("-Xfatal-warnings", "-language:higherKinds", "-Xlint")

resolvers ++= Seq(
  "spray-repo"          at  "http://repo.spray.io",
  "sonatype-releases"   at  "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++=  {
  val sprayV  = "1.3-RC4"
  val akkaV   = "2.3.0-RC4"
  val specs2V = "2.3.8"
  Seq(
    "io.spray"          %   "spray-can"             % sprayV,
    "io.spray"          %   "spray-routing"         % sprayV,
    "io.spray"          %   "spray-testkit"         % sprayV  % "test",
    "com.typesafe.akka" %%  "akka-actor"            % akkaV,
    "org.specs2"        %%  "specs2"                % specs2V % "test"
  )
}

Revolver.settings

org.scalastyle.sbt.ScalastylePlugin.Settings
