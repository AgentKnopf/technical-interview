package algorithms


/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Note: The steps to the approach implemented here are as follows:
 *
 * #1 Fill candy array with 1 candy for each cell
 * #2 Go from left to right > update candy
 * #3 Go from right to left > update candy
 */
internal fun candyCount(ratings: IntArray): Int {
    val maxIndex = ratings.size - 1
    if (maxIndex < 0) {
        return 0
    }
    val candies = IntArray(maxIndex + 1) { 1 }

    //Left to right, skipping the first element as we only compare left-hand neighbors
    for (i in 1..maxIndex) {
        if (ratings[i] > ratings[i - 1]) {
            candies[i] = candies[i - 1] + 1
        }
    }
    var sum = candies[maxIndex]
    //Right to left, skipping the last element as we only compare right-hand neighbors
    for (i in maxIndex - 1 downTo 0) {
        if (ratings[i] > ratings[i + 1]) {
            candies[i] = Math.max(candies[i], candies[i + 1] + 1)
        }
        sum += candies[i]
    }
    return sum
}