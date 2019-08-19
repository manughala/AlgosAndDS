package com.leetcode.microsoft.treesandgraphs;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.


 Example 1:

   2
  / \
 1   3

 Input: [2,1,3]
 Output: true

 Example 2:

     5
    / \
   1   4
      / \
     3   6

 Input: [5,1,4,null,null,3,6]
 Output: false
 Explanation: The root node's value is 5 but its right child's value is 4.

 * @author Santosh Manughala (SM030146).
 */
public class ValidateBinarySearchTree {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        TreeNode node1 = new TreeNode(2);
        node1.left = new TreeNode(1);
        node1.right = new TreeNode(3);
        System.out.println("expected: true; actual: " + isValidBestCase(node1));

        TreeNode node2 = new TreeNode(5);
        node2.left = new TreeNode(1);
        node2.right = new TreeNode(4);
        node2.right.left = new TreeNode(3);
        node2.right.right = new TreeNode(6);
        System.out.println("expected: false; actual: " + isValidBestCase(node2));

        TreeNode node3 = new TreeNode(10);
        node3.left = new TreeNode(5);
        node3.left.left = new TreeNode(3);
        node3.left.right = new TreeNode(7);
        node3.right = new TreeNode(14);
        System.out.println( "expected: true; actual: " + isValidBSTRecurBruteForce(node3));

        System.out.println("expected: true; actual: " + isValidBSTRecurBruteForce(null));

        System.out.println("expected: true; actual: " + isValidBSTRecurBruteForce(new TreeNode(1)));

        TreeNode node4 = new TreeNode(1);
        node4.left = new TreeNode(1);
        System.out.println("expected: false; actual: " + isValidBSTRecurBruteForce(node4));
    }

    // Time O(n)
    // space O(n)
    private static boolean isValidBestCase (TreeNode node) {
        if(node == null || (node.left == null && node.right == null)) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        // NOTE : cannot use int.min cos we can get the minimum integer value in the binary tree
        // NOTE: cannot use double.min as min double is not actually a negative number as it does not have the sign bit as int: https://stackoverflow.com/questions/3884793/why-is-double-min-value-in-not-negative
        // so negative of max value is actually the smallest availble doubel number there is
        double prev = -Double.MAX_VALUE;

        while(!stack.isEmpty() || node != null) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }

            TreeNode temp = stack.pop();
            // If next element in inorder traversal is smaller than the previous one that's not BST.
            if(temp.val <= prev) {
                return false;
            }

            prev = temp.val;
            node = temp.right;
        }

        return true;
    }

    private static boolean isValidBSTRecurBruteForce(TreeNode node) {
        if(node == null || (node.left == null && node.right == null)) {
            return true;
        }

        if(node.left == null || node.right == null) {
            return false;
        }

        if(node.left != null && node.val <= getMinMax(node.left, false)) {
            return false;
        }

        if(node.right != null && node.val >= getMinMax(node.right, true)) {
            return false;
        }

        return isValidBSTRecurBruteForce(node.left) && isValidBSTRecurBruteForce(node.right);
    }

    private static int getMinMax(TreeNode node, boolean isMin) {
        if(node == null) {
            return node.val;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        int min = node.val, max = node.val;

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            max = temp.val > max ? temp.val : max;
            min = temp.val < min ? temp.val : min;

            if(temp.left != null){
                queue.add(temp.left);
            }

            if(temp.right != null) {
                queue.add(temp.right);
            }
        }

        return isMin ? min : max;
    }

}
