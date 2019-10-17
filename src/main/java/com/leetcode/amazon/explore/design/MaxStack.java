package com.leetcode.amazon.explore.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Design a max stack that supports push, pop, top, peekMax and popMax.

 push(x) -- Push element x onto stack.
 pop() -- Remove the element on top of the stack and return it.
 top() -- Get the element on the top.
 peekMax() -- Retrieve the maximum element in the stack.
 popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 Example 1:
 MaxStack stack = new MaxStack();
 stack.push(5);
 stack.push(1);
 stack.push(5);
 stack.top(); -> 5
 stack.popMax(); -> 5
 stack.top(); -> 1
 stack.peekMax(); -> 5
 stack.pop(); -> 1
 stack.top(); -> 5
 Note:
 -1e7 <= x <= 1e7
 Number of operations won't exceed 10000.
 The last four operations won't be called when stack is empty.

 * @author Santosh Manughala (SM030146).
 */
public class MaxStack {
    // The idea to reduce the time to O(logN) is similar to LRUCache
    // https://leetcode.com/articles/max-stack/
    // We use a TreeMap, with the element as key and linkedlist as value
    // When inserting we insert a node at the end
    // when deleting, we just need to get the node and unlink it -> popmax / for pop we just delete the tail.
    // when reading we can just get the value;
    public static void main(String args[]) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        maxStack.push(5);
        System.out.println("Expected: 5, actual: " + maxStack.top());
        System.out.println("Expected: 5, actual: " + maxStack.popMax());
        System.out.println("Expected: 1, actual: " + maxStack.top());
        System.out.println("Expected: 5, actual: " + maxStack.peekMax());
        System.out.println("Expected: 1, actual: " + maxStack.pop());
        System.out.println("Expected: 5, actual: " + maxStack.top());
    }

    TreeMap<Integer, List<ListNode>> map;
    DoublyLinkedList list;
    public MaxStack() {
        map = new TreeMap();
        list = new DoublyLinkedList();
    }

    // Time: O(logn)
    public void push(int x) {
        ListNode node = list.add(x);
        List<ListNode> nodes = map.getOrDefault(x, new ArrayList<>());
        nodes.add(node);
        map.put(x, nodes);
    }

    // Time: O(logn)
    public int pop() {
        int value = list.pop();

        List<ListNode> listNodes = map.get(value);
        listNodes.remove(listNodes.size() - 1);
        if(listNodes.isEmpty()) {
            map.remove(value);
        }
        return value;
    }

    // Time: O(1)
    public int top() {
        return list.peek();
    }

    // Time: O(logn)
    public int peekMax() {
        return map.lastKey();
    }

    // Time: O(logn)
    public int popMax() {
        int max = list.peek();
        List<ListNode> nodes = map.get(max);
        ListNode node = nodes.remove(nodes.size() - 1);
        list.remove(node);

        if(nodes.isEmpty()) {
            map.remove(node);
        }

        return max;
    }



    private class DoublyLinkedList {
        ListNode head, tail;

        DoublyLinkedList() {
            head = new ListNode(0);
            tail = new ListNode(0);
            head.next = tail;
            tail.prev = head;
        }


        private int peek() {
            return tail.prev.value;
        }

        private ListNode add(int value) {
            ListNode node = new ListNode(value);
            node.next = tail;
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;

            return node;
        }

        private int pop() {
            return remove(tail.prev);
        }

        private int remove(ListNode node) {
            node.prev.next = node.next;
            if(node.next != null) {
                node.next.prev = node.prev;
            }
            return node.value;
        }
    }

    private class ListNode {
        int value;
        ListNode next, prev;

        ListNode(int value) {
            this.value = value;
        }
    }

//    public Stack<Integer> stack;
//    public Stack<Integer> maxStack;
//
//    public MaxStack() {
//        this.stack = new Stack<>();
//        this.maxStack = new Stack<>();
//    }
//
//    // Time: O(1)
//    public void push(int x) {
//        int max = maxStack.isEmpty() ? x : maxStack.peek();
//        maxStack.push(x > max ? x : max);
//        stack.push(x);
//    }
//
//    // Time: O(1)
//    public int pop() {
//        maxStack.pop();
//        return stack.pop();
//    }
//
//    // Time: O(1)
//    public int top() {
//        return stack.peek();
//    }
//
//    // Time: O(1)
//    public int peekMax() {
//        return maxStack.peek();
//    }
//
//    // Time: O(N) -> n is number of operations performed so far
//    // in order to reduce this to logN, we can use doubly linked list
//    public int popMax() {
//        int max = peekMax();
//
//        Stack<Integer> temp = new Stack<>();
//        while(max != stack.peek()) {
//            temp.push(pop());
//        }
//
//        pop();
//
//        while (!temp.isEmpty()) {
//            push(temp.pop());
//        }
//
//        return max;
//    }
}
