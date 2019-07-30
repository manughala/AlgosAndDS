package com.leetcode.microsoft.treesandgraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
 \
 2
 /
 3

 Output: [1,3,2]
 Follow up: Recursive solution is trivial, could you do it iteratively?

 * @author Santosh Manughala (SM030146).
 */
public class BinaryTreeInorderTraversal {
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


        List<Integer> result = printInOrderIter(node);
        System.out.println("ITER: ");
        for(int r : result) {
            System.out.println(r);
        }

        List<Integer> result2 = printInOrderRecur(node);
        System.out.println("RECUR: ");
        for(int r : result2) {
            System.out.println(r);
        }
    }

    private static List<Integer> printInOrderIter(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        if(node == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();

        while(!stack.isEmpty() || node != null) {
            while(node != null) {
                stack.add(node);
                node = node.left;
            }

            TreeNode temp = stack.pop();

            result.add(temp.val);
            node = temp.right;
        }
        return result;
    }

    private static List<Integer> result2 = new ArrayList<>();
    private static List<Integer> printInOrderRecur(TreeNode node) {
        if(node == null) {
            return result2;
        }

        printInOrderRecur(node.left);
        result2.add(node.val);
        printInOrderRecur(node.right);

        return result2;
    }
}
