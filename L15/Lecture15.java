import java.util.*;

/*
Practice Exercise: Sorting with Generic Comparators
Save your work as Lecture15.java and upload it to Canvas.

The Comparator<T> interface in Java defines how we can compare objects of type T. The interface expects the existence of
a method

int compare(T o1, T o2)

which compares o1 and o2 for order. More specifically:

if o1 < o2, then compare returns a negative value;
if o1 == o2, then compare returns 0 (this should be consistent with .equals);
if o1 > o2, then compare returns a positive value.

There are further technical details that you may wish to check out in the documentation here.

To further your understanding of the sorting algorithms discussed in class, you will implement the following static
methods:
*/

public class Lecture15<T> {

    // Write static <T> void insertionSort(T[] array, Comparator<T> cc) This should run in O(n^2) time in the worst
    // case.
    static <T> void insertionSort(T[] array, Comparator<T> cc) {
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            int j = i - 1;

            while (j >= 0 && cc.compare(temp, array[j]) < 0) { // sorted from greatest-lowest
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = temp;
        }
    }

    /*
    Write static <T> void quickSort(T[] array, Comparator<T> cc) Implement the version that picks pivots at random. This
    makes it a randomized algorithm and we expect it to run in O(n logn) time.Unless you're masochistic, it will make
    sense for your code to internally create an ArrayList from the given array, sort the ArrayList, and copy the results
    back into the initial array.
    */

    // Write static <T> void mergeSort(T[] array, Comparator<T> cc) This should run in O(n logn) time in the worst case.

    // (You may wish to write helper methods or helper classes.)
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // test
    public static void main(String[] args) {
        Comparator<Integer> asc = Integer::compare;

        Integer[] t1 = {1, 2, 3, 4, 5};
        insertionSort(t1, asc);
        assert Arrays.equals(t1, new Integer[] {1, 2, 3, 4, 5});

        Integer[] t2 = {5, 4, 3, 2, 1};
        insertionSort(t2, asc);
        assert Arrays.equals(t2, new Integer[] {1, 2, 3, 4, 5});

        Integer[] t3 = {3, 1, 4, 1, 5};
        insertionSort(t3, asc);
        assert Arrays.equals(t3, new Integer[] {1, 1, 3, 4, 5});

        Integer[] t4 = {2, 2, 2, 1, 1};
        insertionSort(t4, asc);
        assert Arrays.equals(t4, new Integer[] {1, 1, 2, 2, 2});

        String[] t5 = {"banana", "apple", "cherry"};
        insertionSort(t5, String::compareTo);
        assert Arrays.equals(t5, new String[] {"apple", "banana", "cherry"});
    }
}
