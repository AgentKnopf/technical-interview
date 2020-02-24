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

fun stringToInteger(value: String): Int {
    //Check if the first non-whitespace character is a digit or +/- - otherwise we'll return 0
    val valueTrimmed = value.trimStart()
    if (valueTrimmed.isEmpty()) {
        return 0
    }
    val isNotDigit = !valueTrimmed[0].isDigit()
    val isPlus = valueTrimmed[0] == '+'
    val isMinus = valueTrimmed[0] == '-'
    if ((valueTrimmed.length == 1 && (isPlus || isMinus)) ||
        (isNotDigit && !isPlus && !isMinus)
    ) {
        return 0
    }

    //First check if there is a plus symbol
    var numberAsString = value.replace("[^\\d-.]".toRegex(), "")
    println(numberAsString)
    return try {
        //If the resulting string contains a dot we remove everything after the dot
        val indexOfDot = numberAsString.indexOf('.', 0)
        if (indexOfDot >= 0) {
            //We got a dot, cut it off and everything after it
            numberAsString = numberAsString.substring(0, indexOfDot)
        }
        numberAsString.toInt()
    } catch (e: NumberFormatException) {
        //We know its a valid number, so it must be out of range, return [Int.MIN_VALUE]
        Int.MIN_VALUE
    }
}