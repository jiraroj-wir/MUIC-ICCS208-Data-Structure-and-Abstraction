// NOTE: partial credit, 1.25/2
// I could think of a few optimization tricks, maybe use a `byte` type to encode the 4 poles instead
// or maybe I can try hash before storing to `visit` set, as eventhough it's a hashset, i supposed it needs to do some
// comparison on the list as well --- that could be (maybe) O(n)?
//
// Ps. as there's 4 questions, and i only have to do 3, optimizing this one would takes too much time, and i guess the
// last question (that i choose to skip initially) should be easier to AC than this one; so i'll just leave this one
// like this, skip.

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
     * Complete the 'hanoi' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY posts as parameter.
     */

    private static class pair {
        List<Integer> config;
        int moves;

        pair(List<Integer> c, int m) {
            this.config = c;
            this.moves = m;
        }
    }

    public static int hanoi(List<Integer> posts) {
        List<Integer> GOAL = new ArrayList<>();
        for (int i = 0; i < posts.size(); i++) {
            GOAL.add(1);
        }

        Queue<pair> q = new LinkedList<>();
        q.add(new pair(new ArrayList<>(posts), 0));

        HashSet<List<Integer>> visited = new HashSet<>();
        visited.add(new ArrayList<>(posts));

        while (!q.isEmpty()) {
            pair current = q.poll();
            List<Integer> config = current.config;
            int moves = current.moves;

            if (config.equals(GOAL)) {
                return moves;
            }

            for (int disk = 0; disk < posts.size(); disk++) {
                int fromRod = config.get(disk);

                boolean isTop = true;
                // anything on top of the disk?
                for (int smaller = 0; smaller < disk; smaller++) {
                    if (config.get(smaller) == fromRod) {
                        isTop = false;
                        break;
                    }
                }
                if (!isTop) {
                    continue;
                }

                for (int toRod = 1; toRod <= 4; toRod++) {
                    if (toRod == fromRod) {
                        continue;
                    }

                    boolean valid = true;
                    for (int smaller = 0; smaller < disk; smaller++) {
                        if (config.get(smaller) == toRod) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) {
                        continue;
                    }

                    List<Integer> next = new ArrayList<>(config);
                    next.set(disk, toRod);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.add(new pair(next, moves + 1));
                    }
                }
            }
        }

        return -1;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> loc = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList());

        int res = Result.hanoi(loc);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
