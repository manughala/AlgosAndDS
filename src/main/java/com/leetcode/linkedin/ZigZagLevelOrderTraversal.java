package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
public class ZigZagLevelOrderTraversal {
    private static class TreeNode {
        int value;
        TreeNode left, right;
        TreeNode(int value) {
            this.value = value;
        }
    }

    public static void main(String args[]) {
        TreeNode node1 = new TreeNode(3);
        node1.left = new TreeNode(9);
        node1.left.left = new TreeNode(15);
        node1.right = new TreeNode(20);
        node1.right.right = new TreeNode(7);

        List<List<Integer>> resultIter = zigzagLevelOrderTraversalIter(node1);
        int levelCount = 0;
        for(List<Integer> level : resultIter) {
            System.out.println("level : " + levelCount++);
            for(int i : level) {
                System.out.println(i);
            }
        }
    }

    // TIme: O(n) - we visit each node only once
    // Space: O(n) - stacks
    private static List<List<Integer>> zigzagLevelOrderTraversalIter(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null) {
            return levels;
        }

        List<Integer> currentLevelValues = new ArrayList<>();
        Stack<TreeNode> currentLevelNodes = new Stack<>();
        Stack<TreeNode> nextLevelNodes = new Stack<>();

        boolean leftToRight = true;

        currentLevelNodes.add(root);

        while(!currentLevelNodes.isEmpty()) {
            TreeNode node = currentLevelNodes.pop();
            currentLevelValues.add(node.value);

            if(leftToRight) {
                if(node.left != null) {
                    nextLevelNodes.add(node.left);
                }

                if(node.right != null) {
                    nextLevelNodes.add(node.right);
                }
            } else {
                if(node.right != null) {
                    nextLevelNodes.add(node.right);
                }

                if(node.left != null) {
                    nextLevelNodes.add(node.left);
                }
            }

            if(currentLevelNodes.isEmpty()) {
                leftToRight = !leftToRight;

                currentLevelNodes = nextLevelNodes;
                nextLevelNodes = new Stack<>();

                levels.add(currentLevelValues);
                currentLevelValues = new ArrayList<>();
            }
        }


        return levels;
    }
}
