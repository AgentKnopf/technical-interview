package algorithms

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class StringAlgorithmsTest {

    /**
     * Tests [isAnagram] function.
     */
    @Test
    fun isAnagramTest() {
        assertTrue(isAnagram("hello", "ohell"))
        assertFalse(isAnagram("min", "max"))
        assertFalse(isAnagram("wonder", "land"))
    }
}