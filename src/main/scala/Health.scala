package com.example

import unfiltered.request._
import unfiltered.response._

import unfiltered.netty._

/** unfiltered plan */
@io.netty.channel.ChannelHandler.Sharable
object Health extends cycle.Plan
  with cycle.SynchronousExecution with ServerErrorResponse {

  def intent = {
    case GET(Path("/health")) => 
      ResponseString("ok")
  }
}
