package enigma;

import static enigma.EnigmaException.*;

/** Superclass that represents a rotor in the enigma machine.
 *  @author
 */
class Rotor {

    /** A rotor named NAME whose permutation is given by PERM. */
    Rotor(String name, Permutation perm) {
        _name = name;
        _permutation = perm;
        _setting = 0;
        // FIXME
    }

    /** Return my name. */
    String name() {
        return _name;
    }

    /** Return my alphabet. */
    Alphabet alphabet() {
        return _permutation.alphabet();
    }

    /** Return my permutation. */
    Permutation permutation() {
        return _permutation;
    }

    /** Return the size of my alphabet. */
    int size() {
        return _permutation.size();
    }

    /** Return true iff I have a ratchet and can move. */
    boolean rotates() {
        return false;
    }

    /** Return true iff I reflect. */
    boolean reflecting() {
        return false;
    }

    /** Return my current setting. */
    int setting() {
        return _setting; // FIXME
    }


    /** Set setting() to POSN.  */
    void set(int posn) {
        _setting = posn;
        // FIXME
    }

    /** Set setting() to character CPOSN. */
    void set(char cposn) {
        _setting = _permutation.alphabet().toInt(cposn);
        // FIXME
    }

    /** Return the conversion of P (an integer in the range 0..size()-1)
     *  according to my permutation. */
    int convertForward(int p) {
        // FIXME
        int conF = _permutation.permute(_permutation.wrap(_setting + p));
        return _permutation.wrap(conF -_setting);
    }

    /** Return the conversion of E (an integer in the range 0..size()-1)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        // FIXME
        int conB = _permutation.invert(_permutation.wrap(_setting + e));
        return _permutation.wrap(conB - _setting);
    }

    /** Returns the positions of the notches, as a string giving the letters
     *  on the ring at which they occur. */
    String notches() {
        return "";
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance.
     *  if current setting of rotor is one of the notches
     *  alphabet with setting to get the index
     *  input setting into alphabhet*/
    boolean atNotch() {

       return notches().indexOf(alphabet().toChar(this.setting())) >= 0;
                // FIXME
    }

    /** Advance me one position, if possible. By default, does nothing. */
    void advance() {
    }

    @Override
    public String toString() {
        return "Rotor " + _name;
    }

    /** My name. */
    private final String _name;

    /** The permutation implemented by this rotor in its 0 position. */
    private Permutation _permutation;

    // FIXME: ADDITIONAL FIELDS HERE, AS NEEDED
    private int _setting;

}
