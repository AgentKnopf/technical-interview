package datastructures

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Class under test [LinkedListImpl]
 */
internal class LinkedListImplTest {

    @Test
    fun add() {
        val list = LinkedListImpl<String>()
        assertEquals(0, list.size())

        list.add("Hello")
        list.add("world")
        list.add("how")
        list.add("goes")
        list.add("it")

        assertEquals("Hello world how goes it", list.toString())
        assertEquals(5, list.size())
    }

    @Test
    fun remove() {
        val list = LinkedListImpl<String>()
        assertEquals(0, list.size())

        list.add("Hello")
        list.add("world")
        list.add("how")
        list.add("goes")
        list.add("it")
        assertEquals(5, list.size())

        //Remove element at index 2 (how)
        list.remove(2)
        assertEquals(4, list.size())
        assertEquals("Hello world goes it", list.toString())

        //Remove a head element
        list.remove(0)
        assertEquals(3, list.size())
        assertEquals("world goes it", list.toString())

        //Remove a tail element
        list.remove(2)
        assertEquals(2, list.size())
        assertEquals("world goes", list.toString())
    }

    @Test
    fun get() {
        val list = LinkedListImpl<String>()
        assertEquals(0, list.size())

        list.add("Hello")
        list.add("world")
        list.add("how")
        list.add("goes")
        list.add("it")

        assertEquals("Hello", list.get(0))
        assertEquals("world", list.get(1))
        assertEquals("how", list.get(2))
        assertEquals("goes", list.get(3))
        assertEquals("it", list.get(4))
    }
}