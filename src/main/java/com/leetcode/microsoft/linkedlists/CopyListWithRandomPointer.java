package com.leetcode.microsoft.linkedlists;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

 Return a deep copy of the list.

 Example 1:

 Input:
 {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

 Explanation:
 Node 1's value is 1, both of its next and random pointer points to Node 2.
 Node 2's value is 2, its next pointer points to null and its random pointer points to itself.


 Note:

 You must return the copy of the given head as a reference to the cloned list.

 * @author Santosh Manughala (SM030146).
 */
public class CopyListWithRandomPointer {

    public static class ListNode{
        ListNode next;
        int val;
        ListNode random;

        public ListNode() {
        }

        ListNode(int val, ListNode next, ListNode random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }

    public static void main(String args[]) {
        ListNode node2 = new ListNode();
        ListNode node1 = new ListNode(1, node2, node2);

        node2.val = 2;
        node2.random = node2;


        System.out.println("node1: ");
        printList(node1);

        ListNode copy = copyList(node1);
        System.out.println("copyList: ");
        printList(copy);

        copy = copyListRecur(node1);
        System.out.println("copyListRecur: ");
        printList(copy);

        copy = copyListBestCase(node1);
        System.out.println("copyListBestCase: ");
        printList(copy);
    }

    private static void printList(ListNode node) {
        while(node != null) {
            System.out.println("val = " + node.val);
            int random = (node.random == null) ? 0 : node.random.val;
            System.out.println("random = " + random);
            int next = (node.next == null) ? 0 : node.next.val;
            System.out.println("next = " + next);
            node = node.next;
        }
    }

    // Time O(n)
    // Space O(1)
    private static ListNode copyListBestCase(ListNode node) {
        if(node == null) {
            return null;
        }

        ListNode tempOldNode = node;

        // A -> B -> C ===> A -> A` -> B -> B` -> C -> C`
        while(tempOldNode != null) {
            ListNode newNode = new ListNode(tempOldNode.val, null, null);

            newNode.next = tempOldNode.next;
            tempOldNode.next = newNode;
            tempOldNode = newNode.next;
        }

        tempOldNode = node;

        while(tempOldNode != null) {
            tempOldNode.next.random = tempOldNode.random != null ? tempOldNode.random.next : null;
            tempOldNode = tempOldNode.next.next;
        }

        ListNode oldNode = node, newNode = node.next, result = node.next;

        // A -> A` -> B -> B` -> C -> C` ===> A -> B -> C
        while(oldNode != null) {
            oldNode.next = oldNode.next.next;
            newNode.next = newNode.next == null ? null : newNode.next.next;

            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return result;
    }

    // Time O(n)
    // Space O(n)
    private static Map<ListNode, ListNode> map = new HashMap<>();
    private static ListNode copyListRecur(ListNode node) {
        if(node == null) {
            return null;
        }

        if(map.containsKey(node)) {
            return map.get(node);
        }

        ListNode copyNode = new ListNode(node.val, null, null);
        map.put(node, copyNode);

        copyNode.random = copyListRecur(node.random);
        copyNode.next = copyListRecur(node.next);

        return node;
    }

    // Time O(n)
    // Space O(n)
    private static ListNode copyList(ListNode node) {
        if(node == null) {
            return null;
        }

        ListNode copyNode = node;
        Map<ListNode, ListNode> map = new HashMap<>();

        map.put(copyNode, new ListNode(copyNode.val, null, null));
        ListNode newNode = map.get(node);

        while(copyNode != null) {
            newNode.random = getCopiedNode(copyNode.random, map);
            newNode.next = getCopiedNode(copyNode.next, map);

            newNode = newNode.next;
            copyNode = copyNode.next;
        }

        return map.get(node);

    }

    private static ListNode getCopiedNode(ListNode node, Map<ListNode, ListNode> map) {
        if(node == null) {
            return null;
        }

        ListNode temp = map.getOrDefault(node, new ListNode(node.val, null, null));
        map.put(node, temp);

        return temp;
    }
}
