Fucking about with Spray
========================
Figure out how to Spray


I should be able to
-------------------

 * Run the project with scala 2.10.3 or 2.11-rcwhateverthefuckweareupto
 * Run the project using sbt 0.12.4 (This the latest?)
 * Have an app, not just some code
 * Run the app with `sbt run` or `run` on the sbt command line
 * Run the app using spray/sbt-revolver (`sbt re-start`?)
 * Not have any pom.xml at all
 * Load the sbt file into IDEA without a hassle, or a documented hassle
 * Run the Scala repl in IDEA with the project classpath from sbt
 * Either run sbt-revolver in IDEA, or run sbt directly without too many hacks (IE, CLI -> SBT -> sbt-resolver == bad)
 * Incorporate findbugs and other things into my project
 * Utilise Akka actors
 * Test my project
 * Test my routing without spinning up actors


Things I can't yet do
---------------------

 * Actually have an app.
 * Run the Scala repl in IDEA with the project classpath from sbt
 * Run sbt-revolver in IDEA
 * Incorporate findbugs
 * Actorise
 * Test


Things I've actually acheived so far
------------------------------------
 * Project runs with Scala 2.11.0-RC1, specified in build.sbt.
 * Project runs with sbt 0.13.1, specified in project/build.properties.
 * Project has no pom as of yet.
 * Project sbt file is loadable on the command line and directly by IntelliJ IDEA with sbt plugin.
 * Project depends on spray-can/spray-routing 1.3-RC4
 * Project depends on akka 2.3.0-RC4
 * Project pulls in 0.7.1 of sbt-revolver, though it cannot be used yet (nothing to re-start)
 * Routing is separate from the Actor that runs it -- testing routing doesn't require Akka
 * Project attempts to run with `sbt run`. Fun with Akka prevents this from being useful
 * sbt-revolver does fun revolving stuff with `sbt re-start`
