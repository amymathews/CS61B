package enigma;

import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

import static enigma.EnigmaException.*;

/** Class that represents a complete enigma machine.
 *  @author Amy Mathews
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
        _currRotors = new Rotor[numRotors];
        _allRotors = new HashMap<String, Rotor>();

        Iterator<Rotor> iterator = allRotors.iterator();
        while (iterator.hasNext()) {
            Rotor temp = iterator.next();
            _allRotors.put(temp.getName(), temp);
        }
    }

    /** Return the number of rotor slots I have. */
    int numRotors() {

        return _numRotors;
    }

    /** Return the number pawls (and thus rotating rotors) I have. */
    int numPawls() {

        return _pawls;
    }

    /** Return Rotor #K, where Rotor #0 is the reflector, and Rotor
     *  #(numRotors()-1) is the fast Rotor.  Modifying this Rotor has
     *  undefined results. */
    Rotor getRotor(int k) {

        return _currRotors[k];

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

        int counter = 0;

        for (int i = 0; i < rotors.length; i += 1) {
            _currRotors[i] = _allRotors.get(rotors[i]);
        }
        for (int i = 0; i < numRotors(); i += 1) {
            if (_currRotors[i].rotates()) {
                counter += 1;
            }
        }
        if (counter != numPawls()) {
            throw new EnigmaException("There should be an reflector! ");
        }
        if (_currRotors.length != rotors.length) {
            throw new EnigmaException("Missing some rotors");
        }
    }

    /** Set my rotors according to SETTING, which must be a string of
     *  numRotors()-1 characters in my alphabet. The first letter refers
     *  to the leftmost rotor setting (not counting the reflector).  */
    void setRotors(String setting) {

        if (setting.length() != numRotors() - 1) {
            throw new EnigmaException("setting does not match"
                    + " with number of rotors");
        }
        if (setting.length() == numRotors() - 1) {
            for (int i = 1; i < _currRotors.length; i += 1) {
                _currRotors[i].set(setting.charAt(i - 1));
            }
        } else {
            throw new EnigmaException("setting length does not "
                    + "match conditions");
        }
    }

    /** Return the current plugboard's permutation. */
    Permutation plugboard() {
        return this._plugboard;
    }

    /** Set the plugboard to PLUGBOARD. */
    void setPlugboard(Permutation plugboard) {
        _plugboard = plugboard;
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

        Boolean[] flag = new Boolean[_numRotors];
        int k = numRotors() - 1;
        flag[k] = true;

        if (numRotors() == 0) {
            throw new EnigmaException("No rotors!");
        }
        for (int i = numRotors() - 2; i >= 0; i -= 1) {
            if (_currRotors[i + 1].atNotch() && _currRotors[i].rotates()) {
                if (_currRotors[i + 1].rotates()) {
                    flag[i + 1] = true;
                }
                flag[i] = true;
            } else {
                flag[i] = false;
            }
        }
        int m = numRotors() - numPawls();
        for (int i = m; i < _numRotors;  i += 1) {
            if (!(_currRotors[i] instanceof MovingRotor)) {
                throw error("Incorrect order");
            }
        }
        for (int j = 0; j < numRotors(); j += 1) {
            if (flag[j]) {
                _currRotors[j].advance();
            }
        }

    }

    /** Return the result of applying the rotors to the character C (as an
     *  index in the range 0..alphabet size - 1).
     *  use convert forward and convert backward, iterate through
     *  end of array -> to front got covertforward method
     *  front -> the back convertbackward.*/
    private int applyRotors(int c) {
        int result = c;

        for (int i = _numRotors - 1; i >= 0; i -= 1) {
            result = _currRotors[i].convertForward(result);
        }

        for (int j = 1; j < _numRotors; j += 1) {
            result = _currRotors[j].convertBackward(result);
        }
        return result;
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly.
     **/
    String convert(String msg) {
        String retString = "";
        for (int i = 0; i < msg.length(); i += 1) {
            retString += _alphabet.toChar(
                    convert(_alphabet.toInt(msg.charAt(i))));
        }
        return retString;
    }

    /** Common alphabet of my rotors. */
    private final Alphabet _alphabet;
    /** number of all rotors possible. **/
    private int _numRotors;
    /** number of all pawls possible. **/
    private int _pawls;
    /** all possible rotors. **/
    private HashMap<String, Rotor> _allRotors;
    /** current rotors selected by the machine. **/
    private Rotor[] _currRotors;
    /** Permutation object to store plugboard. **/
    private Permutation _plugboard;
}
