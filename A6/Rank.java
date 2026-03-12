public class Rank {
    public static int rank(int[] A, int[] B, int e) { return binarySearch(A, e) + binarySearch(B, e); }

    private static int binarySearch(int[] arr, int e) {
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
}
