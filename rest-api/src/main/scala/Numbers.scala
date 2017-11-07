package com.example

import argonaut._, Argonaut._

import unfiltered.request._
import unfiltered.response._

import unfiltered.netty._

/** unfiltered plan */
@io.netty.channel.ChannelHandler.Sharable
object Numbers extends cycle.Plan
  with cycle.SynchronousExecution with ServerErrorResponse {
  //import QParams._

  private val numberStore = new NumberStore()

  def intent = {
    case GET(Path("/numbers")) => 
      JsonContent ~> ResponseString(numberStore.listNumbers().asJson.spaces4)

    case GET(Path(Seg("numbers" :: "sum" :: Nil))) =>
      JsonContent ~> ResponseString(Json("sum" -> jNumber(numberStore.sumNumbers())).spaces4)

    case GET(Path(Seg("numbers" :: num :: "fizzbuzz" :: Nil))) =>
      numberStore.getNumber(num) match {
        case Some(number) => 
          JsonContent ~> ResponseString((("fizzbuzz", jString(number.fizzbuzz)) ->: number.asJson).spaces4)
        case None => 
          NotFound ~> JsonContent ~> ResponseString(Json("error" := s"unknown number '$num'").spaces4)
      }

    case req @ Path(Seg("numbers" :: num :: Nil)) => req match {
      case GET(_) =>
        numberStore.getNumber(num) match {
          case Some(number) => 
            JsonContent ~> ResponseString(number.asJson.spaces4)
          case None => 
            NotFound ~> JsonContent ~> ResponseString(Json("error" := s"unknown number '$num'").spaces4)
        }
      case _ =>
        MethodNotAllowed ~> JsonContent ~> ResponseString(Json("error" := "unimplemented method").spaces4)
    }
  }
}
