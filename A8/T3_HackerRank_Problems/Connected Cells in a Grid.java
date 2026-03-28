import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'connectedCell' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY matrix as parameter.
     */
    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    enum Direction {
        // as we check from left-right, up-down
        NW(-1, -1),
        N(-1, 0),
        NE(-1, 1),
        W(0, -1),
        E(0, 1),
        SW(1, -1),
        S(1, 0),
        SE(1, 1);

        public int dx;
        public int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    private static int floodField(List<List<Integer>> matrix, boolean[][] visited, int i, int j) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        visited[i][j] = true;

        int area = 0;

        while (!q.isEmpty()) {
            Pair current = q.poll();
            area += 1;

            for (Direction d : Direction.values()) {
                int nx = current.x + d.dx;
                int ny = current.y + d.dy;

                if (nx >= 0 && nx < matrix.size() && ny >= 0 && ny < matrix.get(0).size()) {
                    if (matrix.get(nx).get(ny) == 1 && !visited[nx][ny]) {
                        q.add(new Pair(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return area;
    }

    public static int connectedCell(List<List<Integer>> matrix) {
        // Write your code here
        boolean[][] visited = new boolean[matrix.size()][matrix.get(0).size()];

        int max = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == 1 && !visited[i][j]) {
                    max = Math.max(max, floodField(matrix, visited, i, j));
                }
            }
        }

        return max;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> matrix = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] matrixRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> matrixRowItems = new ArrayList<>();

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowTempItems[j]);
                matrixRowItems.add(matrixItem);
            }

            matrix.add(matrixRowItems);
        }

        int result = Result.connectedCell(matrix);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
