import java.util.function.*;

/*
You will hand in one file MaxDemo.java to Canvas. We will turn ideas from the lecture into complete code. As a reminder,
the lecture contained two (long-running) examples:

Example I: The User Provides A Comparator. This results in a version of maxIndex that takes in an explicit comparison
function. To test this, we applied it to the Cat class as instructed.
Example II: The User Provides A Default Comparator. This results in a version of maxIndex that takes an array of
HasIsLarger items. And to apply this on our Cat class, we needed to make sure the class implements HasIsLarger.

Your Task: The goal is to write methods maxValue that will take in a generic array T of the right kind and return the
largest value in that array. You will write two methods of the same name:
*/

interface HasIsLarger<T> {
    boolean isLarger(T other);
}

public class MaxDemo {
    static public class Cat implements HasIsLarger<Cat> {
        private String name;
        private int weight;
        private int age;

        public Cat(String name, int weight, int age) {
            this.name = name;
            this.weight = weight;
            this.age = age;
        }

        public String getName() { return this.name; }

        public int getWeight() { return this.weight; }

        public int getAge() { return this.age; }

        public boolean isLarger(Cat that) { return (this.weight > that.weight); }
    }

    // static boolean isLargerByWeight(Cat x, Cat y) { return (x.getWeight() > y.getWeight()); }

    /*
    The first method will use the user-provided comparator design (like Example I). In doing so, it is a good excuse to
    practice writing such a function using both the Java 8+ and pre Java 8 methods. (Hint: Your method should probably
    like like static<T> T maxValue(T[] items, BiFunction<...> isLarger), where you will fill in the ...)
    */
    static <T> T maxValue(T[] items, BiFunction<T, T, Boolean> isLarger) {
        if (items.length == 0) {
            return null;
        }

        T largest = items[0];

        for (int i = 1; i < items.length; i++) {
            if (isLarger.apply(items[i], largest)) {
                largest = items[i];
            }
        }

        return largest;
    }

    /*
    The second method will use the default comparator design (like Example II). (Hint: Your method should probably look
    like static <T extends ...> T maxValue(T[] items), where you will find in the ...).
    */
    static <T extends HasIsLarger<T>> T maxValue(T[] items) {
        if (items.length == 0) {
            return null;
        }

        T largest = items[0];

        for (int i = 1; i < items.length; i++) {
            if (items[i].isLarger(largest)) {
                largest = items[i];
            }
        }

        return largest;
    }

    // test
    public static void main(String[] args) {

        Cat[] cats = {new Cat("A", 4, 3), new Cat("B", 6, 9), new Cat("C", 5, 1)};

        Cat r1 = maxValue(cats, (a, b) -> a.getWeight() > b.getWeight());
        Cat r2 = maxValue(cats);

        assert r1.weight == 6;
        assert r2.weight == 6;
        assert r1 == r2;

        Integer[] nums = {3, 9, 2, 7};

        Integer n = maxValue(nums, (a, b) -> a > b);
        assert n == 9;

        Cat[] single = {new Cat("X", 1, 2)};
        assert maxValue(single).weight == 1;

        Cat[] equal = {new Cat("P", 5, 9), new Cat("Q", 5, 2)};
        assert maxValue(equal).weight == 5;
    }
}
