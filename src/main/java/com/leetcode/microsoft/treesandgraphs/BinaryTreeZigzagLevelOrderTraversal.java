package com.leetcode.microsoft.treesandgraphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
  3
 / \
 9  20
   /  \
  15   7
 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]

 * @author Santosh Manughala (SM030146).
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        List<List<Integer>> zigzagLevelOrderTraversal = zigzagLevelOrderTraversal(node);

        int i = 0;
        for (List<Integer> level : zigzagLevelOrderTraversal) {
            System.out.println("level: " + i++);
            for (Integer val : level) {
                System.out.println(val);
            }
        }
    }

    private static List<List<Integer>> zigzagLevelOrderTraversal(TreeNode node) {
        List<List<Integer>> levelOrderValues = new ArrayList<>();

        if(node == null) {
            return levelOrderValues;
        }

        List<Integer> currentLevelValues = new ArrayList<>();

        Stack<TreeNode> currentLevelNodes = new Stack<>();
        Stack<TreeNode> nextLevelNodes = new Stack<>();

        boolean leftToRight = true;

        currentLevelNodes.add(node);

        while (!currentLevelNodes.isEmpty()) {
            TreeNode temp = currentLevelNodes.pop();
            currentLevelValues.add(temp.val);

            if(leftToRight) {
                if(temp.left != null) {
                    nextLevelNodes.add(temp.left);
                }

                if(temp.right != null) {
                    nextLevelNodes.add(temp.right);
                }
            } else {
                if(temp.right != null) {
                    nextLevelNodes.add(temp.right);
                }

                if(temp.left != null) {
                    nextLevelNodes.add(temp.left);
                }
            }

            if(currentLevelNodes.isEmpty()) {
                leftToRight = !leftToRight;

                currentLevelNodes = nextLevelNodes;
                nextLevelNodes = new Stack<>();

                levelOrderValues.add(currentLevelValues);
                currentLevelValues = new ArrayList<>();
            }
        }

        return levelOrderValues;
    }
}
