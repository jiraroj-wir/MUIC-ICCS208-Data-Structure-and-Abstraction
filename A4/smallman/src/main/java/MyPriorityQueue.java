import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MyPriorityQueue<T> implements IPriorityQueue<T>, Iterable<T> {
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
        for (T item : items) {
            this.add(item);
        }
    }

    @Override
    // returns the smallest item currently in the container (if there are multiple such items, return any one of them)
    public T getMinimum() {
        // return null;
        if (this.queueItems.isEmpty()) {
            throw new NoSuchElementException("the queue is empty");
        }

        return this.queueItems.get(0); // as the queue is sorted form lowest to highest, index 0 is the lowest
    }

    @Override
    // removes the smallest item in the container (if there are multiple such items, remove the one that getMinimum
    // would return.)
    public void removeMinimum() {
        if (this.queueItems.isEmpty()) {
            throw new NoSuchElementException("the queue is empty");
        }

        // I don't think there's an easy way to reference the item that getMinimum would return, then remove that item,
        // here; but, my getMinimum will always return index 0, so remove at index 0?
        this.queueItems.remove(0);
    }

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

    /*
    @Override
    public Iterator<T> iterator() {
        return new ForwardIterator();
    }
    */

    @Override
    public Iterator<T> revIterator() {
        // return null;
        // I don't think you can reverse an iterator here, like .reverse()?
        return new ReverseIterator();
    }

    private class ForwardIterator implements Iterator<T> {
        private final List<T> queue = new ArrayList<>(MyPriorityQueue.this.queueItems);
        private int index;

        @Override
        public boolean hasNext() {
            return index < queue.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return queue.get(index++);
        }
    }

    private class ReverseIterator implements Iterator<T> {
        private final List<T> queue = new ArrayList<>(MyPriorityQueue.this.queueItems);
        private int index = queue.size() - 1;

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return queue.get(index--);
        }
    }
}
