package com.devagorilla.serializer

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import com.sksamuel.avro4s.{FromValue, ToRecord, ToSchema, ToValue}
import org.apache.avro.Schema
import org.apache.avro.Schema.Field
import org.apache.avro.generic.GenericData.Record
import org.apache.avro.generic.GenericRecord

object CustomMapping {

  implicit object DateTimeToSchema extends ToSchema[ZonedDateTime] {
    override val schema: Schema = Schema.create(Schema.Type.STRING)
  }

  implicit object DateTimeToValue extends ToValue[ZonedDateTime] {
    override def apply(value: ZonedDateTime): String = value.format(DateTimeFormatter.ISO_DATE_TIME)
  }

  implicit object DateTimeFromValue extends FromValue[ZonedDateTime] {
    override def apply(value: Any, field: Field): ZonedDateTime = ZonedDateTime.parse(value.toString)
  }


}
