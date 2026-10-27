package com.learning.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Breadth-First Search (BFS) - Binary Tree Level Order Traversal
 *
 * Visit nodes level by level using a queue.
 * BFS guarantees we process all nodes at depth d before depth d+1.
 *
 * Tree:
 *         3
 *        / \
 *       9  20
 *          / \
 *         15   7
 *
 * Output: [[3], [9, 20], [15, 7]]
 */
public class BinaryTreeLevelOrder {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // number of nodes at current level
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(level);
        }

        return result;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrder solution = new BinaryTreeLevelOrder();

        // Build tree:  3
        //             / \
        //            9  20
        //               / \
        //              15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = solution.levelOrder(root);
        System.out.println("Level Order Traversal: " + result); // [[3], [9, 20], [15, 7]]
    }
}
