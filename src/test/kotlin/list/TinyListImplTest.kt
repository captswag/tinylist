package list

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TinyListImplTest {

    private lateinit var tinyList: TinyList<Int>

    @BeforeEach
    fun setUp() {
        tinyList = TinyListImpl<Int>()
    }

    @Test
    fun size() {
        tinyList.add(1)
        tinyList.add(2)
        tinyList.add(3)

        Assertions.assertEquals(3, tinyList.size())
    }
}