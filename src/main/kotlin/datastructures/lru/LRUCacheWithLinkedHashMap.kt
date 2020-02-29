package datastructures.lru

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * The cache is initialized with a positive capacity.
 */
internal class LRUCacheWithLinkedHashMap(private val capacity: Int) : ILRUCache {

    /**
     * We specify that ordering of the map is based on access order. Simple iterations do not change the order, only
     * explicit access operations do.
     */
    private val map = object : LinkedHashMap<Int, Int>(capacity, 0.75f, true) {
        //Note: we want to remove the eldes entry on the next insertion AFTER we reach the capacity, so we add +1
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean = size == capacity + 1
    }

    /**
     * @return value (will always be positive) of the key if the key exists in the cache; -1 otherwise.
     */
    override fun get(key: Int): Int = if (map.containsKey(key)) map[key]!! else -1

    /**
     * Puts the given key/value mapping into the cache
     * @return true if an old entry was evicted; false otherwise.
     */
    override fun put(key: Int, value: Int): Boolean {
        var didEviction = false
        if (!map.containsKey(key) && capacity == map.size) {
            //This means eviction will happen as the key didnt exist in the map prior
            didEviction = true
        }
        map[key] = value
        return didEviction
    }
}