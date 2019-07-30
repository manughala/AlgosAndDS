package com.leetcode.facebook.sortingandsearching;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

 Example:

 Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4

 * @author Santosh Manughala (SM030146).
 */
public class Merge2SortedLists {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(-10);
        node1.next = new ListNode(-10);
        node1.next.next = new ListNode(-9);
        node1.next.next.next = new ListNode(-4);
        node1.next.next.next.next = new ListNode(1);
        node1.next.next.next.next.next = new ListNode(6);
        node1.next.next.next.next.next.next = new ListNode(6);

        ListNode node2 = new ListNode(-6);

//        ListNode result = mergeTwoListsBestCaseI(node1, node2);
        ListNode result = mergeTwoListsBestCaseIRecursive(node1, node2);
//        ListNode result = mergeTwoListsBestCaseII(node1, node2);
//        ListNode result = mergeTwoListsBestCaseI(null, new ListNode(0));

        while(result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    // Time: O(m+n) Space: O(1) m -> node1 has m links and n -> node2 has n links; space 1 cos we only have one result node with multiple pointers
    private static ListNode mergeTwoListsBestCaseI(ListNode node1, ListNode node2) {
        ListNode result = new ListNode(0), head = result;

        if(node1 == null && node2 != null) {
            return node2;
        }

        if(node2 == null && node1 != null) {
            return node1;
        }

        while(node2 != null && node1 != null) {
            if(node1.val < node2.val) {
                result.next = node1;
                node1 = node1.next;
            } else {
                result.next = node2;
                node2 = node2.next;
            }

            result = result.next;
        }


        result.next = node1 == null ? node2 : node1;

        return head.next;
    }

    // Time: O(m+n) Space: O(m+n) m -> node1 has m links and n -> node2 has n links; space m+n cos recursion uses stack internally to store
    private static ListNode mergeTwoListsBestCaseIRecursive(ListNode node1, ListNode node2) {
        if(node1 == null) {
            return node2;
        }

        if(node2 == null) {
            return node1;
        }

        if(node1.val < node2.val) {
            node1.next = mergeTwoListsBestCaseIRecursive(node1.next, node2);
            return node1;
        } else {
            node2.next = mergeTwoListsBestCaseIRecursive(node1, node2.next);
            return node2;
        }
    }

    // Time: O(m+n) Space: O(m+n) m -> node1 has m links and n -> node2 has n links; space m+n cos we store all the nodes in the queue.
    private static ListNode mergeTwoListsBestCaseII(ListNode node1, ListNode node2) {
        if(node1 == null && node2 == null) {
            return null;
        }

        if(node1 == null && node2 != null) {
            return node2;
        }

        if(node2 == null && node1 != null) {
            return node1;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        });

        while(node1 != null) {
            queue.add(node1);
            node1 = node1.next;
        }

        while(node2 != null) {
            queue.add(node2);
            node2 = node2.next;
        }

        ListNode head = queue.poll();
        ListNode result = head;

        while(!queue.isEmpty()) {
            // if i dont create a new list node for this, when we have 6, 6 repeated values for any of the linked list,
            // the result will just be looping around 6-> since the node 6 is already there, there is a circular link created.
            head.next = new ListNode(queue.poll().val);
            head = head.next;
        }
        return result;
    }
}
