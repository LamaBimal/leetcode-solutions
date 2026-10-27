package com.learning.monotonicstack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NextGreaterElementTest {

    private final NextGreaterElement solution = new NextGreaterElement();

    @Test
    void testBasicCase() {
        assertArrayEquals(new int[]{5, 5, 6, 6, -1}, solution.nextGreaterElement(new int[]{2, 1, 5, 3, 6}));
    }

    @Test
    void testDescendingArray() {
        // no element has a next greater → all -1
        assertArrayEquals(new int[]{-1, -1, -1, -1}, solution.nextGreaterElement(new int[]{5, 4, 3, 2}));
    }

    @Test
    void testAscendingArray() {
        assertArrayEquals(new int[]{2, 3, 4, 5, -1}, solution.nextGreaterElement(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testSingleElement() {
        assertArrayEquals(new int[]{-1}, solution.nextGreaterElement(new int[]{7}));
    }

    @Test
    void testDuplicates() {
        // [2,2,3] → [3,3,-1]
        assertArrayEquals(new int[]{3, 3, -1}, solution.nextGreaterElement(new int[]{2, 2, 3}));
    }
}
