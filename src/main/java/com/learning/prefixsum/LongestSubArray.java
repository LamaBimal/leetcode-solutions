package com.learning.prefixsum;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Finds target-sum subarrays, including arrays with negative values. */
public final class LongestSubArray {

    /** Returns the longest matching length, or zero; O(n) time and O(n) space. */
    public int findLongestSubArrayWithTarget(int[] array, int target) {
        Objects.requireNonNull(array, "array");
        Map<Long, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0L, -1);
        long prefixSum = 0;
        int longestLength = 0;
        for (int index = 0; index < array.length; index++) {
            prefixSum += array[index];
            Integer start = firstIndex.get(prefixSum - target);
            if (start != null) {
                longestLength = Math.max(longestLength, index - start);
            }
            firstIndex.putIfAbsent(prefixSum, index);
        }
        return longestLength;
    }
}
