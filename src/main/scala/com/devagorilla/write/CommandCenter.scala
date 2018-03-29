package com.devagorilla.write

import akka.actor.{Actor, ActorContext, ActorLogging, Props}
import com.devagorilla.domain.CommandModel.{CommandEnvelope, CommandModelMessage, WriteSuccess}

object CommandCenter {

  def props: Props = Props[CommandCenter]
}

class CommandCenter extends Actor with ActorLogging {

  override def receive: Receive = {
    case e: CommandEnvelope =>
      handleEvent(e)
      sender ! WriteSuccess("perfect")

  }

  private def handleEvent(e: CommandEnvelope): Unit = {

    println(s"Receive command ${e.data} at ${e.timeStamp}")
  }

}
