package com.learning.binarysearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchRotatedArrayTest {

    private final SearchRotatedArray solution = new SearchRotatedArray();

    @Test
    void testFoundInRightHalf() {
        assertEquals(4, solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

    @Test
    void testFoundInLeftHalf() {
        assertEquals(2, solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 6));
    }

    @Test
    void testNotFound() {
        assertEquals(-1, solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    @Test
    void testSingleElement() {
        assertEquals(0, solution.search(new int[]{1}, 1));
        assertEquals(-1, solution.search(new int[]{1}, 2));
    }

    @Test
    void testNotRotated() {
        assertEquals(3, solution.search(new int[]{1, 2, 3, 4, 5}, 4));
    }
}
