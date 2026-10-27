package com.learning.twopointers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SumTwoNumberTest {

    private final SumTwoNumber solution = new SumTwoNumber();

    @Test
    void testTargetExists() {
        assertTrue(solution.findTarget(new int[]{3, 5, 7, 8, 10}, 18)); // 8+10
    }

    @Test
    void testTargetAtEnds() {
        assertTrue(solution.findTarget(new int[]{1, 4, 6, 9}, 10)); // 1+9
    }

    @Test
    void testTargetNotFound() {
        assertFalse(solution.findTarget(new int[]{3, 5, 7, 8, 10}, 6));
    }

    @Test
    void testMinimalArray() {
        assertTrue(solution.findTarget(new int[]{1, 2}, 3));
    }

    @Test
    void testNoPairSumMatchesTarget() {
        assertFalse(solution.findTarget(new int[]{1, 2, 3}, 10));
    }
}
