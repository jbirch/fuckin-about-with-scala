name := "fuckingaboutwithspray"

scalaVersion := "2.10.4-RC3"

scalacOptions ++= Seq("-Xfatal-warnings", "-language:higherKinds", "-Xlint")

resolvers ++= Seq(
  "spray-repo"          at  "http://repo.spray.io",
  "sonatype-releases"   at  "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++=  {
  val sprayV    = "1.3-RC4"
  val akkaV     = "2.3.0-RC4"
  Seq(
    "io.spray"          %   "spray-can"             % sprayV,
    "io.spray"          %   "spray-routing"         % sprayV,
    "com.typesafe.akka" %%  "akka-actor"            % akkaV,
    "org.scalastyle"    %%  "scalastyle-sbt-plugin" % "0.4.0"
  )
}

Revolver.settings

org.scalastyle.sbt.ScalastylePlugin.Settings
