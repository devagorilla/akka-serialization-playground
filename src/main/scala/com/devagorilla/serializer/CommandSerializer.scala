package com.devagorilla.serializer

import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets

import akka.serialization.SerializerWithStringManifest
import com.devagorilla.domain.CommandModel.{CommandEnvelope, WriteSuccess}
import com.devagorilla.domain.CommandModel.{CommandEnvelope, WriteSuccess}
import com.sksamuel.avro4s.{AvroInputStream, AvroOutputStream}
import com.devagorilla.serializer.CustomMapping._

/**
  *   sealed trait CommandModelMessage
  final case class CommandEnvelope(data: DeviceCommandEvent, timeStamp: ZonedDateTime) extends CommandModelMessage

  sealed trait CommandModelEvent
  final case object WriteSuccess extends CommandModelEvent
  final case object WriteFailed extends CommandModelEvent
  */
class CommandSerializer extends SerializerWithStringManifest {

  val CommandEnvelopeManifest = "CommandEnvelope"
  val WriteSuccessManifest = "WriteSuccess"

  val UTF_8 = StandardCharsets.UTF_8.name()


  // Pick a unique identifier for your Serializer,
  // you've got a couple of billions to choose from,
  // 0 - 40 is reserved by Akka itself
  def identifier = 902450001

  // The manifest (type hint) that will be provided in the fromBinary method
  // Use `""` if manifest is not needed.
  def manifest(obj: AnyRef): String =
    obj match {
      case _: CommandEnvelope => CommandEnvelopeManifest
      case _: WriteSuccess => WriteSuccessManifest
    }

  // "toBinary" serializes the given object to an Array of Bytes
  def toBinary(obj: AnyRef): Array[Byte] = {
    // Put the real code that serializes the object here
    obj match {
      case _: CommandEnvelope =>
        val output = new ByteArrayOutputStream
        val avro = AvroOutputStream.data[CommandEnvelope](output)
        avro.write(obj.asInstanceOf[CommandEnvelope])
        avro.close()
        output.toByteArray

      case _: WriteSuccess =>
        val output = new ByteArrayOutputStream
        val avro = AvroOutputStream.data[WriteSuccess](output)
        avro.write(obj.asInstanceOf[WriteSuccess])
        avro.close()
        output.toByteArray
    }
  }

  // "fromBinary" deserializes the given array,
  // using the type hint
  def fromBinary(bytes: Array[Byte], manifest: String): AnyRef = {
    // Put the real code that deserializes here
    manifest match {
      case CommandEnvelopeManifest ⇒
        val is = AvroInputStream.data[CommandEnvelope](bytes)
        val messages = is.iterator.toSet
        is.close()
        messages.head

      case WriteSuccessManifest ⇒
        val is = AvroInputStream.data[WriteSuccess](bytes)
        val messages = is.iterator.toSet
        is.close()
        messages.head
    }
  }


}
