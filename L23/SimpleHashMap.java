import java.util.*;

interface Map<K, V> {
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

    @SuppressWarnings("unchecked")
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

    private int hash(K key) {
        int hash = (key == null) ? 0 : key.hashCode();
        return (hash & 0x7fffffff) % capacity;
    }

    public V get(K key) {
        Entry<K, V> current = this.table[hash(key)];

        while (current != null) {
            if (key.equals(current.key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    public int size() { return this.size; }

    public boolean isEmpty() { return (this.size() == 0); }
}
