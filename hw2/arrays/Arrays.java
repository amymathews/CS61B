package arrays;

/* NOTE: The file Arrays/Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2 */

/** Array utilities.
 *  @author
 */
class Arrays {

    /* C1. */
    /** Returns a new array consisting of the elements of A followed by the
     *  the elements of B. */
    static int[] catenate(int[] A, int[] B) {
        int APos = A.length;
        int Alen = A.length;
        int Blen = B.length;
        int[] destarr =  new int[A.length+ B.length];

        System.arraycopy(A,0,destarr,0,Alen);
        System.arraycopy(B,0,destarr,APos,Blen);

        return destarr;
    }

    /* C2. */
    /** Returns the array formed by removing LEN items from A,
     *  beginning with item #START. If the start + len is out of bounds for our array, you
     *  can return null.
     *  Example: if A is [0, 1, 2, 3] and start is 1 and len is 2, the
     *  result should be [0, 3]. */
    static int[] remove(int[] A, int start, int len) {
        int[] retarr = new int[A.length-len]; // new array to be returned with subtracted length.
        int desPos = start + len;
        int srcPos = start + len;
        int length = A.length - desPos;
        if(start+len  > A.length)
            return null;
        System.arraycopy(A,0,retarr,0,start);
        System.arraycopy(A,srcPos,retarr,start,length);
        return retarr;
    }

}
