package enigma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
        String[] rows;
        String data;
        _alphabet = alphabet;
        permhash = new HashMap<>();
        rpermhash = new HashMap<>();
        data = cycles;
        data = data.replaceAll("[)(]", "");
        rows = data.split(" ");

        for(String row: rows) {
            addCycle(row);
        }

//        data = cycles;
//        data = data.replace("(","");
//        data = data.replace(")","");
//        String[] rows = data.split(" ");
//        String[][] map = new String[rows.length][];
//        int i = 0;
//        for(String row: rows) {
//            map[i++] =  row.split(",");
//        }
//        System.out.println(Arrays.deepToString(map));


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
     *  c0c1...cm.
     *  _alphabet of this permutation
     *  alphabet used to initialize this permutation.*/
    private void addCycle(String cycle) {
        // FIXME

        for(int i =0; i < cycle.length(); i +=1)
            if(cycle.length() < 1){
                permhash.put(cycle.charAt(i),cycle.charAt(i));
                rpermhash.put(cycle.charAt(i),cycle.charAt(i));
            }
            else if(i != cycle.length() - 1){
                permhash.put(cycle.charAt(i),cycle.charAt(i+1));
                rpermhash.put(cycle.charAt(i+1),cycle.charAt(i));
            }
            else {
                permhash.put(cycle.charAt(i),cycle.charAt(0));
                rpermhash.put(cycle.charAt(0),cycle.charAt(i));
            }
            for(int i = 0; i < alphabet().size(); i +=1){
                char c = _alphabet.toChar(i);
                if(!permhash.containsKey(c)) {
                    permhash.put(_alphabet.toChar(i), _alphabet.toChar(i));
                }
                if(!rpermhash.containsKey(c)){
                    rpermhash.put(_alphabet.toChar(i),_alphabet.toChar(i));
                }

            }




//        int cylen = cycle.length();
//        for(int i =0; i<cylen; i+=1) {
//            char flag = cycle.charAt(i);
//            if(cylen<1){
//                permlist.set(_alphabet.toInt(flag),_alphabet.toInt(cycle.charAt(flag)));
//            }
//            if(i != cylen-1){
//                permlist.set(_alphabet.toInt(flag),alphabet().toInt(cycle.charAt(i+1)));
//            }
//            else{
//                permlist.set(_alphabet.toInt(flag),alphabet().toInt(cycle.charAt(0)));
//            }
//        }
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
     *  alphabet size.
     *  the get function ->  return the element at a given index from the specified Array */
    int permute(int p) {
        char index = _alphabet.toChar(wrap(p));
        return _alphabet.toInt(permhash.get(index)); // FIXME
//        return permhash.get(wrap(p));
    }

    /** Return the result of applying the inverse of this permutation
     *  to  C modulo the alphabet size. */
    int invert(int c) {
           // FIXME
        char index = _alphabet.toChar(wrap(c));
        return _alphabet.toInt(rpermhash.get(index));
//        return rpermhash.get(wrap(c));
    }

    /** Return the result of applying this permutation to the index of P
     *  in ALPHABET, and converting the result to a character of ALPHABET. */
    char permute(char p) {
//        int index = _alphabet.toInt(p);
//        return _alphabet.toChar(permute(index));// FIXME
        return permhash.get(p);

    }

    /** Return the result of applying the inverse of this permutation to C. */
    char invert(char c) {
        return rpermhash.get(c);
//        int index = _alphabet.toInt(c);
//        return _alphabet.toChar(invert(index));// FIXME
    }

    /** Return the alphabet used to initialize this Permutation. */
    Alphabet alphabet() {
        return _alphabet;
    }

    /** Return true iff this permutation is a derangement (i.e., a
     *  permutation for which no value maps to itself). */
    boolean derangement() {
        {
            for(char i: permhash.keySet()){
                for(char j: permhash.values()){
                    if(i == j){
                        return false;
                    }
                }
            }
        }
        return true;  // FIXME
    }

    /** Alphabet of this permutation. */
    private Alphabet _alphabet;
    private String data;
    private HashMap<Character, Character> permhash;
    private HashMap<Character, Character> rpermhash;


    // FIXME: ADDITIONAL FIELDS HERE, AS NEEDED
}
