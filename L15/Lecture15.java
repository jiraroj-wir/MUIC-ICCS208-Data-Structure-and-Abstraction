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

    /*
    private static void quickSort(int[] arr) { quickSortHelper(arr, 0, arr.length - 1); }

    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivotIndex = partition(arr, low, high);

        quickSortHelper(arr, low, pivotIndex - 1);
        quickSortHelper(arr, pivotIndex + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        // int pivot = arr[high];
        int pivotIndex = low + new Random().nextInt(high - low + 1);
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        return i + 1;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    */
    static <T> void quickSort(T[] array, Comparator<T> cc) {
        ArrayList<T> list = new ArrayList<>(Arrays.asList(array));

        list = sortArrayList(list, cc); // sort

        // copy the result back into the initial array
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
    }

    // Write static <T> void mergeSort(T[] array, Comparator<T> cc) This should run in O(n logn) time in the worst
    // case.
    static <T> void mergeSort(T[] array, Comparator<T> cc) { mergeSortHelper(array, 0, array.length - 1, cc); }

    // (You may wish to write helper methods or helper classes.)

    // quick sort's sort
    static <T> ArrayList<T> sortArrayList(ArrayList<T> list, Comparator<T> cc) {
        if (list.size() <= 1) {
            return list;
        }

        Random rand = new Random();
        T pivot = list.get(rand.nextInt(list.size())); // get value of random index between [0,list.size)

        ArrayList<T> less = new ArrayList<>();
        ArrayList<T> equal = new ArrayList<>();
        ArrayList<T> greater = new ArrayList<>();

        for (T x : list) {
            int cmp = cc.compare(x, pivot);
            if (cmp < 0) {
                less.add(x);
            } else if (cmp > 0) {
                greater.add(x);
            } else {
                equal.add(x);
            }
        }

        ArrayList<T> result = new ArrayList<>();
        result.addAll(sortArrayList(less, cc));
        result.addAll(equal);
        result.addAll(sortArrayList(greater, cc));

        return result;
    }

    private static <T> void mergeSortHelper(T[] array, int left, int right, Comparator<T> cc) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSortHelper(array, left, mid, cc);
        mergeSortHelper(array, mid + 1, right, cc);

        merge(array, left, mid, right, cc);
    }

    private static <T> void merge(T[] array, int left, int mid, int right, Comparator<T> cc) {
        ArrayList<T> temp = new ArrayList<>();

        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (cc.compare(array[i], array[j]) <= 0) {
                temp.add(array[i++]);
            } else {
                temp.add(array[j++]);
            }
        }

        while (i <= mid) {
            temp.add(array[i++]);
        }

        while (j <= right) {
            temp.add(array[j++]);
        }

        for (int k = 0; k < temp.size(); k++) {
            array[left + k] = temp.get(k);
        }
    }

    /*
     * unused
     *
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    } */

    // test
    public static void main(String[] args) {
        Comparator<Integer> asc = Integer::compare;

        // insertion sort tests
        Integer[] ti1 = {1, 2, 3, 4, 5};
        insertionSort(ti1, asc);
        assert Arrays.equals(ti1, new Integer[] {1, 2, 3, 4, 5});

        Integer[] ti2 = {5, 4, 3, 2, 1};
        insertionSort(ti2, asc);
        assert Arrays.equals(ti2, new Integer[] {1, 2, 3, 4, 5});

        Integer[] ti3 = {3, 1, 4, 1, 5};
        insertionSort(ti3, asc);
        assert Arrays.equals(ti3, new Integer[] {1, 1, 3, 4, 5});

        Integer[] ti4 = {2, 2, 2, 1, 1};
        insertionSort(ti4, asc);
        assert Arrays.equals(ti4, new Integer[] {1, 1, 2, 2, 2});

        String[] ti5 = {"banana", "apple", "cherry"};
        insertionSort(ti5, String::compareTo);
        assert Arrays.equals(ti5, new String[] {"apple", "banana", "cherry"});

        // quick sort tests
        Integer[] tq1 = {1, 2, 3, 4, 5};
        quickSort(tq1, asc);
        assert Arrays.equals(tq1, new Integer[] {1, 2, 3, 4, 5});

        Integer[] tq2 = {5, 4, 3, 2, 1};
        quickSort(tq2, asc);
        assert Arrays.equals(tq2, new Integer[] {1, 2, 3, 4, 5});

        Integer[] tq3 = {3, 1, 4, 1, 5};
        quickSort(tq3, asc);
        assert Arrays.equals(tq3, new Integer[] {1, 1, 3, 4, 5});

        Integer[] tq4 = {2, 2, 2, 1, 1};
        quickSort(tq4, asc);
        assert Arrays.equals(tq4, new Integer[] {1, 1, 2, 2, 2});

        String[] tq5 = {"banana", "apple", "cherry"};
        quickSort(tq5, String::compareTo);
        assert Arrays.equals(tq5, new String[] {"apple", "banana", "cherry"});

        // merge sort tests
        Integer[] tm1 = {1, 2, 3, 4, 5};
        mergeSort(tm1, asc);
        assert Arrays.equals(tm1, new Integer[] {1, 2, 3, 4, 5});

        Integer[] tm2 = {5, 4, 3, 2, 1};
        mergeSort(tm2, asc);
        assert Arrays.equals(tm2, new Integer[] {1, 2, 3, 4, 5});

        Integer[] tm3 = {3, 1, 4, 1, 5};
        mergeSort(tm3, asc);
        assert Arrays.equals(tm3, new Integer[] {1, 1, 3, 4, 5});

        Integer[] tm4 = {2, 2, 2, 1, 1};
        mergeSort(tm4, asc);
        assert Arrays.equals(tm4, new Integer[] {1, 1, 2, 2, 2});

        String[] tm5 = {"banana", "apple", "cherry"};
        mergeSort(tm5, String::compareTo);
        assert Arrays.equals(tm5, new String[] {"apple", "banana", "cherry"});
    }
}
