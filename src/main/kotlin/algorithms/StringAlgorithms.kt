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

/**
 * @return true if all characters in the given String are unique; false otherwise
 */
internal fun String?.hasUniqueCharacters(withDataStructure: Boolean = true): Boolean = if (this == null) {
    false
} else {
    if (withDataStructure) hasUniqueCharactersUsingDataStructure(this) else hasUniqueCharactersUsingNoDataStructure(this)
}

/**
 * Implements [hasUniqueCharacters] using no additional data structure.
 */
private fun hasUniqueCharactersUsingNoDataStructure(value: String): Boolean {
    //We'll go through each character and compare it to the rest of the characters, resulting in O(n)
    val characters = value.toLowerCase().toCharArray()
    for ((outerIndex, outerCharacter) in characters.withIndex()) {
        for ((innerIndex, innerCharacter) in characters.withIndex()) {
            if (innerIndex != outerIndex && outerCharacter == innerCharacter) {
                return false
            }
        }
    }
    return true
}

/**
 * Implements [hasUniqueCharacters] with additional data structure.
 */
private fun hasUniqueCharactersUsingDataStructure(value: String): Boolean {
    var set = mutableSetOf<Char>()
    value.toCharArray().forEach { character -> set = set.plus(character.toLowerCase()).toMutableSet() }
    return set.size == value.length
}
