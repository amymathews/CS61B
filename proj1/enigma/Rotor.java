package enigma;

/** Superclass that represents a rotor in the enigma machine.
 *  @author Amy Mathews
 */
class Rotor {

    /** A rotor named NAME whose permutation is given by PERM. */
    Rotor(String name, Permutation perm) {
        _name = name;
        _permutation = perm;
        _setting = 0;

    }

    /** Return my name. */
    String getName() {
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
        return _setting;
    }


    /** Set setting() to POSN.  */
    void set(int posn) {
        _setting = posn;

    }

    /** Set setting() to character CPOSN. */
    void set(char cposn) {
        _setting = _permutation.alphabet().toInt(cposn);

    }


    /** Return the conversion of P (an integer in the range 0..size()-1)
     *  according to my permutation. */
    int convertForward(int p) {

        int conF = _permutation.permute(_setting + p);
        int result;
        if (conF < _setting) {
            result = conF - _setting + size();
        } else {
            result = conF - _setting;
        }
        if (Main.verbose()) {
            System.err.printf("%c -> ", alphabet().toChar(p));
        }
        return result;

    }

    /** Return the conversion of E (an integer in the range 0..size()-1)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {

        int conB = _permutation.invert(e + setting());
        int result;
        if (conB < _setting) {
            result = conB - _setting + size();
        } else {
            result = conB - _setting;
        }
        if (Main.verbose()) {
            System.err.printf("%c -> ", alphabet().toChar(e));
        }
        return result;
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

    }

    /** Advance me one position, if possible. By default, does nothing. */
    void advance() { }

    @Override
    public String toString() {
        return "Rotor " + _name;
    }

    /** My name. */
    private final String _name;

    /** The permutation implemented by this rotor in its 0 position. */
    private Permutation _permutation;

    /** setting for the permutation. **/
    private int _setting;

}
