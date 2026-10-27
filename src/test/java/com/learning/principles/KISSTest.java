package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KISSTest {

    private final KISS kiss = new KISS();

    @Test
    void isEvenReturnsTrueForEven() {
        assertTrue(kiss.isEven(4));
    }

    @Test
    void isEvenReturnsFalseForOdd() {
        assertFalse(kiss.isEven(7));
    }

    @Test
    void maxReturnsLargestElement() {
        assertEquals(9, kiss.max(new int[]{3, 9, 1, 5}));
    }
}
