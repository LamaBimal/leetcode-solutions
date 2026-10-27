package com.learning.slidingwindow;

import java.util.Objects;

/** Finds the longest binary window containing at most the allowed number of zeros. */
public final class MaxConsecutiveOnes {

    /** Returns the maximum window length in O(n) time and O(1) space. */
    public int findLongestOnes(int[] numbers, int maxZeros) {
        Objects.requireNonNull(numbers, "numbers");
        if (maxZeros < 0) {
            throw new IllegalArgumentException("maxZeros must be nonnegative");
        }
        int left = 0;
        int zeros = 0;
        int longestLength = 0;
        for (int right = 0; right < numbers.length; right++) {
            if (numbers[right] != 0 && numbers[right] != 1) {
                throw new IllegalArgumentException("numbers must contain only 0 and 1");
            }
            if (numbers[right] == 0) {
                zeros++;
            }
            while (zeros > maxZeros) {
                if (numbers[left++] == 0) {
                    zeros--;
                }
            }
            longestLength = Math.max(longestLength, right - left + 1);
        }
        return longestLength;
    }
}
