package datastructures

/**
 * Enlarges the given array if necessary.
 *
 * @return the resized array.
 */
fun enlargeArrayIfNeeded(array: Array<Any?>, indexPointer: Int): Array<Any?> {
    var result = array
    //Check if the array is full
    val arraySize = array.size
    if (arraySize - 1 == indexPointer) {
        //Double the array size
        result = arrayOfNulls(arraySize * 2)
        //Copy over from the old to the new array
        array.forEachIndexed { index, value -> result[index] = value }
    }
    return result
}