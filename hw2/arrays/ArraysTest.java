package arrays;

import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author Amy Mathews
 */

public class ArraysTest {
    /** FIXME
     */
    @Test
    public void catenate(){
        int[] result = {1,2,3,4};
        int[] A = {1,2};
        int[] B = {3,4};
        assertArrayEquals(result, Arrays.catenate(A, B));
        assertArrayEquals(new int[] {5,7,89,9,7}, Arrays.catenate(new int[] {5,7}, new int[] {89,9,7}));
    }
    @Test
    public void remove(){
        assertArrayEquals(new int[] {1,4}, Arrays.remove(new int[]{1,2,3,4},1,2));
        assertArrayEquals(new int[] {0,3}, Arrays.remove(new int[]{0,1,2,3},1,2));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }
}
