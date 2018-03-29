package com.devagorilla

import java.time.ZonedDateTime

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern._
import akka.util.Timeout
import com.devagorilla.domain.CommandModel.WriteSuccess
import com.devagorilla.write.CommandCenter
import com.devagorilla.domain.CommandModel.{CommandEnvelope, WriteSuccess}
import com.devagorilla.write.CommandCenter

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Main extends App {

  implicit val time: Timeout = 1.seconds

  // Create the 'helloAkka' actor system
  val system: ActorSystem = ActorSystem("play-ground")

  //#create-actors
  // Create the printer actor
  val writer: ActorRef = system.actorOf(CommandCenter.props, "commandReceiver")

  val command1 = CommandEnvelope("message 1", ZonedDateTime.now())
  //val command2 = CommandEnvelope("message 2", ZonedDateTime.now())

  Await.result((writer ? command1).mapTo[WriteSuccess], 1.second)

//  Thread.sleep(1000L)
//
//  writer ! command2

  system.terminate()
}
