package com.leetcode.facebook.linkedlists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * LeetCode – Flatten Binary Tree to Linked List

 Given a binary tree, flatten it to a linked list in-place.

 For example,
 Given
 1
 / \
 2   5
 / \   \
 3   4   6

 The flattened tree should look like:

 1
 \
 2
 \
 3
 \
 4
 \
 5
 \
 6

 * @author Santosh Manughala (SM030146).
 */
public class FlattenBinaryTree {

    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int value) {
            this.value = value;
        }
    }

    public static void main (String arg[]) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(3);
//        node.left.left.right = new TreeNode(7);
//        node.left.left.right.left = new TreeNode(8);
        node.left.right = new TreeNode(4);
        node.right = new TreeNode(5);
        node.right.right = new TreeNode(6);

//        TreeNode node = new TreeNode(1);
//        node.left = new TreeNode(2);
//        node.left.left = new TreeNode(4);
//        node.left.right = new TreeNode(5);
//        node.right = new TreeNode(3);

        if(node == null) {
            return;
        }

//        flattenBinaryTreeRecursive(node);
        flattenBinaryTreeIterative(node);

        printDFS(node);
//        printBFS(node);
        return;
    }

    private static void flattenBinaryTreeIterative(TreeNode node) {
        if(node == null || node.right == null && node.left == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while(node != null || !stack.isEmpty()) {
            if(node.right != null) {
                stack.push(node.right);
            }

            if(node.left != null) {
                node.right = node.left;
                node.left = null;
            } else if(!stack.isEmpty()) {
                TreeNode tempRight = stack.pop();
                node.right = tempRight;
            }

            node = node.right;
        }

        return;
    }

    // O(n) ?? space: O(n)
    private static void flattenBinaryTreeRecursive(TreeNode root) {
        if(root == null || root.left == null && root.right == null) {
            return;
        }

        if(root.left != null) {
            flattenBinaryTreeRecursive(root.left);

            TreeNode tempRight = root.right;
            root.right = root.left;
            root.left = null;

            TreeNode t = root.right;
            while(t.right != null) {
                t = t.right;
            }

            t.right = tempRight;
        }
        flattenBinaryTreeRecursive(root.right);
    }

    public static void printBFS(TreeNode node) {

        // BFS printing
        System.out.println("BFS PRINTING");
        Queue<TreeNode> queue = new LinkedList<>();

        System.out.println(node.value);
        queue.add(node);

        while(!queue.isEmpty()) {

            TreeNode temp = queue.poll();
            if(temp.left != null) {
                System.out.println(temp.left.value);
                queue.add(temp.left);
            }

            if(temp.right != null) {
                System.out.println(temp.right.value);
                queue.add(temp.right);
            }
        }
    }


    private static void printDFS (TreeNode node) {
        // https://www.geeksforgeeks.org/dfs-traversal-of-a-tree-using-recursion/

        System.out.println("INORDER");
        printInOrder(node);
        System.out.println("PREORDER");
        printPreOrder(node);
        System.out.println("PREORDER ITERATIVE: ");
        printPreOrderIterative(node);
        System.out.println("POSTORDER");
        printPostOrder(node);

    }

    private static void printInOrder(TreeNode node) {
        if(node == null) {
            return;
        }

        printInOrder(node.left);
        System.out.println(node.value);
        printInOrder(node.right);
    }

    private static void printInOrderIterative(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();

        while(node != null || !stack.isEmpty()) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            System.out.println(node.value);

            node = node.right;
        }
    }

    private static void printPreOrder(TreeNode node) {
        if(node == null) {
            return;
        }

        System.out.println(node.value);
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    // WROTE IT RANDOM -> TEST IF THIS WORKS.. may not? i dont know
    private static void printPreOrderIterative(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();

        while(node != null || !stack.isEmpty()) {
            while(node.left != null) {
                stack.push(node);
                node = node.left;
            }

            System.out.println(node.value);
            if(node.left != null) {
                System.out.println(node.left.value);
            }

            node = node.right;
        }
    }

    private static void printPostOrder(TreeNode node) {
        if(node == null) {
            return;
        }

        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.println(node.value);
    }
}
