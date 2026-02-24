import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyPriorityQueue<T> implements IPriorityQueue<T> {
    private List<T> queueItems;

    public MyPriorityQueue(CompareWith<T> cc) { queueItems = new ArrayList<>(); }

    @Override
    public void add(T item) {}

    @Override
    public void addAll(List<T> items) {}

    @Override
    public T getMinimum() {
        return null;
    }

    @Override
    public void removeMinimum() {}

    @Override
    // returns how many items the container has
    public int size() {
        // return 0;
        return queueItems.size();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Iterator<T> revIterator() {
        return null;
    }
}
