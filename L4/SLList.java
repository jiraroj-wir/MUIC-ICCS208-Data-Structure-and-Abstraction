public class SLList {

    private static class IntNode {
        int value;
        IntNode next;
    }

    private final IntNode sentinel;

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
    SLList() {
        this.sentinel = new IntNode();
        this.sentinel.next = null;
    }

    SLList(int x) {
        this.sentinel = new IntNode();
        this.sentinel.next = new IntNode();
        this.sentinel.next.value = x;
    }
}
/*
Exercise 1: Write toString
Add a public String toString() method to your SLList class. If you have a compelling reason to change the internal
IntNode class, feel free to do so. You should know that it is possible to implement this without touching IntNode at
all.

Exercise 2: Write removeFirst
Add a public void removeFirst() method to your SLList class. This method removes the element at the front of the list.
If the list is empty, it does nothing. How does it affect how we maintain size ?

Exercise 3: Write insert
Add a public void insert(int newValue, int k) method to your SLList class. The method insert(newValue, k) inserts
newValue into the list at position k. This means, for example, insert(x, 0) will insert x at the front of the list.
Because insert adds a number to the list, if the list's size was n prior, it will be n + 1 after insert.
*/
