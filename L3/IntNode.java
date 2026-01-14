public class IntNode {
    // instance variables
    int head;
    IntNode next;

    public IntNode(int head, IntNode next) {
        this.head = head;
        this.next = next;
    }

    public String toString() {
        if (this.next == null) {
            return "" + this.head;
        } else {
            return "" + this.head + ", " + this.next.toString();
        }
    }

    public int size() {
        if (this.next == null)
            return 1;
        else
            return 1 + this.next.size();
    }

    public int iterativeSize() {
        IntNode current = this;
        int totalSize = 0;

        while (current != null) {
            totalSize++;
            current = current.next;
        }

        return totalSize;
    }

    /*
    1. Iterative toString: Inside the class IntNode, implement a method public String iterativeToString() with the same
    specification as toString() (above) except that your method walks the list iteratively.
    */
    public String iterativeToString() {
        /*
         * since java `String` is immutable, `str = str + current.head` will creates a new string every time it gets
         * called --- so, I use string with buffer instead.
         */
        StringBuffer str_buff = new StringBuffer();
        IntNode current = this;

        while (current != null) {
            if (str_buff.length() > 0) {
                str_buff.append(", ");
            }
            str_buff.append(current.head);

            current = current.next;
        }

        return str_buff.toString();
    }

    /*
     2. get and set:
     The .get(int i) method returns the i-th item in the list (the front item has index is 0).

     The.set(int i, int newValue) method sets the i-th item in the list to a new value of newValue. For ease, assume
     that the index i is valid. If someone gives you an invalid index, you don't have to worry about it—your program can
     crash and burn in any way.
     */
    public int get(int i) {
        // your code goes here
        IntNode current = this;

        for (int _i = 0; _i < i; _i++) {
            current = current.next;
        }

        return current.head;
    }

    public void set(int i, int newValue) {
        // your code goes here
        IntNode current = this;

        for (int _i = 0; _i < i; _i++) {
            current = current.next;
        }

        current.head = newValue;
    }

    /*
     3. Increment List: Inside the class IntNode, implement a method `public IntNode incrList(int delta)` that returns a
     new IntNode identical to this list, but with all values incremented by delta. Specifically, your method returns a
     brand new list as specified, and the values in this original list won't change.
     */
    public IntNode incrList(int delta) {
        if (this.next == null)
            return new IntNode(this.head + delta, null);
        else {
            IntNode rest = this.next.incrList(delta);
            return new IntNode(this.head + delta, rest); // TODO: what to return? how to do this recursively?
        }
    }

    // do test
    public static void main(String[] args) {
        IntNode list = new IntNode(1, new IntNode(2, new IntNode(3, null)));

        assert "1, 2, 3".equals(list.toString());
        assert list.size() == 3;
        assert "1, 2, 3".equals(list.toString());
        assert "1, 2, 3".equals(list.iterativeToString());

        assert list.get(0) == 1;
        assert list.get(2) == 3;

        list.set(1, 4);
        assert "1, 4, 3".equals(list.iterativeToString());

        IntNode incremented = list.incrList(7);
        assert "8, 11, 10".equals(incremented.toString());
        assert "1, 4, 3".equals(list.iterativeToString());
    }
}
