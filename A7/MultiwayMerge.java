import java.util.*;

public class MultiwayMerge {
    class Pair {
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
}
