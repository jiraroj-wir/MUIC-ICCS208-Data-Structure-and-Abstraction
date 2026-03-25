import java.util.*;

public class MyPriorityQueue<T> {
    T[] heap;
    int size;
    Comparator<T> comparator;

    public MyPriorityQueue(Comparator<T> cc) {
        this.heap = (T[]) new Object[2 << 4];
        this.size = 0;
        this.comparator = cc;
    }

    public void add(T item) {
        if (this.size == this.heap.length) {
            resize(this.heap.length << 1);
        }
        this.heap[this.size] = item;
        int i = this.size;
        this.size++;

        // similar to bubble sort, the newly added item floats up
        while (i > 0 && this.comparator.compare(this.heap[(i - 1) / 2], this.heap[i]) > 0) { // min heap
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public T peek() { return this.heap[0]; }

    public T poll() {
        if (this.size == 0) {
            return null;
        }

        T root = this.heap[0];

        this.heap[0] = this.heap[this.size - 1];
        this.heap[this.size - 1] = null;
        this.size--;

        heapify(0);

        return root;
    }

    // allow heapify to be public, might be useful for user?
    public void heapify(int i) {
        int min = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < this.size && this.comparator.compare(this.heap[left], this.heap[min]) < 0) {
            min = left;
        }

        if (right < this.size && this.comparator.compare(this.heap[right], this.heap[min]) < 0) {
            min = right;
        }

        if (min != i) {
            swap(i, min);
            heapify(min);
        }
    }

    private void resize(int newSize) { this.heap = Arrays.copyOf(this.heap, newSize); }

    private void swap(int a, int b) {
        T temp = this.heap[a];
        this.heap[a] = this.heap[b];
        this.heap[b] = temp;
    }

    public int size() { return this.size; }

    public boolean isEmpty() { return (this.size == 0); }

    // tests
    public static void main(String[] args) {
        MyPriorityQueue<Integer> pq1 = new MyPriorityQueue<Integer>(Comparator.naturalOrder());
        pq1.add(5);
        pq1.add(1);
        pq1.add(3);
        assert pq1.peek() == 1;

        MyPriorityQueue<Integer> pq2 = new MyPriorityQueue<Integer>(Comparator.naturalOrder());
        pq2.add(4);
        pq2.add(2);
        pq2.add(7);
        assert pq2.poll() == 2;
        assert pq2.peek() == 4;

        MyPriorityQueue<Integer> pq3 = new MyPriorityQueue<Integer>(Comparator.naturalOrder());
        for (int i = 10; i >= 1; i--)
            pq3.add(i);
        for (int i = 1; i <= 10; i++)
            assert pq3.poll() == i;

        MyPriorityQueue<String> pq4 = new MyPriorityQueue<String>(Comparator.naturalOrder());
        pq4.add("c");
        pq4.add("a");
        pq4.add("b");
        assert pq4.poll().equals("a");
        assert pq4.poll().equals("b");
        assert pq4.poll().equals("c");

        MyPriorityQueue<Integer> pq5 = new MyPriorityQueue<Integer>(Comparator.reverseOrder());
        pq5.add(1);
        pq5.add(3);
        pq5.add(2);
        assert pq5.peek() == 3;
    }
}
