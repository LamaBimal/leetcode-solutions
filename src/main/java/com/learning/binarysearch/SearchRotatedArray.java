package com.learning.binarysearch;

/**
 * Modified Binary Search - Search in Rotated Sorted Array
 *
 * A sorted array has been rotated at some pivot. Find the target index.
 * Must run in O(log n).
 *
 * Input:  nums = [4, 5, 6, 7, 0, 1, 2], target = 0
 * Output: 4
 *
 * Input:  nums = [4, 5, 6, 7, 0, 1, 2], target = 3
 * Output: -1
 *
 * Key insight: at every mid, one half is always sorted.
 * Determine which half is sorted, then check if target falls in it.
 */
public class SearchRotatedArray {

    /** Requires distinct values in a rotated ascending array; empty input returns -1. */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target is in left half
                } else {
                    left = mid + 1;  // target is in right half
                }
            } else {
                // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // target is in right half
                } else {
                    right = mid - 1; // target is in left half
                }
            }
        }

        return -1; // not found
    }

    public static void main(String[] args) {
        SearchRotatedArray solution = new SearchRotatedArray();

        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        System.out.println("Array: [4, 5, 6, 7, 0, 1, 2]");
        System.out.println("Search 0: index " + solution.search(nums, 0)); // 4
        System.out.println("Search 6: index " + solution.search(nums, 6)); // 2
        System.out.println("Search 3: index " + solution.search(nums, 3)); // -1
    }
}
