package histogram;

import java.util.Iterator;

// TODO: Uncomment this and make sure to implement all the methods
public class SimpleHistogram<DT> implements Histogram<DT>, Iterable<DT> {
    // constructs an empty histogram (with no information)
    // public SimpleHistogram() {}

    // constructs a histogram from a list of items given by the parameter items
    // public SimpleHistogram(DT items[]) {}

    // constructs a (new) histogram from an existing histogram, sharing nothing internally
    // public SimpleHistogram(Histogram<DT> hist) {}

    @Override
    // Returns the total frequency count of all items in the domain combined.
    public int getTotalCount();

    @Override
    // Returns the frequency count of a given domain item. If invalid domain
    // item is given, return 0.
    public int getCount(DT item);

    @Override
    // Sets the frequecy count of a given domain item. If the  domain item
    // doesn't yet exist in the domain, this will also add it to the domain.
    public void setCount(DT item, int count);
}
