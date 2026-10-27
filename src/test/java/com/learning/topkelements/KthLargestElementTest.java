package com.learning.topkelements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KthLargestElementTest {

    @Test
    void rejectsInvalidRanksAndEmptyInputs() {
        KthLargestElement solver = new KthLargestElement();
        assertThrows(IllegalArgumentException.class, () -> solver.findKthLargest(new int[]{1}, 0));
        assertThrows(IllegalArgumentException.class, () -> solver.findKthLargest(new int[]{1}, 2));
        assertThrows(IllegalArgumentException.class, () -> solver.findKthLargest(new int[]{}, 1));
        assertThrows(NullPointerException.class, () -> solver.findKthLargest(null, 1));
    }

    private final KthLargestElement solution = new KthLargestElement();

    @Test
    void testThirdLargest() {
        assertEquals(5, solution.findKthLargest(new int[]{3, 1, 5, 12, 2, 11}, 3));
    }

    @Test
    void testFirstLargest() {
        assertEquals(12, solution.findKthLargest(new int[]{3, 1, 5, 12, 2, 11}, 1));
    }

    @Test
    void testLastLargest() {
        assertEquals(1, solution.findKthLargest(new int[]{3, 1, 5, 12, 2, 11}, 6));
    }

    @Test
    void testSingleElement() {
        assertEquals(7, solution.findKthLargest(new int[]{7}, 1));
    }

    @Test
    void testWithDuplicates() {
        // sorted desc: [3,3,2,1] → 2nd largest = 3
        assertEquals(3, solution.findKthLargest(new int[]{3, 2, 3, 1}, 2));
    }
}
