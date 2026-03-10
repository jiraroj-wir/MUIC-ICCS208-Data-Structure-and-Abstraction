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

// Write static <T> void insertionSort(T[] array, Comparator<T> cc) This should run in time in the worst case.

/*
Write static <T> void quickSort(T[] array, Comparator<T> cc) Implement the version that picks pivots at random. This
makes it a randomized algorithm and we expect it to run in time.Unless you're masochistic, it will make sense for your
code to internally create an ArrayList from the given array, sort the ArrayList, and copy the results back into the
initial array.
*/

// Write static <T> void mergeSort(T[] array, Comparator<T> cc) This should run in time in the worst case.

// (You may wish to write helper methods or helper classes.)
