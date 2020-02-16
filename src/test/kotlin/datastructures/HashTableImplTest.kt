package datastructures

import org.junit.Assert.*
import org.junit.Test

private const val KEY_ONE = "key1"
private const val KEY_TWO = "key2"
private const val KEY_THREE = "key3"
private const val KEY_INVALID = "keyDoesNotExist"

private const val VALUE_ONE = "value1"
private const val VALUE_TWO = "value2"
private const val VALUE_THREE = "value3"

/**
 * Class under test [HashTableImpl]
 */
internal class HashTableImplTest {

    /**
     * Ensures that the index returned is never larger than the size of the array.
     */
    @Test
    fun indexForHashCode() {
        val map = HashTableImpl<String, String>()
        assertTrue(isValidIndex(map.indexForHashCode(0)))
        assertTrue(isValidIndex(map.indexForHashCode(1)))
        assertTrue(isValidIndex(map.indexForHashCode(23645832)))
    }

    @Test
    fun putGetContainsSize() {
        val map = HashTableImpl<String, String>()
        map.put(KEY_ONE, VALUE_ONE)
        map.put(KEY_TWO, VALUE_TWO)
        map.put(KEY_THREE, VALUE_THREE)
        assertEquals(3, map.size())

        //Test existing entries
        assertTrue(map.contains(KEY_ONE))
        assertTrue(map.contains(KEY_TWO))
        assertTrue(map.contains(KEY_THREE))

        //Test an entry that does not exist
        assertFalse(map.contains(KEY_INVALID))

        //Update an existing entry
        val newValue = "someNewValue"
        map.put(KEY_TWO, newValue)
        assertTrue(map.contains(KEY_TWO))
        assertEquals(newValue, map.get(KEY_TWO))
        assertEquals(3, map.size())
    }

    @Test
    fun remove() {
        val map = HashTableImpl<String, String>()
        map.put(KEY_ONE, VALUE_ONE)
        map.put(KEY_TWO, VALUE_TWO)
        map.put(KEY_THREE, VALUE_THREE)
        assertEquals(3, map.size())

        //Remove an entry that is in the list
        assertTrue(map.remove(KEY_ONE))
        assertEquals(2, map.size())

        //Remove an entry that is not in the list
        assertFalse(map.remove(KEY_INVALID))
        assertEquals(2, map.size())
    }
}

private fun isValidIndex(index: Int) = index in 0..19
