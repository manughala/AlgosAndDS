package com.leetcode.linkedin;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

 1
 / \
 2   2
 / \ / \
 3  4 4  3


 But the following [1,2,2,null,3,null,3] is not:

 1
 / \
 2   2
 \   \
 3    3


 Note:
 Bonus points if you could solve it both recursively and iteratively.

 * @author Santosh Manughala (SM030146).
 */
public class IsSymmetricTree {
    private static class TreeNode {
        int value;
        TreeNode left, right;
        TreeNode(int value) {
            this.value = value;
        }
    }

    public static void main(String args[]) {
        // symmetric tree
        TreeNode node1 = new TreeNode(1);
        node1.left = new TreeNode(2);
        node1.left.left = new TreeNode(3);
        node1.left.right = new TreeNode(4);
        node1.right = new TreeNode(2);
        node1.right.left = new TreeNode(4);
        node1.right.right = new TreeNode(3);

        // not a symmetric tree
        TreeNode node2 = new TreeNode(1);
        node2.left = new TreeNode(2);
        node2.left.right = new TreeNode(3);
        node2.right = new TreeNode(2);
        node2.right.right = new TreeNode(3);

        System.out.println(isSymmetricRecur(node1));
        System.out.println(isSymmetricRecur(node2));
        System.out.println(isSymmetricIter(node1));
        System.out.println(isSymmetricIter(node2));
    }

    // Time O(n)
    // Space O(n) - recursion uses stack internally
    private static boolean isSymmetricRecur(TreeNode root) {
        if(root == null) {
            return true;
        }

        return isMirror(root, root);
    }

    private static boolean isMirror(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) {
            return true;
        }

        if(node1 == null || node2 == null) {
            return false;
        }

        return node1.value == node2.value && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }

    // Time O(n)
    // Space O(n) - queue
    private static boolean isSymmetricIter(TreeNode root) {
        if(root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            if(node1 == null && node2 == null) {
                continue;
            }

            if(node1 == null || node2 == null) {
                return false;
            }

            if(node1.value != node2.value) {
                return false;
            }

            queue.add(node1.left);
            queue.add(node2.right);
            queue.add(node1.right);
            queue.add(node2.left);
        }

        return true;
    }
}
