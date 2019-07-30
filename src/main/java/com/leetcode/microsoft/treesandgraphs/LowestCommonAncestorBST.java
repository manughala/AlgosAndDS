package com.leetcode.microsoft.treesandgraphs;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]




 Example 1:

 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 Output: 6
 Explanation: The LCA of nodes 2 and 8 is 6.
 Example 2:

 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 Output: 2
 Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.


 Note:

 All of the nodes' values will be unique.
 p and q are different and both values will exist in the BST.

 * @author Santosh Manughala (SM030146).
 */
public class LowestCommonAncestorBST {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(4);

        TreeNode node = new TreeNode(6);
        node.left = p;
        p.left = new TreeNode(0);
        p.right = new TreeNode(4);
        p.right.left = new TreeNode(3);
        p.right.right = new TreeNode(5);
        node.right = q;
        q.left = new TreeNode(7);
        q.right = new TreeNode(9);

        printPreOrder(node);
        System.out.println("RECUR = " + lowestCommonAncestorBSTRecur(node, p, q).val);
        System.out.println("ITER = " + lowestCommonAncestorBSTIter(node, p, q).val);

    }

    // Time O(n)
    // Space O(n)
    private static TreeNode lowestCommonAncestorBSTRecur(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null || p == null || q == null) {
            return null;
        }

        int pVal = p.val, qVal = q.val, nodeVal = node.val;

        if(pVal > nodeVal && qVal > nodeVal) {
            return lowestCommonAncestorBSTRecur(node.right, p, q);
        } else if (pVal < nodeVal && qVal < nodeVal) {
            return lowestCommonAncestorBSTRecur(node.left, p, q);
        } else {
            return node;
        }
    }


    // Time O(n)
    // Space O(1)
    private static TreeNode lowestCommonAncestorBSTIter(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null || p == null || q == null) {
            return null;
        }

        int pVal = p.val, qVal = q.val;

        while(node != null) {
            int nodeVal = node.val;
            if(pVal > nodeVal && qVal > nodeVal) {
                node = node.right;
            } else if (pVal < nodeVal && qVal < nodeVal) {
                node = node.left;
            } else {
                return node;
            }
        }

        return null;
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
