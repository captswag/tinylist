package list

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class TinyListImplTest {

    private lateinit var tinyList: TinyList<Int>

    private fun addElements(tinyList: TinyList<Int>, number: Int) {
        var index = 0
        while (index < number) {
            tinyList.add(index)
            index++
        }
    }

    @BeforeEach
    fun setUp() {
        tinyList = TinyListImpl()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 5, 11, 101])
    fun add(number: Int) {
        addElements(tinyList, number)
        val expectedList = TinyListImpl<Int>()
        addElements(expectedList, number)
        Assertions.assertEquals(tinyList, expectedList)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 5])
    @DisplayName("removeAt() failure case - index > size")
    fun `removeAt failure case`(size: Int) {
        addElements(tinyList, size)
        val index = 10
        val exception = Assertions.assertThrows(IndexOutOfBoundsException::class.java) {
            tinyList.removeAt(index)
        }
        val expectedMessage = "Index $index out of bounds for length $size"
        Assertions.assertEquals(expectedMessage, exception.message)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 5, 11, 101])
    fun size(number: Int) {
        addElements(tinyList, number)
        Assertions.assertEquals(number, tinyList.size())
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 5, 11, 101])
    fun indexOf(element: Int) { // element = 5 will be at index 5 in the list
        val size = 101
        addElements(tinyList, size)
        val actualIndex = tinyList.indexOf(element)
        val expectedIndex: Int = if (element >= size) {
            -1
        } else {
            element
        }
        Assertions.assertEquals(actualIndex, expectedIndex)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 5, 11, 101]) // index is the same as the element at the index position
    fun elementAt(index: Int) {
        val size = 102
        addElements(tinyList, size)
        val actualResult = tinyList.elementAt(index)
        Assertions.assertEquals(index, actualResult)
    }

    @Test
    @DisplayName("elementAt() failure case - size 0 list")
    fun `elementAt failure case 1`() {
        val size = 0
        addElements(tinyList, size)
        val exception = Assertions.assertThrows(
            IndexOutOfBoundsException::class.java
        ) {
            tinyList.elementAt(0)
        }
        Assertions.assertEquals("Empty list doesn't contain element at index 0", exception.message)
    }

    @Test
    @DisplayName("elementAt() failure case - index > size")
    fun `elementAt failure case 2`() {
        val size = 2
        addElements(tinyList, size)
        val exception = Assertions.assertThrows(
            IndexOutOfBoundsException::class.java
        ) {
            tinyList.elementAt(2)
        }
        Assertions.assertEquals("Index: 2, Size: 2", exception.message)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 5, 11, 101])
    fun equals(number: Int) {
        addElements(tinyList, number)
        val expectedList = TinyListImpl<Int>()
        addElements(expectedList, number)
        Assertions.assertTrue(tinyList == expectedList)
    }

    @Test
    @DisplayName("equals() failure case - different size")
    fun `equals failure case 1`() {
        addElements(tinyList, 5)
        val expectedList: TinyList<Int> = TinyListImpl()
        addElements(expectedList, 4)
        Assertions.assertFalse(expectedList == tinyList)
    }

    @Test
    @DisplayName("equals() failure case - same size, different list elements")
    fun `equals failure case 2`() {
        addElements(tinyList, 5)
        val expectedList: TinyList<Int> = TinyListImpl()
        expectedList.add(0)
        expectedList.add(1)
        expectedList.add(2)
        expectedList.add(3)
        expectedList.add(5)
        Assertions.assertFalse(expectedList == tinyList)
    }
}