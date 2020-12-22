package list

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
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
    fun size(number: Int) {
        addElements(tinyList, number)
        Assertions.assertEquals(number, tinyList.size())
    }
}