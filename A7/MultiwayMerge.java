import java.util.*;

public class MultiwayMerge {
    static class Pair {
        Integer value;
        int index;

        Pair(int v, int i) {
            value = v;
            index = i;
        }
    }
    public static LinkedList<Integer> mergeAll(LinkedList<Integer>[] lists) {
        LinkedList<Integer> merged = new LinkedList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));

        for (int i = 0; i < lists.length; i++) {
            if (!lists[i].isEmpty()) {
                pq.add(new Pair(lists[i].removeFirst(), i));
            }
        }

        while (!pq.isEmpty()) {
            Pair front = pq.poll();
            merged.add(front.value);

            if (!lists[front.index].isEmpty()) {
                int nextValue = lists[front.index].removeFirst();
                pq.add(new Pair(nextValue, front.index));
            }
        }

        return merged;
    }

    // tests
    public static void main(String[] args) {
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] t1 =
            new LinkedList[] {new LinkedList<>(Arrays.asList(1, 4, 7)), new LinkedList<>(Arrays.asList(2, 5, 8)),
                              new LinkedList<>(Arrays.asList(3, 6, 9))};
        assert mergeAll(t1).equals(new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));

        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] t2 =
            new LinkedList[] {new LinkedList<>(Arrays.asList(1, 10)), new LinkedList<>(Arrays.asList(2, 3, 4, 5)),
                              new LinkedList<>(Arrays.asList(6))};
        assert mergeAll(t2).equals(new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 10)));

        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] t3 =
            new LinkedList[] {new LinkedList<>(Arrays.asList(1, 3, 5)), new LinkedList<>(Arrays.asList(1, 3, 5)),
                              new LinkedList<>(Arrays.asList(1, 3, 5))};
        assert mergeAll(t3).equals(new LinkedList<>(Arrays.asList(1, 1, 1, 3, 3, 3, 5, 5, 5)));

        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] t4 =
            new LinkedList[] {new LinkedList<>(), new LinkedList<>(Arrays.asList(1, 2)), new LinkedList<>()};
        assert mergeAll(t4).equals(new LinkedList<>(Arrays.asList(1, 2)));

        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] t5 = new LinkedList[] {new LinkedList<>(), new LinkedList<>()};
        assert mergeAll(t5).equals(new LinkedList<>());
    }
}
