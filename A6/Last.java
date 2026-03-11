public class Last {
    /*
     * we just continue the binary search, even if we found entry with `k` value, until the range is a single entry
     */
    public static Integer binarySearchLast(int[] a, int k) {
        int left = 0;
        int right = a.length - 1;
        Integer last = null;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (a[mid] == k) {
                last = mid;
                left = mid + 1;
            } else if (a[mid] > k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return last;
    }

    // tests
    public static void main(String[] args) {
        assert binarySearchLast(new int[] {1, 2, 2, 2, 4, 5}, 2) == 3;
        assert binarySearchLast(new int[] {1, 2, 2, 2, 4, 5}, 0) == null;
        assert binarySearchLast(new int[] {1, 2, 2, 2, 4, 5}, 5) == 5;
        assert binarySearchLast(new int[] {1, 1, 1, 1}, 1) == 3;
        assert binarySearchLast(new int[] {1, 2, 3, 4, 5}, 3) == 2;
        assert binarySearchLast(new int[] {1, 2, 3, 4, 5}, 6) == null;
        assert binarySearchLast(new int[] {2, 2, 2, 2, 2}, 2) == 4;
        assert binarySearchLast(new int[] {1}, 1) == 0;
        assert binarySearchLast(new int[] {1}, 2) == null;
    }
}
