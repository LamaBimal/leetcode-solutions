package com.learning.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/** Detects indices i < j < k with nums[i] < nums[k] < nums[j]. */
public final class Pattern132 {

    /** Compatibility entry point matching the original LeetCode method name. */
    public boolean find132pattern(int[] numbers) {
        return contains132Pattern(numbers);
    }

    /** Returns whether a 132 pattern exists in O(n) time and O(n) space. */
    public boolean contains132Pattern(int[] numbers) {
        Objects.requireNonNull(numbers, "numbers");
        Deque<Integer> decreasing = new ArrayDeque<>();
        int middleValue = Integer.MIN_VALUE;
        for (int index = numbers.length - 1; index >= 0; index--) {
            if (numbers[index] < middleValue) {
                return true;
            }
            while (!decreasing.isEmpty() && numbers[index] > decreasing.peek()) {
                middleValue = decreasing.pop();
            }
            decreasing.push(numbers[index]);
        }
        return false;
    }
}
