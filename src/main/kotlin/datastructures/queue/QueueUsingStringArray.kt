package datastructures.queue

import datastructures.enlargeArrayIfNeeded

/**
 * Implements a queue using an array.
 */
internal class QueueUsingStringArray<T> : IQueue<T> {
    //Index 0 == Head, First index where value is null == Tail
    var array = arrayOfNulls<Any>(2)
    var indexPointer = 0

    override fun enqueue(value: T) {
        array = enlargeArrayIfNeeded(array, indexPointer)
        array[indexPointer] = value as Any
        indexPointer++
    }

    override fun dequeue(): T? {
        if (array[0] == null) {
            //The array is empty
            return null
        }
        //Remove element at position zero
        val result = array[0]
        //Now move all elements one to the left (towards zero)
        for (index in 1..indexPointer) {
            array[index - 1] = array[index]
        }
        //Set the index pointer back by one (since we moved all items by 1)
        indexPointer -= 1
        //Return the result
        return if (result != null) result as T else null
    }

    override fun size(): Int = indexPointer
}