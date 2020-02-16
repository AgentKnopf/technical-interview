package datastructures

private const val ARRAY_LENGTH = 20

/**
 * Custom implementation of a hashmap using a linked list. This Hashtable is not synchronized.
 *

 * Resources:
 * - https://www.tutorialspoint.com/data_structures_algorithms/hash_data_structure.htm
 */
internal class HashTableImpl<K, V> {
    /**
     * We're using a set here so we dont have to worry about uniqueness of entries. Also - the reason we need a bucket
     * are potential collisions, as multiple keys may map to the same index.
     */
    private val values = Array<Set<IHashMapEntry<K, V>>>(ARRAY_LENGTH) { mutableSetOf() }


    /**
    We map hashcodes to a given index of the array by including the overall array length in our calculation,
    which requires the array size to be fixed. If we change the array size, our method will no longer find the
    original positions.
     */
    fun indexForHashCode(hash: Int): Int = hash % ARRAY_LENGTH

    /**
     * Adds the given key/value pair to the table if the entry doesn't exist yet; otherwise it updates it.
     */
    fun put(key: K, value: V) {
        val index = indexForHashCode(key.hashCode())
        //Check if entry exists for given key/value
        val entry = getEntry(key, index)
        var bucket = values[index]
        entry?.let {
            //Remove the existing entry so we can update it later
            bucket = bucket.minus(entry)
        }
        //Update or add the entry
        values[index] = bucket.plus(HashMapEntry(key, value))
    }

    /**
     * @return true if an element matching the given key was found and removed; false otherwise.
     */
    fun remove(key: K): Boolean {
        val index = indexForHashCode(key.hashCode())
        val entry = getEntry(key, index)
        entry?.let {
            //Remove the entry from the bucket
            values[index] = values[index].minus(entry)
            return true
        }
        return false
    }

    /**
     * @return the value mapped to the given key; null if there is no mapping.
     */
    fun get(key: K): V? = getEntry(key, indexForHashCode(key.hashCode()))?.value

    private fun getEntry(key: K, index: Int): IHashMapEntry<K, V>? {
        //Get the bucket at the calculated index
        val bucket = values[index]
        for (entry in bucket) {
            if (key == entry.key) {
                return entry
            }
        }
        return null
    }

    /**
     * @return true if the given key is contained in the table; false otherwise.
     */
    fun contains(key: K): Boolean = getEntry(key, indexForHashCode(key.hashCode())) != null

    /**
     * @return the number of entries
     */
    fun size(): Int {
        var count = 0
        values.forEach { set -> count += set.size }
        return count
    }
}

/**
 * Represents an entry in the [HashTableImpl].
 */
private interface IHashMapEntry<K, V> {
    val value: V
    val key: K
}

private class HashMapEntry<K, V>(val initialKey: K, val initialValue: V) : IHashMapEntry<K, V> {
    override val value: V
        get() {
            return initialValue
        }

    override val key: K
        get() {
            return initialKey
        }

}