package com.learning.question1;

/**
 * 132 Pattern
 *
 * Given an array, determine whether there exist indices i < j < k such that
 * nums[i] < nums[k] < nums[j].
 *
 * Brute-force O(n^3): check every triple.
 */
public class Pattern132 {

    /**
     * Returns true if a 132 pattern exists in the array.
     */
    public boolean find132pattern(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (nums[i] < nums[k] && nums[k] < nums[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Pattern132 solution = new Pattern132();
        int[] numArray = {-2, 3, 2, -1, 1, 0};
        System.out.println("Pattern found: " + solution.find132pattern(numArray));
    }
}
