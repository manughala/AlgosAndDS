package com.leetcode.amazon.explore.design;

import java.util.Stack;
/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.


 Example:

 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.

 * @author Santosh Manughala (SM030146).
 */
public class MinStack {
    public static void main(String args[]) {
        MinStack minStack = new MinStack();
        minStack.pop();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Expected: -3, actual: " + minStack.getMin());
        minStack.pop();
        System.out.println("Expected: 0, actual: " + minStack.top());
        System.out.println("Expected: -2, actual: " + minStack.getMin());
//        minStack.popMin();
        System.out.println("Expected: 0, actual: " + minStack.getMin());
    }

    class ListNode {
        int value, min;
        ListNode next;
        ListNode(int value, int min, ListNode next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }
    }
    ListNode head;

    /** initialize your data structure here. */
    public MinStack() {
        head = null;
    }

    public void push(int x) {
        ListNode next = head == null ? null : head;
        head = new ListNode(x, Math.min(x, head == null ? x : head.min), next);
    }

    public void pop() {
         if(head == null) {
             return;
         }

        head = head.next;
    }

    public int top() {
        return head == null ? -1 : head.value;
    }

    public int getMin() {
        return head == null ? -1 : head.min;
    }

//    class ListNode {
//        int value, min;
//        ListNode next;
//        ListNode(int value, int min) {
//            this.value = value;
//            this.min = min;
//        }
//    }
//    ListNode top;
//
//    /** initialize your data structure here. */
//    public MinStack() {
//        top = null;
//    }
//
//    public void push(int x) {
//        ListNode temp = new ListNode(x, Math.min(x, top == null ? x : getMin()));
//
//        if(top == null) {
//            top = temp;
//        } else {
//            temp.next = top;
//            top = temp;
//        }
//    }
//
//    public void pop() {
//        if(top == null) {
//            return;
//        }
//
//        ListNode temp = top.next;
//        top.next = null;
//        top = temp;
//    }
//
//    public int top() {
//        return top.value;
//    }
//
//    public int getMin() {
//        return top.min;
//    }
//
//    public void popMin() {
//
//    }

//    Stack<Integer> stack;
//    Stack<Integer> minStack;
//
//    // Since we are using the java util stack, we get the capacity handling for free here.
//    public MinStack() {
//        stack = new Stack();
//        minStack = new Stack();
//    }
//
//    // At every insertion it will find the min and push to minStack.
//    // So if we get 4, 5, 3, 1 -> the stack has 4 ,5, 3, 1 and minStack has 4, 4, 3, 1
//    // Time: O(1)
//    public void push(int x) {
//        int min = minStack.isEmpty() ? x : minStack.peek();
//        minStack.push(x < min ? x : min);
//        stack.push(x);
//    }
//
//    // Time: O(1)
//    public void pop() {
//        minStack.pop();
//        stack.pop();
//    }
//
//    // Time: O(1)
//    public int top() {
//        return stack.peek();
//    }
//
//    // Time: O(1)
//    public int getMin() {
//        return minStack.peek();
//    }
//
//    // Time: O(N) -> n is number of operations performed so far
//    // in order to reduce this to logN, we can use doubly linked list
//    public void popMin() {
//        int min = minStack.peek();
////        int min = getMin();
//
//        Stack<Integer> temp = new Stack<>();
//        while(stack.peek() != min) {
//            temp.push(stack.peek());
//            // It would be nice if pop could return the item thats removed
//            // But since the method signature asked to void it, we call peek.
//            pop();
//        }
//
//        // Its important we call pop and push in this class not the stacks methods,
//        // as we write to stack and minstack simultaneously.
//        pop();
//
//        while(!temp.isEmpty()) {
//            push(temp.pop());
//        }
//    }
}
