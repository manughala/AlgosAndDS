package com.leetcode.facebook.linkedlists;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.

 Example:

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:

 Given n will always be valid.

 Follow up:

 Could you do this in one pass?

 * @author Santosh Manughala (SM030146).
 */
public class RemoveNthFromTheEnd {
    private static class ListNode {
        int value;
        ListNode next;
        ListNode(int value) {
            this.value = value;
        }
    }

    public static void main (String arg[]) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        int n = 2;

        if(node == null) {
            return;
        }

//        ListNode head = removeNthFromTheEndBruteForce(node, n);
        ListNode head = removeNthFromTheEndBruteForceBetter(node, n);
//        ListNode head = removeNthFromTheEndBestCase(node, n);

        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    private static ListNode removeNthFromTheEndBruteForce (ListNode node, int n) {
        ListNode head = null, prev = null, temp = null;
        List<Integer> list = new ArrayList<>();

        while (node != null) {
            list.add(node.value);
            node = node.next;
        }

        int j = 0, length = list.size();

        while (j < length) {
            if(j != length - n) {
                temp = new ListNode(list.get(j));
                if(head == null) {
                    head = temp;
                } else {
                    prev.next = temp;
                }
                prev = temp;
            }
            j++;
        }

        return head;
    }

    private static ListNode removeNthFromTheEndBruteForceBetter (ListNode node, int n) {
        ListNode head = node;
        int length = 0;

        while(head != null) {
            length++;
            head = head.next;
        }

        if(length - n < 0) {
            throw new IllegalArgumentException("not possible");
        }

        if(length - n == 0) {
            node = node.next;
            return node;
        }


        int i = 1;
        head = node;
        while(head != null) {
            if(i == length - n) {
                head.next = head.next.next;
            }

            head = head.next;
            i++;
        }

        return node;
    }

    private static ListNode removeNthFromTheEndBestCase (ListNode head, int n) {
       ListNode main = head, ahead = head;

       while(n >= 0) {
           if(ahead == null) {
               throw new IllegalArgumentException("Not possible");
           }

           ahead = ahead.next;
           n--;
       }

       if(ahead == null) {
           head = head.next;
           return head;
       }

       while(ahead.next != null) {
           ahead = ahead.next;
           main = main.next;
       }

       main.next = main.next.next;
       return main;
    }
}