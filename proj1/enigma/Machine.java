package enigma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

import static enigma.EnigmaException.*;

/** Class that represents a complete enigma machine.
 *  @author
 */
class Machine {

    /** A new Enigma machine with alphabet ALPHA, 1 < NUMROTORS rotor slots,
     *  and 0 < PAWLS < NUMROTORS pawls.  ALLROTORS contains all the
     *  available rotors. */
    Machine(Alphabet alpha, int numRotors, int pawls,
            Collection<Rotor> allRotors) {
        _alphabet = alpha;
        _numRotors = numRotors;
        _pawls = pawls;
        _allRotors = new ArrayList<>(allRotors);
        _currRotors = _allRotors.toArray(new Rotor[0]);
        // FIXME
    }

    /** Return the number of rotor slots I have. */
    int numRotors() {

        return _numRotors; // FIXME
    }

    /** Return the number pawls (and thus rotating rotors) I have. */
    int numPawls() {

        return _pawls; // FIXME
    }

    /** Return Rotor #K, where Rotor #0 is the reflector, and Rotor
     *  #(numRotors()-1) is the fast Rotor.  Modifying this Rotor has
     *  undefined results. */
    Rotor getRotor(int k) {
        return _currRotors[k]; // FIXME
    }

    Alphabet alphabet() {
        return _alphabet;
    }

    /** Set my rotor slots to the rotors named ROTORS from my set of
     *  available rotors (ROTORS[0] names the reflector).
     *  Initially, all rotors are set at their 0 setting.
     *  How can we convert these string of names to the actual rotor object?
     *  We are updating _currRotors here
     *  We need to somehow call the name method from rotor here.*/
    void insertRotors(String[] rotors) {
        // FIXME
        int m = 0;
        for(String i: rotors){
            for(Rotor j: _allRotors){
                m += 1;
                if(i == j.name()){
                    _currRotors[m] = j;
                 }
             }
        }
    }


    /** Set my rotors according to SETTING, which must be a string of
     *  numRotors()-1 characters in my alphabet. The first letter refers
     *  to the leftmost rotor setting (not counting the reflector).  */
    void setRotors(String setting) {
        // FIXME
        if(setting.length() == numRotors() - 1){
            for(int i = 0; i < _currRotors.length; i +=1 ){
                if(!_alphabet.contains(setting.charAt(i))){
                    throw new EnigmaException("no such setting");
                }
                _currRotors[i].set(setting.charAt(i));
            }
        }
        else{
            throw new EnigmaException("setting length does not match conditions");
        }
    }

    /** Return the current plugboard's permutation. */
    Permutation plugboard() {
        return _plugboard;// FIXME
    }

    /** Set the plugboard to PLUGBOARD. */
    void setPlugboard(Permutation plugboard) {
        _plugboard = plugboard;// FIXME
    }

    /** Returns the result of converting the input character C (as an
     *  index in the range 0..alphabet size - 1), after first advancing
     *  the machine. */
    int convert(int c) {
        advanceRotors();
        if (Main.verbose()) {
            System.err.printf("[");
            for (int r = 1; r < numRotors(); r += 1) {
                System.err.printf("%c",
                        alphabet().toChar(getRotor(r).setting()));
            }
            System.err.printf("] %c -> ", alphabet().toChar(c));
        }
        c = plugboard().permute(c);
        if (Main.verbose()) {
            System.err.printf("%c -> ", alphabet().toChar(c));
        }
        c = applyRotors(c);
        c = plugboard().permute(c);
        if (Main.verbose()) {
            System.err.printf("%c%n", alphabet().toChar(c));
        }
        return c;
    }

    /** Advance all rotors to their next position.
     * here we must check for double stepping*/
    private void advanceRotors() {
        //int i = numRotors()-numPawls();
        for(int i = 1; i<numRotors(); i+=1) {
            /** I am at a notch, no only do I rotate but I rotate my neighbor on the right**/

            if(_currRotors[i].atNotch()){
                _currRotors[i].advance();
                _currRotors[i+1].advance();
            }
            /** if the right rotor is at a notch it should advance **/
            if(_currRotors[0].atNotch()){
                _currRotors[1].advance();
            }
            /** the right rotor always advances **/
            _currRotors[0].advance();
        }

        // FIXME
    }

    /** Return the result of applying the rotors to the character C (as an
     *  index in the range 0..alphabet size - 1). */
    private int applyRotors(int c) {
        return c; // FIXME
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        return ""; // FIXME
    }

    /** Common alphabet of my rotors. */
    private final Alphabet _alphabet;

    // FIXME: ADDITIONAL FIELDS HERE, IF NEEDED.
    /** number of all rotors possible **/
    private int _numRotors;
    /** number of all pawls possible **/
    private int _pawls;
    /** all possible rotors **/
    private ArrayList<Rotor> _allRotors;
    /** current rotors selected by the machine **/
    private Rotor[] _currRotors;
    /** Permutation object to store plugboard **/
    private Permutation _plugboard;
}
