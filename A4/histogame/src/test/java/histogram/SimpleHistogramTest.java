package histogram;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.Test;

/* TODO: Uncomment me when you're done implementing the class
 */
public class SimpleHistogramTest {

    @Test
    public void testHistogram() {
        Character[] target = {'a', 'b', 'c', 'a'};
        Histogram<Character> h = new SimpleHistogram<>(target);
        Iterator<Character> iter = h.iterator();
        int elemCount = 0;
        while (iter.hasNext()) {
            iter.next();
            elemCount++;
        }

        assertEquals(3, elemCount);
        assertEquals(2, h.getCount('a'));
        assertEquals(1, h.getCount('b'));
        assertEquals(1, h.getCount('c'));
        assertEquals(4, h.getTotalCount());
    }

    @Test
    public void toStringFormatsEntriesAndEqualsMatches() {
        Character[] chars = {'a', 'b', 'a', 'c'};
        SimpleHistogram<Character> first = new SimpleHistogram<>(chars);
        SimpleHistogram<Character> second = new SimpleHistogram<>(first);

        assertEquals("{(a, 2), (b, 1), (c, 1)}", first.toString());
        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
    }

    @Test
    public void equalsDetectsDifferentCountsAndDomain() {
        SimpleHistogram<Character> base = new SimpleHistogram<>(new Character[] {'x', 'y', 'x'});
        SimpleHistogram<Character> diffCount = new SimpleHistogram<>(new Character[] {'x', 'y', 'y'});
        SimpleHistogram<Character> diffDomain = new SimpleHistogram<>(new Character[] {'x', 'z', 'x'});

        assertFalse(base.equals(diffCount));
        assertFalse(base.equals(diffDomain));
        assertNotEquals(base.toString(), diffCount.toString());
        assertNotEquals(base.toString(), diffDomain.toString());
    }
}
