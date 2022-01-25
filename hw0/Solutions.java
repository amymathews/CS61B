/** Solutions to the HW0 Java101 exercises.
 *  @author Allyson Park and Amy Mathews
 */
public class Solutions {

    /** Returns whether or not the input x is even.
     */
    public static boolean isEven(int x) {
        
        return (x%2 == 0);

        /* Extended Answer:
        if(x %2 == 0){
            return true;
        }
        else{
            return false;
        }*/  
    }
    public static int max(int[] a){
        int answer = a[0];
        for(int i = 0 ; i < a.length; i = i+1){
            if (a[i] > answer){
                answer = a[i];
            }
        }
        return answer;
    }
    public static boolean wordBank(String word, String[] bank){
        for (int i = 0; i <bank.length; i = i+1){
           if (bank[i].equals(word)){
               return true;
           }
        }
        return false;
    }
    public static boolean threeSum(int[] a){

        for(int i =0; i <a.length; i += 1){
            for( int j = 0; j< a.length; j += 1){
                for( int k = 0; k < a.length; k += 1){
                    if(a[i] + a[j] + a [k] == 0)
                        return true;
                }
            }  
        }
        return false;
    }

    // TODO: Fill in the method signatures for the other exercises
    // Your methods should be static for this HW. DO NOT worry about what this means.
    // Note that "static" is not necessarily a default, it just happens to be what
    // we want for THIS homework. In the future, do not assume all methods should be
    // static.

}
