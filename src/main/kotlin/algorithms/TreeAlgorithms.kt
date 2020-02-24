package algorithms

import datastructures.TreeNode

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