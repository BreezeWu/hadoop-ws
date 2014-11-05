package org.wuhz.akka

import akka.actor.{ActorLogging, Actor}

/**
 * Created by hadoop on 10/31/14.
 */
class TeacherActor extends Actor with ActorLogging {
  val quotes = List(
  "Moderation is for cowards",
  "Anything worth doing is worth overdoing",
  "The trouble is you think you have time",
  "You never gonna know if you never even try")

  def receive = {
    // TeacherActor接收到了QuoteRequest消息，并且用QuoteResponse 作为响应
    case QuoteRequest => {
      import util.Random
      //Get a random Quote from the list and construct a response
      val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))

      log.info(quoteResponse.toString())

      //respond back to the Student who is the original sender of QuoteRequest
      sender ! quoteResponse
    }
  }

  //We'll cover the purpose of this method in the Testing section
  def quoteList=quotes
}
