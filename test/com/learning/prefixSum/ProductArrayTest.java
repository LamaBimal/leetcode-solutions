package com.learning.prefixSum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductArrayTest {

    private final ProductArray solution = new ProductArray();

    @Test
    void method1_basicExample() {
        // [1,2,3,4] → [24,12,8,6]
        assertArrayEquals(new int[]{24, 12, 8, 6}, solution.method1(new int[]{1, 2, 3, 4}));
    }

    @Test
    void method1_withNegatives() {
        // [-1,1,0,-3,3] → [0,-0,9,0,0]  (contains zero so most products are 0)
        int[] result = solution.method1(new int[]{-1, 1, 0, -3, 3});
        assertEquals(0, result[0]);
        assertEquals(0, result[2]); // product of all except the zero
    }

    @Test
    void method1_twoElements() {
        assertArrayEquals(new int[]{3, 2}, solution.method1(new int[]{2, 3}));
    }

    @Test
    void method1_fiveElements() {
        // [5,7,8,9,3] → each index is product of all others
        int[] input = {5, 7, 8, 9, 3};
        int[] result = solution.method1(input);
        assertEquals(7 * 8 * 9 * 3, result[0]);
        assertEquals(5 * 8 * 9 * 3, result[1]);
        assertEquals(5 * 7 * 9 * 3, result[2]);
        assertEquals(5 * 7 * 8 * 3, result[3]);
        assertEquals(5 * 7 * 8 * 9, result[4]);
    }

    @Test
    void method2_basicExample() {
        assertArrayEquals(new int[]{24, 12, 8, 6}, solution.method2(new int[]{1, 2, 3, 4}));
    }

    @Test
    void method1AndMethod2AgreeOnNonZeroInput() {
        int[] input = {2, 3, 4, 5};
        assertArrayEquals(solution.method1(input), solution.method2(input));
    }
}
