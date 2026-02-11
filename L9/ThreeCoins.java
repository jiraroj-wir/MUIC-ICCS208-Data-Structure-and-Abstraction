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

Proof.

we split this proof into five congruence cases, prove each of them by
observation, we claim that

any integer p that's at least 20 can be constructed
with the sum of 5, 11 and 12

classify the integers by their residue modulo 5, given the base integer
of each category (residue of 0, 1, 2, 3 and 4), sum it up with arbitrary 5n
where n is also an integer --- we could construct any integer following
that base integers.

so, now we just need to find those 5 base integers, intersect their range
--- which is b - 5 where b is that base integer.

1) case 1: b0 ≡ 0 (mod 5)

we got the smallest b0 as 0, which we can now construct any integer of
this same residue

0 = 0*5 + 0*11 + 0*12

0 ≡ 0 + 5n (mod 5)

and thus, the range of this base residue case is
(-5, ∞) (around (0, ∞)).

2) case 2: b1 ≡ 1 (mod 5)

the smallest b1 following the construction rule is

11 = 11,

hence every integer of this residue is

11 ≡ 11 + 5n (mod 5),     11 + 5n = 11 + 5n

and the range is
(11 - 5, ∞) = (6, ∞).

3) case 3: b2 ≡ 2 (mod 5)

the smallest b2 is

12 = 12,

so any integer of this residue satisfies

12 ≡ 12 + 5n (mod 5),     12 + 5n = 12 + 5n

and the range is
(12 - 5, ∞) = (7, ∞).

4) case 4: b3 ≡ 3 (mod 5)

the smallest b3 is

23 = 11 + 12,

giving

23 ≡ 23 + 5n (mod 5),     23 + 5n = 11 + 12 + 5n

and the range is
(23 - 5, ∞) = (18, ∞).

5) case 5: b4 ≡ 4 (mod 5)

the smallest b4 is

24 = 12 + 12,

giving

24 ≡ 24 + 5n (mod 5),     24 + 5n = 12 + 12 + 5n

and the range is
(24 - 5, ∞) = (19, ∞).

we then intersect the range from those cases

(0, ∞) ∩ (6, ∞) ∩ (7, ∞) ∩ (18, ∞) ∩ (19, ∞) = (19, ∞) ≈ [20, ∞)

thus p is true for all integers over 20.

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
    public static int numWays(int n) {
        if (n < 0) {
            return 0;
        }

        int[] coins = {5, 11, 12};

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int c : coins) {
            for (int i = c; i <= n; i++) {
                dp[i] += dp[i - c];
            }
        }

        return dp[n];
    }

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
