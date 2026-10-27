package com.learning.prefixsum;

import com.learning.dynamicprogramming.MaxSumSubArray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrefixAlgorithmsTest {
    @Test
    void findsLongestTargetSumWithNegativesAndRepeatedPrefixes() {
        LongestSubArray solver = new LongestSubArray();
        assertEquals(4, solver.findLongestSubArrayWithTarget(new int[]{1, -1, 5, -2, 3}, 3));
        assertEquals(3, solver.findLongestSubArrayWithTarget(new int[]{0, 0, 0}, 0));
        assertEquals(0, solver.findLongestSubArrayWithTarget(new int[]{}, 0));
        assertEquals(0, solver.findLongestSubArrayWithTarget(new int[]{Integer.MAX_VALUE, 1}, Integer.MIN_VALUE));
        assertThrows(NullPointerException.class, () -> solver.findLongestSubArrayWithTarget(null, 0));
    }

    @Test
    void handlesAllNegativeMaximumSumAndOverflow() {
        MaxSumSubArray solver = new MaxSumSubArray();
        assertEquals(-2, solver.calculateMaxSumArray(new int[]{-2, -4}));
        assertEquals(11, solver.calculateMaxSumArray(new int[]{2, 3, -8, 7, -1, 2, 3}));
        assertEquals(5, solver.calculateMaxSumArray(new int[]{5}));
        assertThrows(IllegalArgumentException.class, () -> solver.calculateMaxSumArray(new int[]{}));
        assertThrows(ArithmeticException.class,
                () -> solver.calculateMaxSumArray(new int[]{Integer.MAX_VALUE, 1}));
        assertThrows(NullPointerException.class, () -> solver.calculateMaxSumArray(null));
    }

    @Test
    void productsHandleEmptySingletonNegativesAndZerosWithoutMutation() {
        ProductArray solver = new ProductArray();
        int[] input = {1, 2, 3, 4};
        assertArrayEquals(new int[]{24, 12, 8, 6}, solver.productExceptSelf(input));
        assertArrayEquals(new int[]{1, 2, 3, 4}, input);
        assertArrayEquals(new int[]{}, solver.productExceptSelf(new int[]{}));
        assertArrayEquals(new int[]{1}, solver.productExceptSelf(new int[]{0}));
        assertArrayEquals(new int[]{0, 0, 8, 0}, solver.productExceptSelf(new int[]{-1, 2, 0, -4}));
        assertArrayEquals(new int[]{0, 0, 0}, solver.productExceptSelf(new int[]{0, 0, Integer.MAX_VALUE}));
        assertArrayEquals(new int[]{2, Integer.MAX_VALUE},
                solver.productExceptSelf(new int[]{Integer.MAX_VALUE, 2}));
        assertThrows(ArithmeticException.class,
                () -> solver.productExceptSelf(new int[]{Integer.MAX_VALUE, 2, 2}));
        assertThrows(NullPointerException.class, () -> solver.productExceptSelf(null));
    }
}
