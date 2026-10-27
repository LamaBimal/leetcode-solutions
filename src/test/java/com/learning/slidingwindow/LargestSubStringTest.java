package com.learning.slidingwindow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LargestSubStringTest {

    private final LargestSubString solution = new LargestSubString();

    @Test
    void testExample1() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb")); // "abc"
    }

    @Test
    void testAllSameChars() {
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb")); // "b"
    }

    @Test
    void testExample3() {
        assertEquals(3, solution.lengthOfLongestSubstring("pwwkew")); // "wke"
    }

    @Test
    void testEmptyString() {
        assertEquals(0, solution.lengthOfLongestSubstring(""));
    }

    @Test
    void testAllUnique() {
        assertEquals(5, solution.lengthOfLongestSubstring("abcde"));
    }
}
