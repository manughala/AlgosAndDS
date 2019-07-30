package com.leetcode.microsoft.treesandgraphs;

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
public class LowestCommonAncestorBT {

    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(5);
        node.left.left = new TreeNode(6);
        node.left.right = new TreeNode(2);
        node.left.right.left = new TreeNode(7);
        node.left.right.right = new TreeNode(4);

        node.right = new TreeNode(1);
        node.right.left = new TreeNode(0);
        node.right.right = new TreeNode(8);


        System.out.println("Expected= 3, Actual = " + lowestCommonAncestorBT(node, node.left, node.right).val);
        System.out.println("Expected= 5, Actual = " + lowestCommonAncestorBT(node, node.left, node.left.right.right).val);
    }

    // Time O(n)
    // Space O(n)
    private static TreeNode lowestCommonAncestorBT(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null || p == null || q == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        Map<TreeNode, TreeNode> nodeToParentMap = new HashMap<>();
        nodeToParentMap.put(node, null);

        while (!stack.isEmpty() || !nodeToParentMap.containsKey(p) || !nodeToParentMap.containsKey(q)) {
            TreeNode temp = stack.pop();

            if(temp.left != null ) {
                stack.push(node.left);
                nodeToParentMap.put(node.left, node);
            }

            if(temp.right != null) {
                stack.push(node.right);
                nodeToParentMap.put(node.right, node);
            }
        }

        Set<TreeNode> setP = new HashSet<>();
        while(p != null) {
            setP.add(p);
            p = nodeToParentMap.get(p);
        }

        while(!setP.contains(q)) {
            q = nodeToParentMap.get(q);
        }

        return q;
    }
}
