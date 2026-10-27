package com.learning.overlappingintervals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeIntervalsTest {

    @Test
    void handlesExtremeEndpointsWithoutComparatorOverflow() {
        assertArrayEquals(new int[][]{{Integer.MIN_VALUE, -1}, {0, 1}},
                new MergeIntervals().merge(new int[][]{{Integer.MIN_VALUE, -1}, {0, 1}}));
    }

    @Test
    void preservesInputsAndReturnsIndependentOutput() {
        int[][] input = {{2, 6}, {1, 3}};
        int[][] result = new MergeIntervals().merge(input);
        assertArrayEquals(new int[][]{{2, 6}, {1, 3}}, input);
        result[0][1] = 99;
        assertArrayEquals(new int[][]{{2, 6}, {1, 3}}, input);
    }

    @Test
    void handlesEmptyInputAndRejectsMalformedIntervals() {
        MergeIntervals solver = new MergeIntervals();
        assertEquals(0, solver.merge(new int[0][]).length);
        assertThrows(NullPointerException.class, () -> solver.merge(null));
        assertThrows(IllegalArgumentException.class, () -> solver.merge(new int[][]{{3, 1}}));
        assertThrows(IllegalArgumentException.class, () -> solver.merge(new int[][]{{1}}));
    }

    private final MergeIntervals solution = new MergeIntervals();

    @Test
    void testBasicMerge() {
        int[][] result = solution.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        assertArrayEquals(new int[][]{{1, 6}, {8, 10}, {15, 18}}, result);
    }

    @Test
    void testOverlappingAtEdge() {
        // [1,4] and [4,5] share endpoint → merge to [1,5]
        int[][] result = solution.merge(new int[][]{{1, 4}, {4, 5}});
        assertArrayEquals(new int[][]{{1, 5}}, result);
    }

    @Test
    void testNoOverlap() {
        int[][] result = solution.merge(new int[][]{{1, 2}, {3, 4}, {5, 6}});
        assertArrayEquals(new int[][]{{1, 2}, {3, 4}, {5, 6}}, result);
    }

    @Test
    void testAllMergeIntoOne() {
        int[][] result = solution.merge(new int[][]{{1, 10}, {2, 6}, {3, 8}});
        assertArrayEquals(new int[][]{{1, 10}}, result);
    }

    @Test
    void testSingleInterval() {
        int[][] result = solution.merge(new int[][]{{1, 5}});
        assertArrayEquals(new int[][]{{1, 5}}, result);
    }
}
