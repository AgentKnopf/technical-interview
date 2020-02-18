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

    @Test
    fun hasUniqueCharactersWithDataStructure() {
        assertTrue("hole".hasUniqueCharacters(true))
        assertTrue("Waldorf".hasUniqueCharacters(true))
        assertFalse("wOoW".hasUniqueCharacters(true))
    }

    @Test
    fun hasUniqueCharactersNoDataStructure() {
        assertTrue("hole".hasUniqueCharacters(false))
        assertTrue("Waldorf".hasUniqueCharacters(false))
        assertFalse("wOoW".hasUniqueCharacters(false))
    }
}