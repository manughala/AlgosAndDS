package com.leetcode.facebook.design;

import com.leetcode.facebook.treesandgraphs.BuildBinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Serialize and Deserialize Binary Tree

 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 Example:

 You may serialize the following tree:

 1
 / \
 2   3
 / \
 4   5

 as "[1,2,3,null,null,4,5]"
 Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 * @author Santosh Manughala (SM030146).
 */
public class SerializeAndDeserializeBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    private static String delimiter = ", ", leafValue = "null";

    public static void main(String args[]) {
        TreeNode node = new TreeNode(-1);
        node.left = new TreeNode(0);
        node.right = new TreeNode(1);
//        node.left.left = new TreeNode(3);
//        node.left.right = new TreeNode(4);
//        node.left.left.right = new TreeNode(8);
//        node.left.left.left = new TreeNode(9);
//        node.right = new TreeNode(5);
//        node.right.left = new TreeNode(6);
//        node.right.right = new TreeNode(7);

        String serializedString = serialize(node);
        System.out.println("serializedString = " + serializedString);

        TreeNode deserialized = deserialize(serializedString);
        System.out.println(deserialized.val);
    }

    // Encodes a tree to a single string.
    // Used preorder binary tree traversal
    // Time: O(n)
    // Space: O(n)
    private static String serialize(TreeNode root) {
        if(root == null) {
            return null;
        }

        if(root.left == null && root.right == null) {
            return Integer.toString(root.val);
        }

        String result = "";
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode temp = stack.pop();

            if(temp == null) {
                result += leafValue + delimiter;
                continue;
            }

            result += temp.val + delimiter;

            stack.push(temp.right);
            stack.push(temp.left);
        }

        //NOTE: MAKE SURE TO REMOVE THE last ", " at the end -> also note that there is space after comma, so we have to skip last 2 chars
        // I guess it does not matter even if we return with , since we will deserialize it
        return result.substring(0, result.length() - 2);

        // SAME CODE RECURSIVE
//        return recursiveSerialization(root, "");
    }

    private static String recursiveSerialization(TreeNode root, String result) {
        if(root == null) {
            result += leafValue + delimiter;
            return result;
        }

        result += root.val + delimiter;
        result = recursiveSerialization(root.left, result);
        result = recursiveSerialization(root.right, result);
        return result;
    }

    // Decodes your encoded data to tree.
    // Time: O(n)
    // Space: O(n)
    private static TreeNode deserialize(String data) {
        String[] nodes = data.split(delimiter);

        return recursiveDeserialize(new LinkedList<>(Arrays.asList(nodes)));
    }

    private static TreeNode recursiveDeserialize(List<String> nodes) {
        if(nodes.get(0).equals(leafValue)) {
            nodes.remove(0);
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(nodes.get(0)));
        nodes.remove(0);
        node.left = recursiveDeserialize(nodes);
        node.right = recursiveDeserialize(nodes);

        return node;
    }
}
