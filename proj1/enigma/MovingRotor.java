package enigma;

import static enigma.EnigmaException.*;

/** Class that represents a rotating rotor in the enigma machine.
 *  @author Amy Mathews
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
    }

    @Override
    boolean rotates() {
        return true;
    }

    @Override
    void advance() {
        int advance = permutation().wrap(setting() + 1);
        super.set(advance);

    }

    @Override
    String notches() {
        return _notch;
    }

    /** keeps track of notch.**/
    private String _notch;

}
