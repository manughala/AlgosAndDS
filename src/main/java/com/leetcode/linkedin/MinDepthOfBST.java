package com.leetcode.linkedin;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 Note: A leaf is a node with no children.

 Example:

 Given binary tree [3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7
 return its minimum depth = 2.

 * @author Santosh Manughala (SM030146).
 */
public class MinDepthOfBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String args[]) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.left.left = new TreeNode(1);
        tree.right = new TreeNode(4);
        tree.right.left = new TreeNode(3);
        tree.right.right = new TreeNode(-1);
        tree.right.right.right = new TreeNode(6);
        tree.right.right.right.right = new TreeNode(8);
        tree.right.left.left = new TreeNode(5);
        tree.right.left.right = new TreeNode(1);

        int depthRecur = depthRecur(tree);
        System.out.println("depthRecur = " + depthRecur);
    }

    // TIme: O(n) - once every node
    // Space: O(n) - recursion uses stack
    private static int depthRecur(TreeNode node) {
        if(node == null) {
            return 0;
        }

        if(node.left == null && node.right == null) {
            return 1;
        }

        int minDepth = Integer.MAX_VALUE;

        if(node.left != null) {
            minDepth = Math.min(minDepth, depthRecur(node.left));
        }

        if(node.right != null) {
            minDepth = Math.min(minDepth, depthRecur(node.right));
        }

        return minDepth + 1;
    }
}
