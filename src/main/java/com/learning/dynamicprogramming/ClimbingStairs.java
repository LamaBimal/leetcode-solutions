package com.learning.dynamicprogramming;

/** Counts paths taking one or two stairs at a time; inputs are limited to 0..45. */
public final class ClimbingStairs {

    /** Returns the number of paths in O(n) time and O(1) auxiliary space. */
    public int countWays(int n) {
        validateSteps(n);
        int previous = 1;
        int current = 1;
        for (int step = 2; step <= n; step++) {
            int next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    /** Returns the same count using O(n) time and O(n) space for a learning table. */
    public int countWaysWithTabulation(int n) {
        validateSteps(n);
        int[] ways = new int[n + 1];
        ways[0] = 1;
        if (n > 0) {
            ways[1] = 1;
        }
        for (int step = 2; step <= n; step++) {
            ways[step] = ways[step - 1] + ways[step - 2];
        }
        return ways[n];
    }

    private void validateSteps(int n) {
        if (n < 0 || n > 45) {
            throw new IllegalArgumentException("Steps must be between 0 and 45 for an int result");
        }
    }
}
