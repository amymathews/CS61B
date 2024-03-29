package enigma;
import java.util.HashMap;


/** Represents a permutation of a range of integers starting at 0 corresponding
 *  to the characters of an alphabet.
 *  @author Amy Mathews
 */
class Permutation {

    /** Set this Permutation to that specified by CYCLES, a string in the
     *  form "(cccc) (cc) ..." where the c's are characters in ALPHABET, which
     *  is interpreted as a permutation in cycle notation.  Characters in the
     *  alphabet that are not included in any cycle map to themselves.
     *  Whitespace is ignored.*/
    Permutation(String cycles, Alphabet alphabet) {
        String[] rows;
        String data;
        _alphabet = alphabet;
        permhash = new HashMap<>();
        rpermhash = new HashMap<>();
        data = cycles;
        data = data.replaceAll("[)(]", " ");
        rows = data.split(" ");

        for (String row: rows) {
            addCycle(row);
        }
    }

    /** Add the cycle c0->c1->...->cm->c0 to the permutation, where CYCLE is
     *  c0c1...cm.
     *  _alphabet of this permutation
     *  alphabet used to initialize this permutation.*/
    private void addCycle(String cycle) {

        for (int i = 0; i < cycle.length(); i += 1) {
            if (cycle.length() < 1) {
                permhash.put(cycle.charAt(i), cycle.charAt(i));
                rpermhash.put(cycle.charAt(i), cycle.charAt(i));
            } else if (i != cycle.length() - 1) {
                permhash.put(cycle.charAt(i), cycle.charAt(i + 1));
                rpermhash.put(cycle.charAt(i + 1), cycle.charAt(i));
            } else {
                permhash.put(cycle.charAt(i), cycle.charAt(0));
                rpermhash.put(cycle.charAt(0), cycle.charAt(i));
            }
        }
        for (int i = 0; i < alphabet().size(); i += 1) {
            char c = _alphabet.toChar(i);
            if (!permhash.containsKey(c)) {
                permhash.put(_alphabet.toChar(i), _alphabet.toChar(i));
            }
            if (!rpermhash.containsKey(c)) {
                rpermhash.put(_alphabet.toChar(i), _alphabet.toChar(i));
            }
        }
    }

    /** Return the value of P modulo the size of this permutation. */
    final int wrap(int p) {
        int r = p % size();
        if (r < 0) {
            r += size();
        }
        return r;
    }

    /** Returns the size of the alphabet I permute. */
    int size() {
        return _alphabet.size();
    }

    /** Return the result of applying this permutation to P modulo the
     *  alphabet size.*/
    int permute(int p) {

        char index = _alphabet.toChar(wrap(p));
        return _alphabet.toInt(permhash.get(index));
    }

    /** Return the result of applying the inverse of this permutation
     *  to  C modulo the alphabet size. */
    int invert(int c) {

        char index = _alphabet.toChar(wrap(c));
        return _alphabet.toInt(rpermhash.get(index));

    }

    /** Return the result of applying this permutation to the index of P
     *  in ALPHABET, and converting the result to a character of ALPHABET. */
    char permute(char p) {

        return permhash.get(p);

    }

    /** Return the result of applying the inverse of this permutation to C. */
    char invert(char c) {

        return rpermhash.get(c);

    }

    /** Return the alphabet used to initialize this Permutation. */
    Alphabet alphabet() {
        return _alphabet;
    }

    /** Return true iff this permutation is a derangement (i.e., a
     *  permutation for which no value maps to itself). */
    boolean derangement() {
        {
            for (char i: permhash.keySet()) {
                for (char j: permhash.values()) {
                    if (i == j) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /** Alphabet of this permutation. */
    private Alphabet _alphabet;
    /** Hold forward permutation. **/
    private HashMap<Character, Character> permhash;
    /** Hold backward permutation. **/
    private HashMap<Character, Character> rpermhash;

}
