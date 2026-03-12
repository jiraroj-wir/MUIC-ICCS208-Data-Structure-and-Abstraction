import java.util.*;

public class Rank {
    public static int rank(int[] A, int[] B, int e) { return binarySearch_rank(A, e) + binarySearch_rank(B, e); }

    /*
     * NOTE: Keep in mind that when n,m > 0,log n ≤ log(n+m) and log m ≤ log(n+m). Hence, we know that log n + log m is
     * still O(log(n + m)).
     */

    private static int binarySearch_rank(int[] arr, int e) { // O(log n)
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

    public static Integer select(int[] A, int[] B, int k) {
        return Math.max(binarySearch_select(A, k), binarySearch_select(B, k)) + 1;
    }

    private static Integer binarySearch_select(int[] arr, int k) { return null; }

    // tests
    public static void main(String[] args) {
        // rank
        int[] Ar1 = {10, 21, 32, 53, 54};
        int[] Br1 = {};
        assert rank(Ar1, Br1, 9) == 0;

        int[] Ar2 = {10, 21, 32, 53, 54};
        int[] Br2 = {};
        assert rank(Ar2, Br2, 10) == 0;

        int[] Ar3 = {10, 21, 32};
        int[] Br3 = {5, 40};
        assert rank(Ar3, Br3, 33) == 4;

        int[] Ar4 = {1, 3, 5};
        int[] Br4 = {2, 4, 6};
        assert rank(Ar4, Br4, 4) == 3;

        int[] Ar5 = {1, 2, 3};
        int[] Br5 = {4, 5, 6};
        assert rank(Ar5, Br5, 7) == 6;

        int[] Ar6 = {2, 4, 6, 8};
        int[] Br6 = {1, 3, 5, 7};
        assert rank(Ar6, Br6, 5) == 4;

        int[] Ar7 = {10, 20, 30};
        int[] Br7 = {15, 25, 35};
        assert rank(Ar7, Br7, 26) == 4;

        int[] Ar8 = {};
        int[] Br8 = {1, 2, 3, 4};
        assert rank(Ar8, Br8, 3) == 2;

        int[] Ar9 = {5, 15, 25};
        int[] Br9 = {10, 20, 30};
        assert rank(Ar9, Br9, 1) == 0;

        int[] Ar10 = {1, 4, 7};
        int[] Br10 = {2, 3, 5, 6, 8};
        assert rank(Ar10, Br10, 6) == 5;

        // select
        int[] As1 = {10, 21, 32};
        int[] Bs1 = {5, 40};
        assert select(As1, Bs1, 0) == 5;

        int[] As2 = {10, 21, 32};
        int[] Bs2 = {5, 40};
        assert select(As2, Bs2, 1) == 10;

        int[] As3 = {10, 21, 32};
        int[] Bs3 = {5, 40};
        assert select(As3, Bs3, 2) == 21;

        int[] As4 = {10, 21, 32};
        int[] Bs4 = {5, 40};
        assert select(As4, Bs4, 3) == 32;

        int[] As5 = {10, 21, 32};
        int[] Bs5 = {5, 40};
        assert select(As5, Bs5, 4) == 40;

        int[] As6 = {1, 3, 5};
        int[] Bs6 = {2, 4, 6};
        assert select(As6, Bs6, 3) == 4;

        int[] As7 = {1, 2, 3};
        int[] Bs7 = {4, 5, 6};
        assert select(As7, Bs7, 5) == 6;

        int[] As8 = {};
        int[] Bs8 = {1, 2, 3, 4};
        assert select(As8, Bs8, 2) == 3;

        int[] As9 = {5, 15, 25};
        int[] Bs9 = {10, 20, 30};
        assert select(As9, Bs9, 0) == 5;

        int[] As10 = {1, 4, 7};
        int[] Bs10 = {2, 3, 5, 6, 8};
        assert select(As10, Bs10, 5) == 6;
    }
}
