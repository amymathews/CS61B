package lists;

import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *
 *  @author FIXME
 */

public class ListsTest {

    @Test
    public void basicRunsTest() {
        IntList input = IntList.list(1, 2, 3, 1, 2);
        IntList run1 = IntList.list(1, 2, 3);
        IntList run2 = IntList.list(1, 2);
        IntListList result = IntListList.list(run1, run2);
        assertEquals(result.toString(), Lists.naturalRuns(input).toString());
        //FIXME: Add some assertion to make this a real test.
    }
    @Test
    public void naturalRuns(){
        IntList input1 = IntList.list(1,2,2,1,3);
        IntList run3 = IntList.list(1,2);
        IntList run4 =IntList.list(2);
        IntList run5 = IntList.list(1,3);
        IntListList result2 = IntListList.list(run3,run4,run5);
        assertEquals(result2, Lists.naturalRuns(input1));

    }
    //FIXME: Add more tests!

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ListsTest.class));
    }
}
