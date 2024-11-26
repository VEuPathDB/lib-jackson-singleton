@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.jackson.common

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.contains
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

// region Collecting

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
 * @since v4.0.0
 */
inline fun Sequence<Pair<String, Any?>>.toJsonObject(fn: () -> ObjectNode) =
  fn().also { forEach { (k, v) -> it.put(k, v) } }

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
 * @since v4.0.0
 */
inline fun Iterable<Pair<String, Any?>>.toJsonObject(fn: () -> ObjectNode) =
  fn().also { forEach { (k, v) -> it.put(k, v) } }

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
 * @since v4.0.0
 */
inline fun Map<String, Any?>.toJsonObject(fn: () -> ObjectNode) =
  fn().also { forEach { (k, v) -> it.put(k, v) } }

// endregion Collecting

// region Put

/**
 * Sets the given key/value pair on this [ObjectNode].
 *
 * @param key Key to set the new value under.
 *
 * @param value Byte value to set.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
inline fun ObjectNode.put(key: String, value: Byte): ObjectNode =
  put(key, value.toShort())

/**
 * Sets the given key/value pair on this [ObjectNode].
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
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
    is UByte      -> putUnsigned(key, value)
    is UShort     -> putUnsigned(key, value)
    is UInt       -> putUnsigned(key, value)
    is ULong      -> putUnsigned(key, value)
    else          -> putPOJO(key, value)
  }

// endregion Put

// region Put Unsigned

/**
 * Sets the given key/value pair on this [ObjectNode].
 *
 * @param key Key to set the new value under.
 *
 * @param value UByte value to set.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
inline fun ObjectNode.putUnsigned(key: String, value: UByte): ObjectNode =
  put(key, value.toShort())

/**
 * Sets the given key/value pair on this [ObjectNode].
 *
 * @param key Key to set the new value under.
 *
 * @param value UShort value to set.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
inline fun ObjectNode.putUnsigned(key: String, value: UShort): ObjectNode =
  put(key, value.toInt())

/**
 * Sets the given key/value pair on this [ObjectNode].
 *
 * @param key Key to set the new value under.
 *
 * @param value UInt value to set.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
inline fun ObjectNode.putUnsigned(key: String, value: UInt): ObjectNode =
  put(key, value.toLong())

/**
 * Sets the given key/value pair on this [ObjectNode].
 *
 * @param key Key to set the new value under.
 *
 * @param value ULong value to set.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
inline fun ObjectNode.putUnsigned(key: String, value: ULong): ObjectNode =
  put(key, BigInteger(value.toString()))

// endregion Put Unsigned

// region Put If Not Null

// region Primitives

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
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
 *
 * @since v4.0.0
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
 *
 * @since v4.0.0
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
 *
 * @since v4.0.0
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
 *
 * @since v4.0.0
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
 *
 * @since v4.0.0
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
 *
 * @since v4.0.0
 */
inline fun ObjectNode.putIfNN(key: String, value: Double?): ObjectNode =
  if (value != null) put(key, value) else this

// endregion Primitives

// region Unsigned

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
inline fun ObjectNode.putUnsignedIfNN(key: String, value: UByte?): ObjectNode =
  if (value != null) putUnsigned(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
inline fun ObjectNode.putUnsignedIfNN(key: String, value: UShort?): ObjectNode =
  if (value != null) putUnsigned(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
inline fun ObjectNode.putUnsignedIfNN(key: String, value: UInt?): ObjectNode =
  if (value != null) putUnsigned(key, value) else this

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
inline fun ObjectNode.putUnsignedIfNN(key: String, value: ULong?): ObjectNode =
  if (value != null) putUnsigned(key, value) else this

// endregion Unsigned

// region Class Types

/**
 * Sets the given key/value pair on this [ObjectNode] if the given [value] is
 * not `null`.
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set if not `null`.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
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
 *
 * @since v4.0.0
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
 *
 * @since v4.0.0
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
 *
 * @since v4.0.0
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
 *
 * @since v4.0.0
 */
inline fun ObjectNode.putIfNN(key: String, value: Any?): ObjectNode =
  if (value != null) put(key, value) else this

/**
 * Sets the given key to the value returned by the given mapping function if it
 * is not `null`.
 *
 * @param T Type of the initial value that will be mapped to the actual value.
 *
 * @param k Key to set the new value under.
 *
 * @param v Initial value
 *
 * @param f Mapping function.
 *
 * @return This [ObjectNode].
 *
 * @since v4.0.0
 */
inline fun <T> ObjectNode.putIfNN(k: String, v: T?, f: (T) -> Any?): ObjectNode {
  contract {
    callsInPlace(f, InvocationKind.AT_MOST_ONCE)
  }

  return if (v != null) putIfNN(k, f(v)) else this
}

// endregion Class Types

// region Put If Not Null

// region Merge

/**
 * Merges JSON objects into a new JSON object containing the key/value pairs
 * from the first overlayed with the key/value pairs from the second.
 *
 * If conflicting keys are both objects or arrays, they will be merged, else the
 * value from the second JSON object will be used.
 *
 * @param rhs Second object to merge into a copy of the receiver object.
 *
 * @param newObj Function that will be called to construct new JSON object
 * instances.
 *
 * @param newArr Function that will be called to construct new JSON array
 * instances.
 *
 * @return A new JSON object containing the merged input objects.
 *
 * @since v4.0.0
 */
fun ObjectNode.mergeWith(
  rhs: ObjectNode,
  newObj: () -> ObjectNode,
  newArr: (Int) -> ArrayNode,
): ObjectNode {
  val out = newObj()

  for ((key, value) in fields())
    out.set<ObjectNode>(key, value)

  for ((key, value) in rhs.fields()) {
    if (!out.has(key)) {
      out.set<ObjectNode>(key, value)
      continue
    }

    val lhv = out[key]!!

    if (lhv.nodeType != value.nodeType || lhv.isPojo) {
      out.set<ObjectNode>(key, value)
      continue
    }

    when (lhv) {
      is ObjectNode -> out.set<ObjectNode>(key, lhv.mergeWith(value as ObjectNode, newObj, newArr))
      is ArrayNode  -> out.set<ObjectNode>(key, lhv.mergeWith(value as ArrayNode, newArr))
      else          -> out.set<ObjectNode>(key, value)
    }
  }

  return out
}

// endregion Merge
