package datastructures

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Class under test [ArrayListImpl]
 */
internal class ArrayListImplTest {

    @Test
    fun addRemoveGet() {
        val array = ArrayListImpl<String>()
        assertEquals(0, array.size())

        //Add elements for index 0 to 5
        array.add("hello")
        array.add("world")
        array.add("the")
        array.add("weather")
        array.add("is")
        array.add("nice")
        assertEquals(6, array.size())

        //Remove elements
        assertEquals("world", array.remove(1))
        //Since we removed an element the location of the other elements changed
        assertEquals("is", array.remove(3))
        assertEquals(4, array.size())
    }

    @Test
    fun testCapacityExpansion() {
        val array = ArrayListImpl<String>()
        assertEquals(0, array.size())

        //Add more elements than we have initial capacity for
        for (count in 0..50) {
            array.add("$count")
        }
        assertEquals(51, array.size())
    }
}