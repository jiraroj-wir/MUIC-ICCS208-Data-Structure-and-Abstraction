public class LinkedListDeque<T> {

    private class Node {
        T value;

        Node prev;
        Node next;

        public Node(T val, Node next, Node prev) {
            this.value = val;
            this.next = next;
            this.prev = prev;
        }
    }

    private final Node sentinel;
    private int size;

    // Creates an empty linked list deque.
    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    // Creates a deep copy of other
    public LinkedListDeque(LinkedListDeque<T> other) {
        this();

        Node other_ptr = other.sentinel.next;
        for (int _i = 0; _i < other.size && other_ptr != other.sentinel; _i++) {
            this.addLast(other_ptr.value);
            other_ptr = other_ptr.next;
        }
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        Node first = new Node(item, this.sentinel.next, this.sentinel);
        /*
        first.value = item;
        first.next = this.sentinel.next;
        first.prev = this.sentinel;
        */
        this.sentinel.next = first;
        first.next.prev = first; // this.sentinel.next.next
        this.size++;
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        Node last = new Node(item, this.sentinel, this.sentinel.prev);
        this.sentinel.prev = last;
        last.prev.next = last;
        this.size++;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return (this.sentinel.next == this.sentinel && this.sentinel.prev == this.sentinel && this.size == 0);
    }

    // Returns the number of items in the deque.
    public int size() { return this.size; }

    // Returns a string showing the items in the deque from first to last, // separated by a space.
    @Override // use this `toString()` instead of the `official` ones from java.lang.Object
    public String toString() {
        StringBuilder str_b = new StringBuilder(); // avoid `String` copying itself everytime we do append

        Node ptr = sentinel.next;
        str_b.append('[');
        for (int _i = 0; _i < this.size; _i++) {
            if (str_b.length() > 1) { // accounted for '['
                str_b.append(", ");
            }
            str_b.append(ptr.value);
            ptr = ptr.next;
        }
        str_b.append(']');

        return str_b.toString();
    }

    // Removes and returns the item at the front of the deque. // If no such item exists, returns null.
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        this.size--;

        Node first = this.sentinel.next;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;

        return first.value;
    }

    // Removes and returns the item at the back of the deque. // If no such item exists, returns null.
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        this.size--;

        Node last = this.sentinel.prev;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;

        return last.value;
    }
    // Gets the item at the given index, where 0 is the front, 1 is the next item,
    // and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
            // throw new IndexOutOfBoundsException("Ahhh!!!, index out of bound!!!"); // panic, scream
        }

        Node ptr = this.sentinel.next;
        for (int _i = 0; _i < index; _i++) {
            ptr = ptr.next;
        }

        return ptr.value;
    }

    public void printDeque() { System.out.printf("" + this.toString() + '\n'); }

    // tests
    public static void main(String[] args) {
        LinkedListDeque<Integer> dq = new LinkedListDeque<>();
        assert dq.isEmpty();
        assert dq.size() == 0;
        assert "[]".equals(dq.toString());

        dq.addFirst(1);
        dq.addFirst(2);
        dq.addLast(0);
        dq.addLast(-1);
        assert dq.size() == 4;
        assert "[2, 1, 0, -1]".equals(dq.toString());
        assert dq.get(0) == 2;
        assert dq.get(dq.size() - 1) == -1;

        assert dq.removeFirst() == 2;
        assert dq.removeLast() == -1;
        assert dq.size() == 2;
        assert "[1, 0]".equals(dq.toString());

        assert dq.get(0) == 1;
        assert dq.get(1) == 0;
        assert dq.get(10) == null;

        assert dq.removeFirst() == 1;
        assert dq.removeLast() == 0;
        assert dq.isEmpty();
        assert dq.removeFirst() == null;
        assert dq.removeLast() == null;

        for (int i = 0; i < 5; i++)
            dq.addLast(i * 10);
        for (int i = 0; i < 5; i++)
            dq.addFirst(-i * 10);
        assert dq.size() == 10;
        int[] expected = {-40, -30, -20, -10, 0, 0, 10, 20, 30, 40};
        for (int i = 0; i < dq.size(); i++) {
            assert dq.get(i) == expected[i];
        }

        LinkedListDeque<Integer> copy = new LinkedListDeque<>(dq);
        assert copy.size() == dq.size();
        assert copy.toString().equals(dq.toString());

        dq.removeFirst();
        dq.removeLast();
        copy.addFirst(999);
        copy.addLast(111);
        assert dq.size() == 8;
        assert copy.size() == 12;
        assert !dq.toString().equals(copy.toString());

        while (!dq.isEmpty())
            dq.removeFirst();
        assert dq.isEmpty();
        assert "[]".equals(dq.toString());

        LinkedListDeque<String> words = new LinkedListDeque<>();
        words.addLast("orca");
        words.addFirst("6781617");
        assert "[6781617, orca]".equals(words.toString());
        assert "orca".equals(words.removeLast());
        assert "6781617".equals(words.removeFirst());

        LinkedListDeque<Double> doubles = new LinkedListDeque<>();
        doubles.addFirst(1.5);
        doubles.addLast(2.5);
        assert doubles.get(0) == 1.5;
        assert doubles.get(1) == 2.5;
    }
}
