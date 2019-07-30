package com.leetcode.linkedin;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

 Note:

 Given target value is a floating point.
 You are guaranteed to have only one unique value in the BST that is closest to the target.
 Example:

 Input: root = [4,2,5,1,3], target = 3.714286

 4
 / \
 2   5
 / \
 1   3

 Output: 4
 * @author Santosh Manughala (SM030146).
 */
public class ClosestBSTValue {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Initial thought : in order traversal would work: we can store the values in a queue and return in O(1) time
    public static void main(String args[]) {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.right = new TreeNode(5);
        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);

        double target = 3.714286;
        System.out.println(closetValueIter(node, target));
        System.out.println(closetValueRecur(node, target));

    }

    private static int closetValueIter(TreeNode root, double target) {
        double min = Double.MAX_VALUE;
        int result = -1;

        while(root != null) {
            if(root.val > target) {
                if(Math.abs(root.val - target) < min) {
                    min = Math.abs(root.val - target);
                    result = root.val;
                }
                root = root.left;
            } else if (root.val < target) {
                if(Math.abs(root.val - target) < min) {
                    min = Math.abs(root.val - target);
                    result = root.val;
                }
                root = root.right;
            } else {
                return root.val;
            }
        }
        return result;
    }

    static int result;
    static double min = Double.MAX_VALUE;

    private static int closetValueRecur(TreeNode root, double target) {
        helper(root, target);
        return result;
    }

    private static void helper(TreeNode root, double target) {
        if(root == null) {
            return;
        }

        if(Math.abs(root.val - target) < min) {
            min = Math.abs(root.val - target);
            result = root.val;
        }

        if(root.val < target) {
            closetValueRecur(root.right, target);
        } else if (root.val > target) {
            closetValueRecur(root.left, target);
        }
    }
}
