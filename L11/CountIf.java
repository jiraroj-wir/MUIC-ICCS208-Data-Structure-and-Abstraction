/*
Problem 2: Count If
Consider the following Predicate interface:

public interface Predicate<T> {
    boolean test(T x);
}

To begin you'll need to make a file called Predicate.java and put this code in there.

A predicate p, expressed using the Predicate interface, represents a higher-order function that takes in a type T
parameter and returns a boolean (i.e., true or false). This is similar to other higher-order function (HoF) code we have
done in the past. More specifically, the interface has a public method test and to test if an element x satisfies the
predicate, we will look at the outcome of p.test(x).

You are to implement a public class CountIf<T> with the following specifications:

There is the constructor CountIf(Predicate<T> pred). This is the predicate that will be used throughout by this
instance. There is a public method int count(T[] items) that returns the number of items in the input array for which
the predicate (given in the constructor above) returns true. Here is an example usage of CountIf class.

private static class isEven implements Predicate<Integer> {
    @Override
    public boolean test(Integer x) {
        return x%2 == 0;
    }
}

public static void main(String[] args) {
    CountIf<Integer> countEven = new CountIf<>(new isEven());
    Integer[] numbers = new Integer[]{1,2,3,4,5};
    System.out.println(countEven.count(numbers));
}
*/

public class CountIf<T> {
    private Predicate<T> pred;

    CountIf(Predicate<T> pred) { this.pred = pred; }

    public int count(T[] items) {
        int cnt = 0;

        for (int i = 0; i < items.length; i++) {
            if (this.pred.test(items[i])) {
                cnt++;
            }
        }

        return cnt;
    }

    private static class isEven implements Predicate<Integer> {
        @Override
        public boolean test(Integer x) {
            return x % 2 == 0;
        }
    }

    public static void main(String[] args) {
        CountIf<Integer> countEven = new CountIf<>(new isEven());
        Integer[] numbers = new Integer[] {1, 2, 3, 4, 5};
        System.out.println(countEven.count(numbers));
    }
}
