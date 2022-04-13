/** HW #7, Two-sum problem.
 * @author
 */
public class Sum {

    /** Returns true iff A[i]+B[j] = M for some i and j. */
    public static boolean sumsTo(int[] A, int[] B, int m) {

        //sort A
        for (int i = 1; i < A.length; i += 1) {
            int key = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
        //sort B
        for (int i = 1; i < B.length; i += 1) {
            int key = B[i];
            int j = i - 1;
            while (j >= 0 && B[j] > key) {
                B[j + 1] = B[j];
                j = j - 1;
            }
            B[j + 1] = key;
        }

        for(int i = 0; i < A.length; i += 1) {
            for(int j = 0; j < B.length; j += 1) {
                    if(A[i]+ B[j] == m){
                        return true;
                    }
            }
        }
        return false;  // REPLACE WITH YOUR ANSWER
    }

}
