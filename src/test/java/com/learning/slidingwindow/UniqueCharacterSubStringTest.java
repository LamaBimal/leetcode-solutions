package com.learning.slidingwindow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UniqueCharacterSubStringTest {

    private final UniqueCharacterSubString solution = new UniqueCharacterSubString();

    @Test
    void testExample() {
        // "xyz"=good, "yzz"=bad, "zza"=bad, "zaz"=bad → 1
        assertEquals(1, solution.countGoodSubstrings("xyzzaz"));
    }

    @Test
    void testAllUnique() {
        // "abc","bcd","cde" → 3
        assertEquals(3, solution.countGoodSubstrings("abcde"));
    }

    @Test
    void testAllSame() {
        assertEquals(0, solution.countGoodSubstrings("aaaa"));
    }

    @Test
    void testMinLength() {
        // exactly 3 chars, all unique
        assertEquals(1, solution.countGoodSubstrings("abc"));
    }

    @Test
    void testCustomInput() {
        // "xyz","yza","zaz" → "xyz" and "yza" are good → 2
        assertEquals(2, solution.countGoodSubstrings("xyzaz"));
    }
}
