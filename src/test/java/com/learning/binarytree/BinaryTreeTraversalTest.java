package com.learning.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTraversalTest {

    private final BinaryTreeTraversal solution = new BinaryTreeTraversal();
    private BinaryTreeTraversal.TreeNode root;

    @BeforeEach
    void setUp() {
        root = new BinaryTreeTraversal.TreeNode(4);
        root.left = new BinaryTreeTraversal.TreeNode(2);
        root.right = new BinaryTreeTraversal.TreeNode(6);
        root.left.left = new BinaryTreeTraversal.TreeNode(1);
        root.left.right = new BinaryTreeTraversal.TreeNode(3);
    }

    @Test
    void testInorder() {
        assertEquals(Arrays.asList(1, 2, 3, 4, 6), solution.inorder(root));
    }

    @Test
    void testPreorder() {
        assertEquals(Arrays.asList(4, 2, 1, 3, 6), solution.preorder(root));
    }

    @Test
    void testPostorder() {
        assertEquals(Arrays.asList(1, 3, 2, 6, 4), solution.postorder(root));
    }

    @Test
    void testNullRoot() {
        assertTrue(solution.inorder(null).isEmpty());
        assertTrue(solution.preorder(null).isEmpty());
        assertTrue(solution.postorder(null).isEmpty());
    }

    @Test
    void testSingleNode() {
        BinaryTreeTraversal.TreeNode single = new BinaryTreeTraversal.TreeNode(5);
        assertEquals(Arrays.asList(5), solution.inorder(single));
    }
}
