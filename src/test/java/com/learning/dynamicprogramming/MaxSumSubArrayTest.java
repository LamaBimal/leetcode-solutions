package com.learning.dynamicprogramming;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaxSumSubArrayTest {

    private final MaxSumSubArray solution = new MaxSumSubArray();

    @Test
    void testMixedArray() {
        // [7,-1,2,3] = 11
        assertEquals(11, solution.calculateMaxSumArray(new int[]{2, 3, -8, 7, -1, 2, 3}));
    }

    @Test
    void testAllNegative() {
        // best single element is -2
        assertEquals(-2, solution.calculateMaxSumArray(new int[]{-2, -4}));
    }

    @Test
    void testAllPositive() {
        assertEquals(25, solution.calculateMaxSumArray(new int[]{5, 4, 1, 7, 8}));
    }

    @Test
    void testSingleElement() {
        assertEquals(7, solution.calculateMaxSumArray(new int[]{7}));
    }

    @Test
    void testLargeNegativeSpike() {
        // The full array sums to 16; the negative value does not end the best subarray.
        assertEquals(16, solution.calculateMaxSumArray(new int[]{5, -3, 7, -8, 15}));
    }
}
