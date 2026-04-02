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
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        // Write your code here
        int verU = n + 1;
        int verL = 0;
        int horR = n + 1;
        int horL = 0;

        int dia1 = n + 1;
        int dia2 = 0;
        int dia3 = 0;
        int dia4 = n + 1;

        for (List<Integer> obs : obstacles) {
            int dx = r_q - obs.get(0);
            int dy = c_q - obs.get(1);

            if (dx == 0) {
                if (dy < 0) { // upper
                    verU = Math.min(obs.get(1), verU);
                } else { // lower; dy != 0 anyway
                    verL = Math.max(obs.get(1), verL);
                }
            }

            if (dy == 0) {
                if (dx < 0) { // right
                    horR = Math.min(obs.get(0), horR);
                } else { // left
                    horL = Math.max(obs.get(0), horL);
                }
            }

            if (Math.abs(dx) == Math.abs(dy)) {
                if (dx < 0 && dy < 0) { // Q1
                    dia1 = Math.min(obs.get(0), dia1);
                } else if (dx > 0 && dy < 0) { // Q2
                    dia2 = Math.max(obs.get(0), dia2);
                } else if (dx > 0 && dy > 0) { // Q3
                    dia3 = Math.max(obs.get(0), dia3);
                } else {
                    dia4 = Math.min(obs.get(0), dia4);
                }
            }
        }

        /*
        System.out.println("verU = " + verU);
        System.out.println("verL = " + verL);
        System.out.println("horR = " + horR);
        System.out.println("horL = " + horL);
        System.out.println("dia1 = " + dia1);
        System.out.println("dia2 = " + dia2);
        System.out.println("dia3 = " + dia3);
        System.out.println("dia4 = " + dia4);
        */

        long moves = 0;

        moves += (verU == n + 1) ? (n - c_q) : (verU - c_q - 1); // right
        moves += (verL == 0) ? (c_q - 1) : (c_q - verL - 1);     // left
        moves += (horR == n + 1) ? (n - r_q) : (horR - r_q - 1); // up
        moves += (horL == 0) ? (r_q - 1) : (r_q - horL - 1);     // down

        moves += (dia1 == n + 1) ? Math.min(n - r_q, n - c_q) : (dia1 - r_q - 1);

        moves += (dia2 == 0) ? Math.min(r_q - 1, n - c_q) : (r_q - dia2 - 1);

        moves += (dia3 == 0) ? Math.min(r_q - 1, c_q - 1) : (r_q - dia3 - 1);

        moves += (dia4 == n + 1) ? Math.min(n - r_q, c_q - 1) : (dia4 - r_q - 1);

        return (int)moves;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r_q = Integer.parseInt(secondMultipleInput[0]);

        int c_q = Integer.parseInt(secondMultipleInput[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                obstacles.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                  .map(Integer::parseInt)
                                  .collect(toList()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
