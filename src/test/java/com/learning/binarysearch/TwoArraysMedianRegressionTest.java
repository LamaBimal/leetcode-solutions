package com.learning.binarysearch;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoArraysMedianRegressionTest {
    @Test
    void handlesOddEvenEmptySidesAndIntegerBoundaries() {
        assertEquals(2.0, TwoArraysMedian.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        assertEquals(2.5, TwoArraysMedian.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        assertEquals(2.0, TwoArraysMedian.findMedianSortedArrays(new int[]{}, new int[]{2}));
        assertEquals(2.0, TwoArraysMedian.findMedianSortedArrays(new int[]{2}, new int[]{}));
        assertEquals((double) Integer.MAX_VALUE, TwoArraysMedian.findMedianSortedArrays(
                new int[]{Integer.MAX_VALUE}, new int[]{Integer.MAX_VALUE}));
        assertEquals(-0.5, TwoArraysMedian.findMedianSortedArrays(
                new int[]{Integer.MIN_VALUE}, new int[]{Integer.MAX_VALUE}));
        assertThrows(IllegalArgumentException.class,
                () -> TwoArraysMedian.findMedianSortedArrays(new int[]{}, new int[]{}));
        assertThrows(NullPointerException.class, () -> TwoArraysMedian.findMedianSortedArrays(null, new int[]{1}));
    }

    @Test
    void partitionSearchMatchesIndependentSortingOracle() {
        Random random = new Random(42);
        for (int sample = 0; sample < 500; sample++) {
            int[] first = random.ints(random.nextInt(20), -100, 101).sorted().toArray();
            int[] second = random.ints(1 + random.nextInt(20), -100, 101).sorted().toArray();
            int[] merged = new int[first.length + second.length];
            System.arraycopy(first, 0, merged, 0, first.length);
            System.arraycopy(second, 0, merged, first.length, second.length);
            Arrays.sort(merged);
            int middle = merged.length / 2;
            double expected = merged.length % 2 == 1 ? merged[middle]
                    : ((long) merged[middle - 1] + merged[middle]) / 2.0;
            assertEquals(expected, TwoArraysMedian.findMedianSortedArrays(first, second));
        }
    }
}
