package com.leetcode.facebook.treesandgraphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.

 For example, given

 preorder = [3,9,20,15,7]
 inorder = [9,3,15,20,7]
 Return the following binary tree:

  3
 / \
 9  20
 /  \
 15   7

 * @author Santosh Manughala (SM030146).
 */
public class BuildBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public static void main(String args[]) {
        int[] inOrder = new int[]{3,9,20,15,7}, preOrder = new int[] {9,3,15,20,7};
        TreeNode result = getBinaryTree(inOrder, preOrder);
        printInOrder(result);
    }

    private static void printInOrder(TreeNode node) {

        Stack<TreeNode> stack = new Stack<>();

        while(!stack.isEmpty() || node!= null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            TreeNode temp = stack.pop();
            System.out.println(temp.val);
            node = temp.right;
        }
    }

    private static TreeNode getBinaryTree (int[] inOrder, int[] preOrder) {
        Map<Integer, Integer> inOrderToIndex = new HashMap<>();

        for(int i = 0; i < inOrder.length; i++) {
            inOrderToIndex.put(inOrder[i], i);
        }

        return getBinaryTree(0, inOrder.length, inOrderToIndex, preOrder);
    }

    static int preOrderIndex = 0;
    private static TreeNode getBinaryTree(int leftIndex, int rightIndex, Map<Integer, Integer> inOrderToIndex, int[] preOrder) {
        // No elements in between
        if(leftIndex == rightIndex) {
            return null;
        }

        // Get current root value
        int rootValue = preOrder[preOrderIndex++];
        TreeNode root = new TreeNode(rootValue);
        int index = inOrderToIndex.get(rootValue);

        root.left = getBinaryTree(leftIndex, index, inOrderToIndex, preOrder);
        root.right = getBinaryTree(index + 1, rightIndex, inOrderToIndex, preOrder);
        return root;
    }
}
