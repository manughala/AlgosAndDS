package com.leetcode.amazon.explore.linkedlists;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 Example:

 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5

 Note:

 Only constant extra memory is allowed.
 You may not alter the values in the list's nodes, only nodes itself may be changed.

 * @author Santosh Manughala (SM030146).
 */
public class ReverseNodesInKGroups {

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

        ListNode node = reverseNodesInKGroups(root, 2);
        System.out.println("after reverseListIteratively: ");
        printList(node);
    }

    private static void printList(ListNode node) {
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    private static ListNode reverseNodesInKGroups(ListNode root, int k) {
        ListNode temp = root;
        int length = 0;

        while(temp != null) {
            length++;
            temp = temp.next;
        }

        if(length < k) {
            return root;
        }

        ListNode dummy = new ListNode(0), result = dummy, prev, curr;
        for(int i = 0; i < (length / k); i++) {
            prev = null;
            curr = root;

            for(int j = 0; j < k; j++) {
                ListNode next = root.next;
                root.next = prev;

                prev = root;
                root = next;
            }

            dummy.next = prev;
            curr.next = root;
            dummy = curr;
        }

        return result.next;
    }
}
