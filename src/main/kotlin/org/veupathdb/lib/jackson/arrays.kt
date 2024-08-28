@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.jackson

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.max

/**
 * Creates a new [ArrayNode] containing elements from the receiver stream.
 *
 * @param presize A presize value to use if an expected size for the final
 * array is known ahead of time.
 *
 * @return A new [ArrayNode] containing elements from the receiver stream.
 *
 * @since v3.3.0
 */
@JvmOverloads
inline fun Sequence<Any?>.toJsonArray(presize: Int = 8) =
  Json.newArray(presize) { this@toJsonArray.forEach(::add) }

/**
 * Creates a new [ArrayNode] containing elements from the receiver stream.
 *
 * @param presize A presize value to use if an expected size for the final
 * array is known ahead of time.
 *
 * @return A new [ArrayNode] containing elements from the receiver stream.
 *
 * @since v3.3.0
 */
@JvmOverloads
inline fun Iterable<Any?>.toJsonArray(presize: Int = 8) =
  if (this is Collection<*>)
    Json.newArray(max(size, presize)) { this@toJsonArray.forEach(::add) }
  else
    Json.newArray(presize) { this@toJsonArray.forEach(::add) }

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value Byte value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.add(value: Byte): ArrayNode = add(value.toInt())

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Boolean value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Boolean?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Byte value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Byte?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Short value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Short?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Int value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Int?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Long value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Long?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value BigInteger value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: BigInteger?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Float value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Float?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Double value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Double?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value BigDecimal value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: BigDecimal?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value String value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: String?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value JSON value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: JsonNode?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value Value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
fun ArrayNode.add(value: Any?): ArrayNode =
  when (value) {
    null          -> addNull()
    is String     -> add(value)
    is JsonNode   -> add(value)
    is Int        -> add(value)
    is Long       -> add(value)
    is Double     -> add(value)
    is Boolean    -> add(value)
    is BigInteger -> add(value)
    is BigDecimal -> add(value)
    is ByteArray  -> add(value)
    is Byte       -> add(value)
    is Short      -> add(value)
    is Float      -> add(value)
    else          -> addPOJO(value)
  }
