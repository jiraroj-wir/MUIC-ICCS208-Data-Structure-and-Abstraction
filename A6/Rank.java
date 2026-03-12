import java.util.*;

public class Rank {
    public static int rank(int[] A, int[] B, int e) { return binarySearch(A, e) + binarySearch(B, e); }

    /*
     * NOTE: Keep in mind that when n,m > 0,log n ≤ log(n+m) and log m ≤ log(n+m). Hence, we know that log n + log m is
     * still O(log(n + m)).
     */

    private static int binarySearch(int[] arr, int e) { // O(log n)
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (arr[mid] < e) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static Integer select(int[] A, int[] B, int k) {}

    // tests
    public static void main(String[] args) {
        int[] A1 = {10, 21, 32, 53, 54};
        int[] B1 = {};
        assert rank(A1, B1, 9) == 0;

        int[] A2 = {10, 21, 32, 53, 54};
        int[] B2 = {};
        assert rank(A2, B2, 10) == 0;

        int[] A3 = {10, 21, 32};
        int[] B3 = {5, 40};
        assert rank(A3, B3, 33) == 4;

        int[] A4 = {1, 3, 5};
        int[] B4 = {2, 4, 6};
        assert rank(A4, B4, 4) == 3;

        int[] A5 = {1, 2, 3};
        int[] B5 = {4, 5, 6};
        assert rank(A5, B5, 7) == 6;

        int[] A6 = {2, 4, 6, 8};
        int[] B6 = {1, 3, 5, 7};
        assert rank(A6, B6, 5) == 4;

        int[] A7 = {10, 20, 30};
        int[] B7 = {15, 25, 35};
        assert rank(A7, B7, 26) == 4;

        int[] A8 = {};
        int[] B8 = {1, 2, 3, 4};
        assert rank(A8, B8, 3) == 2;

        int[] A9 = {5, 15, 25};
        int[] B9 = {10, 20, 30};
        assert rank(A9, B9, 1) == 0;

        int[] A10 = {1, 4, 7};
        int[] B10 = {2, 3, 5, 6, 8};
        assert rank(A10, B10, 6) == 5;
    }
}
