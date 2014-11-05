package org.wuhz.akka

/**
 * Created by hadoop on 10/31/14.
 */
object TeacherProtocol {
  case class QuoteRequest()
  case class QuoteResponse(quoteString:String)
}
