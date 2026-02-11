import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
Problem 2: Proof + Code
This part of the exercise is to be handed as code and proof written as comments inside the source file. You will upload
ThreeCoins.java to Canvas.
*/

/*
You will begin by first proving the following theorem: Given an unlimited supply of 5-cent stamps, 11-cent, and 12-cent
stamps, we can make any amount that is at least 20 cents.
*/

public class ThreeCoins {
    /*
    Write a public static method public static List<Integer> change(int n) that returns a list of stamp values used to
    make the amount n. For example, change(25) should return [5, 5, 5, 5, 5].
    */
    record Pair(int value, List<Integer> list) {}

    public static final Map<Integer, Pair> MAP =
        Map.of(0, new Pair(0, List.of()), 1, new Pair(11, List.of(11)), 2, new Pair(12, List.of(12)), 3,
               new Pair(23, List.of(11, 12)), 4, new Pair(24, List.of(12, 12)));

    public static List<Integer> change(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be positive integers");
        }
        if (n < 20) {
            // need to brute force, or manually hard coded these 20 answers
            throw new IllegalArgumentException("program don't supported n < 20 right now");
        }
        List<Integer> lst = new ArrayList<Integer>();
        int residue = n % 5;

        lst.addAll(MAP.get(residue).list());
        int fill = (n - MAP.get(residue).value()) / 5;

        for (int _i = 0; _i < fill; _i++) {
            lst.add(5);
        }

        return lst;
    }

    /*
    [TODO] Extra Challenge: Write a public method public static int numWays(int n) that counts the number of ways an
    amount n can be expressed using this set of stamp values.
    */

    // test
    public static void main(String[] args) {
        assert change(25).equals(List.of(5, 5, 5, 5, 5));

        for (int n = 20; n <= 100; n++) {
            List<Integer> c = change(n);
            int s = 0;
            for (int x : c)
                s += x;
            assert s == n;
        }
    }
}
