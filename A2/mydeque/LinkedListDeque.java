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
        for (int _i = 0; _i < this.size; _i++) {
            if (str_b.length() > 0) {
                str_b.append(", ");
            } else {
                str_b.append('[');
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
        System.out.printf("start -> empty=%s size=%d contents=%s%n", dq.isEmpty(), dq.size(), dq);

        // basic adds/removes
        dq.addFirst(1);
        dq.addFirst(2);
        dq.addLast(0);
        dq.addLast(-1);
        System.out.printf("after mixed adds -> size=%d contents=%s first=%s last=%s%n", dq.size(), dq, dq.get(0),
                          dq.get(dq.size() - 1));

        System.out.printf("removeFirst -> %s%n", dq.removeFirst()); // removes 2
        System.out.printf("removeLast -> %s%n", dq.removeLast());   // removes -1
        System.out.printf("after removes -> size=%d contents=%s%n", dq.size(), dq);

        // get at positions and null cases
        System.out.printf("get(0)=%s%n", dq.get(0));
        System.out.printf("get(1)=%s%n", dq.get(1));
        System.out.printf("get(10)=%s%n", dq.get(10));

        // remove down to empty again
        System.out.printf("removeFirst -> %s%n", dq.removeFirst()); // removes 1
        System.out.printf("removeLast -> %s%n", dq.removeLast());   // removes 0
        System.out.printf("after emptying -> empty=%s size=%d contents=%s%n", dq.isEmpty(), dq.size(), dq);

        System.out.printf("removeFirst on empty -> %s%n", dq.removeFirst());
        System.out.printf("removeLast on empty -> %s%n", dq.removeLast());

        // build up larger dataset
        for (int i = 0; i < 5; i++) {
            dq.addLast(i * 10);
        }
        for (int i = 0; i < 5; i++) {
            dq.addFirst(-i * 10);
        }
        System.out.printf("after bulk adds -> size=%d contents=%s%n", dq.size(), dq);

        // iterate manually via get
        System.out.print("sequence via get():");
        for (int i = 0; i < dq.size(); i++) {
            System.out.print(" " + dq.get(i));
        }
        System.out.println();

        // clone tests
        LinkedListDeque<Integer> copy = new LinkedListDeque<>(dq);
        System.out.printf("copy -> size=%d contents=%s%n", copy.size(), copy);

        dq.removeFirst();
        dq.removeLast();
        copy.addFirst(999);
        copy.addLast(111);
        System.out.printf("after independent edits -> original=%s size=%d%n", dq, dq.size());
        System.out.printf("after independent edits -> copy    =%s size=%d%n", copy, copy.size());

        // ensure original still correct after many ops
        while (!dq.isEmpty()) {
            System.out.printf("popping %s%n", dq.removeFirst());
        }
        System.out.printf("end -> empty=%s size=%d contents=%s%n", dq.isEmpty(), dq.size(), dq);
    }
}
