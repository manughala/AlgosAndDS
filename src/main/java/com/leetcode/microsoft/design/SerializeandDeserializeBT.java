package com.leetcode.microsoft.design;

import com.leetcode.linkedin.TextJustification;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

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
public class SerializeandDeserializeBT {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(5);

        System.out.println("before");
        printNode(node);

        String serializedString = serialize(node);
        System.out.println("serialized string : " + serializedString);

        System.out.println("deserialization : ");
        printNode(deserialize(serializedString));
    }

    private static String leafVal = "null", delimeter = ", ";
    // Encodes a tree to a single string.
    private static String serialize(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        StringBuilder stringBuilder = new StringBuilder();

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            if(temp == null) {
                stringBuilder.append(leafVal + delimeter);
                continue;
            }

            stringBuilder.append(temp.val + delimeter);

            queue.add(temp.left);
            queue.add(temp.right);
        }

        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    private static TreeNode deserialize(String data) {
        String[] dataNodes = data.split(delimeter);

        return performRecur(new LinkedList<>(Arrays.asList(dataNodes)));
    }

    private static TreeNode performRecur(List<String> nodes) {
        if(nodes.get(0).equals(leafVal)) {
            nodes.remove(0);
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(nodes.get(0)));
        nodes.remove(0);
        node.left = performRecur(nodes);
        node.right = performRecur(nodes);
        return node;
    }

    private static void printNode(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            System.out.println(temp.val);

            if(temp.left != null) {
                queue.add(temp.left);
            }

            if(temp.right != null) {
                queue.add(temp.right);
            }
        }
    }
}
