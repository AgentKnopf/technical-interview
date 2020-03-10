package algorithms

import datastructures.TreeNode
import org.junit.Assert.*
import org.junit.Test

internal class TreeAlgorithmsTest {

    /**
     * Method under test [insertIntoBstIterative]
     */
    @Test
    fun insertIntoBstIterativeTest() {
        insertIntoBstTest(::insertIntoBstIterative)
    }

    /**
     * Method under test [insertIntoBstRecursive]
     */
    @Test
    fun insertIntoBstRecursiveTest() {
        insertIntoBstTest(::insertIntoBstRecursive)
    }

    private fun insertIntoBstTest(function: (TreeNode<Int>?, Int) -> TreeNode<Int>?) {
        //A null-tree
        var resultTree = function(null, 5)
        assertEquals(5, resultTree!!.value)

        /*
        Builds the following BTS tree:
                4
               / \
              2   7
             / \
            1   3
         */
        var tree = TreeNode(4)
        tree.left = TreeNode(2, TreeNode(1), TreeNode(3))
        tree.right = TreeNode(7)
        //Insert the number 5 into the tree
        resultTree = function(tree, 5)
        println(resultTree.toString())
        //5 should be put as a left-hand child node of 7
        assertEquals(5, resultTree!!.right!!.left!!.value)


        /*
        Builds the following BTS tree:
                8
               / \
              6   10
             / \
            4   7
         */
        tree = TreeNode(8)
        tree.left = TreeNode(6, TreeNode(4), TreeNode(7))
        tree.right = TreeNode(10)
        //Insert the number 5 into the tree
        resultTree = function(tree, 3)
        //5 should be put as a left-hand child node of 7
        assertEquals(3, resultTree!!.left!!.left!!.left!!.value)
    }

    /**
     * Inversion of a binary search tree (after which it no longer is a BST).
     */
    @Test
    fun invertBinaryTreeTest() {
        /*
          Original:
                 4
               /   \
              2     7
             / \   / \
            1   3 6   9

          Target:

                 4
               /   \
              7     2
             / \   / \
            9   6 3   1

          Basically everything will be mirrored to the other side, exept for the root node.
         */
        var tree = TreeNode(4)
        tree.left = TreeNode(2, TreeNode(1), TreeNode(3))
        tree.right = TreeNode(7, TreeNode(6), TreeNode(9))
        var invertedTree = invertBinaryTreeIteratively(tree)!!
        println(invertedTree.toString())
        //Assert correctness of the result
        assertEquals(4, invertedTree.value)
        assertEquals(7, invertedTree.left!!.value)
        assertEquals(2, invertedTree.right!!.value)
        //Check left subtree
        assertEquals(9, invertedTree.left!!.left!!.value)
        assertEquals(6, invertedTree.left!!.right!!.value)
        //Check right subtree
        assertEquals(1, invertedTree.right!!.right!!.value)
        assertEquals(3, invertedTree.right!!.left!!.value)
    }

    @Test
    fun serializeBinaryTreeTest() {
        //Empty tree
        assertEquals("", serializeBinaryTree(null))

        /*
          Tree to serialize:
                 4
               /
              2
         */
        var tree = TreeNode(4)
        assertEquals("4;null;null;", serializeBinaryTree(tree))

        /*
          Tree to serialize:
                 4
               /
              2
         */
        tree = TreeNode(4)
        tree.left = TreeNode(2, null, null)
        assertEquals("4;2;null;null;null;", serializeBinaryTree(tree))

        /*
          Tree to serialize:
                 4
               /   \
              2     7
             / \   / \
            1   3 6   9
         */
        tree = TreeNode(4)
        tree.left = TreeNode(2, TreeNode(1), TreeNode(3))
        tree.right = TreeNode(7, TreeNode(6), TreeNode(9))
        assertEquals("4;2;1;null;null;3;null;null;7;6;null;null;9;null;null;", serializeBinaryTree(tree))

        /*
          Tree to serialize:
                 1
               /   \
              2     5
             / \
            3   4
         */
        tree = TreeNode(1)
        tree.left = TreeNode(2, TreeNode(3), TreeNode(4))
        tree.right = TreeNode(5)
        assertEquals("1;2;3;null;null;4;null;null;5;null;null;", serializeBinaryTree(tree))
    }

    @Test
    fun deserializeBinaryTreeTest() {
        //Empty tree
        assertNull(deserializeBinaryTree(""))

        //The tree we expect from the deserialization
        var tree = TreeNode(4)
        tree.left = TreeNode(2, TreeNode(1), TreeNode(3))
        tree.right = TreeNode(7, TreeNode(6), TreeNode(9))
        assertEquals(tree, deserializeBinaryTree("4;2;1;null;null;3;null;null;7;6;null;null;9;null;null;"))

        //The tree we expect from the deserialization
        assertEquals(TreeNode(4), deserializeBinaryTree("4;null;null;"))

        //The tree we expect from the deserialization
        tree = TreeNode(1)
        tree.left = TreeNode(2, TreeNode(3), TreeNode(4))
        tree.right = TreeNode(5)
        assertEquals(tree, deserializeBinaryTree("1;2;3;null;null;4;null;null;5;null;null;"))

    }

    @Test
    fun isValidBSTRecursiveAndStackTest() {
        isValidBSTTest(::isValidBSTRecursiveAndStack)
    }

    @Test
    fun isValidBSTRecursiveTest() {
        isValidBSTTest(::isValidBSTRecursive)
    }

    /**
     * Helper method to test if a given tree is a valid BST.
     */
    private fun isValidBSTTest(functionToTest: (TreeNode<Int>?) -> Boolean) {
        /*
          Tree to check:
                 4
               /   \
              2     7
             / \   / \
            1   3 6   9
         */
        var tree = TreeNode(4)
        tree.left = TreeNode(2, TreeNode(1), TreeNode(3))
        tree.right = TreeNode(7, TreeNode(6), TreeNode(9))
        //The above is a valid BST
        assertTrue(functionToTest(tree))

        /*
          Tree to check:
                 4
               /   \
              2     7
             / \   / \
            3   1 6   9
         */
        tree = TreeNode(4)
        tree.left = TreeNode(2, TreeNode(3), TreeNode(1))
        tree.right = TreeNode(7, TreeNode(6), TreeNode(9))
        //The above is not a valid BST
        assertFalse(functionToTest(tree))

        /*
          Tree to check:
                 4
               /   \
              2     3
         */
        tree = TreeNode(4)
        tree.left = TreeNode(2)
        tree.right = TreeNode(3)
        //The above is not a valid BST
        assertFalse(functionToTest(tree))

        /*
          Tree to check:
                 1
               /
              1
         */
        tree = TreeNode(1)
        tree.left = TreeNode(1)
        //The above is not a valid BST
        assertFalse(functionToTest(tree))

        //Edge case because a node in the right sub tree is smaller than the root node:
        tree = TreeNode(5)
        tree.left = TreeNode(1)
        tree.right = TreeNode(6, TreeNode(4), TreeNode(7))

        //Edge case: Tree is null
        assertTrue(functionToTest(null))
    }
}