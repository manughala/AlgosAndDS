package com.leetcode.facebook.sortingandsearching;

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

        ListNode node3 = new ListNode(1);
        node3.next = new ListNode(2);
        node3.next.next = new ListNode(3);
        node3.next.next.next = new ListNode(4);
        node3.next.next.next.next = new ListNode(5);

        ListNode[] nodes = new ListNode[] {node1, node2, node3};

//        ListNode result = mergeKSortedListsBestCaseI(nodes);
//        ListNode result = mergeKSortedListsBestCaseII(nodes);
        ListNode result = mergeKSortedListsBestCaseIII(nodes, nodes.length -1); // this takes the same time as others, but is the best space one.

        while(result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }


    // TIme O(nk Log nk)
    //Space  O(1)
    private static ListNode mergeKSortedListsBestCaseIII(ListNode[] nodes, int last) {

        while(last != 0) {
            int i = 0, j = last;

            while (i < j) {
                nodes[i] = mergeIter(nodes[i], nodes[j]);
//                nodes[i] = mergeRecur(nodes[i], nodes[j]); NOTE: we can use either the recur or iterative version of merging 2 sorted lists

                i++;
                j--;

                if(i >= j) {
                    last = j;
                }
            }
        }

        return nodes[0];
    }

    private static ListNode mergeRecur(ListNode node1, ListNode node2) {
        ListNode node = null;
        if (node1 == null) {
            return node2;
        } else if(node2 == null) {
            return node1;
        }

        if(node1.val <= node2.val) {
            node = node1;
            node.next = mergeRecur(node1.next, node2);
        } else {
            node = node2;
            node.next = mergeRecur(node1, node2.next);
        }

        return node;
    }

    private static ListNode mergeIter(ListNode node1, ListNode node2) {
        if(node1 == null) {
            return node2;
        }

        if(node2 == null) {
            return node1;
        }

        ListNode dummy = new ListNode(0), result = dummy;

        while(node1 != null && node2 != null) {
            if(node1.val < node2.val) {
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

    // TIme O(nk Log nk)
    //Space  O(nk)
    private static ListNode mergeKSortedListsBruteForce(ListNode[] nodes) {
        // We could create an array of length n*k
        // We have no way of know n, so we have to do like a integer max or soemthing size array
        // then sort the array
        // Create linked list out of array.
        return null;
    }

    // Time O(nklognk) The main step is while loop, the loop runs n*k times. In every iteration of loop, we call heapify which takes O(Logk) time.
    // Therefore, the time complexity is O(nk Logk).
    // Space (nk) we store k nodes of lenght n
    //NOTE THIS USES Priority queue but add to the queue is not efficient check bestcaseII
    private static ListNode mergeKSortedListsBestCaseI(ListNode[] nodes) {
        if(nodes == null || nodes.length == 0) {
            return null;
        }

        if(nodes.length == 1) {
            return nodes[0];
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(nodes.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });


        for(ListNode node : nodes) {
            queue.add(node);
            while (node.next != null) {
                queue.add(node.next);
                node = node.next;
            }
        }

        ListNode dummy = new ListNode(0), result = dummy;
        while (!queue.isEmpty()) {
            dummy.next = new ListNode(queue.poll().val);
            dummy = dummy.next;
        }

        return result.next;
    }

    // Time O(nklognk) The main step is while loop, the loop runs n*k times. In every iteration of loop, we call heapify which takes O(Logk) time.
    // Therefore, the time complexity is O(nk Logk).
    // Space (nk) we store k nodes of length n each
    private static ListNode mergeKSortedListsBestCaseII(ListNode[] nodes) {
        if(nodes == null || nodes.length == 0) {
            return null;
        }

        if(nodes.length == 1) {
            return nodes[0];
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(nodes.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });


        for(ListNode node : nodes) {
            if(node != null) {
                queue.add(node);
            }
        }

        ListNode dummy = new ListNode(0), result = dummy;
//        ListNode head = null, result = null;
        while (!queue.isEmpty()) {
            ListNode temp = queue.poll();

            if(temp.next != null) {
                queue.add(temp.next);
            }

            dummy.next = new ListNode(temp.val);
            dummy = dummy.next;
            /*
            Another way to add to the list:
            Create ListNode head = null, result = null in line 114 before while loop
            then here
            Should return result not next of result.
            if(head == null) {
                head = temp;
                result = head;
            } else {
                head.next = temp;
                head = head.next;
            }
            */
        }

        return result.next;
    }
}
