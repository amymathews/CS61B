import java.util.Arrays;

/**
 * Note that every sorting algorithm takes in an argument k. The sorting 
 * algorithm should sort the array from index 0 to k. This argument could
 * be useful for some of your sorts.
 *
 * Class containing all the sorting algorithms from 61B to date.
 *
 * You may add any number instance variables and instance methods
 * to your Sorting Algorithm classes.
 *
 * You may also override the empty no-argument constructor, but please
 * only use the no-argument constructor for each of the Sorting
 * Algorithms, as that is what will be used for testing.
 *
 * Feel free to use any resources out there to write each sort,
 * including existing implementations on the web or from DSIJ.
 *
 * All implementations except Counting Sort adopted from Algorithms,
 * a textbook by Kevin Wayne and Bob Sedgewick. Their code does not
 * obey our style conventions.
 */
public class MySortingAlgorithms {

    /**
     * Java's Sorting Algorithm. Java uses Quicksort for ints.
     */
    public static class JavaSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            Arrays.sort(array, 0, k);
        }

        @Override
        public String toString() {
            return "Built-In Sort (uses quicksort for ints)";
        }
    }

    /** Insertion sorts the provided data. */
    public static class InsertionSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            // FIXME
            for (int i = 1; i < k; i += 1) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;
            }
            array[j + 1] = key;
        }

        }

        @Override
        public String toString() {
            return "Insertion Sort";
        }
    }

    /**
     * Selection Sort for small K should be more efficient
     * than for larger K. You do not need to use a heap,
     * though if you want an extra challenge, feel free to
     * implement a heap based selection sort (i.e. heapsort).
     */
    public static class SelectionSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            // FIXME
            for(int i = 0; i < k - 1; i += 1) {
                int min = i;
                for(int j = i + 1; j < k; j += 1) {
                    if (array[j] < array[min]) {
                        min = j;
                    }
                }
                    int temp = array[min];
                    array[min] = array [i];
                    array[i] = temp;
            }
        }

        @Override
        public String toString() {
            return "Selection Sort";
        }
    }

    /** Your mergesort implementation. An iterative merge
      * method is easier to write than a recursive merge method.
      * Note: I'm only talking about the merge operation here,
      * not the entire algorithm, which is easier to do recursively.
      */
    public static class MergeSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            // FIXME
            if(k == 0 || k == 1){
                return;
            }
            int middle = k/2;
            int[] left_array = new int[middle];
            int[] right_array = new int[k-middle];
            //put the array values into the left array
            for(int i = 0; i < middle; i += 1) {
                left_array[i] = array[i];

            }
            //put the array values into the right array
            for(int j = middle; j < k; j += 1 ){
                right_array[j - middle] = array[j];

            }
            sort(left_array, middle);
            sort(right_array, k-middle);
            mergesort(array,left_array,right_array,middle, k-middle );
        }

        private void mergesort(int arr[], int left[], int right[], int left_end, int right_end) {
            int i = 0, j = 0, k = 0;
            while (i < left_end && j < right_end) {
                if (left[i] <= right[j]) {
                    arr[k++] = left[i++];
                }
                else {
                    arr[k++] = right[j++];
                }
            }
            while (i < left_end) {
                arr[k++] = left[i++];
            }
            while (j < right_end) {
                arr[k++] = right[j++];
            }


        }

        // may want to add additional methods

        @Override
        public String toString() {
            return "Merge Sort";
        }
    }

    /**
     * Your Counting Sort implementation.
     * You should create a count array that is the
     * same size as the value of the max digit in the array.
     */
    public static class CountingSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            // FIXME: to be implemented
        }

        // may want to add additional methods

        @Override
        public String toString() {
            return "Counting Sort";
        }
    }

    /** Your Heapsort implementation.
     */
    public static class HeapSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            // FIXME
        }

        @Override
        public String toString() {
            return "Heap Sort";
        }
    }

    /** Your Quicksort implementation.
     */
    public static class QuickSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            // FIXME
        }

        @Override
        public String toString() {
            return "Quicksort";
        }
    }

    /* For radix sorts, treat the integers as strings of x-bit numbers.  For
     * example, if you take x to be 2, then the least significant digit of
     * 25 (= 11001 in binary) would be 1 (01), the next least would be 2 (10)
     * and the third least would be 1.  The rest would be 0.  You can even take
     * x to be 1 and sort one bit at a time.  It might be interesting to see
     * how the times compare for various values of x. */

    /**
     * LSD Sort implementation.
     */
    public static class LSDSort implements SortingAlgorithm {
        @Override
        public void sort(int[] a, int k) {

            int[] new_array = new int[k];
            for(int i=0; i < k; k+=1){
                new_array[i] = a[i];
            }
            int maximumNumber = Max(new_array);
            int numberOfDigits = calculateNumberOfDigitsIn(maximumNumber);
            int placeValue = 1;
            while (numberOfDigits-- > 0) {
                applyCountingSortOn(a, placeValue);
                placeValue *= 10;
            }
            // FIXME
        }
        public int Max(int[] a) {
            int max = 0;
            for(int i=0; i<a.length; i++) {
                if(a[i]>max) {
                    max = a[i];
                }
            }
            return max;
        }
        public int calculateNumberOfDigitsIn(int n) {
            int count = 0;
            while (n != 0) {
                n = n / 10;
                ++count;
            }
            return count;

        }
        void applyCountingSortOn(int[] numbers, int placeValue) {
            int range = 10; // decimal system, numbers from 0-9

            int length = numbers.length;
            int[] frequency = new int[10];
            int[] sortedValues = new int[length];
            int[] result = new int[length];

            // calculate the frequency of digits
            for (int i = 0; i < length; i++) {
                int digit = (numbers[i] / placeValue) % range;
                frequency[digit]++;
            }

            for (int i = 1; i < range; i++) {
                frequency[i] += frequency[i - 1];
            }

            for (int i = length - 1; i >= 0; i--) {
                int digit = (numbers[i] / placeValue) % range;
                sortedValues[frequency[digit] - 1] = numbers[i];
                frequency[digit]--;
            }

            System.arraycopy(result, 0, numbers, 0, length);
        }

        @Override
        public String toString() {
            return "LSD Sort";
        }
    }

    /**
     * MSD Sort implementation.
     */
    public static class MSDSort implements SortingAlgorithm {
        @Override
        public void sort(int[] a, int k) {
            // FIXME
        }

        @Override
        public String toString() {
            return "MSD Sort";
        }
    }

    /** Exchange A[I] and A[J]. */
    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
