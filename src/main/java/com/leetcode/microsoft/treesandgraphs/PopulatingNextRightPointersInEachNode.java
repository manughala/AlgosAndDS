package com.leetcode.microsoft.treesandgraphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

 struct Node {
 int val;
 Node *left;
 Node *right;
 Node *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.



 Example:



 Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

 Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}

 Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.


 Note:

 You may only use constant extra space.
 Recursive approach is fine, implicit stack space does not count as extra space for this problem.

 * @author Santosh Manughala (SM030146).
 */
public class PopulatingNextRightPointersInEachNode {
    private static class TreeNode {
        int val;
        TreeNode left, right, next;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);

        System.out.println("before: ");
        printBFS(node);

        populateNextRightPointerLevelOrder(node);

        System.out.println("after: ");
        printBFS(node);

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);

        System.out.println("before: ");
        printBFS(node);

        populateNextRightPointerPreOrder(node);

        System.out.println("after: ");
        printBFS(node);

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);

        System.out.println("before: ");
        printBFS(node);

        populateNextRightPointerLevelOrderAnotherWay(node);

        System.out.println("after: ");
        printBFS(node);

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);

        System.out.println("before: ");
        printBFS(node);

        populateNextRightPointerBestCaseIter(node);

        System.out.println("after: ");
        printBFS(node);

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);

        System.out.println("before: ");
        printBFS(node);

        populateNextRightPointerBestCaseRecur(node);

        System.out.println("after: ");
        printBFS(node);
    }

    private static void printBFS(TreeNode node) {
        if (node == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            System.out.print(temp.val);
            System.out.println(temp.next == null ? "null" : temp.next.val);

            if (temp.left != null) {
                queue.add(temp.left);
            }

            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }

    // Time O(n)
    // Space O(n)  -> Since implicit stack space does not count as extra space for this problem, we are good with this solution
    private static void populateNextRightPointerBestCaseRecur(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.next != null) {
            populateNextRightPointerBestCaseRecur(node.next);
        }

        if (node.left != null) {
            if (node.right != null) {
                node.left.next = node.right;
                node.right.next = getNextRight(node);
            } else {
                node.left.next = getNextRight(node);
            }
            populateNextRightPointerBestCaseRecur(node.left);
        } else if (node.right != null) {
            node.right.next = getNextRight(node);
            populateNextRightPointerBestCaseRecur(node.right);
        } else {
            populateNextRightPointerBestCaseRecur(getNextRight(node));
        }
    }

    // Time O(n)
    // Space O(1)
    private static void populateNextRightPointerBestCaseIter(TreeNode node) {
        if (node == null) {
            return;
        }

        // the root node
        node.next = null;

        while (node != null) {
            TreeNode temp = node;

            while (temp != null) {
                if (temp.left != null) {
                    if (temp.right != null) {
                        temp.left.next = temp.right;
                    } else {
                        temp.left.next = getNextRight(temp);
                    }
                }

                if (temp.right != null) {
                    temp.right.next = getNextRight(temp);
                }

                temp = temp.right;
            }

            if (node.left != null) {
                node = node.left;
            } else if (node.right != null) {
                node = node.right;
            } else {
                node = getNextRight(node);
            }
        }
    }

    private static TreeNode getNextRight(TreeNode node) {
        node = node.next;

        while (node != null) {
            if (node.left != null) {
                return node.left;
            }

            if (node.right != null) {
                return node.right;
            }

            node = node.next;
        }

        return null;
    }

    // Time O(n)
    // Space O(n)  -> Since implicit stack space does not count as extra space for this problem, we are good with this solution
    private static void populateNextRightPointerPreOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            node.left.next = node.right;
        }

        if (node.right != null) {
            // if we are setting childs next, the root should already set the next, so for the childs next,
            // we can go one level up -> next and left of that will be next to this child node.
            node.right.next = node.next == null ? null : node.next.left;
        }

        populateNextRightPointerPreOrder(node.left);
        populateNextRightPointerPreOrder(node.right);
    }

    // Time O(n)
    // Space O(n) -> we are not good with this solution as we need extra Queue for space.
    private static void populateNextRightPointerLevelOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            if (temp != null) {
                temp.next = queue.peek();

                if (temp.left != null) {
                    queue.add(temp.left);
                }

                if (temp.right != null) {
                    queue.add(temp.right);
                }
            } else if (!queue.isEmpty()) {
                queue.add(null);
            }
        }
    }

    // Time O(n)
    // Space O(n) -> we are not good with this solution as we need extra Queue for space.
    private static void populateNextRightPointerLevelOrderAnotherWay(TreeNode node) {
        if (node == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList();

        queue.add(node);

        while (!queue.isEmpty()) {

            int size = queue.size();
            TreeNode next = null;

            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();

                temp.next = next;
                next = temp;

                if (temp.right != null) {
                    queue.add(temp.right);
                }

                if (temp.left != null) {
                    queue.add(temp.left);
                }
            }
        }
    }
}
