package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You need to find the largest value in each row of a binary tree.

 Example:
 Input:

 1
 / \
 3   2
 / \   \
 5   3   9

 Output: [1, 3, 9]

 * @author Santosh Manughala (SM030146).
 */
public class FindLargestValInEachRow {
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

        List<Integer> resultIter = largestValuesIter(node1);
        for(int i : resultIter) {
            System.out.println(i);
        }

        List<Integer> resultRecur = largestValuesRecur(node1);
        for(int i : resultRecur) {
            System.out.println(i);
        }
    }

    private static List<Integer> largestValuesRecur(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        recurHelper(result, 0, node);
        return result;
    }

    private static void recurHelper(List<Integer> result, int level, TreeNode node) {
        if(node == null) {
            return;
        }

        if (level == result.size()) {
            result.add(Integer.MIN_VALUE);
        }

        result.set(level, Math.max(node.value, result.get(level)));

        if(node.left != null) {
            recurHelper(result, level + 1, node.left);
        }

        if(node.right != null) {
            recurHelper(result, level + 1, node.right);
        }
    }

    private static List<Integer> largestValuesIter(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        if(node == null) {
            return result;
        }

        Queue<TreeNode> currentLevelNodes = new LinkedList<>();
        Queue<TreeNode> nextLevelNodes = new LinkedList<>();

        int currentLevelMax = Integer.MIN_VALUE;
        currentLevelNodes.add(node);

        while(!currentLevelNodes.isEmpty()) {
            TreeNode temp = currentLevelNodes.remove();
            currentLevelMax = Math.max(currentLevelMax, temp.value);

            if(temp.left != null) {
                nextLevelNodes.add(temp.left);
            }

            if(temp.right != null){
                nextLevelNodes.add(temp.right);
            }

            if(currentLevelNodes.isEmpty()) {
                result.add(currentLevelMax);
                currentLevelMax = Integer.MIN_VALUE;

                currentLevelNodes = nextLevelNodes;
                nextLevelNodes = new LinkedList<>();
            }
        }
        return result;
    }
}
