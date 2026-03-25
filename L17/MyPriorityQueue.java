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
}
