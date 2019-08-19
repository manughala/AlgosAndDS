package com.leetcode.amazon.explore.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or
 * memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization
 algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized
 to the original tree structure.

 Example:

 You may serialize the following tree:

    1
  /  \
 2   3
    / \
   4   5

 as "[1,2,3,null,null,4,5]"
 Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format,
 so please be creative and come up with different approaches yourself.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 * @author Santosh Manughala (SM030146).
 */
public class SerializeAndDeserializeBinaryTree {
    public static void main(String args[]) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(5);

        SerializeAndDeserializeBinaryTree serializer = new SerializeAndDeserializeBinaryTree();

        System.out.println("Iterative: Serialized node: " + serializer.serialize(node));
        System.out.println("Recurrence: Serialized node: " + serializer.serializeRecur(node));

        TreeNode root = serializer.deserialize(serializer.serialize(node));
        System.out.println(root.val);
    }

    private static String LEAF_VALUE = "null", DELIMITER = ", ";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder =  new StringBuilder();

        // IF you use queue, got to make sure we account for leaf nodes this way.
        // If we use stack, we can just add the nodes left and right direct to stack,
        // and since left comes last every time we can add leaf nodes in the right order.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            builder.append(node.val + DELIMITER);

            if(node.left == null && node.right == null) {
                builder.append(LEAF_VALUE + DELIMITER + LEAF_VALUE + DELIMITER);
                continue;
            }

            if(node.left != null) {
                queue.add(node.left);
            }

            if(node.right != null) {
                queue.add(node.right);
            }

//            if(node == null) {
//                builder.append(LEAF_VALUE + DELIMITER);
//                continue;
//            }

//            builder.append(node.val + DELIMITER);
//            stack.add(node.right);
//            stack.add(node.left);
        }

        return builder.toString();
    }

    public String serializeRecur(TreeNode root) {
        return serializeRecur(root, "");
    }

    private String serializeRecur(TreeNode root, String result) {
        if(root == null) {
            result += LEAF_VALUE + DELIMITER;
            return result;
        }

        result += root.val + DELIMITER;
        result = serializeRecur(root.left, result);
        result = serializeRecur(root.right, result);
        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataNodes = data.split(DELIMITER);
        return deserializeRecur(new LinkedList<>(Arrays.asList(dataNodes)));
    }

    private TreeNode deserializeRecur(List<String> nodes) {
        String currentVal = nodes.remove(0);

        if(currentVal.equals(LEAF_VALUE)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(currentVal));

        root.left = deserializeRecur(nodes);
        root.right = deserializeRecur(nodes);
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}
