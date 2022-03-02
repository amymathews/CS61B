package enigma;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import static  org.junit.Assert.*;

import java.util.HashMap;

import static enigma.TestUtils.*;

public class AlphaTest {
    /***** Tests *****/

    @Test
    public void testSize() {
        Alphabet test = new Alphabet("ABC");
        int size = test.size();
        assertEquals(3, size);
    }

    @Test
    public void testContains() {
        Alphabet test = new Alphabet("ABC");
        char c = 'A';
        boolean ans = true;
        boolean result = test.contains(c);
        assertEquals(ans, result);
    }

    @Test
    public void testToChar() {
        Alphabet test = new Alphabet("ABC");
        int index = 2;
        char result = test.toChar(2);
        char exp = 'C';
        assertEquals(exp, result);
    }

    @Test
    public void testToInt() {
        Alphabet test = new Alphabet("ABC");
        char c = 'C';
        int exp = 2;
        int result = test.toInt(c);
        assertEquals(exp, result);

    }
}
