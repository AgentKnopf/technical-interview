package datastructures

import datastructures.lru.ILRUCache
import datastructures.lru.LRUCacheWithLinkedHashMap
import datastructures.lru.LRUCacheWithQueueMap
import org.junit.Assert.*
import org.junit.Test

/**
 * Class under test [LRUCacheWithQueueMap]
 */
internal class LRUCacheTest {

    @Test
    fun lRUCacheWithQueueMap() {
        testLRU(LRUCacheWithQueueMap(3))
    }

    @Test
    fun lRUCacheWithLinkedHashMap() {
        testLRU(LRUCacheWithLinkedHashMap(3))
    }

    /**
     * Expects an LRU cache with a capacity of 3 for the tests to work.
     */
    private fun testLRU(cache: ILRUCache) {
        assertFalse(cache.put(1, 5))
        assertFalse(cache.put(2, 10))
        assertFalse(cache.put(3, 15))
        //This call will evict entry 1 -> 5
        assertTrue(cache.put(4, 20))
        //Ensure that the entry 1 -> 5 was really evicted
        assertEquals(-1, cache.get(1))
        //This call moves 2 to the head of the queue, our new order from front to back is (keys only): 2,4,3
        assertEquals(10, cache.get(2))
        //The next call evicts entry 3 -> 15
        assertTrue(cache.put(5, 25))
        assertEquals(-1, cache.get(3))
        //Our current order up to now is: 5 -> 25, 2 -> 10, 4 -> 20; we'll now update item 4 -> 20, no eviction happens
        assertFalse(cache.put(4, 44))
        //New order is: 4 -> 44, 5 -> 25, 2 -> 10 - we'll now do a get on 4 which does not change the order
        assertEquals(44, cache.get(4))
        //Next we'll add another item, which drops 2 -> 10
        assertTrue(cache.put(6, 30))
        //Ensure that 2 -> 10 was dropped
        assertEquals(-1, cache.get(2))
        //Check if the other items are in there as expected
        assertEquals(44, cache.get(4))
        assertEquals(25, cache.get(5))
        assertEquals(30, cache.get(6))
    }
}