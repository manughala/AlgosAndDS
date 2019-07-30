package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

 Note:

 Given target value is a floating point.
 You may assume k is always valid, that is: k ≤ total nodes.
 You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 Example:

 Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

 4
 / \
 2   5
 / \
 1   3

 Output: [4,3]
 Follow up:
 Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

 * @author Santosh Manughala (SM030146).
 */
public class ClosestBSTValueII {
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
        System.out.println(closetValueLinearTime(node, target, 2));
        //NOTE: DID NOT PRACTICE
        System.out.println(closetValueBestCase(node, target, 2));

    }

    // time O(n)
    // space O(n)
    private static List<Integer> closetValueLinearTime(TreeNode node, double target, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        closestKValuesRecur(node, target, k, queue);

        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }

    private static void closestKValuesRecur(TreeNode node, double target, int k, LinkedList<Integer> queue) {
        if(node == null) {
            return;
        }

        closestKValuesRecur(node.left, target, k, queue);

        if(queue.size() < k) {
            queue.add(node.val);
        } else {
            if(Math.abs(queue.peek() - target) > Math.abs(node.val - target)) {
                queue.removeFirst();
                queue.add(node.val);
            } else {
                return;
            }
        }

        closestKValuesRecur(node.right, target, k, queue);
    }

    // NOTE DID NOT PRACTICE
    // time O(logn+ k)
    // space O(n)
    // refer: https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/246944/two-stack-%2B-predecessor-%2B-successor-simple-java-solution
    // https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/281703/From-O(n)-to-O(logn%2Bk)-in-extreme-details
    // https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/178984/Java-O(k-%2B-logN)-solution-should-be-the-fastest-solution-ever-with-explanation
    private static List<Integer> closetValueBestCase(TreeNode root, double target, int k) {
        Stack<TreeNode> smallerPath = new Stack<>();
        findSmallPath(root, target,  smallerPath);
        Stack<TreeNode> largePath = new Stack<>();
        findlargePath(root, target, largePath);

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < k; ++i) {

            TreeNode smaller =  smallerPath.isEmpty() ? null : smallerPath.peek();
            TreeNode larger = largePath.isEmpty() ? null : largePath.peek();

            if(smaller == null && larger == null) {
                break;
            }

            if(smaller == null && larger != null) {
                res.add(largePath.pop().val);
                findSuccessor(largePath, larger);
            } else if (larger == null && smaller != null){
                res.add(smallerPath.pop().val);
                findPredecessor(smallerPath, smaller);
            } else if(target - smaller.val <= larger.val - target) {
                res.add(smallerPath.pop().val);
                findPredecessor(smallerPath, smaller);
            } else {
                res.add(largePath.pop().val);
                findSuccessor(largePath, larger);
            }
        }

        return res;
    }

    private static void findSmallPath(TreeNode root, double target, Stack<TreeNode> stack) {
        while(root != null) {
            if(root.val < target) {
                stack.push(root);
                root = root.right;
            } else {
                root = root.left;
            }
        }
    }

    private static void findlargePath(TreeNode root, double target, Stack<TreeNode> stack) {
        while(root != null) {
            if(root.val >= target) {
                stack.push(root);
                if(root.val == target) {
                    break;
                }
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }

    private static void findPredecessor(Stack<TreeNode> stack, TreeNode node) {
        TreeNode curr = node.left;
        while(curr != null) {
            stack.push(curr);
            curr = curr.right;
        }
    }

    private static void findSuccessor(Stack<TreeNode> stack, TreeNode node) {
        TreeNode curr = node.right;
        while(curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }
}
