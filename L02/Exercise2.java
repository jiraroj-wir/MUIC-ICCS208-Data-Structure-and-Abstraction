import java.math.BigInteger;

public class Exercise2 {
    public static void main(String[] args) {

        /* basic testing
         *
        System.out.printf("" + numTrailingZeros(5) + "\n");
        System.out.printf("" + numTrailingZeros(10) + "\n");
        System.out.printf("" + numTrailingZeros(20) + "\n");
        System.out.printf("" + numTrailingZeros(100) + "\n");
        System.out.printf("" + numTrailingZeros(1000) + "\n");

        int[] data_1 = new int[] {1, 2, -3, 4, 5, 4};
        windowPosSum(data_1, 3);
        System.out.println(Arrays.toString(data_1));
        // {4, 8, -3, 13, 9, 4}

        int[] data_2 = new int[] {1, -1, -1, 10, 5, -1};
        windowPosSum(data_2, 2);
        System.out.println(Arrays.toString(data_2));
        // {-1, -1, -1, 14, 4, -1}
        */
    }

    public static int numTrailingZeros(int n) {
        BigInteger fractorial = BigInteger.ONE;
        for (int i = n; i > 0; i--) {
            fractorial = fractorial.multiply(BigInteger.valueOf(i));
        }

        int trailing_zeros = 0;
        String stringFractorial = fractorial.toString();
        int stringFractorialLength = stringFractorial.length();
        int index = stringFractorialLength - 1;
        while (true) {
            if (stringFractorial.charAt(index) != '0') {
                break;
            }
            trailing_zeros++;
            index--;
        }

        return trailing_zeros;
    }

    public static void windowPosSum(int[] a, int n) {
        /*
         * do sliding window, so I just precompute the 'window', and ... slide through it.
         * The function's time complexity would be O(n) --- whcih you could probably further optimize the second and
         * third if-statement part.
         */
        int window = 0;
        for (int i = 1; i <= n && i < a.length; i++) {
            window += a[i];
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                a[i] += window;
            }

            if (i + n + 1 < a.length) {
                window += a[i + n + 1];
            }
            if (i + 1 < a.length) {
                window -= a[i + 1];
            }
        }
    }
}
