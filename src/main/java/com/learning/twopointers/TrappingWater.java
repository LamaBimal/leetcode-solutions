package com.learning.twopointers;

import java.util.Objects;

/** Two-pointer accumulation over nonnegative elevation heights. */
public final class TrappingWater {

    /**
     * Returns trapped water in O(n) time and O(1) space.
     * @throws IllegalArgumentException for negative heights
     * @throws ArithmeticException if the total cannot fit in an int
     */
    public int calculateWaterAmount(int[] elevation) {
        Objects.requireNonNull(elevation, "elevation");
        for (int height : elevation) {
            if (height < 0) {
                throw new IllegalArgumentException("Heights must be nonnegative");
            }
        }
        int left = 0;
        int right = elevation.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        long total = 0;
        while (left < right) {
            if (elevation[left] <= elevation[right]) {
                maxLeft = Math.max(maxLeft, elevation[left]);
                total += maxLeft - elevation[left++];
            } else {
                maxRight = Math.max(maxRight, elevation[right]);
                total += maxRight - elevation[right--];
            }
        }
        return Math.toIntExact(total);
    }
}
