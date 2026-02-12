import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLList implements Iterable<Integer> {
    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Integer> {
        private IntNode current;

        MyIterator() { current = sentinel.next; }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Integer val = current.value;
            current = current.next;

            return val;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        SLList o = (SLList)other;

        if (this.size() != o.size()) {
            return false;
        }

        // for-each we just implemented
        /*
        while (this.iterator().hasNext()) {
            Integer val1 = this.iterator().next();
            Integer val2 = o.iterator().next();

            if (!val1.equals(val2)) {
                return false;
            }
        }
        */

        IntNode ptr1 = this.sentinel.next;
        IntNode ptr2 = o.sentinel.next;

        while (ptr1 != null) {
            if (!ptr1.value.equals(ptr2.value)) {
                return false;
            }

            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return true;
    }

    private static class IntNode {
        Integer value;
        IntNode next;

        IntNode() {}

        IntNode(Integer value, IntNode next) {
            this.value = value;
            this.next = next;
        }
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

    public SLList(Integer x) {
        this(); // calls SLList
        addFirst(x);
    }

    public void addFirst(Integer x) {
        IntNode new_first = new IntNode();
        new_first.value = x;
        new_first.next = sentinel.next;
        sentinel.next = new_first;
        size++;
    }

    public void addLast(Integer x) {
        IntNode ptr = this.sentinel;
        while (ptr.next != null) {
            ptr = ptr.next;
        }

        ptr.next = new IntNode();
        ptr.next.value = x;
        size++;
    }

    public Integer getFirst() {
        if (this.sentinel.next == null) {
            throw new IllegalStateException("empty list");
        }

        return this.sentinel.next.value;
    }

    public Integer getLast() {
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

    /*
    Exercise 2: Write removeFirst
    Add a public void removeFirst() method to your SLList class. This method removes the element at the front of the
    list. If the list is empty, it does nothing. How does it affect how we maintain size ?
    */
    public void removeFirst() {
        if (this.sentinel.next == null || this.size == 0) {
            return;
        }

        this.sentinel.next = this.sentinel.next.next;
        this.size--;

        /*
         * it doesn't affect our SLList's size maintainance much, just reduce it's size field by 1.
         */
    }

    /*
    Exercise 3: Write insert
    Add a public void insert(int newValue, int k) method to your SLList class. The method insert(newValue, k) inserts
    newValue into the list at position k. This means, for example, insert(x, 0) will insert x at the front of the list.
    Because insert adds a number to the list, if the list's size was n prior, it will be n + 1 after insert.
    */
    public void insert(Integer newValue, int k) {
        if (k <= 0) {
            this.addFirst(newValue);
            return;
        }
        if (k >= size) {
            this.addLast(newValue);
            return;
        }

        IntNode current = this.sentinel;
        for (int i = 0; i < k; i++) {
            current = current.next;
        }

        IntNode node = new IntNode();
        node.value = newValue;
        node.next = current.next;
        current.next = node;
        size++;
    }

    public static void main(String[] args) {
        SLList list = new SLList();
        assert list.size() == 0;
        assert "[]".equals(list.toString());

        list.addFirst(5);
        assert list.size() == 1;
        assert list.getFirst() == 5;
        assert list.getLast() == 5;
        assert "[5]".equals(list.toString());

        list.addLast(7);
        list.addLast(9);
        assert list.size() == 3;
        assert list.getFirst() == 5;
        assert list.getLast() == 9;
        assert "[5, 7, 9]".equals(list.toString());

        list.removeFirst(); // drops 5
        assert list.size() == 2;
        assert list.getFirst() == 7;
        assert "[7, 9]".equals(list.toString());

        list.removeFirst();
        list.removeFirst(); // now empty
        assert list.size() == 0;
        assert "[]".equals(list.toString());

        boolean threw = false;
        try {
            list.getFirst();
        } catch (IllegalStateException e) {
            threw = true;
        }
        assert threw;

        threw = false;
        try {
            list.getLast();
        } catch (IllegalStateException e) {
            threw = true;
        }
        assert threw;

        list.addLast(1);
        list.addLast(3);
        list.insert(0, 0);           // front
        list.insert(2, 2);           // middle
        list.insert(4, list.size()); // end
        assert "[0, 1, 2, 3, 4]".equals(list.toString());
        assert list.size() == 5;
    }
}
