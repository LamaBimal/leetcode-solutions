package com.learning.bfs;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeLevelOrderTest {

    private final BinaryTreeLevelOrder solution = new BinaryTreeLevelOrder();

    @Test
    void testBasicTree() {
        BinaryTreeLevelOrder.TreeNode root = new BinaryTreeLevelOrder.TreeNode(3);
        root.left = new BinaryTreeLevelOrder.TreeNode(9);
        root.right = new BinaryTreeLevelOrder.TreeNode(20);
        root.right.left = new BinaryTreeLevelOrder.TreeNode(15);
        root.right.right = new BinaryTreeLevelOrder.TreeNode(7);

        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(3),
            Arrays.asList(9, 20),
            Arrays.asList(15, 7)
        );
        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void testNullRoot() {
        assertTrue(solution.levelOrder(null).isEmpty());
    }

    @Test
    void testSingleNode() {
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1));
        assertEquals(expected, solution.levelOrder(new BinaryTreeLevelOrder.TreeNode(1)));
    }

    @Test
    void testLinearTree() {
        BinaryTreeLevelOrder.TreeNode root = new BinaryTreeLevelOrder.TreeNode(1);
        root.right = new BinaryTreeLevelOrder.TreeNode(2);
        root.right.right = new BinaryTreeLevelOrder.TreeNode(3);
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(1), Arrays.asList(2), Arrays.asList(3)
        );
        assertEquals(expected, solution.levelOrder(root));
    }
}
