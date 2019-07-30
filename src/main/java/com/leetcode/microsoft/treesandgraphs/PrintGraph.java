package com.leetcode.microsoft.treesandgraphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Santosh Manughala (SM030146).
 */
public class PrintGraph {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        TreeNode node1 = new TreeNode(1);
        node1.left = new TreeNode(2);
        node1.right = new TreeNode(3);
        node1.left.left = new TreeNode(4);
        node1.left.right = new TreeNode(5);

        System.out.println("BFS ITER: ");
        System.out.println("node1: "); printBFS(node1);

        System.out.println("BFS RECUR: ");
        System.out.println("node1: "); printBFSRecur(node1);

        System.out.println("PreOrder ITER: ");
        System.out.println("node1: "); printPreOrderIter(node1);

        System.out.println("PreOrder RECUR: ");
        System.out.println("node1: "); printPreOrderRecur(node1);

        System.out.println("PostOrder ITER I: ");
        System.out.println("node1: "); printPostOrderIterI(node1);

        System.out.println("PostOrder ITER II: ");
        System.out.println("node1: "); printPostOrderIterII(node1);

        System.out.println("PostOrder RECUR: ");
        System.out.println("node1: "); printPostOrderRecur(node1);

        System.out.println("InOrder ITER: ");
        System.out.println("node1: "); printInOrderIter(node1);

        System.out.println("InOrder RECUR: ");
        System.out.println("node1: "); printInOrderRecur(node1);
    }

    private static void printBFS(TreeNode node) {
        if(node == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            System.out.print(temp.val);

            if(temp.left != null) {
                queue.add(temp.left);
            }

            if(temp.right != null) {
                queue.add(temp.right);
            }
        }
    }

    private static void printBFSRecur(TreeNode node) {
        System.out.println("N/A");
        // NOT A GOOD practice to write BFS in recur
    }

    private static void printPreOrderIter(TreeNode node) {
        if(node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(node);

        while(!stack.isEmpty()) {
            TreeNode temp = stack.pop();

            System.out.print(temp.val);

            if(temp.right != null) {
                stack.add(temp.right);
            }

            if(temp.left != null) {
                stack.add(temp.left);
            }
        }
    }

    private static void printPreOrderRecur(TreeNode node) {
        if(node == null) {
            return;
        }

        System.out.print(node.val);
        printPreOrderRecur(node.left);
        printPreOrderRecur(node.right);

    }

    // Using 2 stacks
    private static void printPostOrderIterI(TreeNode node) {
        if(node == null) {
            return;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(node);

        while(!stack1.isEmpty()) {
            TreeNode temp = stack1.pop();
            stack2.push(temp);

            if(temp.left != null) {
                stack1.push(temp.left);
            }

            if(temp.right != null) {
                stack1.push(temp.right);
            }
        }

        while(!stack2.isEmpty()) {
            System.out.println(stack2.pop().val);
        }

    }

    private static void printPostOrderIterII(TreeNode node) {
        if(node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = node;

        while(true) {
            while(current != null) {
                if(current.right != null) {
                    stack.push(current.right);
                }
                stack.push(current);
                current = current.left;
            }

            if(stack.isEmpty()) {
                return;
            }

            current = stack.pop();

            if(!stack.isEmpty() && current.right != null && current.right == stack.peek()){
                stack.pop();
                stack.push(current);
                current = current.right;
            } else {
                System.out.println(current.val);
                current = null;
            }
        }
    }

    private static void printPostOrderRecur(TreeNode node) {
        if(node == null) {
            return;
        }

        printPostOrderRecur(node.left);
        printPostOrderRecur(node.right);
        System.out.print(node.val);

    }

    private static void printInOrderIter(TreeNode node) {
        if(node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();

        while(!stack.isEmpty() || node != null) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }

            TreeNode temp = stack.pop();
            System.out.println(temp.val);

            node = temp.right;
        }
    }

    private static void printInOrderRecur(TreeNode node) {
        if(node == null) {
            return;
        }

        printInOrderRecur(node.left);
        System.out.print(node.val);
        printInOrderRecur(node.right);
    }
}
