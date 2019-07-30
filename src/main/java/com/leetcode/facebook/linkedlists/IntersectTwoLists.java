package com.leetcode.facebook.linkedlists;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.

 For example, the following two linked lists:
       a1 -> a2 -> c1 -> c2 -> c3 -> c4
 b1 -> b2 -> b3 -> c1 -> c2 -> c3 -> c4

 begin to intersect at node c1.


 Example 1:


 Input:  listA = [4,1,8,4,5], listB = [5,0,1,8,4,5]
 Output: Reference of the node with value = 8


 Example 2:


 Input: listA = [0,9,1,2,4], listB = [3,2,4]
 Output: Reference of the node with value = 2


 Example 3:


 Input: listA = [2,6,4], listB = [1,5]
 Output: null


 Notes:

 If the two linked lists have no intersection at all, return null.
 The linked lists must retain their original structure after the function returns.
 You may assume there are no cycles anywhere in the entire linked structure.
 Your code should preferably run in O(n) time and use only O(1) memory.

 * @author Santosh Manughala (SM030146).
 */
public class IntersectTwoLists {

    public static class ListNode {
        int value;
        ListNode next;
        ListNode(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object node1) {
            if(node1 instanceof ListNode && ((ListNode) node1).value == value) {
                if(((ListNode) node1).next == next) {
                    return true;
                } else {
                    return ((ListNode) node1).next.equals(next);
                }
            }
            return false;
        }
    }

    // NOTE TO SELF: intersection of two singly linked lists begins
    // This means that we have to find where the linked list reference start
    // Not the value
    public static void main (String arg[]) {
        ListNode nodeEqualPoint = new ListNode(6);
        nodeEqualPoint.next = new ListNode(7);
        nodeEqualPoint.next.next = new ListNode(8);
        nodeEqualPoint.next.next.next = new ListNode(9);


        ListNode node1 = new ListNode(4);
        node1.next = new ListNode(1);
        node1.next.next = new ListNode(8);
        node1.next.next.next = nodeEqualPoint;

        ListNode node2 = new ListNode(4);
        node2.next = new ListNode(5);
        node2.next.next = nodeEqualPoint;

//        ListNode node2 = new ListNode(5);
//        node2.next = new ListNode(0);
//        node2.next.next = new ListNode(1);
//        node2.next.next = new ListNode(8);
//        node2.next.next.next = new ListNode(4);
//        node2.next.next.next.next = new ListNode(5);

        if(node1 == null || node2 == null) {
            return;
        }

//        System.out.println(node1.equals( node2));

//        ListNode head = getIntersectionNodeBruteForce(node1, node2);
//        ListNode head = getIntersectionNodeBetterCase(node1, node2);
        ListNode head = getIntersectionNodeBestCase(node1, node2);
//        ListNode head = copied(node1, node2);

        if (head != null) {
            System.out.println("Intersection found at " + head.value);
            while (head!= null) {
                System.out.println(head.value);
                head = head.next;
            }
            return;
        }

//        System.out.println("Did not find intersection");
//        return;/
    }

    /*
   Approach 3: Two Pointers
Maintain two pointers pApA and pBpB initialized at the head of A and B, respectively. Then let them both traverse through the lists, one node at a time.
When pApA reaches the end of a list, then redirect it to the head of B (yes, B, that's right.); similarly when pBpB reaches the end of a list, redirect it the head of A.
If at any point pApA meets pBpB, then pApA/pBpB is the intersection node.
To see why the above trick would work, consider the following two lists: A = {1,3,5,7,9,11} and B = {2,4,9,11}, which are intersected at node '9'. Since B.length (=4) < A.length (=6), pBpB would reach the end of the merged list first, because pBpB traverses exactly 2 nodes less than pApA does. By redirecting pBpB to head A, and pApA to head B, we now ask pBpB to travel exactly 2 more nodes than pApA would. So in the second iteration, they are guaranteed to reach the intersection node at the same time.
If two lists have intersection, then their last nodes must be the same one. So when pApA/pBpB reaches the end of a list, record the last element of A/B respectively. If the two last elements are not the same one, then the two lists have no intersections.
Complexity Analysis

Time complexity : O(m+n)O(m+n).

Space complexity : O(1)O(1).
     */
    private static ListNode getIntersectionNodeBestCase(ListNode headA, ListNode headB){
        if(headA == null || headB == null) {
            return null;
        }

        ListNode a = headA;
        ListNode b = headB;

        while(a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }

    // Actualy brute force would be to use 2 loops and get it done
    // Here this appraoch is a little better as we use O(m+n)
    private static ListNode getIntersectionNodeBruteForce(ListNode headA, ListNode headB) {
        ListNode temp = null, headATemp = null, headBTemp = null;
        int lengthA = 0, lengthB = 0;

        temp = headA;
        while(temp != null) {
            lengthA++;
            temp = temp.next;
        }

        temp = headB;
        while(temp != null) {
            lengthB++;
            temp = temp.next;
        }

        int diff = 0;

        headATemp = headA;
        headBTemp = headB;

        if(lengthA > lengthB) {
            diff = lengthA - lengthB;
            while (diff > 0) {
                headATemp = headATemp.next;
                diff--;
            }
        } else {
            diff = lengthB - lengthA;
            while (diff > 0) {
                headBTemp = headBTemp.next;
            }
            diff--;
        }

        while(headATemp != null && headBTemp != null) {
            if(headATemp == headBTemp) {
                return headATemp;
            }

            headATemp = headATemp.next;
            headBTemp = headBTemp.next;
        }

        return null;
    }

    private static ListNode getIntersectionNodeBetterCase(ListNode headA, ListNode headB) {
        ListNode headATemp = headA, headBTemp = headB;
        HashMap<Integer, ListNode> valToListNode = new HashMap<> ();

        while(headATemp != null) {
            valToListNode.put(headATemp.value, headATemp);
            headATemp = headATemp.next;
        }

        while(headBTemp != null) {
            if(valToListNode.containsKey(headBTemp.value) && valToListNode.get(headBTemp.value).equals(headBTemp)) {
                return headBTemp;
            }

            headBTemp = headBTemp.next;
        }

        return null;

    }
}
