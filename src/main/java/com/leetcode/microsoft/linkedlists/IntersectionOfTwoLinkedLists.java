package com.leetcode.microsoft.linkedlists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.

 For example, the following two linked lists:


 begin to intersect at node c1.



 Example 1:


 Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 Output: Reference of the node with value = 8
 Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.


 Example 2:


 Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 Output: Reference of the node with value = 2
 Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.


 Example 3:


 Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 Output: null
 Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 Explanation: The two lists do not intersect, so return null.


 Notes:

 If the two linked lists have no intersection at all, return null.
 The linked lists must retain their original structure after the function returns.
 You may assume there are no cycles anywhere in the entire linked structure.
 Your code should preferably run in O(n) time and use only O(1) memory.

 * @author Santosh Manughala (SM030146).
 */
public class IntersectionOfTwoLinkedLists {
    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        ListNode nodeEqualPoint = new ListNode(8);
        nodeEqualPoint.next = new ListNode(4);
        nodeEqualPoint.next.next = new ListNode(5);


        ListNode node1 = new ListNode(4);
        node1.next = new ListNode(1);
        node1.next.next = nodeEqualPoint;

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(0);
        node2.next.next = new ListNode(1);
        node2.next.next.next = nodeEqualPoint;

        System.out.println("Before: ");
        System.out.println("Node1: ");
        printList(node1);
        System.out.println("Node2: ");
        printList(node2);

        ListNode node = getIntersectionBestCase(node1, node2);
        System.out.println("getIntersectionBestCase: ");
        printList(node);

        node = getIntersectionBetterCase(node1, node2);
        System.out.println("getIntersectionBetterCase: ");
        printList(node);

        node = getIntersectionBruteForce(node1, node2);
        System.out.println("getIntersectionBruteForce: ");
        printList(node);
    }

    private static void printList(ListNode node) {
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    // Time O(m + n)
    // Space O(1)
    private static ListNode getIntersectionBestCase(ListNode node1, ListNode node2) {
        if(node1 == null || node2 == null) {
            return null;
        }

        ListNode a = node1;
        ListNode b = node2;

        while(a != b) {
            a = a == null ? node2 : a.next;
            b = b == null ? node1 : b.next;
        }

        return a;
    }

    // Time O(m + n)
    // Space O(m)
    private static ListNode getIntersectionBetterCase(ListNode node1, ListNode node2) {
        if(node1 == null || node2 == null) {
            return null;
        }

        ListNode tempNode1 = node1;
        ListNode tempNode2 = node2;

        Map<Integer, ListNode> map = new HashMap<>();

        while(tempNode1 != null) {
            map.put(tempNode1.val, tempNode1);
            tempNode1 = tempNode1.next;
        }

        while(tempNode2 != null) {
            if(map.containsKey(tempNode2.val) && map.get(tempNode2.val) == tempNode2) {
                return map.get(tempNode2.val);
            }
            tempNode2 = tempNode2.next;
        }

        return null;
    }

    // Time O(m + n)
    // Space O(1)
    private static ListNode getIntersectionBruteForce(ListNode node1, ListNode node2) {
        if(node1 == null || node2 == null) {
            return null;
        }

        ListNode temp = node1;
        int length1 = 0, length2 = 0;

        while(temp != null) {
            length1++;
            temp = temp.next;
        }

        temp = node2;
        while(temp != null) {
            length2++;
            temp = temp.next;
        }

        ListNode tempNode1 = node1;
        ListNode tempNode2 = node2;

        if (length1 > length2) {
            int diff = length1 - length2;
            while(diff > 0) {
                tempNode1 = tempNode1.next;
                diff--;
            }
        } else if (length1 < length2) {
            int diff = length2 - length1;
            while(diff > 0) {
                tempNode2 = tempNode2.next;
                diff--;
            }
        }

        while(tempNode1 != null && tempNode2 != null) {
            if(tempNode1 == tempNode2) {
                return tempNode1;
            }

            tempNode1 = tempNode1.next;
            tempNode2 = tempNode2.next;
        }

        return null;
    }
}
