public class SLList {

    private static class IntNode {
        int value;
        IntNode next;
    }

    private final IntNode sentinel;

    private int size;

    /*
    Exercise 0: Putting Them Altogether

    It's time to use the fragments described in the lesson to put together a working SLList that uses a sentinel. Your
    SLList class will not keep a reference to the last node. It should be complete with the following
    constructors/methods:

    - Two constructors: SLList() to construct an empty list, and SLList(int x) to construct a list with with int, namely
    x.
    - addFirst
    - addLast
    - getFirst
    - getLast
    - size (should be fast)
    */
    public SLList() {
        this.sentinel = new IntNode();
        size = 0;
    }

    public SLList(int x) {
        this(); // calls SLList
        addFirst(x);
    }

    public void addFirst(int x) {
        IntNode new_first = new IntNode();
        new_first.value = x;
        new_first.next = sentinel.next;
        sentinel.next = new_first;
        size++;
    }

    public void addLast(int x) {
        IntNode ptr = this.sentinel;
        while (ptr.next != null) {
            ptr = ptr.next;
        }

        ptr.next = new IntNode();
        ptr.next.value = x;
        size++;
    }

    public int getFirst() {
        if (this.sentinel.next == null) {
            throw new IllegalStateException("empty list");
        }

        return this.sentinel.next.value;
    }

    public int getLast() {
        if (this.sentinel.next == null) {
            throw new IllegalStateException("empty list");
        }

        IntNode ptr = this.sentinel;
        while (ptr.next != null) {
            ptr = ptr.next;
        }

        return ptr.value;
    }

    public int size() { return this.size; }

    /*
    Exercise 1: Write toString

    Add a public String toString() method to your SLList class. If you have a compelling reason to change the internal
    IntNode class, feel free to do so. You should know that it is possible to implement this without touching IntNode at
    all.
    */
    public String toString() {
        /*
         * since java `String` is immutable, `str = str + current.head` will creates a new string every time it gets
         * called --- so, I use string with buffer instead.
         */
        StringBuffer str_buff = new StringBuffer();
        IntNode current = this.sentinel.next;

        str_buff.append("[");
        while (current != null) {
            if (str_buff.length() > 1) { // ignore the first '['
                str_buff.append(", ");
            }
            str_buff.append(current.value);

            current = current.next;
        }
        str_buff.append("]");

        return str_buff.toString();
    }
}

/*
Exercise 2: Write removeFirst
Add a public void removeFirst() method to your SLList class. This method removes the element at the front of the list.
If the list is empty, it does nothing. How does it affect how we maintain size ?

Exercise 3: Write insert
Add a public void insert(int newValue, int k) method to your SLList class. The method insert(newValue, k) inserts
newValue into the list at position k. This means, for example, insert(x, 0) will insert x at the front of the list.
Because insert adds a number to the list, if the list's size was n prior, it will be n + 1 after insert.
*/
