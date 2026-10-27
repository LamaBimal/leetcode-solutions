package com.learning.dynamicprogramming;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClimbingStairsTest {

    private final ClimbingStairs solution = new ClimbingStairs();

    @Test
    void testBaseCase0() {
        assertEquals(1, solution.countWays(0));
        assertEquals(1, solution.countWaysWithTabulation(0));
    }

    @Test
    void testBaseCase1() {
        assertEquals(1, solution.countWays(1));
        assertEquals(1, solution.countWaysWithTabulation(1));
    }

    @Test
    void testFiveStairs() {
        assertEquals(8, solution.countWays(5));
        assertEquals(8, solution.countWaysWithTabulation(5));
    }

    @Test
    void testTenStairs() {
        assertEquals(89, solution.countWays(10));
        assertEquals(89, solution.countWaysWithTabulation(10));
    }

    @Test
    void testBothMethodsAgree() {
        for (int n = 0; n <= 15; n++) {
            assertEquals(solution.countWays(n), solution.countWaysWithTabulation(n),
                "Mismatch at n=" + n);
        }
    }
}
