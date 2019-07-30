package com.leetcode.linkedin;

import com.leetcode.facebook.linkedlists.ReverseLinkedLists;
import practice.algorithms.BinarySearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.



 Example:

   7

 3    15

    9   20

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

//    Queue<TreeNode> queue;
//
//    BinarySearchTreeIterator(TreeNode node) {
//        queue = new LinkedList<>();
//
//        recurAdd(node);
//    }
//
//    private void recurAdd(TreeNode node) {
//        if(node == null) {
//            return;
//
//
//        recurAdd(node.left);
//        queue.add(node);
//        recurAdd(node.right);
//    }
//
//    public int next() {
//        return queue.remove().val;
//    }
//
//    public boolean hasNext() {
//        return !queue.isEmpty();
//    }


    Queue<TreeNode> queue;

    BinarySearchTreeIterator(TreeNode node) {
        queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            queue.add(node);
            node = node.right;
        }
    }

    public int next() {
        return queue.remove().val;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}


