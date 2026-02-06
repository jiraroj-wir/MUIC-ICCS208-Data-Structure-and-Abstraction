/* IMPORTANT:
 * Uncomment this file after you have implemented OffByOne
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    // static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByFive() {
        CharacterComparator offBy5 = new OffByN(5);
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('f', 'a'));
        assertTrue(offBy5.equalChars('1', '6'));
        assertFalse(offBy5.equalChars('a', 'a'));
        assertFalse(offBy5.equalChars('a', 'z'));
    }

    @Test
    public void testOffByTwo() {
        CharacterComparator offBy2 = new OffByN(2);
        assertTrue(offBy2.equalChars('a', 'c'));
        assertTrue(offBy2.equalChars('c', 'a'));
        assertFalse(offBy2.equalChars('c', 'd'));
        assertFalse(offBy2.equalChars('0', '5'));
    }
}
