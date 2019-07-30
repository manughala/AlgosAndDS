package com.leetcode.facebook.design;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.

     7
    / \
   3  15
     /  \
    9   20

 Example:

 BSTIterator iterator = new BSTIterator(root);
 iterator.next();    // return 3
 iterator.next();    // return 7
 iterator.hasNext(); // return true
 iterator.next();    // return 9
 iterator.hasNext(); // return true
 iterator.next();    // return 15
 iterator.hasNext(); // return true
 iterator.next();    // return 20
 iterator.hasNext(); // return false


 Note:

 next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 *
 * @author Santosh Manughala (SM030146).
 */
public class BinarySearchTreeIterator {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Initial thought : in order traversal would work: we can store the values in a queue and return in O(1) time
    public static void main(String args[]) {
        TreeNode node = new TreeNode(7);
        node.left = new TreeNode(3);
        node.right = new TreeNode(15);
        node.right.left = new TreeNode(9);
        node.right.right = new TreeNode(20);

        BinarySearchTreeIterator iterator = new BinarySearchTreeIterator(node);
        while (iterator.hasNext()) {
            System.out.println("Next value : " + iterator.next());
        }
    }

    // RECURSIVE
    Queue<TreeNode> queue = null;

    public BinarySearchTreeIterator(TreeNode root) {
        queue = new LinkedList<>();
        recurAdd(root);
    }

    private void recurAdd(TreeNode root) {
        if(root == null) {
            return;
        }

        recurAdd(root.left);
        queue.add(root);
        recurAdd(root.right);
    }

    /** @return the next smallest number */
    public int next() {
        return queue.poll().val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

//    // ITERATIVE
//     Queue<TreeNode> queue = null;
//
//    public BinarySearchTreeIterator(TreeNode root) {
//        queue = new LinkedList<>();
//        Stack<TreeNode> stack = new Stack<>();
//
//        while (root != null || !stack.isEmpty()) {
//            while(root != null) {
//                stack.push(root);
//                root = root.left;
//            }
//
//            root = stack.pop();
//
//            queue.add(root);
//            root = root.right;
//        }
//    }
//
//    /** @return the next smallest number */
//    public int next() {
//        return queue.poll().val;
//    }
//
//    /** @return whether we have a next smallest number */
//    public boolean hasNext() {
//        return !queue.isEmpty();
//    }
}
