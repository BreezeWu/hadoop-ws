package org.wuhz.akka

import akka.actor.{Props, ActorSystem}

/**
 * Created by hadoop on 10/31/14.
 */
object StudentSimulatorApp extends App{
  //Initialize the ActorSystem
  val actorSystem=ActorSystem("UniversityMessageSystem")

  //construct the Teacher Actor Ref
  val teacherActorRef=actorSystem.actorOf(Props[TeacherActor])
  //send a message to the Teacher Actor
  teacherActorRef!QuoteRequest

  //Let's wait for a couple of seconds before we shut down the system
  Thread.sleep (2000)
  //Shut down the ActorSystem.
  actorSystem.shutdown()
}

