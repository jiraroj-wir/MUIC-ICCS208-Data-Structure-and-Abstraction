/*
Problem 3: Bounded Skipper

For integer k > 1, the bounded skipper sequence of length n is the sequence of the first n numbers such that each number
is neither divisible by k nor has k inside its decimal representation (e.g., 145223 has k = 52 inside). For example,
using k = 3, the bounded skipper sequence of length n = 11 is

1, 2, 4, 5, 7, 8, 10, 11, 14, 16, 17

(Notice that 3, 6, 9, 12, 15 are skipped because they are divisible by 3, and 13 is skipped because it contains 3.)

You will write a public class

public class BoundedSkipper implements Iterable<Integer>

with the constructor

public BoundedSkipper(int k, int n) {...}

so that the following code will work

for (int v: new BoundedSkipper(3, 11)) {
  System.out.println(v); // prints out the above sequence on separate lines
}

Since the class implements Iterable, it must have a public Iterator<Integer> iterator() method.

Ground Rules: You must not construct the entire sequence anywhere. It is expected that your iterator generates the next
number in the sequence on the fly. This means that you cannot, for example, make an ArrayList of the whole answer and
return/iterate over it. In fact, you cannot store more a constant amount of data anywhere in your class.
*/

import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoundedSkipper implements Iterable<Integer> {
    private int k;
    private int len;

    public BoundedSkipper(int k, int n) {
        this.k = k;
        this.len = n;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Integer> {
        private int iter;
        private int rem;

        MyIterator() {
            iter = 1;
            rem = len;
        }

        @Override
        public boolean hasNext() {
            return rem > 0;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            while (true) {
                if (!((iter % k != 0) && notContain(iter))) {
                    iter++;
                    continue;
                }

                iter++;
                rem--;
                return iter - 1;
            }
        }

        private boolean notContain(int n) {
            int num = n;
            int temp = num % pow10(length(k));
            num /= pow10(length(k));

            if (temp == k) {
                return false;
            }
            while (num > 0) {
                temp /= 10;
                temp *= 10;
                temp += num % 10;
                num /= 10;

                if (temp == k) {
                    return false;
                }
            }

            return true;
        }

        private int length(int n) {
            int count = 0;
            while (n > 0) {
                count++;
                n /= 10;
            }

            return count;
        }

        private int pow10(int n) {
            int product = 1;
            for (int _i = 0; _i < n; _i++) {
                product *= 10;
            }

            return product;
        }
    }

    public static void main(String[] args) {
        for (int v : new BoundedSkipper(3, 11)) {
            System.out.println(v); // prints out the above sequence on separate lines
        }
    }
}
