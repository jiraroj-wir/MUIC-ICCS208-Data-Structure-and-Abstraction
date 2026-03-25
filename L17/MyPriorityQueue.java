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
        this.heap[this.size] = item;
        int i = this.size;
        this.size++;

        // similar to bubble sort, the newly added item floats up
        while (i > 0 && this.comparator.compare(this.heap[(i - 1) / 2], this.heap[i]) > 0) { // min heap
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void swap(int child, int parent) {
        T temp = this.heap[child];
        this.heap[child] = this.heap[parent];
        this.heap[parent] = temp;
    }
}
