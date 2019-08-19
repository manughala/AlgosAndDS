package com.leetcode.amazon.explore.treesandgraphs;

/**
 * Given a non-empty binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

 Example 1:

 Input: [1,2,3]

 1
 / \
 2   3

 Output: 6
 Example 2:

 Input: [-10,9,20,null,null,15,7]

 -10
 / \
 9  20
 /  \
 15   7

 Output: 42

 * @author Santosh Manughala (SM030146).
 */
public class BinaryTreeMaxPathSum {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        TreeNode node1 = new TreeNode(2);
        node1.left = new TreeNode(-1);
        node1.right = new TreeNode(3);
        System.out.println("expected: true; actual: " + getMaxPathSum(node1));
    }

    private static int MAX = Integer.MIN_VALUE;
    private static int getMaxPathSum(TreeNode node) {
        performRecur(node);
        return MAX;
    }

    private static int performRecur(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int left = Math.max(performRecur(node.left), 0 );
        int right = Math.max(performRecur(node.right), 0);

        MAX = Math.max(MAX, left + right + node.val);

        return node.val + Math.max(left, right);
    }
}
