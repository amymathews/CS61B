package enigma;

import java.util.Arrays;

import static enigma.EnigmaException.*;

/** Represents a permutation of a range of integers starting at 0 corresponding
 *  to the characters of an alphabet.
 *  @author
 */
class Permutation {

    /** Set this Permutation to that specified by CYCLES, a string in the
     *  form "(cccc) (cc) ..." where the c's are characters in ALPHABET, which
     *  is interpreted as a permutation in cycle notation.  Characters in the
     *  alphabet that are not included in any cycle map to themselves.
     *  Whitespace is ignored.
     *  I want to get rid of the brackets and whitespaces first*/
    Permutation(String cycles, Alphabet alphabet) {
        _alphabet = alphabet;
        data = cycles;
        data = data.replace("(","");
        data = data.replace(")","");
        String[] rows = data.split(" ");
        String[][] map = new String[rows.length][];
        int i = 0;
        for(String row: rows) {
            map[i++] =  row.split(",");
        }
        System.out.println(Arrays.deepToString(map));


//        holder = cycles;
//        holder = holder.trim();
//        holder = holder.replace("(","");
//        holder = holder.replace(")","");
//        cycle = holder;
//        char[][] perm = new char[cycle.length()][cycle.length()];
//        for(int i = 0; i < _alphabet.size(); i += 1){
//            for(int j =0; j < cycle.length(); j +=1){
//                perm[i][j] = cycle.charAt(j);
//            }
//        }
//        System.out.println(perm);
        // FIXME
    }

    /** Add the cycle c0->c1->...->cm->c0 to the permutation, where CYCLE is
     *  c0c1...cm. */
    private void addCycle(String cycle) {
//        char[][] perm = new char[cycle.length()][cycle.length()];
//        for(int i = 0; i < _alphabet.size(); i += 1){
//            for(int j =0; j < cycle.length(); j +=1){
//                perm[i][j] = cycle[j];
//                System.out.println(perm);
//            }
//        }
        // FIXME
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
        return _alphabet.size(); // FIXME
    }

    /** Return the result of applying this permutation to P modulo the
     *  alphabet size. */
    int permute(int p) {
        return 0;  // FIXME
    }

    /** Return the result of applying the inverse of this permutation
     *  to  C modulo the alphabet size. */
    int invert(int c) {
        return 0;  // FIXME
    }

    /** Return the result of applying this permutation to the index of P
     *  in ALPHABET, and converting the result to a character of ALPHABET. */
    char permute(char p) {
        return 0;  // FIXME
    }

    /** Return the result of applying the inverse of this permutation to C. */
    char invert(char c) {
        return 0;  // FIXME
    }

    /** Return the alphabet used to initialize this Permutation. */
    Alphabet alphabet() {
        return _alphabet;
    }

    /** Return true iff this permutation is a derangement (i.e., a
     *  permutation for which no value maps to itself). */
    boolean derangement() {
        return true;  // FIXME
    }

    /** Alphabet of this permutation. */
    private Alphabet _alphabet;
    private String data;


    // FIXME: ADDITIONAL FIELDS HERE, AS NEEDED
}
