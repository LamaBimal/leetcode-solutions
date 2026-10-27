package com.learning.monotonicstack;

import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Pattern132RegressionTest {
    private final Pattern132 solver = new Pattern132();

    @Test
    void handlesPatternsStrictInequalitiesAndBoundaries() {
        assertTrue(solver.contains132Pattern(new int[]{3, 1, 4, 2}));
        assertTrue(solver.contains132Pattern(new int[]{-1, 3, 2, 0}));
        assertTrue(solver.contains132Pattern(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0}));
        assertFalse(solver.contains132Pattern(new int[]{1, 2, 3, 4}));
        assertFalse(solver.contains132Pattern(new int[]{1, 2, 2}));
        assertFalse(solver.contains132Pattern(new int[]{}));
        assertThrows(NullPointerException.class, () -> solver.contains132Pattern(null));
    }

    @Test
    void monotonicStackMatchesIndependentBruteForceOracle() {
        Random random = new Random(132);
        for (int sample = 0; sample < 500; sample++) {
            int[] numbers = random.ints(random.nextInt(12), -5, 6).toArray();
            assertEquals(bruteForce(numbers), solver.contains132Pattern(numbers));
        }
    }

    private boolean bruteForce(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    if (numbers[i] < numbers[k] && numbers[k] < numbers[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
