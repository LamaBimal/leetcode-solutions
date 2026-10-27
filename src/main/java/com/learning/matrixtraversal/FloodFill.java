package com.learning.matrixtraversal;

import java.util.Arrays;

/**
 * Matrix Traversal - Flood Fill
 *
 * Starting from a given cell, replace its color and all connected cells
 * of the same original color with a new color. (Like the paint bucket tool.)
 *
 * Input:
 *   image = [[1,1,1],
 *            [1,1,0],
 *            [1,0,0]]
 *   sr=1, sc=1, newColor=2
 *
 * Output:
 *   [[2,2,2],
 *    [2,2,0],
 *    [2,0,0]]
 *
 * All 1s connected to (1,1) are replaced with 2.
 */
public class FloodFill {

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * Paints the supplied rectangular image in place and returns that same image.
     * Starting coordinates must identify an existing cell. O(rows * columns)
     * time and worst-case recursion space; deep regions can exhaust the stack.
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        if (originalColor == newColor) {
            return image; // already the target color, nothing to do
        }
        dfs(image, sr, sc, originalColor, newColor);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int originalColor, int newColor) {
        // Out of bounds or not the original color → stop
        if (r < 0 || c < 0 || r >= image.length || c >= image[0].length) {
            return;
        }
        if (image[r][c] != originalColor) {
            return;
        }

        image[r][c] = newColor; // paint this cell

        for (int[] d : DIRECTIONS) {
            dfs(image, r + d[0], c + d[1], originalColor, newColor);
        }
    }

    public static void main(String[] args) {
        FloodFill solution = new FloodFill();

        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 0}
        };

        System.out.println("Before flood fill:");
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }

        int[][] result = solution.floodFill(image, 1, 1, 2);

        System.out.println("After flood fill (newColor=2):");
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
        // [2,2,2]
        // [2,2,0]
        // [2,0,0]
    }
}
