package org.veupathdb.lib.jackson.yaml

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import org.veupathdb.lib.jackson.common.initSharedConfig
import java.io.InputStream
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Jackson YAML Singleton Access
 */
object Yaml {

  @JvmStatic
  val Mapper: YAMLMapper = YAMLMapper().initSharedConfig()

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
    contract {
      callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }

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
   * @param size Pre-size the newly created [ArrayNode] to this size.
   *
   * @return The newly created [ArrayNode]
   */
  @JvmStatic
  @JvmOverloads
  fun newArray(size: Int = 8) = ArrayNode(Mapper.nodeFactory, size)

  /**
   * Creates a new [ArrayNode] instance and applies the given configuration
   * function to it.
   *
   * @param size Pre-size the newly created [ArrayNode] to this size.
   *
   * @param fn Configuration function.
   *
   * @return The newly created [ArrayNode].
   */
  @JvmStatic
  inline fun newArray(size: Int = 8, fn: ArrayNode.() -> Unit): ArrayNode {
    contract {
      callsInPlace(fn, InvocationKind.EXACTLY_ONCE)
    }

    val out = newArray(size)

    out.fn()

    return out
  }

  /**
   * Creates a new [ObjectNode] instance
   *
   * @return The newly created [ObjectNode]
   */
  @JvmStatic
  fun newObject() = ObjectNode(Mapper.nodeFactory)

  /**
   * Creates a new [ObjectNode] instance and applies the given configuration
   * function to it.
   *
   * @param fn Configuration function.
   *
   * @return The newly created [ObjectNode].
   */
  @JvmStatic
  inline fun newObject(fn: ObjectNode.() -> Unit) = newObject().apply(fn)

  /**
   * Converts the given [input] to a [JsonNode] instance.
   *
   * @param input Value/stream to convert or parse into a [JsonNode] instance.
   *
   * @return Parsed/converted [JsonNode] value.
   */
  @JvmStatic
  fun convert(input: Any): JsonNode =
    Mapper.convertValue(input, JsonNode::class.java)

  /**
   * Parses the given input string into a value of type [T].
   *
   * @param input Json string to parse.
   *
   * @param T Type of the value to parse the Json into.
   *
   * @return The parsed value.
   */
  @JvmStatic
  inline fun <reified T> parse(input: String): T =
    when (T::class) {
      ObjectNode::class -> Mapper.readTree(input) as T
      ArrayNode::class  -> Mapper.readTree(input) as T
      else              -> Mapper.readValue(input, T::class.java)
    }

  /**
   * Parses the given [InputStream] into a value of type [T].
   *
   * @param input Json stream to parse.
   *
   * @param T Type of the value to parse the Json into.
   *
   * @return The parsed value.
   */
  @JvmStatic
  inline fun <reified T> parse(input: InputStream): T =
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
  inline fun <reified T> parse(input: JsonNode): T =
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
  fun <T> parse(input: JsonNode, cls: Class<T>): T =
    Mapper.convertValue(input, cls)
}
