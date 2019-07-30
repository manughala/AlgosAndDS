package com.leetcode.facebook.treesandgraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * Given a binary tree, return all root-to-leaf paths.

 Note: A leaf is a node with no children.

 Example:

 Input:

 1
 /   \
 2     3
 \
 5

 Output: ["1->2->5", "1->3"]

 Explanation: All root-to-leaf paths are: 1->2->5, 1->3

 * @author Santosh Manughala (SM030146).
 */
public class BinaryTreePaths {

    private static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value) {
            this.value = value;
        }
    }
    public static void main(String arg[]) {
//        TreeNode node = new TreeNode(2);
//        node.left = new TreeNode(1);
//        node.right = new TreeNode(3);

//        TreeNode node = new TreeNode(5);
//        node.left = new TreeNode(1);
//        node.right = new TreeNode(4);
//        node.right.right = new TreeNode(6);
//        node.left.left = new TreeNode(3);

        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(5);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(25);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(6);

//        TreeNode node = new TreeNode(10);
//        node.left = new TreeNode(5);
//        node.right = new TreeNode(20);
//        node.right.left = new TreeNode(9);
//        node.right.right = new TreeNode(25);
//        node.left.left = new TreeNode(3);
//        node.left.right = new TreeNode(6);



//         List<String> paths = getBinaryTreePathsI(node);
         List<String> paths = getBinaryTreePathsII(node);

        for(String path: paths) {
            System.out.println("path = " + path);
            System.out.println("");
        }
    }


    // Time O(n) Space O(n)
    public static List<String> getBinaryTreePathsI(TreeNode root) {
        LinkedList<String> paths = new LinkedList<>();
        getPaths(root, "", paths);
        return paths;
    }

    private static void getPaths(TreeNode root, String currentPath, LinkedList paths) {
        if(root != null) {
            currentPath += root.value;

            if(root.left == null && root.right == null) {
                paths.add(currentPath);
            }
            currentPath += "->";
            getPaths(root.left, currentPath, paths);
            getPaths(root.right, currentPath, paths);
        }
    }


    // Time O(n) Space O(n)
    public static List<String> getBinaryTreePathsII(TreeNode root) {
        List<String> paths = new ArrayList<> ();
        if(root == null) {
            return paths;
        }

        Stack<String> currentPath = new Stack<>();
        Stack<TreeNode> nodes = new Stack<> ();

        nodes.add(root);
        currentPath.add(Integer.toString(root.value));

        while(!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            String path = currentPath.pop();

            if(node.left == null && node.right == null) {
                paths.add(path);
            }

            if(node.left != null) {
                currentPath.add(path + "->" + node.left.value);
                nodes.add(node.left);
            }

            if(node.right != null) {
                currentPath.add(path + "->" + node.right.value);
                nodes.add(node.right);
            }
        }

        return paths;
    }


}
