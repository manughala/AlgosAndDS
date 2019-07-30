package com.leetcode.facebook.linkedlists;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given a linked list, determine if it has a cycle in it.

 To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.



 Example 1:

 Input: head = [3,2,0,-4], pos = 1
 Output: true
 Explanation: There is a cycle in the linked list, where tail connects to the second node.


 Example 2:

 Input: head = [1,2], pos = 0
 Output: true
 Explanation: There is a cycle in the linked list, where tail connects to the first node.


 Example 3:

 Input: head = [1], pos = -1
 Output: false
 Explanation: There is no cycle in the linked list.




 Follow up:

 Can you solve it using O(1) (i.e. constant) memory?

 * @author Santosh Manughala (SM030146).
 */
public class LinkedListCycle {

    private static class ListNode {
        int value;
        ListNode next;
        ListNode(int value) {
            this.value = value;
        }

        ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main (String arg[]) {
        ListNode node = new ListNode(3);
        node.next = new ListNode(2);
        node.next.next = new ListNode(0);
        node.next.next.next = new ListNode(-4);
        node.next.next.next = new ListNode(2, node);

        if(node == null) {
            return;
        }

//        boolean result = hasCycleBruteForce(node);
        boolean result = hasCycleBetter(node);

        // NOTE: dont print it, cos introduced a cycle in the list for testing
//        while(node != null) {
//            System.out.println(node.value);
//            node = node.next;
//        }

        System.out.println("has cycle: " + result);
        return;
    }

    // Time O(n) space O(n)
    // For brute force we can also use 2 loops and a copy of list node
    private static boolean hasCycleBruteForce(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    private static boolean hasCycleBetter(ListNode node) {
        if(node == null || node.next == null) {
            return false;
        }

        ListNode firstPtr = node, secondPtr = node.next;

        while(firstPtr != secondPtr) {
            if(secondPtr == null || secondPtr.next == null) {
                return false;
            }

            firstPtr = firstPtr.next;
            secondPtr = secondPtr.next.next;
        }

        return true;
    }


}
