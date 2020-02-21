package datastructures

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Class under test [TreeNode]
 */
internal class TreeNodeTest {

    @Test
    fun isRightNull() {
        val treeNode = TreeNode(5)
        assertTrue(treeNode.isRightNull())
        treeNode.right = TreeNode(20)
        assertFalse(treeNode.isRightNull())
    }

    @Test
    fun isLeftNull() {
        val treeNode = TreeNode(5)
        assertTrue(treeNode.isLeftNull())
        treeNode.left = TreeNode(4)
        assertFalse(treeNode.isLeftNull())
    }

    @Test
    fun isLeaf() {
        val treeNode = TreeNode(5)
        assertTrue(treeNode.isLeaf())
        treeNode.left = TreeNode(4)
        assertFalse(treeNode.isLeaf())
        treeNode.left = TreeNode(9)
        assertFalse(treeNode.isLeaf())
        treeNode.left = null
        treeNode.right = null
        assertTrue(treeNode.isLeaf())
    }

}