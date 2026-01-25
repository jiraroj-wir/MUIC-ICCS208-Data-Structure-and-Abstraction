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

        // TODO: find a better way to do this.
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
    public String toString() { return ""; }
    // Removes and returns the item at the front of the deque. // If no such item exists, returns null.
    public T removeFirst() { return null; }

    // Removes and returns the item at the back of the deque. // If no such item exists, returns null.
    public T removeLast() { return null; }
    // Gets the item at the given index, where 0 is the front, 1 is the next item, // and so forth. If no such item
    // exists, returns null. Must not alter the deque!
    public T get(int index) { return null; }

    public void printDeque() {}
}
