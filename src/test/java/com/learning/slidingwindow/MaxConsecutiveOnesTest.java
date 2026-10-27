package com.learning.slidingwindow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaxConsecutiveOnesTest {

    private final MaxConsecutiveOnes solution = new MaxConsecutiveOnes();

    @Test
    void testExample1() {
        assertEquals(6, solution.findLongestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    @Test
    void testExample2() {
        assertEquals(10, solution.findLongestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    @Test
    void testNoFlipsAllowed() {
        // k=0, longest run of 1s is 3
        assertEquals(3, solution.findLongestOnes(new int[]{1, 1, 1, 0, 1}, 0));
    }

    @Test
    void testAllOnes() {
        assertEquals(5, solution.findLongestOnes(new int[]{1, 1, 1, 1, 1}, 2));
    }

    @Test
    void testAllZeros() {
        // k=2, can flip 2 zeros → window of 2
        assertEquals(2, solution.findLongestOnes(new int[]{0, 0, 0, 0}, 2));
    }
}
