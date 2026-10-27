package com.learning.twopointers;

import java.util.Objects;

/** Pair search over an array sorted in ascending order. */
public final class SumTwoNumber {

    /** Returns whether two different indices sum to target; O(n) time, O(1) space. */
    public boolean findTarget(int[] sortedArray, int target) {
        Objects.requireNonNull(sortedArray, "sortedArray");
        int left = 0;
        int right = sortedArray.length - 1;
        while (left < right) {
            long sum = (long) sortedArray[left] + sortedArray[right];
            if (sum == target) {
                return true;
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}
