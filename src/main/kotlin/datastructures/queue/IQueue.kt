package datastructures.queue

/**
 * Interface for a queue like implementation.
 */
internal interface IQueue<T> {
    /**
     * Enqueues the given element into the queue.
     */
    fun enqueue(value: T)

    /**
     * @return the current head of the queue; null if the queue is empty.
     */
    fun dequeue(): T?

    /**
     * @return number of elements in the queue.
     */
    fun size(): Int
}