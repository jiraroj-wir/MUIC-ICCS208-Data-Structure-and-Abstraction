import java.util.*;

public class Zombies {
    public static int countBad(int[] hs) { return helper(hs, 0, hs.length - 1); }

    private static int helper(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = (left + right) >> 1;

        int count = 0;
        count += helper(arr, left, mid);
        count += helper(arr, mid + 1, right);
        count += merge(arr, left, mid, right);

        return count;
    }

    private static int merge(int[] array, int left, int mid, int right) {
        ArrayList<Integer> temp = new ArrayList<>();

        int i = left;
        int j = mid + 1;

        int count = 0;

        while (i <= mid && j <= right) {
            if ((array[i] >= array[j])) {
                temp.add(array[i++]);
            } else {
                count += j - (left + temp.size());
                temp.add(array[j++]);
            }
        }

        while (i <= mid) {
            temp.add(array[i++]);
        }

        while (j <= right) {
            temp.add(array[j++]);
        }

        for (int k = 0; k < temp.size(); k++) {
            array[left + k] = temp.get(k);
        }

        return count;
    }

    // given `in-efficient` code
    public class NaiveZombies {
        public static int countBad(int[] hs) {
            int badPairs = 0;
            for (int i = 0; i < hs.length; i++)
                for (int j = i + 1; j < hs.length; j++)
                    if (hs[i] < hs[j])
                        badPairs++;
            return badPairs;
        }
    }

    // tests
    public static void main(String[] args) {

        assert Zombies.countBad(new int[] {35, 22, 10}) == 0;
        assert Zombies.countBad(new int[] {3, 1, 4, 2}) == 3;
        assert Zombies.countBad(new int[] {5, 4, 11, 7}) == 4;
        assert Zombies.countBad(new int[] {1, 7, 22, 13, 25, 4, 10, 34, 16, 28, 19, 31}) == 49;

        assert Zombies.countBad(new int[] {1, 2, 3, 4}) == 6;
        assert Zombies.countBad(new int[] {9, 7, 5, 3}) == 0;
        assert Zombies.countBad(new int[] {5, 5, 5}) == 0;
        assert Zombies.countBad(new int[] {10, 3, 8, 6, 2}) == 2;
        assert Zombies.countBad(new int[] {42}) == 0;
        assert Zombies.countBad(new int[] {}) == 0;

        Random rand = new Random();
        for (int t = 0; t < 1000; t++) {
            int[] arr = rand.ints(20, 0, 100).toArray();
            int expected = Zombies.NaiveZombies.countBad(Arrays.copyOf(arr, arr.length));
            int actual = Zombies.countBad(Arrays.copyOf(arr, arr.length));
            assert expected == actual;
        }
    }
}
