package com.learning.prefixsum;

import java.util.Objects;

/** Products of all other elements, with zero support and checked int arithmetic. */
public final class ProductArray {

    /**
     * Returns a new array without division in O(n) time and O(1) auxiliary space
     * excluding output. Throws ArithmeticException if a required product overflows.
     */
    public int[] productExceptSelf(int[] array) {
        Objects.requireNonNull(array, "array");
        int[] result = new int[array.length];
        int zeros = 0;
        int zeroIndex = -1;
        for (int index = 0; index < array.length; index++) {
            if (array[index] == 0) {
                zeros++;
                zeroIndex = index;
            }
        }
        if (zeros > 1 || array.length == 0) {
            return result;
        }
        if (zeros == 1) {
            int product = 1;
            for (int value : array) {
                if (value != 0) {
                    product = Math.multiplyExact(product, value);
                }
            }
            result[zeroIndex] = product;
            return result;
        }
        int prefix = 1;
        for (int index = 0; index < array.length; index++) {
            result[index] = prefix;
            if (index < array.length - 1) {
                prefix = Math.multiplyExact(prefix, array[index]);
            }
        }
        int suffix = 1;
        for (int index = array.length - 1; index >= 0; index--) {
            result[index] = Math.multiplyExact(result[index], suffix);
            if (index > 0) {
                suffix = Math.multiplyExact(suffix, array[index]);
            }
        }
        return result;
    }

    /** @deprecated Use {@link #productExceptSelf(int[])}. */
    @Deprecated
    public int[] method1(int[] array) {
        return productExceptSelf(array);
    }

    /** @deprecated Use {@link #productExceptSelf(int[])}. */
    @Deprecated
    public int[] method2(int[] array) {
        return productExceptSelf(array);
    }
}
