package com.leetcode.microsoft.linkedlists;

/**
 * Reverse a singly linked list.

 Example:

 Input: 1->2->3->4->5->NULL
 Output: 5->4->3->2->1->NULL
 Follow up:

 A linked list can be reversed either iteratively or recursively. Could you implement both?

 * @author Santosh Manughala (SM030146).
 */
public class ReverseLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);

        System.out.println("Before: ");
        printList(root);

        ListNode node = reverseListIteratively(root);
        System.out.println("after reverseListIteratively: ");
        printList(node);

        ListNode root2 = new ListNode(1);
        root2.next = new ListNode(2);
        root2.next.next = new ListNode(3);
        root2.next.next.next = new ListNode(4);
        root2.next.next.next.next = new ListNode(5);

        node = reverseListRecursively(root2);
        System.out.println("after reverseListRecursively: ");
        printList(node);
    }

    private static void printList(ListNode node) {
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    // Time O(n) -> num of elements in the list
    // Space O(1)
    private static ListNode reverseListIteratively(ListNode root) {
        if(root == null || root.next == null) {
            return root;
        }

        ListNode curr = root, prev = null, next = null;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    // Time O(n) -> num of elements in the list
    // Space O(1)
    private static ListNode reverseListRecursively(ListNode root) {
        return recurReverse(root, null);
    }

    private static ListNode recurReverse(ListNode curr, ListNode prev) {
        ListNode next = curr.next;
        curr.next = prev;

        return next == null ? curr : recurReverse(next, curr);
    }
}
