public class SumEven {
    public static void main(String[] args) {}
    /*
     * The time complexity of this function is O(n), so roughly n steps.
     */
    public static long sumEven(int n) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long)(i << 1); // bit shift i to the right by 1, same as 2 * i
        }

        return sum;
    }
}
