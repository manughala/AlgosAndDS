package com.leetcode.linkedin;

import com.leetcode.facebook.linkedlists.AddTwoNumbers;

import java.util.Comparator;
import java.util.PriorityQueue;

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

        ListNode node3 = new ListNode(5);
        node2.next = new ListNode(6);

        ListNode[] listNodes = new ListNode[] {node1, node2, node3};

        ListNode result = mergeKSortedLists(listNodes);

        while(result != null) {
            System.out.println(result.value);
            result = result.next;
        }
    }

    // Wrote only best case here -> check com.leetcode.facebook.sortingandsearching for all possible cases.
    private static ListNode mergeKSortedLists(ListNode[] listNodes) {
        if(listNodes == null || listNodes.length == 0) {
            return null;
        }

        if(listNodes.length == 1) {
            return listNodes[0];
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.value - l2.value;
            }
        });

        for(ListNode node : listNodes) {
            if(node != null) {
                queue.add(node);
            }
        }

        ListNode result = new ListNode(0), dummy = result;

        while (!queue.isEmpty()) {
            ListNode temp = queue.poll();

            if(temp.next != null) {
                queue.add(temp.next);
            }

            dummy.next = new ListNode(temp.value);
            dummy = dummy.next;
        }
        return result.next;
    }
}
