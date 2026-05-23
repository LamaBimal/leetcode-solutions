package com.learning.question1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pattern132Test {

    private final Pattern132 solution = new Pattern132();

    @Test
    void patternExistsInExample() {
        // -2 < 0 < 2  →  pattern at indices (0, 1, 4)
        assertTrue(solution.find132pattern(new int[]{-2, 3, 2, -1, 1, 0}));
    }

    @Test
    void classicExample_3_1_4_2() {
        // 1 < 2 < 4  →  pattern exists
        assertTrue(solution.find132pattern(new int[]{3, 1, 4, 2}));
    }

    @Test
    void noPatternInAscendingArray() {
        assertFalse(solution.find132pattern(new int[]{1, 2, 3, 4}));
    }

    @Test
    void noPatternInDescendingArray() {
        assertFalse(solution.find132pattern(new int[]{4, 3, 2, 1}));
    }

    @Test
    void tooShortArrayReturnsFalse() {
        assertFalse(solution.find132pattern(new int[]{1, 2}));
    }

    @Test
    void allSameElementsReturnsFalse() {
        assertFalse(solution.find132pattern(new int[]{5, 5, 5, 5}));
    }
}
