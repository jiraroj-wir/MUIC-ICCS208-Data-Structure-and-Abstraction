import java.math.BigInteger;

public class Fib {
    public static void main(String[] args) {
        assert firstNDigit(1) == 1;
        assert firstNDigit(2) == 7;
        assert firstNDigit(3) == 12;
        assert firstNDigit(4) == 17;
        assert firstNDigit(5) == 21;
    }
    public static int firstNDigit(int n) {
        int pos = -1;
        BigInteger[] fib = new BigInteger[40001];
        fib[1] = fib[2] = BigInteger.ONE;

        if (n == 1) {
            return 1;
        }

        for (int k = 3; k <= 40000; k++) {
            fib[k] = fib[k - 1].add(fib[k - 2]);

            if (big_int_length(fib[k]) >= n) {
                pos = k;
                break;
            }
        }

        return pos;
    }
    /*
     * returns amount of digits in BigInteger n
     */
    public static int big_int_length(BigInteger n) { return n.toString().length(); }
}
