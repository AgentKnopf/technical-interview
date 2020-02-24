package algorithms

import org.junit.Assert.assertEquals
import org.junit.Test

internal class ArrayAlgorithmTest {

    @Test
    fun totalFruitTest() {
        assertEquals(6, totalFruit(arrayOf(0, 0, 0, 0, 0, 0)))
        assertEquals(4, totalFruit(arrayOf(0, 0, 1, 1)))
        assertEquals(3, totalFruit(arrayOf(1, 2, 2)))
        assertEquals(3, totalFruit(arrayOf(0, 1, 2, 2)))
        assertEquals(5, totalFruit(arrayOf(3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4)))
    }
}