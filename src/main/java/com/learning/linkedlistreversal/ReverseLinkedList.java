package com.learning.linkedlistreversal;

/**
 * Linked List In-Place Reversal
 *
 * Example 1: Reverse entire list.
 * Input:  1 -> 2 -> 3 -> 4 -> 5
 * Output: 5 -> 4 -> 3 -> 2 -> 1
 *
 * Example 2: Reverse a sublist from position left to right.
 * Input:  1 -> 2 -> 3 -> 4 -> 5, left=2, right=4
 * Output: 1 -> 4 -> 3 -> 2 -> 5
 */
public class ReverseLinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Reverse the entire list by rewiring .next pointers in-place
    public ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next; // save next
            curr.next = prev;          // reverse pointer
            prev = curr;               // move prev forward
            curr = next;               // move curr forward
        }
        return prev; // prev is the new head
    }

    // Reverse only nodes from index `left` to `right` (1-indexed)
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        // Move pre to the node just before `left`
        for (int i = 1; i < left; i++) pre = pre.next;

        ListNode curr = pre.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }

    private void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " -> " : "\n"));
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();

        // Build 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.print("Original:        ");
        solution.printList(head);

        ListNode reversed = solution.reverse(head);
        System.out.print("Fully reversed:  ");
        solution.printList(reversed); // 5->4->3->2->1

        // Rebuild 1->2->3->4->5 for partial reversal
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);

        ListNode partial = solution.reverseBetween(head2, 2, 4);
        System.out.print("Reversed [2..4]: ");
        solution.printList(partial); // 1->4->3->2->5
    }
}
