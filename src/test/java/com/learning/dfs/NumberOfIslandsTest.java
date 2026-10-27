package com.learning.dfs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberOfIslandsTest {

    private final NumberOfIslands solution = new NumberOfIslands();

    @Test
    void testTwoIslands() {
        char[][] grid = {
            {'1', '1', '0', '0'},
            {'1', '0', '0', '1'},
            {'0', '0', '0', '1'},
            {'0', '0', '0', '0'}
        };
        assertEquals(2, solution.numIslands(grid));
    }

    @Test
    void testOneIsland() {
        char[][] grid = {
            {'1', '1', '1'},
            {'1', '1', '0'},
            {'0', '0', '0'}
        };
        assertEquals(1, solution.numIslands(grid));
    }

    @Test
    void testAllWater() {
        char[][] grid = {
            {'0', '0'},
            {'0', '0'}
        };
        assertEquals(0, solution.numIslands(grid));
    }

    @Test
    void testAllLand() {
        char[][] grid = {
            {'1', '1'},
            {'1', '1'}
        };
        assertEquals(1, solution.numIslands(grid));
    }

    @Test
    void testDiagonalIslands() {
        // diagonals are NOT connected, each '1' is its own island
        char[][] grid = {
            {'1', '0', '1'},
            {'0', '1', '0'},
            {'1', '0', '1'}
        };
        assertEquals(5, solution.numIslands(grid));
    }
}
