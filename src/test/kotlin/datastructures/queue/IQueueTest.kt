package datastructures.queue

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

/**
 * Runs a set of tests against the given String-based queue.
 */
internal fun enqueueDequeue(queue: IQueue<String>) {
    assertEquals(0, queue.size())

    //Add elements like this: Head -> hello, world, how, are, you <- Tail
    queue.enqueue("hello")
    queue.enqueue("world")
    queue.enqueue("how")
    queue.enqueue("are")
    queue.enqueue("you")
    assertEquals(5, queue.size())

    //When doing a dequeue we expect "hello" first, then "world" etc... FIFO fashion
    assertEquals("hello", queue.dequeue())
    assertEquals("world", queue.dequeue())
    assertEquals(3, queue.size())

    //Add two more elements
    queue.enqueue("doing")
    queue.enqueue("today")
    assertEquals(5, queue.size())

    //Remove all elements
    assertEquals("how", queue.dequeue())
    assertEquals("are", queue.dequeue())
    assertEquals("you", queue.dequeue())
    assertEquals("doing", queue.dequeue())
    assertEquals("today", queue.dequeue())
    assertEquals(0, queue.size())

    //Expected result when calling dequeue on an empty queue is null
    assertNull(queue.dequeue())
}