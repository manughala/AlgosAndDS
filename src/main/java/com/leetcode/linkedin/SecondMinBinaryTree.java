package com.leetcode.linkedin;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node.
 * If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

 Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

 If no such second minimum value exists, output -1 instead.

 Example 1:

 Input:
 2
 / \
 2   5
 / \
 5   7

 Output: 5
 Explanation: The smallest value is 2, the second smallest value is 5.


 Example 2:

 Input:
 2
 / \
 2   2

 Output: -1
 Explanation: The smallest value is 2, but there isn't any second smallest value.


 * @author Santosh Manughala (SM030146).
 */
public class SecondMinBinaryTree {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(7);
        node.right = new TreeNode(15);
        node.right.left = new TreeNode(9);
        node.right.right = new TreeNode(20);


        System.out.println(findSecondMinimumValueI(node));
        System.out.println(findSecondMinimumValueII(node));
        System.out.println(findSecondMinimumValueIII(node));
    }

    // time: O(n)
    // space O(n)
    private static int findSecondMinimumValueIII(TreeNode root) {
//        Set<Integer> allValues = new HashSet<>();

        // in a binary tree we would always have the root as min value
        min = root.val;
        addValuesIII(root);

        return secondMin < Long.MAX_VALUE ? (int) secondMin : -1;
    }

    private static int min;
    // we could have int max value as well in the tree, so we should have long here.
    private static long secondMin = Long.MAX_VALUE;

    private static void addValuesIII(TreeNode root) {
        if(root != null) {
            if(root.val > min && secondMin > root.val) {
                secondMin = root.val;
            } else if(min == root.val){
                addValuesIII(root.left);
                addValuesIII(root.right);
            }
        }
    }

    // time: O(n)
    // space O(n)
    private static int findSecondMinimumValueII(TreeNode root) {
        Set<Integer> allValues = new HashSet<>();
        addValuesII(root, allValues);

        // in a binary tree we would always have the root as min value
        int min = root.val;
        // we could have int max value as well in the tree, so we should have long here.
        long secondMin = Long.MAX_VALUE;

        for(int val : allValues) {
            if(min < val && secondMin > val) {
                secondMin = val;
            }
        }

        return secondMin < Long.MAX_VALUE ? (int) secondMin : -1;
    }

    private static void addValuesII(TreeNode root, Set<Integer> allValues) {
        if(root != null) {
            allValues.add(root.val);
            addValuesII(root.left, allValues);
            addValuesII(root.right, allValues);
        }
    }

    // brute force: TIme: O(n)
    // Space: O(n)
    private static int findSecondMinimumValueI(TreeNode root) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1 - i2;
            }
        });

        addValuesI(root, queue);

        int i = 2, result = 0, prev = result, first = queue.peek();
        while(i > 0 && !queue.isEmpty()) {
            result = queue.poll();
            if(result == prev) {
                continue;
            }
            prev = result;
            i--;
        }

        return first == result ? -1 : result;
    }

    private static void addValuesI(TreeNode root, PriorityQueue<Integer> queue) {
        if(root != null) {
            queue.add(root.val);
            addValuesI(root.left, queue);
            addValuesI(root.right, queue);
        }
    }

}
