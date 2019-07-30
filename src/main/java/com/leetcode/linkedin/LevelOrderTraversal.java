package com.leetcode.linkedin;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its level order traversal as:
 [
 [3],
 [9,20],
 [15,7]
 ]

 * @author Santosh Manughala (SM030146).
 */
public class LevelOrderTraversal {
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

        List<List<Integer>> resultIter = levelOrderTraversalIter(node1);
        List<List<Integer>> resultIterI = levelOrderTraversalIterAnotherWay(node1);
        List<List<Integer>> resultRecur = levelOrderTraversalRecur(node1);

        int levelCount = 0;
        for(List<Integer> level : resultIter) {
            System.out.println("level : " + levelCount++);
            for(int i : level) {
                System.out.println(i);
            }
        }

        levelCount = 0;
        for(List<Integer> level : resultIterI) {
            System.out.println("level Iter I : " + levelCount++);
            for(int i : level) {
                System.out.println(i);
            }
        }

        levelCount = 0;
        for(List<Integer> level : resultRecur) {
            System.out.println("level : " + levelCount++);
            for(int i : level) {
                System.out.println(i);
            }
        }

    }

    private static List<List<Integer>> levelOrderTraversalIterAnotherWay(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        if(root == null) {
            return levels;
        }

        List<Integer> currentLevelNodeValues = new ArrayList<>();

        Queue<TreeNode> currentLevelNodes = new LinkedList<>();
        Queue<TreeNode> nextLevelNodes = new LinkedList<>();

        currentLevelNodes.add(root);

        while(!currentLevelNodes.isEmpty()) {
            TreeNode node = currentLevelNodes.remove();
            currentLevelNodeValues.add(node.value);

            if(node.left != null) {
                nextLevelNodes.add(node.left);
            }

            if (node.right != null) {
                nextLevelNodes.add(node.right);
            }

            if(currentLevelNodes.isEmpty()) {
                currentLevelNodes = nextLevelNodes;
                nextLevelNodes = new LinkedList<>();

                levels.add(currentLevelNodeValues);
                currentLevelNodeValues = new ArrayList<>();
            }
        }

        return levels;
    }

    // TIme: O(n) - each node is traversed only once
    // Space: O(n) - recursion uses stack
    private static List<List<Integer>> levelOrderTraversalRecur(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        if(root == null) {
            return levels;
        }

        return performRecur(root, 0, levels);
    }

    private static List<List<Integer>> performRecur(TreeNode node, int level, List<List<Integer>> levels) {
        if(levels.size() == level) {
            levels.add(new ArrayList<>());
        }

        levels.get(level).add(node.value);

        if(node.left != null) {
            performRecur(node.left, level + 1, levels);
        }

        if(node.right != null) {
            performRecur(node.right, level + 1, levels);
        }

        return levels;
    }

    //TIme: O(n) - each node is traversed only once
    // Space: O(n) - queue size
    private static List<List<Integer>> levelOrderTraversalIter(TreeNode root) {
        List<List<Integer>> nodesByLevel = new ArrayList<>();

        if(root == null) {
            return nodesByLevel;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

        while(!queue.isEmpty()) {
            nodesByLevel.add(new ArrayList<>());

            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.remove(); // using remove instead of poll as remove will throw NoElementFound exception if there is not one while poll will return null, and we might get NPE.

                nodesByLevel.get(level).add(node.value);

                if(node.left != null) {
                    queue.add(node.left);
                }

                if(node.right != null) {
                    queue.add(node.right);
                }
            }

            level++;
        }

        return nodesByLevel;
    }
}
