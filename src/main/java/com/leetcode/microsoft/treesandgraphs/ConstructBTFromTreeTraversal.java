package com.leetcode.microsoft.treesandgraphs;

import java.util.HashMap;
import java.util.Map;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.

 For example, given

 preorder = [3,9,20,15,7]
 inorder = [9,3,15,20,7]
 Return the following binary tree:

   3
  /  \
 9   20
    /  \
   15   7
 * @author Santosh Manughala (SM030146).
 */
public class ConstructBTFromTreeTraversal {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        int[] preOrder = new int[]{3, 9, 20, 15, 7};
        int[] inOrder = new int[]{9, 3, 15, 20, 7};

        TreeNode node = getBinaryTree(inOrder, preOrder);

        printPreOrder(node);

    }

    private static TreeNode getBinaryTree(int[] inOrder, int[] preOrder) {
        if(inOrder == null || inOrder.length == 0 || preOrder == null || preOrder.length == 0) {
            return null;
        }

        Map<Integer, Integer> inOrderValToIdx = new HashMap<>();
        for(int i = 0; i < inOrder.length; i++) {
            inOrderValToIdx.put(inOrder[i], i);
        }

        return performBinaryTreeRecur(0, inOrder.length, inOrderValToIdx, preOrder);
    }

    private  static int preOrderIdx = 0;
    private static TreeNode performBinaryTreeRecur(int leftIdx, int rightIdx, Map<Integer, Integer> inOrderValToIdx, int[] preOrder) {
        if(leftIdx == rightIdx) {
            return null;
        }

        int rootVal = preOrder[preOrderIdx++];
        TreeNode root = new TreeNode(rootVal);
        int index = inOrderValToIdx.get(rootVal);

        root.left = performBinaryTreeRecur(leftIdx, index, inOrderValToIdx, preOrder);
        root.right = performBinaryTreeRecur(index + 1, rightIdx, inOrderValToIdx, preOrder);

        return root;
    }

    private static void printPreOrder(TreeNode node) {
        if(node == null) {
            return;
        }

        System.out.println(node.val);
        printPreOrder(node.left);
        printPreOrder(node.right);
    }
}
