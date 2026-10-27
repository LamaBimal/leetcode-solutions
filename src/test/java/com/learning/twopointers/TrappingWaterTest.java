package com.learning.twopointers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrappingWaterTest {

    private final TrappingWater solution = new TrappingWater();

    @Test
    void testBasicCase() {
        // classic: [0,1,0,2,1,0,1,3,2,1,2,1] = 6
        assertEquals(6, solution.calculateWaterAmount(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    @Test
    void testFlatSurface() {
        assertEquals(0, solution.calculateWaterAmount(new int[]{3, 3, 3}));
    }

    @Test
    void testDescending() {
        assertEquals(0, solution.calculateWaterAmount(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void testValley() {
        // [3,0,3] traps 3 units
        assertEquals(3, solution.calculateWaterAmount(new int[]{3, 0, 3}));
    }

    @Test
    void testCustomCase() {
        // [3,5,7,0,4]: only the 0 at index 3 traps water, bounded by 4 on right → 4 units
        assertEquals(4, solution.calculateWaterAmount(new int[]{3, 5, 7, 0, 4}));
    }
}
