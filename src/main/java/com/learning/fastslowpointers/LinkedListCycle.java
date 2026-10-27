package com.learning.fastslowpointers;

/**
 * Fast & Slow Pointers - Cycle Detection & Middle Element
 *
 * Example 1: Detect if a linked list has a cycle.
 * Example 2: Find the middle node of a linked list.
 *
 * Input (no cycle): 1 -> 2 -> 3 -> 4 -> 5 -> null  => false
 * Input (cycle):    1 -> 2 -> 3 -> 4 -> 2 (loop)   => true
 * Middle of 1->2->3->4->5 => 3
 */
public class LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Detect cycle: if fast ever meets slow, there's a cycle
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    // Find middle node: when fast reaches end, slow is at middle
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Build list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Has cycle (no cycle): " + solution.hasCycle(head)); // false

        // Create cycle: tail points back to node 2
        head.next.next.next.next.next = head.next;
        System.out.println("Has cycle (with cycle): " + solution.hasCycle(head)); // true

        // Middle of 1->2->3->4->5 (rebuild without cycle)
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        System.out.println("Middle node value: " + solution.findMiddle(head2).val); // 3
    }
}
