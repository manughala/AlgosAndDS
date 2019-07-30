package com.leetcode.microsoft.linkedlists;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

 Example:

 Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4

 * @author Santosh Manughala (SM030146).
 */
public class MergeTwoSortedLists {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(4);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);
        node2.next.next.next = new ListNode(8);;

        System.out.println("Before: ");
        System.out.println("Node1: ");
        printList(node1);
        System.out.println("Node2: ");
        printList(node2);

        ListNode node = getMergedListBestCase(node1, node2);
        System.out.println("getMergedListBestCase: ");
        printList(node);

        node = getMergedListIntermediateCase(node1, node2);
        System.out.println("getMergedListIntermediateCase: ");
        printList(node);

        // The recursive method manipulates the give node1, so call this at the end
        node = getMergedListBestCaseRecur(node1, node2);
        System.out.println("getMergedListBestCaseRecur: ");
        printList(node);
    }

    private static void printList(ListNode node) {
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    // Time O(m + n)
    // Space O(m + n)
    private static ListNode getMergedListIntermediateCase(ListNode node1, ListNode node2) {
        if(node1 == null && node2 == null) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });

        ListNode dummy = new ListNode(0), result = dummy;
        queue.add(node1);
        queue.add(node2);

        while(!queue.isEmpty()) {
            ListNode node = queue.poll();
            dummy.next = new ListNode(node.val);
            dummy = dummy.next;

            if(node.next != null) {
                queue.add(node.next);
            }
        }

//         Another way to do this:
//        while(node1 != null) {
//            queue.add(node1);
//            node1 = node1.next;
//        }
//
//        while(node2 != null) {
//            queue.add(node2);
//            node2 = node2.next;
//        }
//
//        ListNode dummy = new ListNode(queue.poll().val), result = dummy;
//        while(!queue.isEmpty()) {
//            dummy.next = new ListNode(queue.poll().val);
//            dummy = dummy.next;
//        }

        return result.next;
    }

    // Time O(m + n)
    // Space O(1)
    private static ListNode getMergedListBestCase(ListNode node1, ListNode node2) {
        if(node1 == null && node2 == null) {
            return null;
        }

        ListNode dummy = new ListNode(0), result = dummy;
        while(node1 != null && node2 != null) {
            if(node1.val < node2.val) {
                dummy.next = new ListNode(node1.val);
                node1 = node1.next;
            } else {
                dummy.next = new ListNode(node2.val);
                node2 = node2.next;
            }

            dummy = dummy.next;
        }

        dummy.next = node1 == null ? node2 : node1;
        return result.next;
    }

    // Time O(m + n)
    // Space O(m + n)
    private static ListNode getMergedListBestCaseRecur(ListNode node1, ListNode node2) {
        return recurMerge(node1, node2);
    }

    private static ListNode recurMerge(ListNode node1, ListNode node2) {
        if(node1 == null) {
            return node2;
        }

        if(node2 == null) {
            return node1;
        }

        if(node1.val < node2.val) {
            node1.next = recurMerge(node1.next, node2);
            return node1;
        } else {
            node2.next = recurMerge(node1, node2.next);
            return node2;
        }
    }
}
