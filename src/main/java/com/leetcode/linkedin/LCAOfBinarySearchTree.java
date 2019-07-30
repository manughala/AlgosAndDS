package com.leetcode.linkedin;

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
public class LCAOfBinarySearchTree {

    // Note: the difference between this and LCAOfBinaryTree is that, in this we can use binary search tree property
    // meaning, left is less, right is more always
    private static class TreeNode {
        int value;
        TreeNode left, right;
        TreeNode(int value) {
            this.value = value;
        }
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        System.out.println(lowestCommonAncestorIter(root, root.left, root.left.right).value);
        System.out.println(lowestCommonAncestorRecur(root, root.left, root.left.right).value);
    }

    // Time: O(n)
    // Space: O(n)
    private static TreeNode lowestCommonAncestorRecur(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) {
            return null;
        }

        int pVal = p.value, qVal = q.value;
        int parentVal = root.value;

        if(pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestorRecur(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestorRecur(root.left, p, q);
        } else {
            return root;
        }
    }



    // Time: O(n)
    // Space: O(1)
    private static TreeNode lowestCommonAncestorIter(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) {
            return null;
        }

        TreeNode temp = root;

        while(temp != null) {
            if(p.value > temp.value && q.value > temp.value) {
                temp = temp.right;
            } else if (p.value < temp.value && q.value < temp.value) {
                temp = temp.left;
            } else {
                return temp;
            }
        }
        return null;
    }
}
