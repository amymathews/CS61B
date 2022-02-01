import static org.junit.Assert.*;
import org.junit.Test;

public class MultiArrTest {

    @Test
    public void testMaxValue() {
        assertEquals(33,MultiArr.maxValue(new int[][] {{1,2,3}, {33}, {17,8,25}}));
        assertEquals(101001,MultiArr.maxValue(new int[][] {{-2674,2,10101}, {99,89}, {101001,8,26}}));

    }

    @Test
    public void testAllRowSums() {

        assertArrayEquals(new int[]{4, 20, 0}, MultiArr.allRowSums(new int[][] {{1,2,1},{10,9,1}, {0,0,0}}));
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(MultiArrTest.class));
    }
}
