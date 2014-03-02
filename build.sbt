name := "fuckingaboutwithspray"

scalaVersion := "2.10.4-RC3"

scalacOptions ++= Seq("-Xfatal-warnings", "-language:higherKinds", "-Xlint")

resolvers ++= Seq(
  "spray-repo"  at  "http://repo.spray.io"
)

libraryDependencies ++=  {
  val sprayV    = "1.3-RC4"
  val akkaV     = "2.3.0-RC4"
  Seq(
    "io.spray"               %   "spray-can"     % sprayV,
    "io.spray"               %   "spray-routing" % sprayV,
    "com.typesafe.akka"      %%  "akka-actor"    % akkaV
  )
}

Revolver.settings
