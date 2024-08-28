@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.jackson

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import java.math.BigDecimal
import java.math.BigInteger

/**
 * Creates a new [ObjectNode] containing elements for each of the key/value
 * [Pair] instances in the receiver stream.
 *
 * *Simple Example*
 * ```kt
 * listOf("a" to 1, "b" to 2, "c" to 3).toJsonObject() // {"a": 1, "b": 2, "c": 3}
 * ```
 *
 * In the event of conflicting keys, previously set values will be overwritten
 * by values appearing later.
 *
 * @return A new [ObjectNode] containing elements for each of the key/value
 * [Pair] instances in the receiver stream.
 *
 * @since v3.3.0
 */
inline fun Sequence<Pair<String, Any?>>.toJsonObject() =
  Json.newObject { this@toJsonObject.forEach { (k, v) -> put(k, v) } }

/**
 * Creates a new [ObjectNode] containing elements for each of the key/value
 * [Pair] instances in the receiver stream.
 *
 * *Simple Example*
 * ```kt
 * listOf("a" to 1, "b" to 2, "c" to 3).toJsonObject() // {"a": 1, "b": 2, "c": 3}
 * ```
 *
 * In the event of conflicting keys, previously set values will be overwritten
 * by values appearing later.
 *
 * @return A new [ObjectNode] containing elements for each of the key/value
 * [Pair] instances in the receiver stream.
 *
 * @since v3.3.0
 */
inline fun Iterable<Pair<String, Any?>>.toJsonObject() =
  Json.newObject { this@toJsonObject.forEach { (k, v) -> put(k, v) } }

/**
 * Creates a new [ObjectNode] containing elements from the receiver map.
 *
 * *Simple Example*
 * ```kt
 * mapOf("a" to 1, "b" to 2, "c" to 3).toJsonObject() // {"a": 1, "b": 2, "c": 3}
 * ```
 *
 * @return A new [ObjectNode] containing elements from the receiver stream.
 *
 * @since v3.3.0
 */
inline fun Map<String, Any?>.toJsonObject() =
  Json.newObject { this@toJsonObject.forEach { (k, v) -> put(k, v) } }

/**
 * Sets the given key/value pair on this [ObjectNode].
 *
 * @param key Key to set the new value under.
 *
 * @param value Byte value to set.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.put(key: String, value: Byte): ObjectNode =
  put(key, value.toShort())

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: Boolean?): ObjectNode =
  if (value != null) put(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: Byte?): ObjectNode =
  if (value != null) put(key, value.toShort()) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: Short?): ObjectNode =
  if (value != null) put(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: Int?): ObjectNode =
  if (value != null) put(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: Long?): ObjectNode =
  if (value != null) put(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: BigInteger?): ObjectNode =
  if (value != null) put(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: Float?): ObjectNode =
  if (value != null) put(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: Double?): ObjectNode =
  if (value != null) put(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: BigDecimal?): ObjectNode =
  if (value != null) put(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: String?): ObjectNode =
  if (value != null) put(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: JsonNode?) =
  if (value != null) set(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 */
inline fun ObjectNode.putIfNN(key: String, value: Any?): ObjectNode =
  if (value != null) put(key, value) else this

fun ObjectNode.put(key: String, value: Any?): ObjectNode =
  when (value) {
    null          -> putNull(key)
    is String     -> put(key, value)
    is JsonNode   -> set(key, value)
    is Int        -> put(key, value)
    is Long       -> put(key, value)
    is Double     -> put(key, value)
    is Boolean    -> put(key, value)
    is BigInteger -> put(key, value)
    is BigDecimal -> put(key, value)
    is ByteArray  -> put(key, value)
    is Byte       -> put(key, value)
    is Short      -> put(key, value)
    is Float      -> put(key, value)
    else          -> putPOJO(key, value)
  }

inline fun <T> ObjectNode.putIfNN(k: String, v: T?, f: (T) -> Any?): ObjectNode =
  if (v != null) putIfNN(k, f(v)) else this