package org.wuhz.akka

import akka.actor.{Actor, ActorRef, ActorLogging}

/**
 * Created by hadoop on 10/31/14.
 */
class StudentActor (teacherActorRef:ActorRef) extends Actor with ActorLogging {
  def receive = {
    // 第二块: StudentActor接收来自DriverApp的InitSignal 消息，然后给TeacherActor发送一个QuoteRequest。
    case InitSignal=> {
      teacherActorRef!QuoteRequest
    }

    // 第四块: StudentActor将从TeacherActor接收到的消息记到日志里面
    case QuoteResponse(quoteString) => {
      log.info ("Received QuoteResponse from Teacher")
      log.info(s"Printing from Student Actor $quoteString")
    }
  }
}
