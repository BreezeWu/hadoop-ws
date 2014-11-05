package org.wuhz.akka

import javax.xml.stream.EventFilter

import akka.actor.{ActorSystem, Props}

/**
 * Created by hadoop on 10/31/14.
 */
object DriverApp extends App {
  "A student" must {
    "log a QuoteResponse eventually when an InitSignal is sent to it" in {
      import me.rerun.akkanotes.messaging.protocols.StudentProtocol._

      val teacherRef = system.actorOf(Props[TeacherActor], "teacherActor")
      val studentRef = system.actorOf(Props(new StudentActor(teacherRef)), "studentActor")
      EventFilter.info (start="Printing from Student Actor", occurrences=1).intercept{

        studentRef!InitSignal

      }
    }
  }

  //Initialize the ActorSystem
  val system = ActorSystem("UniversityMessageSystem")
  //construct the teacher actor
  val teacherRef = system.actorOf(Props[TeacherActor], "teacherActor")

  //construct the Student Actor - pass the teacher actorref
  // as a constructor parameter to StudentActor
  val studentRef = system.actorOf(Props(new StudentActor(teacherRef)), "studentActor")
  //send a message to the Student Actor
  studentRef ! InitSignal

  //Let's wait for a couple of seconds before we shut down the system
  Thread.sleep(2000)

  //Shut down the ActorSystem.
  system.shutdown()

}
