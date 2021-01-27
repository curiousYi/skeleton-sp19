import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        return a == b;
    }

    @Test
    public void testProduct() {
        /* assertEquals for comparison of ints takes two arguments:
        assertEquals(expected, actual).
        if it is false, then the assertion will be false, and this test will fail.
        */

        assertEquals(30, Arithmetic.product(5, 6));
        assertEquals(-30, Arithmetic.product(5, -6));
        assertEquals(0, Arithmetic.product(0, -6));
    }

}
