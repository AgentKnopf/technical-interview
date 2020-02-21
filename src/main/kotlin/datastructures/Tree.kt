package datastructures

/**
 * Represents a node in a tree which has a value and one node each - left and right.
 * Can be used to represent a BTS.
 */
internal data class TreeNode<T>(val value: T, var left: TreeNode<T>? = null, var right: TreeNode<T>? = null) {
    /**
     * @return true if the right node is null
     */
    fun isRightNull() = right == null

    /**
     * @return true if the left node is null
     */
    fun isLeftNull() = left == null

    /**
     * @return true if the left and right node are null; false otherwise
     */
    fun isLeaf() = isRightNull() && isLeftNull()
}