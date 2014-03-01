name := "fuckingaboutwithspray"

scalaVersion := "2.11.0-M8"

scalacOptions ++= Seq("-Xfatal-warnings", "-language:higherKinds", "-Xlint")

resolvers ++= Seq(
  "atlassian-proxy" at "https://m2proxy.atlassian.com/content/groups/internal/",
  "atlassian-maven" at "http://maven.atlassian.com/content/groups/public/",
  "spray-repo"      at "http://repo.spray.io"
)

libraryDependencies ++= Seq(
    "io.spray"      %   "spray-can"         % "1.3-RC4",
    "io.spray"      %   "spray-routing"     % "1.3-RC4"
)
