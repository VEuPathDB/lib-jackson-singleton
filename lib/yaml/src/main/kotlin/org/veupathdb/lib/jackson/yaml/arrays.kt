@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.jackson.yaml

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import org.veupathdb.lib.jackson.common.mergeWith
import org.veupathdb.lib.jackson.common.toJsonArray
import java.math.BigDecimal
import java.math.BigInteger

import org.veupathdb.lib.jackson.common.add as cAdd
import org.veupathdb.lib.jackson.common.addIfNN as cAddIfNN
import org.veupathdb.lib.jackson.common.addUnsigned as cAddUnsigned
import org.veupathdb.lib.jackson.common.addUnsignedIfNN as cAddUnsignedIfNN

// region Stream Collecting

/**
 * Creates a new [ArrayNode] containing elements from the receiver stream.
 *
 * @param initialSize An initial size value to use if an expected size for the
 * final array is known ahead of time.
 *
 * @return A new [ArrayNode] containing elements from the receiver stream.
 *
 * @since v3.3.0
 */
@JvmOverloads
inline fun Sequence<Any?>.toJsonArray(initialSize: Int = 8) = toJsonArray(initialSize, Yaml::newArray)

/**
 * Creates a new [ArrayNode] containing elements from the receiver stream.
 *
 * @param initialSize An initial size value to use if an expected size for the
 * final array is known ahead of time.
 *
 * @return A new [ArrayNode] containing elements from the receiver stream.
 *
 * @since v3.3.0
 */
@JvmOverloads
inline fun Iterable<Any?>.toJsonArray(initialSize: Int = 8) = toJsonArray(initialSize, Yaml::newArray)

/**
 * Creates a new [ArrayNode] containing elements from the receiver array.
 *
 * @return A new [ArrayNode] containing elements from the receiver array.
 *
 * @since v4.0.0
 */
inline fun Array<Any?>.toJsonArray() = toJsonArray(Yaml::newArray)

// endregion Stream Collecting

// region Add

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value Byte value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.add(value: Byte) = cAdd(value)

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value Value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.add(value: Any?) = cAdd(value)

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
inline fun ArrayNode.addUnsigned(value: UByte) = cAddUnsigned(value)

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value UShort value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsigned(value: UShort) = cAddUnsigned(value)

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value UInt value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsigned(value: UInt) = cAddUnsigned(value)

/**
 * Adds the given value to this [ArrayNode].
 *
 * @param value ULong value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsigned(value: ULong) = cAddUnsigned(value)

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
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Boolean?) = cAddIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Byte value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Byte?) = cAddIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Short value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Short?) = cAddIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Int value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Int?) = cAddIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Long value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Long?) = cAddIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Float value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Float?) = cAddIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value Double value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: Double?) = cAddIfNN(value)

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
inline fun ArrayNode.addUnsignedIfNN(value: UByte?) = cAddUnsignedIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value UShort value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsignedIfNN(value: UShort?) = cAddUnsignedIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value UInt value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsignedIfNN(value: UInt?) = cAddUnsignedIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value ULong value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v4.0.0
 */
inline fun ArrayNode.addUnsignedIfNN(value: ULong?) = cAddUnsignedIfNN(value)

// endregion Unsigned

// region Class Types

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value BigInteger value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: BigInteger?) = cAddIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value BigDecimal value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: BigDecimal?) = cAddIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value String value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: String?) = cAddIfNN(value)

/**
 * Adds the given value to this [ArrayNode] only if it is not `null`.
 *
 * @param value JSON value to add.
 *
 * @return This [ArrayNode].
 *
 * @since v3.3.0
 */
inline fun ArrayNode.addIfNN(value: JsonNode?) = cAddIfNN(value)

// endregion Class Types

// endregion Add If Not Null

// region Merge

/**
 * Merges JSON arrays into a new JSON arrays containing the values from both
 * input arrays.
 *
 * @param rhs Second object to merge into a copy of the receiver array.
 *
 * @return A new JSON array containing the merged input arrays.
 *
 * @since v4.0.0
 */
inline operator fun ArrayNode.plus(rhs: ArrayNode) = mergeWith(rhs, Yaml::newArray)

// endregion Merge
