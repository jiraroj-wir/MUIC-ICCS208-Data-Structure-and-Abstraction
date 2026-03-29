import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'quickestWayUp' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY ladders
     *  2. 2D_INTEGER_ARRAY snakes
     */
    static class Pair {
        int pos;
        int moves;

        Pair(int p, int m) {
            this.pos = p;
            this.moves = m;
        }
    }

    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
        // Write your code here
        HashMap<Integer, Integer> laddersMap = new HashMap<>();
        for (List<Integer> ladder : ladders) {
            laddersMap.put(ladder.get(0), ladder.get(1));
        }

        HashMap<Integer, Integer> snakesMap = new HashMap<>();
        for (List<Integer> snake : snakes) {
            snakesMap.put(snake.get(0), snake.get(1));
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(1, 0));

        while (!q.isEmpty()) {
            Pair current = q.poll();

            for (int i = 1; i <= 6; i++) {
                int temp = current.pos + i;
                if (laddersMap.containsKey(temp)) {
                    temp = laddersMap.get(temp);
                } else if (snakesMap.containsKey(temp)) {
                    temp = snakesMap.get(temp);
                }

                if (temp == 100) {
                    return current.moves + 1;
                }

                q.add(new Pair(temp, current.moves + 1));
            }
        }

        return -1;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> ladders = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] laddersRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                List<Integer> laddersRowItems = new ArrayList<>();

                for (int j = 0; j < 2; j++) {
                    int laddersItem = Integer.parseInt(laddersRowTempItems[j]);
                    laddersRowItems.add(laddersItem);
                }

                ladders.add(laddersRowItems);
            }

            int m = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> snakes = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                String[] snakesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                List<Integer> snakesRowItems = new ArrayList<>();

                for (int j = 0; j < 2; j++) {
                    int snakesItem = Integer.parseInt(snakesRowTempItems[j]);
                    snakesRowItems.add(snakesItem);
                }

                snakes.add(snakesRowItems);
            }

            int result = Result.quickestWayUp(ladders, snakes);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
