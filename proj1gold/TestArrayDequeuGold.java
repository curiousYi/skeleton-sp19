import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeuGold {

    @Test
    public void testMethod1() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sln = new ArrayDequeSolution<>();

        for (int i = 0; i < 1000; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(i);
                sln.addLast(i);
            } else if(numberBetweenZeroAndOne >= 0.25 && numberBetweenZeroAndOne < 0.5) {
                if(sln.size() > 0) {
                    sad1.removeFirst();
                    sln.removeFirst();
                } else {
                    sad1.addFirst(i);
                    sln.addFirst(i);
                }
            } else if(numberBetweenZeroAndOne >= 0.5 && numberBetweenZeroAndOne < 0.75) {
                sad1.addFirst(i);
                sln.addFirst(i);
            } else {
                if(sln.size() > 0) {
                    sad1.removeLast();
                    sln.removeLast();
                } else {
                    sad1.addLast(i);
                    sln.addLast(i);
                }
            }
        }

        Integer[] sad1Soln = new Integer[sad1.size()];
        Integer[] slnArray = new Integer[sln.size()];
        Integer slnSize = sln.size();


        for(int i = 0; i < slnSize; i++) {
            sad1Soln[i] = sad1.removeFirst();
            slnArray[i] = sln.removeFirst();
        }


        assertArrayEquals("The output did not match soln", sad1Soln, slnArray);
    }
}
