package datastructures.queue

import java.util.*

/**
 * Implements a queue using two stacks. A push stack and a pop stack.
 */
internal class QueueUsingTwoStacks<T> : IQueue<T> {
    private val popStack = Stack<T>()
    private val pushStack = Stack<T>()

    override fun enqueue(value: T) {
        pushStack.push(value)
    }

    override fun dequeue(): T? {
        return if (popStack.isEmpty()) {
            //Get the objects from the pushStack if available and attempt another dequeue, otherwise return null
            if (moveAllFromPushToPopStack()) dequeue() else null
        } else {
            popStack.pop()
        }
    }

    override fun size(): Int = popStack.size + pushStack.size
    /**
     * @return true if elements were moved; false otherwise
     */
    private fun moveAllFromPushToPopStack(): Boolean {
        while (pushStack.isNotEmpty()) {
            popStack.push(pushStack.pop())
        }
        return popStack.size > 0
    }
}