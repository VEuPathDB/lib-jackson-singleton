@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.jackson.common

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.contains
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.max

// region Stream Collecting

/**
 * Creates a new [ArrayNode] containing elements from the receiver stream.
 *
 * @param initialSize An initial size value to use if an expected size for the
 * final array is known ahead of time.
 *
 * @return A new [ArrayNode] containing elements from the receiver stream.
 *
 * @since v4.0.0
 */
inline fun Sequence<Any?>.toJsonArray(initialSize: Int = 8, fn: (Int) -> ArrayNode) =
  fn(initialSize).also { forEach(it::add) }

/**
 * Creates a new [ArrayNode] containing elements from the receiver stream.
 *
 * @param initialSize An initial size value to use if an expected size for the
 * final array is known ahead of time.
 *
 * @return A new [ArrayNode] containing elements from the receiver stream.
 *
 * @since v4.0.0
 */
inline fun Iterable<Any?>.toJsonArray(initialSize: Int = 8, fn: (Int) -> ArrayNode) =
  if (this is Collection<*>)
    fn(max(size, initialSize)).also { forEach(it::add) }
  else
    fn(initialSize).also { forEach(it::add) }

/**
 * Creates a new [ArrayNode] containing elements from the receiver array.
 *
 * @return A new [ArrayNode] containing elements from the receiver array.
 *
 * @since v4.0.0
 */
inline fun Array<Any?>.toJsonArray(fn: (Int) -> ArrayNode) = fn(size).also { forEach(it::add) }

// endregion Stream Collecting

// region Add

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value Byte value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.add(value: Byte): ArrayNode = add(value.toInt())

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value Value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
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
    is UByte      -> addUnsigned(value)
    is UShort     -> addUnsigned(value)
    is UInt       -> addUnsigned(value)
    is ULong      -> addUnsigned(value)
    else          -> addPOJO(value)
  }

// endregion Add

// region Add Unsigned

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value UByte value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsigned(value: UByte): ArrayNode = add(value.toShort())

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value UShort value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsigned(value: UShort): ArrayNode = add(value.toInt())

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value UInt value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsigned(value: UInt): ArrayNode = add(value.toLong())

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value ULong value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsigned(value: ULong): ArrayNode = add(BigInteger(value.toString()))

// endregion Add Unsigned

// region Add If Not Null

// region Primitives

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Boolean value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
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
 * @since v4.0.0
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
 * @since v4.0.0
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
 * @since v4.0.0
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
 * @since v4.0.0
 */
inline fun ArrayNode.addIfNN(value: Long?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Float value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
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
 * @since v4.0.0
 */
inline fun ArrayNode.addIfNN(value: Double?): ArrayNode =
  if (value != null) add(value) else this

// endregion Primitives

// region Unsigned

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value UByte value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsignedIfNN(value: UByte?): ArrayNode =
  if (value != null) addUnsigned(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value UShort value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsignedIfNN(value: UShort?): ArrayNode =
  if (value != null) addUnsigned(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value UInt value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsignedIfNN(value: UInt?): ArrayNode =
  if (value != null) addUnsigned(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value ULong value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsignedIfNN(value: ULong?): ArrayNode =
  if (value != null) addUnsigned(value) else this

// endregion Unsigned

// region Class Types

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value BigInteger value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addIfNN(value: BigInteger?): ArrayNode =
  if (value != null) add(value) else this

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value BigDecimal value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
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
 * @since v4.0.0
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
 * @since v4.0.0
 */
inline fun ArrayNode.addIfNN(value: JsonNode?): ArrayNode =
  if (value != null) add(value) else this

// endregion Class Types

// endregion Add If Not Null

// region Merge

/**
 * Merges JSON arrays into a new JSON arrays containing the values from both
 * input arrays.
 *
 * @param rhs Second object to merge into a copy of the receiver array.
 *
 * @param fn Function that will be called to construct new JSON array
 * instances.
 *
 * @return A new JSON array containing the merged input arrays.
 *
 * @since v4.0.0
 */
fun ArrayNode.mergeWith(rhs: ArrayNode, fn: (Int) -> ArrayNode): ArrayNode {
  val out = fn(size() + rhs.size())

  out.addAll(this)
  out.addAll(rhs)

  return out
}

// endregion Merge
