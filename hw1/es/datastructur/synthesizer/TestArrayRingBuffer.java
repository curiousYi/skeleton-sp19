package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testBasics() {
        ArrayRingBuffer<Integer> arr = new ArrayRingBuffer<Integer>(5);
        arr.enqueue(3);

        Integer expected3 = 3;
        assertEquals(arr.peek(), expected3);
        assertEquals(arr.dequeue(),expected3);

        for(int i = 3; i < 3+4; i++) {
            arr.enqueue(i);
        }

        for(int i = 3; i < 3+4; i++) {
            assertEquals(arr.dequeue(), (Integer) i);
        }

    }

    @Test(expected = RuntimeException.class)
    public void testBounded() {
        ArrayRingBuffer<Integer> arr = new ArrayRingBuffer<Integer>(5);
        arr.enqueue(3);


        for (int i = 3; i < 3 + 4; i++) {
            arr.enqueue(i);
        }
        arr.enqueue(8);
    }

    @Test(expected = RuntimeException.class)
    public void testCannotDequeueEmpty() {
        ArrayRingBuffer<Integer> arr = new ArrayRingBuffer<Integer>(5);
        arr.dequeue();
    }

    @Test(expected = RuntimeException.class)
    public void testCannotPeekEmpty() {
        ArrayRingBuffer<Integer> arr = new ArrayRingBuffer<Integer>(5);
        arr.peek();
    }
}
