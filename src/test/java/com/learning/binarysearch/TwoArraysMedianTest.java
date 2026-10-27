package com.learning.binarysearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TwoArraysMedianTest {

    @Test
    void testOddMergedLength() {
        // merged [1,2,3] → median = 2.0
        assertEquals(2.0, TwoArraysMedian.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    @Test
    void testEvenMergedLength() {
        // merged [1,2,3,4] → median = 2.5
        assertEquals(2.5, TwoArraysMedian.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    @Test
    void testLargerArrays() {
        // merged [1,2,3,4,5] → median = 3.0
        assertEquals(3.0, TwoArraysMedian.findMedianSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4}));
    }

    @Test
    void testNonOverlapping() {
        // merged [1,2,5,6] → median = (2+5)/2 = 3.5
        assertEquals(3.5, TwoArraysMedian.findMedianSortedArrays(new int[]{1, 2}, new int[]{5, 6}));
    }

    @Test
    void testSingleElements() {
        // merged [1,2] → median = 1.5
        assertEquals(1.5, TwoArraysMedian.findMedianSortedArrays(new int[]{1}, new int[]{2}));
    }
}
