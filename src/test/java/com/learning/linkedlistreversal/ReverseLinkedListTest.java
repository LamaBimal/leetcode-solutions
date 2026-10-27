package com.learning.linkedlistreversal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReverseLinkedListTest {

    private final ReverseLinkedList solution = new ReverseLinkedList();

    private ReverseLinkedList.ListNode build(int... vals) {
        ReverseLinkedList.ListNode dummy = new ReverseLinkedList.ListNode(0);
        ReverseLinkedList.ListNode curr = dummy;
        for (int v : vals) { curr.next = new ReverseLinkedList.ListNode(v); curr = curr.next; }
        return dummy.next;
    }

    private int[] toArray(ReverseLinkedList.ListNode head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        while (head != null) { list.add(head.val); head = head.next; }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    void testFullReversal() {
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, toArray(solution.reverse(build(1, 2, 3, 4, 5))));
    }

    @Test
    void testSingleNode() {
        assertArrayEquals(new int[]{1}, toArray(solution.reverse(build(1))));
    }

    @Test
    void testReverseBetween() {
        // 1->2->3->4->5, reverse [2..4] → 1->4->3->2->5
        assertArrayEquals(new int[]{1, 4, 3, 2, 5}, toArray(solution.reverseBetween(build(1, 2, 3, 4, 5), 2, 4)));
    }

    @Test
    void testReverseBetweenFullList() {
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, toArray(solution.reverseBetween(build(1, 2, 3, 4, 5), 1, 5)));
    }

    @Test
    void testReverseBetweenSingleNode() {
        // reversing a single position is a no-op
        assertArrayEquals(new int[]{1, 2, 3}, toArray(solution.reverseBetween(build(1, 2, 3), 2, 2)));
    }
}
