package com.learning.prefixsum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestSubArrayTest {

    private final LongestSubArray solution = new LongestSubArray();

    @Test
    void testBasicCase() {
        // [1,5,2,0] sums to 8 and includes the trailing zero.
        assertEquals(4, solution.findLongestSubArrayWithTarget(new int[]{1, 5, 2, 0, 8, 10}, 8));
    }

    @Test
    void testEntireArrayIsTarget() {
        // [1,2,3] sums to 6
        assertEquals(3, solution.findLongestSubArrayWithTarget(new int[]{1, 2, 3}, 6));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.findLongestSubArrayWithTarget(new int[]{5}, 5));
    }

    @Test
    void testNoSubArrayFound() {
        assertEquals(0, solution.findLongestSubArrayWithTarget(new int[]{1, 2, 3}, 100));
    }

    @Test
    void testWithZeros() {
        // [1,0,0,3] sums to 4, length 4
        assertEquals(4, solution.findLongestSubArrayWithTarget(new int[]{1, 0, 0, 3}, 4));
    }
}
