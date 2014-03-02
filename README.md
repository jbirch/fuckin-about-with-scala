Fucking about with Spray
========================
Figure out how to Spray


I should be able to
-------------------

 * Run the project with scala 2.10.3 or 2.11-rcwhateverthefuckweareupto
 * Run the project using sbt 0.12.4+ (This the latest?)
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

 * Incorporate findbugs


Things I've actually acheived so far
------------------------------------
 * Identified and defeated a compiler bug between Scala 2.11.0-M8 and 2.11.0-RC1.
 * Project runs with Scala 2.10.3-RC3, specified in build.sbt.
 * Project runs with sbt 0.13.1, specified in project/build.properties.
 * Project has no pom as of yet.
 * Project sbt file is loadable on the command line and directly by IntelliJ IDEA with sbt plugin.
 * Project depends on spray-can/spray-routing 1.3-RC4
 * Project depends on akka 2.3.0-RC4
 * Project pulls in 0.7.1 of sbt-revolver, though it cannot be used yet (nothing to re-start)
 * Routing is separate from the Actor that runs it -- testing routing doesn't require Akka
 * Project runs with `sbt run`.
 * sbt-revolver does fun revolving stuff with `~re-start` in the sbt console
 * sbt-revolver can work in IDEA using terminal -> SBT -> ~re-start. Terrible.
 * IDEA can run a scala intepreter with the project on the classpath. Great for quick feedback.
 * Application is run by the Actor system, not directly.
 * Incorporate scalastyle (`sbt scalastyle`) for common issues. Generated with `scalastyle-generate-config`, minus the header option.
 * Test using specs2
 * Test without explicitly spinning up Akka actors (Uses test actors in RouteTest)


Caveats
-------

__There's no Spray built for Scala 2.11 yet__. Presumably because of transitive dependencies -- Despite Spray.io dependencies being expressed with `%` instead of `%%`, they are very much tied to the underlying Scala version and versions of Akka, Specs2, and Scalaz compiled against that version as well. 

__It looks like Spray will work with Scala 2.11, but it doesn't__. Changing the Scala version to something like 2.11.0-RC1 will compile file. However, due to transitive dependencies on an Akka compiled against 2.10, you'll get NoClassDefFoundErrors in Akka on inner classes. In my case, it was on scala.runtime.AbstractPartialFunction$mcVL$sp.

__Scala 2.11 modularises the scala language__. Spray has made no attempt to follow that modularisation (I guess because of the former point) and the BasicMarshallers trait has a scala.xml.NodeSeq dependency to allow XML serialisation natively. Unfortunately, this means __No basic marshallers, like StringMarshaller, are imported implicitly because of this, silently__. It's a bit of a "Fuck you" moment.

__Running sbt-revolver and IDEA compiles things twice__. Because they have different target roots but both notice source changes, every file results in a double-compile. This can result in lock contention on the Ivy cache, depending on what you change.

__There's a random message about deadletters on application start__. I don't know what this means:

    [INFO] [03/02/2014 16:00:26.769] [default-akka.actor.default-dispatcher-2] [akka://default/deadLetters] Message [akka.io.Tcp$Bound] from Actor[akka://default/user/IO-HTTP/listener-0#1393455647] to Actor[akka://default/deadLetters] was not delivered. [1] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.

__Scalastyle generates a useless check by default__. scalastyle-generate-config will generate a config file that includes org.scalastyle.file.HeaderMatchesChecker by default. This is for ensuring the first lines of every file match what's defined in this check. This is generally pretty useless unless you're enforcing licensing arrangements.

__I don't understand implicits__. There was a point in my test when I bolted on the Specs2RouteTest trait to my test. It complained that I hadn't implemented abstract member actorRefFactory, though it helpfully offered to put in either `implicit def actorRefFactory: ActorRefFactory = ???` or `override implicit def actorRefFactory: ActorRefFactory = ???` for me. Even after replacing `???` with `system` (implicit val inside RouteTest), everything looked fine, but didn't compile! The `~>` function below was being over-shadowed by our implicit def. The solution is to simply use `def actorRefFactory: ActorRefFactory = system`. This is not immediately clear, and IDE integration here proved to be helpful, but not entirely so and slightly misleading.

    implicit class WithTransformation2(request: HttpRequest) {
      def ~>[A, B](f: A ⇒ B)(implicit ta: TildeArrow[A, B]): ta.Out = ta(request, f)
    }
