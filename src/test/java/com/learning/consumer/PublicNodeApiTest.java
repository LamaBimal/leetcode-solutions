package com.learning.consumer;

import java.util.Arrays;

import com.learning.bfs.BinaryTreeLevelOrder;
import com.learning.binarytree.BinaryTreeTraversal;
import com.learning.fastslowpointers.LinkedListCycle;
import com.learning.linkedlistreversal.ReverseLinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Verifies that callers outside the algorithm packages can construct usable inputs. */
class PublicNodeApiTest {

    @Test
    void externalCallerCanBuildAndTraverseLevels() {
        BinaryTreeLevelOrder.TreeNode root = new BinaryTreeLevelOrder.TreeNode(1);
        root.left = new BinaryTreeLevelOrder.TreeNode(2);
        root.right = new BinaryTreeLevelOrder.TreeNode(3);

        assertEquals(1, root.val);
        assertEquals(Arrays.asList(Arrays.asList(1), Arrays.asList(2, 3)),
                new BinaryTreeLevelOrder().levelOrder(root));
    }

    @Test
    void externalCallerCanBuildAndTraverseTree() {
        BinaryTreeTraversal.TreeNode root = new BinaryTreeTraversal.TreeNode(2);
        root.left = new BinaryTreeTraversal.TreeNode(1);
        root.right = new BinaryTreeTraversal.TreeNode(3);
        BinaryTreeTraversal solver = new BinaryTreeTraversal();

        assertEquals(Arrays.asList(1, 2, 3), solver.inorder(root));
        assertEquals(Arrays.asList(2, 1, 3), solver.preorder(root));
        assertEquals(Arrays.asList(1, 3, 2), solver.postorder(root));
    }

    @Test
    void externalCallerCanInspectMiddleAndCreateCycle() {
        LinkedListCycle.ListNode first = new LinkedListCycle.ListNode(1);
        LinkedListCycle.ListNode second = new LinkedListCycle.ListNode(2);
        first.next = second;
        LinkedListCycle solver = new LinkedListCycle();

        assertFalse(solver.hasCycle(first));
        assertSame(second, solver.findMiddle(first));
        assertEquals(2, solver.findMiddle(first).val);
        second.next = first;
        assertTrue(solver.hasCycle(first));
    }

    @Test
    void externalCallerCanReverseAndInspectTheSameNodes() {
        ReverseLinkedList.ListNode first = new ReverseLinkedList.ListNode(1);
        ReverseLinkedList.ListNode second = new ReverseLinkedList.ListNode(2);
        ReverseLinkedList.ListNode third = new ReverseLinkedList.ListNode(3);
        first.next = second;
        second.next = third;
        ReverseLinkedList solver = new ReverseLinkedList();

        ReverseLinkedList.ListNode reversed = solver.reverse(first);
        assertSame(third, reversed);
        assertEquals(3, reversed.val);
        assertSame(second, reversed.next);
        assertSame(first, reversed.next.next);
        assertNull(first.next);

        ReverseLinkedList.ListNode partial = solver.reverseBetween(reversed, 1, 2);
        assertSame(second, partial);
        assertSame(third, partial.next);
        assertSame(first, partial.next.next);
        assertNull(first.next);
    }
}
