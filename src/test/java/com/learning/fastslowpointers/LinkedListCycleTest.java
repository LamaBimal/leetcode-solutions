package com.learning.fastslowpointers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListCycleTest {

    private final LinkedListCycle solution = new LinkedListCycle();

    private LinkedListCycle.ListNode buildList(int... vals) {
        LinkedListCycle.ListNode dummy = new LinkedListCycle.ListNode(0);
        LinkedListCycle.ListNode curr = dummy;
        for (int v : vals) { curr.next = new LinkedListCycle.ListNode(v); curr = curr.next; }
        return dummy.next;
    }

    @Test
    void testNoCycle() {
        assertFalse(solution.hasCycle(buildList(1, 2, 3, 4, 5)));
    }

    @Test
    void testWithCycle() {
        LinkedListCycle.ListNode head = buildList(1, 2, 3, 4, 5);
        // create cycle: tail -> node at index 1
        LinkedListCycle.ListNode tail = head;
        LinkedListCycle.ListNode cycleEntry = head.next;
        while (tail.next != null) tail = tail.next;
        tail.next = cycleEntry;
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void testNullHead() {
        assertFalse(solution.hasCycle(null));
    }

    @Test
    void testMiddleOfOddList() {
        // 1->2->3->4->5, middle = 3
        assertEquals(3, solution.findMiddle(buildList(1, 2, 3, 4, 5)).val);
    }

    @Test
    void testMiddleOfEvenList() {
        // 1->2->3->4, middle = 3 (second of two middle nodes)
        assertEquals(3, solution.findMiddle(buildList(1, 2, 3, 4)).val);
    }
}
