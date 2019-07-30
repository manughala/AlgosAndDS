package com.leetcode.microsoft.linkedlists;

import java.util.HashSet;
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

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String args[]) {
        ListNode node = new ListNode(5);
        node.next = new ListNode(0);
        node.next.next = new ListNode(1);
        node.next.next.next =  new ListNode(6, node);

        System.out.println("hasCycleBruteForce: " + hasCycleBruteForce(node));
        System.out.println("hasCycleBestCase: " + hasCycleBestCase(node));

        node = new ListNode(5);
        node.next = new ListNode(0);
        node.next.next = new ListNode(1);
        node.next.next.next =  new ListNode(6);

        System.out.println("hasCycleBruteForce: " + hasCycleBruteForce(node));
        System.out.println("hasCycleBestCase: " + hasCycleBestCase(node));

    }

    private static boolean hasCycleBruteForce (ListNode node) {
        if(node == null) {
            return false;
        }

        Set<ListNode> nodes = new HashSet<>();

        while(node != null) {
            if(nodes.contains(node)) {
                return true;
            }
            nodes.add(node);
            node = node.next;
        }

        return false;
    }

    private static boolean hasCycleBestCase (ListNode node) {
        if(node == null) {
            return false;
        }

        ListNode firstPtr = node, secondPtr = node.next;

        while (firstPtr != secondPtr) {
            if(secondPtr == null || secondPtr.next == null) {
                return false;
            }

            firstPtr = firstPtr.next;
            secondPtr = secondPtr.next.next;
        }

        return true;
    }

}
