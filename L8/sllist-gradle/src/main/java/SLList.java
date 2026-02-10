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
}
