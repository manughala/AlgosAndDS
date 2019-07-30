package com.leetcode.facebook.treesandgraphs;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

 Example:
 Given a binary tree
 1
 / \
 2   3
 / \
 4   5
 Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

 Note: The length of path between two nodes is represented by the number of edges between them.

 * @author Santosh Manughala (SM030146).
 */
public class DiameterBinaryTree {
    static int numberOfNodes = 0;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String args[]) {
        /* creating a binary tree and entering the nodes */
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.left.left = new TreeNode(3);
        tree.left.right = new TreeNode(4);
        tree.left.right.left = new TreeNode(5);
        tree.left.right.right = new TreeNode(6);
        tree.right = new TreeNode(7);
        tree.right.right = new TreeNode(8);
        tree.right.right.right = new TreeNode(9);
        tree.right.right.right.left = new TreeNode(10);
        tree.right.right.right.left.right = new TreeNode(11);
        tree.right.right.right.right = new TreeNode(12);

//        int diameter = diameterBruteForce(tree);
        int diameter = diameterBetterCase(tree);
        System.out.println(diameter);
    }

    //TIME O(n^2) space O(n) i think since we are storing the diameter of n objects
    private static int diameterBruteForce(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int heightLeft = heightBruteForce(node.left);
        int heightright = heightBruteForce(node.right);

        int diameterLeft = diameterBruteForce(node.left);
        int diameterRight = diameterBruteForce(node.right);

        return Math.max(heightLeft+heightright+1, Math.max(diameterLeft, diameterRight));

    }

    private static int heightBruteForce(TreeNode node) {
        if(node == null) {
            return 0;
        }

        return 1 + Math.max(heightBruteForce(node.left), heightBruteForce(node.right));
    }


    //TIme O(n) same space
    private static int diameterBetterCase (TreeNode node) {
        numberOfNodes = 1;
        diameter(node);
        return numberOfNodes - 1;
    }

    // Same as above approach but returning the height while updating the diamter in the same method, so we dont have to go over this twice
    private static int diameter (TreeNode node) {
        if(node == null) {
            return 0;
        }

        int left = diameter(node.left);
        int right = diameter(node.right);

        numberOfNodes = Math.max(numberOfNodes, left+right+1);
        return 1 + Math.max(left, right);
    }
}
