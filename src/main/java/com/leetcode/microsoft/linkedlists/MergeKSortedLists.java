package com.leetcode.microsoft.linkedlists;

import java.util.ArrayList;
import java.util.List;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

 Example:

 Input:
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 Output: 1->1->2->3->4->4->5->6

 * @author Santosh Manughala (SM030146).
 */
public class MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }


    public static void main(String args[]) {
        ListNode[] nodes = new ListNode[3];

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);
        nodes[0] = node1;

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);
        nodes[1] = node2;

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);
        nodes[2] = node3;

        System.out.println("Before: ");
        System.out.println("Node1: ");
        printList(node1);
        System.out.println("Node2: ");
        printList(node2);
        System.out.println("Node2: ");
        printList(node3);

        ListNode node = getMergedListBestCase(nodes);
        System.out.println("getMergedListBestCase: ");
        printList(node);

//        node = getMergedListIntermediateCase(node1, node2);
//        System.out.println("getMergedListIntermediateCase: ");
//        printList(node);
//
//        // The recursive method manipulates the give node1, so call this at the end
//        node = getMergedListBestCaseRecur(node1, node2);
//        System.out.println("getMergedListBestCaseRecur: ");
//        printList(node);
    }

    private static void printList(ListNode node) {
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    // Time O(m + n)
    // Space O(m + n)
    private static ListNode getMergedListBestCase(ListNode[] nodes) {
        if(nodes == null || nodes.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0), result = dummy;
        int last = nodes.length - 1;

        while(last != 0){
            int i = 0, j = last;

            while(i < j) {
                nodes[i] = mergeTwoLists(nodes[i], nodes[j]);

                i++;
                j--;

                if(i >= j) {
                    last = j;
                }
            }
        }

        return nodes[0];
    }

    private static ListNode mergeTwoLists(ListNode node1, ListNode node2) {
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
}
