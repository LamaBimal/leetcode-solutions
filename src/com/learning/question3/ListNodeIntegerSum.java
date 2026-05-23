package com.learning.question3;

public class ListNodeIntegerSum {
    public ListNode sumTwoNumbers(ListNode val1, ListNode val2){
        int carryVal = 0;
        ListNode head = new ListNode(0);
        ListNode current = head;

        System.out.println(current);
        System.out.println(head);

        while(val1 != null || val2 != null || carryVal != 0){
            System.out.println("Testing1");

            int x = (val1 != null) ? val1.val : 0;
            int y = (val2 != null) ? val2.val : 0;

            int sum = x+y+carryVal;

            carryVal = sum/10;
            if (val1 != null) val1 = val1.next;
            if (val2 != null) val2 = val2.next;
            current.next = new ListNode(sum%10);
            current = current.next;
        }
            return head;
    }

    public static void main(String[] args) {
        ListNodeIntegerSum object = new ListNodeIntegerSum();
        // Input: l1 = [2,4,3], l2 = [5,6,4]
        ListNode val1 = new ListNode(2);
        val1.next = new ListNode(4);
        val1.next.next = new ListNode(3);

        ListNode val2 = new ListNode(5);
        val2.next = new ListNode(6);
        val2.next.next = new ListNode(4);
        ListNode response = object.sumTwoNumbers(val1,val2);
        System.out.println(response.val);
        while((response= response.next)!= null){
            System.out.println(response.val);
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
