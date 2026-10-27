package com.learning.topkelements;

import java.util.PriorityQueue;
import java.util.Objects;

/**
 * Top K Elements - Kth Largest Element in an Array
 *
 * Use a min-heap of size K. As we process each number:
 *   - Add it to the heap
 *   - If heap size exceeds K, remove the smallest
 * At the end, the heap contains the K largest elements,
 * and the top (peek) is the Kth largest.
 *
 * Input:  nums = [3, 1, 5, 12, 2, 11], k = 3
 * Output: 5  (the 3rd largest element)
 *
 * Sorted descending: [12, 11, 5, 3, 2, 1] → 3rd largest = 5
 */
public class KthLargestElement {

    /** Returns the kth largest value in O(n log(k + 1)) time and O(k) space. */
    public int findKthLargest(int[] nums, int k) {
        Objects.requireNonNull(nums, "nums");
        if (k < 1 || k > nums.length) {
            throw new IllegalArgumentException("k must be between 1 and nums.length");
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // min-heap by default

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove the smallest, keeping only top K
            }
        }

        return minHeap.peek(); // top of min-heap = Kth largest
    }

    public static void main(String[] args) {
        KthLargestElement solution = new KthLargestElement();

        int[] nums = {3, 1, 5, 12, 2, 11};
        int k = 3;

        int result = solution.findKthLargest(nums, k);
        System.out.println("Array: [3, 1, 5, 12, 2, 11]");
        System.out.println("K = " + k);
        System.out.println("Kth Largest Element: " + result); // 5
    }
}
