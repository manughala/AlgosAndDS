package com.leetcode.facebook.treesandgraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Example 1:

 Input:
 2
 / \
 1   3
 Output: true

 Example 2:

 5
 / \
 1   4
 / \
 3   6
 Output: false
 Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 is 5 but its right child's value is 4.

 * @author Santosh Manughala (SM030146).
 */
public class ValidateBinarySearchTree {

    private static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value) {
            this.value = value;
        }
    }
    public static void main(String arg[]) {
//        TreeNode node = new TreeNode(2);
//        node.left = new TreeNode(1);
//        node.right = new TreeNode(3);

//        TreeNode node = new TreeNode(5);
//        node.left = new TreeNode(1);
//        node.right = new TreeNode(4);
//        node.right.right = new TreeNode(6);
//        node.left.left = new TreeNode(3);

        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(5);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(25);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(6);

        // 3 5 6  // 5 10 20 // 15 20 25

//        TreeNode node = new TreeNode(10);
//        node.left = new TreeNode(5);
//        node.right = new TreeNode(20);
//        node.right.left = new TreeNode(9);
//        node.right.right = new TreeNode(25);
//        node.left.left = new TreeNode(3);
//        node.left.right = new TreeNode(6);

//        boolean result = isValidBinaryBruteForce(node);
//        boolean result = isValidBinaryBetterI(node, null, null);
//        boolean result = isValidBinaryBetterII(node);
        boolean result = isValidBinaryBest(node);
//
//        getMinMaxTest(node, true);
        // best case:

//        System.out.println("result = " + result);

//        printBFS(node);

    }


    // Time O(n) space O(n)
    private static boolean isValidBinaryBetterI (TreeNode node, Integer min, Integer max) {
        if(min != null && node.value <= min) {
                return false;
        }

        if(max != null&& node.value >= max) {
                return false;
        }

        if(node.left != null ? isValidBinaryBetterI(node.left, min, node.value) : true) {
            return node.right != null ? isValidBinaryBetterI(node.right, node.value, max) : true;
        }

        return false;

    }

    private static boolean isValidBinaryBest (TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;
        // NOTE : cannot use int.min cos we can get the minimum integer value in the binary tree
        // NOTE: cannot use double.min as min double is not actually a negative number as it does not have the sign bit as int: https://stackoverflow.com/questions/3884793/why-is-double-min-value-in-not-negative
        // so negative of max value is actually the smallest availble doubel number there is

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.value <= inorder) return false;
            inorder = root.value;
            root = root.right;
        }
        return true;
    }

    // Very bad
    private static boolean isValidBinaryBruteForce(TreeNode node) {
        if(node == null || node.left == null && node.right == null) {
            return true;
        }

        if(node.left != null && node.value <= getMinMax(node.left, false)) {
            return false;
        }

        if(node.right != null && node.value >= getMinMax(node.right, true)) {
            return false;
        }

        return isValidBinaryBruteForce(node.left) && isValidBinaryBruteForce(node.right);
    }

    private static int getMinMaxTest(TreeNode node, boolean isMin) {
        Stack<TreeNode> stack = new Stack<>();
        if(node == null) {
            return 0;
        }

        int min = node.value, max = node.value;

        stack.add(node);

        while(!stack.isEmpty()) {
            TreeNode temp = stack.pop();

            min = min > temp.value ? temp.value : min;
            max = max < temp.value ? temp.value : max;

            if(temp.left != null) {
                stack.add(temp.left);
            }

            if(temp.right != null) {
                stack.add(temp.right);
            }
        }

        System.out.println(min);
        System.out.println(max);

        return isMin ? min : max;
    }

    private static int getMinMax(TreeNode node, boolean isMin) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(node == null) {
            return 0;
        }

        int min = node.value, max = node.value;

        queue.add(node);

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if(temp.left != null) {
                min = min > temp.left.value ? temp.left.value : min;
                max = max < temp.left.value ? temp.left.value : max;

                queue.add(temp.left);
            }

            if(temp.right != null) {
                min = min > temp.right.value ? temp.right.value : min;
                max = max < temp.right.value ? temp.right.value : max;

                queue.add(temp.right);
            }
        }

        return isMin ? min : max;
    }

    // Time O(n) space O(n) NOTE: THIS IS ITERATIVE AND DID NOT UNDERSTAND
    private static boolean isValidBinaryBetterII (TreeNode root) {
        if (root == null)
            return true;

        LinkedList<TreeNode> stack = new LinkedList();
        LinkedList<Integer> upper_limits = new LinkedList();
        LinkedList<Integer> lower_limits = new LinkedList();
        stack.add(root);
        upper_limits.add(null);
        lower_limits.add(null);

        while (!stack.isEmpty()) {
            TreeNode node = stack.poll();
            Integer lower_limit = lower_limits.poll();
            Integer upper_limit = upper_limits.poll();
            if (node.right != null) {
                if (node.right.value > node.value) {
                    if ((upper_limit != null) && (node.right.value >= upper_limit))
                        return false;
                    stack.add(node.right);
                    lower_limits.add(node.value);
                    upper_limits.add(upper_limit);
                } else
                    return false;
            }

            if (node.left != null) {
                if (node.left.value < node.value) {
                    if ((lower_limit != null) && (node.left.value <= lower_limit))
                        return false;
                    stack.add(node.left);
                    lower_limits.add(lower_limit);
                    upper_limits.add(node.value);
                } else
                    return false;
            }
        }
        return true;
    }
}
