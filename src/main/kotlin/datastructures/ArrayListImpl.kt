package datastructures

import java.lang.IndexOutOfBoundsException

/**
 * Implementation of an array list using a resizable array.
 *
 * Lookup time: O(1)
 *
 * Note: resizing happens so rarely that the O(n) resize operation can be ignored.
 */
internal class ArrayListImpl<T> {

    var array = arrayOfNulls<Any>(16)
    var indexPointer = 0

    /**
     * Adds the given element to the end of the array list.
     */
    fun add(element: T) {
        array = enlargeArrayIfNeeded(array, indexPointer)
        array[indexPointer] = element
        indexPointer++
    }

    /**
     * @return the element at the given index; null if the element at that location is null.
     * @throws IndexOutOfBoundsException if the given index exceeds the official size of the array (official size < internal size)
     */
    fun get(at: Int): T? {
        raiseExceptionIfInvalidIndex(at, indexPointer)
        val result = array[indexPointer]
        return if (result != null) result as T else null
    }

    /**
     * @return the element that was removed at the given index (might be null)
     * @throws IndexOutOfBoundsException if the given index exceeds the official size of the array (official size < internal size)
     */
    fun remove(at: Int): T? {
        raiseExceptionIfInvalidIndex(at, indexPointer)
        //Set element at given index to null
        val result = array[at]
        array[at] = null
        //Now we shift all elements on the right to the left by one and we also update the pointer
        shiftElementsToLeft(at)
        return if (result != null) result as T else null
    }

    fun size(): Int = indexPointer

    /**
     * This method assumes that [toIndex] is a valid value
     */
    private fun shiftElementsToLeft(toIndex: Int) {
        //Update the index pointer
        if (indexPointer > 0) {
            indexPointer--
        }
        //Note that the index pointer is actually pointing at the next free cell, so we skip that max value
        for (index in toIndex until indexPointer) {
            array[index] = array[index + 1]
        }
    }

    /**
     * @throws IndexOutOfBoundsException if the given index exceeds the official size of the array (official size < internal size)
     */
    private fun raiseExceptionIfInvalidIndex(index: Int, pointer: Int) {
        if (index > pointer) {
            throw IndexOutOfBoundsException("Invalid index $index")
        }
    }
}