public class AList<T> {
    // public class AList {
    private T[] items;
    // private int[] items;
    private int size;

    // Creates an empty array deque.
    @SuppressWarnings("unchecked") // remove my lsp warnings
    public AList() {
        items = (T[]) new Object[8];
        // items = new int[8];

        this.size = 0;
    }

    /*
    Exercise I: Actions in the Front
    Add support for adding an item to the front of our int array list by writing a public method:
    `public void addFirst(int x)`
    */

    /*
    How long do you you think it takes to run this? Does it change with the length of the the list?
    Provide your answers as comments in the code.

    Ans. On 'standard' array based list where the first element always starts at index 0. we must shift all the
    elements to the right by 1, so the worse case runtime will be O(n) --- linear run time as the length grew.
    But, for a circular array based list, since we kept track of the list's head and tail, the front append time would
    be constant --- O(1).
    */
    public void addFirst(T x) {
        if (this.size() == this.items.length) {
            resize(this.items.length << 1);
        }

        for (int i = this.size(); i >= 1; i--) {
            this.items[i] = this.items[i - 1];
        }
        this.items[0] = x;
        this.size++;
    }

    /*
    Exercise II: Array List with Generic Types
    You will implement an array list class class AList<T> capable of storing elements of a generic type T, similar to
    what you've read in the self-study lesson. Your implementation should be complete with the same set of convenient
    methods as the SLList from last time.

    The general idea is to change int[] items to a generic T[] items. But there are some technical concerns.

    How to make an array of generics?
    It is not possible to write new T[2]. Java simply doesn't allow that (more on this soon). To sidestep Java's
    craziness, you'll create an Object array and typecast it as T[]. This will give you a warning, but you can safely
    ignore it. In effect, if you want to create an array of type T of size n and store it as items, you'll write:

    items = (T[]) new Object[n];

    To explain this a bit more, the expression new Object[n] makes an array of length n of type Object. This is a hack
    because we aren't interested in storing Objects, but it is necessary and one of the dark corners of Java. The
    expression (T[]) is known as typecasting—the process of forcing a type into another type. In this case, force the
    thing to the right to be seen as a (T[])—that is, an array of generic type T. The joy of garbage collection When you
    implement removeLast, it is important that you set the removed item to null, like so:


    public T removeLast() {
        T itemToRemove = items[size - 1];
        items[size - 1] = null; // important: see below
        size--;

        return itemToRemove
    }
    While zeroing out the removed position was optional in the int array list implementation, it is crucial in the
    generic implementation. Here are a few things to understand:

    You don't explicitly free unused memory in Java. Java destroys unused objects automatically—and it knows this
    because the last reference to an object has been lost. Therefore, if you keep a reference to an otherwise unused
    object, Java will continue to keep this object around. We need to "free" this reference, so Java can properly
    collect garbage!
    */

    public void addLast(T x) {
        if (this.items.length == this.size()) {
            resize(this.items.length << 1);
        }

        this.items[size] = x;
        this.size++;
    }

    public T getFirst() {
        if (this.isEmpty()) {
            throw new IllegalStateException("empty list");
        }

        return this.items[0];
    }

    public T getLast() {
        if (this.isEmpty()) {
            throw new IllegalStateException("empty list");
        }
        return this.items[this.size - 1];
    }

    public String toString() {
        /*
         * since java `String` is immutable, `str = str + current.head` will creates a new string every time it gets
         * called --- so, I use string with buffer instead.
         */
        StringBuffer str_buff = new StringBuffer();

        str_buff.append("[");
        for (int i = 0; i < this.size(); i++) {
            if (str_buff.length() > 1) { // ignore the first '['
                str_buff.append(", ");
            }
            str_buff.append(this.items[i]);
        }
        str_buff.append("]");

        return str_buff.toString();
    }

    public void removeFirst() {
        if (this.isEmpty()) {
            return;
            // throw new IllegalStateException("empty list");
        }

        for (int i = 0; i < (this.size() - 1); i++) {
            this.items[i] = this.items[i + 1];
        }

        this.items[size - 1] = null;
        this.size--;
    }

    public void removeLast() {
        if (this.isEmpty()) {
            return;
            // throw new IllegalStateException("empty list");
        }

        this.items[size - 1] = null;
        this.size--;
    }

    public void insert(T newValue, int k) {
        if (k <= 0) {
            addFirst(newValue);
            return;
        }
        if (k >= this.size) {
            addLast(newValue);
            return;
        }

        if (this.size == this.items.length) {
            resize(this.items.length << 1);
        }

        for (int i = this.size; i > k; i--) { // shift right, open entry for k
            this.items[i] = this.items[i - 1];
        }
        this.items[k] = newValue;
        this.size++;
    }

    @SuppressWarnings("unchecked")
    private void resize(int new_capacity) {
        T[] new_items = (T[]) new Object[new_capacity];
        // int[] new_items = new int[new_capacity];

        for (int i = 0; i < this.size(); i++) {
            new_items[i] = this.items[i];
        }

        this.items = new_items;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() { return (this.size() == 0); }

    // Returns the number of items in the deque.
    public int size() { return this.size; }

    /*
    Exercise III: Loop Invariant
    As a comment block in the same file you're handing in for the previous exercises, complete the following reasoning.
    Importantly, flesh out the loop invariant and "assertions" throughout the code to establish a valid chain of
    reasoning from the start to finish.
    */

    /*
    int sum(int x) { // precondition: x >= 0
        int p = 0;
        for (int i = 0; i < x; i++) { // @loop_invariant: 0 <= i <= x and ___________________________
            p += pow(2, i);           // suppose there is a powering function where pow(2, i) returns 2**i
        }
        return p;
    }
    // post-condition: return 2**x - 1
    */

    /*
    at the start of each loop iteration (current i):
    1) 0 <= i <= x
    2) p == (2^i - 1)

    Initialization: i = 0, p = 0; 2^0 - 1 = 0, so invariant holds

    Maintenance: firstly, we assume invariant holds at loop start: p = 2^i - 1, then the body do: p += 2^i
    -> p = (2^i - 1) + 2^i = 2^(i+1) - 1 --- the next assumption is p = 2^(i+1) - 1, so invariant holds

    Termination: loop ends when (i == x); invariant gives p = 2^x - 1, which match the postcondition
    */

    // test
    public static void main(String[] args) {
        AList<Integer> list = new AList<>();
        assert "[]".equals(list.toString());
        assert list.isEmpty();

        list.addLast(2);
        assert "[2]".equals(list.toString());
        assert list.getFirst() == 2;
        assert list.getLast() == 2;

        list.addFirst(1);
        assert "[1, 2]".equals(list.toString());
        assert list.getFirst() == 1;

        list.addLast(4);
        assert "[1, 2, 4]".equals(list.toString());
        assert list.getLast() == 4;

        list.insert(3, 2);
        assert "[1, 2, 3, 4]".equals(list.toString());

        list.insert(0, 0);
        assert "[0, 1, 2, 3, 4]".equals(list.toString());

        list.insert(5, list.size());
        assert "[0, 1, 2, 3, 4, 5]".equals(list.toString());

        list.removeFirst();
        assert "[1, 2, 3, 4, 5]".equals(list.toString());

        list.removeLast();
        assert "[1, 2, 3, 4]".equals(list.toString());

        assert list.size() == 4;
    }
}
