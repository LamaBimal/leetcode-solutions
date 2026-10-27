package com.learning.binarysearch;

import java.util.Objects;

/** Binary partition search across two ascending sorted arrays. */
public final class TwoArraysMedian {

    /**
     * Returns the median in O(log(min(m,n) + 1)) time and O(1) space.
     * Inputs must be sorted; sortedness is a caller precondition.
     * @throws IllegalArgumentException if both arrays are empty
     */
    public static double findMedianSortedArrays(int[] first, int[] second) {
        Objects.requireNonNull(first, "first");
        Objects.requireNonNull(second, "second");
        if (first.length > second.length) {
            return findMedianSortedArrays(second, first);
        }
        if (second.length == 0) {
            throw new IllegalArgumentException("At least one array must be nonempty");
        }
        long totalLength = (long) first.length + second.length;
        int leftSize = (int) ((totalLength + 1) / 2);
        int low = 0;
        int high = first.length;
        while (low <= high) {
            int firstCut = low + (high - low) / 2;
            int secondCut = leftSize - firstCut;
            int firstLeft = firstCut == 0 ? Integer.MIN_VALUE : first[firstCut - 1];
            int firstRight = firstCut == first.length ? Integer.MAX_VALUE : first[firstCut];
            int secondLeft = secondCut == 0 ? Integer.MIN_VALUE : second[secondCut - 1];
            int secondRight = secondCut == second.length ? Integer.MAX_VALUE : second[secondCut];
            if (firstLeft <= secondRight && secondLeft <= firstRight) {
                int lowerMiddle = Math.max(firstLeft, secondLeft);
                if (totalLength % 2 == 1) {
                    return lowerMiddle;
                }
                return ((long) lowerMiddle + Math.min(firstRight, secondRight)) / 2.0;
            }
            if (firstLeft > secondRight) {
                high = firstCut - 1;
            } else {
                low = firstCut + 1;
            }
        }
        throw new IllegalArgumentException("Inputs must be sorted in ascending order");
    }
}
