package datastructures.lru

interface ILRUCache {
    fun get(key: Int): Int
    fun put(key: Int, value: Int): Boolean
}