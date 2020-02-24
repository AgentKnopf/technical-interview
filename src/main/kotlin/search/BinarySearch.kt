package search

/**
 * Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search
 * target in nums. If target exists, then return its index, otherwise return -1.
 *
 * You may assume that all elements in nums are unique.
 * n will be in the range [1, 10000].
 * The value of each element in nums will be in the range [-9999, 9999].
 */
internal fun binarySearch(nums: IntArray, target: Int): Int {
    var pivot: Int
    var left = 0
    var right = nums.size - 1
    while (left <= right) {
        pivot = left + (right - left) / 2
        when {
            nums[pivot] == target -> return pivot
            target < nums[pivot] -> right = pivot - 1
            else -> left = pivot + 1
        }
    }
    return -1
}