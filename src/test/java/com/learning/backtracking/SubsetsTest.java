package com.learning.backtracking;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SubsetsTest {

    private final Subsets solution = new Subsets();

    @Test
    void testSubsetCount() {
        assertEquals(8, solution.subsets(new int[]{1, 2, 3}).size());
    }

    @Test
    void testContainsEmptySubset() {
        assertTrue(solution.subsets(new int[]{1, 2, 3}).contains(Arrays.asList()));
    }

    @Test
    void testContainsFullSet() {
        assertTrue(solution.subsets(new int[]{1, 2, 3}).contains(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testSingleElement() {
        List<List<Integer>> result = solution.subsets(new int[]{5});
        assertEquals(2, result.size());
        assertTrue(result.contains(Arrays.asList()));
        assertTrue(result.contains(Arrays.asList(5)));
    }

    @Test
    void testTwoElements() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2});
        assertEquals(4, result.size());
        assertTrue(result.contains(Arrays.asList(1, 2)));
    }
}
