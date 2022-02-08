package image;

import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author Amy Mathews
 */

public class MatrixUtilsTest {
    /** FIXME
     */
    @Test
    public void accumulateVertical(){
        double [][] input = {{1000000   ,1000000   ,1000000   ,1000000},
                            {1000000     ,75990    ,30003   ,1000000},
                            {1000000     ,30002    ,103046   ,1000000},
                            {1000000    , 29515     ,38273   ,1000000},
                            {1000000     ,73403     ,35399   ,1000000},
                            {1000000   ,1000000   ,1000000   ,1000000}};
        double [][] result = {{1000000   ,1000000   ,1000000   ,1000000},
                                {2000000   ,1075990   ,1030003   ,2000000},
                                {2075990   ,1060005   ,1133049   ,2030003},
                                {2060005   ,1089520   ,1098278   ,2133049},
                                {2089520   ,1162923   ,1124919   ,2098278},
                                {2162923   ,2124919   ,2124919   ,2124919}};
        assertArrayEquals(result, MatrixUtils.accumulateVertical(input));
    }
    @Test
    public void accumulate() {
        double [][] input = {{1, 1, 1, 1},
                {1, 2, 2, 1},
                {1, 3, 3, 1},
                {1, 1, 1, 1}};
        double [][] result = {{1, 1, 1, 1},
                {2, 3, 3, 2},
                {3, 5, 5, 3},
                {4, 4, 4, 4}};
        assertArrayEquals(result, MatrixUtils.accumulate(input, MatrixUtils.Orientation.VERTICAL));
        double [][] input2 = {{1,0,3},{4,5,6},{7,8,9}};
        double [][] result2 = {{1,0,3},{4,5,6},{11,12,14}};
        assertArrayEquals(result2, MatrixUtils.accumulate(input2, MatrixUtils.Orientation.VERTICAL));

    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MatrixUtilsTest.class));
    }
}
