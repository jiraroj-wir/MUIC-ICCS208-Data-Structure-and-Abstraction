import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'kFactorization' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY A
     */

    public static List<Integer> kFactorization(int n, List<Integer> A) {
        Collections.sort(A);

        Queue<List<Integer>> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> start = new ArrayList<>();

        visited.add(1);
        start.add(1);
        queue.add(start);

        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            int current = path.get(path.size() - 1);

            if (current == n) {
                return path;
            }

            for (int s : A) {
                long next = (long)current * s;

                if (next > n) {
                    continue;
                }
                if (n % next != 0) {
                    continue;
                }
                if (visited.contains((int)next)) {
                    continue;
                }

                visited.add((int)next);

                List<Integer> newPath = new ArrayList<>(path);
                newPath.add((int)next);

                queue.add(newPath);
            }
        }

        return Arrays.asList(-1);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] ATemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> A = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            int AItem = Integer.parseInt(ATemp[i]);
            A.add(AItem);
        }

        List<Integer> result = Result.kFactorization(n, A);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
