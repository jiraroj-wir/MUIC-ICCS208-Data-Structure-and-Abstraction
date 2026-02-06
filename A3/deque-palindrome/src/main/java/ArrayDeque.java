public class ArrayDeque<T> implements Deque<T> {
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
    public ArrayDeque(ArrayDeque<T> other) {
        this();

        for (int i = 0; i < other.size(); i++) {
            this.addLast(other.get(i));
        }
    }

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
    @Override
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
    @Override
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
    @Override
    public boolean isEmpty() { return (this.size() == 0); }

    // Returns the number of items in the deque.
    @Override
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
    @Override
    public String toString() {
        StringBuilder str_b = new StringBuilder(); // avoid `String` copying itself everytime we do append

        int idx = first_ptr;
        for (int i = 0; i < this.size(); i++) {
            if (i > 0) {
                str_b.append(' ');
            }

            str_b.append(this.items[idx]);
            idx = (idx + 1) % this.items.length;
        }

        return str_b.toString();
    }

    // Removes and returns the item at the front of the deque. // If no such item exists, returns null.
    @Override
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
    @Override
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
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }

        return this.items[(this.first_ptr + index) % this.items.length];
    }

    @Override
    public void printDeque() { System.out.printf("" + this.toString() + '\n'); }

    // tests
    public static void main(String[] args) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        assert dq.isEmpty();
        assert dq.size() == 0;
        assert "".equals(dq.toString());

        dq.addFirst(1);
        dq.addFirst(2);
        dq.addLast(0);
        dq.addLast(-1);
        assert dq.size() == 4;
        assert "2 1 0 -1".equals(dq.toString());
        assert dq.get(0) == 2;
        assert dq.get(dq.size() - 1) == -1;

        assert dq.removeFirst() == 2;
        assert dq.removeLast() == -1;
        assert dq.size() == 2;
        assert "1 0".equals(dq.toString());

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

        ArrayDeque<Integer> copy = new ArrayDeque<>(dq);
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
        assert "".equals(dq.toString());

        ArrayDeque<String> words = new ArrayDeque<>();
        words.addLast("orca");
        words.addFirst("6781617");
        assert "6781617 orca".equals(words.toString());
        assert "orca".equals(words.removeLast());
        assert "6781617".equals(words.removeFirst());

        ArrayDeque<Double> doubles = new ArrayDeque<>();
        doubles.addFirst(1.5);
        doubles.addLast(2.5);
        assert doubles.get(0) == 1.5;
        assert doubles.get(1) == 2.5;
    }
}
