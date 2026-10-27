package com.learning.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListAlgorithmsTest {
    private final ListNodeIntegerSum solver = new ListNodeIntegerSum();

    @Test
    void addsDifferentDigitsWithoutDummyHeadAndPreservesInputs() {
        ListNode first = nodes(2, 4, 3);
        ListNode second = nodes(5, 6, 4);
        ListNode result = solver.sumTwoNumbers(first, second);
        assertEquals(Arrays.asList(7, 0, 8), values(result));
        assertEquals(Arrays.asList(2, 4, 3), values(first));
        assertEquals(Arrays.asList(5, 6, 4), values(second));
        assertNotSame(first, result);
        assertNotSame(second, result);
    }

    @Test
    void handlesUnequalLengthsCarryAndZero() {
        assertEquals(Arrays.asList(0, 0, 0, 1),
                values(solver.sumTwoNumbers(nodes(9, 9, 9), nodes(1))));
        assertEquals(Arrays.asList(0), values(solver.sumTwoNumbers(null, null)));
        assertEquals(Arrays.asList(1, 2), values(solver.sumTwoNumbers(null, nodes(1, 2))));
        assertEquals(Arrays.asList(1, 2), values(solver.sumTwoNumbers(nodes(1, 2), null)));
    }

    @Test
    void supportsNumbersBeyondPrimitiveRange() {
        int[] digits = new int[100];
        Arrays.fill(digits, 9);
        List<Integer> result = values(solver.sumTwoNumbers(nodes(digits), nodes(1)));
        assertEquals(101, result.size());
        assertTrue(result.subList(0, 100).stream().allMatch(digit -> digit == 0));
        assertEquals(Integer.valueOf(1), result.get(100));
    }

    @Test
    void convertsZeroAndIntBoundaryWithoutTruncation() {
        assertEquals(Arrays.asList(0), IntegerSum.convertToList(0));
        assertEquals(Integer.valueOf(342),
                IntegerSum.generateNumber(new LinkedList<>(Arrays.asList(2, 4, 3))));
        assertEquals(Integer.valueOf(Integer.MAX_VALUE),
                IntegerSum.generateNumber(IntegerSum.convertToList(Integer.MAX_VALUE)));
        assertThrows(ArithmeticException.class,
                () -> IntegerSum.generateNumber(new LinkedList<>(Arrays.asList(8, 4, 6, 3, 8, 4, 7, 4, 1, 2))));
    }

    @Test
    void rejectsInvalidDigitsAndNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new ListNode(10));
        assertThrows(IllegalArgumentException.class, () -> new ListNode(-1));
        assertThrows(IllegalArgumentException.class, () -> IntegerSum.convertToList(-1));
        assertThrows(IllegalArgumentException.class,
                () -> IntegerSum.generateNumber(new LinkedList<>(Arrays.asList(10))));
        assertThrows(NullPointerException.class, () -> IntegerSum.generateNumber(null));
    }

    private ListNode nodes(int... digits) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int digit : digits) {
            tail.next = new ListNode(digit);
            tail = tail.next;
        }
        return dummy.next;
    }

    private List<Integer> values(ListNode node) {
        List<Integer> result = new ArrayList<>();
        while (node != null) {
            result.add(node.val);
            node = node.next;
        }
        return result;
    }
}
