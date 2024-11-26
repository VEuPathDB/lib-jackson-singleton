@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.jackson.yaml

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.jackson.common.mergeWith
import org.veupathdb.lib.jackson.common.toJsonObject
import java.math.BigDecimal
import java.math.BigInteger

import org.veupathdb.lib.jackson.common.put as cPut
import org.veupathdb.lib.jackson.common.putIfNN as cPutIfNN
import org.veupathdb.lib.jackson.common.putUnsigned as cPutUnsigned
import org.veupathdb.lib.jackson.common.putUnsignedIfNN as cPutUnsignedIfNN

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
 * @since v3.3.0
 */
inline fun Sequence<Pair<String, Any?>>.toYamlObject() = toJsonObject(Yaml::newObject)

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
inline fun Iterable<Pair<String, Any?>>.toYamlObject() = toJsonObject(Yaml::newObject)

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
inline fun Map<String, Any?>.toYamlObject() = toJsonObject(Yaml::newObject)

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
 * @since v2.1.0
 */
inline fun ObjectNode.put(key: String, value: Byte) = cPut(key, value)

/**
 * Sets the given key/value pair on this [ObjectNode].
 *
 * @param key Key to set the new value under.
 *
 * @param value Value to set.
 *
 * @return This [ObjectNode].
 *
 * @since v2.1.0
 */
fun ObjectNode.put(key: String, value: Any?) = cPut(key, value)

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
inline fun ObjectNode.putUnsigned(key: String, value: UByte) = cPutUnsigned(key, value)

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
inline fun ObjectNode.putUnsigned(key: String, value: UShort) = cPutUnsigned(key, value)

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
inline fun ObjectNode.putUnsigned(key: String, value: UInt) = cPutUnsigned(key, value)

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
inline fun ObjectNode.putUnsigned(key: String, value: ULong) = cPutUnsigned(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: Boolean?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: Byte?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: Short?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: Int?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: Long?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: Float?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: Double?) = cPutIfNN(key, value)

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
inline fun ObjectNode.putUnsignedIfNN(key: String, value: UByte?) = cPutUnsignedIfNN(key, value)

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
inline fun ObjectNode.putUnsignedIfNN(key: String, value: UShort?) = cPutUnsignedIfNN(key, value)

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
inline fun ObjectNode.putUnsignedIfNN(key: String, value: UInt?) = cPutUnsignedIfNN(key, value)

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
inline fun ObjectNode.putUnsignedIfNN(key: String, value: ULong?) = cPutUnsignedIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: BigInteger?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: BigDecimal?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: String?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: JsonNode?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun ObjectNode.putIfNN(key: String, value: Any?) = cPutIfNN(key, value)

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
 * @since v2.1.0
 */
inline fun <T> ObjectNode.putIfNN(k: String, v: T?, f: (T) -> Any?) = cPutIfNN(k, v, f)

// endregion Class Types

// endregion Put If Not Null

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
 * @return A new JSON object containing the merged input objects.
 *
 * @since v4.0.0
 */
inline operator fun ObjectNode.plus(rhs: ObjectNode) = mergeWith(rhs, Yaml::newObject, Yaml::newArray)

// endregion Merge
