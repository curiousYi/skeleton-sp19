package bearmaps;

import static org.junit.Assert.*;
import org.junit.Test;

public class ArrayHeapMinPQTest {

    /*
        Null case
     */

    /*   1
        2 3
       4  5

       verify getSmallest works
       remove and then verify it gives it in the order we expect
       Math.random


     */
//    @Test
//    public void sanityTestForNoElementGetSmallest() {
//        //TO-DO figure out how to test for  an exception error
//        try {
//            ArrayHeapMinPQ<Double> minHeap = new ArrayHeapMinPQ();
//            double smallest = minHeap.getSmallest();
//        } catch (e) {
//        }
//    }

//    @Test
//    public void sanityTestForNoElementGetSmallest() {
//        //TO-DO figure out how to test for  an exception error
//        try {
//            ArrayHeapMinPQ<Double> minHeap = new ArrayHeapMinPQ();
//            double smallest = minHeap.getSmallest();
//        } catch (e) {
//        }
//    }

    @Test
    public void sanityTestForSingleElementContainsMethod() {
        int randomInteger = (int)Math.random()*(100);
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ();

        minHeap.add(randomInteger, (double) randomInteger);

        assertTrue(minHeap.contains(randomInteger));

        //add number a bunch of test

    }

    @Test
    public void sanityTestForSingleElementAddMethod() {
        int randomInteger = (int)(Math.random()*(100));
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ();

        minHeap.add(randomInteger, (double) randomInteger);

        System.out.println("Working");
        System.out.println(randomInteger);
        System.out.println(minHeap.getSmallest());
        assertEquals((int)minHeap.getSmallest(), randomInteger);

        //add number a bunch of test

    }
//    @Test
//    public void sanityTestForSingleElement() {
//        Math.
//        //add number a bunch of test
//
//    }

}
