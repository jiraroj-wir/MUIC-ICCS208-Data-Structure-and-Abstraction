// public class AList<T> {
public class AList {
    // private T[] items;
    private int[] items;
    private int size;

    // Creates an empty array deque.
    @SuppressWarnings("unchecked") // remove my lsp warnings
    public AList() {
        // items = (T[]) new Object[8];
        items = new int[8];

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
    public void addFirst(int x) {
        if (this.size() == this.items.length) {
            resize(this.items.length << 1);
        }

        for (int i = this.size(); i >= 1; i--) {
            this.items[i] = this.items[i - 1];
        }
        this.items[0] = x;
        this.size++;
    }

    // from A2
    @SuppressWarnings("unchecked")
    private void resize(int new_capacity) {
        // T[] new_items = (T[]) new Object[new_capacity];
        int[] new_items = new int[new_capacity];

        for (int i = 0; i < this.size(); i++) {
            new_items[i] = this.items[i];
        }

        this.items = new_items;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() { return (this.size() == 0); }

    // Returns the number of items in the deque.
    public int size() { return this.size; }
}
