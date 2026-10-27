package com.learning.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Backtracking - Generate All Subsets
 *
 * Build every possible subset by choosing to include or skip each element.
 * At each step, add the current path to results, then try adding each
 * remaining element. After recursing, remove the last element (backtrack).
 *
 * Input:  nums = [1, 2, 3]
 * Output: [[], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]]
 *
 * Decision tree:
 *   []
 *   ├── [1]
 *   │   ├── [1,2]
 *   │   │   └── [1,2,3]
 *   │   └── [1,3]
 *   ├── [2]
 *   │   └── [2,3]
 *   └── [3]
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> path, int[] nums, int start) {
        result.add(new ArrayList<>(path)); // snapshot current path as a valid subset

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);           // choose
            backtrack(result, path, nums, i + 1); // explore
            path.remove(path.size() - 1); // un-choose (backtrack)
        }
    }

    public static void main(String[] args) {
        Subsets solution = new Subsets();

        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solution.subsets(nums);

        System.out.println("Input: [1, 2, 3]");
        System.out.println("All subsets (" + result.size() + " total):");
        for (List<Integer> subset : result) {
            System.out.println("  " + subset);
        }
        // [], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]
    }
}
