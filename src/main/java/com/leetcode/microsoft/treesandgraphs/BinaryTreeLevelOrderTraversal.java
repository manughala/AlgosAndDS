package com.leetcode.microsoft.treesandgraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
public class BinaryTreeLevelOrderTraversal {

    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.right = new TreeNode(4);
        node.right.left = new TreeNode(3);
        node.right.right = new TreeNode(6);

        List<List<Integer>> levelOrderTraversal = levelOrderTraversal(node);

        int i = 0;
        for(List<Integer> level : levelOrderTraversal) {
            System.out.println("level: " + i++);
            for(Integer val : level) {
                System.out.println(val);
            }
        }

        List<List<Integer>> levelOrderTraversalAnother = levelOrderTraversalAnother(node);

        i = 0;
        for(List<Integer> level : levelOrderTraversalAnother) {
            System.out.println("level: " + i++);
            for(Integer val : level) {
                System.out.println(val);
            }
        }

        List<List<Integer>> levelOrderTraversalRecur = levelOrderTraversalRecur(node);

        i = 0;
        for(List<Integer> level : levelOrderTraversalRecur) {
            System.out.println("level: " + i++);
            for(Integer val : level) {
                System.out.println(val);
            }
        }
    }

    private static List<List<Integer>> levelOrderTraversalRecur (TreeNode node) {
        List<List<Integer>> levels = new ArrayList<>();
        return recurTraversal(node, 0, levels);
    }

    private static List<List<Integer>> recurTraversal(TreeNode node, int level, List<List<Integer>> levels) {
        if(levels.size() == level) {
            levels.add(new ArrayList<>());
        }

        levels.get(level).add(node.val);

        if(node.left != null) {
            recurTraversal(node.left, level + 1, levels);
        }

        if(node.right != null) {
            recurTraversal(node.right, level + 1, levels);
        }

        return levels;
    }

    private static List<List<Integer>> levelOrderTraversalAnother(TreeNode node) {
        List<List<Integer>> levelOrderValues = new ArrayList<>();

        if(node == null) {
            return levelOrderValues;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        int level = 0;

        while (!queue.isEmpty()) {
            levelOrderValues.add(new ArrayList<>());

            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                levelOrderValues.get(level).add(temp.val);

                if(temp.left != null) {
                    queue.add(temp.left);
                }

                if(temp.right != null) {
                    queue.add(temp.right);
                }
            }

            level++;
        }

        return levelOrderValues;
    }

    private static List<List<Integer>> levelOrderTraversal(TreeNode node) {
        List<List<Integer>> levelOrderValues = new ArrayList<>();

        if(node == null) {
            return levelOrderValues;
        }

        List<Integer> currentLevelValues = new ArrayList<>();

        Queue<TreeNode> currentLevelNodes = new LinkedList<>();
        Queue<TreeNode> nextLevelNodes = new LinkedList<>();

        currentLevelNodes.add(node);

        while (!currentLevelNodes.isEmpty()) {
            TreeNode temp = currentLevelNodes.poll();
            currentLevelValues.add(temp.val);

            if(temp.left != null) {
                nextLevelNodes.add(temp.left);
            }

            if(temp.right != null) {
                nextLevelNodes.add(temp.right);
            }

            if(currentLevelNodes.isEmpty()) {
                currentLevelNodes = nextLevelNodes;
                nextLevelNodes = new LinkedList<>();

                levelOrderValues.add(currentLevelValues);
                currentLevelValues = new ArrayList<>();
            }
        }

        return levelOrderValues;
    }
}
