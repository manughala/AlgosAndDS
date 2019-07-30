package com.leetcode.microsoft.linkedlists;

import java.util.Stack;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Follow up:
 What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

 Example:

 Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 8 -> 0 -> 7

 * @author Santosh Manughala (SM030146).
 */
public class AddTwoNumbersII {
    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        ListNode node1 = new ListNode(7);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(4);
        node1.next.next.next = new ListNode(3);

        System.out.println("node1: ");
        printList(node1);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(4);

        System.out.println("node2: ");
        printList(node2);

        ListNode node = addTwoNumbers(node1, node2);
        System.out.println("after addTwoNumbers: ");
        printList(node);
    }

    private static void printList(ListNode node) {
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    // Time: O(m + n)
    // Space: O(m + n)
    private static ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        if(node1 == null && node2 == null) {
            return null;
        }

        Stack<Integer> node1Stack = new Stack<>();
        Stack<Integer> node2Stack = new Stack<>();
        Stack<Integer> resultStack = new Stack<>();

        ListNode node1Copy = node1, node2Copy = node2, dummy = new ListNode(0), result = dummy;

        while(node1Copy != null) {
            node1Stack.push(node1Copy.val);
            node1Copy = node1Copy.next;
        }

        while(node2Copy != null) {
            node2Stack.push(node2Copy.val);
            node2Copy = node2Copy.next;
        }

        int carry = 0;
        while(!node1Stack.isEmpty() || !node2Stack.isEmpty()) {
            int currentSum = (node1Stack.isEmpty() ? 0 : node1Stack.pop()) + (node2Stack.isEmpty() ? 0 : node2Stack.pop()) + carry;
            int currentDigit = currentSum % 10;
            carry = currentSum / 10;

            resultStack.push(currentDigit);
        }

        if(carry > 0) {
            resultStack.push(carry);
        }

        while(!resultStack.isEmpty()) {
            dummy.next = new ListNode(resultStack.pop());
            dummy = dummy.next;
        }

        return result.next;
    }
}
