package enigma;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import static  org.junit.Assert.*;

import java.util.HashMap;

import static enigma.TestUtils.*;

public class PermTest {

    @Test
    public void test1(){
        String cycles = "(abc) (def)";
        Alphabet testa = new Alphabet("abcdef");
        char[][] result = {{'a','b','c'},{'d','e','f'}};
        Permutation test =  new Permutation(cycles,testa);
        assertEquals(result,test);
    }
}
