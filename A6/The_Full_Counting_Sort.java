import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'countSort' function below.
     *
     * The function accepts 2D_STRING_ARRAY arr as parameter.
     */

    public static void countSort(List<List<String>> arr) {
        // Write your code here
        List<String>[] bucket = new List[100];

        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<>();
        }
        // replace the first half with '-'
        for (int i = 0; i < arr.size(); i++) {
            int key = Integer.parseInt(arr.get(i).get(0));
            String val = arr.get(i).get(1);

            if (i < arr.size() >> 1) { // turn the first half value into '-'
                bucket[key].add("-");
                continue;
            }

            bucket[key].add(val);
        }

        // print it out
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < bucket.length; i++) {
            for (String str : bucket[i]) {
                // TLE!!!
                // I supposed printf is very slow?
                // System.out.printf(str + " ");

                out.append(str).append(" ");
            }
        }

        System.out.printf(out.toString());
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(Arrays.asList(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")));
        }

        Result.countSort(arr);

        bufferedReader.close();
    }
}
