/** Multidimensional array
 *  @author Zoe Plaxco
 */

public class MultiArr {

    /**
    {{"hello","you","world"} ,{"how","are","you"}} prints:
    Rows: 2
    Columns: 3

    {{1,3,4},{1},{5,6,7,8},{7,9}} prints:
    Rows: 4
    Columns: 4
    */
    public static void printRowAndCol(int[][] arr) {
        //TODO: Your code here!
    }

    /**
    @param arr: 2d array
    @return maximal value present anywhere in the 2d array
    */
    public static int maxValue(int[][] arr) {
        int answer = arr[0][0];
        for(int i =0; i <arr.length; i += 1){
            for(int j = 0; j <arr[i].length; j += 1){
                if(arr[i][j] > answer){
                    answer = arr[i][j];
                }
            }
        }
        return answer;
    }

    /**Return an array where each element is the sum of the
    corresponding row of the 2d array*/
    public static int[] allRowSums(int[][] arr) {
        int answer[] = new int[arr.length];
        int sum;
        for(int i =0; i <arr.length; i += 1) {
            sum = 0;
            for (int j = 0; j < arr[i].length; j += 1) {
                sum += arr[i][j];
            }
            answer[i] += sum;
        }
        return answer;
    }
}
