package com.learning.linkedlist;

/** Adds reverse-order digit lists without converting them to fixed-size numbers. */
public final class ListNodeIntegerSum {

    /**
     * Returns newly allocated nodes in O(max(m,n)) time and output space.
     * Null represents zero; inputs must be acyclic and are never modified.
     */
    public ListNode sumTwoNumbers(ListNode first, ListNode second) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;
        while (first != null || second != null || carry != 0) {
            int sum = carry;
            if (first != null) {
                sum += first.val;
                first = first.next;
            }
            if (second != null) {
                sum += second.val;
                second = second.next;
            }
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }
        return dummy.next == null ? new ListNode(0) : dummy.next;
    }
}
