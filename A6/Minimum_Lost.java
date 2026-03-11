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
     * Complete the 'minimumLoss' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts LONG_INTEGER_ARRAY price as parameter.
     */

    private static class pair {
        long price;
        int index;

        pair(long p, int i) {
            price = p;
            index = i;
        }
    }

    public static int minimumLoss(List<Long> price) {
        // Write your code here
        // List<Long> SortedPrice = new ArrayList<>(price);
        List<pair> sortedPrice = new ArrayList<>();

        for (int i = 0; i < price.size(); i++) { // build pair --- O(n)
            sortedPrice.add(new pair(price.get(i), i));
        }

        // SortedPrice.sort(Comparator.naturalOrder());    // O(n logn)
        /*
        SortedPrice.sort(new Comparator<pair<long, int>>() {
            @Override
            public int compare(pair<long, int> o1, pair<long, int> o2) {
                return o1.getRight()
                .compareTo(o2.getRight());
            }
        });   // source: https://www.baeldung.com/java-list-sort-pairs
        */

        sortedPrice.sort(Comparator.comparingLong(p -> p.price)); // O(n logn)

        // int min = Integer.MAX_VALUE;    // assume a valid answer exist
        long min = Integer.MAX_VALUE; // assume a valid answer exist
        /* TLE!!!
        for (int i=1; i<SortedPrice.size(); i++) {  // O(n) <- wrong
            if (price.indexOf(SortedPrice.get(i)) < price.indexOf(SortedPrice.get(i-1))) {  // Oh, this O(n^2)
                min = Math.min(min, (int)(SortedPrice.get(i) - SortedPrice.get(i-1)));
            }
        }*/

        for (int i = 1; i < sortedPrice.size(); i++) {                     // O(n)
            if (sortedPrice.get(i).index < sortedPrice.get(i - 1).index) { // accessing should be O(1) now
                // min = Math.min(min, (int)(sortedPrice.get(i).price - sortedPrice.get(i-1).price));

                // maybe integer overflow during type conversion, making the new diff negavive?
                // min = Math.min(min, (int)Math.abs(sortedPrice.get(i).price - sortedPrice.get(i-1).price));    // bad
                // fix
                min = Math.min(min, sortedPrice.get(i).price - sortedPrice.get(i - 1).price);
            }
        }

        // return min;
        return (int)min;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Long> price = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                               .map(Long::parseLong)
                               .collect(toList());

        int result = Result.minimumLoss(price);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
