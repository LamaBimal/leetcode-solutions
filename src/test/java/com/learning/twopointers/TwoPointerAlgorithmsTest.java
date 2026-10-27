package com.learning.twopointers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoPointerAlgorithmsTest {
    @Test
    void pairSearchUsesDistinctIndicesAndAvoidsOverflow() {
        SumTwoNumber solver = new SumTwoNumber();
        assertTrue(solver.findTarget(new int[]{3, 5, 7, 8, 10}, 18));
        assertTrue(solver.findTarget(new int[]{2, 2}, 4));
        assertFalse(solver.findTarget(new int[]{2}, 4));
        assertFalse(solver.findTarget(new int[]{}, 0));
        assertFalse(solver.findTarget(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, -2));
        assertThrows(NullPointerException.class, () -> solver.findTarget(null, 0));
    }

    @Test
    void waterHandlesBasinsAndBoundaries() {
        TrappingWater solver = new TrappingWater();
        assertEquals(6, solver.calculateWaterAmount(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        assertEquals(9, solver.calculateWaterAmount(new int[]{4, 2, 0, 3, 2, 5}));
        assertEquals(0, solver.calculateWaterAmount(new int[]{}));
        assertEquals(0, solver.calculateWaterAmount(new int[]{1, 2, 3}));
        assertThrows(IllegalArgumentException.class, () -> solver.calculateWaterAmount(new int[]{-1}));
        assertThrows(ArithmeticException.class, () -> solver.calculateWaterAmount(
                new int[]{Integer.MAX_VALUE, 0, 0, Integer.MAX_VALUE}));
        assertThrows(NullPointerException.class, () -> solver.calculateWaterAmount(null));
    }
}
