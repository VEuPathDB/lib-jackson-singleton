package org.veupathdb.lib.jackson

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import java.io.InputStream

/**
 * Jackson JSON Singleton Access
 */
object Json {

  @JvmStatic
  val Mapper = ObjectMapper()
    .registerModule(JsonOrgModule())
    .registerModule(JavaTimeModule())
    .registerModule(Jdk8Module())
    .registerModule(KotlinModule.Builder()
      .enable(KotlinFeature.NullToEmptyMap)
      .enable(KotlinFeature.NullToEmptyCollection)
      .enable(KotlinFeature.SingletonSupport)
      .build())
    .registerModule(ParameterNamesModule())

  /**
   * Creates a new [ObjectNode] or [ArrayNode] based on the type [T], then
   * configures it with the given action.
   *
   * @param T Type of the node to return.
   *
   * @param action Optional action to perform on the newly created [JsonNode].
   *
   * @return The new node of type [T].
   *
   * @throws UnsupportedOperationException if attempting to create a [JsonNode]
   * that is not an [ArrayNode] or [ObjectNode].
   */
  @JvmStatic
  inline fun <reified T : JsonNode> new(action: T.() -> Unit = {}): T {
    val tmp = when (T::class) {
      ObjectNode::class -> Mapper.createObjectNode()
      ArrayNode::class  -> Mapper.createArrayNode()
      else              -> throw UnsupportedOperationException()
    }

    action(tmp as T)

    return tmp
  }

  /**
   * Creates a new [ArrayNode] instance
   *
   * @param size Presize the newly created [ArrayNode] to this size.
   *
   * @return The newly created [ArrayNode]
   */
  @JvmStatic
  @JvmOverloads
  fun newArray(size: Int = 1) = ArrayNode(Mapper.nodeFactory, size)

  /**
   * Creates a new [ObjectNode] instance
   *
   * @return The newly created [ObjectNode]
   */
  @JvmStatic
  fun newObject() = ObjectNode(Mapper.nodeFactory)

  @JvmStatic
  inline fun <reified T> from(input: String): T =
    when (T::class) {
      ObjectNode::class -> Mapper.readTree(input) as T
      ArrayNode::class  -> Mapper.readTree(input) as T
      else              -> Mapper.readValue(input, T::class.java)
    }

  @JvmStatic
  inline fun <reified T> from(input: InputStream): T =
    when (T::class) {
      ObjectNode::class -> Mapper.readTree(input) as T
      ArrayNode::class  -> Mapper.readTree(input) as T
      else              -> Mapper.readValue(input, T::class.java)
    }

  /**
   * Converts a [JsonNode] into a value of type [T].
   *
   * @param T     Type to convert the given [JsonNode] to.
   * @param input `JsonNode` to convert.
   *
   * @return The converted value.
   */
  @JvmStatic
  inline fun <reified T> from(input: JsonNode): T =
    Mapper.convertValue(input, T::class.java)

  /**
   * Converts a [JsonNode] into a value of type [T].
   *
   * @param T     Type to convert the given [JsonNode] to.
   * @param input `JsonNode` to convert.
   * @param cls   Reference class of type [T]
   *
   * @return The converted value.
   */
  @JvmStatic
  fun <T> from(input: JsonNode, cls: Class<T>): T =
    Mapper.convertValue(input, cls)
}