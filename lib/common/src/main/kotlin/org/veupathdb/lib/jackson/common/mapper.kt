package org.veupathdb.lib.jackson.common

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import java.nio.file.Path

fun <T : ObjectMapper> T.initSharedConfig() = also {
  it.registerModule(JsonOrgModule())
    .registerModule(JavaTimeModule())
    .registerModule(Jdk8Module())
    .registerModule(
      KotlinModule.Builder()
      .enable(KotlinFeature.NullToEmptyMap)
      .enable(KotlinFeature.NullToEmptyCollection)
      .enable(KotlinFeature.SingletonSupport)
      .build())
    .registerModule(ParameterNamesModule())
    .registerModule(SimpleModule().apply {
      addSerializer(Path::class.java, object : JsonSerializer<Path>() {
        override fun serialize(value: Path?, generator: JsonGenerator, serializer: SerializerProvider) {
          if (value == null)
            generator.writeNull()
          else
            generator.writeString(value.toString())
        }
      })
    })
}
