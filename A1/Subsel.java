public class Subsel {
    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4};
        assert java.util.Arrays.equals(Subsel.takeEvery(a1, 2), new int[] {1, 3});

        int[] a2 = {2, 7, 1, 8, 4, 5};
        assert java.util.Arrays.equals(Subsel.takeEvery(a2, 3, 2), new int[] {1, 5});

        int[] a3 = {3, 1, 4, 5, 9, 2, 6, 5};
        assert java.util.Arrays.equals(Subsel.takeEvery(a3, -1, 5), new int[] {2, 9, 5, 4, 1, 3});
    }
    public static int[] takeEvery(int[] a, int stride, int beginWith) {
        int count = 0;
        // simulate the filling, get the actual array size
        for (int i = beginWith; i >= 0 && i < a.length; i += stride) {
            count++;
        }

        int[] result = new int[count];
        int idx = beginWith;
        for (int i = 0; i < count; i++) {
            result[i] = a[idx];
            idx += stride;
        }

        return result;
    }

    public static int[] takeEvery(int[] a, int stride) { return takeEvery(a, stride, 0); }
}
