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

    V get(K k) {
        Node current = this.root;
        while (current != null) {
            int cmp = current.key.compareTo(k);

            if (cmp == 0) {
                return current.value;
            } else if (cmp > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    void put(K k, V v) {
        if (this.root == null) {
            this.root = new Node(k, v);
            return;
        }

        Node current = this.root;
        Node parent = null;
        while (current != null) {
            parent = current;
            int cmp = current.key.compareTo(k);

            if (cmp == 0) {
                current.value = v;
                return;
            } else if (cmp > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // `current = null` when the loop terminates
        if (parent.key.compareTo(k) > 0) {
            parent.left = new Node(k, v);
        } else {
            parent.right = new Node(k, v);
        }
    }

    // tests
    public static void main(String[] args) {
        // get
        MyTreeMap<Integer, String> mapG = new MyTreeMap<>();

        assert mapG.get(1) == null;

        mapG.root = mapG.new Node(5, "a");
        mapG.root.left = mapG.new Node(3, "b");
        mapG.root.right = mapG.new Node(7, "c");

        assert "a".equals(mapG.get(5));
        assert "b".equals(mapG.get(3));
        assert "c".equals(mapG.get(7));

        // put
        MyTreeMap<Integer, String> mapP = new MyTreeMap<>();

        mapP.put(5, "a");
        assert "a".equals(mapP.root.value);

        mapP.put(3, "b");
        mapP.put(7, "c");
        assert "b".equals(mapP.root.left.value);
        assert "c".equals(mapP.root.right.value);

        mapP.put(5, "updated");
        assert "updated".equals(mapP.root.value);
    }
}
