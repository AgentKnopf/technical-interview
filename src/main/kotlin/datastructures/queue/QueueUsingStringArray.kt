package datastructures.queue

/**
 * Implements a queue using an array.
 * Note: we need the concrete type for the array, as it does not support abstract type T.
 */
internal class QueueUsingStringArray : IQueue<String> {
    //Index 0 == Head, First index where value is null == Tail
    var array = arrayOfNulls<String>(4)
    var indexPointer = 0

    override fun enqueue(value: String) {
        enlargeArrayIfNeeded()
        array[indexPointer] = value
        indexPointer++
    }

    /**
     * Enlarges the current array if necessary
     */
    private fun enlargeArrayIfNeeded() {
        //Check if the array is full
        val arraySize = array.size
        if (arraySize - 1 >= indexPointer) {
            //Double the array size
            val oldArray = array
            array = arrayOfNulls(arraySize * 2)
            //Copy over from the old to the new array
            oldArray.forEachIndexed { index, value -> array[index] = value }
        }
    }

    override fun dequeue(): String? {
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
        return result
    }

    override fun size(): Int = indexPointer
}