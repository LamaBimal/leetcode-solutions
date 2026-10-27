package com.learning.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Monotonic Stack - Next Greater Element
 *
 * For each element, find the next element to its right that is greater.
 * If none exists, output -1.
 *
 * Input:  [2, 1, 5, 3, 6]
 * Output: [5, 5, 6, 6, -1]
 *
 * Explanation:
 *   2 -> next greater is 5
 *   1 -> next greater is 5
 *   5 -> next greater is 6
 *   3 -> next greater is 6
 *   6 -> no greater element -> -1
 */
public class NextGreaterElement {

    public int[] nextGreaterElement(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1); // default: no greater element found

        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = 0; i < nums.length; i++) {
            // While current element is greater than element at stack top,
            // the current element is the "next greater" for the stacked index
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        NextGreaterElement solution = new NextGreaterElement();

        int[] nums = {2, 1, 5, 3, 6};
        int[] result = solution.nextGreaterElement(nums);

        System.out.println("Input:  " + Arrays.toString(nums));
        System.out.println("Output: " + Arrays.toString(result)); // [5, 5, 6, 6, -1]
    }
}
