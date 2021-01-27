import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {

    /** Performs a few arbitrary tests to see if the product method is correct */

    @Test
    public void testSteveCase() {
        /* assertEquals for comparison of ints takes two arguments:
        assertEquals(expected, actual).
        if it is false, then the assertion will be false, and this test will fail.
        */
        int i = 0;
        for (int j = 0; i < 9; ++i, ++j) {
            assertEquals(true, Flik.isSameNumber(i, j));
        }
    }

    /** Performs a few arbitrary tests to see if the sum method is correct */

//    @Test
//    public void testSum() {
//
//        assertEquals(11, Arithmetic.sum(5, 6));
//        assertEquals(-1, Arithmetic.sum(5, -6));
//        assertEquals(-6, Arithmetic.sum(0, -6));
//        assertEquals(0, Arithmetic.sum(6, -6));
//    }

    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.TestRunner.runTests("all", FlikTest.class);
    }
}

