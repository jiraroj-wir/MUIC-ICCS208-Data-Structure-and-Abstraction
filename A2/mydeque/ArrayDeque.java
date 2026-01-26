public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first_ptr;
    private int last_ptr;

    // Creates an empty array deque.
    @SuppressWarnings("unchecked") // remove my lsp warnings
    public ArrayDeque() {
        items = (T[]) new Object[8];
        this.size = 0;
        this.first_ptr = 0;
        this.last_ptr = 0;
    }

    // Creates a deep copy of other.
    public ArrayDeque(ArrayDeque<T> other) {}

    @SuppressWarnings("unchecked")
    private void resize(int new_capacity) {
        T[] new_items = (T[]) new Object[new_capacity];

        int right_chunk = Math.min(this.size(), items.length - first_ptr);
        System.arraycopy(items, first_ptr, new_items, 0, right_chunk);
        if (this.size() > right_chunk) {
            System.arraycopy(items, 0, new_items, right_chunk, this.size() - right_chunk);
        }

        items = new_items;
        first_ptr = 0;
        last_ptr = this.size() == 0 ? 0 : this.size() - 1;
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (this.size() == this.items.length) {
            resize(this.items.length << 1);
        }

        if (this.isEmpty()) {
            this.first_ptr = 0;
            this.last_ptr = 0;
        } else {
            this.first_ptr = (this.first_ptr - 1 + this.items.length) % this.items.length;
        }

        this.items[first_ptr] = item;
        this.size++;
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if (this.size() == this.items.length) {
            resize(this.items.length << 1);
        }

        if (this.isEmpty()) {
            this.first_ptr = 0;
            this.last_ptr = 0;
        } else {
            this.last_ptr = (this.last_ptr + 1) % this.items.length;
        }

        this.items[last_ptr] = item;
        this.size++;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() { return (this.size() == 0); }

    // Returns the number of items in the deque.
    public int size() {
        return this.size;

        /*  this failed when `first_ptr` == `last_ptr`, and maybe a few more cases
        if (first_ptr > last_ptr) {
            return this.items.length - this.first_ptr + this.last_ptr + 1;
        }

        return this.last_ptr - this.first_ptr + 1;
        */
    }

    // Returns a string showing the items in the deque from first to last, // separated by a space.
    public String toString() {
        StringBuilder str_b = new StringBuilder(); // avoid `String` copying itself everytime we do append

        int idx = first_ptr;
        str_b.append('[');
        for (int i = 0; i < this.size(); i++) {
            if (str_b.length() > 1) { // accounted for '['
                str_b.append(", ");
            }

            str_b.append(this.items[idx]);
            idx = (idx + 1) % this.items.length;
        }
        str_b.append(']');

        return str_b.toString();
    }

    // Removes and returns the item at the front of the deque. // If no such item exists, returns null.
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        T removed = items[this.first_ptr];
        this.items[this.first_ptr] = null;
        this.first_ptr = (this.first_ptr + 1) % this.items.length;
        this.size--;

        // threshold at 0.25 of the items size, reduce by halfs
        // the items' size must be at least 16
        if (this.items.length >= 16 && this.size < (this.items.length >> 2)) {
            this.resize(this.items.length >> 1);
        }

        if (this.isEmpty()) {
            this.first_ptr = 0;
            this.last_ptr = 0;
        }

        return removed;
    }
    // Removes and returns the item at the back of the deque. // If no such item exists, returns null.
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        T removed = this.items[this.last_ptr]; // save for return
        this.items[this.last_ptr] = null;
        this.last_ptr = (this.last_ptr - 1 + this.items.length) % this.items.length;
        this.size--;

        // threshold at 0.25 of the items size, reduce by halfs
        // the items' size must be at least 16
        if (this.items.length >= 16 && this.size < (this.items.length >> 2)) {
            this.resize(this.items.length >> 1);
        }

        if (this.isEmpty()) {
            this.first_ptr = 0;
            this.last_ptr = 0;
        }

        return removed;
    }
    // Gets the item at the given index, where 0 is the front, 1 is the next item, // and so forth. If no such
    // item exists, returns null. Must not alter the deque!
    public T get(int index) { return this.items[(this.first_ptr + index) % this.items.length]; }

    public void printDeque() { System.out.printf("" + this.toString() + '\n'); }
}
