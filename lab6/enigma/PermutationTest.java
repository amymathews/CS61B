package enigma;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;


import static org.junit.Assert.*;

import static enigma.TestUtils.*;

/**
 * The suite of all JUnit tests for the Permutation class. For the purposes of
 * this lab (in order to test) this is an abstract class, but in proj1, it will
 * be a concrete class. If you want to copy your tests for proj1, you can make
 * this class concrete by removing the 4 abstract keywords and implementing the
 * 3 abstract methods.
 *
 *  @author
 */
public abstract class PermutationTest {

    /**
     * For this lab, you must use this to get a new Permutation,
     * the equivalent to:
     * new Permutation(cycles, alphabet)
     * @return a Permutation with cycles as its cycles and alphabet as
     * its alphabet
     * @see Permutation for description of the Permutation conctructor
     */
    abstract Permutation getNewPermutation(String cycles, Alphabet alphabet);

    /**
     * For this lab, you must use this to get a new Alphabet,
     * the equivalent to:
     * new Alphabet(chars)
     * @return an Alphabet with chars as its characters
     * @see Alphabet for description of the Alphabet constructor
     */
    abstract Alphabet getNewAlphabet(String chars);

    /**
     * For this lab, you must use this to get a new Alphabet,
     * the equivalent to:
     * new Alphabet()
     * @return a default Alphabet with characters ABCD...Z
     * @see Alphabet for description of the Alphabet constructor
     */
    abstract Alphabet getNewAlphabet();

    /** Testing time limit. */
    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    /** Check that PERM has an ALPHABET whose size is that of
     *  FROMALPHA and TOALPHA and that maps each character of
     *  FROMALPHA to the corresponding character of FROMALPHA, and
     *  vice-versa. TESTID is used in error messages. */
    private void checkPerm(String testId,
                           String fromAlpha, String toAlpha,
                           Permutation perm, Alphabet alpha) {
        int N = fromAlpha.length();
        assertEquals(testId + " (wrong length)", N, perm.size());
        for (int i = 0; i < N; i += 1) {
            char c = fromAlpha.charAt(i), e = toAlpha.charAt(i);
            assertEquals(msg(testId, "wrong translation of '%c'", c),
                         e, perm.permute(c));
            assertEquals(msg(testId, "wrong inverse of '%c'", e),
                         c, perm.invert(e));
            int ci = alpha.toInt(c), ei = alpha.toInt(e);
            assertEquals(msg(testId, "wrong translation of %d", ci),
                         ei, perm.permute(ci));
            assertEquals(msg(testId, "wrong inverse of %d", ei),
                         ci, perm.invert(ei));
        }
    }

    /* ***** TESTS ***** */

    @Test
    public void checkIdTransform() {
        Alphabet alpha = getNewAlphabet();
        Permutation perm = getNewPermutation("", alpha);
        checkPerm("identity", UPPER_STRING, UPPER_STRING, perm, alpha);
    }
    @Test
    public void Test1(){
          Permutation perm1 = getNewPermutation("(ABC)", getNewAlphabet("ABCDE"));
          assertEquals('B',perm1.permute('A'));
          assertEquals('C', perm1.permute('B'));
          assertEquals('A', perm1.permute('C'));
          assertEquals('D', perm1.permute('D'));
          assertEquals('E', perm1.permute('E'));
          assertEquals(5,perm1.size());
          assertEquals('B', perm1.invert('C'));
          assertEquals('A', perm1.invert('B'));
          assertEquals('D', perm1.invert('D'));

    }
    public void Test2(){
        Permutation perm2 = getNewPermutation("(ABC) (DEF)", getNewAlphabet("ABCDEFGH"));
        assertEquals('C', perm2.permute('A'));
        assertEquals('D', perm2.permute('F'));
        assertEquals(8,perm2.size());
        assertEquals('G', perm2.permute('G'));
        assertEquals('B',perm2.invert('C'));
        assertEquals('E', perm2.invert('F'));
        assertEquals('H', perm2.invert('H'));
    }

    @Test
    public void Test3(){
        Permutation perm3 = getNewPermutation("()", getNewAlphabet("ABCDEFGHI"));
        assertEquals('A',perm3.invert('A'));
        assertEquals('C', perm3.invert('C'));
        assertEquals('G',perm3.permute('G'));
        assertEquals(9, perm3.size());
    }

    public void Test(){
        Permutation perm4 = getNewPermutation("", getNewAlphabet("ABC"));
        assertEquals("A", perm4.permute('A'));
        assertEquals('B', perm4.invert('B'));
        assertEquals(3,perm4.size());
    }

    @Test
    public void TestInvertInt(){

    }

    @Test
    public void TestPermuteChar(){

    }

    @Test
    public void TestInvertChar(){

    }

    @Test
    public void  TestDerangement(){

    }

    // FIXME: Add tests here that pass on a correct Permutation and fail on buggy Permutations.
}
