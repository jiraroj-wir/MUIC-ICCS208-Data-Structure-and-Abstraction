import java.util.*;

public class Lecture14 {
    /*
    1. Linear Search

    Linear search is a basic routine for locating an index where a target key appears in a collection (array). It does
    what the name suggests: it goes through the array from one end to the other in linear manner.

    Your task is to write a static method

    public static int linearSearch(String[] array, String targetKey)

    that takes in an array of Strings and a target key string, and returns the smallest index i that is the target key.
    If the target key is not present in the array, return -1.

    For example, linearSearch({"ab", "cc", "bex", "def"}, "cc") should return 1.
    */

    public static int linearSearch(String[] array, String targetKey) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(targetKey)) {
                return i;
            }
        }

        return -1;
    }

    /*
    2. Binary Search

    If the array is the previous exercise is ordered from small to large, you know you can write a much faster program
    using binary search. Write a static method

    public static int binarySearch(String[] array, String targetKey)

    from scratch (i.e., don't use the built-in binarySearch). If comparing two strings takes O(1) , your code should run
    in O(log n) time, where n is the length of the array.
    */

    public static int binarySearch(String[] array, String targetKey) {
        int lower_bound = 0;
        int upper_bound = array.length - 1;

        while (lower_bound <= upper_bound) {
            int index = (lower_bound + upper_bound) >> 1;

            int compare = array[index].compareTo(targetKey);
            if (compare == 0) { // found `targetKey`
                return index;
            } else if (compare > 0) { // array[index] is greater than `targetKey`
                upper_bound = index - 1;
            } else {
                lower_bound = index + 1;
            }
        }

        return -1;
    }

    /*
    3. Running Time Recurrence
    Determine the running time of the following recursive functions (primSum and whazIt):


    int primSum(int[] xs) {
         if (xs.length == 1) return xs[0];
         if (xs.length == 2) return xs[0] + xs[1];
         else {
             int[] ys = Arrays.copyOfRange(xs, 1, xs.length);
             return xs[0]+xs[1]+primSum(ys);
         }
    }

    int whazIt(int[] ys) {
        if (ys.length == 0) return 0;
        if (ys.length == 1) return ys[0];
        int n = ys.length;
        int m = n/2;
        for (int i=0;i<n;i++) {
            int theSum = 0;
            for (int j=0;j<=i;j++) { theSum += ys[j]; }
            ys[i] = theSum;
        }
        int a = whazIt(Arrays.copyOfRange(ys, 0, m));
        int b = whazIt(Arrays.copyOfRange(ys, m, ys.length));
        return a + b;
    }
    */
    // test
    public static void main(String[] args) {
        // 1. Linear Search
        assert linearSearch(new String[] {"ab", "cc", "bex", "def"}, "cc") == 1;
        assert linearSearch(new String[] {"a", "b", "c"}, "a") == 0;
        assert linearSearch(new String[] {"a", "b", "c"}, "c") == 2;
        assert linearSearch(new String[] {"a", "b", "c"}, "x") == -1;
        assert linearSearch(new String[] {"x", "y", "x", "z"}, "x") == 0;

        // 2. Binary Search
        String[] arr1 = {"a", "b", "c", "d", "e"};
        assert binarySearch(arr1, "c") == 2;
        assert binarySearch(arr1, "a") == 0;
        assert binarySearch(arr1, "e") == 4;
        assert binarySearch(arr1, "x") == -1;

        String[] arr2 = {"hello"};
        assert binarySearch(arr2, "hello") == 0;
    }
}
