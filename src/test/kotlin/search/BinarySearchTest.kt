package search

import org.junit.Assert
import org.junit.Test

internal class BinarySearchTest {

    @Test
    fun binarySearchTest() {
        Assert.assertEquals(0, binarySearch(intArrayOf(0, 2, 4, 6, 8, 10, 12), 0))
        Assert.assertEquals(-1, binarySearch(intArrayOf(12), 11))
        Assert.assertEquals(0, binarySearch(intArrayOf(6), 6))
        Assert.assertEquals(1, binarySearch(intArrayOf(0, 2, 4, 6, 8, 10, 12), 2))
        Assert.assertEquals(4, binarySearch(intArrayOf(0, 2, 4, 6, 8, 10, 12), 8))
        Assert.assertEquals(-1, binarySearch(intArrayOf(0, 2, 4, 6, 8, 10, 12), 11))
        Assert.assertEquals(6, binarySearch(intArrayOf(0, 2, 4, 6, 8, 10, 12), 12))
    }
}