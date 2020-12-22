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
    @ValueSource(ints = [0, 5, 11, 101])
    fun add(number: Int) {
        addElements(tinyList, number)
        val expectedList = TinyListImpl<Int>()
        addElements(expectedList, number)
        Assertions.assertEquals(tinyList, expectedList)
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
    @DisplayName("equals() failure cases - different size")
    fun `equals failure case 1`() {
        addElements(tinyList, 5)
        val expectedList: TinyList<Int> = TinyListImpl()
        addElements(expectedList, 4)
        Assertions.assertFalse(expectedList == tinyList)
    }

    @Test
    @DisplayName("equals() failure cases - same size, different list elements")
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

    @ParameterizedTest
    @ValueSource(ints = [0, 5, 11, 101])
    fun size(number: Int) {
        addElements(tinyList, number)
        Assertions.assertEquals(number, tinyList.size())
    }
}