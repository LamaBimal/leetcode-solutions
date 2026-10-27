package com.learning.matrixtraversal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FloodFillTest {

    private final FloodFill solution = new FloodFill();

    @Test
    void testBasicFloodFill() {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 0}};
        int[][] expected = {{2, 2, 2}, {2, 2, 0}, {2, 0, 0}};
        assertArrayEquals(expected, solution.floodFill(image, 1, 1, 2));
    }

    @Test
    void testSameColorNoChange() {
        // newColor == originalColor → return as-is
        int[][] image = {{0, 0, 0}, {0, 1, 1}};
        int[][] expected = {{0, 0, 0}, {0, 1, 1}};
        assertArrayEquals(expected, solution.floodFill(image, 1, 1, 1));
    }

    @Test
    void testSingleCell() {
        int[][] image = {{1}};
        assertArrayEquals(new int[][]{{9}}, solution.floodFill(image, 0, 0, 9));
    }

    @Test
    void testCornerStart() {
        int[][] image = {{1, 1}, {1, 0}};
        int[][] expected = {{3, 3}, {3, 0}};
        assertArrayEquals(expected, solution.floodFill(image, 0, 0, 3));
    }

    @Test
    void testDisconnectedRegion() {
        // only the connected 1s from (0,0) get filled
        int[][] image = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        int[][] expected = {{2, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        assertArrayEquals(expected, solution.floodFill(image, 0, 0, 2));
    }
}
