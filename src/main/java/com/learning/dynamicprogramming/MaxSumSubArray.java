package com.learning.dynamicprogramming;

import java.util.Objects;

/** Kadane's algorithm for a nonempty contiguous subarray. */
public final class MaxSumSubArray {

    /**
     * Returns the maximum sum in O(n) time and O(1) space.
     * @throws IllegalArgumentException if the input is empty
     * @throws ArithmeticException if the result cannot fit in an int
     */
    public int calculateMaxSumArray(int[] array) {
        Objects.requireNonNull(array, "array");
        if (array.length == 0) {
            throw new IllegalArgumentException("array must contain at least one element");
        }
        long endingHere = array[0];
        long maximum = array[0];
        for (int index = 1; index < array.length; index++) {
            endingHere = Math.max(endingHere + array[index], array[index]);
            maximum = Math.max(maximum, endingHere);
        }
        return Math.toIntExact(maximum);
    }
}
