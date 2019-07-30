package com.leetcode.facebook.treesandgraphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given two binary trees, write a function to check if they are the same or not.

 Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 Example 1:

 Input:     1         1
 / \       / \
 2   3     2   3

 [1,2,3],   [1,2,3]

 Output: true
 Example 2:

 Input:     1         1
 /           \
 2             2

 [1,2],     [1,null,2]

 Output: false
 Example 3:

 Input:     1         1
 / \       / \
 2   1     1   2

 [1,2,1],   [1,1,2]

 Output: false

 * @author Santosh Manughala (SM030146).
 */
public class SameTree {

    private static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof TreeNode)) {
                return false;
            }

            if(((TreeNode) o).value == value) {
                if(((TreeNode) o).left == left && (((TreeNode) o).right == right)) {
                    return true;
                }

                return ((((TreeNode) o).right).equals(right)) && ((((TreeNode) o).left).equals(left));
            }

            return false;
        }
    }

    public static void main (String arg[]){
        TreeNode node1 = new TreeNode(1);
        node1.left = new TreeNode(2);
        node1.right = new TreeNode(3);

        TreeNode node2 = new TreeNode(1);
        node2.right = new TreeNode(4);

        if(node1 == null || node2 == null) {
            return;
        }

        boolean result = node1.equals(node2);
//        boolean result = isSameTree(node1, node2);

        System.out.println("is same tree: " + result);
//        printBFS(node1);
//        printBFS(node2);
        return;
    }

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        if(p.value == q.value) {
            return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
        }
        return false;
    }

    public static void printBFS(TreeNode node) {

        // BFS printing
        System.out.println("BFS PRINTING");
        Queue<TreeNode> queue = new LinkedList<>();

        System.out.println(node.value);
        queue.add(node);

        while(!queue.isEmpty()) {

            TreeNode temp = queue.poll();
            if(temp.left != null) {
                System.out.println(temp.left.value);
                queue.add(temp.left);
            }

            if(temp.right != null) {
                System.out.println(temp.right.value);
                queue.add(temp.right);
            }
        }
    }
}
