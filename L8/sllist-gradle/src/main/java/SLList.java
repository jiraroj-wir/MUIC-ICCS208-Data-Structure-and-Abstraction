public class SLList {
    private class IntNode {
        int value;    // an int data item
        IntNode next; // ref to the next node

        public IntNode(int val, IntNode r) {
            this.value = val;
            this.next = r;
        }
    }

    private IntNode first;

    public SLList() { first = null; }

    public void addFirst(int x) { first = new IntNode(x, first); }

    public int getFirst() { return first.value; }

    public int get(int index) {
        IntNode current = this.first;

        for (int i = 0; i < index; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException();
            }

            current = current.next;
        }

        return current.value;
    }
}
