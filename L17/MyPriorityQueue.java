import java.util.*;

public class MyPriorityQueue<T> {
    T[] heap;
    int size;
    Comparator<T> comparator;

    public MyPriorityQueue(Comparator<T> cc) {
        heap = (T[]) new Object[2 << 4];
        size = 0;
        comparator = cc;
    }
}
