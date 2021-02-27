import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String notAPalindromeString = "abc";
        String notAPalindromeString1 = "abcd";
        String isPalindromeString = "abba";
        String isPalindromeString1 = "aba";
        String isPalindromeString2 = "a";
        String isPalindromeString3 = "";
        //isNone a palineDrome assume yes
        //odd
        //even

        assertFalse(palindrome.isPalindrome(notAPalindromeString));
        assertFalse(palindrome.isPalindrome(notAPalindromeString1));
        assertTrue(palindrome.isPalindrome(isPalindromeString));
        assertTrue(palindrome.isPalindrome(isPalindromeString1));
        assertTrue(palindrome.isPalindrome(isPalindromeString2));
        assertTrue(palindrome.isPalindrome(isPalindromeString3));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        String notAPalindromeString = "abAB";
        String notAPalindromeString1 = "abC";
        String isPalindromeString = "abb";
        String isPalindromeString1 = "abcb";
        String isPalindromeString2 = "";
        String isPalindromeString3 = "a";
        String isPalindromeString4 = "%a&";

        OffByOne cc = new OffByOne();

        assertFalse(palindrome.isPalindrome(notAPalindromeString, cc));
        assertFalse(palindrome.isPalindrome(notAPalindromeString1, cc));
        assertTrue(palindrome.isPalindrome(isPalindromeString, cc));
        assertTrue(palindrome.isPalindrome(isPalindromeString1, cc));
        assertTrue(palindrome.isPalindrome(isPalindromeString2, cc));
        assertTrue(palindrome.isPalindrome(isPalindromeString3, cc));
        assertTrue(palindrome.isPalindrome(isPalindromeString4, cc));
    }

}