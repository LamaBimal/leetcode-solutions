package com.learning.examples;

import java.util.Arrays;

import com.learning.binarysearch.TwoArraysMedian;
import com.learning.dynamicprogramming.ClimbingStairs;
import com.learning.monotonicstack.Pattern132;
import com.learning.prefixsum.ProductArray;
import com.learning.slidingwindow.LargestSubString;

/** Small runnable examples; library algorithms do not write to the console. */
public final class AlgorithmExamples {
    private AlgorithmExamples() {
    }

    public static void main(String[] args) {
        System.out.println("132 pattern: "
                + new Pattern132().contains132Pattern(new int[]{3, 1, 4, 2}));
        System.out.println("Longest unique substring: "
                + new LargestSubString().lengthOfLongestSubstring("abcabcbb"));
        System.out.println("Median: "
                + TwoArraysMedian.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println("Product except self: "
                + Arrays.toString(new ProductArray().productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println("Ways to climb five stairs: " + new ClimbingStairs().countWays(5));
    }
}
