package com.learning.principles;

/**
 * KISS - Keep It Simple, Stupid
 *
 * Prefer the simplest solution that works. Avoid over-engineering.
 *
 * BAD: using a stream pipeline with multiple steps just to check if a number is even.
 * GOOD: a single, readable condition.
 */
public class KISS {

    // BAD: unnecessarily complex
    public boolean isEvenComplex(int n) {
        return java.util.stream.IntStream.of(n)
                .filter(x -> x % 2 == 0)
                .findFirst()
                .isPresent();
    }

    // GOOD: simple and obvious
    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    // BAD: manual loop to find max
    public int maxComplex(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    // GOOD: clear intent
    public int max(int[] nums) {
        return java.util.Arrays.stream(nums).max()
                .orElseThrow(() -> new java.util.NoSuchElementException("nums must not be empty"));
    }
}
