package org.wuhz.akka

import scala.sys.Prop

/**
 * Created by hadoop on 10/31/14.
 */
object HelloActor {
  import akka.actor._

  class GreatingActor extends Actor {
    def receive = {
      case s:String => println(s)
    }
  }

  val system = ActorSystem("greeting")
  //val actor = system.actorOf(Prop[GreatingActor], name="Hello World Actor")
  val actor = system.actorOf(Prop[GreatingActor], name="Hello World Actor")
  actor ! "Hello World!"

  Thread.sleep(50)
  system.shutdown()
}
