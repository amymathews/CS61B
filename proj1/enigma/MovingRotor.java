package enigma;

import static enigma.EnigmaException.*;

/** Class that represents a rotating rotor in the enigma machine.
 *  @author
 */
class MovingRotor extends Rotor {

    /** A rotor named NAME whose permutation in its default setting is
     *  PERM, and whose notches are at the positions indicated in NOTCHES.
     *  The Rotor is initally in its 0 setting (first character of its
     *  alphabet).
     */
    MovingRotor(String name, Permutation perm, String notches) {
        super(name, perm);
        _notch = notches;
        // FIXME
    }

    // FIXME?
//    @Override
//    boolean rotates() {
//        return true;
//    }
//    @Override
//    boolean atNotch() {
//        return false;
//    }

    @Override
    /**we should be advancing one at a time then set the position to it, so we can call set defined in aprent class **/
    void advance() {
        int _advance = permutation().wrap(setting()+1);
        super.set(_advance);
        // FIXME
    }

    @Override
    String notches() {
        return "";  // FIXME
    }

    // FIXME: ADDITIONAL FIELDS HERE, AS NEEDED
    private String _notch;

}
