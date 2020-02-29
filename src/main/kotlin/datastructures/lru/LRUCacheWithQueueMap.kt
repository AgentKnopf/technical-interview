package datastructures.lru

import java.util.*
import kotlin.collections.HashMap

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * The cache is initialized with a positive capacity.
 */
internal class LRUCacheWithQueueMap(private val capacity: Int) : ILRUCache {
    /**
     * Maintains the order of which items were most recently used. For quick access use [keyToEntry].
     */
    private val queue = LinkedList<Entry>()
    /**
     * Allows quick, key-based lookup.
     */
    private val keyToEntry = HashMap<Int, Entry>(capacity)

    /**
     * @return value (will always be positive) of the key if the key exists in the cache; -1 otherwise.
     */
    override fun get(key: Int): Int {
        return if (keyToEntry.contains(key)) {
            //Get the entry from the map
            val entry = keyToEntry[key]!!
            //Remove it from the queue
            queue.remove(entry)
            //Enqueue again at the front
            queue.addFirst(entry)
            //Return the result
            entry.value
        } else {
            //No match found, return -1
            -1
        }
    }

    /**
     * Puts the given key/value mapping into the cache
     * @return true if an old entry was evicted; false otherwise.
     */
    override fun put(key: Int, value: Int): Boolean {
        var didEviction = false
        val entry = keyToEntry[key]
        if (entry != null) {
            //Handles the case where the key is already in the map, so we just update it and the map
            val newEntry = Entry(key, value)
            keyToEntry[key] = newEntry
            //Remove the old entry
            queue.remove(entry)
            //Add the new entry
            queue.addFirst(newEntry)
            //We return here so the latter code for adding an item isn't executed
            return didEviction
        } else if (queue.size == capacity) {
            //Evict the least used item from the cache
            val lastEntry = queue.removeLast()
            //Remove the entry from the map
            keyToEntry.remove(lastEntry.key)
            didEviction = true
        }
        //Handles the default behavior - regardless of whether an item was evicted or not
        val newEntry = Entry(key, value)
        keyToEntry[key] = newEntry
        queue.addFirst(newEntry)
        return didEviction
    }
}

/**
 * Represents an entry in the LRU cache
 */
private data class Entry(val key: Int, val value: Int)