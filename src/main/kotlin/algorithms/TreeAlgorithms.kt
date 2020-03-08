package algorithms

import datastructures.TreeNode
import java.util.*

/**
 * Insert the given node into a binary search tree iteratively.
 */
internal fun insertIntoBstIterative(root: TreeNode<Int>?, toInsert: Int): TreeNode<Int>? {
    if (root == null) {
        //The tree is empty - return our value as the new root
        return TreeNode(toInsert)
    }
    //The tree is not empty, there is at least a root node
    var currentNode = root
    while (currentNode != null) {
        if (currentNode.value < toInsert) {
            //The current node has a smaller value than our insert value > continue to the right
            if (currentNode.isRightNull()) {
                //We're done, add our new value and return the result
                currentNode.right = TreeNode(toInsert)
                return root
            }
            //The right-hand node was not null, so we continue
            currentNode = currentNode.right
        } else {
            //The current node has a larger value than our insert value > continue to the left
            if (currentNode.isLeftNull()) {
                //We're done, add our new value and return the result
                currentNode.left = TreeNode(toInsert)
                return root
            }
            //The right-hand node was not null, so we continue
            currentNode = currentNode.left
        }
    }
    return root
}

/**
 * Insert the given node into a binary search tree recursively.
 */
internal fun insertIntoBstRecursive(root: TreeNode<Int>?, toInsert: Int): TreeNode<Int>? {
    if (root == null) {
        //The tree is empty - return our value as the new root
        return TreeNode(toInsert)
    }

    with(root) {
        if (toInsert > value) {
            right = insertIntoBstRecursive(right, toInsert)
        } else {
            left = insertIntoBstRecursive(left, toInsert)
        }
        return this
    }
}

/**
 * Inverts a binary tree, except for the root node (duh) - no assumption about the tree being a BST can be made, and
 * frankly it doesn't matter for the task at hand.
 */
internal fun invertBinaryTreeIteratively(root: TreeNode<Int>?): TreeNode<Int>? {
    if (root == null || root.isLeaf()) {
        //The tree is empty - return our value as the new root
        return root
    }

    //Root has at least one left and/or right child and so on
    var tempNode: TreeNode<Int>? = null
    with(root) {
        //Swap left and right
        tempNode = right
        right = left
        left = tempNode
        //Now traverse down the subtrees
        left?.let {
            invertBinaryTreeIteratively(left)
        }
        right?.let {
            invertBinaryTreeIteratively(right)
        }
        return this
    }
}

/**
 * Serializes the given binary tree to string. No assumptions about unique elements or sorting can be made.
 */
//TODO
internal fun serializeBinaryTree(root: TreeNode<Int>?, result: StringBuilder) {
    if (root == null) {
        result.append("null;")
        return
    }
    //Print root node
    result.append("${root.value};")
    //Recurse on left subtree
    serializeBinaryTree(root.left, result)
    //Recurse on right subtree
    serializeBinaryTree(root.right, result)
    throw NotImplementedError()
}

/**
 * Deserializes a binary tree that has been serialized prior using [serializeBinaryTree].
 */
//TODO
internal fun deserializeBinaryTree(root: TreeNode<Int>?, result: StringBuilder) {
    if (root == null) {
        result.append("null;")
        return
    }
    //Print root node
    result.append("${root.value};")
    //Recurse on left subtree
    serializeBinaryTree(root.left, result)
    //Recurse on right subtree
    serializeBinaryTree(root.right, result)
    throw NotImplementedError()
}

/**
 * Uses recursion and a stack. The strategy here entirely relies on the fact that in in-order-traversal in case of a BST
 * the numbers printed would make for a sequence where the condition n < n+1 is always true.
 * @return true if the given tree is a valid BST; false otherwise.
 */
internal fun isValidBSTRecursiveAndStack(node: TreeNode<Int>?): Boolean {

    /**
     * Inline function to do the recursion
     */
    fun isValidBinarySearchTree(node: TreeNode<Int>?, stack: Deque<Int>) {
        //We're going with in order traversal here, as in case of a BST it would mean as we pop elements from the stack,
        //each element we pop (if pushed in lnr order) is greater than the next popped element

        //We reached the end, stop here
        if (node == null) {
            return
        }

        //First we go left
        if (node.left != null) {
            isValidBinarySearchTree(node.left, stack)
        }
        //Now we read our node
        stack.push(node.value)

        //Now we go right
        if (node.right != null) {
            isValidBinarySearchTree(node.right, stack)
        }
    }

    if (node == null) {
        return true
    }
    if (node.isLeaf()) {
        return true
    }
    val stack = ArrayDeque<Int>()
    isValidBinarySearchTree(node, stack)
    //Process the stack
    var lastValue = stack.pop()
    while (stack.peek() != null) {
        if (lastValue <= stack.peek()) {
            //This is our deal-breaker
            return false
        } else {
            lastValue = stack.pop()
        }
    }
    return true
}

/**
 * Pure recursive solution to validating a BST. Time and space complexity is O(n)
 * @return true if the given tree is a valid BST; false otherwise.
 */
internal fun isValidBSTRecursive(node: TreeNode<Int>?): Boolean {
    //we need to not only compare immediate neighbors but also need to ensure integrity across the entire tree, which
    //is why we use upper and lower limits
    fun recursionHelper(node: TreeNode<Int>?, lowerLimit: Int?, upperLimit: Int?): Boolean {
        if (node == null) {
            //An empty node is avalid in BST terms
            return true
        }
        val nodeValue = node.value
        //Validate node against limits
        if (lowerLimit != null && nodeValue <= lowerLimit) return false
        if (upperLimit != null && nodeValue >= upperLimit) return false

        //Check conditions for left subtree
        if (!recursionHelper(node.left, lowerLimit, nodeValue)) return false
        //Check conditions for right subtree
        if (!recursionHelper(node.right, nodeValue, upperLimit)) return false
        //If we reached here, we're done.
        return true
    }

    return recursionHelper(node, null, null)
}
