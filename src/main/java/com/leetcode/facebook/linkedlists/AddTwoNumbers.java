package com.leetcode.facebook.linkedlists;

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
        int value;
        ListNode next;
        ListNode(int value) {
            this.value = value;
        }
    }

    public static void main(String args[]) {
        ListNode node1 = new ListNode(2);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(3);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(4);

        if(node1 == null || node2 == null) {
            System.out.println("Invalid input");
            return;
        }

        ListNode result = addTwoNumbersBruteForce(node1, node2);
        while(result != null) {
            System.out.println(result.value);
            result = result.next;
        }
     }

    private static ListNode addTwoNumbersBruteForce(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(0), result = dummy, temp = null;
        int carry = 0;

        while(node1 != null || node2 != null) {

            int currentDigit = (node1 != null ? node1.value : 0) + (node2 != null ? node2.value : 0) + carry;
            carry = currentDigit >= 10 ? 1 : 0;
            currentDigit = currentDigit % 10;

            temp = new ListNode(currentDigit);

            dummy.next = temp;
            dummy = dummy.next;

            if(node1 != null) {
                node1 = node1.next;
            }
            if(node2 != null) {
                node2 = node2.next;
            }
        }

        if(carry > 0) {
            temp.next = new ListNode(carry);
        }


        return result.next;
    }
}
