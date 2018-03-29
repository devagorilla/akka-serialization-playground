package com.devagorilla.domain

import java.time.ZonedDateTime

import com.endiag.common.messages.DeviceCommandEvent

object CommandModel {

  sealed trait CommandModelMessage
  final case class CommandEnvelope(data: String, timeStamp: ZonedDateTime) extends CommandModelMessage

  sealed trait CommandModelEvent
  final case class WriteSuccess(msg: String) extends CommandModelEvent
  //final case class WriteFailed(ex: Throwable) extends CommandModelEvent

}

