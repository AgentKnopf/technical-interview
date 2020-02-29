package algorithms

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Method under test: [candyCount]
 */
internal class CandyCountTest {

    @Test
    fun candyCountTest() {
        assertEquals(0, candyCount(intArrayOf()))
        assertEquals(1, candyCount(intArrayOf(1)))
        assertEquals(2, candyCount(intArrayOf(2, 2)))
        assertEquals(5, candyCount(intArrayOf(1, 0, 2)))
        assertEquals(4, candyCount(intArrayOf(1, 2, 2)))
        assertEquals(9, candyCount(intArrayOf(1, 3, 4, 4, 1)))
        assertEquals(7, candyCount(intArrayOf(1, 3, 2, 2, 1)))
        assertEquals(12, candyCount(intArrayOf(7, 6, 3, 2, 5)))
        assertEquals(11, candyCount(intArrayOf(2, 4, 3, 7, 6, 1, 1)))
        assertEquals(10, candyCount(intArrayOf(2, 2, 1, 1, 1, 2, 2, 2)))
    }
}