package algorithms

import datastructures.TreeNode
import org.junit.Assert.assertEquals
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
}