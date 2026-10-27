package com.learning.dfs;

/**
 * Depth-First Search (DFS) - Number of Islands
 *
 * Count the number of islands in a 2D grid.
 * An island is a group of connected '1's (land) surrounded by '0's (water).
 * DFS from each unvisited '1', marking all connected land as visited.
 *
 * Input:
 *   grid = [
 *     ['1','1','0','0'],
 *     ['1','0','0','1'],
 *     ['0','0','0','1'],
 *     ['0','0','0','0']
 *   ]
 * Output: 2
 *
 * Island 1: top-left cluster (3 cells)
 * Island 2: right-side cluster (2 cells)
 */
public class NumberOfIslands {

    /**
     * Counts islands in a rectangular grid, replacing visited land with water.
     * Null or empty input returns zero. O(rows * columns) time and worst-case
     * recursion space; large connected regions can exhaust the JVM stack.
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c); // sink the entire island
                    count++;
                }
            }
        }
        return count;
    }

    // Mark all connected land cells as visited by setting them to '0'
    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != '1') return;

        grid[r][c] = '0'; // mark visited

        dfs(grid, r + 1, c); // down
        dfs(grid, r - 1, c); // up
        dfs(grid, r, c + 1); // right
        dfs(grid, r, c - 1); // left
    }

    public static void main(String[] args) {
        NumberOfIslands solution = new NumberOfIslands();

        char[][] grid = {
            {'1', '1', '0', '0'},
            {'1', '0', '0', '1'},
            {'0', '0', '0', '1'},
            {'0', '0', '0', '0'}
        };

        System.out.println("Number of Islands: " + solution.numIslands(grid)); // 2
    }
}
