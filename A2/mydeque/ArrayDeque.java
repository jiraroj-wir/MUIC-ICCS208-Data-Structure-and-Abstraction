public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first_ptr;
    private int last_ptr;

    // Creates an empty array deque.
    public ArrayDeque() {
        items = (T[]) new Object[8];
        this.size = 0;
        this.first_ptr = 0;
        this.last_ptr = 0;
    }

    // Creates a deep copy of other.
    public ArrayDeque(ArrayDeque<T> other) {}

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {}
    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {}
    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() { return (this.size() == 0 && this.first_ptr == this.last_ptr); }
    // Returns the number of items in the deque.
    public int size() { return this.size; }
    // Returns a string showing the items in the deque from first to last, // separated by a space.
    public String toString() { return ""; }
    // Removes and returns the item at the front of the deque. // If no such item exists, returns null.
    public T removeFirst() { return null; }
    // Removes and returns the item at the back of the deque. // If no such item exists, returns null.
    public T removeLast() { return null; }
    // Gets the item at the given index, where 0 is the front, 1 is the next item, // and so forth. If no such
    // item exists, returns null. Must not alter the deque!
    public T get(int index) { return null; }
}
