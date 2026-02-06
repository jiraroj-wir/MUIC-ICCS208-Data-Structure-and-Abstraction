/* IMPORTANT:
 * Uncomment this file after you have implemented OffByOne
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne_true() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertTrue(offByOne.equalChars('y', 'z'));
        assertTrue(offByOne.equalChars('1', '0'));
    }

    @Test
    public void testOffByOne_false() {
        assertFalse(offByOne.equalChars('a', 'c'));
        assertFalse(offByOne.equalChars('a', 'z'));
        assertFalse(offByOne.equalChars('0', '2'));
    }

    @Test
    public void testOffByOne_same_char_false() {
        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('z', 'z'));
        assertFalse(offByOne.equalChars('0', '0'));
    }
}
// */
