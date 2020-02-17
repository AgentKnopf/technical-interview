package algorithms


/**
 * Two strings are an anagram of each other, if they contain the same letters, the same number of times,
 * possibly in a different order.
 * @return true if the given strings are anagrams of each other, ignoring capitalization; false otherwise.
 */
internal fun isAnagram(left: String, right: String): Boolean {
    if (left.length != right.length) {
        //Not the same length so not an anagram
        return false
    }
    //To lowercase both values
    val leftString = left.toLowerCase()
    val rightString = right.toLowerCase()
    //Sort and compare
    return leftString.sort() == rightString.sort()
}

/**
 * @return the given string, with it's letters sorted in alphabetical order.
 */
internal fun String.sort(): String = toCharArray().sorted().joinToString("")