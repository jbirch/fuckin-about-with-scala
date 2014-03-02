package com.fuckingaroundwithspray

import org.specs2.mutable.Specification
import akka.actor.ActorRefFactory
import spray.testkit.Specs2RouteTest
import spray.http.StatusCodes

class FuckingServiceTest extends Specification with Specs2RouteTest with FuckingService {
  //system comes from Specs2RouteTest
  def actorRefFactory: ActorRefFactory = system

  "The fucking service" should {
    "return a lame string" in {
      Get() ~> fuckingRoute ~> check {
        responseAs[String] mustEqual "you get NOTHING!"
      }
    }

    "always succeed" in {
      Get() ~> fuckingRoute ~> check {
        status mustEqual StatusCodes.OK
      }
    }
  }
}
