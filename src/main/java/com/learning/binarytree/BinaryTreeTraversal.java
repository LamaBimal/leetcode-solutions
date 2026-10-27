package com.learning.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary Tree Traversal - Inorder, Preorder, Postorder
 *
 * Tree structure:
 *         4
 *        / \
 *       2   6
 *      / \
 *     1   3
 *
 * Inorder   (left → root → right): [1, 2, 3, 4, 6]  ← sorted order for BST
 * Preorder  (root → left → right): [4, 2, 1, 3, 6]  ← useful for copying tree
 * Postorder (left → right → root): [1, 3, 2, 6, 4]  ← useful for deletion
 */
public class BinaryTreeTraversal {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<Integer> inorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }

    public List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.val);
        preorderHelper(node.left, result);
        preorderHelper(node.right, result);
    }

    public List<Integer> postorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postorderHelper(node.left, result);
        postorderHelper(node.right, result);
        result.add(node.val);
    }

    public static void main(String[] args) {
        BinaryTreeTraversal solution = new BinaryTreeTraversal();

        // Build tree:   4
        //              / \
        //             2   6
        //            / \
        //           1   3
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println("Inorder   (L->Root->R): " + solution.inorder(root));   // [1,2,3,4,6]
        System.out.println("Preorder  (Root->L->R): " + solution.preorder(root));  // [4,2,1,3,6]
        System.out.println("Postorder (L->R->Root): " + solution.postorder(root)); // [1,3,2,6,4]
    }
}
