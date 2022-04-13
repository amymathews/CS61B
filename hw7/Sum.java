/** HW #7, Two-sum problem.
 * @author
 */
import java.util.Arrays;
public class Sum {

    /** Returns true iff A[i]+B[j] = M for some i and j. */
    public static boolean sumsTo(int[] A, int[] B, int m) {

        Arrays.sort(A);
        Arrays.sort(B);
        int lhs = 0, rhs = B.length - 1;
        while ((lhs >= 0) && lhs < A.length && (0 <= rhs) ) {
            int sum = A[lhs] + B[rhs];
            if (sum == m) {
                return true;
            }
            else if (sum < m){
                lhs++;
            }
            else {
                rhs--;
            }
        }
        return false;  // REPLACE WITH YOUR ANSWER
    }
}
