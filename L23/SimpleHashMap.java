import java.util.*;

public static interface Map<K, V> {
    // retrieve the value associated iwht a key key
    V get(K key);

    // associate value val with key key
    void put(K key, V val);

    // does the map have this key?
    boolean containsKey(K key);

    // remove an entry for this key
    void remove(K key);

    // the number of entries
    int size();

    // is it empty
    boolean isEmpty();
}

class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next;

    Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

public class SimpleHashMap<K, V> implements Map<K, V> {

    private Entry<K, V>[] table;
    private int size;
    private int capacity;

    public SimpleHashMap() {
        this.capacity = 16;
        this.table = (Entry<K, V>[]) new Entry[capacity];
        this.size = 0;
    }
}
