package com.learning.slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowAlgorithmsTest {
    @Test
    void longestSubstringHandlesShrinkingRepeatedCharactersAndEmptyInput() {
        LargestSubString solver = new LargestSubString();
        assertEquals(3, solver.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, solver.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, solver.lengthOfLongestSubstring("pwwkew"));
        assertEquals(2, solver.lengthOfLongestSubstring("abba"));
        assertEquals(0, solver.lengthOfLongestSubstring(""));
        assertThrows(NullPointerException.class, () -> solver.lengthOfLongestSubstring(null));
    }

    @Test
    void countsEveryDistinctCharacterWindow() {
        UniqueCharacterSubString solver = new UniqueCharacterSubString();
        assertEquals(1, solver.countGoodSubstrings("xyzzaz"));
        assertEquals(4, solver.countGoodSubstrings("aababcabc"));
        assertEquals(0, solver.countGoodSubstrings("ab"));
        assertEquals(0, solver.countGoodSubstrings(""));
        assertThrows(NullPointerException.class, () -> solver.countGoodSubstrings(null));
    }

    @Test
    void handlesFlipBudgetAndRejectsInvalidInputs() {
        MaxConsecutiveOnes solver = new MaxConsecutiveOnes();
        assertEquals(6, solver.findLongestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        assertEquals(2, solver.findLongestOnes(new int[]{0, 1, 1, 0}, 0));
        assertEquals(3, solver.findLongestOnes(new int[]{0, 0, 0}, 10));
        assertEquals(0, solver.findLongestOnes(new int[]{}, 0));
        assertThrows(IllegalArgumentException.class, () -> solver.findLongestOnes(new int[]{1}, -1));
        assertThrows(IllegalArgumentException.class, () -> solver.findLongestOnes(new int[]{2}, 1));
        assertThrows(NullPointerException.class, () -> solver.findLongestOnes(null, 1));
    }
}
