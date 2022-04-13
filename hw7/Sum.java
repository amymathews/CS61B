import java.util.HashMap;

/** HW #7, Two-sum problem.
 * @author
 */
public class Sum {

    /** Returns true iff A[i]+B[j] = M for some i and j. */
    public static boolean sumsTo(int[] A, int[] B, int m) {
        HashMap<Integer, Integer> reference  = new HashMap<>();
        for(int i = 0; i < A.length; i += 1) {
            reference.put(A[i], 0);

            for(int j = 0; j < B.length; j += 1) {
                    if(reference.containsKey(m-B[j])){
                        return true;
                    }
            }
        }
        return false;  // REPLACE WITH YOUR ANSWER
    }

}
