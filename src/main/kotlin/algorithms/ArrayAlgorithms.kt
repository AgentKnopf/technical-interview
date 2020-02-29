package algorithms

/**
 * Sliding window problem: Two buckets, each can only hold one type of fruit, what's the most fruits we can collect.
 * https://levelup.gitconnected.com/an-introduction-to-sliding-window-algorithms-5533c4fe1cc7
 */
internal fun totalFruit(tree: Array<Int>): Int {
    //Find the longest contiguous sequence, note that each basket can only contain one type of fruit
    //Special cases or easy cases we don't need to compute due to number of baskets/trees
    val treeCount = tree.size
    if (treeCount == 0) {
        return 0
    } else if (treeCount == 1) {
        return 1
    } else if (treeCount == 2) {
        return 2
    }
    //Here we start the actual algorithm that operates on any number of trees
    //We keep track of the fruit types we already collected
    val maxIndex = tree.size - 1
    var bucketOne = -1
    var bucketTwo = -1

    var maxSequenceCount = 0
    var rightPointer: Int

    for (leftPointer in 0..maxIndex) {
        //Update the right pointer
        rightPointer = leftPointer
        //Check if we're done
        if (maxIndex - leftPointer < maxSequenceCount) {
            //We can't do better anymore, so just stop
            return maxSequenceCount
        }
        while (rightPointer <= maxIndex) {
            val fruit = tree[rightPointer]
            if (canFitInBucket(fruit, bucketOne, bucketTwo)) {
                //Put the fruit in either bucket
                if (bucketOne == -1 || bucketOne == fruit) {
                    bucketOne = fruit
                } else if (bucketTwo == -1 || bucketTwo == fruit) {
                    bucketTwo = fruit
                }
            } else {
                //Buckets are full and we're done, clear the buckets
                bucketOne = -1
                bucketTwo = -1
                break
            }
            val currentSequenceCount = rightPointer - leftPointer + 1
            //Check if we have improved our sequence
            if (currentSequenceCount > maxSequenceCount) {
                maxSequenceCount = currentSequenceCount
            }
            rightPointer++
        }
    }
    return maxSequenceCount
}

/**
 * @return true if we can add this fruit type to either of our buckets; false otherwise.
 * also return true, if the fruit type is already in either bucket.
 */
private fun canFitInBucket(fruit: Int, bucketOne: Int, bucketTwo: Int): Boolean {
    val isInBucket = fruit == bucketOne || fruit == bucketTwo
    val areBucketsFull = bucketOne != -1 && bucketTwo != -1
    return isInBucket || !areBucketsFull
}