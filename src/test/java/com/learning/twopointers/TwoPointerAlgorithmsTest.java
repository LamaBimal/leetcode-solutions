package com.learning.twopointers;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoPointerAlgorithmsTest {
    @Test
    void pairSearchMatchesExhaustivePairsAndPreservesInput() {
        Random random = new Random(20261027L);
        SumTwoNumber solver = new SumTwoNumber();
        int[] boundaryValues = {Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 0, 1};
        for (int sample = 0; sample < 500; sample++) {
            int[] values = new int[random.nextInt(21)];
            for (int index = 0; index < values.length; index++) {
                values[index] = random.nextBoolean()
                        ? boundaryValues[random.nextInt(boundaryValues.length)] : random.nextInt(41) - 20;
            }
            Arrays.sort(values);
            int target = sample % 2 == 0
                    ? boundaryValues[random.nextInt(boundaryValues.length)] : random.nextInt(81) - 40;
            int[] original = values.clone();
            String context = "sample=" + sample + ", values=" + Arrays.toString(values) + ", target=" + target;
            assertEquals(hasPairByExhaustiveSearch(values, target), solver.findTarget(values, target), context);
            assertArrayEquals(original, values, context);
        }
    }

    private boolean hasPairByExhaustiveSearch(int[] values, int target) {
        for (int first = 0; first < values.length; first++) {
            for (int second = first + 1; second < values.length; second++) {
                if ((long) values[first] + values[second] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    void trappedWaterMatchesBoundaryScansAndPreservesInput() {
        Random random = new Random(20261028L);
        TrappingWater solver = new TrappingWater();
        for (int sample = 0; sample < 500; sample++) {
            int[] heights = random.ints(random.nextInt(25), 0, 21).toArray();
            int[] original = heights.clone();
            String context = "sample=" + sample + ", heights=" + Arrays.toString(heights);
            assertEquals(waterByBoundaryScans(heights), solver.calculateWaterAmount(heights), context);
            assertArrayEquals(original, heights, context);
        }
    }

    private int waterByBoundaryScans(int[] heights) {
        int total = 0;
        for (int index = 0; index < heights.length; index++) {
            int leftMaximum = heights[index];
            int rightMaximum = heights[index];
            for (int left = 0; left < index; left++) {
                leftMaximum = Math.max(leftMaximum, heights[left]);
            }
            for (int right = index + 1; right < heights.length; right++) {
                rightMaximum = Math.max(rightMaximum, heights[right]);
            }
            total += Math.min(leftMaximum, rightMaximum) - heights[index];
        }
        return total;
    }

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
