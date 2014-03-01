package com.fuckingaroundwithspray

import akka.actor.Actor
import spray.routing._
import spray.http.MediaTypes._

/**
 * Actor to hold and run our service. All we have to say now is "Release the
 * Actors!" and watch an application magically appear.
 */
class FuckingServiceActor extends Actor with FuckingService {
  def actorRefFactory = context
  def receive = runRoute(fuckingRoute)
}

/**
 * Service itself, separated from the actor above. Allows testing without
 * initialising an actor framework.
 */
trait FuckingService extends HttpService {

  val fuckingRoute = {
    path("") {
      respondWithMediaType(`text/plain`) {
        complete("you get NOTHING!")
      }
    }
  }
}
