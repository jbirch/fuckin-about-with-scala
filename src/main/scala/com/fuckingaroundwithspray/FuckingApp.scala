package com.fuckingaroundwithspray

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http


object FuckingApp extends App {

  implicit val system = ActorSystem()
  val handler = system.actorOf(Props[FuckingServiceActor], name = "fucking-service")

  IO(Http) ! Http.Bind(handler, interface = "localhost", port = 8080)
}
