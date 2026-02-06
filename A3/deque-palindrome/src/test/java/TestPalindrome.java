/**
 * IMPORTANT: Uncomment this file after you're done with the Deque interface and wordToDeque
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome_true() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("aa"));
        assertTrue(palindrome.isPalindrome("racecar"));
    }

    @Test
    public void testIsPalindrome_false() {
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("orca"));
        assertFalse(palindrome.isPalindrome("aA"));
    }

    @Test
    public void testIsPalindrome_even_and_odd_lengths() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("level"));
        assertFalse(palindrome.isPalindrome("levels"));
        assertFalse(palindrome.isPalindrome("abc"));
    }
}
// */
