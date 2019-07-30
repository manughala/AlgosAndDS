package com.leetcode.linkedin;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 Note: A leaf is a node with no children.

 Example:

 Given binary tree [3,9,20,null,null,15,7],

 3
 / \
 9  20
   /  \
 15   7
 return its depth = 3.


 [0,2,4,1,null,3,-1,5,1,null,6,null,8]

       0
   2      4
 1      3   -1
      5  1     6
                8
 *
 * @author Santosh Manughala (SM030146).
 */
public class MaxDepthOfBST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String args[]) {
        TreeNode tree = new TreeNode(0);
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

        int depthIter = depthIter(tree);
        System.out.println("depthIter = " + depthIter);
    }

    // TIme: O(n) - once every node
    // Space: O(n) - recursion uses stack
    private static int depthRecur(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int leftDepth = depthRecur(node.left);
        int rightDepth = depthRecur(node.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    // TIme: O(n) - once every node
    // Space: O(n) - queue to store the nodes
    private static int depthIter (TreeNode node) {
        if(node == null) {
            return 0;
        }

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            depth++;
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                TreeNode temp = queue.remove();

                if(temp.left != null) {
                    queue.add(temp.left);
                }

                if(temp.right != null) {
                    queue.add(temp.right);
                }
            }

        }

        return depth;
    }
}
