package algorithms

/**
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to
 * guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in
 * said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match
 * the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses
 * and hints to eventually derive the secret number.
 *
 *  Write a function to return a hint according to the secret number and friend's guess, use A to indicate the
 * bulls and B to indicate the cows.
 *
 * Please note that both secret number and friend's guess may contain duplicate digits.
 */
internal fun bullsAndCowsGetHint(secret: String, guess: String): String {
    //Each non-bull is saved here with: value to count of occurrences
    val potentialCows = mutableMapOf<Char, Int>()
    var bullCount = 0
    var guessWithoutBulls = ""
    //Find the bulls and potential cows
    secret.forEachIndexed { index, value ->
        val guessAtIndex = guess[index]
        if (value == guessAtIndex) {
            //Found a bull
            bullCount++
        } else {
            guessWithoutBulls += guessAtIndex
            //Found a potential cow
            if (potentialCows.contains(value)) {
                //Update count of occurrence
                potentialCows[value] = potentialCows[value]!! + 1
            } else {
                //New entry
                potentialCows[value] = 1
            }
        }
    }
    //Check the guess (minus confirmed bulls) and match against remaining digits
    var cowCount = 0
    guessWithoutBulls.forEach { value ->
        //Check if the value exists in our potential cows, if yes, reduce the count
        if (potentialCows.contains(value)) {
            //We found a cow
            cowCount++
            //Check what the count is, and decrease
            val count = potentialCows[value]!!
            if (count == 1) {
                //Remove the entry
                potentialCows.remove(value)
            } else {
                //Reduce the count
                potentialCows[value] = count - 1
            }
        }
    }
    return "${bullCount}A${cowCount}B"
}