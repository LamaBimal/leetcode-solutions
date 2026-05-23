package com.learning.prefixSum;

/**
 * Product of Array Except Self
 *
 * Given an integer array, return an array where each element is the product
 * of all elements except itself, without using division.
 *
 * Input:  [1, 2, 3, 4]
 * Output: [24, 12, 8, 6]
 */
public class ProductArray {

    /**
     * Method 1: Prefix × suffix pass — O(n) time, O(n) space.
     */
    public int[] method1(int[] array) {
        int n = array.length;
        int[] prefixProduct = new int[n];
        int[] suffixProduct = new int[n];
        int[] result = new int[n];

        prefixProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            prefixProduct[i] = array[i - 1] * prefixProduct[i - 1];
        }

        suffixProduct[n - 1] = 1;
        for (int j = n - 2; j >= 0; j--) {
            suffixProduct[j] = array[j + 1] * suffixProduct[j + 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = prefixProduct[i] * suffixProduct[i];
        }
        return result;
    }

    /**
     * Method 2: Total product divided per element — O(n) time.
     * Note: does not handle zeros correctly; kept for comparison purposes.
     */
    public int[] method2(int[] array) {
        int product = 1;
        for (int v : array) {
            if (v != 0) product *= v;
        }

        int[] productArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            productArray[i] = (array[i] != 0) ? product / array[i] : 0;
        }
        return productArray;
    }

    public static void main(String[] args) {
        int[] array = {5, 7, 8, 9, 3};
        ProductArray pa = new ProductArray();

        int[] r1 = pa.method1(array);
        int[] r2 = pa.method2(array);

        System.out.println("Method1 (prefix×suffix):");
        for (int i = 0; i < r1.length; i++) System.out.println("  index " + i + ": " + r1[i]);

        System.out.println("Method2 (total product):");
        for (int i = 0; i < r2.length; i++) System.out.println("  index " + i + ": " + r2[i]);
    }
}
