package com.learning.slidingwindow;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Sliding windows over UTF-16 code units, matching Java char semantics. */
public final class LargestSubString {

    /** Returns the longest duplicate-free substring length in O(n) time and O(u) space. */
    public int lengthOfLongestSubstring(String input) {
        Objects.requireNonNull(input, "input");
        Set<Character> window = new HashSet<>();
        int left = 0;
        int longestLength = 0;
        for (int right = 0; right < input.length(); right++) {
            while (window.contains(input.charAt(right))) {
                window.remove(input.charAt(left++));
            }
            window.add(input.charAt(right));
            longestLength = Math.max(longestLength, right - left + 1);
        }
        return longestLength;
    }
}
