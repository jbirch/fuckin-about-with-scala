Fucking about with Spray
========================
Figure out how to Spray


I should be able to
-------------------

 * Run the project with scala 2.11+
 * Run the project using sbt 0.13+
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
 * Easily see the dependency tree
 * Easily release


Things I can't yet do
---------------------

 * Incorporate findbugs
 * release


Things I've actually acheived so far
------------------------------------
 * Identified and defeated a compiler bug between Scala 2.11.0-M8 and 2.11.0-RC1.
 * Project runs with Scala 2.11.9, specified in build.sbt.
 * Project runs with sbt 0.13.9, specified in project/build.properties.
 * Project has no pom as of yet.
 * Project sbt file is loadable on the command line and directly by IntelliJ IDEA with sbt plugin.
 * Project depends on spray-can/spray-routing 1.3.3
 * Project depends on akka 2.3.3
 * Project pulls in 0.7.1 of sbt-revolver
 * Routing is separate from the Actor that runs it -- testing routing doesn't require Akka
 * Project runs with `sbt run`.
 * sbt-revolver does fun revolving stuff with `~re-start` in the sbt console
 * sbt-revolver can work in IDEA using the SBT plugin's SBT console.
 * IDEA can run a scala intepreter with the project on the classpath. Great for quick feedback.
 * Application is run by the Actor system, not directly.
 * Incorporate scalastyle (`sbt scalastyle`) for common issues. Generated with `scalastyle-generate-config`, minus the header option.
 * Test using specs2
 * Test without explicitly spinning up Akka actors (Uses test actors in RouteTest)
 * Ability to inspect the dependency tree


Things you can do from the terminal
-----------------------------------

 * __sbt compile__ - Compiles the application
 * __sbt run__ - Runs the application
 * __sbt test__ - Runs the application tests
 * __sbt scalastyle__ - Runs scalastyle over the project
 * __sbt console__ - Starts a Scala REPL with the project classpath
 * __sbt dependency-tree__ - Shows an ascii dependency tree
 * __sbt dependency-dot__ - Generates a GraphViz dot file of the dependency tree.


Things you can do from the sbt cli
----------------------------------

 * __compile__ - Compiles the application
 * __run__ - Runs the application
 * __test__ - Runs the application tests
 * __scalastyle__ - Runs scalastyle over the project
 * __console__ - Starts a Scala REPL with the project classpath
 * __re-start__ - Runs the application in a forked JVM
 * __~re-start__ - as __re-start__, but will kill the old process and re-fork on source changes
 * __re-stop__ - Kills the forked-out application
 * __re-status__ - Shows information about the current forked out application
 * __dependency-tree__ - Shows an ASCII dependency tree


Caveats
-------

__Running sbt-revolver and IDEA compiles things twice__. Because they have different target roots but both notice source changes, every file results in a double-compile. This can result in lock contention on the Ivy cache, depending on what you change.

__There's a random message about deadletters on application start__. I don't know what this means:

    [INFO] [03/02/2014 16:00:26.769] [default-akka.actor.default-dispatcher-2] [akka://default/deadLetters] Message [akka.io.Tcp$Bound] from Actor[akka://default/user/IO-HTTP/listener-0#1393455647] to Actor[akka://default/deadLetters] was not delivered. [1] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.

__Scalastyle generates a useless check by default__. `sbt scalastyle-generate-config` will generate a config file that includes org.scalastyle.file.HeaderMatchesChecker by default. This is for ensuring the first lines of every file match what's defined in this check. This is generally pretty useless unless you're enforcing licensing arrangements.

__I don't understand implicits__. There was a point in my test when I bolted on the Specs2RouteTest trait to my test. It complained that I hadn't implemented abstract member actorRefFactory, though it helpfully offered to put in either `implicit def actorRefFactory: ActorRefFactory = ???` or `override implicit def actorRefFactory: ActorRefFactory = ???` for me. Even after replacing `???` with `system` (implicit val inside RouteTest), everything looked fine, but didn't compile! The `~>` function below was being over-shadowed by our implicit def. The solution is to simply use `def actorRefFactory: ActorRefFactory = system`. This is not immediately clear, and IDE integration here proved to be helpful, but not entirely so and slightly misleading.

    implicit class WithTransformation2(request: HttpRequest) {
      def ~>[A, B](f: A ⇒ B)(implicit ta: TildeArrow[A, B]): ta.Out = ta(request, f)
    }
