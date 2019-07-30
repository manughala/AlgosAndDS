package com.leetcode.linkedin;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.

 Example:

 Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4

 * @author Santosh Manughala (SM030146).
 */
public class MergeTwoSortedLists {


    private static class ListNode {
        int value;
        ListNode next;
        ListNode(int value){
            this.value = value;
        }
    }


    public static void main(String args[]) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(4);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode result = mergeTwoListsIterative(node1, node2);
//        ListNode result = mergeTwoListsRecursive(node1, node2);

        while(result != null) {
            System.out.println(result.value);
            result = result.next;
        }
    }

    private static ListNode mergeTwoListsIterative(ListNode node1, ListNode node2) {
        if(node1 == null && node2 == null) {
            return null;
        }

        if(node1 == null) {
            return node2;
        }

        if(node2 == null) {
            return node1;
        }

        ListNode result = new ListNode(0), dummy = result;

        while(node1 != null && node2 != null) {
            if(node1.value <= node2.value) {
                dummy.next = node1;
                node1 = node1.next;
            } else {
                dummy.next = node2;
                node2 = node2.next;
            }
            dummy = dummy.next;
        }

        dummy.next = node1 == null ? node2 : node1;
        return result.next;
    }

    private static ListNode mergeTwoListsRecursive (ListNode node1, ListNode node2) {
        if(node1 == null && node2 == null) {
            return null;
        }

        if(node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        } else if (node1.value <= node2.value) {
            node1.next = mergeTwoListsRecursive(node1.next, node2);
            return node1;
        } else {
            node2.next = mergeTwoListsRecursive(node1, node2.next);
            return node2;
        }
    }
}
