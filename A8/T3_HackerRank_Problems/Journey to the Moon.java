// NOTE: partial credit, 1.86/2
// I can't find the mistake, so, this code is my first submission (no improvement in score afterward).

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'journeyToMoon' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY astronaut
     */
    static class DSU {
        int[] field;
        int[] size;

        DSU(int size) {
            this.field = new int[size];
            for (int i = 0; i < size; i++) {
                this.field[i] = i;
            }

            this.size = new int[size];
            Arrays.fill(this.size, 1);
        }

        public int root(int a) {
            if (field[a] == a) {
                return a;
            }

            return root(field[a]);
        }

        public void union(int a, int b) {
            int rootA = root(a);
            int rootB = root(b);

            if (rootA != rootB) {
                if (this.size[rootA] > this.size[rootB]) {
                    this.field[rootB] = this.field[rootA];
                    this.size[rootA] += this.size[rootB];
                } else {
                    this.field[rootA] = this.field[rootB];
                    this.size[rootB] += this.size[rootA];
                }
            }
        }

        public int[] getField() { return this.field; }

        public int[] getSize() { return this.size; }
    }

    public static int journeyToMoon(int n, List<List<Integer>> astronaut) {
        // Write your code here
        DSU dsu = new DSU(n);

        for (List<Integer> pair : astronaut) {
            dsu.union(pair.get(0), pair.get(1));
        }

        Set<Integer> rootSet = new HashSet<>();
        for (int f : dsu.getField()) {
            rootSet.add(dsu.root(f));
        }

        LinkedList<Integer> setSizes = new LinkedList<>();
        for (int r : rootSet) {
            setSizes.add(dsu.getSize()[r]);
        }

        int total = 0;
        int sum = 0;

        for (int size : setSizes) {
            total += sum * size;
            sum += size;
        }

        return total;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int p = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> astronaut = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            String[] astronautRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> astronautRowItems = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                int astronautItem = Integer.parseInt(astronautRowTempItems[j]);
                astronautRowItems.add(astronautItem);
            }

            astronaut.add(astronautRowItems);
        }

        int result = Result.journeyToMoon(n, astronaut);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
