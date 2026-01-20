import java.util.*;

public class RPal {
    // store all the known answers, so we don't have to compute them again
    private Map<Integer, List<List<Integer>>> storageAllRPals;

    // a constructor: creates the above storage and puts in answers
    // for our base cases of n = 0 and n = 1.
    public RPal() {
        storageAllRPals = new HashMap<>();
        storageAllRPals.put(0, Arrays.asList(Arrays.asList()));
        storageAllRPals.put(1, Arrays.asList(Arrays.asList(1)));
    }

    // Specification: return a list of integer lists of all recursively
    // palindromic sequences that sum to n.
    //
    // NOTE: When you implement this and want recursively-palindromic
    // sequences of k < n, you should not call yourself. Instead, you
    // will call allRPals(k).  The difference is that computeAllRPals
    // will compute the sequences every time whereas allRPals will *not*
    // compute the answer again unless it hasn't been computed before.
    private List<List<Integer>> computeAllRPals(int n) {
        // TODO: implement me

        List<List<Integer>> local_ll = new ArrayList<>();

        for (int i = 0; i <= (n >> 1); i++) {

            List<List<Integer>> all_pal_of_halves = allRPals(i);
            int mid_val = n - 2 * i;

            for (List<Integer> half : all_pal_of_halves) {
                List<Integer> temp = new ArrayList<>(half);
                if (mid_val != 0) {
                    temp.add(mid_val);
                }

                for (int j = half.size() - 1; j >= 0; j--) {
                    temp.add(half.get(j));
                }

                local_ll.add(temp);
            }
        }

        return local_ll;
    }

    public List<List<Integer>> allRPals(int n) {
        // Challenge: if you feel like learning a new trick, how would you
        // rewrite the following using just storageAllRPals.computeIfAbsent(..)?

        List<List<Integer>> answer = storageAllRPals.get(n);
        if (answer == null) {
            answer = computeAllRPals(n);
            storageAllRPals.put(n, answer);
        }

        return answer;
    }

    public static void main(String[] args) {
        RPal rPal = new RPal();

        System.out.println(rPal.allRPals(7));

        assert rPal.allRPals(99).size() == 9042;
        long start = System.nanoTime();
        List<List<Integer>> pals224 = rPal.allRPals(224);
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;
        assert pals224.size() == 355_906;
        assert elapsedMs <= 2_500;
    }
}
