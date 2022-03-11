@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.jackson

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import java.math.BigDecimal
import java.math.BigInteger

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

fun ObjectNode.put(key: String, value: Any): ObjectNode =
  when (value) {
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