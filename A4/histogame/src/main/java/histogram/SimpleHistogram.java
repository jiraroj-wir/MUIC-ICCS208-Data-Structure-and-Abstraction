package histogram;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Set;

// TODO: Uncomment this and make sure to implement all the methods
public class SimpleHistogram<DT> implements Histogram<DT>, Iterable<DT> {
    private final LinkedHashMap<DT, Integer> map;
    private int totalCount;

    // constructs an empty histogram (with no information)
    public SimpleHistogram() {
        this.map = new LinkedHashMap<>();
        this.totalCount = 0;
    }

    // constructs a histogram from a list of items given by the parameter items
    public SimpleHistogram(DT items[]) {
        this();

        for (DT item : items) {
            this.totalCount++;
            if (map.containsKey(item)) {
                map.put(item, map.get(item) + 1);
                continue;
            }

            map.put(item, 1);
        }
    }

    // constructs a (new) histogram from an existing histogram, sharing nothing internally
    public SimpleHistogram(Histogram<DT> hist) {
        this();

        for (DT item : hist) {
            setCount(item, hist.getCount(item));
        }
    }

    @Override
    // Returns the total frequency count of all items in the domain combined.
    public int getTotalCount() {
        return this.totalCount;
    }

    @Override
    // Returns the frequency count of a given domain item. If invalid domain
    // item is given, return 0.
    public int getCount(DT item) {
        if (!this.map.containsKey(item)) {
            return 0;
        }

        return this.map.get(item);
    }

    @Override
    // Sets the frequecy count of a given domain item. If the  domain item
    // doesn't yet exist in the domain, this will also add it to the domain.
    public void setCount(DT item, int count) {
        if (this.map.containsKey(item)) {
            this.totalCount -= this.map.get(item);
        }
        this.map.put(item, count);

        this.totalCount += count;
    }

    @Override
    public Iterator<DT> iterator() {
        return new histIterator();
    }

    private class histIterator implements Iterator<DT> {
        /*
         * NOTE: linked hash map don't iterate so well, I shouldn't use it
         * I tried to mimic iterator implementation here by listing all the key into an array of object (tried DT[],
         * doesn't work), then just check the index as exiting indicator.
         */
        private final Object[] keys = map.keySet().toArray();
        private int index;

        @Override
        public boolean hasNext() {
            return index < keys.length;
        }

        @Override
        @SuppressWarnings("unchecked")
        public DT next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return (DT)keys[index++];
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (DT item : this) {
            str.append("(");
            str.append("" + item + ", " + this.map.get(item));
            str.append("), ");
        }

        int len = str.length();
        if (len >= 2) {
            str.delete(len - 2, len); // removes trailing ", "
        }

        str.append("}");

        return str.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object object) {
        if (this == object) { // V-address
            return true;
        }

        if (!(object instanceof SimpleHistogram<?>)) {
            return false;
        }

        SimpleHistogram<DT> obj = (SimpleHistogram<DT>)object; // type cast

        if (this.totalCount != obj.totalCount || this.map.size() != obj.map.size()) {
            return false;
        }

        Iterator<DT> itA = this.iterator();
        Iterator<DT> itB = obj.iterator();

        while (itA.hasNext() && itB.hasNext()) {
            DT a = itA.next();
            DT b = itB.next();

            if (!java.util.Objects.equals(a, b)) {
                return false;
            }

            if (!java.util.Objects.equals(this.map.get(a), obj.map.get(b))) {
                return false;
            }
        }

        return true;
    }
}
