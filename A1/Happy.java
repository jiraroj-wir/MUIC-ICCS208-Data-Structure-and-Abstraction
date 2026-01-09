import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Happy {
    private static final long DIGIT_SQUARE[] = {0, 1, 4, 9, 16, 25, 36, 49, 64, 81};

    public static void main(String[] args) {
        assert sumOfDigitsSquared(7) == 49;
        assert sumOfDigitsSquared(145) == 42;
        assert sumOfDigitsSquared(199) == 163;
        assert isHappy(100);
        assert !isHappy(111);
        assert !isHappy(1234);
        assert isHappy(989);
        long[] expected = {1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49};
        assert Arrays.equals(firstK(expected.length), expected);
    }

    public static long sumOfDigitsSquared(long n) {
        long sum = 0;

        while (n > 0) {
            int digit = (int)(n % 10);
            sum += DIGIT_SQUARE[digit];

            n /= 10;
        }

        return sum;
    }

    public static boolean isHappy(long n) {
        while (true) {
            n = sumOfDigitsSquared(n);
            if (n == 1) {
                return true;
            }
            if (n == 4) {
                break; // false
            }
        }

        return false;
    }

    // summon a hash-map!!!
    private static final Map<Long, Boolean> map = new HashMap<>();

    // base pair
    static {
        map.put(1L, true);
        map.put(4L, false);
    }

    public static long[] firstK(int k) {
        long[] first_k_array = new long[k];

        int count = 0;
        long num = 1;
        while (true) {
            if (count >= k) {
                break;
            }

            /*
             * do memoization, stores the path that temp passes through, then when we got the flag (true or false),
             * update the whole path with the flag's value; so next time we enounter the repeated value, we can do quick
             * exit --- hash map average look-up time is O(1), so overall O(n) + O(path)? (I'm not sure)
             */
            long temp = num;
            List<Long> path = new ArrayList<>(); // need dynamic array to store path
            while (true) {
                Boolean flag;

                if (!map.containsKey(temp)) {
                    path.add(temp);
                    temp = sumOfDigitsSquared(temp);
                    continue;
                } else {
                    flag = map.get(temp);
                }

                update_path_to_map(path, flag);

                if (flag) {
                    first_k_array[count] = num;
                    count++;
                }

                break;
            }

            num++;
        }

        return first_k_array;
    }

    public static void update_path_to_map(List<Long> path, Boolean flag) {
        for (long val : path) {
            map.put(val, flag);
        }
    }
}
