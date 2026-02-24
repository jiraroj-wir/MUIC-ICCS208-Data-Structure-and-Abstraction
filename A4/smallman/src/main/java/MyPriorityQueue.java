import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MyPriorityQueue<T> implements IPriorityQueue<T> {
    private List<T> queueItems;
    private CompareWith<T> comparator;

    public MyPriorityQueue(CompareWith<T> cc) {
        if (cc == null) {
            throw new NoSuchElementException("comparator shouldn't be null");
        }

        this.queueItems = new ArrayList<>();
        this.comparator = cc;
    }

    @Override
    // adds an item
    public void add(T item) {
        int idx = 0;
        while (idx < this.queueItems.size() && this.comparator.lessThan(this.queueItems.get(idx), item)) {
            idx++;
        }

        this.queueItems.add(idx, item);
    }

    @Override
    // adds a list of items
    public void addAll(List<T> items) {
        for (item : items) {
            this.add(item);
        }
    }

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
        return this.queueItems.size();
    }

    @Override
    // returns an iterator that will list all the items in the container from small to large
    public Iterator<T> iterator() {
        // return null;
        return new ArrayList<>(this.queueItems).iterator(); // just use the build-ins
    }

    @Override
    public Iterator<T> revIterator() {
        return null;
    }
}
