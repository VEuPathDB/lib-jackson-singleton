package org.veupathdb.lib.jackson.yaml

import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.TextNode
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

@DisplayName("yaml object mixins")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ObjectsKtTest {

  @Nested
  @DisplayName("merging")
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  inner class Merge {
    private val yaml1 = """
      someKey:
        someValue1:
          somethingElse: {}
        someValue2:
        - foo
        - bar
        someValue3: 3
    """.trimIndent()

    private val yaml2 = """
      someKey:
        someValue1:
          somethingElse:
            apples: oranges
        someValue2:
        - fizz
        - buzz
        someValue3: goodbye
    """.trimIndent()

    @Test
    @DisplayName("complex merge")
    fun `complex merge`() {
      val lhs = Yaml.parse<ObjectNode>(yaml1)
      val rhs = Yaml.parse<ObjectNode>(yaml2)
      val result = lhs + rhs

      assertEquals(1, result.size(), "expected result object to have one root key")
      assertEquals("someKey", result.fieldNames().next())

      val value = assertInstanceOf<ObjectNode>(result["someKey"])

      assertEquals(3, value.size(), "expected nested object to have 3 keys")

      assertTrue(value.has("someValue1"), "missing key someValue1")
      with(assertInstanceOf<ObjectNode>(value["someValue1"])) {
        assertEquals(1, size(), "expected someValue1 to have 1 key")
        assertTrue(has("somethingElse"))
        with(assertInstanceOf<ObjectNode>(get("somethingElse"))) {
          assertEquals(1, size())
          assertTrue(has("apples"))
          assertTrue(get("apples").isTextual)
          assertEquals("oranges", get("apples").textValue())
        }
      }

      assertTrue(value.has("someValue2"), "missing key someValue2")
      with(assertInstanceOf<ArrayNode>(value["someValue2"])) {
        assertEquals(4, size())

        val expects = arrayOf("foo", "bar", "fizz", "buzz")

        for (i in expects.indices) {
          assertTrue(get(i).isTextual)
          assertEquals(expects[i], get(i).textValue())
        }
      }

      assertTrue(value.has("someValue3"), "missing key someValue3")
      with(assertInstanceOf<TextNode>(value["someValue3"])) {
        assertEquals("goodbye", textValue())
      }
    }
  }
}
