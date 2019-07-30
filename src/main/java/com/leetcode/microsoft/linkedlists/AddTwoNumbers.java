package com.leetcode.microsoft.linkedlists;

import com.leetcode.linkedin.PaintHouse;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example:

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.

 * @author Santosh Manughala (SM030146).
 */
public class AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        ListNode node1 = new ListNode(2);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(3);

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

    // Time: O(n) -> max number of elements in a list
    // Space: O(n) -> result list
    private static ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        if(node1 == null && node2 == null) {
            return null;
        }

        int carry = 0;
        ListNode dummy = new ListNode(0), result = dummy;

        while(node1 != null || node2 != null) {
            int currentSum = (node1 == null ? 0 : node1.val) + (node2 == null ? 0 : node2.val) + carry;

            int currentDigit = currentSum % 10;
            carry = currentSum / 10;

            dummy.next = new ListNode(currentDigit);
            dummy = dummy.next;
            node1 = node1 == null ? null : node1.next;
            node2 = node2 == null ? null : node2.next;
        }

        if(carry > 0) {
            dummy.next = new ListNode(carry);
        }

        return result.next;
    }
}
