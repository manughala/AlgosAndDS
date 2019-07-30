package com.leetcode.facebook.linkedlists;

/**
 * Reverse a singly linked list.

 Example:

 Input: 1->2->3->4->5->NULL
 Output: 5->4->3->2->1->NULL
 Follow up:

 A linked list can be reversed either iteratively or recursively. Could you implement both?

 * @author Santosh Manughala (SM030146).
 */
public class ReverseLinkedLists {

    public static class LinkedList {
        int value;
        LinkedList next;

        public LinkedList(int value) {
            this.value = value;
        }
    }

    public static void main(String args[]) {
        LinkedList input = new LinkedList(1);
        input.next = new LinkedList(2);
        input.next.next = new LinkedList(3);
        input.next.next.next = new LinkedList(4);
        input.next.next.next.next = new LinkedList(5);

        if(input == null) {
            System.out.println("the input is null: " + input);
            return;
        }

        if(input.next == null) {
            System.out.println("the list has only one element: " + input);
            return;
        }

//        LinkedList result = getReverseIteratively(input);
        LinkedList result = getReverseRecursively(input);

        while (result != null) {
            System.out.println(result.value);
            result = result.next;
        }
    }


    // Time : O(n) Space O(1)
    private static LinkedList getReverseIteratively(LinkedList head) {
        LinkedList current = head, next = null, prev = null;

        while(current != null) {
            next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }

        return prev;
    }

    // Time : O(n) Space O(1)
    private static LinkedList getReverseRecursively(LinkedList head) {
        return reverseRecurse(head, null);
    }

    private static LinkedList reverseRecurse (LinkedList current, LinkedList prev) {
        LinkedList next = current.next;
        current.next = prev;

        return next == null ? current : reverseRecurse(next, current);
    }
}
