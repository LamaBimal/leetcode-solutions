package com.learning.dynamicprogramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClimbingStairsRegressionTest {
    private final ClimbingStairs solver = new ClimbingStairs();

    @Test
    void countsBaseCasesAndKnownResults() {
        assertEquals(1, solver.countWays(0));
        assertEquals(1, solver.countWaysWithTabulation(0));
        assertEquals(1, solver.countWays(1));
        assertEquals(8, solver.countWays(5));
        assertEquals(1836311903, solver.countWays(45));
        for (int n = 0; n <= 45; n++) {
            assertEquals(solver.countWays(n), solver.countWaysWithTabulation(n));
        }
    }

    @Test
    void rejectsUnsupportedStepCounts() {
        for (int n : new int[]{-1, 46, Integer.MAX_VALUE}) {
            assertThrows(IllegalArgumentException.class, () -> solver.countWays(n));
            assertThrows(IllegalArgumentException.class, () -> solver.countWaysWithTabulation(n));
        }
    }
}
