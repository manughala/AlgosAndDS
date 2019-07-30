package com.leetcode.linkedin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
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

    private static String DELIMITER = ", ", LEAF_VALUE = "null";

    public static void main(String args[]) {
        TreeNode node = new TreeNode(-1);
        node.left = new TreeNode(0);
        node.right = new TreeNode(1);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(4);
        node.left.left.left = new TreeNode(9);
        node.left.left.right = new TreeNode(8);

        String serializedStringIter = serializeIter(node);
        System.out.println("serializedStringIter = " + serializedStringIter);

        String serializedStringRecur = serializeRecur(node);
        System.out.println("serializedStringRecur = " + serializedStringRecur);

        TreeNode deserializedIter = deserialize(serializedStringIter);
        System.out.println(deserializedIter.val);

        TreeNode deserializedRecur = deserialize(serializedStringRecur);
        System.out.println(deserializedRecur.val);
    }

    private static String serializeIter(TreeNode node) {
        String result = "";

        if(node == null) {
            return null;
        }

        if(node.left == null && node.right == null) {
            return Integer.toString(node.val) + DELIMITER + LEAF_VALUE + DELIMITER + LEAF_VALUE;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(node);

        while(!stack.isEmpty()) {
            TreeNode temp = stack.pop();

            if(temp == null) {
                result += LEAF_VALUE + DELIMITER;
                continue;
            }

            result += temp.val + DELIMITER;

            stack.add(temp.right);
            stack.add(temp.left);
        }

        return result;
    }

    private static String serializeRecur(TreeNode node) {
        String result = "";

        if(node == null) {
            return null;
        }

        if(node.left == null && node.right == null) {
            return Integer.toString(node.val) + DELIMITER + LEAF_VALUE + DELIMITER + LEAF_VALUE;
        }

        return performRecurSerialize(node, result);
    }

    private static String performRecurSerialize(TreeNode node, String result) {
        if(node == null) {
            return result + LEAF_VALUE + DELIMITER;
        }

        result += node.val + DELIMITER;
        result = performRecurSerialize(node.left, result);
        result = performRecurSerialize(node.right, result);
        return result;
    }

    private static TreeNode deserialize(String serializedString) {
        if(serializedString == null) {
            return null;
        }

        String[] nodeValues = serializedString.split(DELIMITER);

        return performDeserialize(new LinkedList<>(Arrays.asList(nodeValues)));
    }

    private static TreeNode performDeserialize(List<String> nodes) {
        String currentVal = nodes.get(0);

        if(currentVal.equals(LEAF_VALUE)) {
            nodes.remove(0);
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(currentVal));
        nodes.remove(0);

        node.left = performDeserialize(nodes);
        node.right = performDeserialize(nodes);

        return node;
    }
}
