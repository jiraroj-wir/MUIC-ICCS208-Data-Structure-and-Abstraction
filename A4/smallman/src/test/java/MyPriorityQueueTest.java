import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Test;

public class MyPriorityQueueTest {

    @Test
    public void testSimpleCases() {
        IPriorityQueue<Integer> priorityQueue = new MyPriorityQueue<>(new CompareWith<Integer>() {
            @Override
            public boolean lessThan(Integer a, Integer b) {
                return a < b;
            }
        });

        priorityQueue.add(5);
        assertEquals(priorityQueue.size(), 1);
        priorityQueue.add(1);
        assertEquals(priorityQueue.size(), 2);
        assertEquals(priorityQueue.getMinimum(), Integer.valueOf(1));
        priorityQueue.add(2);
        assertEquals(priorityQueue.size(), 3);
        assertEquals(priorityQueue.getMinimum(), Integer.valueOf(1));
        priorityQueue.addAll(Arrays.asList(4, 3, 2, -1));
        assertEquals(priorityQueue.size(), 7);
        assertEquals(priorityQueue.getMinimum(), Integer.valueOf(-1));
        priorityQueue.removeMinimum();
        assertEquals(priorityQueue.size(), 6);
        assertEquals(priorityQueue.getMinimum(), Integer.valueOf(1));
        priorityQueue.removeMinimum();
        assertEquals(priorityQueue.size(), 5);
        assertEquals(priorityQueue.getMinimum(), Integer.valueOf(2));

        Iterable<Integer> itr = () -> priorityQueue.iterator();
        assertEquals(StreamSupport.stream(itr.spliterator(), false).collect(Collectors.toList()),
                     Arrays.asList(2, 2, 3, 4, 5));

        Iterable<Integer> revItr = () -> priorityQueue.revIterator();
        assertEquals(StreamSupport.stream(revItr.spliterator(), false).collect(Collectors.toList()),
                     Arrays.asList(5, 4, 3, 2, 2));
    }

    @Test
    public void testEmptyQueue() {
        IPriorityQueue<Integer> pq = new MyPriorityQueue<>((a, b) -> a < b);

        assertEquals(0, pq.size());
        assertThrows(NoSuchElementException.class, pq::getMinimum);
        assertThrows(NoSuchElementException.class, pq::removeMinimum);
    }

    @Test
    public void testAddAllNullNoOP() {
        IPriorityQueue<Integer> pq = new MyPriorityQueue<>((a, b) -> a < b);

        pq.add(10);
        pq.addAll(null);

        assertEquals(1, pq.size());
        assertEquals(Integer.valueOf(10), pq.getMinimum());
    }

    private static <T> List<T> consumeIterator(Iterator<T> iterator) {
        List<T> values = new ArrayList<>();

        while (iterator.hasNext()) {
            values.add(iterator.next());
        }

        return values;
    }
}
