package com.learning.question3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListNodeIntegerSumTest {

    private final ListNodeIntegerSum solution = new ListNodeIntegerSum();

    /** Helper: build a linked list from digits (least significant first). */
    private ListNode build(int... digits) {
        ListNode head = new ListNode(digits[0]);
        ListNode cur = head;
        for (int i = 1; i < digits.length; i++) {
            cur.next = new ListNode(digits[i]);
            cur = cur.next;
        }
        return head;
    }

    /** Helper: collect result digits into an array for easy assertion. */
    private int[] toArray(ListNode node) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        ListNode cur = node.next; // skip dummy head
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    void addTwoNumbers_342_plus_465() {
        // [2,4,3] = 342, [5,6,4] = 465 → 807 → [7,0,8]
        ListNode l1 = build(2, 4, 3);
        ListNode l2 = build(5, 6, 4);
        int[] result = toArray(solution.sumTwoNumbers(l1, l2));
        assertArrayEquals(new int[]{7, 0, 8}, result);
    }

    @Test
    void addWithCarry() {
        // [9,9] = 99, [1] = 1 → 100 → [0,0,1]
        ListNode l1 = build(9, 9);
        ListNode l2 = build(1);
        int[] result = toArray(solution.sumTwoNumbers(l1, l2));
        assertArrayEquals(new int[]{0, 0, 1}, result);
    }

    @Test
    void addZeros() {
        ListNode l1 = build(0);
        ListNode l2 = build(0);
        int[] result = toArray(solution.sumTwoNumbers(l1, l2));
        assertArrayEquals(new int[]{0}, result);
    }
}
