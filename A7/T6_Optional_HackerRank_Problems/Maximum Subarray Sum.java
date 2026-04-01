import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

class Result {

    /*
     * Complete the 'maximumSum' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. LONG_INTEGER_ARRAY a
     *  2. LONG_INTEGER m
     */

    public static long maximumSum(List<Long> a, long m) {
        // Write your code here
        TreeSet<Long> set = new TreeSet<>();
        long prefix = 0;
        long max = 0;

        for (Long i : a) {
            prefix = (prefix + i) % m;

            // in case the max sub-array starts from index 0 to i
            max = Math.max(prefix, max);

            // sub-array j to i, where j < i, a[j] > a[i], the difference is minimize, and j in the set
            Long smallestLarger = set.higher(prefix);

            if (smallestLarger != null) {
                max = Math.max((prefix - smallestLarger + m) % m, max);
            }

            set.add(prefix);

            if (max == m - 1) {
                return max;
            }
        }

        return max;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                long m = Long.parseLong(firstMultipleInput[1]);

                List<Long> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                   .map(Long::parseLong)
                                   .collect(toList());

                long result = Result.maximumSum(a, m);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
