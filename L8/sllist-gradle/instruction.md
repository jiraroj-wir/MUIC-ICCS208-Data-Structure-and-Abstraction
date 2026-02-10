# Practice Exercises
You will hand in a zip file of your whole Gradle project. Let's begin by creating an empty folder called sllist-gradle and putting in the necessary build.gradle, as well as making the project structure src/{main, test}/java, etc. When you hand it in, you will zip the whole sllist-gradle as sllist-gradle.zip.

Consider the following rudimentary implementation of SLList without sentinels.

``` java
public class SLList {
    private class IntNode {
        int value; // an int data item
        IntNode next; // ref to the next node

        public IntNode(int val, IntNode r) {
            this.value = val; this.next = r;
        }
    }

    private IntNode first;

    public SLList() { first = null; }


    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public int getFirst() {
        return first.value;
    }
}
```

Your tasks are as follows:

- Put this class in a proper location in your Gradle project. It should live in a file callled SLList.java.  

- Implement the following method in SLList:  

> a method public int get(int index) that returns the element at position index, where we adopt the convention that the front of the list has index 0.  

- If the list is empty, getFirst will throw an IndexOutOfBoundsException exception. Similarly, get(i) will throw an exception IndexOutOfBoundsException if the index requested is out of range..  

- Write tests to ensure all the methods work correctly as intended. Your test cases should thoroughly exercise various combinations of list usage. Run through the tests. Your tests should verify that the correct exception is thrown at the right moment. (Hint: Look up on the Internet "how to assert an exception in JUnit")  
