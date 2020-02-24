package algorithms

import org.junit.Assert.*
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

    @Test
    fun stringToIntegerTest() {
        assertEquals(-42, stringToInteger("   -42"))
        assertEquals(42, stringToInteger("42"))
        assertEquals(-62534, stringToInteger("-62534 sdhsdi dfjkhdkfj"))
        //First non-whitespace character is not a digit/+/- so we expect zero
        assertEquals(0, stringToInteger("words and 987"))
        assertEquals(4193, stringToInteger("4193 with words  "))
        assertEquals(Int.MIN_VALUE, stringToInteger("-91283472332"))
        assertEquals(3, stringToInteger("3.14159"))
        assertEquals(2, stringToInteger("2.14159"))
        assertEquals(0, stringToInteger("+"))
        assertEquals(0, stringToInteger("-"))
        assertEquals(0, stringToInteger("."))
        assertEquals(0, stringToInteger(""))
    }
}