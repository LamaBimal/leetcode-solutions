package com.learning.slidingwindow;

import java.util.Objects;

/** Counts distinct-character windows using UTF-16 code units. */
public final class UniqueCharacterSubString {

    /** Counts duplicate-free length-three substrings in O(n) time and O(1) space. */
    public int countGoodSubstrings(String input) {
        Objects.requireNonNull(input, "input");
        int count = 0;
        for (int left = 0; left + 2 < input.length(); left++) {
            char first = input.charAt(left);
            char second = input.charAt(left + 1);
            char third = input.charAt(left + 2);
            if (first != second && first != third && second != third) {
                count++;
            }
        }
        return count;
    }
}
