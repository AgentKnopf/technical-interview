package datastructures

/**
 * Implements a singly linked list.
 */
internal class LinkedListImpl<T> {
    private var tail: Node<T>? = null
    private var head: Node<T>? = null
    private var size = 0

    /**
     * Adds the given element to the end of the list.
     */
    fun add(element: T) {
        //Create the node
        val newNode = Node(element)
        if (head == null) {
            //This is our new head
            head = newNode
        }
        if (tail != null) {
            //Rewire the tail node
            tail!!.next = newNode
        }
        //This is definitely going to be our new tail
        tail = newNode
        size++
    }

    /**
     * Removes the element at the given location (zero based index).
     * @return the node that was removed.
     * @throws IndexOutOfBoundsException if the given index is out of bounds.
     */
    fun remove(at: Int): T {
        raiseExceptionIfInvalidIndex(at, size)
        //Its a singly linked list so we can only start at the beginning to search for the right index
        var index = 0
        var currentNode: Node<T>? = head
        var nodeBeforeCurrent: Node<T>? = null
        while (index < at && currentNode != null) {
            nodeBeforeCurrent = currentNode
            currentNode = currentNode.next
            index++
        }

        if (currentNode == head) {
            //We need to re-link the new head if available
            head = currentNode!!.next
        } else if (currentNode == tail) {
            //We need to re-link the new tail if available
            tail = nodeBeforeCurrent
            nodeBeforeCurrent?.next = null
        }

        if (currentNode?.next != null && nodeBeforeCurrent != null) {
            //Connect the previous node and the next node
            nodeBeforeCurrent.next = currentNode.next
        }
        size--
        return currentNode!!.value
    }

    /**
     * @return the element at the given location (zero based index).
     * @throws IndexOutOfBoundsException if the given index is out of bounds.
     */
    fun get(at: Int): T {
        raiseExceptionIfInvalidIndex(at, size)
        //Its a singly linked list so we can only start at the beginning to search for the right index
        var index = 0
        var currentNode: Node<T>? = head
        while (index < at && currentNode != null) {
            currentNode = currentNode.next
            index++
        }
        return currentNode!!.value
    }

    override fun toString(): String {
        val result = StringBuilder()
        var currentNode = head
        while (currentNode != null) {
            result.append("${currentNode.value} ")
            //Print the current node
            currentNode = currentNode.next
        }
        return result.toString().trim()
    }

    fun size(): Int = size

    /**
     * @throws IndexOutOfBoundsException if the given index exceeds the official size of the array (official size < internal size)
     */
    private fun raiseExceptionIfInvalidIndex(index: Int, size: Int) {
        if (index > size - 1) {
            throw IndexOutOfBoundsException("Invalid index $index")
        }
    }
}

/**
 * Represents a node in the list.
 */
private class Node<T>(val value: T) {
    var next: Node<T>? = null

    fun hasNext(): Boolean = next != null
}