package com.leetcode.linkedin;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]




 Example 1:

 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 Output: 3
 Explanation: The LCA of nodes 5 and 1 is 3.
 Example 2:

 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 Output: 5
 Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


 Note:

 All of the nodes' values will be unique.
 p and q are different and both values will exist in the binary tree.

 * @author Santosh Manughala (SM030146).
 */
public class LCAOfBinaryTree {
    private static class TreeNode {
        int value;
        TreeNode left, right;
        TreeNode(int value) {
            this.value = value;
        }
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        System.out.println(lowestCommonAncestorIter(root, root.left, root.left.right.right).value);
        System.out.println(lowestCommonAncestorRecur(root, root.left, root.left.right.right).value);
    }

    // Time: O(n)
    // Space: O(n)
    static TreeNode result = null;
    private static TreeNode lowestCommonAncestorRecur(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) {
            return null;
        }

        recurHelper(root, p, q);
        return result;
    }

    private static boolean recurHelper(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return false;
        }

        int left = recurHelper(root.left, p, q) ? 1 : 0;
        int right = recurHelper(root.right, p, q) ? 1 : 0;
        int found = (root == p || root == q) ? 1 : 0;

        if(left + right + found >= 2) {
            result = root;
        }

        return left + right + found > 0;
    }

    // Time: O(n)
    // Space: O(n)
    private static TreeNode lowestCommonAncestorIter(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, TreeNode> map = new HashMap<>();

        stack.push(root);
        map.put(root, null);

        while ((!map.containsKey(p) || !map.containsKey(q)) && !stack.isEmpty()) {
            TreeNode node = stack.pop();

            if(node.left != null) {
                map.put(node.left, node);
                stack.push(node.left);
            }

            if(node.right != null) {
                map.put(node.right, node);
                stack.push(node.right);
            }
        }

        Set<TreeNode> set = new HashSet<>();
        while(p != null) {
            set.add(p);
            p = map.get(p);
        }

        while(!set.contains(q)) {
            q = map.get(q);
        }

        return q;
    }
}
