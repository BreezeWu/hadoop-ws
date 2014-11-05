package org.wuhz.akka

import akka.actor.{Props, ActorRef, ActorSystem}

//import scala.actors.ActorRef





/**
 * Created by hadoop on 10/31/14.
 */
class HelloAkka {

  // 1. 初始化ActorSystem
  val system = ActorSystem("UniversityMessageSystem")

  // 2. 为Teacher Actor创建代理
  val teacherActorRef:ActorRef=actorSystem.actorOf(Props[TeacherActor])

  // 3. 将QuoteRequest message 发送到代理中
  // 你仅仅告诉(tell)QuoteRequest message到ActorRef中，Actor中的tell方法是! 如下：
  //send a message to the Teacher Actor
  teacherActorRef!QuoteRequest


}
