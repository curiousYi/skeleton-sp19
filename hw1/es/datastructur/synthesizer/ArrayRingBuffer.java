package es.datastructur.synthesizer;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import org.junit.Test;

import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[])new Object[capacity];
        first = capacity/2;
        last = first;
        fillCount = 0;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.

        // TODO: check if we are full
        if(fillCount == rb.length) {
            throw new RuntimeException("Ring buffer overflow");
        }

        if(fillCount == 0) {
            last = first;
        }

        rb[last] = x;
        last = last + 1;
        fillCount = fillCount + 1;
        //check if we are full
        if(last == rb.length) {
            last = 0;
        }
        //TODO our check to see if first or last we've hit the bounds
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.

        if(fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T output = rb[first];
        first++;
        if(first == rb.length) {
            first = 0;
        }
        fillCount = fillCount - 1;
        return output;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change;
        if(fillCount > 0 ) {
            if(last == 0) {
                return rb[rb.length - 1];
            } else {
            return rb[last - 1];
            }
        } else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    public int capacity() {
        return rb.length;
    }

    public int fillCount() {
        return fillCount;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
