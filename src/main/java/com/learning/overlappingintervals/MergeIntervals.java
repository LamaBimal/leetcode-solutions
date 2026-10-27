package com.learning.overlappingintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Overlapping Intervals - Merge Intervals
 *
 * Sort intervals by start time, then merge any that overlap.
 *
 * Input:  [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 *
 * Explanation:
 *   [1,3] and [2,6] overlap (2 <= 3) → merge to [1,6]
 *   [8,10] does not overlap with [1,6] → keep separate
 *   [15,18] does not overlap with [8,10] → keep separate
 */
public class MergeIntervals {

    /**
     * Returns newly allocated merged intervals without modifying the input.
     * Runs in O(n log n) time and O(n) auxiliary space.
     * Empty input returns an empty array; each interval must contain start <= end.
     */
    public int[][] merge(int[][] intervals) {
        Objects.requireNonNull(intervals, "intervals");
        if (intervals.length == 0) {
            return new int[0][];
        }
        int[][] sorted = new int[intervals.length][2];
        for (int index = 0; index < intervals.length; index++) {
            int[] interval = Objects.requireNonNull(intervals[index], "interval");
            if (interval.length != 2 || interval[0] > interval[1]) {
                throw new IllegalArgumentException("Each interval must contain start <= end");
            }
            sorted[index] = interval.clone();
        }
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(sorted[0]);

        for (int i = 1; i < sorted.length; i++) {
            int[] last = merged.get(merged.size() - 1);
            int[] current = sorted[i];

            if (current[0] <= last[1]) {
                // Overlapping: extend the end if needed
                last[1] = Math.max(last[1], current[1]);
            } else {
                // No overlap: add as new interval
                merged.add(current);
            }
        }

        return merged.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();

        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = solution.merge(intervals);

        System.out.println("Input:  [[1,3],[2,6],[8,10],[15,18]]");
        System.out.print("Output: ");
        for (int[] interval : result) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println(); // [[1,6],[8,10],[15,18]]
    }
}
