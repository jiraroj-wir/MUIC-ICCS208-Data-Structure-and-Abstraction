public class Last {

    public static Integer binarySearchLast(int[] a, int k) { return (Integer)0; }

    // test
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
