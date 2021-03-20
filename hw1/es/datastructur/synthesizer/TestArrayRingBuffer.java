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


        //test what to do if dequeu empty?

        //




    }


    //test it stores one thing
    //test it stores 2 things

    //test that we hit a limit slash exception is thrown
}
