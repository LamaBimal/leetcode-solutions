package com.learning.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

/** Conversion helpers for small reverse-order decimal digit lists. */
public final class IntegerSum {

    /**
     * Converts reverse-order digits to an int in O(n) time and O(1) space.
     * @throws IllegalArgumentException for digits outside 0..9
     * @throws ArithmeticException if the value does not fit in an int
     */
    public static Integer generateNumber(LinkedList<Integer> digits) {
        Objects.requireNonNull(digits, "digits");
        int number = 0;
        Iterator<Integer> iterator = digits.descendingIterator();
        while (iterator.hasNext()) {
            Integer digit = Objects.requireNonNull(iterator.next(), "digit");
            if (digit < 0 || digit > 9) {
                throw new IllegalArgumentException("Digits must be between 0 and 9");
            }
            number = Math.addExact(Math.multiplyExact(number, 10), digit);
        }
        return number;
    }

    /** Converts a nonnegative int to reverse-order digits, representing zero as [0]. */
    public static LinkedList<Integer> convertToList(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("number must be nonnegative");
        }
        LinkedList<Integer> digits = new LinkedList<>();
        do {
            digits.add(number % 10);
            number /= 10;
        } while (number > 0);
        return digits;
    }
}
