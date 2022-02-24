package enigma;

/** An alphabet of encodable characters.  Provides a mapping from characters
 *  to and from indices into the alphabet.
 *  @author Amy Mathews
 */
class Alphabet {

    /** A new alphabet containing CHARS. The K-th character has index
     *  K (numbering from 0). No character may be duplicated. */
    Alphabet(String chars) {
        holder = new char[chars.length()];
        for(int i = 0; i < chars.length(); i+=1) {
            holder[i] = chars.charAt(i);
        }
    }

    /** A default alphabet of all upper-case characters. */
    Alphabet() {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    /** Returns the size of the alphabet. */
    int size() { return holder.length; }

    /** Returns true if CH is in this alphabet. */
    boolean contains(char ch) {
        for(int i : holder) {
            if(i == ch)
                return true;
        }
        return false;
    }

    /** Returns character number INDEX in the alphabet, where
     *  0 <= INDEX < size(). */
    char toChar(int index) { return holder[index]; }

    /** Returns the index of character CH which must be in
     *  the alphabet. This is the inverse of toChar(). */
    int toInt(char ch) {
       for(int i =0; i < holder.length; i+=1)
           if(holder[i] == ch){
               return i;
       }
       return 0;
    }

/** instance variables. */
public char[] holder;

}
