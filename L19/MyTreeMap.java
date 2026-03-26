public class MyTreeMap<K extends Comparable<K>, V> {
    /*
    Save your work in MyTreeMap.java and upload it to Canvas when you're ready. For today, you will implement a public
    class MyTreeMap<K extends Comparable<K>, V> that implements the following methods by keeping a binary search tree
    ordered by the keys:

    - V get(K k) returns the value associated with the key k or null if the key k is not in the map.

    - void put(K k, V v) associates the key k with the value v.

    - K lowerKey(K k) returns the key that is the largest key strictly less than k. It will return null if no such keys
    exist.

    If the tree has height, each of your methods should run in time.
    NOTE: When you grow up (or if you're curious now), you'll learn how to keep the height bounded by at all times.
     */
    class Node {
        K key;
        V value;
        Node left, right;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    Node root;
}
