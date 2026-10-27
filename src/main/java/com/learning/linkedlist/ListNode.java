package com.learning.linkedlist;

/** A mutable decimal digit node; lists supplied to algorithms must be acyclic. */
public final class ListNode {
    public final int val;
    public ListNode next;

    public ListNode(int val) {
        if (val < 0 || val > 9) {
            throw new IllegalArgumentException("Digit must be between 0 and 9");
        }
        this.val = val;
    }
}
